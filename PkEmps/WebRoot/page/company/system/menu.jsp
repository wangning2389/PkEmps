<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>菜单管理</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
  

    <script src="${pageContext.request.contextPath }/scripts/boot.js" type="text/javascript"></script>
</head>
<body>

    <div style="width:100%;">
        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
            <table style="width:100%;">
                <tr>
                    <td style="width:100%;">
                        <a class="mini-button" iconCls="icon-add" onclick="add()">增加</a>
                        <a class="mini-button" iconCls="icon-add" onclick="edit()">编辑</a>
                        <a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a>       
                    </td>
                    <td style="white-space:nowrap;">
                        <input id="key" class="mini-textbox" emptyText="请输入菜单名称" style="width:150px;" />  
                        <input id="key1" class="mini-textbox" emptyText="请输入菜单编号" style="width:150px;" /> 
                        <a class="mini-button" onclick="search()">查询</a>
                    </td>
                </tr>
            </table>           
        </div>
    </div>
    <div id="datagrid1" class="mini-datagrid" style="width:100%;height:330px;" allowResize="true"
        url="system.do?method=getmenu"  idField="id" multiSelect="true" 
    >
        <div property="columns">
            <!--<div type="indexcolumn"></div>        -->
            <div type="checkcolumn" ></div>        
            <div field="id" width="120" headerAlign="center" allowSort="true">频道</div>    
            <div field="fname" width="120" headerAlign="center" allowSort="true">父菜单</div> 
            <div field="name" width="120" headerAlign="center" allowSort="true">菜单名称</div>   
            <div field="flagname" width="120" headerAlign="center" allowSort="true">启用状态</div>   
            <div field="logname" width="120" headerAlign="center" allowSort="true">日志级别</div>   
            <div field="sort" width="120" headerAlign="center" allowSort="true">菜单排序</div>    
        </div>
    </div>
    

    <script type="text/javascript">
        mini.parse();

        var grid = mini.get("datagrid1");
        grid.load();
       

        
        function add() {
            
            mini.open({
                url: bootPATH + "../page/company/system/menu_edit.jsp",
                title: "新增菜单", width: 600, height: 360,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = { action: "new"};
                    iframe.contentWindow.SetData(data);
                },
                ondestroy: function (action) {
                    grid.reload();
                }
            });
        }
        function edit() {
         
            var row = grid.getSelected();
            if (row) {
                mini.open({
                    url: bootPATH + "../page/company/system/menu_edit.jsp",
                    title: "编辑菜单", width: 700, height: 460,
                    onload: function () {
                        var iframe = this.getIFrameEl();
                        var data = { action: "edit", id: row.id };
                        iframe.contentWindow.SetData(data);
                        
                    },
                    ondestroy: function (action) {
                        grid.reload();
                        
                    }
                });
                
            } else {
                alert("请选中一条记录");
            }
            
        }
        function remove() {
            var rows = grid.getSelecteds();
            if (rows.length > 0) {
                if (confirm("确定删除选中记录？")) {
                    var ids = [];
                    for (var i = 0; i < rows.length; i++) {
                        var r = rows[i];
                        ids.push(r.id);
                    }
                    var id = ids.join(',');
                    grid.loading("操作中，请稍后......");
                    $.ajax({
                        url: "system.do?method=removemenu",
                        type: "post",
                        data: { id: id },
               			dataType:"text",
                        success: function (text) {
                        	alert("删除成功");
                            grid.reload();
                        },
                        error: function () {
                        }
                    });
                }
            } else {
                alert("请选中一条记录");
            }
        }
        function search() {
            var key = mini.get("key").getValue();
            var key1 = mini.get("key1").getValue();
            grid.load({ key: key,key1:key1 });
        }
        function onKeyEnter(e) {
            search();
        }
       
    </script>

   
</body>
</html>