package com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class EnterJdbcJsp
{
    PublicSystem sys = PublicSystem.getInstance();
    
    private String[] colnames;
    
    private String[] colTypes;
    
    private int[] colSizes;
    
    String res = "0";
    
    public String createJsp(String tablename, String vopage, String index)
        throws Exception
    {
        getDb(tablename);
        this.res = createJspForQuery(tablename, vopage, index);
        if ("0".equals(this.res))
            this.res = createJspForModify(tablename, vopage, index);
        return this.res;
    }
    
    public String createJspForQuery(String tablename, String vopage, String index)
    {
        String getUrl = Const.path + "/WebRoot/page/" + tablename.toLowerCase();
        String getUrlJsp = Const.path + "/WebRoot/page/" + tablename + "/" + tablename + ".jsp";
        StringBuffer sb = new StringBuffer();
        try
        {
            File sourceFile = new File(Const.path + "/src/com/jspmodel/query_model.jsp");
            File targetFile = new File(getUrl);
            if (!targetFile.exists())
            {
                targetFile.mkdirs();
            }
            
            InputStreamReader read = new InputStreamReader(new FileInputStream(sourceFile), "UTF-8");
            BufferedReader br = new BufferedReader(read);
            
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(getUrlJsp), "UTF-8");
            BufferedWriter bw = new BufferedWriter(write);
            String str = null;
            
            for (int j = 0; j < this.colnames.length; j++)
            {
                sb.append("       <div field=\"" + this.colnames[j].toLowerCase()
                    + "\" width=\"120\" headerAlign=\"center\" allowSort=\"true\">" + this.colnames[j] + "</div>\r\n");
            }
            
            while ((str = br.readLine()) != null)
            {
                if (str.contains("system.do"))
                    str = str.replaceAll("system.do", tablename + ".do");
                if (str.contains("getmenu"))
                    str = str.replaceAll("getmenu", "get" + tablename);
                if (str.contains("page/company/system/menu_edit.jsp"))
                    str = str.replaceAll("page/company/system/menu_edit.jsp", "page/" + tablename + "/" + tablename
                        + "_edit.jsp");
                if (str.contains("removemenu"))
                    str = str.replaceAll("removemenu", "remove" + tablename);
                if (str.contains("row.id"))
                    str = str.replaceAll("row.id", "row." + index);
                if (str.contains("r.id"))
                    str = str.replaceAll("r.id", "r." + index);
                if (str.contains("<listgjx>"))
                    str = str.replaceAll("<listgjx>", sb.toString());
                
                bw.write(str);
                bw.newLine();
            }
            bw.flush();
            bw.close();
            
            System.out.println(Const.path + "/WebRoot/page/" + tablename + "/" + tablename + ".jsp已经创建成功");
        }
        catch (IOException e)
        {
            e.printStackTrace();
            this.res = "1";
        }
        return this.res;
    }
    
    public String createJspForModify(String tablename, String vopage, String index)
    {
        String getUrl = Const.path + "/WebRoot/page/" + tablename;
        String getUrlJsp = Const.path + "/WebRoot/page/" + tablename + "/" + tablename + "_edit.jsp";
        StringBuffer sb = new StringBuffer();
        try
        {
            File sourceFile = new File(Const.path + "/src/com/jspmodel/edit_model.jsp");
            File targetFile = new File(getUrl);
            if (!targetFile.exists())
            {
                targetFile.mkdirs();
            }
            
            InputStreamReader read = new InputStreamReader(new FileInputStream(sourceFile), "UTF-8");
            BufferedReader br = new BufferedReader(read);
            
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(getUrlJsp), "UTF-8");
            BufferedWriter bw = new BufferedWriter(write);
            String str = null;
            String dataType = "";
            String dataClass = "";
            
            sb.append("<tr>\r\n");
            for (int j = 0; j < this.colnames.length; j++)
            {
                dataType = "";
                dataClass = "mini-textbox";
                if ("NUMBER".equals(this.colTypes[j]))
                    dataType = "vtype=\"int\"";
                if ("DATE".equals(this.colTypes[j]))
                {
                    dataType = "vtype=\"date:yyyy-MM-dd\"";
                    dataClass = "mini-datepicker";
                }
                
                sb.append("<td style=\"width:20%;\">" + this.colnames[j] + "：</td>\r\n");
                sb.append("<td style=\"width:30%;\"><input name=\"" + this.colnames[j].toLowerCase() + "\" class=\""
                    + dataClass + "\" required=\"true\" " + dataType + "  emptyText=\"请输入" + this.colnames[j]
                    + "\"/></td>\r\n");
                if (j % 2 != 0)
                    continue;
                if (j == this.colnames.length - 1)
                    sb.append("</tr>\r\n");
                else
                {
                    sb.append("</tr>\r\n<tr>\r\n");
                }
                
            }
            
            while ((str = br.readLine()) != null)
            {
                if (str.contains("/wondermini/system.do"))
                    str = str.replaceAll("/wondermini/system.do", "/" + Const.project + "/" + tablename + ".do");
                if (str.contains("savemenu"))
                    str = str.replaceAll("savemenu", "save" + tablename);
                if (str.contains("getmenudata"))
                    str = str.replaceAll("getmenudata", "get" + tablename + "data");
                
                if (str.contains("<gjx>"))
                {
                    str = str.replaceAll("<gjx>", sb.toString());
                }
                
                bw.write(str);
                bw.newLine();
            }
            bw.flush();
            bw.close();
            
            System.out.println(Const.path + "/WebRoot/page/" + tablename + "/" + tablename + "_edit.jsp已经创建成功");
        }
        catch (IOException e)
        {
            e.printStackTrace();
            this.res = "1";
        }
        return this.res;
    }
    
    private void getDb(String tablename)
        throws Exception
    {
        Connection con = null;
        
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
                this.colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
            }
        }
        catch (Exception f)
        {
            f.printStackTrace();
            this.res = "1";
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
    }
}