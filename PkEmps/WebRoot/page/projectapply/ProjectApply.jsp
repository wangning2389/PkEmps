<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
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
                        <!--<input id="key" class="mini-textbox" emptyText="请输入菜单名称" style="width:150px;" onenter="onKeyEnter"/>   
                        <a class="mini-button" onclick="search()">查询</a>-->
                    </td>
                </tr>
            </table>           
        </div>
    </div>
    <div id="datagrid1" class="mini-datagrid" style="width:100%;height:400px;" allowResize="true"
        url="ProjectApply.do?method=getProjectApply"  idField="id" multiSelect="true" 
    >
        <div property="columns">
            <!--<div type="indexcolumn"></div>        -->
            <div type="checkcolumn" ></div> 
			            <div field="id" width="120" headerAlign="center" allowSort="true">ID</div>
       <div field="name" width="120" headerAlign="center" allowSort="true">name</div>
       <div field="sex" width="120" headerAlign="center" allowSort="true">sex</div>
       <div field="card" width="120" headerAlign="center" allowSort="true">card</div>
       <div field="graduation_time" width="120" headerAlign="center" allowSort="true">graduation_time</div>
       <div field="education" width="120" headerAlign="center" allowSort="true">education</div>
       <div field="degree" width="120" headerAlign="center" allowSort="true">degree</div>
       <div field="school" width="120" headerAlign="center" allowSort="true">school</div>
       <div field="professional" width="120" headerAlign="center" allowSort="true">professional</div>
       <div field="cy_card" width="120" headerAlign="center" allowSort="true">cy_card</div>
       <div field="cy_project" width="120" headerAlign="center" allowSort="true">cy_project</div>
       <div field="iszt" width="120" headerAlign="center" allowSort="true">iszt</div>
       <div field="cy_time" width="120" headerAlign="center" allowSort="true">cy_time</div>
       <div field="cy_money" width="120" headerAlign="center" allowSort="true">cy_money</div>
       <div field="cy_gs" width="120" headerAlign="center" allowSort="true">cy_gs</div>
       <div field="cy_yyzz_address" width="120" headerAlign="center" allowSort="true">cy_yyzz_address</div>
       <div field="cy_registertime" width="120" headerAlign="center" allowSort="true">cy_registertime</div>
       <div field="cy_yyzz_no" width="120" headerAlign="center" allowSort="true">cy_yyzz_no</div>
       <div field="cy_swdj_no" width="120" headerAlign="center" allowSort="true">cy_swdj_no</div>
       <div field="bank" width="120" headerAlign="center" allowSort="true">bank</div>
       <div field="basicaccount" width="120" headerAlign="center" allowSort="true">basicaccount</div>
       <div field="apply_money" width="120" headerAlign="center" allowSort="true">apply_money</div>
       <div field="fillperson" width="120" headerAlign="center" allowSort="true">fillperson</div>
       <div field="tel" width="120" headerAlign="center" allowSort="true">tel</div>
       <div field="fill_time" width="120" headerAlign="center" allowSort="true">fill_time</div>
       <div field="jbr" width="120" headerAlign="center" allowSort="true">jbr</div>
       <div field="shr" width="120" headerAlign="center" allowSort="true">shr</div>
       <div field="spr" width="120" headerAlign="center" allowSort="true">spr</div>
       <div field="shzt" width="120" headerAlign="center" allowSort="true">shzt</div>
       <div field="shyj" width="120" headerAlign="center" allowSort="true">shyj</div>
       <div field="sh_time" width="120" headerAlign="center" allowSort="true">sh_time</div>
       <div field="dispatch_money" width="120" headerAlign="center" allowSort="true">dispatch_money</div>
       <div field="file_1" width="120" headerAlign="center" allowSort="true">file_1</div>
       <div field="file_2" width="120" headerAlign="center" allowSort="true">file_2</div>
       <div field="file_3" width="120" headerAlign="center" allowSort="true">file_3</div>
       <div field="file_4" width="120" headerAlign="center" allowSort="true">file_4</div>
       <div field="file_5" width="120" headerAlign="center" allowSort="true">file_5</div>
       <div field="ext1" width="120" headerAlign="center" allowSort="true">ext1</div>
       <div field="ext2" width="120" headerAlign="center" allowSort="true">ext2</div>
       <div field="ext3" width="120" headerAlign="center" allowSort="true">ext3</div>
       <div field="ext4" width="120" headerAlign="center" allowSort="true">ext4</div>

        </div>
    </div>
    

    <script type="text/javascript">
        mini.parse();

        var grid = mini.get("datagrid1");
        grid.load();        
        function add() {     
            mini.open({
                url: bootPATH + "../page/ProjectApply/ProjectApply_edit.jsp",
                title: "新增", width: 700, height: 460,
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
                    url: bootPATH + "../page/ProjectApply/ProjectApply_edit.jsp",
                    title: "编辑", width: 700, height: 460,
                    onload: function () {
                        var iframe = this.getIFrameEl();
                        var data = { action: "edit", id: row.ID };
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
                        ids.push(r.ID);
                    }
                    var id = ids.join(',');
                    grid.loading("操作中，请稍后......");
                    $.ajax({
                        url: "ProjectApply.do?method=removeProjectApply",
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
            grid.load({ key: key });
        }
        function onKeyEnter(e) {
            search();
        }
       
    </script>

   
</body>
</html>
