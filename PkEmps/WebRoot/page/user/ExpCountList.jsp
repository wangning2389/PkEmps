<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" 
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>评审专家统计</title>
		
	</head>
	<jsp:include page="../default.jsp"></jsp:include>
	<body >
		<div >
			<div >
				<div style="overflow: auto;">
					<div>
						<table width="100%" border="0" cellspacing="0" cellpadding="0" >
							<tr>
								<td>
									<div >
										<button  style="cursor:pointer;width: 138px;height:42px;margin-left:80px;margin-top:30px;" onclick="count('livingadd');">
											按地区统计
										</button>
										<button  style="cursor:pointer;width: 138px;height:42px;margin-left:80px;margin-top:30px;" onclick="count('major');">
											按专业统计
										</button>
										
										<button style="cursor:pointer;width: 138px; height: 42px; margin-left: 80px; margin-top: 10px;" onclick="count('subject');">
											按学科统计
										</button>
										
										<button style="cursor:pointer;width: 138px; height: 42px; margin-left: 80px; margin-top: 10px;" onclick="count('age');">
											按年龄统计
										</button>
										
										<button style="cursor:pointer;width: 138px; height: 42px; margin-left: 80px; margin-top: 10px;" onclick="count('sex');">
											按性别统计
										</button>
										
									</div>
								</td>
								
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
		
		<script type="text/javascript">
		// jquery mini ui 界面渲染
        mini.parse();
function count(type){
	mini.open({
					url : "${pageContext.request.contextPath}/page/user/ExpCount.jsp?type=" + type,
					title : "专家信息统计",
					width: 800, 
	                height: 550
				});
}


</script>
	</body>
</html>
