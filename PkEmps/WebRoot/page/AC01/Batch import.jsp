<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.Const"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort() + path + "/";
	StringBuffer uploadUrl = new StringBuffer("http://");
	uploadUrl.append(request.getHeader("Host"));
	uploadUrl.append(request.getContextPath());
	uploadUrl.append("/file.do");
	System.out.println("uploadUrl====>"+uploadUrl);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>附件上传</title>
		<link href="${pageContext.request.contextPath }/page/upload/css/default.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath }/page/upload/css/button.css" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath }/page/upload/js/swfupload/swfupload.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/page/upload/js/swfupload/swfupload.queue.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/page/upload/js/swfupload/handlers.js"></script>
		<script src="${pageContext.request.contextPath }/scripts/boot.js" type="text/javascript"></script>
		<script type="text/javascript">
			var swfu;
			window.onload = function () {
				swfu = new SWFUpload({
					upload_url: "<%=uploadUrl.toString()%>",
					//==========
					use_query_string:true,
					// File Upload Settings
					file_size_limit : "100 MB",	// 文件大小控制
					file_types : "*.xls;*.xlsx",
					file_types_description : "All Files",
					file_upload_limit : "0",
									
					file_queue_error_handler : fileQueueError,
					file_dialog_complete_handler : fileDialogComplete,//选择好文件后提交
					file_queued_handler : fileQueued,
					upload_progress_handler : uploadProgress,
					upload_error_handler : uploadError,
					upload_success_handler : uploadSuccess,
					upload_complete_handler : uploadComplete,
					button_placeholder_id : "spanButtonPlaceholder",
					button_width: 60,
					button_height: 18,
					button_text : '浏览',
					button_text_style : '.button { font-family: Helvetica, Arial, sans-serif; font-size: 12pt;  } .buttonSmall { font-size: 10pt; }',
					button_text_top_padding: 2,
					button_text_left_padding: 30,
					button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
					button_cursor: SWFUpload.CURSOR.HAND,
					
					// Flash Settings
					flash_url : "${pageContext.request.contextPath }/page/upload/js/swfupload/swfupload.swf",
	
					custom_settings : {
						upload_target : "divFileProgressContainer"
					},
					// Debug Settings
					debug: false  //是否显示调试窗口
				});
			};
			function startUploadFile(){
			//上传参数定义
			mini.parse();
			var urlpath = "/uploadFiles";
			var postobj = { "urlpath": urlpath};
			swfu.setPostParams(postobj);
			swfu.startUpload();
			}
			
		</script>
</head>
<body style="overflow-x: hidden">
    <form name="form1">
      <div style="padding-left:2px;padding-bottom:5px;padding-top:10px;">
        <table style="table-layout:fixed;">
           <tr>
             <td style="display: inline; border: solid 1px #AAAAAA; padding-top:0px;">
               <span id="spanButtonPlaceholder" ></span>
             </td>
           </tr>
        </table>
      </div>
      <div style="text-align:left;padding:2px;">
        <a id="btnUpload" class="mini-button" iconCls="icon-upload" onclick="startUploadFile();">上  传</a>
        <a id="btnCancel" class="mini-button" onclick="cancelUpload();">取消所有上传</a>
      </div>
    </form>
    <div id="divFileProgressContainer"></div>
    <div id="thumbnails">
      <table id="infoTable" border="0" width="600px" style="display: inline;  padding: 2px;margin-top:8px;">
      </table>
    </div>
</body>
</html>





