package com;
import com.*;
import java.io.BufferedWriter;
import java.io.FileOutputStream;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class Tree {
	PublicSystem sys=PublicSystem.getInstance();
	StringBuffer sb = new StringBuffer();
	Meun m=new Meun();
	public void createTree() throws Exception{
		String pid="";String name="";String sid="";String sname="";
		String url="";
		sb.append("[");
		Vector res=m.menuPid();
		for(int i=0;i<res.size();i++)
		{
			pid = (String) ((Vector) res.elementAt(i)).elementAt(0);
			name = (String) ((Vector) res.elementAt(i)).elementAt(2);
			sb.append("{id: \""+pid+"\", text: \""+name+"\"},");
	
			Vector res1=m.menuSid(pid);
			if(null!=res1)
			{
				createNodeTree(pid);
			}
		}
		sb.append("]");
		sb.replace(sb.length()-2, sb.length()-1,"");
		String outputPath = Const.server_path+"/page/company/system/tree.txt";
		OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(outputPath),"UTF-8");
		BufferedWriter bw = new BufferedWriter(write);
		//FileWriter fw = new FileWriter(outputPath);
		PrintWriter pw = new PrintWriter(bw);
		pw.println(sb);
		pw.flush();
		pw.close();
		System.out.println("在"+outputPath+"已经创建成功");
	
	}
		public void createNodeTree(String pid) throws Exception{
			
			String checked="false";
			String sid="";String sname="";
			String url="";
			//如果qxid== sid
			Vector res=m.menuSid(pid);
			if(null!=res)
			{
				for(int i=0;i<res.size();i++)
				{
					sid = (String) ((Vector) res.elementAt(i)).elementAt(1);
					sname = (String) ((Vector) res.elementAt(i)).elementAt(2);
					url = (String) ((Vector) res.elementAt(i)).elementAt(3);
					//如果qxid== sid  checked='true'
					if("#".equals(url))
					{
						sb.append("{id: \""+sid+"\", text: \""+sname+"\",pid: \""+pid+"\",checked: \""+checked+"\"},");
						createNodeTree(sid);
					}
					else
					{
						sb.append("{id: \""+sid+"\", text: \""+sname+"\",pid: \""+pid+"\",checked: \""+checked+"\"},");
					}
					
				}
		
			
			}
			
		}
		
}
	
