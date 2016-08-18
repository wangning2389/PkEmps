package com;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class EnterJdbcDao
{
    PublicSystem sys = PublicSystem.getInstance();
    
    private String authorName = "gjx";
    
    private String[] colnames;
    
    private String[] colTypes;
    
    private int[] colSizes;
    
    private boolean f_util = false;
    
    private boolean f_sql = false;
    
    public String createVo(String tablename, String packageOutPath, String index)
    {
        Connection con = null;
        String res = "0";
        
        String sql = "select * from " + tablename;
        Statement pStemt = null;
        try
        {
            con = this.sys.getConnection();
            pStemt = con.createStatement();
            ResultSet rs = pStemt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            
            int size = rsmd.getColumnCount();
            this.colnames = new String[size];
            this.colTypes = new String[size];
            this.colSizes = new int[size];
            for (int i = 0; i < size; i++)
            {
                this.colnames[i] = rsmd.getColumnName(i + 1);
                this.colTypes[i] = rsmd.getColumnTypeName(i + 1);
                
                if ((this.colTypes[i].equalsIgnoreCase("date")) || (this.colTypes[i].equalsIgnoreCase("timestamp")))
                {
                    this.f_util = true;
                }
                if ((this.colTypes[i].equalsIgnoreCase("blob")) || (this.colTypes[i].equalsIgnoreCase("char")))
                {
                    this.f_sql = true;
                }
                this.colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
            }
            
            String content = parse(this.colnames, this.colTypes, this.colSizes, packageOutPath, tablename);
            try
            {
                String outputPathp = Const.path + "/src/" + packageOutPath.replace(".", "/") + "/" + "vo/";
                String outputPath = Const.path + "/src/" + packageOutPath.replace(".", "/") + "/" + "vo/"
                    + Util.initcap(tablename) + ".java";
                File f = new File(outputPathp);
                if (!f.exists())
                {
                    f.mkdirs();
                }
                FileWriter fw = new FileWriter(outputPath);
                PrintWriter pw = new PrintWriter(fw);
                pw.println(content);
                pw.flush();
                pw.close();
                System.out.println("在" + outputPath + "VO类已经创建成功");
            }
            catch (IOException e)
            {
                e.printStackTrace();
                res = "1";
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            res = "1";
            try
            {
                pStemt.close();
                con.close();
            }
            catch (SQLException e1)
            {
                e1.printStackTrace();
            }
        }
        finally
        {
            try
            {
                pStemt.close();
                con.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        
        return res;
    }
    
    private String parse(String[] colnames, String[] colTypes, int[] colSizes, String packageOutPath, String tablename)
    {
        StringBuffer sb = new StringBuffer();
        
        sb.append("package " + packageOutPath + ".vo" + ";\r\n");
        sb.append("\r\n");
        
        if (this.f_util)
        {
            sb.append("import java.util.Date;\r\n");
        }
        if (this.f_sql)
        {
            sb.append("import java.sql.*;\r\n");
        }
        
        sb.append("   /**\r\n");
        sb.append("    * " + tablename + " \r\n");
        sb.append("    * " + new Date() + " " + this.authorName + "\r\n");
        sb.append("    */ \r\n");
        
        sb.append("\r\n\r\npublic class " + Util.initcap(tablename) + "{\r\n");
        processAllAttrs(sb);
        processAllMethod(sb);
        sb.append("}\r\n");
        
        return sb.toString();
    }
    
    private void processAllAttrs(StringBuffer sb)
    {
        for (int i = 0; i < this.colnames.length; i++)
            sb.append("\tprivate " + sqlType2JavaType(this.colTypes[i]) + " " + this.colnames[i] + ";\r\n");
    }
    
    private void processAllMethod(StringBuffer sb)
    {
        for (int i = 0; i < this.colnames.length; i++)
        {
            sb.append("\tpublic void set" + Util.initcap(this.colnames[i]) + "(" + sqlType2JavaType(this.colTypes[i])
                + " " + this.colnames[i] + "){\r\n");
            sb.append("\tthis." + this.colnames[i] + "=" + this.colnames[i] + ";\r\n");
            sb.append("\t}\r\n");
            sb.append("\tpublic " + sqlType2JavaType(this.colTypes[i]) + " get" + Util.initcap(this.colnames[i])
                + "(){\r\n");
            sb.append("\t\treturn " + this.colnames[i] + ";\r\n");
            sb.append("\t}\r\n");
        }
    }
    
    private String sqlType2JavaType(String sqlType)
    {
        if (sqlType.equalsIgnoreCase("binary_double"))
            return "double";
        if (sqlType.equalsIgnoreCase("binary_float"))
            return "float";
        if (sqlType.equalsIgnoreCase("blob"))
            return "byte[]";
        if (sqlType.equalsIgnoreCase("blob"))
            return "byte[]";
        if ((sqlType.equalsIgnoreCase("char")) || (sqlType.equalsIgnoreCase("nvarchar2"))
            || (sqlType.equalsIgnoreCase("varchar2")))
            return "String";
        if ((sqlType.equalsIgnoreCase("date")) || (sqlType.equalsIgnoreCase("timestamp"))
            || (sqlType.equalsIgnoreCase("timestamp with local time zone"))
            || (sqlType.equalsIgnoreCase("timestamp with time zone")))
            return "Date";
        if (sqlType.equalsIgnoreCase("number"))
        {
            return "int";
        }
        
        return "String";
    }
}