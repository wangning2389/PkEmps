package module.project.services;

import java.util.*;
import com.*;
import module.project.vo.ProjectApply;
import java.util.Date;
   /**
    * ProjectApply 
    * Thu Aug 18 18:38:55 CST 2016 gjx
    */ 
    public class ProjectApplyServices{

    PublicSystem sys=PublicSystem.getInstance();

    public HashMap getProjectApply(Object[] obj,HashMap parms) throws Exception{

        String sql = "select * from ProjectApply ";
        return  sys.getGrid(sql, obj,parms,true);
    } 



    public void removeProjectApply(String idStr) throws Exception{

        ProjectApply vo=new ProjectApply();
        String[] ids = idStr.split(",");
        for (int i = 0, l = ids.length; i < l; i++){
        vo.setID(ids[i]);
        sys.Jdbc_deleteDao(vo,new Object[]{"ID:A"});
        }
    } 



    public HashMap getProjectApplyData (String id) throws Exception{

        ProjectApply vo=new ProjectApply();
        String sql = "select * from ProjectApply where ID = ?";
        List data = sys.query(sql, new Object[] {id},true);
        return data.size() > 0 ? (HashMap)data.get(0) : null;
    } 



    public String insertProjectApply(HashMap obj) throws Exception{

        ProjectApply vo=new ProjectApply();
         vo=(ProjectApply)Util.MapToVo(vo, obj);
         sys.Jdbc_saveDao(vo);
         return "";
    } 



    public String modifiedProjectApply(HashMap obj) throws Exception{

        ProjectApply vo=new ProjectApply();
         vo=(ProjectApply)Util.MapToVo(vo, obj);
         sys.Jdbc_modifyDao(vo,new Object[]{"ID:A"});
         return "";
    } 



}

