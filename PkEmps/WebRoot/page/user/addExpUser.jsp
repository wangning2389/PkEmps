<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" 
    pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>专家信息登记</title>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<script src="${pageContext.request.contextPath}/resources/js/swfupload.js" type="text/javascript"></script>
		
		<style type="text/css">
html,body {
	font-size: 12px;
	padding: 0;
	margin: 0;
	border: 0;
	height: 100%;
}
</style>
	</head>
	<jsp:include page="../default.jsp"></jsp:include>
	<body>
		<fieldset
			style="border: solid 1px #aaa; padding: 3px; ">
			<legend>
				专家基础信息
			</legend>
			<form id="form1" method="post">
				<input name="id" class="mini-hidden"/>
				<div style="padding-left: 11px; padding-bottom: 5px;">
					<table>
						<tr height="34">
							<td  align="right" nowrap="nowrap">
								<span style="color:red;">*</span>姓名：
							</td>
							<td >
								<input name="name" class="mini-textbox" maxlength="20"id="name" required="true"requiredErrorText="请输入姓名"
									style="width: 185px;" />
							</td>
							<td  align="right" nowrap="nowrap">
								<span style="color:red;">*</span>性别：
							</td>
							<td >
								<input name="sex" class="mini-combobox" valueField="code" required="true"
									textField="text" id="sex" url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=sex"
									style="width: 185px;" />
							</td>
							<td  align="right" nowrap="nowrap">
								<span style="color:red;">*</span>出生日期：
							</td>
							<td >
								<input name="birthday" class="mini-datepicker"  required="true"
									id="birthday" 
									style="width: 185px;" />
							</td>
						
						</tr>
	
							<tr height="34">
							
							
								<td  align="right" nowrap="nowrap">
									<span style="color:red;">*</span>民族：
								</td>
								<td >
									<input name="nation" class="mini-combobox" valueField="code"  required="true"
										textField="text" id="nation"  url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=nation"
										style="width: 185px;" />
								</td>
								<td  align="right" nowrap="nowrap">
									<span style="color:red;">*</span>国籍：
								</td>
								<td >
									<input name="country" class="mini-combobox" valueField="code"  required="true" allowInput="true"  valueFromSelect="true"
										textField="text" id="country"  url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=country"
										style="width: 185px;" />
								</td>
								<td  align="right" nowrap="nowrap">
									<span style="color:red;">*</span>政治面貌：
								</td>
								<td >
									<input name="polic" class="mini-combobox" style="width: 185px;"id="polic" valueField="code"textField="text"
									 url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=polic"  required="true"
										 />
								</td>
							</tr>


							<tr height="34">
								<td  align="right" nowrap="nowrap">
									<span style="color:red;">*</span>证件类型：
								</td>
								<td >
									<input name="idtype" class="mini-combobox" style="width: 185px;"id="idtype" 
									valueField="code"textField="text"  required="true"
									url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=idType"
										 />
								</td>
								<td  align="right" nowrap="nowrap">
								<span style="color:red;">*</span>证件号码：
								</td>
								<td >
									<input name="idcode" class="mini-textbox"id="idcode"  required="true"
										onvalidation="onNumber" maxlength="18" style="width: 185px;"
										 />
								</td>
								<td  align="right" nowrap="nowrap">
								籍贯：
								</td>
								<td >
									<input name="nativeplace" class="mini-textbox"id="nativeplace" 
										maxlength="25" style="width: 185px;"
										 />
								</td>
							</tr>
	
						<tr height="34">
							<td  align="right" nowrap="nowrap">
								出生地：
								</td>
								<td >
									<input name="birplace" class="mini-textbox" id="birplace" 
										 maxlength="50" style="width: 185px;"
										 />
								</td>
							<td  align="right" nowrap="nowrap">
								<span style="color:red;">*</span>工作单位：
							</td>
							<td >
								<input name="company" class="mini-textbox" style="width: 185px;" id="company"  required="true"
									/>
							</td>
							<td  align="right" nowrap="nowrap">
								<span style="color:red;">*</span>单位性质：
							</td>
							<td >
								<input name="worktype" class="mini-combobox" style="width: 185px;" id="worktype" 
									valueField="code"textField="text"  required="true" onvalidation="worktype"
									url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=workType"
									 />
							</td>
						</tr>
						
						<tr height="34">
							<td  align="right" nowrap="nowrap">
								<span style="color:red;">*</span>行政职务：
							</td>
							<td >
								<input name="position" class="mini-textbox" style="width: 185px;" id="position"  required="true"
									/>
							</td>
							<td  align="right" nowrap="nowrap">
								<span style="color:red;">*</span>办公电话：
							</td>
							<td >
								<input name="tel" class="mini-textbox" style="width: 185px;" id="tel"  required="true"onvalidation="checktel"maxlength="15"
									 />
							</td>
							<td  align="right" nowrap="nowrap">
								<span style="color:red;">*</span>手机号码：
							</td>
							<td >
								<input name="mobile" class="mini-textbox" style="width: 185px;" id="mobile"  required="true"onvalidation="checkphone"maxlength="11"
									 />
							</td>
							
							
						</tr>
						<tr height="34">
						<td  align="right" nowrap="nowrap">
								<span style="color:red;">*</span>是否留学回国人员：
							</td>
							<td >
								<input name="abroadornot" class="mini-combobox" style="width: 185px;" id="abroadornot" 
								valueField="code"textField="text"  required="true"
									url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=yesorno"
									 />
							</td>
							<td  align="right" nowrap="nowrap">
								<span style="color:red;">*</span>常住地：
							</td>
							<td >
								<input name="livingadd" class="mini-combobox" style="width: 185px;" id="livingadd" 
								valueField="code"textField="text"  required="true"
									url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=livingadd"
									 />
							</td>
							<td  align="right" nowrap="nowrap">
								电子邮箱：
							</td>
							<td >
								<input name="email" class="mini-textbox" style="width: 185px;" id="email" onvalidation="checkemail" 
									 />
							</td>
						</tr>
					<tr height="34">
						<td  align="right" nowrap="nowrap">
							<span style="color:red;">*</span>照片上传：
							</td>
							<td  colspan="3" ><input id="fileupload1" class="mini-fileupload" name="photo" limitType="*.jpg;*.png;*.bmp;*.gif" required="true" 
   							 flashUrl="${pageContext.request.contextPath}/page/user/swfupload.swf" uploadUrl="upload.aspx"
   							 onuploadsuccess="onUploadSuccess" onuploaderror="onUploadError" onfileselect="onFileSelect"/>
     							<a class="mini-button" id="uploadButton" iconCls="icon-upload" onclick="startUpload()">上传</a>
					</tr>
					<tr height="34">
						<td  align="right" nowrap="nowrap">
								备注：
							</td>
							<td colspan="5">
								<input name="mark" class="mini-textbox" style="width: 820px;" id="mark" maxlength="100"
									 />
							</td>
						</tr>
					</table>
				</div>
				
			</form>
		</fieldset>
		
		
		
		<fieldset
			style="border: solid 1px #aaa; padding: 3px; ">
			<legend>
				专家扩展信息
			</legend>
			<form id="form2" method="post">
				<!--  <input name="id" class="mini-hidden"/> -->
				<div style="padding-left: 11px; padding-bottom: 5px;">
					<table >
						<tr height="34">
							<td  align="right" nowrap="nowrap">
								<span style="color:red;">*</span>专家类别：
							</td>
							<td >
								<input name="expcategory" class="mini-combobox" id="expcategory" 
								valueField="code"textField="text"  required="true"
								url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=expcategory"
									style="width: 185px;"  />
							</td>
							<td  align="right" nowrap="nowrap">
								<span style="color:red;">*</span>专业技术职称：
							</td>
							<td >
			
									<input name="proftitle" id="proftitle" class="mini-treeselect" url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=proftitle" multiSelect="false"  valueFromSelect="true"
       								 textField="text" valueField="code" parentField="parent_code"  onbeforenodeselect="beforenodeselect" allowInput="true"
        								showRadioButton="true" showFolderCheckBox="false"style="width: 185px;"  required="true"/>
							</td>
							<td  align="right" nowrap="nowrap" id="profgrade1">
								专业技术岗位：
							</td>
							<td >
								<input name="profgrade" class="mini-combobox"
									id="profgrade" valueField="code"textField="text" 
								url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=profgrade"
									style="width: 185px;" />
							</td>
							
							
						</tr>
						<tr height="34">
							<td  align="right" nowrap="nowrap">
								<span style="color:red;">*</span>从事专业：
							</td>
							<td >
								<input name="major" id="major" class="mini-treeselect" url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=major" multiSelect="false"  
       								 textField="text" valueField="code" parentField="parent_code"  allowInput="true"valueFromSelect="true"
        								showRadioButton="true" showFolderCheckBox="false"style="width: 185px;"  required="true"/>
							</td>
							<td  align="right" nowrap="nowrap">
								<span style="color:red;">*</span>所属学科：
							</td>
							<td >
								<input name="subject" id="subject" class="mini-treeselect" url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=subject" multiSelect="false"  
       								 textField="text" valueField="code" parentField="parent_code"  onbeforenodeselect="beforenodeselect" allowInput="true" valueFromSelect="true"
        								showRadioButton="true" showFolderCheckBox="false"style="width: 185px;" required="true"
   							 />
							</td>
							</tr>
							<tr>
							<td  align="right" nowrap="nowrap">
								<span style="color:red;">*</span>研究内容所属领域：
							</td>
							<td  colspan="5">
							<input name="filed" id="filed" class="mini-treeselect" url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=filed" multiSelect="true" 
        						textField="text" valueField="code" parentField="parent_code" checkRecursive="true"
        						showFolderCheckBox="false"  expandOnLoad="true" showClose="true" oncloseclick="onCloseClick"
     						   popupWidth="200" style="width: 600px;" required="true"
   							 />
							</td>
							
							
						</tr>
						
						<tr height="34">
							<td  align="right" nowrap="nowrap">
								<span style="color:red;">*</span>专家类型：
							</td>
							<td  colspan="5" >
								<input name="exptype" id="exptype" class="mini-treeselect" url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=exptype" multiSelect="true" 
        						textField="text" valueField="code" parentField="parent_code" checkRecursive="true" 
        						showFolderCheckBox="false"  expandOnLoad="true" showClose="true" oncloseclick="onCloseClick"
     						   popupWidth="200" style="width: 600px;" required="true"
   							 />
							</td>
						</tr>	
						<tr height="34">
							<td  align="right" nowrap="nowrap">
								<span style="color:red;">*</span>研究方向及内容简介：
							</td>
							<td  colspan="5">
								<input name="contents" class="mini-textArea"  style="width: 600px;height: 100px"
									id="contents"  required="true" maxlength="500"
									 />
							</td>
						</tr>
	

						
					</table>
				</div>
				
			</form>
		</fieldset>
				<div style="text-align: center; padding: 10px;">
					<a class="mini-button" onclick="SaveData()";iconCls="icon-ok"
						style="width: 60px; margin-right: 20px;">提交</a>
					<a class="mini-button" onclick="onReset()";iconCls="icon-remove"
						style="width: 60px;">重置</a>
				</div>
		<script type="text/javascript">
	mini.parse();
//照片上传
		var imgname=null;
	 function onFileSelect(e) {
	        //alert("选择文件");
	    }
	    function onUploadSuccess(e) {
	    	var result= mini.decode(e.serverData);
	    	imgname=result.name;
	        alert("照片上传成功！");

	        this.setText("照片上传成功");
	    }
	    function onUploadError(e) {
	        
	    }

	    function startUpload() {
	        var fileupload = mini.get("fileupload1");
	        fileupload.setUploadUrl("${pageContext.request.contextPath}/module/common.do?method=uploadimg");
	        fileupload.startUpload();
	    }


	   
	
	var form1 = new mini.Form("form1");
	var form2 = new mini.Form("form2");
	//数据保存
	function SaveData() {
		var o = form1.getData();
		var p = form2.getData();
		form1.validate();
		form2.validate();
		if (form1.isValid() == false)
			return;
		if (form2.isValid() == false)
			return;
		if(!imgname){
			alert("照片保存失败！");
			return;
			}
		var json = mini.encode( [ o,p,imgname]);
		$.ajax( {
			url : "${pageContext.request.contextPath}/module/user.do?method=saveExpInfo",
			type : 'post',
			data : {
				data : json
			},
			cache : false,
			success : function(text) {
				mini.alert("专家信息登记成功！", "提示", function() {
					CloseWindow();
				});
			},
			error : function(jqXHR, textStatus, errorThrown) {
				var msg=mini.decode(jqXHR.responseText);
                alert(msg.exMsg);
				CloseWindow();
			}
		});
	}

	function worktype(e){
		var worktype = mini.getbyName("worktype").getValue();
		if(worktype!=5){
				$("#profgrade").hide();
				$("#profgrade1").hide();
			}else{
				$("#profgrade").show();
				$("#profgrade1").show();
				};

		}
	function checkphone(e){
		var phone = mini.getbyName("mobile").getValue();
		var pattern=/^1[0-9]{10}$/;
		if (pattern.test(phone) == false) {
			mini.alert("请输入正确的联系手机"); 
			e.isValid = false;
	        return;
	    } 
	} 
	function checkemail(e){
		
		var email = mini.getbyName("email").getValue();
		var pattern = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
		if(email){
		if (pattern.test(email) == false) {
			mini.alert("请输入正确的邮箱"); 
			e.isValid = false;
	        return;
	    } 
		}
	} 
	function checktel(e){
		var tel = mini.getbyName("tel").getValue();
		var pattern = /^([0-9]{3,4})?[0-9]{7,8}$/;
		if (pattern.test(tel) == false) {
			mini.alert("请输入正确的电话号码"); 
			e.isValid = false;
	        return;
	    } 
	} 

	

	  var pattern = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;  
	
	//证件重复性和合法性验证
	function onNumber(e) {
		var idcode = mini.getbyName("idcode").getValue().trim();
		var idtype = mini.getbyName("idtype").getValue();
		var name = mini.getbyName("name").getValue();
		$.ajax( {
			url : "${pageContext.request.contextPath }/module/user.do?method=checkId",
			type : 'post',
			cache: false,
			data : {
				idcode : idcode,
				idtype : idtype,
				name : name//汉字编码
			},
			success : function(text) {
				//已经存在该身份证提示用户
				 var o = mini.decode(text);
				 console.log(o.msg);
			if (o.msg=="failed") {
				mini.alert("该证件号码的用户已经存在！");
				e.isValid = false;
				return;
			}
			
		}
		});
		//身份证合法性验证
		if (idtype == '01') {
			if (e.isValid) {
				if (isNumber(e.value) == false) {
					mini.alert("身份证号码不合法！");
					e.isValid = false;
					return;
				}
			}
		}
	}

	//身份证合法型验证
	function isNumber(v) {
		var tel = /^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i;
		var flag = true;
		if (!tel.test(v)) {
			flag = false;
		} else {
			var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5,
					8, 4, 2);
			var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4',
					'3', '2');
			var nTemp = 0, i;
			var temp = v.substring(0, 17);
			for (i = 0; i < temp.length; i++) {
				nTemp += temp.substr(i, 1) * arrInt[i];
			}
			temp += arrCh[nTemp % 11];
			if (temp != v) {
				flag = false;
			}
		}
		return flag;
	}

	



	function onReset() {
		form1.clear();
		form2.clear();
	}
	//树
	 function onCloseClick(e) {
         var obj = e.sender;
         obj.setText("");
         obj.setValue("");
     }
     function getValue() {
         var obj = mini.get("filed");
         alert(obj.getValue() + ":" + obj.getText());
     }
     function setValue() {
         var obj = mini.get("filed");
         obj.setValue("100,120");
     }

     function beforenodeselect(e) {
         //禁止选中父节点
         if (e.isLeaf == false) e.cancel = true;
     }
     function CloseWindow(action) {     
         window.CloseOwnerWindow(action);
    }
  
</script>
	</body>
</html>