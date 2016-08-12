package core.action.system;


import java.util.ArrayList;




import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



import com.*;

import core.services.system.SystemServices;

@Controller
@RequestMapping("system.do")
public class SystemAction {
	
	SystemServices ss=new SystemServices();
	PublicSystem sys=PublicSystem.getInstance();
	
	/**
	 * jquery mini ui 统一grid列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "method=getmenu")
    public void getMenu(HttpServletRequest request, HttpServletResponse response) throws Exception
    {

		//查询条件(可以是多个)
		
		String key=Component.getQuest("key", request);
		String key1=Component.getQuest("key1", request);
		HashMap parms=sys.getGridSort(request);
        String json = Util.Encode(ss.getMenu(new Object[] {key,key1},parms));
        Component.print(json, response);
    }
	
	/**
	 * 删除数据
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "method=removemenu")
    public void removeMenu(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        String idStr = Component.getQuest("id", request);
        ss.removeMenu(idStr);
            
    }
	
	/**
	 * GET菜单明细数据
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "method=getmenudata")
    public void getMenuData(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        HashMap user = ss.getMenuData(Component.getQuest("id", request));
        Component.print(Util.Encode(user), response);
    }
	
	/**
	 * GET下拉列表狂数据
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "method=getselect")
    public void getSelect(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        List data = ss.getSelect(Component.getQuest("lx", request));
        Component.print(Util.Encode(data), response);
    }
	
	/**
	 * 新增和修改
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "method=savemenu")
    public void saveMenu(HttpServletRequest request, HttpServletResponse response) throws Exception
    {	
			String json=request.getParameter("data");
			String pid=request.getParameter("pid");

			//json转list再转map
        	ArrayList rows = (ArrayList)Util.Decode(json);
        	HashMap row = (HashMap)rows.get(0);
        	//取唯一标识号
    		String id = row.get("id") != null ? row.get("id").toString() : "";
    		
    		
            if(id.equals(""))//新增：id为空
            {
            	 row.put("pid", pid);
                 ss.insertMenu(row);
               
            }
            else 	//更新：id不为空
            {
            	 System.out.println(row.get("pid"));
            	 System.out.println(pid);
            	 if("".equals(pid)||null==pid){}
            	 else row.put("pid", pid);
            	
            	 ss.modifiedMenu(row);
            }
     }
	
	@RequestMapping(params = "method=createSrc")
    public void createSrc(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        	ArrayList rows = (ArrayList)Util.Decode(request.getParameter("data"));
        	HashMap row = (HashMap)rows.get(0);
        	Component.print(ss.createSrc(row),response);    	
     }
	
	
	
	
    
}
