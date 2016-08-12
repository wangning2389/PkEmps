package module.meeting.action;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import module.common.services.LogServices;
import module.meeting.services.MeetingServices;
import module.user.services.UserServices;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import sun.net.www.content.text.plain;

import com.Component;
import com.PublicSystem;
import com.Util;
import com.sun.org.apache.bcel.internal.generic.NEW;

import core.exception.BusinessException;
import flexjson.factories.ArrayObjectFactory;

@Controller
@RequestMapping("/module/meeting.do")
public class MeetingAction {

	MeetingServices ms = new MeetingServices();
	PublicSystem sys = PublicSystem.getInstance();
	LogServices logservices = new LogServices();

	/**
	 * jquery mini ui 统一grid列表
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "method=getMeetingList")
	public void getMeetingList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 查询条件
		String theme = Component.getQuest("theme", request);
		String time = Component.getQuest("time", request);
		String savestatus = Component.getQuest("savestatus", request);
		HashMap parms = sys.getGridSort(request);
		String json = Util.Encode(ms.getMeetingList(
				new Object[] { theme, time,savestatus }, parms));
		Component.print(json, response);
	}

	// 抽取专家
	@RequestMapping(params = "method=chooseMeeting")
	public void chooseMeeting(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 查询条件
		String data = Component.getQuest("data", request);
		String grid = Component.getQuest("grid", request);
		String teamname=Component.getQuest("teamname", request);
		ArrayList meetInfoList = (ArrayList) Util.Decode(data);
		HashMap majorgrid = (HashMap) Util.Decode(grid);// 专业组信息
		HashMap teamnameMap = (HashMap)Util.Decode(teamname);
		HashMap json=new HashMap();
		HashMap meetInfo = (HashMap) meetInfoList.get(0);
		JSONArray jsonarr = new JSONArray();
		ArrayList teamcountIndfo =new ArrayList();
		json = (HashMap) ms.chooseTeam(teamnameMap,majorgrid);
		for(Object keyset :  json.keySet()){
			JSONObject team = new JSONObject();
			 JSONArray personArray = new JSONArray();
			 HashMap teamMap = (HashMap) json.get(keyset);//获取组
			 for(int k=0;k<teamMap.size();k++){
				 
				 HashMap teammajorMap=(HashMap)teamMap.get(k);
			
		        for(int i=0;i<teammajorMap.size();i++){
		        	HashMap personValMap =(HashMap)teammajorMap.get(i);
		            JSONObject person = new JSONObject();
		            person.put("id",personValMap.get("id"));
		            person.put("name",personValMap.get("name"));
		            person.put("major",personValMap.get("major"));
		            person.put("mobile",personValMap.get("mobile"));
		            personArray.put(person);
		        }
		        
				
			 }
			 team.put("person", personArray);
			 team.put("teamname", keyset);
			 jsonarr.put(team);  
		 }
		
		
		
/*		if ("1".equals(meetInfo.get("majorOrnot"))) {// 按专业分组
			HashMap p = new HashMap();
			for (int i = 0; i < majorgrid.size(); i++) {
				HashMap pi = new HashMap();
				pi = (HashMap) majorgrid.get(i);
				if ("".equals(pi.get("major")) || (pi.get("major") == null)) {

					throw new BusinessException("专业组输入专业不可为空！");
				}
				p.put(i, pi);
			}
		 json = (HashMap) ms.queryTeam(p, meetInfo,meetInfo.get("majorOrnot"));
		 for(Object keyset :  json.keySet()){
			 HashMap majorMap = (HashMap) json.get(keyset);
			 JSONObject major = new JSONObject();
			 JSONArray personArray = new JSONArray();
		        for(int i=0;i<majorMap.size();i++){
		        	HashMap personValMap =(HashMap)majorMap.get(i);
		            JSONObject person = new JSONObject();
		            person.put("id",personValMap.get("id"));
		            person.put("name",personValMap.get("name"));
		            person.put("major",personValMap.get("major"));
		            person.put("mobile",personValMap.get("mobile"));
		            person.put("majorOrnot","1");
		            personArray.put(person);
		        }
		        major.put("major", keyset);
		        major.put("person", personArray);
		        jsonarr.put(major);
		        
		 }
		 
		} else {// 不分专业
			json = ms.queryTeam(null,meetInfo, meetInfo.get("majorOrnot"));
	        for(int i=0;i<json.size();i++){
	        	HashMap p=(HashMap)json.get(i);
	            JSONObject jo = new JSONObject();
	            jo.put("id",p.get("id"));
	            jo.put("name",p.get("name"));
	            jo.put("major",p.get("major"));
	            jo.put("mobile",p.get("mobile"));
	            jo.put("majorOrnot","0");
//	            jo.put("team",row.get("team"));
//	            jo.put("personnum",row.get("person"));
	            jsonarr.put(jo);
	        }
					 
		 

		}*/

		Component.print(jsonarr.toString(), response);
	}
	
	
	@RequestMapping(params = "method=DownloadExpList")
	public void DownloadExpList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String meetingid = Component.getQuest("id", request);
		System.out.println(meetingid);
		HashMap meetdata = ms.MeetInfo(meetingid);//评审会信息
		List teamdata = ms.teamleaderInfo(meetingid);//组长信息
		List<Map> data = new ArrayList<Map>();//数据重组
		String teamleader = "";
		String expid = "";
		HashMap end = new HashMap();//end作用：换行显示起分组作用
		end.put("name", "");
		end.put("mobile", "");
		end.put("id", "");
		end.put("teamleader", "");
		for (int i = 0; i < teamdata.size(); i++) {
			HashMap team = (HashMap) teamdata.get(i);
			team.put("teamleader", "组长");
			teamleader = team.get("id").toString();
			data.add(team);//组长信息放第一个
			List<Map> exp = ms.teamexpInfo(meetingid, teamleader);	
			for (int j = 0; j < exp.size(); j++) {
				expid = exp.get(j).get("id").toString();
				if (!teamleader.equals(expid)) {
					exp.get(j).put("teamleader", "");
					data.add(exp.get(j));
				}
			}
			data.add(end);
		}
		WritableWorkbook book = null;
		String fileName = "评审会名单.xls";
		OutputStream os = null;
		try {
			response.setContentType("application/vnd.ms-excel");//定义输出类型
			response.setHeader("Content-Disposition", "attachment:filename="+fileName);
			os = response.getOutputStream();		
			book = Workbook.createWorkbook(os);
			//创建工作表
			WritableSheet sheet = book.createSheet("sheet1", 0);
			
//			//设置单元格字体
//			WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);  
//			WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD); 
//
//			//设置单元格样式
//			// 用于标题居中  
//			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);  
//			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条  
//			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐  
//			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐  
//			wcf_center.setWrap(false); // 文字是否换行

	
	//

			
			//EXCEL开头大标题 
			//sheet.mergeCells(0, 0, colWidth, 0); 
			addCell(0, 0, "主题："+meetdata.get("theme").toString(), sheet);
			addCell(1, 0, "主任："+meetdata.get("chairman").toString(), sheet);
			addCell(2, 0, "副主任："+meetdata.get("vicechairman").toString(), sheet);
			addCell(3, 0, "时间："+meetdata.get("time").toString(), sheet);
			
			//第一行列标题
			String[] title = {"专家姓名","专家电话","是否是组长"};
			for (int i = 0; i < title.length; i++) {  
				addCell(i, 1, title[i], sheet);  
			}
			//正文数据
			for (int i = 0; i < data.size(); i++) {
				HashMap d = (HashMap) data.get(i);
				addCell(0, i+2, d.get("name").toString(), sheet);
				addCell(1, i+2, d.get("mobile").toString(), sheet);
				addCell(2, i+2, d.get("teamleader").toString(), sheet);
			}
	
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(book != null){
				try {
					book.write();
					book.close();
				} catch (WriteException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(os != null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}	
	}
	private void addCell(int x, int y, String value, WritableSheet sheet) throws RowsExceededException, WriteException{
		jxl.write.Label lbl = new jxl.write.Label(x, y, value);
		sheet.addCell(lbl);
	}

		 
	 
	 @RequestMapping(params = "method=saveMeeting")
	public void saveMeeting(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String data = Component.getQuest("data", request);
		String grid = Component.getQuest("grid", request);
//		String majorOrnot =Component.getQuest("majorOrnot", request);
		String savestatus=Component.getQuest("savestatus", request);
		String idreturn="";
	    String operator = (String) request.getSession().getAttribute("userid");
		ArrayList rows = (ArrayList) Util.Decode(data);
		HashMap majorgrid = (HashMap) Util.Decode(grid);// 专业组信息
		HashMap row = (HashMap) rows.get(0);
		if(!StringUtils.isNotBlank((String)row.get("id"))){
		idreturn=ms.insertMeetingInfo(row, majorgrid,savestatus,operator);
		}else{
			idreturn=ms.updateMeetingInfo(row, majorgrid,savestatus,operator);
			HashMap log = new HashMap();
		    log.put("module", "2");
		    log.put("type", "2");
	  		log.put("content", "修改了ID为"+(String)row.get("id")+"的评审会信息");
	  		log.put("operator", operator);
			logservices.addLog(log);
		}
		
		
		Component.print(idreturn, response);
	}
	
	
	 @RequestMapping(params ="method=deleteMeeting")
     public void deleteMeeting(HttpServletRequest request, HttpServletResponse response) throws Exception{
    	 String ids =Component.getQuest("ids", request);
         if (StringUtils.isBlank(ids)) {
             throw new BusinessException("获取ID失败！");
         }
         List<String> idsArr = Arrays.asList(StringUtils.split(ids, ","));
         List<Integer> idList = new ArrayList<Integer>();
         for (String idstr : idsArr) {
             if (StringUtils.isNotBlank(idstr)) {
                 idList.add(new Integer(idstr));
             }
         }
         ms.deleteMeeting(idList);
         String idreturn="";
         HashMap log = new HashMap();
  	    log.put("module", "2");
  	    log.put("type", "3");
    	log.put("content", "删除了ID为"+ids+"的评审会信息");
    	log.put("operator", request.getSession().getAttribute("userid"));
  		logservices.addLog(log);
         Component.print(idreturn, response);
    }
	 
	 @RequestMapping(params ="method=queryMeetingInfo")
     public void queryMeetingInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		 int id=Integer.parseInt(Component.getQuest("id", request));
		 HashMap meeting=ms.getMeetingById(id);
		 List teamInfo=ms.getTeamInfo(id);
		 String json1=Util.Encode(meeting);
		 String json2=Util.Encode(teamInfo);
		 JSONObject jsonObject = new JSONObject();  
	        jsonObject.put("meeting", json1);
	        jsonObject.put("team", json2);
	        String json = jsonObject.toString();
		 Component.print(json, response);
		 
	 }
	 
}
