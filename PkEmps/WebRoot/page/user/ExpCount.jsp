<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'count.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->


	<script src="${pageContext.request.contextPath}/resources/js/echarts.min.js"></script>


  </head>
  
  <body>
  <jsp:include page="../default.jsp"></jsp:include>
    <div id="main" style="width: 600px;height:400px;"></div>
    <script type="text/javascript" defer="defer">
       
        var label=[];     //字段
  		var value=[];     //数据
  		var names="<%=request.getParameter("type")%>" ;       //标题名
  		if(names==("livingadd")){
  			names = "地区"
  		}
  		if(names==("major")){
  			names = "专业"
  		}
  		if(names==("subject")){
  			names = "学科"
  		}
  		if(names==("sex")){
  			names = "性别"
  		}
  		if(names==("age")){
  			names = "年龄"
  		}
            
            $(function() {
                // 基于准备好的dom，初始化echarts图表
                var myChart = echarts.init($("#main")[0]);     
                
                 				
                myChart.setOption({
                     title : {
        				text: '按'+names+'统计专家人数',
        				subtext: '',
        				x:'center'
    				},
   				 	tooltip : {
        				trigger: 'item',
        				formatter: "{a} <br/>{b} : {c}人 ({d}%)"
    				},
    				legend: {
        				orient : 'vertical',
        				x : 'left',
        				data:[]
    				},
    				
    				calculable : true,
    				
    				
    				series: [{
                		name: '人数：',
                		type: 'pie',
                		radius : '55%',
            			center: ['50%', '50%'],
                		data: []
            		}]
                });
                		
                // 为echarts对象加载数据 
                $.ajax({
                		url: "${pageContext.request.contextPath}/module/count.do?method=ExpUserCount&counttype=<%=request.getParameter("type")%>",
                		cache: false,
        				dataType:"json",
                		success:function(data){
                			
          					$.each(data,function(i,p){
          						
          						label[i]=p['name'];
          						value[i]={'name':p['name'],'value':p['count']};
          					});
          					
              			  myChart.setOption({
                			legend:{data:label},
                			series:[{data:value}]
                			}); 
          				}
          			});
                
               
            });
        
                
                
        
    </script>
    
    
    
	
	<script type="text/javascript">
		function CloseWindow(action) {
		if (window.CloseOwnerWindow)
			return window.CloseOwnerWindow(action);
		else
			window.close();
	}
	</script>
  </body>
   
</html>
