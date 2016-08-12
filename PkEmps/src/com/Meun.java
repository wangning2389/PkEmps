package com;

import java.util.Vector;
import com.*;
public class Meun {
	PublicSystem sys=PublicSystem.getInstance();
	
	StringBuffer sb = new StringBuffer();

	
	
	
	public String getMenu(String userid) throws Exception
	{
		
		String pid;String sid;String name;String url;
	
		
		//主应用菜单
		Vector res = menuPid();
		if(res!=null){
			for(int i=0;i<res.size();i++)
			{
				pid = (String) ((Vector) res.elementAt(i)).elementAt(0);
				name = (String) ((Vector) res.elementAt(i)).elementAt(2);
				sb.append("<li><span expanded=false>"+name+"</span><ul><li>");
				Vector res1 = menuSid(pid);
				if(res1!=null)
				{
					getData(res1,userid,pid);
				}
				sb.append("</ul></li>");
			}
		}
		
		return sb.toString();
	}
	
	private void getData(Vector vector, String  userid, String pid)
	throws Exception {
		String id = "";
		String name = "";
		String url = "";
		String target = "";
		Vector res2 = menuSid(pid);
		for (int i = 0; i < res2.size(); i++) 
		{
			id = (String) ((Vector) res2.elementAt(i)).elementAt(1);
			name = (String) ((Vector) res2.elementAt(i)).elementAt(2);
			url = (String) ((Vector) res2.elementAt(i)).elementAt(3);
			target = (String) ((Vector) res2.elementAt(i)).elementAt(4);

			if (!"#".equals(url)) {
				String f=sb.substring(sb.length()-4, sb.length());
				if("<li>".equals(f))sb.append("<a href='login.do?method=ForWard&id="+id+"' target='"+target+"'>"+name+"</a></li>");
				else if(i==res2.size()-1)sb.append("<li><a href='login.do?method=ForWard&id="+id+"' target='"+target+"'>"+name+"</a>");
				else sb.append("<li><a href='login.do?method=ForWard&id="+id+"' target='"+target+"'>"+name+"</a></li>");
			} else {
				Vector resChild = menuSid(id);
				if (resChild != null) {
					String f=sb.substring(sb.length()-4, sb.length());
					if("<li>".equals(f))sb.append("<span expanded='false'>"+name+"</span>");
					else sb.append("<li><span expanded='false'>"+name+"</span>");
					sb.append("<ul><li>");
					getData(resChild, userid,id);
					sb.append("</li></ul></li>");
				}
		}

}
}
	
	
	
	
	public Vector menuPid() throws Exception {
  		Vector res = sys.SelResultVec("select pid,id,name from oa_tymenu a where pid=id   " +
  			    		" and flag='1' group by pid,id,name,sort order by sort");
  		return res;
  	}
	
	public Vector menuSid(String pid) throws Exception {
  		Vector res = sys.SelResultVec("select c.pid,c.id,c.name,c.url,c.target from oa_tymenu c where  pid='" + pid + "' " + "and flag='1' and c.pid<>c.id group by c.pid,c.id,c.name,c.url," 
  		+ "c.target,sort order by sort");
  		if (res == null)
  			return null;
  		else
  			return res;
  	}

	public Vector menuchecked(String idd,String id) throws Exception {
		Vector res = sys.SelResultVec("select  checked from oa_roles_qx where rolesid='" + idd + "' and qxid='" + id + "'");
		return res;
	}
	
	
}
