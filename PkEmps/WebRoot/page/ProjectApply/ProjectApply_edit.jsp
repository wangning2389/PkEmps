<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <jsp:include page="../default.jsp" />
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
       
        <div style="padding-left:11px;padding-bottom:5px;">
            <table style="table-layout:fixed;">
               <tr>
<td style="width:20%;">ID：</td>
<td style="width:30%;"><input name="id" class="mini-textbox" required="true"   emptyText="请输入ID"/></td>
</tr>
<tr>
<td style="width:20%;">name：</td>
<td style="width:30%;"><input name="name" class="mini-textbox" required="true"   emptyText="请输入name"/></td>
<td style="width:20%;">sex：</td>
<td style="width:30%;"><input name="sex" class="mini-textbox" required="true"   emptyText="请输入sex"/></td>
</tr>
<tr>
<td style="width:20%;">card：</td>
<td style="width:30%;"><input name="card" class="mini-textbox" required="true"   emptyText="请输入card"/></td>
<td style="width:20%;">graduation_time：</td>
<td style="width:30%;"><input name="graduation_time" class="mini-datepicker" required="true" vtype="date:yyyy-MM-dd"  emptyText="请输入graduation_time"/></td>
</tr>
<tr>
<td style="width:20%;">education：</td>
<td style="width:30%;"><input name="education" class="mini-textbox" required="true"   emptyText="请输入education"/></td>
<td style="width:20%;">degree：</td>
<td style="width:30%;"><input name="degree" class="mini-textbox" required="true"   emptyText="请输入degree"/></td>
</tr>
<tr>
<td style="width:20%;">school：</td>
<td style="width:30%;"><input name="school" class="mini-textbox" required="true"   emptyText="请输入school"/></td>
<td style="width:20%;">professional：</td>
<td style="width:30%;"><input name="professional" class="mini-textbox" required="true"   emptyText="请输入professional"/></td>
</tr>
<tr>
<td style="width:20%;">cy_card：</td>
<td style="width:30%;"><input name="cy_card" class="mini-textbox" required="true"   emptyText="请输入cy_card"/></td>
<td style="width:20%;">cy_project：</td>
<td style="width:30%;"><input name="cy_project" class="mini-textbox" required="true"   emptyText="请输入cy_project"/></td>
</tr>
<tr>
<td style="width:20%;">iszt：</td>
<td style="width:30%;"><input name="iszt" class="mini-textbox" required="true"   emptyText="请输入iszt"/></td>
<td style="width:20%;">cy_time：</td>
<td style="width:30%;"><input name="cy_time" class="mini-datepicker" required="true" vtype="date:yyyy-MM-dd"  emptyText="请输入cy_time"/></td>
</tr>
<tr>
<td style="width:20%;">cy_money：</td>
<td style="width:30%;"><input name="cy_money" class="mini-textbox" required="true"   emptyText="请输入cy_money"/></td>
<td style="width:20%;">cy_gs：</td>
<td style="width:30%;"><input name="cy_gs" class="mini-textbox" required="true"   emptyText="请输入cy_gs"/></td>
</tr>
<tr>
<td style="width:20%;">cy_yyzz_address：</td>
<td style="width:30%;"><input name="cy_yyzz_address" class="mini-textbox" required="true"   emptyText="请输入cy_yyzz_address"/></td>
<td style="width:20%;">cy_registertime：</td>
<td style="width:30%;"><input name="cy_registertime" class="mini-datepicker" required="true" vtype="date:yyyy-MM-dd"  emptyText="请输入cy_registertime"/></td>
</tr>
<tr>
<td style="width:20%;">cy_yyzz_no：</td>
<td style="width:30%;"><input name="cy_yyzz_no" class="mini-textbox" required="true"   emptyText="请输入cy_yyzz_no"/></td>
<td style="width:20%;">cy_swdj_no：</td>
<td style="width:30%;"><input name="cy_swdj_no" class="mini-textbox" required="true"   emptyText="请输入cy_swdj_no"/></td>
</tr>
<tr>
<td style="width:20%;">bank：</td>
<td style="width:30%;"><input name="bank" class="mini-textbox" required="true"   emptyText="请输入bank"/></td>
<td style="width:20%;">basicaccount：</td>
<td style="width:30%;"><input name="basicaccount" class="mini-textbox" required="true"   emptyText="请输入basicaccount"/></td>
</tr>
<tr>
<td style="width:20%;">apply_money：</td>
<td style="width:30%;"><input name="apply_money" class="mini-textbox" required="true"   emptyText="请输入apply_money"/></td>
<td style="width:20%;">fillperson：</td>
<td style="width:30%;"><input name="fillperson" class="mini-textbox" required="true"   emptyText="请输入fillperson"/></td>
</tr>
<tr>
<td style="width:20%;">tel：</td>
<td style="width:30%;"><input name="tel" class="mini-textbox" required="true"   emptyText="请输入tel"/></td>
<td style="width:20%;">fill_time：</td>
<td style="width:30%;"><input name="fill_time" class="mini-datepicker" required="true" vtype="date:yyyy-MM-dd"  emptyText="请输入fill_time"/></td>
</tr>
<tr>
<td style="width:20%;">jbr：</td>
<td style="width:30%;"><input name="jbr" class="mini-textbox" required="true"   emptyText="请输入jbr"/></td>
<td style="width:20%;">shr：</td>
<td style="width:30%;"><input name="shr" class="mini-textbox" required="true"   emptyText="请输入shr"/></td>
</tr>
<tr>
<td style="width:20%;">spr：</td>
<td style="width:30%;"><input name="spr" class="mini-textbox" required="true"   emptyText="请输入spr"/></td>
<td style="width:20%;">shzt：</td>
<td style="width:30%;"><input name="shzt" class="mini-textbox" required="true"   emptyText="请输入shzt"/></td>
</tr>
<tr>
<td style="width:20%;">shyj：</td>
<td style="width:30%;"><input name="shyj" class="mini-textbox" required="true"   emptyText="请输入shyj"/></td>
<td style="width:20%;">sh_time：</td>
<td style="width:30%;"><input name="sh_time" class="mini-datepicker" required="true" vtype="date:yyyy-MM-dd"  emptyText="请输入sh_time"/></td>
</tr>
<tr>
<td style="width:20%;">dispatch_money：</td>
<td style="width:30%;"><input name="dispatch_money" class="mini-textbox" required="true"   emptyText="请输入dispatch_money"/></td>
<td style="width:20%;">file_1：</td>
<td style="width:30%;"><input name="file_1" class="mini-textbox" required="true"   emptyText="请输入file_1"/></td>
</tr>
<tr>
<td style="width:20%;">file_2：</td>
<td style="width:30%;"><input name="file_2" class="mini-textbox" required="true"   emptyText="请输入file_2"/></td>
<td style="width:20%;">file_3：</td>
<td style="width:30%;"><input name="file_3" class="mini-textbox" required="true"   emptyText="请输入file_3"/></td>
</tr>
<tr>
<td style="width:20%;">file_4：</td>
<td style="width:30%;"><input name="file_4" class="mini-textbox" required="true"   emptyText="请输入file_4"/></td>
<td style="width:20%;">file_5：</td>
<td style="width:30%;"><input name="file_5" class="mini-textbox" required="true"   emptyText="请输入file_5"/></td>
</tr>
<tr>
<td style="width:20%;">ext1：</td>
<td style="width:30%;"><input name="ext1" class="mini-textbox" required="true"   emptyText="请输入ext1"/></td>
<td style="width:20%;">ext2：</td>
<td style="width:30%;"><input name="ext2" class="mini-textbox" required="true"   emptyText="请输入ext2"/></td>
</tr>
<tr>
<td style="width:20%;">ext3：</td>
<td style="width:30%;"><input name="ext3" class="mini-textbox" required="true"   emptyText="请输入ext3"/></td>
<td style="width:20%;">ext4：</td>
<td style="width:30%;"><input name="ext4" class="mini-textbox" required="true"   emptyText="请输入ext4"/></td>
</tr>

            </table>
        </div>
       
        <div style="text-align:center;padding:10px;">               
            <a class="mini-button" onclick="onOk" style="width:60px;margin-right:20px;">确定</a>       
            <a class="mini-button" onclick="onCancel" style="width:60px;">取消</a>       
        </div>        
    </form>
	</fieldset>
    <script type="text/javascript">
        mini.parse();

        var form = new mini.Form("form1");

        function SaveData() {
            var o = form.getData();            

            form.validate();
            if (form.isValid() == false) return;

            var json = mini.encode([o]);
            $.ajax({
                url: "/pkemps/ProjectApply.do?method=saveProjectApply",
				type: 'post',
                data: { data: json},
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
                    url: "/pkemps/ProjectApply.do?method=getProjectApplydata&id=" + data.id,
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
