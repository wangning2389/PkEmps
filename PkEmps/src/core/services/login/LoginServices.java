package core.services.login;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.sql.*;

import org.apache.commons.codec.digest.DigestUtils;

import com.PublicSystem;

import core.exception.BusinessException;
import core.vo.User;
import core.vo.system.AC01;

public class LoginServices {
	
	PublicSystem sys=PublicSystem.getInstance();
	
	public String getUser(HashMap map) throws Exception
	{
	    User vo=new User();
        String sql = "select * from C_USER where username = ?";
        List data = sys.query(sql, new Object[] {map.get("username")} , true);
        if (data.size() == 0||data==null ){
        	throw new BusinessException("用户名不存在！");
        }else {
        	HashMap map1 = new HashMap();
        	map1=(HashMap)data.get(0);
        	String res="登录成功！";
        	if(map1.get("password").equals(DigestUtils.md5Hex((String)map.get("password")))){
        		return res;
        	}else{
        		throw new BusinessException("密码错误！");
        	}
        }
        }
		
		
//        if (entity.getPassword().equals(DigestUtils.md5Hex(user.getPassword()))) {
//			this.setSessionAttr(UserConstant.USER_IN_SESSION, entity);
//			return SUCCESS;
//		} else {
//			throw new LocalBusinessException("密码不正确！");
//		}
		
		
//		String res="登录成功！";
//		res=sys.SelResultStr("select id from C_USER where username='"+map.get("username")+"' and password='"+map.get("password")+"'");
//		
//		String reslut =  res != null ? res : "1";
//		
//		return reslut; 
	
	
	public String getUrl(String sid) throws Exception {
		String sql = "SELECT url FROM oa_tymenu WHERE id='"
				+ sid + "'";
		return sys.SelResultStr(sql);
	}
	
	
	
}
