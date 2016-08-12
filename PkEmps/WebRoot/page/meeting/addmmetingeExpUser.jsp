<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" 
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>专家信息添加</title>
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
					
					<td></td>
			     </tr>
			     <tr height="34">
			    	<td align="right" nowrap="nowrap"><span>研究内容所属领域：</span></td>
					<td><input name="filed" id="filed" class="mini-treeselect" url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=filed" multiSelect="false"  valueFromSelect="true"
       								 textField="text" valueField="code" parentField="parent_code"  allowInput="true"
        								showRadioButton="true" showFolderCheckBox="false"style="width: 165px;"/>
        			</td>
					<td align="right"><span>提交时间：</span></td>
					<td><input id="subtime" name="subtime"
						class="mini-datepicker" onkeypress="" format="yyyy-MM-dd" style="width: 165px;"/></td>

					<!-- <input id="userType" name="userType" type="hidden" value="2"/> -->
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
     						   popupWidth="200" style="width: 650px;" 
   							 />
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

 
    <div>
    <div id="expUserGrid" class="mini-datagrid" width="100%" allowResize="true"
        url="${pageContext.request.contextPath}/module/user.do?method=getExpUserList"  idField="name" multiSelect="true" 
           showReloadButton="true" virtualScroll="true" idField="id">
            <div property="columns">
            <div type="checkcolumn" ></div>
            <div type="indexcolumn"></div>
            <div field="id"width="40"  allowSort="true" >ID</div>
            <div field="name" width="80" headerAlign="center" align="left" allowSort="true">姓名</div>  
            <div field="major" width="110" headerAlign="center" align="left" allowSort="true" renderer="trmajor">从事专业</div>   
            <div field="subject" width="140" headerAlign="center" align="left" allowSort="true"renderer="trsubject">所属学科</div>    
            <div field="filed" width="300" headerAlign="center" align="left" allowSort="true"renderer="trfiled">研究内容所属领域</div>
            <div field="subtime" dateFormat="yyyy-MM-dd  HH:mm:ss" width="140" headerAlign="center" align="left" allowSort="true">提交时间</div>
            <div field="verifytime" dateFormat="yyyy-MM-dd  HH:mm:ss" width="140" headerAlign="center" align="left" allowSort="true">审核时间</div>
            <div field="verifystatus" width="100" headerAlign="center" align="left" allowSort="true" renderer="onrelease">审核状态</div>
        </div>
        </div>
    </div>
    
    
				<div style="text-align: center; padding: 10px;">
					<a class="mini-button" onclick="SubmitData()";iconCls="icon-ok"
						style="width: 60px; margin-right: 20px;">添加</a>
					
				</div>
 <script type="text/javascript">
        // jquery mini ui 界面渲染
        mini.parse();
        var dic="1";
        var grid = mini.get("expUserGrid");
       //获取后台字典
        $.ajax({
            url: "${pageContext.request.contextPath}/module/user.do?method=getDic&type=major,subject,filed,null",
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
                
                 var verifystatus = 1;
                 var status = 1;
                grid.load({//刷新页面时查询列表
                	
                	verifystatus :verifystatus,
                	status:status
                });
                }
            });

        



        
        
        function queryExpUser() {

            var name = mini.get("name").getValue();
            var major = mini.get("major").getValue();
            var subject = mini.get("subject").getValue();
            var filed = mini.get("filed").getValue();
            var verifystatus = 1;
            var status = 1;
            var subtime = mini.get("subtime").getFormValue();
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
				status:status,
				subtime : subtime,
				profgrade : profgrade,
				exptype : exptype
			});

        }
        
        function reset() {

        	var form = new mini.Form("form1");
    		form.clear();
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
        
    	function trmajor(e){
    		return window['map']['major'][e.value];
    	
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
    	function SubmitData(e){
    		var se = grid.getSelecteds();
        	console.log(se[0].verifystatus);
			if (se.length == 1) {
				CloseWindow("ok");
			}  else {
				mini.alert("请选择一条要提交的数据!");
			}

        	}



        function GetData() {
            var row = grid.getSelected();
            return row;
        }
       
        function onRowDblClick(e) {
            onOk();
        }
        //////////////////////////////////
        function CloseWindow(action) {
            if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
            else window.close();
        }

    	function onCloseClick(e) {
            var obj = e.sender;
            obj.setText("");
            obj.setValue("");
        }

        	
        
 </script>      
</body>
</html>