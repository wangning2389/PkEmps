package module.common.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import module.common.vo.C_OP_LOG;
import module.user.vo.Exp_User;

import com.PublicSystem;
import com.Util;

public class LogServices {
	PublicSystem sys = PublicSystem.getInstance();
	
	public String addLog(HashMap obj) throws Exception{
		int id = sys.getId("SEQ_C_OP_ID");
		C_OP_LOG vo = new C_OP_LOG();
		obj.put("id", id);
		vo = (C_OP_LOG) Util.MapToVo(vo, obj);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		String date=sdf.format(new Date());
		vo.setOP_DATE((sdf.parse(date)));
		sys.Jdbc_saveDao(vo);
		return "0";
	}
	
}
