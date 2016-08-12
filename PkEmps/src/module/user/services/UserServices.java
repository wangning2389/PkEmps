package module.user.services;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import module.common.services.LogServices;
import module.user.vo.Exp_User;

import com.PublicSystem;
import com.Util;

import core.utils.BeanTools;

public class UserServices {

	PublicSystem sys = PublicSystem.getInstance();
	LogServices logservices = new LogServices();

	// 取菜单列表
	public HashMap getExpUserList(Object[] obj, String exptype, HashMap parms) throws Exception {
		StringBuilder sb=new StringBuilder();
		sb.append("1=1");
		if(StringUtils.isNotBlank(exptype)){
		List<String> list = Arrays.asList(StringUtils.split(exptype, ","));
		for(int i=0;i<list.size();i++ ){ 
			if(i==0){
			sb.append(" and exptype like '%"+list.get(i)+"%' ");
			}else{
				sb.append("  or exptype like '%"+list.get(i)+"%' ");
			}
			
		}
		
		}
		// 设置sql语句
		String sql = "select * from EXP_USER " + "where ('" + obj[0]
				+ "' is null or name like '%'||?||'%') and ('" + obj[1]
				+ "' is null or major like ''||?||'%')" + "and ('" + obj[2]
				+ "' is null or subject like ''||?||'%')and ('" + obj[3]
				+ "' is null or filed like ''||?||'%')and ('" + obj[4]
				+ "' is null or verifystatus = ?)and ('" + obj[5]
				+ "' is null or to_char(subtime,'yyyy-mm-dd')= ?) and ('" + obj[6]
				+ "' is null or status = ?) and ('" + obj[7]
				+ "' is null or profgrade = ?) and("+sb.toString()+") order by id desc";
		System.out.println(sql);
		// 调用统一表单方法并返回map
		return sys.getGrid(sql, obj, parms, true);
	}

	/**
	 * 新增个人基础信息
	 * 
	 * @author wh
	 * @param obj
	 * @throws Exception
	 */
	public String insertExpInfo(HashMap obj) throws Exception {

		int id = sys.getId("SEQ_EXP_USER_ID");
		Exp_User vo = new Exp_User();
		obj.put("id", id);
	//	obj.put("aac999", sys.getIdMum(aac999, 10));
		vo = (Exp_User) Util.MapToVo(vo, obj);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		String date=sdf.format(new Date());
		vo.setSUBTIME((sdf.parse(date)));
//		vo.setPHOTO("asa");
		vo.setVERIFYSTATUS("0");
		vo.setSOURCE("1");
		vo.setSTATUS("1");
	
		
		sys.Jdbc_saveDao(vo);
		
		
		HashMap log = new HashMap();
	    log.put("module", "1");
	    log.put("type", "1");
  		log.put("content", "新增了ID为"+id+"的专家信息");
  		log.put("operator", obj.get("operator"));
		logservices.addLog(log);
		//return sys.getIdMum(aac999, 10);
		return "0";
	}

	/**
	 * 更新个人基础信息
	 * 
	 * @author wh
	 * @param obj
	 * @throws Exception
	 */
	public String modifiedExpInfo(HashMap obj) throws Exception {

		Exp_User vo = new Exp_User();
		vo = (Exp_User) Util.MapToVo(vo, obj);
		Exp_User bo = new Exp_User();
		HashMap p=new HashMap();
		p=this.getExpUserData(vo.getID());
		bo=(Exp_User) Util.MapToVo(bo, p);
        BeanTools.copyPropertiesIgnoreNull(vo, bo, true);
        if("2".equals(bo.getVERIFYSTATUS()))
        {
        	bo.setVERIFYSTATUS("0");
        }
		
        sys.Jdbc_modifyDao(bo, new Object[] { "id:A" });
		return "";
	}

	public List getSelect(String type) throws Exception {
		String sql = "SELECT * FROM c_dic where type=?";
		List data = sys.query(sql, new Object[] { type }, true);
		return data;
	}
	
	public List getDic(List s)throws Exception {
		String sql = "SELECT * FROM c_dic where type in(?,?,?,?)";
		List  data = sys.query(sql, new Object[] { s.get(0), s.get(1), s.get(2),s.get(3) }, true);
		
		return data;
	}

	public HashMap checkId(String idtype, String idcode, String name)
			throws Exception {
		String sql = "select * from EXP_USER where idtype = ? and idcode = ? and name= ?";
		List data = sys.query(sql, new Object[] { idtype, idcode, name }, true);
		return data.size() > 0 ? (HashMap) data.get(0) : null;
	}
	
	  /**
     * 根据id获取个人基础信息
     * @param id
     * @throws Exception
     */
    public HashMap getExpUserData (int id) throws Exception{
    
    	Exp_User vo=new Exp_User();
        String sql = "select * from Exp_User where id = ?";
        List data = sys.query(sql, new Object[] {id} , true);
        return data.size() > 0 ? (HashMap)data.get(0) : null;
    }

	public void deleteExpUser(int id, String reason) throws Exception {
		// TODO Auto-generated method stub
			Exp_User bo = new Exp_User();
			HashMap p=new HashMap();
			p=this.getExpUserData(id);
			bo=(Exp_User) Util.MapToVo(bo, p);
			bo.setSTATUS("0");
			bo.setDELETEREASON(reason);
			sys.Jdbc_modifyDao(bo, new Object[] { "id:A" });
		
	} 
	//获取菜单列表
	public List getMenu() throws Exception{
		String sql = "select * from C_MENU ";
		List data = sys.query(sql, null, true);
		return  data;
	}

	public void verifyExpInfo(int id, String verifystatus) throws Exception {
		// TODO Auto-generated method stub
		Exp_User bo = new Exp_User();
		HashMap p=new HashMap();
		p=this.getExpUserData(id);
		bo=(Exp_User) Util.MapToVo(bo, p);
		bo.setVERIFYSTATUS(verifystatus);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		String date=sdf.format(new Date());
		bo.setVERIFYTIME((sdf.parse(date)));
		sys.Jdbc_modifyDao(bo, new Object[] { "id:A" });
	}

	public List getMeetingHistory(int id) throws Exception{
		String sql = "select m.theme as theme,m.time as time from team_user u,team t,meeting m,exp_user e " +
				"where e.id = ? and e.id = u.expuser_id and u.team_id=t.id and t.meeting_id=m.id and e.status='1' ";
		List data = sys.query(sql, new Object[] {id}, true);
		return data;
	}
	
}
