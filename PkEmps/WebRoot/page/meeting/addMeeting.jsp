<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" 
    pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>评审会新增</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />  
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
        评审会条件
      </legend>
      <form id="form1" method="post">
        <input name="id" class="mini-hidden"/>
        <div style="padding-left: 11px; padding-bottom: 5px;">
          <table style="padding-left: 21px; padding-bottom: 5px;">
            <tr height="34">
              <td  align="right" nowrap="nowrap">
                <span style="color:red;">*</span>主题：
              </td>
              <td >
                <input name="theme" class="mini-textbox" maxlength="20"id="theme" required="true"requiredErrorText="请输入主题"
                  style="width: 185px;" align="left"/>
              </td>
              
              
              <td  align="right" nowrap="nowrap">
                &nbsp&nbsp&nbsp&nbsp<span style="color:red;">*</span>时间：
              </td>
              <td >
                <input name="time" class="mini-datepicker"  required="true" allowInput="false"
                  id="time" align="right"
                  style="width: 185px;" />
              </td>
              <td  align="right" nowrap="nowrap">
              		评审会主任：
              </td>
              <td >
                <input name="chairman" 
                  id="chairman"  class="mini-buttonedit" onbuttonclick="onButtonEdit" align="left"allowInput="true"
                  style="width: 185px;" />
              </td>
              </tr>
              <tr height="34">          
              <td align="right" nowrap="nowrap">
            		  评审会副主任：
              </td>
              <td >
                <input name="vicechairman" class="mini-buttonedit" id="vicechairman" onbuttonclick="onButtonEdit1"allowInput="true"
                  style="width: 185px;" align="left"/>
              </td>
  
            </tr>
             <tr height="34">
             <td></td>
             <td>
                 <a class="mini-button" iconCls="icon-add" onclick="addteam()" tooltip="增加...">添加组</a>
             </td>
             </tr>
          </table>  
        </div>
      </form>
   
  <div style="padding-top:5px;padding-bottom:5px;padding-left: 80px;" name="selectmajor" id="selectmajor">  
  </div>
  </fieldset> 
  
  
  
        <div style="text-align: center; padding: 10px;" id="choose">
          <a class="mini-button" onclick="chooseUser()";iconCls="icon-ok"
            style="width: 60px; margin-right: 20px;">抽取</a>
          
        </div>  
  </fieldset>
  
  <fieldset
      style="border: solid 1px #aaa; padding: 3px; " id="teamtitle">
      <legend>
        专家分组信息
      </legend>
    <div style="padding-top:5px;padding-bottom:5px;padding-left: 45px;" name="teamshow" id="teamshow">
    
    
    
    </div>   
  </fieldset>    
    
        <div id="submit" style="text-align: center; padding: 10px;">
          <a class="mini-button" onclick="submit()";iconCls="icon-ok"
            style="width: 60px; margin-right: 20px;">确认</a>
          <a class="mini-button" onclick="willsubmit()";iconCls="icon-ok"
            style="width: 60px; margin-right: 20px;">暂存</a>

        </div>
    <script type="text/javascript">
  mini.parse();

  $("#teamtitle").hide();
  $("#submit").hide();
//表格绑定

    var dic="1";
   //获取后台字典
    $.ajax({
        url: "${pageContext.request.contextPath}/module/user.do?method=getDic&type=major,subject,filed,null",
        cache: false,
        success: function (text) {
             dic = mini.decode(text);
             //将json数据转换为map
             var map = {};
             $.each(dic,function(k, v){
                 var key = v.type;
                 if(!map[key]){
                   map[key] = {}               
                 }
                 map[key][v.code] = v.text;  
             })
             window['map'] = map;
            
           
            }
        });

		var zuID=0;
	
		function addteam(){
				var id=zuID++;
				$("#selectmajor").append(" <fieldset id=\""+id+"\"  style='padding-top:5px;padding-bottom:5px;width:730px;height:310px;'>"+
						  "<legend>组信息"+(id+1)+"</legend><div style='width:725px;height:32px;'><div class='mini-toolbar' style='border-bottom:0;padding:2px;'>"+
						  " <table style='width:100%;'><tr><td  align='left' nowrap='nowrap'><span style='color:red;'>*</span>组名称：<input name='teamname"+id+"' class='mini-textbox' maxlength='20'id='teamname"+id+"' required='true' style='width: 165px;' align='left'/></td>"+
						   "<td align='left' ><a class='mini-button' iconCls='icon-add' onclick='addRow(\"teamgrid"+id+"\")' plain='true' tooltip='增加...'>增加专业信息</a><a class='mini-button' iconCls='icon-remove' onclick='removeRow(\"teamgrid"+id+"\")' plain='true'>删除专业信息</a></td></tr></table></div></div>"+
						   " <div id='teamgrid"+id+"' class='mini-datagrid' style='width:725px;height:250px;' showPager='false' allowCellEdit='true'  allowCellSelect='true' multiSelect='true'"+ 
						     "enterNextCell='true'   allowMoveColumn='true' editNextOnEnterKey='true'  _showPagerButtonIcon='false' _showPagerButtonText='true' skipReadOnlyCell='true'"+
						  " idField='id' allowResize='true'><div property='columns'> <div type='checkcolumn'></div><div type='indexcolumn'></div><div field='major' width='120' headerAlign='center' allowSort='false'   renderer='trmajor'>专业"+
						   "<input property='editor' name='major' id='major' class='mini-treeselect' url='${pageContext.request.contextPath }/module/user.do?method=getselect&type=major' multiSelect='false'  valueFromSelect='true'"+
						   "textField='text' valueField='code' parentField='parent_code'  onbeforenodeselect='beforenodeselect' allowInput='true' showRadioButton='true' showFolderCheckBox='false' required='true'/></div> "+
						   " <div field='majornum' width='100' allowSort='false'>本专业人数<input property='editor' name='majornum' id='majornum' class='mini-textbox' required='true'/></div>"
						   +"<div field='voidcompany' width='100' allowSort='false'>回避单位<input property='editor' name='voidcompany' id='voidcompany' class='mini-textbox' /></div></div></div> "
						    );
				mini.parse();
			}


    
       
        function addRow(id) {
            console.log(id);
            var grid=mini.get(id);        
            var newRow = { name: "New Row" };
            grid.addRow(newRow, 0);

            grid.beginEditCell(newRow, "major");
        }
        function removeRow(id) {
        	var grid=mini.get(id);
            var rows = grid.getSelecteds();
            if (rows.length > 0) {
                grid.removeRows(rows, true);
            }
        }
     
    var form1 = new mini.Form("form1");
	var gridInfo=null;

        //抽取
    function chooseUser() {
      
      form1.validate();
      if (form1.isValid() == false)
        return;
      var o = form1.getData();
	 var dataMap={};
	 var teamnameMap={};
	 console.log(zuID);
      for(var i=0;i<zuID;i++){
			var team_grid=mini.get("teamgrid"+i);
			var data = team_grid.getChanges();
			var teamname=mini.get("teamname"+i).getValue();
			if(!teamname){
					mini.alert("组名不可为空！");
					return;
				}
			console.log(data);
			var val=true;
            var valnum =true;
            //抽取信息校验 
        	$.each(data, function(k ,v){
            	if(!v.major||!v.majornum){
						val=false;
						return ;
                	}
            	 var pattern=/^[0-9]*$/;
            	    if (pattern.test(v.majornum) == false) {
            	    	valnum=false;
            	          return;
            	      } 
            	
                }) 
                if(!val){
                mini.alert("请填写完整专业抽取信息！");
                return;
                }
        	if(!valnum){
                mini.alert("专业人数请填写数字！");
                return;
                }
        	dataMap[i]=data;
        	teamnameMap[i]=teamname;
            }
			for(var i=0;i<zuID-1;i++){
					for(var j=i+1;j<zuID;j++){
							if(teamnameMap[i]==teamnameMap[j]){
								mini.alert("组名称不可以重复！");
				                return;
								}
						}
				}
      
      var jsonGrid=JSON.stringify(dataMap);
      var teamnamejson=JSON.stringify(teamnameMap);
      console.log(teamnamejson);
      var json = mini.encode([o]);
      $.ajax({
          url: "${pageContext.request.contextPath}/module/meeting.do?method=chooseMeeting",
          data: { data: json,
      			grid: jsonGrid,
      			teamname:teamnamejson
               },
          type: "post",
          success: function (text) {
                   
                 $("#teamtitle").show();
                 var team=null;
                 var person=null;
                 var o = mini.decode(text);
                 gridInfo=o;
                 console.log(o);
                         var map = {};
               
                        var i=0;                                                                                                                                                           
                        $.each(o, function(k, v){
                          creatTeamGrid(v.teamname);                             
                          mini.parse();
                                  var grid0 = mini.get("teamdatagrid"+v.teamname);
                                   for(var j=0;j<v.person.length;j++){
                                       var data=v.person[j];
                                         grid0.addRow(data,j);
                                   }

                                 i++;
                          })
                       
    
                   $("#choose").hide();
                   $("#submit").show();
          },
          error: function (jqXHR, textStatus, errorThrown) {
              var msg=mini.decode(jqXHR.responseText);
              alert(msg.exMsg);
          }
      });
          }


      

 
        
     
  
  
  //数据保存
  function SaveData(s){
    var o = form1.getData();
    if(o.vicechairman&&o.chairman){
        if(o.vicechairman==o.chairman){
    	 mini.alert("主任副主任不可以选同一人！");
         return;
        }
        }
    form1.validate();
    if (form1.isValid() == false){
      return;
    }
    var result = {};
    var result1 = {};
      var valid=true;
      console.log(gridInfo);
      $.each(gridInfo, function(k, v){
             var j=0;
               var c=0;
               var id="teamdatagrid"+v.teamname;
               console.log(id);
                    var grids = mini.get("teamdatagrid"+v.teamname);
                    var data=grids.getChanges();

                     //校验是否选组长
             for(i=0;i<data.length;i++){//所有组长清零
              var one =data[i];
              if(one.teamleader=='1'){
              c++;
                        }    
                  }
                  if(c==0){
              
              valid=false;
              return false;
                   }
                  result[v.teamname]=data;
      			j++;
      			
              })
             
        if(!valid){
                	  mini.alert("有未选择组长的小组，请完善！");
                	  return;
                      }
  
    var jsonGrid=JSON.stringify(result);
    var json = mini.encode( [o]);
    $.ajax( {
      url : "${pageContext.request.contextPath}/module/meeting.do?method=saveMeeting",
      type : 'post',
      data : {
        data : json,
        grid:jsonGrid,
        savestatus:s
      },
      cache : false,
      success : function(text) {
    	  mini.alert("评审会信息保存成功！", "提示", function() {
				CloseWindow();
			});
      },
      error : function(jqXHR, textStatus, errorThrown) {
        var msg=mini.decode(jqXHR.responseText);
        alert(msg.exMsg);
        CloseWindow();
      }
    });
  }
  
  
  

     function beforenodeselect(e) {
         //禁止选中父节点
         if (e.isLeaf == false) e.cancel = true;
     }



  function onReset() {
    form1.clear();
  }



  function trmajor(e){
    return window['map']['major'][e.value];
  
      }


  function checknumber(e){
    var pattern=/^[0-9]*$/;
    if (pattern.test(e.value) == false) {
      mini.alert("请输入数字！"); 
      e.isValid = false;
          return;
      } 
    }
//创建动态列表、

  function creatTeamGrid(teamname){
        //$("#teamshow1").remove();
        $("#teamshow").append("<div id=teamshow1> </div>");
    
            id = teamname;
            console.log(id);
          
          
        $("#teamshow1").append(
            "<div style='width:800px;'><div class='mini-toolbar' style='border-bottom:0;padding:0px;'><table style='width:100%;'><tr><td style='width:100%;'>"+
                 "<a class='mini-button' iconCls='icon-add' onclick='add(\""+id+"\")'>增加</a><a class='mini-button' iconCls='icon-remove' onclick='remove(\""+id+"\")'>删除</a><a class='mini-button' iconCls='icon-edit' onclick='check(\""+id+"\")'>查看</a><a class='mini-button'  onclick='setleader(\""+id+"\")'>设为组长</a><a><b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+
                 "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp组名："+teamname+"</b></a></td></tr></table></div></div>"+
            "<div id='teamdatagrid"+id+"' showPager='false' class='mini-datagrid' style='width:800px;height:280px;' allowResize='true'idField='id' multiSelect='true' >"+
                "<div property='columns'><div type='checkcolumn' ></div><div type='indexcolumn'></div>  <div field='name' width='120' headerAlign='center' allowSort='false'>专家姓名</div> "+   
                    " <div field='mobile' width='120' headerAlign='center' allowSort='false'>电话</div> <div field='major' width='120' headerAlign='center' renderer='trmajor' allowSort='false'>专业</div><div  field='teamleader'  width='60' headerAlign='center' renderer='trleader'>是否为组长</div> </div></div>"
              );
        
        
  }
  function remove(e) {
    var grid =mini.get("teamdatagrid"+e);  
    console.log(grid);   
    var rows = grid.getSelecteds();
        if (rows.length > 0) {
            grid.removeRows(rows, true);
        }
    }

    function add(id){
      mini.open({
      url : "${pageContext.request.contextPath}/page/meeting/addmmetingeExpUser.jsp",
      title : "添加专家信息",
      width: 900, 
            height: 520,
          
      ondestroy : function(action) {
               if (action == "ok") {
                     var iframe = this.getIFrameEl();
                     var data = iframe.contentWindow.GetData();
                     data = mini.clone(data);    //必须
                     if (data) {
                    	 var valid=true;
                    		 $.each(gridInfo, function(k, v){
                        				 var j=0;
                                        var grids = mini.get("teamdatagrid"+v.teamname);
                                        var griddata=grids.getChanges();
                                        for(var i=0;i<griddata.length;i++){
                               			 if(griddata[i].id==data.id){
           										valid=false;
           											return false;//跳出each方法
           										}
           	                    		 }
          	                    		 j++;
                                 })
                                 if(!valid){
         	                    	mini.alert("该专家已在评审会名单中！");
         	                    	 return;
         	                    	 }
                    
                       var grid =mini.get("teamdatagrid"+id); 
                       grid.addRow(data,0);
                     }
                 }
      }
    });
        }
    function CloseWindow(action) {
    if (window.CloseOwnerWindow)
      return window.CloseOwnerWindow(action);
    else
      window.close();
  }



    function onButtonEdit(e) {
        var btnEdit=mini.get("chairman");
        mini.open({
            url:"${pageContext.request.contextPath}/page/meeting/addmmetingeExpUser.jsp",
            title : "添加主任信息",
      width: 800, 
            height: 520,
            ondestroy: function (action) {
                //if (action == "close") return false;
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = mini.clone(data);    //必须
                    if (data) {
                        //btnEdit.setValue(data.id);
                        btnEdit.setValue(data.name);
                        btnEdit.setText(data.name);
                    }
                }

            }
        });

    }

    function onButtonEdit1(e) {
    var btnEdit=mini.get("vicechairman");
    mini.open({
        url:"${pageContext.request.contextPath}/page/meeting/addmmetingeExpUser.jsp",
        title : "添加主任信息",
    width: 800, 
        height: 520,
        ondestroy: function (action) {
            //if (action == "close") return false;
            if (action == "ok") {
                var iframe = this.getIFrameEl();
                var data = iframe.contentWindow.GetData();
                data = mini.clone(data);    //必须
                if (data) {
                    //btnEdit.setValue(data.id);
                    btnEdit.setValue(data.name);
                    btnEdit.setText(data.name);
                }
            }

        }
    });

}
  function setleader(id){
    var grid =mini.get("teamdatagrid"+id);     
    var rows = grid.getSelecteds();
    
    
        if (rows.length ==1) {
          var all=grid.getChanges();
          for(i=0;i<all.length;i++){//所有组长清零
          var one =all[i];
            one.teamleader='0';  
            grid.updateRow(all[i], one);    
              }
            var o = rows[0];
            o.teamleader ='1';
          grid.updateRow(rows[0], o);
        }else{
          mini.alert("请选择一条要设置的数据!");
            }

    }
  function trleader(e){
		if(e.value==1){
			return "是";
			}else {
				return "否";
				}
	  }


  function submit(){
		var savestatus=1;
		SaveData(savestatus);
	  }
  function willsubmit(){
		var savestatus=2;
		SaveData(savestatus);
	  }
  function check(e) {
	    var grid =mini.get("teamdatagrid"+e);  
	    var se = grid.getSelecteds();
		if (se.length == 1) {
			mini.open({
				url : "${pageContext.request.contextPath}/page/user/viewExpUser.jsp?id="+se[0].id,
				title : "查看专家信息",
				width: 1000, 
	            height: 580,
	            onload: function () {
	                var iframe = this.getIFrameEl();
	                var data = { action: "edit", id: se[0].id };
	                iframe.contentWindow.SetData(data);
	            },
				ondestroy : function(action) {
				}
			});
		}  else {
			mini.alert("请选择一条要查看的数据!");
		}
	    }      
	    
    
  
</script>
  </body>
</html>