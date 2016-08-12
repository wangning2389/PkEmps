package module.CountInfo.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import module.user.vo.Exp_User;

import com.PublicSystem;
import com.Util;

import core.utils.BeanTools;

public class CountInfoServices {

	PublicSystem sys = PublicSystem.getInstance();
	
	public List getExpCount(String counttype) throws Exception{
		
		String sql = "";
		if ("livingadd".equals(counttype)) {
			sql = "select c.text as name,count(0) as count from exp_user e,c_dic c " +
					"where e.status='1' and c.code = e.livingadd and c.type='livingadd' and e.verifystatus='1' " +
					"group by c.text order by count(0) desc ";
		}
		if ("major".equals(counttype)) {
			sql = "select c.text as name,count(0) as count from exp_user e,c_dic c " +
					"where e.status='1' and e.verifystatus='1' and substr(e.major,1,2)=c.code and c.type='major' " +
					"group by c.text order by count(0) desc " ;
		}
		if ("subject".equals(counttype)) {
			sql = "select c.text as name,count(0) as count from exp_user e,c_dic c " +
			"where e.status='1' and e.verifystatus='1' and substr(e.subject,1,2)=c.code and c.type='subject' " +
			"group by c.text order by count(0) desc " ;
		}
		if ("sex".equals(counttype)) {
			sql = "select c.text as name,count(0) as count from exp_user e,c_dic c " +
					"where e.status = '1' and e.verifystatus='1' and c.code = e.sex and c.type='sex' " +
					"group by c.text order by count(0) desc ";
		}
		if ("age".equals(counttype)) {
			sql = "select trunc((to_char(sysdate,'yyyy')-to_char(birthday,'yyyy'))/10) as name,count(0) as count " +
					"from exp_user e where status='1' and e.verifystatus='1' " +
					"group by trunc((to_char(sysdate,'yyyy')-to_char(birthday,'yyyy'))/10) order by count(0) desc";
		}
		
		List data = sys.query(sql, null, true);
		return data;
	}

	
	public List getMeetCount(String counttype) throws Exception{
		
		String sql = "";
		if ("livingadd".equals(counttype)) {
			sql = "select c.text as name,count(0) as count from exp_user e,c_dic c,team_user t " +
					"where t.expuser_id = e.id and c.code = e.livingadd and c.type='livingadd' " +
					"group by c.text order by count(0) desc ";
		}
		if ("major".equals(counttype)) {
			sql = "select c.text as name,count(0) as count from exp_user e,c_dic c,team_user t " +
					"where t.expuser_id = e.id and substr(e.major,1,2)=c.code and c.type='major' " +
					"group by c.text order by count(0) desc " ;
		}
		if ("subject".equals(counttype)) {
			sql = "select c.text as name,count(0) as count from exp_user e,c_dic c,team_user t " +
			"where t.expuser_id = e.id and substr(e.subject,1,2)=c.code and c.type='subject' " +
			"group by c.text order by count(0) desc " ;
		}
		if ("sex".equals(counttype)) {
			sql = "select c.text as name,count(0) as count from exp_user e,c_dic c,team_user t " +
					"where t.expuser_id = e.id and c.code = e.sex and c.type='sex' " +
					"group by c.text order by count(0) desc ";
		}
		if ("age".equals(counttype)) {
			sql = "select trunc((to_char(sysdate,'yyyy')-to_char(e.birthday,'yyyy'))/10) as name,count(0) as count " +
					"from exp_user e,team_user t where t.expuser_id = e.id " +
					"group by trunc((to_char(sysdate,'yyyy')-to_char(e.birthday,'yyyy'))/10) order by count(0) desc";
		}
		
		List data = sys.query(sql, null, true);
		return data;
	}
	
}
