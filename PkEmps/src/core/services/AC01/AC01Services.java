package core.services.AC01;

import java.util.*;

import com.*;

import core.vo.system.AC01;
   /**
    * ac01 
    * Wed Dec 23 16:16:39 CST 2015 gxf
    */ 
    public class AC01Services{

    PublicSystem sys=PublicSystem.getInstance();

    /**
     * 查询个人基础信息
     * @author gxf 
     * @param obj
     * @param parms
     * @throws Exception
     */
    public HashMap getAc01(Object[] obj,HashMap parms) throws Exception{

        String  sql = "select * from v_ac01 where ('"+obj[0]+"' is null or aac999=?) and " +
                "('"+obj[1]+"' is null or aac058_id=?) and " +
                "('"+obj[2]+"' is null or aac147 like '%'||?||'%') and " +
                "('"+obj[3]+"' is null or aac003 like '%'||?||'%')";
    
         return  sys.getGrid(sql, obj, parms, true);
    } 

    /**
     * 根据id获取个人基础信息
     * @author gxf 
     * @param id
     * @throws Exception
     */
    public HashMap getAC01Data (String id) throws Exception{

        AC01 vo=new AC01();
        String sql = "select * from ac01 where AAC999 = ?";
        List data = sys.query(sql, new Object[] {id} , true);
        return data.size() > 0 ? (HashMap)data.get(0) : null;
    } 

    /**
     * 新增个人基础信息
     * @author gxf 
     * @param obj
     * @throws Exception
     */
    public String insertAC01(HashMap obj) throws Exception{

         int aac999 = sys.getId("tbr");
         AC01 vo=new AC01();
         obj.put("aac001",aac999);
         obj.put("aac999", sys.getIdMum(aac999, 10));
         vo=(AC01)Util.MapToVo(vo, obj);
         sys.Jdbc_saveDao(vo);
         return sys.getIdMum(aac999, 10);
    } 

    /**
     * 更新个人基础信息
     * @author gxf 
     * @param obj
     * @throws Exception
     */
    public String modifiedAC01(HashMap obj) throws Exception{

        AC01 vo=new AC01();
        obj.put("aac001",sys.getId("tbr"));
         vo=(AC01)Util.MapToVo(vo, obj);
         sys.Jdbc_modifyDao(vo,new Object[]{"AAC999:A"});
         return "";
    } 

    /**
     * 根据aac058和aac147查询个人基础信息
     * @author gxf 
     * @param aac058
     * @param aac147
     * @throws Exception
     */
    public HashMap getAAC147 (String aac058,String aac147) throws Exception{
        String sql = "select * from ac01 where aac058 = ? and aac147 = ?";
        List data = sys.query(sql, new Object[] {aac058, aac147}, true);
        return data.size() > 0 ? (HashMap)data.get(0) : null;
    } 

    /**
     * 下拉框数据取得
     * @author gxf 
     * @param lx
     * @throws Exception
     */
     public List getSelect(String lx) throws Exception
     {
         String sql = "SELECT bm,mch FROM zyk_cs where lx=?";
         List data = sys.query(sql, new Object[] {lx}, true);
         return data;
      }
}

