package com;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Date;

public class EnterJdbcAction
{
  PublicSystem sys = PublicSystem.getInstance();
  private String authorName = "gjx";

  private boolean f_util = false;
  private boolean f_sql = false;

  public String createAction(String tablename, String vopage, String index)
  {
    String res = "0";
    String packageOutPath = "action." + tablename;
    String content = parse(tablename, index);
    try
    {
      String outputPathp = Const.path + "/src/" + packageOutPath.replace(".", "/");

      File f = new File(outputPathp);
      if (!f.exists()) {
        f.mkdirs();
      }

      String outputPath = Const.path + "/src/" + packageOutPath.replace(".", "/") + "/" + Util.initcap(tablename) + "Action.java";

      FileWriter fw = new FileWriter(outputPath);
      PrintWriter pw = new PrintWriter(fw);
      pw.println(content);
      pw.flush();
      pw.close();
      System.out.println("在" + outputPath + "Action类已经创建成功");
    } catch (IOException e) {
      e.printStackTrace();
      res = "1";
    }
    return res;
  }

  private String parse(String tablename, String index)
  {
    StringBuffer sb = new StringBuffer();

    sb.append("package action." + tablename + ";\r\n");
    sb.append("\r\n");

    sb.append("import java.util.*;\r\n");
    sb.append("import javax.servlet.http.HttpServletRequest;\r\n");
    sb.append("import javax.servlet.http.HttpServletResponse;\r\n");
    sb.append("import org.springframework.stereotype.Controller;\r\n");
    sb.append("import org.springframework.web.bind.annotation.RequestMapping;\r\n");
    sb.append("import com.*;\r\n");
    sb.append("import services." + tablename + "." + Util.initcap(tablename) + "Services;\r\n");
    if (this.f_util) {
      sb.append("import java.util.Date;\r\n");
    }
    if (this.f_sql) {
      sb.append("import java.sql.*;\r\n");
    }

    sb.append("   /**\r\n");
    sb.append("    * " + tablename + " \r\n");
    sb.append("    * " + new Date() + " " + this.authorName + "\r\n");
    sb.append("    */ \r\n");
    sb.append("    @Controller\r\n");
    sb.append("    @RequestMapping(\"" + tablename + ".do\")\r\n");

    sb.append("    public class " + Util.initcap(tablename) + "Action{\r\n\r\n");

    sb.append("    PublicSystem sys=PublicSystem.getInstance();\r\n\r\n");
    sb.append("    " + Util.initcap(tablename) + "Services ss=new " + Util.initcap(tablename) + "Services();\r\n\r\n");

    sb.append("   /**\r\n");
    sb.append("    * jquery mini ui Grid \r\n");
    sb.append("    * @param request\r\n");
    sb.append("    * @param response\r\n");
    sb.append("    *@throws Exception\r\n");
    sb.append("    */ \r\n");
    sb.append("    @RequestMapping(params =\"method=get" + tablename + "\")\r\n");
    sb.append("    public void get" + Util.initcap(tablename) + "(HttpServletRequest request, HttpServletResponse response) throws Exception{\r\n\r\n");

    sb.append("        HashMap parms=sys.getGridSort(request);\r\n");
    sb.append("        String json = Util.Encode(ss.get" + Util.initcap(tablename) + "(new Object[] {},parms));\r\n");
    sb.append("        Component.print(json, response);\r\n");
    sb.append("    } \r\n\r\n\r\n\r\n");

    sb.append("    @RequestMapping(params =\"method=remove" + tablename + "\")\r\n");
    sb.append("    public void remove" + Util.initcap(tablename) + "(HttpServletRequest request, HttpServletResponse response) throws Exception{\r\n\r\n");
    sb.append("        String idStr = Component.getQuest(\"id\", request);\r\n");
    sb.append("         ss.remove" + Util.initcap(tablename) + "(idStr);\r\n");
    sb.append("    } \r\n\r\n\r\n\r\n");

    sb.append("    @RequestMapping(params =\"method=get" + tablename + "data\")\r\n");
    sb.append("    public void get" + Util.initcap(tablename) + "Data(HttpServletRequest request, HttpServletResponse response) throws Exception{\r\n\r\n");
    sb.append("        HashMap user = ss.get" + Util.initcap(tablename) + "Data(Component.getQuest(\"id\", request));\r\n");
    sb.append("         Component.print(Util.Encode(user), response);\r\n");
    sb.append("    } \r\n\r\n\r\n\r\n");

    sb.append("    @RequestMapping(params =\"method=save" + tablename + "\")\r\n");
    sb.append("    public void save" + Util.initcap(tablename) + "(HttpServletRequest request, HttpServletResponse response) throws Exception{\r\n\r\n");
    sb.append("       String json=request.getParameter(\"data\");\r\n");
    sb.append("       ArrayList rows = (ArrayList)Util.Decode(json);\r\n");
    sb.append("       HashMap row = (HashMap)rows.get(0);\r\n");
    sb.append("       String id = row.get(\"" + index + "\") != null ? row.get(\"" + index + "\").toString() : \"\";\r\n");
    sb.append("       if(id.equals(\"\"))ss.insert" + Util.initcap(tablename) + "(row);\r\n");
    sb.append("       else ss.modified" + Util.initcap(tablename) + "(row);\r\n");
    sb.append("    } \r\n\r\n\r\n\r\n");

    sb.append("}\r\n");

    return sb.toString();
  }
}