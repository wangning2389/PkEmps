<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>江苏省人力资源社会保障厅专家评审管理平台</title> 

<link
	href="${pageContext.request.contextPath}/resources/css/index.css"
	rel="stylesheet" type="text/css" />

<script
	src="${pageContext.request.contextPath}/resources/js/EvensForm.js"
	type="text/javascript"></script>

</head>
<jsp:include page="default.jsp" />
<body
	style="overflow:hidden;background:url(${pageContext.request.contextPath}/resources/images/index-bj14825.jpg) no-repeat center top;">

	<div evens >
		<table  width="1080" height="850" border="0" cellpadding="0"
			cellspacing="0" align="center">
			<tr>
				<td><img
					src="${pageContext.request.contextPath}/resources/images/dl_01.gif"
					width="449" height="381" alt=""></td>
				<td><img
					src="${pageContext.request.contextPath}/resources/images/dl_02.gif"
					width="631" height="381" alt=""></td>
			</tr>
			<tr>
				<td rowspan="2" style="width =: 449px; height: 469px"></td>
				<td
					style="background:url(${pageContext.request.contextPath}/resources/images/dl_04.gif); height:110px">

					<table border="0" cellpadding="0" cellspacing="0"
						class="index-table-131210">
						<tr id="form1">
							<td width="31%"><input type="text" name="username"
								class="index-input-131210" value="用户名"></td>
							<td width="66%"><input type="password" name="password"
								class="index-input-131210"></td>
							<td width="3%"><input type="button" class="index-an-131210"
								value="登录" onclick="submit()"></td>
						</tr>
						<tr style="display: none">
							<td colspan="3">
								<div class="index-wb-1-131210">
									<input type="checkbox" class="index-checkbox-131210">记住密码



									
								</div>
								<div class="index-wb-2-131210">
									<a href="#" target="_blank">相关下载</a>
								</div>
								<div class="index-wb-3-131210">
									<a href="#" target="_blank">关于我们</a>
								</div>
							</td>
						</tr>
					</table>

				</td>
			</tr>
			<tr>
				<td style="width: 631px; height: 359px">
		</table>

	</div>

<script type="text/javascript">
$(function() {
	//用户名，密码提示域，自动显示或隐藏
	var sel = "input[name=username],input[name=password]";
	$(sel).focus(inputTipHandler);
	$(sel).blur(inputTipHandler);
	$(sel).next()
	$.ajax({
		url:
	});
	function inputTipHandler(event) {
		var jqE = $(this);
		var isFocused = event.type == "focus";
		var value = jqE.val();
		var name = jqE.attr("name");
		if (hasInput(value)) {
			//有输入 
		} else {
			//无输入
			if (isFocused) {
				jqE.val(null);
				if (name == "password") {
					jqE.attr("type", "password");
				}
			} else {
				var tip = getTip(jqE);
				jqE.val(tip);
				if (name == "password") {
					jqE.attr("type", "text");
				}
			}
		}

		function hasInput(input) {
			var reg = new RegExp("[a-zA-Z0-9_]");
			if (reg.test(input)) {
				return true;
			} else {
				return false;
			}
		}
		function getTip(jqE) {
			var name = jqE.attr("name");
			if (name == "username") {
				return "用户名";
			} else if (name == "password") {
				return "";
			}
		}
	}

});

mini.parse();

function submit() {
	if($("input[name=username]").val().length < 1 || $("input[name=username]").val() == '用户名'){
		alert("请输入用户名!");
		return;
	}
	if($("input[name=password]").val().length < 1 || $("input[name=password]").val() == '密码'){
		alert("请输入密码!");
		return;
	}
	var formParam = EvensForm.prototype.getFormAsData("submitData");
     var json = mini.encode(formParam);
	$.ajax({
		url : "${pageContext.request.contextPath}/core/login.do?method=login1",
		data :  { submitData: json },
		type : "POST",
			 success: function (res) {           
             window.location.href="${pageContext.request.contextPath}/module/user.do?method=toFrame"; 
        },
        error: function (jqXHR, textStatus, errorThrown) {
            var response=$.parseJSON(jqXHR.responseText);
            alert(response.exMsg);
           // CloseWindow();
        }
	});
}


    </script>
</body>
</html>