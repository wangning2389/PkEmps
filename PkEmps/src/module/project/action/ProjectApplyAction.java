package module.project.action;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.*;
import module.project.services.ProjectApplyServices;

/**
 * ProjectApply Thu Aug 18 18:38:55 CST 2016 gjx
 */
@Controller
@RequestMapping("/ProjectApply.do")
public class ProjectApplyAction
{
    
    PublicSystem sys = PublicSystem.getInstance();
    
    ProjectApplyServices ss = new ProjectApplyServices();
    
    /**
     * jquery mini ui Grid
     * 
     * @param request
     * @param response
     *@throws Exception
     */
    @RequestMapping(params = "method=getProjectApply")
    public void getProjectApply(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        
        HashMap parms = sys.getGridSort(request);
        String json = Util.Encode(ss.getProjectApply(new Object[] {}, parms));
        Component.print(json, response);
    }
    
    @RequestMapping(params = "method=removeProjectApply")
    public void removeProjectApply(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        
        String idStr = Component.getQuest("id", request);
        ss.removeProjectApply(idStr);
    }
    
    @RequestMapping(params = "method=getProjectApplydata")
    public void getProjectApplyData(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        
        HashMap user = ss.getProjectApplyData(Component.getQuest("id", request));
        Component.print(Util.Encode(user), response);
    }
    
    @RequestMapping(params = "method=saveProjectApply")
    public void saveProjectApply(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        
        String json = request.getParameter("data");
        ArrayList rows = (ArrayList)Util.Decode(json);
        HashMap row = (HashMap)rows.get(0);
        String id = row.get("ID") != null ? row.get("ID").toString() : "";
        if (id.equals(""))
            ss.insertProjectApply(row);
        else
            ss.modifiedProjectApply(row);
    }
    
}
