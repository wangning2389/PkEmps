function EvensForm() {

}
EvensForm.prototype.EVENS = "div[evens]";
EvensForm.prototype.init = function() {
	EvensForm.prototype.initSelect();
};

EvensForm.prototype.getFormAsData = function() {
	var data = {};
	$("[name]", EvensForm.prototype.EVENS).each(function(k, v) {
		var jqE = $(this);
		var name = jqE.attr("name");
		var value = jqE.val();
		if (value != undefined && value != "") {
			data[name] = value;
		}
		jqE = null;
	});
	return data;
};
// ////表单参数提取工具
EvensForm.prototype.getFormAsParam = function(prefix) {
	var data = EvensForm.prototype.getFormAsData();
	return EvensForm.prototype.dataAsParamStr(prefix, data);
};

EvensForm.prototype.dataAsParamStr = function(prefix, formData) {
	var localFormData = formData;
	if (!localFormData) {
		localFormData = EvensForm.prototype.getFormData();
	}
	var prefiexedParam = localFormData;
	if (prefix) {
		prefiexedParam = new Object();
		prefiexedParam[prefix] = localFormData;
	}
	var paramStr = EvensForm.prototype.toParamStr(prefiexedParam);
	return paramStr;
};

EvensForm.prototype.listDataAsParamStr = function(formData,prefix) {
	if (prefix == undefined) {
		prefix = "";
	}
	var result = new Array();
 
	$.each(formData, function(k, v) {  
		var line = '';
		if(v.length!=undefined){ 
			$.each(v, function(k1, v1) {  
				if (prefix) {
					line = prefix + "." + k + "=" + v1;
				} else {
					line = k + "=" + v1; 
				} 
				result.push(line);
			});
		} else { 
			if (prefix) {
				line = prefix + "." + k + "=" + v;
			} else {
				line = k + "=" + v;
				 
			} 
			result.push(line);
		} 
	});
	return result ;
};

EvensForm.prototype.toParamStr = function(param) {
	var paramStr = $.param(param);
	paramStr = decodeURIComponent(paramStr);
	paramStr = paramStr.replace(/\[/g, '.');
	paramStr = paramStr.replace(/\]/g, '');
	return paramStr;
}
// /////////////form populating
EvensForm.prototype.populate = function(data) {
	if (!data) {
		return;
	}
	EvensForm.prototype.formatForRender(data);
	$("[name]", EvensForm.prototype.EVENS).each(function(k, v) {
		var jqE = $(this);
		var name = jqE.attr("name");
		var value = data[name]; 

		if (jqE.is("input")) {
			EvensForm.prototype.populateText(jqE, value);
		} else if (jqE.is("select")) {
			EvensForm.prototype.populateSelect(jqE, value);
		}

		jqE = null;
	});
};
EvensForm.prototype.formatForRender =function(obj){
	$.each(obj,function(k,v){
		if(v&&v.getMonth){
			v = mini.formatDate(v,"yyyy-MM-dd HH:mm:ss");
			obj[k] = v;
		}
	});
	
};
EvensForm.prototype.populateText = function(ele, value) { 
	var jqE = $(ele); 
	var param = jqE.attr("label"); 
	var paramConfig = EvensForm.prototype.toolParseParam(param); 
	
	if(param!=undefined ){ 
		var newE = $("<div></div>").html(value).attr("class","display-label");
		if(paramConfig.width){
			newE.css("width",paramConfig.width);
		}
		jqE.replaceWith(newE); 
	} else{
		jqE.val(value);
		jqE = null;
	}
	

};

EvensForm.prototype.populateSelect = function(ele, value) {
	var jqE = $(ele);

	if ($("option", ele).length == 0) {
		jqE.data("populateValue", value);
	} else {
		jqE.val(value);
		EvensForm.prototype.setSelectReadonly(ele);
	}
	jqE = null;
};

// select 工具
EvensForm.prototype.initSelect = function() {
	$("select[url]", EvensForm.prototype.EVENS).each(function(k, v) {
		var jqE = $(this);
		var url = jqE.attr("url");

		$.ajax({
			type : 'GET',
			url : url
		}).done(function(data, textStatus, jqXHR) {
			EvensForm.prototype.renderSelect(jqE, data);
		}).fail(function(jqXHR, textStatus, errorThrown) {

		});
	});

	// 填充select
	$("select[dicCode]").each(function(k, v) {
		var jqE = $(this);
		var code = jqE.attr("name");
		jqE = null;
		var data = CodeManager.prototype.getCodeList(code);
		EvensForm.prototype.renderSelectWithCode(this, data);
	});
};

EvensForm.prototype.renderSelect = function(ele, options) {
	var jqE = $(ele);
	if (jqE.is(".label")) {
		jqE = null;
		return;
	}

	var name = jqE.attr("name");

	var populateValue = jqE.data("populateValue");

	var newSel = jqE.clone().html("<option></option>");
	$.each(options, function(k, v) {
		var opt = $("<option></option>").attr("value", v.code).html(v.text);
		opt.appendTo(newSel);
	});
	if (populateValue != undefined && populateValue != "") {
		newSel.val(populateValue);

	}
	jqE.replaceWith(newSel);
	EvensForm.prototype.setSelectReadonly(newSel);
	jqE = null;
};

EvensForm.prototype.renderSelectWithCode = function(ele, data) {
	var jqE = $(ele);
	if (jqE.is(".label")) {
		jqE = null;
		return;
	}

	var populateValue = jqE.data("populateValue");
	var newSel = jqE.clone().html("<option></option>");
	$.each(data, function(k, v) {
		var opt = $("<option></option>").attr("value", v.code).html(v.text);
		opt.appendTo(newSel);
	});
	if (populateValue != undefined && populateValue != "") {
		newSel.val(populateValue);

	}
	jqE.replaceWith(newSel);
	EvensForm.prototype.setSelectReadonly(newSel);
	jqE = null;
}
// ////////common tools
EvensForm.prototype.toolParseParam = function(paramStr) {
	if (!paramStr) {
		return null;
	}
	var paramArr = paramStr.split(",");
	var param = new Object();
	$.each(paramArr, function(k, v) {
		var pair = v.split("=");
		param[pair[0]] = pair[1];
	});
	return param;

};
EvensForm.prototype.setReadonly = function() {
	$("[name]", EvensForm.prototype.EVENS).each(function(k, v) {
		$(this).attr("readOnly", "readOnly");
	});
};
EvensForm.prototype.setSelectReadonly = function(ele) {
	var jqE = $(ele);
	if (!jqE.is("[readOnly]")) {
		jqE = null;
		return;
	}
	var text = jqE.find("option:selected").text();
	if (!text) {
		return;
	}
	var span = $("<div></div>");
	span.attr("class", "label");
	span.html(text);
	jqE.replaceWith(span);
	jqE = null;
	span = null;
};