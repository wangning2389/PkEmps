<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>个人基础信息</title>
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
  <legend >个人基础信息</legend> 
  <form id="form1" method="post">
    <input name="id" class="mini-hidden"/>
    <div style="padding-left:11px;padding-bottom:5px;">
      <table style="table-layout:fixed;">
        <tr>
          <td style="width:20%;white-space:nowrap;text-align:right;"><span style="color:red;">*</span>社会保障卡号：</td>
          <td style="width:30%;"><input name="aac999" id="aac999" class="mini-textbox sasLabel" borderStyle="border:0" readonly="readonly" maxlength="10" style="width:100%;"/></td>
        </tr>
        <tr>
          <td style="width:20%;white-space:nowrap;text-align:right;"><span style="color:red;">*</span>姓名：</td>
          <td style="width:30%;"><input name="aac003" class="mini-textbox" required="true" maxlength="25" style="width:100%;" emptyText="请输入姓名"/></td>
          <td style="width:20%;white-space:nowrap;text-align:right;"><span style="color:red;">*</span>证件类型：</td>
          <td style="width:30%;"><input name="aac058" class="mini-combobox" valueField="bm" textField="mch"
                                        id="aac058" onvaluechanged="accept" style="width:100%;"
                                        url="${pageContext.request.contextPath }/AC01.do?method=getselect&lx=sfzjlx"
                                        required="true" emptyText="请选择证件类型"/></td>
                                        
        </tr>
        <tr>
          <td style="width:20%;white-space:nowrap;text-align:right;"><span style="color:red;">*</span>证件号码：</td>
          <td style="width:30%;"><input name="aac147" class="mini-textbox" required="true"
                                        onvalidation="onNumber" maxlength="18" style="width:100%;" emptyText="请输入证件号"/></td>
		  <td style="width:12%;text-align:right;white-space:nowrap;">证件有效期限：</td>
		  <td style="width:22%;"><input name="zjyxqx" class="mini-datepicker"  style="width:80%;"  emptyText="请输入证件有效期限"/></td>
        </tr>
        <tr>
          <td style="width:20%;white-space:nowrap;text-align:right;"><span style="color:red;">*</span>出生日期：</td>
          <td style="width:30%;"><input name="aac006" class="mini-datepicker" required="true" onvalidation="onBirthday"
                                        id="aac006" emptyText="请选择日期" style="width:100%;"/></td>
          <td style="width:20%;white-space:nowrap;text-align:right;"><span style="color:red;">*</span>性别：</td>
          <td style="width:30%;"><input name="aac004" class="mini-combobox" valueField="bm" textField="mch" id="aac004" 
                                        url="${pageContext.request.contextPath }/AC01.do?method=getselect&lx=xb"
                                        required="true" emptyText="请选择性别" style="width:100%;"/></td>
        </tr>
        <tr>
          <td style="width:20%;white-space:nowrap;text-align:right;"><span style="color:red;">*</span>民族：</td>
          <td style="width:30%;"><input name="aac005" class="mini-combobox" valueField="bm" textField="mch" id="aac005" 
                                        url="${pageContext.request.contextPath }/AC01.do?method=getselect&lx=mz"
                                        required="true" emptyText="请选择民族" style="width:100%;"/></td>
          <td style="width:20%;white-space:nowrap;text-align:right;"><span style="color:red;">*</span>国籍或地区：</td>
          <td style="width:30%;"><input name="aac161" class="mini-combobox" valueField="bm" textField="mch" id="aac161" 
                                        url="${pageContext.request.contextPath }/AC01.do?method=getselect&lx=gj"
                                        required="true" emptyText="请选择国籍或地区" style="width:100%;"/></td>
           <!-- <td style="width:20%;white-space:nowrap;text-align:right;"><span style="color:red;">*</span>省人员识别号：
          <td style="width:30%;"><input name="aac998" class="mini-textbox sasLabel" borderStyle="border:0" readonly="readonly" style="width:100%;"/></td> -->
        </tr>
        <tr>
        	 <td style="width:12%;text-align:right;white-space:nowrap;">联系电话：</td>
		   <td style="width:22%;"><input name="aae005" class="mini-textbox"  style="width:80%;" vtype="minLength:7" maxlength="20" emptyText="请输入联系电话"/></td>
        </tr>
      </table>
    </div>
    <div style="text-align:center;padding:10px;">
      <a class="mini-button" onclick="onOk" iconCls="icon-ok" style="width:60px;margin-right:20px;">确定</a>
      <a class="mini-button" onclick="onCancel" iconCls="icon-close" style="width:60px;">取消</a>
    </div>
  </form>
</fieldset>
<script type="text/javascript">
    mini.parse();
    var form = new mini.Form("form1");
    var tag = true;
    var fag = true;
    //数据保存
    function SaveData() {
      var o = form.getData();
      form.validate();
      if (form.isValid() == false) return;
         var json = mini.encode([o]);
         $.ajax({
                url: "/zyk/AC01.do?method=saveAC01",
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

    //标准方法接口定义
    function SetData(data) {
      if (data.action == "edit") {
         //跨页面传递的数据对象，克隆后才可以安全使用
         data = mini.clone(data);
         $.ajax({
                url: "/zyk/AC01.do?method=getAC01data&id=" + data.id,
                cache: false,
                success: function (text) {
                    var o = mini.decode(text);
                    form.setData(o);
                    form.setChanged(false);
                    }
                });
        }
    }

    // 根据所选的证件的类型自动输入国籍并清空其他关联项
    function accept(){
        var zjlx=mini.get("#aac058").getValue();
        if(zjlx == '01'||zjlx == '02'||zjlx == '03') {
            mini.get("#aac161").setValue("CHN");
            mini.getbyName("aac147").setValue("");
            mini.getbyName("aac006").setValue("");
        }else if(zjlx == '04') {
            mini.get("#aac161").setValue("HKG");
            mini.getbyName("aac147").setValue("");
            mini.getbyName("aac006").setValue("");
        }else if(zjlx == '05') {
            mini.get("#aac161").setValue("MAC");
            mini.getbyName("aac147").setValue("");
            mini.getbyName("aac006").setValue("");
        }else if(zjlx == '06') {
            mini.get("#aac161").setValue("TWN");
            mini.getbyName("aac147").setValue("");
            mini.getbyName("aac006").setValue("");
        }else {
            mini.get("#aac161").setValue("");
            mini.getbyName("aac147").setValue("");
            mini.getbyName("aac006").setValue("");
        }
    }

    //证件重复性和合法性验证
    function onNumber(e){
        var aac058=mini.getbyName("aac058").getValue();
        //身份证合法性验证
        if(aac058 == '01'){
            if(e.isValid){
                if(isNumber(e.value) == false){
                    e.errorText = "身份证输入不合法";
                    e.isValid = false;
                }else {
                    //身份证合法时自动录入出生日期和性别
                    mini.get("#aac006").setValue(e.value.substring(6,14));
                    mini.get("#aac004").setValue(e.value.substring(16,17)%2?"1":"2");
                }
            }
        }
    }

    //身份证合法型验证
    function isNumber(v){
        var tel = /^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i;
        var flag = true;
        if(!tel.test(v)){
            flag = false;
        }else{
            var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
            var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
            var nTemp = 0, i;
            var temp = v.substring(0,17);
            for (i = 0; i < temp.length; i++) {
                 nTemp += temp.substr(i, 1) * arrInt[i];
                }
                temp += arrCh[nTemp % 11];
            if(temp != v) {
               flag = false;
            }
        }
          return flag;
    }

    // 出生日期验证
    function onBirthday(e){
      var newDate = new Date();
      var aac006 = mini.get("#aac006").getValue();
      var nowDate = newDate.getFullYear()+"/"+((newDate.getMonth()+1)<10?"0":"")+(newDate.getMonth()+1)+"/"+(newDate.getDate()<10?"0":"")+newDate.getDate();
      var now_Date = new Date(Date.parse(nowDate));
      var AAC006 = new Date(Date.parse(aac006));
      if (AAC006 > now_Date) {
         e.errorText = "出生日期超过当前日期";
         e.isValid = false;
       }
    }

    // 数据保存
    function onOk(e) {
        var aac147 = mini.getbyName("aac147").getValue().trim();
        var aac058=mini.getbyName("aac058").getValue();
        var aac999 = mini.get("#aac999").getValue();
         $.ajax({
            url: "/zyk/AC01.do?method=getaac147",
                dataType : 'JSON',
                async: false,
                data : {aac147:aac147,aac058:aac058},
                success: function(result) {
                if (aac999 != result.aac999) {
                  //已经存在该身份证提示用户
                  if(result != null && aac058 == '01') {
                      tag = false;
                  }
                  if(result != null && aac058 != '01') {
                      fag = false;
                  }
                }
            }
        });
        if(tag == false) {
          mini.alert("身份证号码已经存在");
          mini.get("#aac006").setValue("");
          mini.get("#aac004").setValue("");
          mini.getbyName("aac147").setValue("");
        }else if (fag == false) {
          mini.alert("相同类型的证件号码已经存在");
          mini.getbyName("aac147").setValue("");
        }else {
          SaveData();
        }
    }

    function CloseWindow(action) {
      if (action == "close" && form.isChanged()) {
         if (confirm("数据被修改了，是否先保存？")) {
             return false;
             }
           }
         if (window.CloseOwnerWindow) {
             return window.CloseOwnerWindow(action);
         }else {
             window.close();
         }
    }

    function onCancel(e) {
      CloseWindow("cancel");
    }

    function GetData() {
        var o = form.getData();
        return o;
    }
</script>
</body>
</html>