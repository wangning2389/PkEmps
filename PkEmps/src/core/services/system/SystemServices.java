package core.services.system;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.*;

import core.vo.system.Oa_tymenu;

public class SystemServices {
	
	PublicSystem sys=PublicSystem.getInstance();
	
			//取菜单列表
		  public HashMap getMenu(Object[] obj,HashMap parms) throws Exception
		  {
			    //设置sql语句
		    	String sql = "select id,fname,name,flagname,logname,sort from v_meun " +
		    		"where ('"+obj[0]+"' is null or name like '%'||?||'%') and ('"+obj[1]+"' is null or id = ?)";
		    	//System.out.println(sql);
		    	//调用统一表单方法并返回map
		    	return  sys.getGrid(sql, obj,parms,true);
		  }
	
		  //删除数据
		  public void removeMenu(String idStr) throws Exception
		   {
			  //设置删除sql
			 // String sql="delete from oa_tymenu where sid=?"; 
			 // 循环删除数据
			    Oa_tymenu vo=new Oa_tymenu();
			  	String[] ids = idStr.split(",");
		        for (int i = 0, l = ids.length; i < l; i++)
		        {
		        	//调用公用删除方法
		        	//sys.RemoveData(sql, new Object[] {ids[i]});
		        	  vo.setID(Integer.parseInt(ids[i]));
					  sys.Jdbc_deleteDao(vo,new Object[]{"Id:A"});
		        }    
		        
		 
			   
			  	
		   }
		  //查看菜单明细数据
		  public HashMap getMenuData(String id) throws Exception
		    {
		    	String sql = "select * from oa_tymenu where id = ?";
		        //调用公用查询方法
		    	List data = sys.query(sql, new Object[] {id},true);
		        return data.size() > 0 ? (HashMap)data.get(0) : null;
		    }
		  //下拉列表参数
		  public List getSelect(String lx) throws Exception
		    {
			  	String sql = "SELECT bm,mch FROM oa_csb where lx=?";
			  	List data = sys.query(sql, new Object[] {lx},true);
		        return data;
		    }
		 
		  public String insertMenu(HashMap obj) throws Exception
		    {
				 
			  
			  	 int id=sys.getId("menu");
			     //json转换vo
	    		 Oa_tymenu vo = new Oa_tymenu();
	    		 obj.put("czsj",new Date());
				 obj.put("id",id);
				 if("".equals(obj.get("pid")))obj.put("pid", id);
				  vo=(Oa_tymenu)Util.MapToVo(vo, obj);
				  //事务开始
				  sys.BeginTransaction();
				  
				  //事务执行
				  	//--执行录入的dao事务方法
				  sys.Jdbc_saveDaoTransaction(vo);
				  	//--执行sql语句的事务方法
				  sys.updateDataTransaction("UPDATE oa_tymenu SET url='#',target='' WHERE id='"
						+ obj.get("pid") + "'");
				  
				  //事务结束
				  sys.endTransaction();
				  return "";
		    }
		  
		  public String modifiedMenu(HashMap obj) throws Exception
		    { 
			  Oa_tymenu vo=new Oa_tymenu();
			  vo=(Oa_tymenu)Util.MapToVo(vo, obj);
			  sys.Jdbc_modifyDao(vo,new Object[]{"id:A"});
			  
		      return "";
		    }
		  
		  public String  createSrc(HashMap map) throws Exception
		  {
			  String r="0";
			  String res=new EnterJdbcDao().createVo((String)map.get("tabname"),(String)map.get("pakeage"),(String)map.get("index"));
			 
			 
			  if("0".equals(res)) 
			  {
				  String res1=new EnterJdbcAction().createAction((String)map.get("tabname"),(String)map.get("pakeage"),(String)map.get("index"));
				  if("0".equals(res1)) 
				  {
					  String res2=new EnterJdbcServices().createServices((String)map.get("tabname"),(String)map.get("pakeage"),(String)map.get("index"));
					  if("0".equals(res2)) 
					  {
						  String res3=new EnterJdbcJsp().createJsp((String)map.get("tabname"),(String)map.get("pakeage"),(String)map.get("index"));
						  if(!"0".equals(res3))r=res3;
					  }
					  else r=res2;  
				  }
				  else r=res1;
			  }
			  else r=res;
			  
			  return r;
		  }
		 

//		  public void  saveMenu(HashMap map) throws Exception
//		  {
//			  Oa_tymenu vo=new Oa_tymenu();
//			  sys.Jdbc_saveDao(vo);
//			  
//		  }
//		  
//		  public void  modifyMenu(HashMap map) throws Exception
//		  {
//			  Oa_tymenu vo=new Oa_tymenu();
//			  sys.Jdbc_modifyDao(vo,new Object[]{"Sid:A"});
//			
//		  }
//		  
//		  public void  deleteMenu(HashMap map) throws Exception
//		  {
//			  Oa_tymenu vo=new Oa_tymenu();
//			  sys.Jdbc_deleteDao(vo,new Object[]{"Sid:A"});
//			
//		  }
		  
		  
		
		  
		
		  
}
