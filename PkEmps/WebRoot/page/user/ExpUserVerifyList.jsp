<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" 
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>专家信息维护</title>
</head>
<jsp:include page="../default.jsp"></jsp:include>
<body>
 
<div style="padding-bottom: 5px; padding-top: 5px;">
<fieldset id="unitQuery">
		<legend>
			<span style="font-weight: bold;">查询信息</span>
		</legend>
		<div id="form1" class="fieldset-body">
			<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr height="34">
					<td align="right"><span>姓名：</span></td>
					<td><input id="name" name="name"style="width: 165px;"
						class="mini-textbox" onkeypress="" maxlength="10"/></td>
					<td align="right"><span>从事专业：</span></td>
					<td><input name="major" id="major" class="mini-treeselect" url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=major" multiSelect="false"  valueFromSelect="true"
       								 textField="text" valueField="code" parentField="parent_code"  allowInput="true"
        								showRadioButton="true" showFolderCheckBox="false"style="width: 165px;"  />
        			</td>
					<td align="right"><span>所属学科：</span></td>
					<td><input name="subject" id="subject" class="mini-treeselect" url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=subject" multiSelect="false"  valueFromSelect="true"
       								 textField="text" valueField="code" parentField="parent_code"  allowInput="true"
        								showRadioButton="true" showFolderCheckBox="false"style="width: 165px;"/>
        			</td>
					
					
			     </tr>
			     <tr height="34">
			    	
					<td align="right"><span>提交时间：</span></td>
					<td><input id="subtime" name="subtime"
						class="mini-datepicker" onkeypress="" format="yyyy-MM-dd" style="width: 165px;"/></td>
					<td align="right"><span>审核状态：</span></td>
					<td><input id="verifystatus" name="verifystatus"  data="[{ id: '0', text: '待审核'},{ id: '1', text: '审核通过' },{ id: '2', text: '审核未通过' }]" 
						class="mini-combobox" onkeypress="" maxlength="30" style="width: 165px;"/></td>
					<td align="right"><span>专业技术岗位：</span></td>
					<td><input name="profgrade" class="mini-combobox"id="profgrade" valueField="code"textField="text" 
								url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=profgrade"
									style="width: 165px;" />
        			</td>
        			
				</tr>
				
				 <tr height="34">
			    	
					<td align="right"><span>专家类型：</span></td>
					<td  colspan="5" >
								<input name="exptype" id="exptype" class="mini-treeselect" url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=exptype" multiSelect="true" 
        						textField="text" valueField="code" parentField="parent_code" checkRecursive="true" showFolderCheckBox="false"  expandOnLoad="true" showClose="true" oncloseclick="onCloseClick"
     						   popupWidth="200" style="width: 500px;" 
   							 />
							</td>
					
				</tr>
				<tr height="34">
			    	<td nowrap="nowrap" align="right"><span>研究内容所属领域：</span></td>
					<td><input name="filed" id="filed" class="mini-treeselect" url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=filed" multiSelect="false"  valueFromSelect="true"
       								 textField="text" valueField="code" parentField="parent_code"  allowInput="true"
        								showRadioButton="true" showFolderCheckBox="false"style="width: 165px;"/>
        			</td>
	
        
				</tr>
				<tr height="34">
				<td></td>
				<td align="left"><a class="mini-button" value="查询" iconCls="icon-search" 
						onclick="queryExpUser()">查询</a>&nbsp;&nbsp;<a class="mini-button"
						value="重置" iconCls="icon-reload" onclick="reset()">重置</a></td>
					<td></td>
				</tr>
			</table>
		</div>
	</fieldset>
    </div>

    <div style="width:100%">
        <div class="mini-toolbar">
           <a class="mini-button" iconCls="icon-edit" onclick="view()">审核</a>

        </div>
    </div>
    <div>
    <div id="expUserGrid" class="mini-datagrid" width="100%" allowResize="true"
        url="${pageContext.request.contextPath}/module/user.do?method=getExpUserList"  idField="name" multiSelect="true" 
           showReloadButton="true" virtualScroll="true" idField="id">
            <div property="columns">
            <div type="checkcolumn" ></div>
            <div type="indexcolumn"></div>
            <!--  <div field="id"width="40"  allowSort="true" >ID</div>-->
            <div field="name" width="80" headerAlign="center" align="left" allowSort="true">姓名</div>  
            <div field="major" width="110" headerAlign="center" align="left" allowSort="true" renderer="trmajor">从事专业</div>   
            <div field="subject" width="120" headerAlign="center" align="left" allowSort="true"renderer="trsubject">所属学科</div>    
            <div field="filed" width="220" headerAlign="center" align="left" allowSort="true"renderer="trfiled">研究内容所属领域</div>
            <div field="company" width="100" headerAlign="center" align="left" allowSort="true">单位名称</div>
            <div field="livingadd" width="60" headerAlign="center" align="left" allowSort="true"renderer="trAdd">常住地</div>
            <div field="subtime" dateFormat="yyyy-MM-dd  " width="80" headerAlign="center" align="left" allowSort="true">提交时间</div>
            <div field="verifytime" dateFormat="yyyy-MM-dd " width="80" headerAlign="center" align="left" allowSort="true">审核时间</div>
			<div field="status" width="60" headerAlign="center" align="left" allowSort="true" renderer="trstatus">信息状态</div>
            <div field="verifystatus" width="70" headerAlign="center" align="left" allowSort="true" renderer="onrelease">审核状态</div>
        </div>
        </div>
    </div>
 <script type="text/javascript">
        // jquery mini ui 界面渲染
        mini.parse();
        var dic="1";
        var grid = mini.get("expUserGrid");
       //获取后台字典
        $.ajax({
            url: "${pageContext.request.contextPath}/module/user.do?method=getDic&type=major,subject,filed,livingadd",
            cache: false,
            success: function (text) {
                 dic = mini.decode(text);
                 //将json数据转换为map
                 var map = {};
                 $.each(dic,function(k, v){
                     var key = v.type;
                     if(!map[key]){
                    	 map[key] = {}               
                     }
                     map[key][v.code] = v.text;  
                 })
               	window['map'] = map;
                
               
                 var status =1;
                grid.load({//刷新页面时查询列表
                	status:status
                });
                }
            });

        



        
        
        function queryExpUser() {

            var name = mini.get("name").getValue();
            var major = mini.get("major").getValue();
            var subject = mini.get("subject").getValue();
            var filed = mini.get("filed").getValue();
            var verifystatus = mini.get("verifystatus").getValue();
            var subtime = mini.get("subtime").getFormValue();
            var status =1;
            var profgrade =mini.get("profgrade").getValue();
            var exptype =mini.get("exptype").getValue();
          
           
            
            //加载表格数据 
			var grid = mini.get("expUserGrid");
			grid.load({//调后台查询列表
				name : name,
				major : major,
				subject : subject,
				filed : filed,
				verifystatus :verifystatus,
				subtime : subtime,
				status:status,
				profgrade : profgrade,
				exptype : exptype
			});

        }
        
        function reset() {

        	var form = new mini.Form("form1");
    		form.clear();
		}
        
      
        
        function view(e) {
        	var se = grid.getSelecteds();
			if (se.length == 1) {
	
				if(se[0].verifystatus!=0){
						mini.alert("审核状态为待审核的专家才可以审核！");
						return;
					}
				mini.open({
					url : "${pageContext.request.contextPath}/page/user/verifyExpUser.jsp",
					title : "审核专家信息",
					width: 1000, 
	                height: 580,
	                onload: function () {
	                    var iframe = this.getIFrameEl();
	                    var data = { action: "edit", id: se[0].id };
                        iframe.contentWindow.SetData(data);
	                },
					ondestroy : function(action) {
						grid.reload();
					}
				});
			}  else {
				mini.alert("请选择一条要审核的数据!");
			}
		}
        
      
        
 
     
        
        var mt = function() {
    	};
    	
    	mt.dataProject2List = function(arr, col, removePrefix, type) {
    		var start = 0;
    		if (removePrefix && removePrefix.length) {
    			start = removePrefix.length;
    		}
    		if (!col) {
    			col = "id";
    		}
    		var result = new Array();
    		$.each(arr, function(k, v) {
    			var value = v[col];
    			if (value && value.substring) {
    				value = value.substring(start);
    			}
    			if (type == "int") {
    				value = +value;
    			}
    			result.push(value);
    		});

    		return result;
    	};
    	
    	function onrelease(e) {
            if (e.value == 0) return "待审核";
            if (e.value == 1)return "审核通过";
            if (e.value == 2)return "审核不通过";
        }
    	function trstatus(e) {
            if (e.value == 0) return "注销";
            if (e.value == 1)return "有效";
        }
        
    	function trmajor(e){
    		return window['map']['major'][e.value];
    	
        	}
    	function trAdd(e){
    		return window['map']['livingadd'][e.value];
    	
        	}
    	function trsubject(e){
    		return window['map']['subject'][e.value];
        	}
    	
    	function trfiled(e){
    		var txt= new Array();
    		str=e.value.split(",");
            for (i=0;i<str.length ;i++ ) {
				txt[i]=window['map']['filed'][str[i]];
				
                }
            var text="";
            for (i=0;i<txt.length ;i++ )
            {
               if(!text){
                  text=text+txt[i];
                   }else{
                    text=text+','+txt[i];  
                       }
           
                }
    		return text;
        	}
    	
    	function onCloseClick(e) {
            var obj = e.sender;
            obj.setText("");
            obj.setValue("");
        }
 </script>      
</body>
</html>