package core.action.AC01;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.*;

import core.services.AC01.AC01Services;

   /**
    * ac01 
    * Wed Dec 23 16:16:39 CST 2015 gxf
    */ 
    @Controller
    @RequestMapping("AC01.do")
    public class AC01Action{

    PublicSystem sys=PublicSystem.getInstance();
    AC01Services ss=new AC01Services();

   /**
    * 查询个人基础信息
    * @author gxf 
    * @param request
    * @param response
    *@throws Exception
    */ 
    @RequestMapping(params ="method=getAC01")
    public void getAc01(HttpServletRequest request, HttpServletResponse response) throws Exception{

        String AAC999=Component.getQuest("AAC999", request);
        String AAC058=Component.getQuest("AAC058", request);
        String AAC147=Component.getQuest("AAC147", request);
        String AAC003=Component.getQuest("AAC003", request);
        HashMap parms=sys.getGridSort(request);
        String json = Util.Encode(ss.getAc01(new Object[] {AAC999,AAC058,AAC147,AAC003},parms));
        Component.print(json, response);
    }

    /**
     * 获取选中数据的个人基础信息
     * @author gxf 
     * @param request
     * @param response
     *@throws Exception
     */
    @RequestMapping(params ="method=getAC01data")
    public void getAC01Data(HttpServletRequest request, HttpServletResponse response) throws Exception{

        HashMap user = ss.getAC01Data(Component.getQuest("id", request));
        Component.print(Util.Encode(user), response);
    } 

    /**
     * 新增和更新个人基础信息
     * @author gxf 
     * @param request
     * @param response
     *@throws Exception
     */
    @RequestMapping(params ="method=saveAC01")
    public void saveAC01(HttpServletRequest request, HttpServletResponse response) throws Exception{

       String json=request.getParameter("data");
       String idreturn="";
       ArrayList rows = (ArrayList)Util.Decode(json);
       HashMap row = (HashMap)rows.get(0);
       String id = row.get("aac999") != null ? row.get("aac999").toString() : "";
       // 页面上如果主键为空的话为新增
       if(id.equals("")) {
          idreturn=ss.insertAC01(row);
       }
       // 页面上如果主键不为为空的话为更新
       else {
          ss.modifiedAC01(row);
       }
       Component.print(idreturn, response);
    } 

    /**
     * 根据证件类型和号码查询个人基础信息数据
     * @author gxf 
     * @param request
     * @param response
     *@throws Exception
     */
    @RequestMapping(params ="method=getaac147")
    public void getAAC147(HttpServletRequest request, HttpServletResponse response) throws Exception{

        String aac058=Component.getQuest("aac058", request);
        String aac147=Component.getQuest("aac147", request);
        HashMap user = ss.getAAC147(aac058,aac147);
        Component.print(Util.Encode(user), response);
    } 

    /**
     * 获取下拉列表狂数据
     * @author gxf 
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

}

