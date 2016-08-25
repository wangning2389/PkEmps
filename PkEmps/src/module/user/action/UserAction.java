package module.user.action;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import module.common.services.LogServices;
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
@RequestMapping("/module/user.do")
public class UserAction
{
    
    UserServices us = new UserServices();
    
    PublicSystem sys = PublicSystem.getInstance();
    
    LogServices logservices = new LogServices();
    
    // 跳转到主页
    @RequestMapping(params = "method=toFrame")
    public String toFrame(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        
        return "frames/main";
    }
    
    /**
     * jquery mini ui 统一grid列表
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(params = "method=getExpUserList")
    public void getExpUserList(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        
        // 查询条件
        String name = null;
        name = Component.getQuest("name", request);
        String major = Component.getQuest("major", request);
        String subject = Component.getQuest("subject", request);
        String filed = Component.getQuest("filed", request);
        String verifystatus = Component.getQuest("verifystatus", request);
        String subtime = Component.getQuest("subtime", request);
        String status = Component.getQuest("status", request);
        String profgrade = Component.getQuest("profgrade", request);
        String exptype = Component.getQuest("exptype", request);
        HashMap parms = sys.getGridSort(request);
        String json = Util.Encode(us.getExpUserList(new Object[] {name, major, subject, filed, verifystatus, subtime,
            status, profgrade}, exptype, parms));
        Component.print(json, response);
    }
    
    // //跳转至新增页面
    // @RequestMapping(params = "method=toAddExpUser")
    // public String toAddExpUser(HttpServletRequest request, HttpServletResponse response) throws Exception
    // {
    // return "user/addExpUser";
    // }
    
    // 修改页面
    @RequestMapping(params = "method=UpdateExpUser")
    public void UpdateExpUser(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        
        int id = Integer.parseInt(Component.getQuest("id", request));
        HashMap ExpUser = us.getExpUserData(id);
        Component.print(Util.Encode(ExpUser), response);
    }
    
    // 获取字典
    @RequestMapping(params = "method=getDic")
    public void getDic(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String type = Component.getQuest("type", request);
        List<String> s = Arrays.asList(StringUtils.split(type, ","));
        List dic = us.getDic(s);
        
        JSONArray json = new JSONArray();
        for (int i = 0; i < dic.size(); i++)
        {
            HashMap p = (HashMap)dic.get(i);
            JSONObject jo = new JSONObject();
            jo.put("id", p.get("id"));
            jo.put("type", p.get("type"));
            jo.put("code", p.get("code"));
            jo.put("text", p.get("text"));
            jo.put("parent_code", p.get("parent_code"));
            json.put(jo);
        }
        
        Component.print(json.toString(), response);
    }
    
    /**
     * 获取下拉列表字典数据
     * 
     * @author wh
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(params = "method=getselect")
    public void getSelect(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        List data = us.getSelect(Component.getQuest("type", request));
        Component.print(Util.Encode(data), response);
    }
    
    /**
     * 根据证件类型和号码姓名校验专家唯一性
     * 
     * @author wh
     * @param request
     * @param response
     *@throws Exception
     */
    @RequestMapping(params = "method=checkId")
    public void checkId(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        
        String idtype = Component.getQuest("idtype", request);
        String idcode = Component.getQuest("idcode", request);
        String name = Component.getQuest("name", request);
        HashMap ExpUser = us.checkId(idtype, idcode, name);
        JSONObject mes = new JSONObject();
        if (ExpUser == null)
        {
            mes.put("msg", "success");
            
        }
        else
        {
            mes.put("msg", "failed");
        }
        Component.print(mes.toString(), response);
    }
    
    /**
     * 新增和更新专家信息
     * 
     * @param request
     * @param response
     *@throws Exception
     */
    @RequestMapping(params = "method=saveExpInfo")
    public void saveExpInfo(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        
        String json = request.getParameter("data");
        String idreturn = "";
        ArrayList rows = (ArrayList)Util.Decode(json);
        HashMap row = (HashMap)rows.get(0);
        HashMap row1 = (HashMap)rows.get(1);
        row.putAll(row1);
        String imgname = (String)rows.get(2);
        if (StringUtils.isNotBlank(imgname))
        {
            String path = "upload/" + imgname;
            row.put("photo", path);
        }
        
        String operator = (String)request.getSession().getAttribute("userid");
        row.put("operator", operator);
        System.out.println(operator);
        HashMap log = new HashMap();
        log.put("module", "1");
        
        String id = row.get("id") != null ? row.get("id").toString() : "";
        // 页面上如果主键为空的话为新增
        if (id.equals(""))
        {
            idreturn = us.insertExpInfo(row);
        }
        // 页面上如果主键不为为空的话为更新
        else
        {
            us.modifiedExpInfo(row);
            log.put("type", "2");
            log.put("content", "修改了ID为" + id + "的专家信息");
            log.put("operator", operator);
            logservices.addLog(log);
        }
        Component.print(idreturn, response);
    }
    
    // 注销用户
    @RequestMapping(params = "method=deleteExpUser")
    public void deleteExpUser(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String id = Component.getQuest("id", request);
        String reason = Component.getQuest("data", request);
        if (StringUtils.isBlank(id))
        {
            throw new BusinessException("获取ID失败！");
        }
        int id1 = Integer.parseInt(id);
        
        us.deleteExpUser(id1, reason);
        String idreturn = "";
        HashMap log = new HashMap();
        log.put("module", "1");
        log.put("type", "3");
        log.put("content", "删除了ID为" + id + "的专家信息");
        log.put("operator", request.getSession().getAttribute("userid"));
        logservices.addLog(log);
        Component.print(idreturn, response);
    }
    
    // 加载菜单列表
    @RequestMapping(params = "method=getMenu")
    public void getMenu(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String username = (String)request.getSession().getAttribute("userid");
        List data = us.getMenu(username);
        Component.print(Util.Encode(data), response);
    }
    
    // 确认专家信息
    @RequestMapping(params = "method=verifyExpInfo")
    public void verifyExpInfo(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String id1 = Component.getQuest("id", request);
        String verifystatus = Component.getQuest("verifystatus", request);
        int id = Integer.parseInt(id1);
        us.verifyExpInfo(id, verifystatus);
        
        String idreturn = "";
        HashMap log = new HashMap();
        log.put("module", "1");
        log.put("type", "4");
        log.put("content", "审核了ID为" + id1 + "的专家信息");
        log.put("operator", request.getSession().getAttribute("userid"));
        logservices.addLog(log);
        Component.print(idreturn, response);
    }
    
    // 获取评审会历史记录
    @RequestMapping(params = "method=getMeetingHistory")
    public void getMeetingHistory(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        int id = Integer.parseInt(Component.getQuest("id", request));
        System.out.println(id);
        Component.print(Util.Encode(us.getMeetingHistory(id)), response);
    }
}
