<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" 
    pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>评审会修改</title>
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
        评审会信息
      </legend>
      <form id="form1" method="post">
        <input name="id" class="mini-hidden"/>
        <div style="padding-left: 11px; padding-bottom: 5px;">
          <table style="padding-left: 17px; padding-bottom: 5px;">
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
                <input name="time" class="mini-datepicker"  required="true" allowIput="false"
                  id="time" align="right"
                  style="width: 185px;" />
              </td>
     		 <td  align="right" nowrap="nowrap">
          		  &nbsp&nbsp&nbsp&nbsp  评审会主任：
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
                <input name="vicechairman" class="mini-buttonedit" id="vicechairman" onbuttonclick="onButtonEdit1" allowInput="true"
                  style="width: 185px;" align="left"/>
              </td>
               <td align="right" nowrap="nowrap">
              保存状态：
              </td>
              <td >
               <input name="savestatus" class="mini-combobox" valueField="code" readonly="readonly"
							textField="text" id="savestatus" url="${pageContext.request.contextPath }/module/user.do?method=getselect&type=savestatus"
							style="width: 185px;" />
              </td>
    
            </tr>
            
          </table>  
        </div>
      </form>
      

  </fieldset>
  
  <fieldset
      style="border: solid 1px #aaa; padding: 3px; " id="teamtitle">
      <legend>
        专家组信息
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
//表格绑定
        var form1 = new mini.Form("form1");
        var team=null;

   	 //标准方法接口定义
        function SetData(data) {
            var dic="1";
            //获取后台字典
             $.ajax({
                 url: "${pageContext.request.contextPath}/module/user.do?method=getDic&type=major,subject,filed,livingadd",
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
                      if (data.action == "edit") {
                          //跨页面传递的数据对象，克隆后才可以安全使用
                          data = mini.clone(data);
                          $.ajax({
                                 url: "${pageContext.request.contextPath}/module/meeting.do?method=queryMeetingInfo&id=" + data.id,
                                 cache: false,
                                 success: function (text) {
                                     var o = mini.decode(text);
                                     var meeting=mini.decode(o.meeting);
                                     if(meeting.chairman){
                                    	 setChairman(meeting.chairman,0);
                                    }
                                     if(meeting.vicechairman){
                                    	 setChairman(meeting.vicechairman,1);
                                    }
                                      team=mini.decode(o.team);
                                     form1.setData(meeting);
                   	                form1.setChanged(false);
                   	                var i=0;
                   	                var lastP;
                   	                $.each(team, function(k, v){
                       	            /*    if(!lastP){
                       	                	lastP =  v.profession;
                       	                }else{
                           	                if(lastP != v.profession){
                               	                i = 0;   
                               	            }
                           	             lastP = v.profession;
                       	                }*/
                       	                var grid = mini.get("teamdatagrid_" + v.teamid );
                       	                if(!grid){
                           	                i++;
                       	                	creatTeamGrid("_" + v.teamid ,v.teamname,i);
                       	                	grid = mini.get("teamdatagrid_" + v.teamid );
                       	                }
                       	              	grid.addRow(v, grid.data.length - 1);
                       	            })
                                 }
                                 });
                     }                   
                     }
                 });
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

  function creatTeamGrid(id,teamname,i){

        $("#teamshow").append("<div id=teamshow1> </div>");
        $("#teamshow1").append(
            "<div style='width:800px;'><div class='mini-toolbar' style='border-bottom:0;padding:0px;'><table style='width:100%;'><tr><td style='width:100%;'>"+
                 "<a class='mini-button' iconCls='icon-add' onclick='add(\""+id+"\")'>增加</a><a class='mini-button' iconCls='icon-remove' onclick='remove(\""+id+"\")'>删除</a><a class='mini-button' iconCls='icon-edit' onclick='check(\""+id+"\")'>查看</a><a class='mini-button'  onclick='setleader(\""+id+"\")'>设为组长</a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+
                 "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<a><b>组名："+teamname+"</b></a></td></tr></table></div></div>"+
            "<div id='teamdatagrid"+id+"' class='mini-datagrid' showPager='false' style='width:800px;height:280px;' allowResize='true'idField='id' multiSelect='true' >"+
                "<div property='columns'><div type='checkcolumn' ></div>  <div type='indexcolumn'></div><div field='name' width='120' headerAlign='center' allowSort='false' >专家姓名</div> "+   
                    " <div field='mobile' width='120' headerAlign='center' allowSort='false'>电话</div> <div field='major' width='120' headerAlign='center' renderer='trmajor' allowSort='false'>专业</div><div  field='teamleader'  width='60' headerAlign='center' renderer='trleader'>是否为组长</div> </div></div>"
              );
        
        mini.parse();
  }



  function submit(){
		var savestatus=1;
		SaveData(savestatus);
	  }
  function willsubmit(){
		var savestatus=2;
		SaveData(savestatus);
	  }


  function remove(e) {
	    var grid =mini.get("teamdatagrid"+e);  
	    var rows = grid.getSelecteds();
	        if (rows.length > 0) {
	            grid.removeRows(rows, true);
	        }
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
	                     console.log(data);
	                     var valid=true;
	                     if (data) {
		                     //专家重复性校验
	                    	 $.each(team, function(k, v){
	                    		 var grids = mini.get("teamdatagrid_" + v.teamid );
	                    		 var griddata=grids.getChanges();
	                    		 for(var i=0;i<griddata.length;i++){
	                    			 if(griddata[i].id==data.id){
											valid=false;
												return false;
											}
		                    		 }
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

       /* function queryChairman(id,lb){
            if(lb==0){
        	var btnEdit=mini.get("chairman");
            }else{
            	var btnEdit=mini.get("vicechairman");
                }
        	 $.ajax({
                 url: "${pageContext.request.contextPath}/module/user.do?method=UpdateExpUser&id="+id,
                 cache: false,
                 success: function (text) {
                     var o = mini.decode(text);
                     console.log(o);
                     if (o){
                     btnEdit.setValue(o.id);
                     btnEdit.setText(o.name);
                     }
                 }
                 });
            }
			*/

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
                    	btnEdit.setValue(data.name);
                        btnEdit.setText(data.name);
                    }
                }

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
          var teamnameMap={};
		var j=0;
		var valid=true;	
          $.each(team, function(k, v){
        	  		var c=0;
	                var grids = mini.get("teamdatagrid_" + v.teamid );
	                var data=grids.getChanges();
	                ///////////////校验是否选组长
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
                  //////////////////  
                  //传入teamid
                   result[v.teamid]=data;
                   teamnameMap[v.teamid]=v.teamname;
	            })
	            if(!valid){
	            	  mini.alert("有未选择组长的小组，请完善！");
	            	  return;
	                  }
             
          var jsonGrid=JSON.stringify(result);
          var teamnameJson=JSON.stringify(teamnameMap);
          var json = mini.encode( [o]);
          $.ajax( {
            url : "${pageContext.request.contextPath}/module/meeting.do?method=saveMeeting",
            type : 'post',
            data : {
              data : json,
              grid:jsonGrid,
              teamnameJson:teamnameJson,
              savestatus:s
            },
            cache : false,
            success : function(text) {
            	mini.alert("评审会信息修改成功！", "提示", function() {
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
        function CloseWindow(action) {     
            window.CloseOwnerWindow(action);
       }

        function setChairman(name,lb){
       	 if(lb==0){
            	var btnEdit=mini.get("chairman");
                }else{
                	var btnEdit=mini.get("vicechairman");
                    }
       	 btnEdit.setValue(name);
            btnEdit.setText(name);
       	}
  
</script>
  </body>
</html>