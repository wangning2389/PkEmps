<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" 
    pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>专家信息注销</title>
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
				
			</legend>
			<form id="form1" method="post">
				<!--  <input name="id" class="mini-hidden"/> -->
				<div style="padding-left: 11px; padding-bottom: 5px;">
					<table >

						<tr height="34">
							<td  align="right" nowrap="nowrap">
								<span style="color:red;">*</span>注销原因：
							</td>
							<td  colspan="5">
								<input name="deletereason" class="mini-textArea"  style="width: 350px;height: 100px"
									id="deletereason"  required="true" maxlength="200"
									 />
							</td>
						</tr>
	

						
					</table>
				</div>
				
			</form>
		</fieldset>
				<div style="text-align: center; padding: 10px;">
					<a class="mini-button" onclick="SaveData()";iconCls="icon-ok"
						style="width: 60px; margin-right: 20px;">注销</a>
					<a class="mini-button" iconCls="icon-close" onclick="CloseWindow('close')">关闭</a>
				</div>
		<script type="text/javascript">
	mini.parse();

	var form1 = new mini.Form("form1");
	var userdata="";



	


	 //标准方法接口定义
    function SetData(data) {
      if (data.action == "remove") {
         //跨页面传递的数据对象，克隆后才可以安全使用
         data = mini.clone(data);
         userdata=data;
        }
    }


	//数据保存
	function SaveData() {
		var o = form1.getData();
		form1.validate();
		if (form1.isValid() == false)
			return;
	
		var json = mini.get("deletereason").getValue();
		$.ajax( {
			url : "${pageContext.request.contextPath}/module/user.do?method=deleteExpUser",
			type : 'post',
			data : {
				data : json,
				id: userdata.id
			},
			cache : false,
			success : function(text) {
				mini.alert("专家注销成功！", "提示", function() {
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
	
 	function CloseWindow(action) {
		if (window.CloseOwnerWindow)
			return window.CloseOwnerWindow(action);
		else
			window.close();
	}
</script>
	</body>
</html>