package com;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Date;

public class EnterJdbcServices
{
  PublicSystem sys = PublicSystem.getInstance();
  private String authorName = "gjx";

  private boolean f_util = true;
  private boolean f_sql = false;

  public String createServices(String tablename, String vopage, String index)
  {
    String res = "0";
    String packageOutPath = "services." + tablename;
    String content = parse(tablename, vopage, index);
    try
    {
      String outputPathp = Const.path + "/src/" + packageOutPath.replace(".", "/");

      File f = new File(outputPathp);
      if (!f.exists()) {
        f.mkdirs();
      }

      String outputPath = Const.path + "/src/" + packageOutPath.replace(".", "/") + "/" + Util.initcap(tablename) + "Services.java";

      FileWriter fw = new FileWriter(outputPath);
      PrintWriter pw = new PrintWriter(fw);
      pw.println(content);
      pw.flush();
      pw.close();
      System.out.println("在" + outputPath + "Services类已经创建成功");
    } catch (IOException e) {
      e.printStackTrace();
      res = "1";
    }
    return res;
  }

  private String parse(String tablename, String vopage, String index)
  {
    StringBuffer sb = new StringBuffer();

    sb.append("package services." + tablename + ";\r\n");
    sb.append("\r\n");

    sb.append("import java.util.*;\r\n");
    sb.append("import com.*;\r\n");
    sb.append("import " + vopage + "." + Util.initcap(tablename) + ";\r\n");
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

    sb.append("    public class " + Util.initcap(tablename) + "Services{\r\n\r\n");

    sb.append("    PublicSystem sys=PublicSystem.getInstance();\r\n\r\n");

    sb.append("    public HashMap get" + Util.initcap(tablename) + "(Object[] obj,HashMap parms) throws Exception{\r\n\r\n");
    sb.append("        String sql = \"select * from " + tablename + " \";\r\n");
    sb.append("        return  sys.getGrid(sql, obj,parms,true);\r\n");
    sb.append("    } \r\n\r\n\r\n\r\n");

    sb.append("    public void remove" + Util.initcap(tablename) + "(String idStr) throws Exception{\r\n\r\n");
    sb.append("        " + Util.initcap(tablename) + " vo=new " + Util.initcap(tablename) + "();\r\n");
    sb.append("        String[] ids = idStr.split(\",\");\r\n");
    sb.append("        for (int i = 0, l = ids.length; i < l; i++){\r\n");
    sb.append("        vo.set" + index.toUpperCase() + "(ids[i]);\r\n");
    sb.append("        sys.Jdbc_deleteDao(vo,new Object[]{\"" + index + ":A\"});\r\n");
    sb.append("        }\r\n");
    sb.append("    } \r\n\r\n\r\n\r\n");

    sb.append("    public HashMap get" + Util.initcap(tablename) + "Data (String id) throws Exception{\r\n\r\n");
    sb.append("        " + Util.initcap(tablename) + " vo=new " + Util.initcap(tablename) + "();\r\n");
    sb.append("        String sql = \"select * from " + tablename + " where " + index + " = ?\";\r\n");
    sb.append("        List data = sys.query(sql, new Object[] {id},true);\r\n");
    sb.append("        return data.size() > 0 ? (HashMap)data.get(0) : null;\r\n");
    sb.append("    } \r\n\r\n\r\n\r\n");

    sb.append("    public String insert" + Util.initcap(tablename) + "(HashMap obj) throws Exception{\r\n\r\n");
    sb.append("        " + Util.initcap(tablename) + " vo=new " + Util.initcap(tablename) + "();\r\n");
    sb.append("         vo=(" + Util.initcap(tablename) + ")Util.MapToVo(vo, obj);\r\n");
    sb.append("         sys.Jdbc_saveDao(vo);\r\n");
    sb.append("         return \"\";\r\n");
    sb.append("    } \r\n\r\n\r\n\r\n");

    sb.append("    public String modified" + Util.initcap(tablename) + "(HashMap obj) throws Exception{\r\n\r\n");
    sb.append("        " + Util.initcap(tablename) + " vo=new " + Util.initcap(tablename) + "();\r\n");
    sb.append("         vo=(" + Util.initcap(tablename) + ")Util.MapToVo(vo, obj);\r\n");
    sb.append("         sys.Jdbc_modifyDao(vo,new Object[]{\"" + index + ":A\"});\r\n");
    sb.append("         return \"\";\r\n");
    sb.append("    } \r\n\r\n\r\n\r\n");

    sb.append("}\r\n");

    return sb.toString();
  }
}