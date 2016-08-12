<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" import="com.*"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>菜单面板</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    
     <script src="${pageContext.request.contextPath }/scripts/boot.js" type="text/javascript"></script>
    

    <style type="text/css">
    html, body
    {
        font-size:12px;
        padding:0;
        margin:0;
        border:0;
        height:100%;
        overflow:hidden;
    }
    </style>
</head>
<body>    
    
     <fieldset style="border:solid 1px #aaa;padding:3px;">
            <legend >基本信息</legend> 
    <form id="form1" method="post">
        <input name="id" class="mini-hidden" />
        <input name="pid" class="mini-hidden" />
        <div style="padding-left:11px;padding-bottom:5px;">
            <table style="table-layout:fixed;">
                <tr>
                    <td style="width:70px;">菜单名称：</td>
                    <td style="width:150px;">    
                        <input name="name" class="mini-textbox" required="true"  emptyText="请输入菜单名称"/>
                    </td>
                    <td style="width:70px;">跳转方式：</td>
                    <td style="width:150px;">    
                         <input name="target" class="mini-combobox" valueField="bm" textField="mch" 
                            url="${pageContext.request.contextPath }/system.do?method=getselect&lx=tar"
                            required="true"
                             emptyText="请选择跳转方式"
                            />
                    </td>
                </tr>
                <tr>
                    <td >排列序号：</td>
                    <td >    
                        <input name="sort" class="mini-textbox" required="true" emptyText="请输入排列序号"/>
                    </td>
                    <td >链接地址：</td>
                    <td >    
                        <input name="url"  class="mini-textbox" style="width:200px;" required="true"  emptyText="请输入链接地址"  />
                    </td>
                </tr>
               
                <tr>
                    <td >日志级别：</td>
                    <td >    
                        <input name="logflag" class="mini-combobox" valueField="bm" textField="mch" url="${pageContext.request.contextPath }/system.do?method=getselect&lx=log" required="true"
                             emptyText="请选择日志级别"/>
                    </td>
                    <td >启用状态：</td>
                    <td >    
                        <input name="flag" class="mini-combobox" valueField="bm" textField="mch" 
                            url="${pageContext.request.contextPath }/system.do?method=getselect&lx=stat"
                            onvaluechanged="onDeptChanged" required="true"
                             emptyText="请选择启用状态"
                            />
                    </td>
                </tr>           
            </table>
        </div>
        </fieldset>
        <fieldset style="border:solid 1px #aaa;padding:3px;">
            <legend >菜单树</legend>
            <div style="padding:5px;">
        <table>
            <tr>
                <td style="width:70px;">
                
                 <%new Tree().createTree();%>
               
                 <ul id="tree1" class="mini-tree" url="${pageContext.request.contextPath }/page/company/system/tree.txt" style="width:200px;padding:5px;" 
       				 showTreeIcon="true" textField="text" idField="id" parentField="pid" resultAsTree="false" 
       				 showArrow="true" checkRecursive="true">
    			 </ul>       
             
                
                </td>
                 
            </tr>
           
        </table>            
            </div>
        </fieldset>
        <div style="text-align:center;padding:10px;">               
            <a class="mini-button" onclick="onOk" style="width:60px;margin-right:20px;">确定</a>       
            <a class="mini-button" onclick="onCancel" style="width:60px;">取消</a>       
        </div>        
    </form>
    <script type="text/javascript">
        mini.parse();

        var form = new mini.Form("form1");

        function SaveData() {
            var o = form.getData();            
			
            form.validate();
            
            var tree = mini.get("tree1");
            var value = tree.getValue();
          	
            if (form.isValid() == false) return;

            var json = mini.encode([o]);
            $.ajax({
                url: "/<%=Const.project%>/system.do?method=savemenu",
				type: 'post',
                data: { data: json,pid:value},
                cache: false,
                success: function (text) {
                    CloseWindow("save");
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    CloseWindow();
                }
            });
        }

        ////////////////////
        //标准方法接口定义
        function SetData(data) {
            if (data.action == "edit") {
                //跨页面传递的数据对象，克隆后才可以安全使用
                data = mini.clone(data);
               
                $.ajax({
                    url: "/<%=Const.project%>/system.do?method=getmenudata&id=" + data.id,
                    cache: false,
                    success: function (text) {
                        var o = mini.decode(text);
                        form.setData(o);
                        form.setChanged(false);
                        //onDeptChanged();
                        //mini.getbyName("position").setValue(o.position);
                    }
                });
            }
        }

        function GetData() {
            var o = form.getData();
            return o;
        }
        function CloseWindow(action) {            
            if (action == "close" && form.isChanged()) {
                if (confirm("数据被修改了，是否先保存？")) {
                    return false;
                }
            }
            if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
            else window.close();            
        }
        function onOk(e) {
      
          SaveData();
           
        }
        function onCancel(e) {
            CloseWindow("cancel");
        }
        //表单联动查询时使用
        function onDeptChanged(e) {
            var deptCombo = mini.getbyName("dept_id");
            var positionCombo = mini.getbyName("position");
            var dept_id = deptCombo.getValue();

            positionCombo.load("../data/AjaxService.jsp?method=GetPositionsByDepartmenId&id=" + dept_id);
            positionCombo.setValue("");
        }

	    
    </script>
</body>
</html>
