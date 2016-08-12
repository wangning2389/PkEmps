<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" 
    pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>专家信息修改</title>
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
								<input name="name" class="mini-textbox" maxlength="20"id="name" required="true"requiredErrorText="请输入姓名" readonly="readonly"
									style="width: 185px;" />
							</td>
							<td  align="right" nowrap="nowrap">
								<span style="color:red;">*</span>性别：
							</td>
							<td >
								<input name="sex" class="mini-combobox" valueField="code" required="true"readonly="readonly"
									textField="text" id="sex" url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=sex"
									style="width: 185px;" />
							</td>
							
							<td align="right" nowrap="nowrap">照片：</td>
								<td rowspan="5" align="left"><img width="134" height="175"
									id="photo1"src="${pageContext.request.contextPath }/resources/images/no-img.gif" </td>
						
						</tr>
	
							<tr height="34">
							<td  align="right" nowrap="nowrap">
								<span style="color:red;">*</span>出生日期：
							</td>
							<td >
								<input name="birthday" class="mini-datepicker"  required="true"readonly="readonly"
									id="birthday" 
									style="width: 185px;" />
							</td>
							
								
								<td  align="right" nowrap="nowrap">
									<span style="color:red;">*</span>国籍：
								</td>
								<td >
									<input name="country" class="mini-combobox" valueField="code"  required="true"readonly="readonly"
										textField="text" id="country"  url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=country"
										style="width: 185px;" />
								</td>
								</tr>
								<tr height="34">
								<td  align="right" nowrap="nowrap">
									<span style="color:red;">*</span>民族：
								</td>
								<td >
									<input name="nation" class="mini-combobox" valueField="code"  required="true"readonly="readonly"
										textField="text" id="nation"  url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=nation"
										style="width: 185px;" />
								</td>
								<td  align="right" nowrap="nowrap">
									<span style="color:red;">*</span>政治面貌：
								</td>
								<td >
									<input name="polic" class="mini-combobox" style="width: 185px;"id="polic" valueField="code"textField="text"
									 url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=polic"  required="true"readonly="readonly"
										 />
								</td>
							</tr>


							<tr height="34">
								<td  align="right" nowrap="nowrap">
									<span style="color:red;">*</span>证件类型：
								</td>
								<td >
									<input name="idtype" class="mini-combobox" style="width: 185px;"id="idtype" 
									valueField="code"textField="text"  required="true"readonly="readonly"
									url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=idType"
										 />
								</td>
								<td  align="right" nowrap="nowrap">
								<span style="color:red;">*</span>证件号码：
								</td>
								<td >
									<input name="idcode" class="mini-textbox"id="idcode"  required="true"readonly="readonly"
										onvalidation="onNumber" maxlength="18" style="width: 185px;"
										 />
								</td>
								
							</tr>
	
						<tr height="34">
							<td  align="right" nowrap="nowrap">
								籍贯：
								</td>
								<td >
									<input name="nativeplace" class="mini-textbox"id="nativeplace" readonly="readonly"
										maxlength="25" style="width: 185px;"
										 />
								</td>
							<td  align="right" nowrap="nowrap">
								出生地：
								</td>
								<td >
									<input name="birplace" class="mini-textbox" id="birplace" 
										 maxlength="50" style="width: 185px;"readonly="readonly"
										 />
								</td>
								</tr>
							<tr height="34">
							<td  align="right" nowrap="nowrap">
								<span style="color:red;">*</span>工作单位：
							</td>
							<td >
								<input name="company" class="mini-textbox" style="width: 185px;" id="company"  required="true"readonly="readonly"
									/>
							</td>
							<td  align="right" nowrap="nowrap">
								<span style="color:red;">*</span>单位性质：
							</td>
							<td >
								<input name="worktype" class="mini-combobox" style="width: 185px;" id="worktype" readonly="readonly"
									valueField="code"textField="text"  required="true"
									url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=workType"
									 />
							</td>
						</tr>
						
						<tr height="34">
							<td  align="right" nowrap="nowrap">
								<span style="color:red;">*</span>行政职务：
							</td>
							<td >
								<input name="position" class="mini-textbox" style="width: 185px;" id="position"  required="true"readonly="readonly"
									/>
							</td>
							<td  align="right" nowrap="nowrap">
								<span style="color:red;">*</span>办公电话：
							</td>
							<td >
								<input name="tel" class="mini-textbox" style="width: 185px;" id="tel"  required="true"onvalidation="checktel"maxlength="15"readonly="readonly"
									 />
							</td>
							<td  align="right" nowrap="nowrap">
								<span style="color:red;">*</span>手机号码：
							</td>
							<td >
								<input name="mobile" class="mini-textbox" style="width: 185px;" id="mobile"  required="true"onvalidation="checkphone"maxlength="11"readonly="readonly"
									 />
							</td>
							
							
						</tr>
						<tr height="34">
						<td  align="right" nowrap="nowrap">
								<span style="color:red;">*</span>是否留学回国人员：
							</td>
							<td >
								<input name="abroadornot" class="mini-combobox" style="width: 185px;" id="abroadornot" 
								valueField="code"textField="text"  required="true"readonly="readonly"
									url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=yesorno"
									 />
							</td>
							<td  align="right" nowrap="nowrap">
								<span style="color:red;">*</span>常住地：
							</td>
							<td >
								<input name="livingadd" class="mini-combobox" style="width: 185px;" id="livingadd" 
								valueField="code"textField="text"  required="true"readonly="readonly"
									url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=livingadd"
									 />
							</td>
							<td  align="right" nowrap="nowrap">
								电子邮箱：
							</td>
							<td >
								<input name="email" class="mini-textbox" style="width: 185px;" id="email" onvalidation="checkemail"readonly="readonly"
									 />
							</td>
						</tr>
				
					<tr height="34">
						<td  align="right" nowrap="nowrap">
								备注：
							</td>
							<td colspan="5">
								<input name="mark" class="mini-textbox" style="width: 820px;" id="mark" maxlength="100"readonly="readonly"
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
								valueField="code"textField="text"  required="true"readonly="readonly"
								url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=expcategory"
									style="width: 185px;"  />
							</td>
							<td  align="right" nowrap="nowrap">
								<span style="color:red;">*</span>专业技术职称：
							</td>
							<td >
			
									<input name="proftitle" id="proftitle" class="mini-treeselect" url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=proftitle" multiSelect="false"  valueFromSelect="false"
       								 textField="text" valueField="code" parentField="parent_code"  onbeforenodeselect="beforenodeselect" allowInput="false"readonly="readonly"
        								showRadioButton="true" showFolderCheckBox="false"style="width: 185px;"  required="true"/>
							</td>
							<td  align="right" nowrap="nowrap">
								专业技术岗位：
							</td>
							<td >
								<input name="profgrade" class="mini-combobox"readonly="readonly"
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
								<input name="major" id="major" class="mini-treeselect" url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=major" multiSelect="false"  valueFromSelect="false"
       								 textField="text" valueField="code" parentField="parent_code"  onbeforenodeselect="beforenodeselect" allowInput="false"readonly="readonly"
        								showRadioButton="true" showFolderCheckBox="false"style="width: 185px;"  required="true"/>
							</td>
							<td  align="right" nowrap="nowrap">
								<span style="color:red;">*</span>所属学科：
							</td>
							<td >
								<input name="subject" id="subject" class="mini-treeselect" url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=subject" multiSelect="false"  valueFromSelect="false"
       								 textField="text" valueField="code" parentField="parent_code"  onbeforenodeselect="beforenodeselect" allowInput="false"readonly="readonly"
        								showRadioButton="true" showFolderCheckBox="false"style="width: 185px;" required="true"
   							 />
							</td>
							</tr>
							
					<tr>
							<td  align="right" nowrap="nowrap">
								<span style="color:red;">*</span>研究内容所属领域：
							</td>
							<td  colspan="5">
							<input name="filed" id="filed" class="mini-treeselect" url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=filed" multiSelect="true" readonly="readonly"
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
        						textField="text" valueField="code" parentField="parent_code" checkRecursive="true" readonly="readonly"
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
								<input name="contents" class="mini-textArea"  style="width: 600px;height: 100px"readonly="readonly"
									id="contents"  required="true" maxlength="500"
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
				审核信息
			</legend>
			<form id="form3" method="post">
				<!--  <input name="id" class="mini-hidden"/> -->
				<div style="padding-left: 11px; padding-bottom: 5px;">
					<table >
						<tr height="34"align="right">
						
						<td style="width: 124px;" align="right" nowrap="nowrap">
								<span style="color:red;">*</span>提交时间：
							</td>
							<td   >
								<input name="subtime" class="mini-datepicker"  required="true"readonly="readonly"
									id="birthday" 
									style="width: 185px;" />
							</td>
							<td style="width: 93px;"  align="right" nowrap="nowrap">
								<span style="color:red;">*</span>审核时间：
							</td>
							<td >
								<input name="verifytime" class="mini-datepicker"  required="true"readonly="readonly"
									id="verifytime" 
									style="width: 185px;" />
							</td>
							<td style="width: 113px;" align="right" nowrap="nowrap">
								审核状态：
							</td>
							<td >
								<input name="verifystatus" class="mini-combobox"readonly="readonly"
									id="verifystatus" valueField="code"textField="text" 
								url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=verifystatus"
									style="width: 185px;" />
							</td>
						</tr>
						<tr height="34">
							<td  align="right" nowrap="nowrap">
								<span style="color:red;">*</span>操作人：
							</td>
							<td  >
								<input name="operator" class="mini-textbox"  style="width: 185px;"readonly="readonly"
									id="operator"  readonly="readonly"
									 />
							</td>
							<td  align="right" nowrap="nowrap">
								<span style="color:red;">*</span>信息状态：
							</td>
							<td  >
								<input name="status" class="mini-combobox"  style="width: 185px;"readonly="readonly"  valueField="id" data="[{ id: '0', text: '注销' }, { id: '1', text: '有效'}]" 
									id="status"  readonly="readonly"
									 />
							</td>
						</tr>
					<tr id="deletereason1" height="34">
							<td align="right" nowrap="nowrap">
								注销原因：
							</td>
							<td  colspan="5">
								<input name="deletereason" class="mini-textArea"  style="width: 600px;height: 50px"readonly="readonly"
									id="deletereason"  required="true" maxlength="200"
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
				过往评审会记录
			</legend>
			<form id="form4" method="post">
				<!--  <input name="id" class="mini-hidden"/> -->
				<div style="padding-left: 11px; padding-bottom: 5px;">
					<div id="meetingHistory" class="mini-datagrid" width="100%" allowResize="true"
        				url="${pageContext.request.contextPath}/module/user.do?method=getMeetingHistory&id=<%=request.getParameter("id")%>"  idField="name" multiSelect="true" 
           				showReloadButton="true" virtualScroll="true" idField="id">
            			<div property="columns">
            			<div field="theme" width="80" headerAlign="center" align="left" allowSort="true">主题</div>  
            			<div field="time" dateFormat="yyyy-MM-dd"width="110" headerAlign="center" align="left" allowSort="true" >时间</div>   
           
        			</div>
				</div>
				</div>
			</form>
		</fieldset>
	<div class="mini-toolbar"
		style="text-align: center; vertical-align: bottom;"
		borderStyle="border:0;">
		<a class="mini-button" iconCls="icon-close" onclick="CloseWindow('close')">关闭</a>
	</div>
		<script type="text/javascript">
	mini.parse();
	var grid = mini.get("meetingHistory");
	grid.load();
    //照片上传
	var form1 = new mini.Form("form1");
	var form2 = new mini.Form("form2");
	var form3 = new mini.Form("form3");
	$("#deletereason1").hide();
	 //标准方法接口定义
    function SetData(data) {
      if (data.action == "edit") {
         //跨页面传递的数据对象，克隆后才可以安全使用
         data = mini.clone(data);
         $.ajax({
                url: "${pageContext.request.contextPath}/module/user.do?method=UpdateExpUser&id=" + data.id,
                cache: false,
                success: function (text) {
                    var o = mini.decode(text);
                    if(o.status==0){
                    	$("#deletereason1").show();
                        }
                    form1.setData(o);
                    form2.setData(o);
                    form3.setData(o);
                    getphoto(o.photo);
                    form1.setChanged(false);
                    form2.setChanged(false);
                    form3.setChanged(false);
                    }
                });
        }
    }
	


     function getphoto(path){
		document.getElementById("photo1").src = "../../"
 										+ path;
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