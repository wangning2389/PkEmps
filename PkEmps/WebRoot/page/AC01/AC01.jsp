<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>个人基础信息查询</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<script src="${pageContext.request.contextPath }/scripts/boot.js" type="text/javascript"></script>
</head>
<style type="text/css">
html,body {
    margin: 0;
    padding: 0;
    border: 0;
    width: 100%;
    height: 100%;
    overflow: hidden;
}
</style>
<body>
<form id="form1" method="post">
  <div style="padding-bottom:5px;">
    <table style="border:solid 1px #aaa;width:100%;">
      <tr>
        <td style="width:9%;text-align: right;">社会保障卡号:</td>
        <td style="width:15%;"><input name="aac999" class="mini-textbox" emptyText="请输入社会保障卡号" /></td>
        <td style="width:6%;text-align: right;">证件类型：</td>
        <td style="width:15%;"><input name="aac058" class="mini-combobox" valueField="bm" 
                                      textField="mch" id="aac058" emptyText="请选择证件类型"
                                      url="${pageContext.request.contextPath }/AC01.do?method=getselect&lx=sfzjlx"/>
        </td>
        <td style="width:6%;text-align: right;">证件号码：</td>
        <td style="width:15%;"><input name="aac147" emptyText="请输入证件号码" class="mini-textbox" />
        </td>
        <td style="width:4%;text-align: right;">姓名：</td>
        <td style="width:15%;"><input name="aac003" emptyText="请输入姓名" class="mini-textbox" /></td>
        <td style="width:15%;text-align: center; white-space:nowrap;">
          <a class="mini-button" iconCls="icon-search" onclick="search()">查询</a>
          <a class="mini-button" iconCls="icon-remove"  onclick="onReset">重置</a>
        </td>
      </tr>
    </table>
  </div>
</form>
<div style="width:100%;">
  <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
    <table style="width:100%;">
      <tr>
        <td style="width:100%;">
          <a class="mini-button" iconCls="icon-edit" onclick="edit()">编辑</a>
        </td>
      </tr>
    </table>
  </div>
</div>
<div class="mini-fit">
  <div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" allowResize="true"
       url="AC01.do?method=getAC01" idField="id" multiSelect="true">
    <div property="columns">
      <div type="checkcolumn"></div> 
      <div field="aac999" width="120" headerAlign="center" allowSort="true">社会保障卡号</div>
      <div field="aac003" width="120" headerAlign="center" allowSort="true">姓名</div>
      <div field="aac058" width="120" headerAlign="center" allowSort="true">证件类型</div>
      <div field="aac147" width="120" headerAlign="center" allowSort="true">证件号码</div>
      <div field="aac006" width="120" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">出生日期</div>
      <div field="zjyxqx" width="120" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">证件有效日期</div>
      <div field="aac004" width="120" headerAlign="center" allowSort="true" >性别</div>
      <div field="aac005" width="120" headerAlign="center" allowSort="true">民族</div>
      <div field="aac161" width="120" headerAlign="center" allowSort="true">国籍或地区</div>
      <div field="aae005" width="120" headerAlign="center" allowSort="true">联系电话</div>
     <!--  <div field="aac998" width="120" headerAlign="center" allowSort="true">省人员识别号</div> -->
    </div>
  </div>
</div>
<script type="text/javascript">
    mini.parse();
    var grid = mini.get("datagrid1");
    var form = new mini.Form("form1");
    grid.load();
    function edit() {
      var row = grid.getSelected();
      if (row) {
          mini.open({
                    url: bootPATH + "../page/AC01/AC01_edit.jsp",
                    title: "编辑", width: 650, height: 460,
                    onload: function () {
                        var iframe = this.getIFrameEl();
                        var data = { action: "edit", id: row.aac999 };
                        iframe.contentWindow.SetData(data);
                        },
                    ondestroy: function (action) {
                        grid.reload();
                        }
                    });
               } else {
                 mini.alert("请选中一条记录");
               }
    }

    function search() {
      var AAC999 = mini.getbyName("aac999").getValue().trim();
      var AAC058 = mini.getbyName("aac058").getValue();
      var AAC147 = mini.getbyName("aac147").getValue().trim();
      var AAC003 = mini.getbyName("aac003").getValue().trim();
      if (AAC999 == "" && AAC058 == "" && AAC147 == "" && AAC003 == "") {
         mini.alert("请输入查询条件");
      }
      grid.load({ AAC999: AAC999, AAC058: AAC058, AAC147: AAC147, AAC003: AAC003});
      }

    function onKeyEnter(e) {
      search();
    }

    function onReset(e) {
       form.clear();
    }
</script>
</body>
</html>
