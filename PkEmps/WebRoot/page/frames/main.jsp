<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>江苏省人力资源社会保障厅评审专家系统</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />

<jsp:include page="../default.jsp" />

<style type="text/css">
body {
	margin: 0;
	padding: 0;
	border: 0;
	width: 100%;
	height: 100%;
	overflow: hidden;
}

.top {
	background:
		url(${pageContext.request.contextPath}/resources/images/nr-bj.jpg)
		repeat-x top;
}

.nav {
	min-width: 90px;
	float: left;
	background:
		url(${pageContext.request.contextPath}/resources/images/nr-bj-line.jpg)
		right no-repeat;
}

.nav a {
	cursor: pointer;
	color: #2c3294;
	padding-left: 15px;
	margin-right: 3px;
}
</style>
</head>
<body>

	<!--Layout-->
	<div id="layout1" class="mini-layout"
		style="width: 100%; height: 100%;">
		<div class="header top" region="north" height="70" showSplit="false"
			showHeader="false">
			<h1
				style="margin: 0; padding: 15px; cursor: default; font-family: 微软雅黑, 黑体, 宋体;">江苏省人力资源社会保障厅评审专家系统</h1>
			<div style="position: absolute; top: 42px; right: 10px;">
				<div class="nav">
					<a style="margin-right: 15px"><img
						src="${pageContext.request.contextPath}/resources/images/nr-bj-tb1.gif"
						align="absmiddle" />当前用户:${sessionScope.userid}</a>
				</div>
				<!--<div class="nav">
					<a><img
						src="${pageContext.request.contextPath}/resources/images/nr-bj-tb3.gif"
						align="absmiddle" />密码修改</a>
				</div>-->
				<div class="nav">
					<a onclick="logout()"><img
						src="${pageContext.request.contextPath}/resources/images/nr-bj-tb4.gif"
						align="absmiddle" />退出系统</a>
				</div>
			</div>

		</div>
		<div title="south" region="south" showSplit="false" showHeader="false"
			height="30">
			<div style="line-height: 28px; text-align: center; cursor: default">
			</div>
		</div>
		<div title="center" region="center" style="border: 0;"
			bodyStyle="overflow:hidden;">
			<!--Splitter-->
			<div class="mini-splitter" style="width: 100%; height: 100%;"
				borderStyle="border:0;">
				<div size="180" maxSize="250" minSize="100"
					showCollapseButton="true" style="border: 0;">
					<!--OutlookTree-->
					<div id="leftTree" class="mini-outlooktree"
						url="${pageContext.request.contextPath}/module/user.do?method=getMenu"
						onnodeclick="onNodeSelect" textField="name" idField="id"
						parentField="parent_id"></div>

				</div>
				<div showCollapseButton="false" style="border: 0;">
					<!--Tabs-->
					<div id="mainTabs" class="mini-tabs" activeIndex="0"
						style="width: 100%; height: 100%;" plain="false"
						onactivechanged="onTabsActiveChanged">
						<div title="首页"
							url="${pageContext.request.contextPath}/page/frames/welcome.jsp"
							_nodeid="lists"></div>
					</div>
				</div>
			</div>
		</div>
	</div>



	<script type="text/javascript">
	 
			mini.parse();
			var tree = mini.get("leftTree");
 
		function showTab(node) {
			if (!node.url) {
				mini.alert("此功能暂未开通！");
				return;
			}
			var tabs = mini.get("mainTabs");

			var id = "tab$" + node.id;
			var tab = tabs.getTab(id);
			if (!tab) {
				tab = {};
				tab._nodeid = node.id;
				tab.name = id;
				tab.title = node.text;
				tab.showCloseButton = true;
				tab.refreshOnClick = true;

				tab.url = "${pageContext.request.contextPath}" + node.url;

				tabs.addTab(tab);
			} else {
				tabs.reloadTab(tab);
			}
			tabs.activeTab(tab);
		}

		function onNodeSelect(e) {
			var node = e.node;
			var isLeaf = e.isLeaf;

			if (isLeaf) {
				showTab(node);
			}
		}

		function onClick(e) {
			var text = this.getText();
			alert(text);
		}
		function onQuickClick(e) {
			tree.expandPath("datagrid");
			tree.selectNode("datagrid");
		}

		function onTabsActiveChanged(e) {
			var tabs = e.sender;
			var tab = tabs.getActiveTab();
			if (tab && tab._nodeid) {

				var node = tree.getNode(tab._nodeid);
				if (node && !tree.isSelectedNode(node)) {
					tree.selectNode(node);
				}
			}
		}
		function logout() {
			$.ajax({
				url :" ${pageContext.request.contextPath}/core/login.do?method=logout",
				type : "POST"
			}).done(function(data, textStatus, jqXHR) {
				window.location.href = "${pageContext.request.contextPath}/index.jsp";
			}).fail(function(jqXHR, textStatus, errorThrown) {
				var response = jqXHR.responseJSON;
				alert(response.msg);
			});
		}
		function newTab(tab) {
			var tabs = mini.get("#mainTabs"); 
			tabs.addTab(tab);
			tabs.activeTab(tab);
		}
	</script>

</body>
</html>
