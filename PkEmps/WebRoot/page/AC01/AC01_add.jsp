<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>个人基础信息登记</title>
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
          <td style="width:12%;white-space:nowrap;text-align:right;">姓名：</td>
          <td style="width:22%;"><input name="aac003" class="mini-textbox"  maxlength="50" style="width:80%;" emptyText="请输入姓名"/></td>
          <td style="width:12%;white-space:nowrap;text-align:right;">证件类型：</td>
          <td style="width:22%;"><input name="aac058" class="mini-combobox" valueField="bm" textField="mch" 
                                        id="aac058" onvaluechanged="accept" style="width:80%;"
                                        url="${pageContext.request.contextPath }/AC01.do?method=getselect&lx=sfzjlx"
                                        emptyText="请选择证件类型"/></td>
          <td style="width:12%;white-space:nowrap;text-align:right;">证件号码：</td>
          <td style="width:22%;"><input name="aac147" class="mini-textbox" 
                                        onvalidation="onNumber" maxlength="18" style="width:80%;" emptyText="请输入证件号"/></td>
        </tr>
        <tr>
<td style="width:12%;text-align:right;white-space:nowrap;">证件有效期限：</td>
<td style="width:22%;"><input name="zjyxqx" class="mini-datepicker"  style="width:80%;"  emptyText="请输入证件有效期限"/></td>
          <td style="width:12%;white-space:nowrap;text-align:right;">出生日期：</td>
          <td style="width:22%;"><input name="aac006" class="mini-datepicker"  onvalidation="onBirthday"
                                        id="aac006" emptyText="请选择日期" style="width:80%;"/></td>
          <td style="width:12%;white-space:nowrap;text-align:right;">性别：</td>
          <td style="width:22%;"><input name="aac004" class="mini-combobox" valueField="bm" textField="mch" id="aac004" 
                                        url="${pageContext.request.contextPath }/AC01.do?method=getselect&lx=xb"
                                         emptyText="请选择性别" style="width:80%;"/></td>
        <tr>
          <td style="width:12%;white-space:nowrap;text-align:right;">民族：</td>
          <td style="width:22%;"><input name="aac005" class="mini-combobox" valueField="bm" textField="mch" id="aac005" 
                                        url="${pageContext.request.contextPath }/AC01.do?method=getselect&lx=mz"
                                         emptyText="请选择民族" style="width:80%;"/></td>
        
          <td style="width:12%;white-space:nowrap;text-align:right;">国籍或地区：</td>
          <td style="width:22%;"><input name="aac161" class="mini-combobox" valueField="bm" textField="mch" id="aac161" 
                                        url="${pageContext.request.contextPath }/AC01.do?method=getselect&lx=gj"
                                        emptyText="请选择国籍或地区" style="width:80%;"/></td>
<td style="width:12%;text-align:right;white-space:nowrap;">联系电话：</td>
<td style="width:22%;"><input name="aae005" class="mini-textbox"  style="width:80%;" vtype="minLength:7" maxlength="20" emptyText="请输入联系电话"/></td>
        </tr>
        
        
        <tr>
<td style="width:12%;text-align:right;white-space:nowrap;">省人员识别码：</td>
<td style="width:22%;"><input name="aac998" class="mini-textbox"  style="width:80%;"  emptyText="请输入省人员识别码"/></td>
</tr>
<tr>
<td style="width:12%;text-align:right;white-space:nowrap;"><span style="color:red;">*</span>户口性质：</td>
<td style="width:22%;"><input name="aac009" class="mini-combobox" valueField="bm" textField="mch"  required="true"
                                        url="${pageContext.request.contextPath }/AC01.do?method=getselect&lx=hkxz"  style="width:80%;"  emptyText="请输入户口性质"/></td>
<td style="width:12%;text-align:right;white-space:nowrap;">户籍行政区：</td>
<td style="width:22%;"><input name="cac205" class="mini-textbox"  style="width:80%;"  emptyText="请输入户籍行政区"/></td>
<td style="width:12%;text-align:right;white-space:nowrap;">户籍街道：</td>
<td style="width:22%;"><input name="aaf013" class="mini-textbox"  style="width:80%;"  emptyText="请输入户籍街道"/></td>
</tr>
<tr>
<td style="width:12%;text-align:right;white-space:nowrap;">户籍社区：</td>
<td style="width:22%;"><input name="cac351" class="mini-textbox"  style="width:80%;"  emptyText="请输入户籍社区"/></td>
<td style="width:12%;text-align:right;white-space:nowrap;">户籍地址：</td>
<td style="width:22%;"><input name="aac010" class="mini-textbox"  style="width:80%;"  emptyText="请输入A户籍地址"/></td>
<td style="width:12%;text-align:right;white-space:nowrap;">所属行政区：</td>
<td style="width:22%;"><input name="cac217" class="mini-textbox"  style="width:80%;"  emptyText="请输入所属行政区"/></td>
</tr>
<tr>
<td style="width:12%;text-align:right;white-space:nowrap;">所属街道：</td>
<td style="width:22%;"><input name="cac218" class="mini-textbox"  style="width:80%;"  emptyText="请输入所属街道"/></td>
<td style="width:12%;text-align:right;white-space:nowrap;">所属社区：</td>
<td style="width:22%;"><input name="cac219" class="mini-textbox"  style="width:80%;"  emptyText="请输入所属社区"/></td>
<td style="width:12%;text-align:right;white-space:nowrap;"><span style="color:red;">*</span>参加工作日期：</td>
<td style="width:22%;"><input name="aac007" class="mini-datepicker"  style="width:80%;" vtype="date:yyyy-MM-dd" required="true" emptyText="请输入参加工作日期"/></td>
</tr>
<tr>
<td style="width:12%;text-align:right;white-space:nowrap;">政治面貌：</td>
<td style="width:22%;"><input name="aac024" class="mini-combobox" valueField="bm" textField="mch"  
                                        url="${pageContext.request.contextPath }/AC01.do?method=getselect&lx=zzmm"  style="width:80%;"  emptyText="请输入政治面貌"/></td>
<td style="width:12%;text-align:right;white-space:nowrap;">生存状态：</td>
<td style="width:22%;"><input name="aac060" class="mini-textbox"  style="width:80%;"  value="正常"/></td>
<td style="width:12%;text-align:right;white-space:nowrap;">行政职务：</td>
<td style="width:22%;"><input name="aac024" class="mini-combobox" valueField="bm" textField="mch"  
                                        url="${pageContext.request.contextPath }/AC01.do?method=getselect&lx=xzzw"  style="width:80%;"  emptyText="请输入行政职务"/></td>
</tr>
<tr>
<td style="width:12%;text-align:right;white-space:nowrap;">技术职务：</td>
<td style="width:22%;"><input name="aac200" class="mini-textbox"  style="width:80%;"  emptyText="请输入技术职务"/></td>
<td style="width:12%;text-align:right;white-space:nowrap;">技能等级：</td>
<td style="width:22%;"><input name="aac015" class="mini-combobox" valueField="bm" textField="mch"  
                                        url="${pageContext.request.contextPath }/AC01.do?method=getselect&lx=jndj"  style="width:80%;"  emptyText="请输入技能等级"/></td>
<td style="width:12%;text-align:right;white-space:nowrap;">邮政编码：</td>
<td style="width:22%;"><input name="aac047" class="mini-textbox"  style="width:80%;"  emptyText="请输入邮政编码"/></td>
</tr>
<tr>
<td style="width:12%;text-align:right;white-space:nowrap;">家庭地址：</td>
<td style="width:22%;"><input name="cac220" class="mini-textbox"  style="width:80%;"  emptyText="请输入家庭地址"/></td>
<td style="width:12%;text-align:right;white-space:nowrap;">电子邮件：</td>
<td style="width:22%;"><input name="aae159" class="mini-textbox"  style="width:80%;"  emptyText="请输入电子邮件"/></td>
<td style="width:12%;text-align:right;white-space:nowrap;">文化程度/学历：</td>
<td style="width:22%;"><input name="aac011" class="mini-combobox" valueField="bm" textField="mch"  
                                        url="${pageContext.request.contextPath }/AC01.do?method=getselect&lx=whcd" style="width:80%;"  emptyText="请输入文化程度/学历"/></td>
</tr>
<tr>
<td style="width:12%;text-align:right;white-space:nowrap;">毕业学校：</td>
<td style="width:22%;"><input name="cac642" class="mini-textbox"  style="width:80%;"  emptyText="请输入毕业学校"/></td>
<td style="width:12%;text-align:right;white-space:nowrap;">毕业时间：</td>
<td style="width:22%;"><input name="cac639" class="mini-textbox"  style="width:80%;"  emptyText="请输入毕业时间"/></td>
<td style="width:12%;text-align:right;white-space:nowrap;">就业单位名称：</td>
<td style="width:22%;"><input name="jydwmc" class="mini-textbox"  style="width:80%;"  emptyText="请输入就业单位名称"/></td>
</tr>
<tr>
<td style="width:12%;text-align:right;white-space:nowrap;">就业形势：</td>
<td style="width:22%;"><input name="jyxs" class="mini-textbox"  style="width:80%;"  emptyText="请输入就业形势"/></td>
<td style="width:12%;text-align:right;white-space:nowrap;">就业状态：</td>
<td style="width:22%;"><input name="aac016" class="mini-combobox" valueField="bm" textField="mch"  
                                        url="${pageContext.request.contextPath }/AC01.do?method=getselect&lx=jyzt"  style="width:80%;"  emptyText="请输入就业状态"/></td>
<td style="width:12%;text-align:right;white-space:nowrap;">实现就业登记日期：</td>
<td style="width:22%;"><input name="sxjydjrq" class="mini-textbox"  style="width:80%;"  emptyText="请输入实现就业登记日期"/></td>
</tr>
<tr>
<td style="width:12%;text-align:right;white-space:nowrap;">终止就业登记日期：</td>
<td style="width:22%;"><input name="zzjydjrq" class="mini-datepicker"  style="width:80%;"  emptyText="请输入终止就业登记日期"/></td>
<td style="width:12%;text-align:right;white-space:nowrap;">失业登记日期：</td>
<td style="width:22%;"><input name="ajc094" class="mini-datepicker"  style="width:80%;"  emptyText="请输入失业登记日期"/></td>
<td style="width:12%;text-align:right;white-space:nowrap;">注销失业登记日期：</td>
<td style="width:22%;"><input name="zxsydjrq" class="mini-datepicker"  style="width:80%;"  emptyText="请输入注销失业登记日期"/></td>
</tr>
<tr>
<td style="width:12%;text-align:right;white-space:nowrap;">个人参保日期：</td>
<td style="width:22%;"><input name="aac030" class="mini-datepicker"  style="width:80%;"  emptyText="请输入个人参保日期"/></td>
<td style="width:12%;text-align:right;white-space:nowrap;">参保地所在统筹地区名称：</td>
<td style="width:22%;"><input name="aae380" class="mini-textbox"  style="width:80%;"  emptyText="请输入参保地所在统筹地区名称"/></td>
<td style="width:12%;text-align:right;white-space:nowrap;">参保地行政区划代码：</td>
<td style="width:22%;"><input name="cbdxzdm" class="mini-textbox"  style="width:80%;"  emptyText="请输入参保地行政区划代码"/></td>
</tr>
<tr>
<td style="width:12%;text-align:right;white-space:nowrap;">参保单位名称：</td>
<td style="width:22%;"><input name="cbdwmc" class="mini-textbox"  style="width:80%;"  emptyText="请输入参保单位名称"/></td>
<td style="width:12%;text-align:right;white-space:nowrap;">离退休标识：</td>
<td style="width:22%;"><input name="aac084" class="mini-combobox" valueField="bm" textField="mch"  
                                        url="${pageContext.request.contextPath }/AC01.do?method=getselect&lx=ltxbz"  style="width:80%;"  emptyText="请输入离退休标识"/></td>
<td style="width:12%;text-align:right;white-space:nowrap;">退役军人标识：</td>
<td style="width:22%;"><input name="aac064" class="mini-combobox" valueField="bm" textField="mch"  
                                        url="${pageContext.request.contextPath }/AC01.do?method=getselect&lx=tyjrbz" style="width:80%;"  emptyText="请输入退役军人标识"/></td>
</tr>
<tr>
<td style="width:12%;text-align:right;white-space:nowrap;">农民工标识：</td>
<td style="width:22%;"><input name="aac028" class="mini-combobox" valueField="bm" textField="mch"  
                                        url="${pageContext.request.contextPath }/AC01.do?method=getselect&lx=nmgbz"  style="width:80%;"  emptyText="请输入农民工标识"/></td>
<td style="width:12%;text-align:right;white-space:nowrap;">用工形式：</td>
<td style="width:22%;"><input name="aac013" class="mini-combobox" valueField="bm" textField="mch"  
                                        url="${pageContext.request.contextPath }/AC01.do?method=getselect&lx=ygxs"  style="width:80%;"  emptyText="请输入用工形式"/></td>
<td style="width:12%;text-align:right;white-space:nowrap;">劳动合同期限起始日期：</td>
<td style="width:22%;"><input name="htqxqsrq" class="mini-datepicker"  style="width:80%;"  emptyText="请输入劳动合同期限起始日期"/></td>
</tr>
<%-- <tr>
<td style="width:12%;text-align:right;white-space:nowrap;">劳动合同期限终止日期：</td>
<td style="width:22%;"><input name="htqxzzrq" class="mini-datepicker"  style="width:80%;"  emptyText="请输入劳动合同期限终止日期"/></td>
<td style="width:12%;text-align:right;white-space:nowrap;">劳动合同解除日期：</td>
<td style="width:22%;"><input name="htjcrq" class="mini-datepicker"  style="width:80%;"  emptyText="请输入劳动合同解除日期"/></td>
<td style="width:12%;text-align:right;white-space:nowrap;">劳动合同解除原因：</td>
<td style="width:22%;"><input name="htjcyy" class="mini-textbox"  style="width:80%;"  emptyText="请输入劳动合同解除原因"/></td>
</tr>
<tr>
<td style="width:12%;text-align:right;white-space:nowrap;">劳动合同终止日期：</td>
<td style="width:22%;"><input name="htzzrq" class="mini-datepicker"  style="width:80%;"  emptyText="请输入劳动合同终止日期"/></td>
<td style="width:12%;text-align:right;white-space:nowrap;">劳动合同终止原因：</td>
<td style="width:22%;"><input name="htzzyy" class="mini-textbox"  style="width:80%;"  emptyText="请输入劳动合同终止原因"/></td>
<td style="width:12%;text-align:right;white-space:nowrap;">险种类型：</td>
<td style="width:22%;"><input name="aae243" class="mini-textbox"  style="width:80%;"  emptyText="请输入险种类型"/></td>
</tr>
<tr>
<td style="width:12%;text-align:right;white-space:nowrap;">人员参保状态：</td>
<td style="width:22%;"><input name="aac008" class="mini-combobox" valueField="bm" textField="mch"  
                                        url="${pageContext.request.contextPath }/AC01.do?method=getselect&lx=cbzt"  style="width:80%;"  emptyText="请输入人员参保状态"/></td>
<td style="width:12%;text-align:right;white-space:nowrap;">人员缴费状态：</td>
<td style="width:22%;"><input name="aac031" class="mini-textbox"  style="width:80%;"  emptyText="请输入人员缴费状态"/></td>
</tr>        --%> 
        
        
      </table>
    </div>
    <div style="text-align:center;padding:10px;">
      <a class="mini-button" onclick="onOk" iconCls="icon-ok" style="width:60px;margin-right:20px;">确定</a>
      <a class="mini-button" onclick="onReset" iconCls="icon-remove" style="width:60px;">重置</a>
    </div>
  </form>
</fieldset>
<script type="text/javascript">
    mini.parse();
    var form = new mini.Form("form1");
    mini.get("#aac005").setValue("01");
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
                   mini.alert("个人信息登记成功;社会保障卡号为:"+text);
                   form.clear();
                   return;
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    CloseWindow();
                }
            });
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
        var aac147 = mini.getbyName("aac147").getValue().trim();
        var aac058=mini.getbyName("aac058").getValue();
         $.ajax({
            url: "/zyk/AC01.do?method=getaac147",
                dataType : 'JSON',
                data : {aac147:aac147,aac058:aac058},
                success: function(result) {
                //已经存在该身份证提示用户
                if(result != null && aac058== '01') {
                    mini.alert("身份证号码已经存在");
                    mini.get("#aac006").setValue("");
                    mini.get("#aac004").setValue("");
                    mini.getbyName("aac147").setValue("");
                }
                if(result != null && aac058!= '01') {
                    mini.alert("相同类型的证件号码已经存在");
                    mini.getbyName("aac147").setValue("");
                }
            }
        });
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

    function onOk(e) {
        SaveData();
    }

    function GetData() {
        var o = form.getData();
        return o;
    }

    function onReset(e) {
        form.clear();
    }
</script>
</body>
</html>