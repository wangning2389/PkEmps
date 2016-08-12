package module.CountInfo.action;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import module.CountInfo.services.CountInfoServices;
import module.user.services.UserServices;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Component;
import com.PublicSystem;
import com.Util;
import com.wondersgroup.framework.core.exception.BusinessException;




@Controller
@RequestMapping("/module/count.do")
public class CountInfoAction {
	
	CountInfoServices cs=new CountInfoServices();
	PublicSystem sys=PublicSystem.getInstance();

	
    /**
     * 专家信息统计
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(params="method=ExpUserCount")
    public void ExpUserCount(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	String counttype = Component.getQuest("counttype", request);
    	System.out.println(counttype);
    	if (counttype != null && !"".equals(counttype)) {
			List data = cs.getExpCount(counttype);
			String name = "";
			JSONArray json = new JSONArray();
			int age = 0;
			if (counttype.equals("age")) {
				for(int i=0;i<data.size();i++){		        	
		        	HashMap p=(HashMap)data.get(i);
		        	age = Integer.parseInt(p.get("name").toString());		        	
		        	switch (age) {
					case 0:
						name = "0-9岁";
						break;
					case 1:
						name = "10-19岁";
						break;
					case 2:
						name = "20-29岁";
						break;
					case 3:
						name = "30-39岁";
						break;
					case 4:
						name = "40-49岁";
						break;
					case 5:
						name = "50-59岁";
						break;
					case 6:
						name = "60-69岁";
						break;
					case 7:
						name = "70-79岁";
						break;
					case 8:
						name = "80-89岁";
						break;
					case 9:
						name = "90-99岁";
						break;

					default:
						name = "其他";
						break;
					}
		        	Object o = new String (name);
		            JSONObject jo = new JSONObject();
		            jo.put("count",p.get("count"));
		            jo.put("name",o);	            
		            json.put(jo);
		        }
			}else{
				for(int i=0;i<data.size();i++){	        	
		        	HashMap p=(HashMap)data.get(i);
		            JSONObject jo = new JSONObject();
		            jo.put("count",p.get("count"));
		            jo.put("name",p.get("name"));	            
		            json.put(jo);
		        }
			}
	        	 
			 Component.print(json, response);
		}
    	
    }
    
    
    /**
     * 专家信息统计
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(params="method=MeetCount")
    public void MeetCount(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	String counttype = Component.getQuest("counttype", request);
    	System.out.println(counttype);
    	if (counttype != null && !"".equals(counttype)) {
			List data = cs.getMeetCount(counttype);
			String name = "";
			JSONArray json = new JSONArray();
			int age = 0;
			if (counttype.equals("age")) {
				for(int i=0;i<data.size();i++){		        	
		        	HashMap p=(HashMap)data.get(i);
		        	age = Integer.parseInt(p.get("name").toString());		        	
		        	switch (age) {
					case 0:
						name = "0-9岁";
						break;
					case 1:
						name = "10-19岁";
						break;
					case 2:
						name = "20-29岁";
						break;
					case 3:
						name = "30-39岁";
						break;
					case 4:
						name = "40-49岁";
						break;
					case 5:
						name = "50-59岁";
						break;
					case 6:
						name = "60-69岁";
						break;
					case 7:
						name = "70-79岁";
						break;
					case 8:
						name = "80-89岁";
						break;
					case 9:
						name = "90-99岁";
						break;

					default:
						name = "其他";
						break;
					}
		        	Object o = new String (name);
		            JSONObject jo = new JSONObject();
		            jo.put("count",p.get("count"));
		            jo.put("name",o);	            
		            json.put(jo);
		        }
			}else{
				for(int i=0;i<data.size();i++){	        	
		        	HashMap p=(HashMap)data.get(i);
		            JSONObject jo = new JSONObject();
		            jo.put("count",p.get("count"));
		            jo.put("name",p.get("name"));	            
		            json.put(jo);
		        }
			}
	        	 
			 Component.print(json, response);
		}
    	
    }
	
	
}
