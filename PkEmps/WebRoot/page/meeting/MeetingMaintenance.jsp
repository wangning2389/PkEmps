<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" 
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评审会信息维护</title>
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
					<td align="right"><span>主题：</span></td>
					<td><input id="theme" name="theme"style="width: 165px;"
						class="mini-textbox" onkeypress="" maxlength="10"/></td>
					<td  align="right"><span>时间：</span></td>
					<td><input id="time" name="time"
						class="mini-datepicker" onkeypress="" format="yyyy-MM-dd" style="width: 165px;"/></td>
					<td  align="right" nowrap="nowrap">
							保存状态：
					</td>
					<td >
						<input name="savestatus" class="mini-combobox" valueField="code" 
							textField="text" id="savestatus" url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=savestatus"
							style="width: 165px;" />
					</td>
					<td></td>
					
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
           <a class="mini-button" iconCls="icon-add" onclick="add()">新增</a>
           <a class="mini-button" iconCls="icon-edit" onclick="view()">查看</a>
           <a class="mini-button" iconCls="icon-edit" onclick="update()">修改</a>
           <a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a>
           <a class="mini-button" iconCls="icon-download" onclick="download()">导出名单</a> 
        </div>
    </div>
    <div>
    <div id="meetingGrid" class="mini-datagrid" width="100%" allowResize="true"
        url="${pageContext.request.contextPath}/module/meeting.do?method=getMeetingList"  idField="name" multiSelect="true" 
           showReloadButton="true" virtualScroll="true" idField="id">
            <div property="columns">
            <div type="checkcolumn"width="10"   ></div>
            <div type="indexcolumn"width="10"  ></div>
            <div field="id"width="10"  allowSort="true" >ID</div>
            <div field="theme" width="80" headerAlign="center" align="left" allowSort="true">主题</div>  
            <div field="time" dateFormat="yyyy-MM-dd"width="110" headerAlign="center" align="left" allowSort="true" >时间</div>   
           	<div field="savestatus" width="80" headerAlign="center" align="left" renderer="transsave" allowSort="true">保存状态</div>  
        </div>
        </div>
    </div>
 <script type="text/javascript">
        // jquery mini ui 界面渲染
        mini.parse();
        var grid = mini.get("meetingGrid");
       //获取后台字典
		 grid.load({//刷新页面时查询列表
		 });   	
        
        function queryExpUser() {

            var theme = mini.get("theme").getValue();
            var time = mini.get("time").getFormValue();
            var savestatus = mini.get("savestatus").getValue();
           
            
            //加载表格数据 
			var grid = mini.get("meetingGrid");
			grid.load({//调后台查询列表
				theme : theme,
				time : time,
				savestatus :savestatus
			});

        }
        
        function reset() {

        	var form = new mini.Form("form1");
    		form.clear();
		}
        
        function add(e) {
            mini.open({
                url: "${pageContext.request.contextPath}/page/meeting/addMeeting.jsp",
                title: "新增评审会信息", 
                width: 900, 
                height: 580,
                allowDrag : true,
        		showCloseButton : true, //显示关闭按钮
        		showModal : true, //显示遮罩
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = { action: "new"};
                },
                ondestroy: function (action) {

                    grid.reload();
                }
            });
        }
        
        function view(e) {
        	var se = grid.getSelecteds();
			if (se.length == 1) {
				mini.open({
					url : "${pageContext.request.contextPath}/page/meeting/viewMeeting.jsp",
					title : "查看评审会信息",
					width: 900, 
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
				mini.alert("请选择一条要查看的数据!");
			}
		}
        
        function update(e) {
        	var myDate = new Date();
        	console.log(myDate);
        	var date=myDate.toLocaleDateString(); //获取当前时间
        	console.log(date);
        	var se = grid.getSelecteds();
			if (se.length == 1) {
				var meetingdate=se[0].time.toLocaleDateString();
				if(meetingdate<date){
					mini.alert("过期的评审会不可修改!");
					return;
					}
				if(se[0].savestatus==1){
					mini.alert("已确认的评审会不可修改!");
					return;
					}
				mini.open({
					url : "${pageContext.request.contextPath}/page/meeting/updateMeeting.jsp",
					title : "修改评审会信息",
					width: 900, 
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
				mini.alert("请选择一条要编辑的数据!");
			}
		}
        
 
        
        function remove() {
			var rows = grid.getSelecteds();
			if (rows == null||rows.length == 0) {
				mini.alert("请选择需要删除的数据！");
				return;
			}else{
					for(var i=0;i<rows.length;i++){
							if(rows[i].savestatus==1){
								mini.alert("已确认的数据不可删除！");
								return;
								}
						}
				}
			mini.confirm("审核删除选中的会议吗？", "删除会议", function(e) {
				if (e == "ok") {
					var ids = mt.dataProject2List(rows);
					var idStr = "ids=" + ids.join(',');

					$.ajax({
						url : "${pageContext.request.contextPath}/module/meeting.do?method=deleteMeeting",
						data : idStr,
						type : "POST"
					}).done(function(data, textStatus, jqXHR) {
						grid.reload();
					}).fail(function(jqXHR, textStatus, errorThrown) {
						//console.log("fail");
					});
				}

			});
		}
        
        
        function download(e) {
        	var se = grid.getSelecteds();
        	//console.log(se[0].verifystatus);
			if (se.length == 1) {
				if(se[0].savestatus!=1){
					mini.alert("只有确认状态的评审会信息才可以导出！");
						return;
					}
				document.location.href="<%=request.getContextPath()%>/module/meeting.do?method=DownloadExpList&id="+se[0].id;
			}  else {
				mini.alert("请选择一条要导出的数据!");
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

    	function transsave(e){
        	if(e.value==1){
				return "确认";
            	}else if(e.value==2){
				return "暂存";
            	}

        	}
        
 </script>      
</body>
</html>