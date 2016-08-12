package module.meeting.services;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import module.common.services.LogServices;
import module.meeting.vo.MEETING;
import module.meeting.vo.TEAM;
import module.meeting.vo.TEAM_USER;
import module.user.vo.Exp_User;

import com.PublicSystem;
import com.Util;

import core.exception.BusinessException;
import core.utils.BeanTools;
import core.vo.system.Oa_tymenu;

public class MeetingServices {

  PublicSystem sys = PublicSystem.getInstance();
  LogServices logservices = new LogServices();

  // 取菜单列表
  public HashMap getMeetingList(Object[] obj, HashMap parms) throws Exception {
    // 设置sql语句
    String sql = "select * from MEETING " + "where ('" + obj[0]
        + "' is null or theme like '%'||?||'%') and ('" + obj[1]
        + "' is null or to_char(time,'yyyy-mm-dd')= ?) and ('" + obj[2]
        + "' is null or savestatus= ?) and status='1' order by time desc" ;
    System.out.println(sql);
    // 调用统一表单方法并返回map
    return sys.getGrid(sql, obj, parms, true);
  }

  public HashMap chooseTeam(HashMap teamnameMap, HashMap majorgrid)  throws Exception{
    HashMap TeamList = new HashMap();
    ArrayList choosedexpuser = new ArrayList();
    for(int i=0;i<majorgrid.size();i++){//获取每组信息
      String teamname=(String) teamnameMap.get(Integer.toString(i));
      ArrayList majorList = (ArrayList) majorgrid.get(Integer.toString(i));//获取每组汇总专业信息
      HashMap majorbyTeam = new HashMap();
      for(int j=0;j<majorList.size();j++){
        HashMap major = new HashMap();
        HashMap majorteam=(HashMap)majorList.get(j);//获取组中单条专业信息
        //查询三级专业
        List data=this.queryMajor3(majorteam, (String) majorteam.get("major"));
        
      int s1 = Integer.parseInt((String) majorteam.get("majornum"));
  if (data!=null&&data.size()>0){//三级专业不为空
        // 获取每个专业人数
	  	List data_c= this.cleanteam(data,choosedexpuser);
        int max0 = 0;
        if (data_c.size() >= s1 ) {// 查出三级专家数量满足要求
          max0 = s1;
        } else {// 查出三级专业专家数量不足
          max0 = data_c.size();
  
        }
        // 获取随机数随机抽取专家
        if(data_c.size()>0){
        major=this.chooseRandom(0, max0, major, data_c);
        }
        for (Object keyset : major.keySet()){
        	choosedexpuser.add(major.get(keyset));
        	
        }
        
        if (data_c.size() < s1 ) {// 查出三级专家数量不满足要求
          int cha = s1  - data_c.size();
          String major3 = (String) majorteam.get("major");
          List data_1=this.queryMajor2(majorteam, major3);

      if(data_1.size()>0&&data_1!=null){
    	  List data_1c= this.cleanteam(data_1,choosedexpuser);
          int min = data_c.size();
          int max = 0;
          if (data_1c.size() >= cha) {//查出二级专业专家满足要求
            max = s1;
            } else {//查出二级专业专家不满足要求
    
            max = data_c.size() + data_1c.size();
            }
          if(data_1c.size()>0)
          major=this.chooseRandom(min, max, major, data_1c);
          for (Object keyset : major.keySet()){
          	choosedexpuser.add(major.get(keyset));
          	
          }
          if (data_1c.size() < cha) {//一级专业查询
            
            List data_2 = this.queryMajor1(majorteam, major3);
            if(data_2.size()>0&&data_2!=null){
            
            List data_2c= this.cleanteam(data_2,choosedexpuser);
            int cha1=cha-data_2c.size();
            int min1 = data_c.size() + data_1c.size();
            int max1 = 0;
            if (data_2c.size() >= cha1) {//一级专业人员数量可以填补空缺
              max1 = s1 ;

            } else {
              max1 = data_c.size() + data_1c.size()+data_2c.size();
            }
            if(data_2c.size()>0)
            major=this.chooseRandom(min1, max1, major, data_2c);
            for (Object keyset : major.keySet()){
            	choosedexpuser.add(major.get(keyset));
            	
            }
            }
          }  
      }else{//二级专家数量为空

        List data_2 = this.queryMajor1(majorteam, major3);
        if(data_2.size()>0&&data_2!=null){
         
        List data_2c= this.cleanteam(data_2,choosedexpuser);
        int cha1=cha-data_c.size();
        int min1 = data_c.size();
        int max1 = 0;
        if (data_2c.size() >= cha1) {//一级专业人员数量可以填补空缺
          max1 = s1 ;

        } else {
          max1 = data_c.size() + data_2c.size();
        }
        if(data_2c.size()>0)
        major=this.chooseRandom(min1, max1, major, data_2c);
        for (Object keyset : major.keySet()){
        	choosedexpuser.add(major.get(keyset));
        }	
        }
      }  
        }  
  }else{//三级专业为空
      String major3 = (String) majorteam.get("major");
      List data_1 =this.queryMajor2(majorteam, major3);
    if(data_1.size()>0&&data_1!=null){//c查出二级专业不为空
    	List data_1c= this.cleanteam(data_1,choosedexpuser);
      int min = 0;
      int max = 0;
      if (data_1c.size() >= s1) {//查出二级专业专家满足要求
        max = s1;
        } else {//查出二级专业专家不满足要求
        max =  data_1c.size();
        }
      // 获取随机数随机抽取专家
      if(data_1c.size()>0)
      major=this.chooseRandom(min, max, major, data_1c);
      for (Object keyset : major.keySet()){
      	choosedexpuser.add(major.get(keyset));
      	
      }
      if (data_1c.size() < s1) {//一级专业查询
        List data_2 = this.queryMajor1(majorteam, major3);
        if(data_2.size()>0&&data_2!=null){
         
        List data_2c= this.cleanteam(data_2,choosedexpuser);
        int cha=s1-data_1c.size();
        int min1 = data_1c.size() ;
        int max1 = 0;
        if (data_2c.size() >= cha) {//一级专业人员数量可以填补空缺
          max1 = s1 ;
        } else {
          max1 =  data_1c.size()+data_2c.size();
        }
        if(data_2c.size()>0)
        major=this.chooseRandom(min1, max1, major, data_2c);
        for (Object keyset : major.keySet()){
        	choosedexpuser.add(major.get(keyset));
        	
        }
        }
      }

    }else{//查出二级专业为空
      
      List data_2 = this.queryMajor1(majorteam, major3);
      if(data_2.size()>0&&data_2!=null){
      List data_2c= this.cleanteam(data_2,choosedexpuser);
      int max1 = 0;
      
      if (data_2c.size() >=s1) {//一级专业人员数量可以填补空缺
        max1 = s1 ;
      } else {
        max1 = data_2c.size();
      }
      if(data_2c.size()>0)
      major=this.chooseRandom(0, max1, major, data_2c);
      for (Object keyset : major.keySet()){
      	choosedexpuser.add(major.get(keyset));
      }
      }
    }
    }
      majorbyTeam.put(j, major);
      }
      TeamList.put(teamname, majorbyTeam);
    }
    return TeamList;
  }  
    

  private List cleanteam(List data, ArrayList choosedexpuser) {
	  if(choosedexpuser==null||choosedexpuser.size()==0){
		  return data;
	  }else{
		  for(int i=0;i<data.size();i++){
			  boolean valid=true;
			  HashMap dataone=(HashMap) data.get(i);
			  for(int j=0;j<choosedexpuser.size();j++){
				  HashMap choosedexpuserone=(HashMap) choosedexpuser.get(j);
				  if(dataone.get("id").equals(choosedexpuserone.get("id"))){
					  valid=false;
					  break;
				  }
			  }
			  if(!valid){
			  data.remove(i);
			  i--;
			  }
		  }
		  
		  return data;
	  }
	
}

//查询三级专业专家
  public List  queryMajor3(HashMap majorteam,String major3 ){
	  String voidcompany=(String) majorteam.get("voidcompany");
	  if(voidcompany==null){
		  voidcompany="";
	  }
    String sql = "SELECT id,name,major,mobile FROM exp_user where major=? and  verifystatus='1' and status='1' and ('"
      + voidcompany
      + "' is null or company != ?) ";
      List data = sys.query(sql, new Object[] { majorteam.get("major"),
    		  voidcompany }, true);
    return data;
  }
  //查询二级专业专家
  public List  queryMajor2(HashMap majorteam,String major3 ){
	  String voidcompany=(String) majorteam.get("voidcompany");
	  if(voidcompany==null){
		  voidcompany="";
	  }
    String major2 = major3.substring(0, 4);
    String sql = "SELECT id,name,major,mobile FROM exp_user where major like ''||?||'%' and major!=?  and status='1' and verifystatus='1' and ('"
        +voidcompany
        + "' is null or company != ?) ";
    List data = sys.query(sql, new Object[] { major2,major3,
    		voidcompany }, true);
    return data;
  }
  //查询一级专业专家
  public List  queryMajor1(HashMap majorteam,String major3 ){
	  String voidcompany=(String) majorteam.get("voidcompany");
	  if(voidcompany==null){
		  voidcompany="";
	  }
    String major2 = major3.substring(0, 4);
    String major1 = major3.substring(0, 2);
    String sql = "SELECT id,name,major,mobile FROM exp_user where major like ''||?||'%' and major!=? and major not like  ''||?||'%'  and verifystatus='1' and status='1' and ('"
        + voidcompany
        + "' is null or company != ?) ";
    List data = sys.query(sql, new Object[] {  major1,major3,major2,
    		voidcompany}, true);
    return data;
  }
  
  
  //抽取随机专家
  public HashMap  chooseRandom(int min ,int max,HashMap major,List data){
    HashMap choose=new HashMap(); 
    while (major.size() < max) {
      int k = (int) (min + Math.random() * ((max-1) - min + 1));// (数据类型)(最小值+Math.random()*(最大值-最小值+1))
      if (major.size() > 0) {
        if (major.get(k) == null) {
           choose = (HashMap) data.get(k-min);
          major.put(k, choose);
        }
      } else {
         choose = (HashMap) data.get(k-min);
        major.put(k, choose);
      }
    }
    
    return major;
    
  }
  
  
  
  

  public HashMap queryTeam(HashMap p, HashMap row, Object o) throws Exception {
    // TODO Auto-generated method stub
    if ("1".equals(o)) {// 按专业分组
      HashMap ban = new HashMap();
      // 分专业抽取
      for (Object keyset : p.keySet()) {
        HashMap team = new HashMap();
        HashMap t = (HashMap) p.get(keyset);
        String sql = "SELECT id,name,major,mobile FROM exp_user where major=? and  verifystatus='1' and status='1' and ('"
            + row.get("voidcompany")
            + "' is null or company != ?) ";
        List data = sys.query(sql, new Object[] { t.get("major"),
            row.get("voidcompany") }, true);

        if (data.size() == 0 || data == null) {
          throw new BusinessException("未查到相关专业专家！");
        }
        // 获取每个专业人数
        int s1 = Integer.parseInt((String) t.get("team"));
        int s2 = Integer.parseInt((String) t.get("person"));
        int max0 = 0;
        if (data.size() >= s1 * s2) {// 查出专家数量满足要求
          max0 = s1 * s2 - 1;
        } else {
          max0 = data.size()-1;
        }
        // 获取随机数随机抽取专家
        while (team.size() <= max0) {
          int i = (int) (0 + Math.random() * (max0 - 0 + 1));// (数据类型)(最小值+Math.random()*(最大值-最小值+1))
          if (team.size() > 0) {
            if (team.get(i) == null) {
              HashMap choose = (HashMap) data.get(i);
              team.put(i, choose);
            }
          } else {
            HashMap choose = (HashMap) data.get(i);
            team.put(i, choose);
          }
        }
        if (data.size() < s1 * s2) {// 二级专业查询
          int cha = s1 * s2 - data.size();
          String major3 = (String) t.get("major");
          String major2 = major3.substring(0, 4);
          String sql_1 = "SELECT id,name,major,mobile FROM exp_user where major like ''||?||'%' and major!=?  and status='1' and verifystatus='1' and ('"
              + row.get("voidcompany")
              + "' is null or company != ?) ";
          List data_1 = sys.query(sql_1, new Object[] { major2,major3,
              row.get("voidcompany") }, true);
          int min = data.size()-1;;
          int max = 0;
          if (data_1.size() >= cha) {
            
            max = s1*s2-1;

          } else {
    
            max = data.size() + data_1.size()-1-1;
          }
          while (team.size() <max) {
          int i = (int) (min + Math.random() * (max - min + 1));// (数据类型)(最小值+Math.random()*(最大值-最小值+1))
          if (team.get(i) == null&&data_1.size()!=0) {
            HashMap choose = (HashMap) data_1.get(i - min);
            team.put(i-1, choose);
          }
          }
          if (data_1.size() < cha) {//一级专业查询
            String major1 = major3.substring(0, 2);
            String sql_2 = "SELECT id,name,major,mobile FROM exp_user where major like ''||?||'%' and major!=? and major not like  ''||?||'%'  and verifystatus='1' and status='1' and ('"
                + row.get("voidcompany")
                + "' is null or company != ?) ";
            List data_2 = sys.query(sql_2, new Object[] {  major1,major3,major2,
                row.get("voidcompany") }, true);
            int cha1=cha-data_2.size();
            int min1 = data.size() + data_1.size()-1;
            int max1 = 0;
            if (data_2.size() >= cha1) {//一级专业人员数量可以填补空缺
              max1 = s1 * s2-1;

            } else {
              max1 = data.size() + data_1.size()+data_2.size()-1-1;
            }
            while(team.size() <= max1){
            int j = (int) (min1 + Math.random() * (max1 - min1 + 1));// (数据类型)(最小值+Math.random()*(最大值-最小值+1))
            if (team.get(j) == null&&data_2.size()!=0) {
              HashMap choose = (HashMap) data_2.get(j - min1);
              team.put(j, choose);
              //team.put(j-1, choose);
            }
            }

          }

        }

        ban.put(t.get("major"), team);

      }
      return ban;
    } else {// 不分专业抽取专家
      String sql = "SELECT id,name,major,mobile FROM exp_user  where verifystatus='1' and status='1' and ('"
          + row.get("voidcompany") + "' is null or company != ?)";
      List data = sys.query(sql, new Object[] { row.get("voidcompany") },
          true);
      int s1 = Integer.parseInt((String) row.get("team"));
      int s2 = Integer.parseInt((String) row.get("person"));
      int s3 = 0;
      if (data.size() < s1 * s2) {
        s3 = data.size() - 1;
      } else {
        s3 = s1 * s2 - 1;
      }
      // 获取随机数随机抽取专家
      HashMap team = new HashMap();
      while (team.size() <= s3) {
        int i = (int) (0 + Math.random() * (s3 - 0 + 1));// (数据类型)(最小值+Math.random()*(最大值-最小值+1))
        if (team.size() > 0) {
          if (team.get(i) == null) {// 该专家未抽取
            if (i <= data.size()) {
              HashMap choose = (HashMap) data.get(i);
              team.put(i, choose);
            } else {
              team.put(i, null);
            }
          }
        } else {
          if (i <= data.size()) {
            HashMap choose = (HashMap) data.get(i);
            team.put(i, choose);
          } else {
            team.put(i, "");
          }

        }

      }
      return team;
    }

  }
  
  //导出所需评审会名单信息
  public HashMap MeetInfo(String meetingid) throws Exception{
      String sql = "select * from meeting m where m.id = ? ";
      List data = sys.query(sql, new Object[] { meetingid }, true);
      HashMap meet = (HashMap) data.get(0);
      
      String chairman = meet.get("chairman").toString();
      String vicechairman = meet.get("vicechairman").toString();
    
        if(!StringUtils.isNotBlank(chairman)){
          meet.put("chairman", "无");
        }
        if(!StringUtils.isNotBlank(chairman)){
          meet.put("vicechairman", "无");
        }
        return meet;
    
      
    }
  
  //组长信息
  public List teamleaderInfo(String meetingid) throws Exception{
    String sql = "select e.name as name,e.mobile as mobile,t.teamleader as id " +
        "from team t,exp_user e " +
        "where t.meeting_id=? and to_number(t.teamleader)=e.id " ;
    System.out.println(sql);    
    List data = sys.query(sql, new Object[] { meetingid }, true);
    return data;
  }
  
  //各组信息
  public List teamexpInfo(String meetingid,String team) throws Exception{
    String sql = "select e.name as name,e.mobile as mobile,e.id as id " +
        "from exp_user e,team t,team_user u " +
        "where t.meeting_id=? and t.id=u.team_id and u.expuser_id=e.id and t.teamleader=? " ;
    System.out.println(sql);    
    List data = sys.query(sql, new Object[] { meetingid,team }, true);
    return data;
  }
  
  //导出所需评审会名单信息
public List DownloadExpList(String meetingid) throws Exception{
    String sql = "select e.name as name,e.mobile as mobile,e.id as expid,e3.id as teamleaderid " +
        "from meeting m,team t,team_user tu,exp_user e,exp_user e3 " +
        "where m.id = ? and m.id = t.meeting_id and tu.team_id = t.id and tu.expuser_id = e.id " +
        "and to_number(t.teamleader) = e3.id " +
        "order by e3.name";
    System.out.println(sql);
    
    List data = sys.query(sql, new Object[] { meetingid }, true);
    return data;
  }

  public String insertMeetingInfo(HashMap row, HashMap majorgrid,
       String savestatus, String operator)
      throws Exception {
    // TODO Auto-generated method stub

    int meetingid = sys.getId("SEQ_MEETING_ID");
    // json转换vo
    MEETING meetingvo = new MEETING();
    meetingvo = (MEETING) Util.MapToVo(meetingvo, row);
    meetingvo.setID(meetingid);
    meetingvo.setSTATUS("1");
    meetingvo.setSAVESTATUS(savestatus);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
    String date = sdf.format(new Date());
    meetingvo.setSUBTIME((sdf.parse(date)));
    meetingvo.setOPERATOR(operator);

    // 事务开始
    sys.BeginTransaction();
    try {
      // 事务执行
      // --执行录入的dao事务方法
      sys.Jdbc_saveDaoTransaction(meetingvo);

      Set keySet = majorgrid.keySet();
      Iterator iter = keySet.iterator();
      while (iter.hasNext()) {
        int teamid = sys.getId("SEQ_TEAM_ID");
        TEAM teamvo = new TEAM();
        ArrayList grid = new ArrayList();
        String major = "";
        String key = (String) iter.next();
//        if ("1".equals(majorOrnot)) {
//          for (int j = 0; j < key.length(); j++) // 获取专业
//          {
//            if (key.substring(j, j + 1).equals("|")) {
//              major = key.substring(0, j).trim();
//              // k=key.substring(i+1,key.length()).trim();
//            }
//          }
//        }
        grid = (ArrayList) majorgrid.get(key);
        teamvo.setID(teamid);
        teamvo.setTEAMNAME(key);
        teamvo.setMEETING_ID(meetingvo.getID());
//        if ("1".equals(majorOrnot)) {
//          teamvo.setPROFESSION(major);
//        } else {
          teamvo.setPROFESSION("");// 不分专业组专业为空
//        }

        for (int j = 0; j < grid.size(); j++) {// 循环保存组与人员关联数据
          HashMap grid1 = (HashMap) grid.get(j);
          // 查询组长
          if ("1".equals(grid1.get("teamleader"))) {
            teamvo.setTEAMLEADER((String) grid1.get("id"));
          }

          TEAM_USER tu_vo = new TEAM_USER();
          tu_vo.setTEAM_ID(teamid);
          tu_vo.setEXPUSER_ID(Integer.parseInt((String) grid1
              .get("id")));

          sys.Jdbc_saveDaoTransaction(tu_vo);
        }
        sys.Jdbc_saveDaoTransaction(teamvo);
      }
    } finally {
      sys.endTransaction();
    }
          HashMap log = new HashMap();
        log.put("module", "2");
        log.put("type", "1");
      log.put("content", "新增了ID为"+meetingid+"的评审会信息");
      log.put("operator", operator);
      logservices.addLog(log);
    return "";
  }

  public String updateMeetingInfo(HashMap row, HashMap majorgrid,
      String savestatus, String operator) throws Exception {
    int meetingid = Integer.parseInt((String) row.get("id"));
    HashMap meeting = this.getMeetingById(meetingid);
    MEETING meetingold = new MEETING();
    meetingold = (MEETING) Util.MapToVo(meetingold, meeting);
    MEETING meetingnew = new MEETING();
    meetingnew = (MEETING) Util.MapToVo(meetingnew, row);
    BeanTools.copyPropertiesIgnoreNull(meetingnew, meetingold, true);
    meetingold.setCHAIRMAN(meetingnew.getCHAIRMAN());
    meetingold.setVICECHAIRMAN(meetingnew.getVICECHAIRMAN());
    meetingold.setVOIDCOMPANY(meetingnew.getVOIDCOMPANY());
    meetingold.setSAVESTATUS(savestatus);
    // 事务开始
    sys.BeginTransaction();

    try {
      // 事务执行
      // --执行录入的dao事务方法
      sys.Jdbc_modifyDaoTransaction(meetingold, new Object[] { "id:A" });

      Set keySet = majorgrid.keySet();
      Iterator iter = keySet.iterator();
      while (iter.hasNext()) {
        TEAM teamvo = new TEAM();
        String key = (String) iter.next();
        int teamid = Integer.parseInt(key);
        // 删除原来team数据
        sys.executeSqlTransaction("delete from team WHERE id='"
            + teamid + "'");
        sys
            .executeSqlTransaction("delete from team_user WHERE team_id='"
                + teamid + "'");

        ArrayList grid = new ArrayList();
        grid = (ArrayList) majorgrid.get(key);
        teamvo.setID(teamid);
        teamvo.setMEETING_ID(meetingold.getID());
        for (int j = 0; j < grid.size(); j++) {// 循环保存组与人员关联数据
          HashMap grid1 = (HashMap) grid.get(j);
          // 查询组长
          if ("1".equals(grid1.get("teamleader"))) {
            teamvo.setTEAMLEADER((String) grid1.get("id"));
          }
          teamvo.setPROFESSION((String) grid1.get("profession"));
          TEAM_USER tu_vo = new TEAM_USER();
          tu_vo.setTEAM_ID(teamid);
          tu_vo.setEXPUSER_ID(Integer.parseInt((String) grid1
              .get("id")));

          sys.Jdbc_saveDaoTransaction(tu_vo);
        }
        sys.Jdbc_saveDaoTransaction(teamvo);

      }

    } finally {
      sys.endTransaction();
    }

    return "";
  }

  public void deleteMeeting(List<Integer> idList) throws Exception {
    // TODO Auto-generated method stub
    for (int id : idList) {
      MEETING bo = new MEETING();
      HashMap p = new HashMap();
      p = this.getMeetingById(id);
      bo = (MEETING) Util.MapToVo(bo, p);
      bo.setSTATUS("0");
      sys.Jdbc_modifyDao(bo, new Object[] { "id:A" });
    }

  }

  public HashMap getMeetingById(int id) throws Exception {

    MEETING vo = new MEETING();
    String sql = "select * from MEETING where id = ? and status='1'";
    List data = sys.query(sql, new Object[] { id }, true);
    return data.size() > 0 ? (HashMap) data.get(0) : null;
  }

  public List getTeamInfo(int id) throws Exception {

    String sql = "select t.id as teamid,t.teamname,t.PROFESSION,decode(t.teamleader-r.id,0,1,0) as teamleader, r.id ,r.name,r.major,r.mobile from TEAM t, exp_user r, team_user u"
        + " where t.meeting_id =? and t.id = u.team_id and u.expuser_id =r.id and r.status='1' order by t.PROFESSION";
    List data = sys.query(sql, new Object[] { id }, true);
    return data;
  }

  
}
