<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" import="com.*"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>自动生成标准程序对象</title>
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
            <legend >标准程序对象生成</legend> 
    <form id="form1" method="post">
        <input name="sid" class="mini-hidden" />
        <div style="padding-left:11px;padding-bottom:5px;">
            <table style="table-layout:fixed;">
                <tr>
                    <td style="width:100px;">数据库表名：</td>
                    <td style="width:250px;">    
                        <input name="tabname" class="mini-textbox" required="true" maxlength="20" emptyText="请输入表名"/>
                    </td>
                    <td style="width:100px;">主键名：</td>
                    <td style="width:250px;">    
                        <input name="index" class="mini-textbox" required="true" maxlength="20" emptyText="请输入主键名"/>
                    </td>
                    <td style="width:100px;">包名：(例如：vo.system)</td>
                    <td style="width:250px;">    
                        <input name="pakeage" class="mini-textbox" required="true"  emptyText="请输入包名"/>
                    </td>
                </tr>
               </table>
            </div>
        </fieldset>
        <div style="text-align:center;padding:10px;">               
            <a class="mini-button" onclick="onOk" style="width:60px;margin-right:20px;">生成标准程序</a>       
                
        </div>        
    </form>
    <script type="text/javascript">
        mini.parse();

        var form = new mini.Form("form1");

        function SaveData() {
            var o = form.getData();            

            form.validate();
            if (form.isValid() == false) return;

            var json = mini.encode([o]);
            $.ajax({
                url: "/<%=Const.project%>/system.do?method=createSrc",
				type: 'post',
                data: { data: json},
                cache: false,
                success: function (text) {
                 	if(text==0)alert("标准程序对象已生成！");
                 	if(text==1)alert("标准程序对象生成失败，请检查数据库表名,主键名或Src包路径名");
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    CloseWindow();
                }
            });
        }

       
        function GetData() {
            var o = form.getData();
            return o;
        }
       
        function onOk(e) {
      
          SaveData();
           
        }
        function onCancel(e) {
            CloseWindow("cancel");
        }
       

    </script>
</body>
</html>
