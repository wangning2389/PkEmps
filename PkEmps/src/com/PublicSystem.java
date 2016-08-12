package com;

import cn.sfkj.util.DBAccess;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class PublicSystem
{
  private static final PublicSystem instance = new PublicSystem();
  Const Const = new Const();

  private DBAccess dbAccess = new DBAccess(Const.JNDI);

  public static PublicSystem getInstance()
  {
    return instance;
  }

  public String getIdMum(int seq, int num) throws Exception
  {
    String str = String.format("%0" + num + "d", new Object[] { Integer.valueOf(seq) });
    return str;
  }

  public int getId(String seq)
    throws Exception
  {
    return Integer.parseInt(getDbAccess().selectOne("select " + seq + ".nextval from dual"));
  }

  public DBAccess getDbAccess()
  {
    return this.dbAccess;
  }

  public Connection getConnection()
  {
    return getDbAccess().getConnection();
  }

  public void BeginTransaction()
  {
    getDbAccess().BeginTransaction();
  }

  public void updateDataTransaction(String sql)
    throws Exception
  {
    getDbAccess().updateDataTransaction(sql);
  }

  public void endTransaction()
    throws Exception
  {
    getDbAccess().endTransaction();
  }

  public void executeSql(String sql)
    throws Exception
  {
    getDbAccess().insert(sql);
  }

  public void executeSqlTransaction(String sql)
    throws Exception
  {
    getDbAccess().updateDataTransaction(sql);
  }

  public Vector SelResultVec(String sql)
    throws Exception
  {
    try
    {
      Vector result = this.dbAccess.select(sql);
      return result;
    }
    catch (Exception ex) {
    }
    return null;
  }

  public String SelResultStr(String sql)
    throws Exception
  {
    try
    {
      String result = this.dbAccess.selectOne(sql);
      return result;
    }
    catch (Exception ex) {
    }
    return null;
  }

  public List SelResultList(String sql)
    throws Exception
  {
    try
    {
      List result = this.dbAccess.ExecQuery(sql);
      return result;
    }
    catch (Exception ex) {
    }
    return null;
  }

  public String[] SelResultNum(String sql)
    throws Exception
  {
    Vector result = getDbAccess().select(sql);
    String reslut = "";
    int sqlCont = SqlCount(sql);
    String[] xml = new String[sqlCont];

    for (int i = 0; i < sqlCont; i++)
    {
      try {
        reslut = ((Vector)result.elementAt(0)).elementAt(i).toString()
          .trim();
      }
      catch (Exception wx)
      {
        reslut = " ";
      }

      xml[i] = reslut;
    }

    return xml;
  }

  public void InesrtData(String sql, HashMap data)
    throws Exception
  {
    Connection con = null;
    PreparedStatement stmt = null;
    try
    {
      String[] col = sql.substring(sql.indexOf('(') + 1, sql.indexOf(')')).split(",");
      con = getConnection();
      stmt = con.prepareStatement(sql);
      for (int i = 0; i < col.length; i++)
      {
        String mapname = col[i].trim();

        boolean isflag = Component.isValidDate((String)data.get(mapname));
        if (isflag)
        {
          try
          {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date date1 = new java.util.Date();
            date1 = sdf.parse((String)data.get(mapname));
            stmt.setObject(i + 1, new java.sql.Date(date1.getTime()));
          }
          catch (Exception c) {
            stmt.setObject(i + 1, data.get(mapname));
          }
        }
        else
        {
          stmt.setObject(i + 1, data.get(mapname));
        }
      }
      stmt.executeUpdate();
      con.commit();
    }
    catch (Exception ex)
    {
      throw ex;
    }
    finally
    {
      try
      {
        stmt.close();
        con.close();
      }
      catch (Exception ex)
      {
        throw ex;
      }
    }
  }

  public void UpdateData(String sql, HashMap data, String wherecol, Object[] obj)
    throws Exception
  {
    Connection con = null;
    PreparedStatement stmt = null;
    try
    {
      String colname = sql.substring(sql.indexOf("set") + 3, sql.length());
      String[] col = colname.split(",");

      sql = sql + "  where " + wherecol;
      con = getConnection();
      stmt = con.prepareStatement(sql);
      int i = 0;
      for (i = 0; i < col.length; i++)
      {
        String mapname = col[i].substring(0, col[i].length() - 2).trim();

        boolean isflag = Component.isValidDate((String)data.get(mapname));
        if (isflag)
        {
          try
          {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date date1 = new java.util.Date();
            date1 = sdf.parse((String)data.get(mapname));
            stmt.setObject(i + 1, new java.sql.Date(date1.getTime()));
          }
          catch (Exception c) {
            stmt.setObject(i + 1, data.get(mapname));
          }
        }
        else
        {
          stmt.setObject(i + 1, data.get(mapname));
        }
      }
      for (int j = 0; j < obj.length; j++) {
        stmt.setObject(i + 1, obj[j]);
      }
      stmt.executeUpdate();
      con.commit();
    }
    catch (Exception ex)
    {
      throw ex;
    }
    finally
    {
      try
      {
        stmt.close();
        con.close();
      }
      catch (Exception ex)
      {
        throw ex;
      }
    }
  }

  public List query(String sql, Object[] obj, boolean flag)
  {
    List list = new ArrayList();
    if (!flag)
    {
      if ("1".equals(Util.getObject(obj)))
      {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
          conn = getConnection();
          pstmt = conn.prepareStatement(sql);

          for (int i = 0; i < obj.length; i++) {
            pstmt.setObject(i + 1, obj[i]);
          }
          ResultSet rs = pstmt.executeQuery();

          ResultSetMetaData rsmd = rs.getMetaData();
          int numCols = rsmd.getColumnCount();
          String tempValue = "";

          while (rs.next())
          {
            HashMap hm = new HashMap();
            for (int i = 1; i <= numCols; i++)
            {
              tempValue = rs.getString(i);
              if (tempValue == null) tempValue = "";
              hm.put(rsmd.getColumnLabel(i).toLowerCase(), tempValue.trim());
            }
            list.add(hm);
          }
        }
        catch (SQLException ex) {
          ex.printStackTrace();
          try
          {
            pstmt.close();
            conn.close();
          } catch (SQLException ex1) {
            ex1.printStackTrace();
          }
        }
        finally
        {
          try
          {
            pstmt.close();
            conn.close();
          } catch (SQLException ex) {
            ex.printStackTrace();
          }
        }
        try
        {
          pstmt.close();
          conn.close();
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
      }
    }
    else
    {
      Connection conn = null;
      PreparedStatement pstmt = null;
      try {
        conn = getConnection();
        pstmt = conn.prepareStatement(sql);
        if ((obj != null) && (obj.length != 0))
        {
          for (int i = 0; i < obj.length; i++) {
            pstmt.setObject(i + 1, obj[i]);
          }
        }
        ResultSet rs = pstmt.executeQuery();

        ResultSetMetaData rsmd = rs.getMetaData();
        int numCols = rsmd.getColumnCount();
        String tempValue = "";

        while (rs.next())
        {
          HashMap hm = new HashMap();
          for (int i = 1; i <= numCols; i++)
          {
            tempValue = rs.getString(i);
            if (tempValue == null) tempValue = "";
            hm.put(rsmd.getColumnLabel(i).toLowerCase(), tempValue.trim());
          }
          list.add(hm);
        }
      }
      catch (SQLException ex) {
        ex.printStackTrace();
        try
        {
          pstmt.close();
          conn.close();
        } catch (SQLException ex1) {
          ex1.printStackTrace();
        }
      }
      finally
      {
        try
        {
          pstmt.close();
          conn.close();
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
      }
    }

    return list;
  }

  public void RemoveData(String sql, Object[] obj)
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    try {
      conn = getConnection();
      pstmt = conn.prepareStatement(sql);
      if (obj != null)
      {
        for (int i = 0; i < obj.length; i++) {
          pstmt.setObject(i + 1, obj[i]);
        }
      }
      pstmt.executeUpdate();
      conn.commit();
    } catch (SQLException ex) {
      ex.printStackTrace();
      try
      {
        pstmt.close();
        conn.close();
      } catch (SQLException ex1) {
        ex1.printStackTrace();
      }
    }
    finally
    {
      try
      {
        pstmt.close();
        conn.close();
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    }
  }

  public HashMap getGridSort(HttpServletRequest request)
    throws Exception
  {
    HashMap parms = new HashMap();

    parms.put("pageIndex", Component.getQuest("pageIndex", request));
    parms.put("pageSize", Component.getQuest("pageSize", request));

    parms.put("sortField", Component.getQuest("sortField", request));
    parms.put("sortOrder", Component.getQuest("sortOrder", request));
    return parms;
  }

  public HashMap getGrid(String sql, Object[] obj, HashMap parms, boolean flag)
    throws Exception
  {
    String sortOrder = (String)parms.get("sortOrder");
    String sortField = (String)parms.get("sortField");
    if (!Util.isNullOrEmpty(sortField))
    {
      if (!"desc".equals(sortOrder)) sortOrder = "asc";
      sql = sql + " order by " + sortField + " " + sortOrder;
    }
    else
    {
      sql = sql + " ";
    }

    List dataAll = query(sql, obj, flag);

    ArrayList data = new ArrayList();

    int start = Integer.parseInt(parms.get("pageIndex").toString()) * Integer.parseInt(parms.get("pageSize").toString()); int end = start + Integer.parseInt(parms.get("pageSize").toString());

    int i = 0; for (int l = dataAll.size(); i < l; i++)
    {
      HashMap record = (HashMap)dataAll.get(i);
      if ((record == null) || 
        (start > i) || (i >= end))
        continue;
      data.add(record);
    }

    HashMap result = new HashMap();
    result.put("data", data);
    result.put("total", Integer.valueOf(dataAll.size()));
    return result;
  }

  public void Jdbc_saveDao(Object entity)
    throws SQLException, IllegalArgumentException, InvocationTargetException, IllegalAccessException
  {
    Class clazz = entity.getClass();
    Method[] methods = clazz.getDeclaredMethods();
    Field[] fields = clazz.getDeclaredFields();
    String tableName = clazz.getSimpleName().toLowerCase();

    String fieldsName = "";
    String placeholder = "";
    for (int i = 0; i < fields.length; i++) {
      fieldsName = fieldsName + fields[i].getName() + ",";
      placeholder = placeholder + "?" + ",";
    }

    fieldsName = fieldsName.substring(0, fieldsName.length() - 1);
    placeholder = placeholder.substring(0, placeholder.length() - 1);

    String sql = "insert into " + tableName + "(" + fieldsName + ")" + 
      " values " + "(" + placeholder + ")";

    System.out.println(sql);

    Connection con = null; PreparedStatement pst = null; Object propertyObj = null;
    try
    {
      con = getConnection();
      pst = con.prepareStatement(sql);
      int index = 1;

      for (int j = 0; j < fields.length; j++)
      {
        String str = "get" + fields[j].getName();
        String type = fields[j].getType().toString();
        for (int k = 0; k < methods.length; k++) {
          if (!str.equalsIgnoreCase(methods[k].getName()))
            continue;
          propertyObj = methods[k].invoke(entity, new Object[0]);

          if ((propertyObj == null) || ("null".equals(propertyObj))) {
            propertyObj = " ";
          }
          if ("class java.util.Date".equals(type))
          {
            if (" ".equals(propertyObj)) pst.setNull(index++, 91); else
              pst.setObject(index++, new java.sql.Date(((java.util.Date)propertyObj).getTime()));
          }
          else if ("int".equals(type))
          {
            if (" ".equals(propertyObj)) pst.setNull(index++, 4); else {
              pst.setObject(index++, propertyObj);
            }
          }
          else {
            pst.setObject(index++, propertyObj);
          }
        }

      }

      pst.executeUpdate();
      con.commit();
      System.out.println("---------------------------------");
    } catch (SQLException ex) {
      ex.printStackTrace();
      
      try
      {
        pst.close();
        con.close();
      } catch (SQLException ex1) {
        ex1.printStackTrace();
        throw ex1;
      }
      throw ex;
    }
    finally
    {
      try
      {
        pst.close();
        con.close();
      } catch (SQLException ex) {
        ex.printStackTrace();
        throw ex;
      }
    }
  }

  public void Jdbc_saveDaoTransaction(Object entity)
    throws SQLException, IllegalArgumentException, InvocationTargetException, IllegalAccessException
  {
    Class clazz = entity.getClass();
    Method[] methods = clazz.getDeclaredMethods();
    Field[] fields = clazz.getDeclaredFields();
    String tableName = clazz.getSimpleName().toLowerCase();

    String fieldsName = "";
    String placeholder = "";
    for (int i = 0; i < fields.length; i++) {
      fieldsName = fieldsName + fields[i].getName() + ",";
      placeholder = placeholder + "?" + ",";
    }

    fieldsName = fieldsName.substring(0, fieldsName.length() - 1);
    placeholder = placeholder.substring(0, placeholder.length() - 1);

    String sql = "insert into " + tableName + "(" + fieldsName + ")" + 
      " values " + "(" + placeholder + ")";

    System.out.println(sql);

    Connection con = null; PreparedStatement pst = null; Object propertyObj = null;
    try
    {
      con = this.dbAccess.getTransaction();
      if (con != null) {
        pst = con.prepareStatement(sql);
        int index = 1;
        for (int j = 0; j < fields.length; j++)
        {
          String str = "get" + fields[j].getName();
          String type = fields[j].getType().toString();
          for (int k = 0; k < methods.length; k++) {
            if (!str.equalsIgnoreCase(methods[k].getName()))
              continue;
            propertyObj = methods[k].invoke(entity, new Object[0]);

            if ((propertyObj == null) || ("null".equals(propertyObj))) {
              propertyObj = " ";
            }
            if ("class java.util.Date".equals(type))
            {
              if (" ".equals(propertyObj)) pst.setNull(index++, 91); else
                pst.setObject(index++, new java.sql.Date(((java.util.Date)propertyObj).getTime()));
            }
            else if ("int".equals(type))
            {
              if (" ".equals(propertyObj)) pst.setNull(index++, 4); else {
                pst.setObject(index++, propertyObj);
              }
            }
            else {
              pst.setObject(index++, propertyObj);
            }
          }

        }

        pst.executeUpdate();

        System.out.println("---------------------------------");
      }
    }
    catch (SQLException ex) {
      ex.printStackTrace();
      pst.close();
      con.rollback();
      try {
        endTransaction(); } catch (Exception localException) {
      }System.out.println("发生严重错误!事务处理失败!");
      try
      {
        if (pst != null)
        {
          pst.close();
        }
      }
      catch (SQLException ex1) {
        ex1.printStackTrace();
      }
    }
    finally
    {
      try
      {
        if (pst != null)
        {
          pst.close();
        }
      }
      catch (SQLException ex) {
        ex.printStackTrace();
      }
    }
  }

  public void Jdbc_modifyDaoTransaction(Object entity, Object[] obj)
    throws SQLException, IllegalArgumentException, InvocationTargetException, IllegalAccessException
  {
    Class clazz = entity.getClass();
    Method[] methods = clazz.getDeclaredMethods();
    Field[] fields = clazz.getDeclaredFields();
    String tableName = clazz.getSimpleName().toLowerCase();

    String fieldsName = ""; String whereName = "";
    Object[] val = new Object[obj.length];

    for (int i = 0; i < fields.length; i++)
    {
      for (int j = 0; j < methods.length; j++)
      {
        String getFieldName = "get" + fields[i].getName();
        if (!getFieldName.equalsIgnoreCase(methods[j].getName())) {
          continue;
        }
        fieldsName = fieldsName + fields[i].getName() + "=?,";
      }

    }

    for (int h = 0; h < obj.length; h++)
    {
      String tc = ""; String link = "";
      String col = obj[h].toString().split(":")[0].toUpperCase();
      tc = "get" + col;
      for (int j = 0; j < methods.length; j++) {
        if (tc.equalsIgnoreCase(methods[j].getName())) {
          val[h] = methods[j].invoke(entity, new Object[0]);
        }
      }
      if (("A".equals(obj[h].toString().split(":")[1])) || ("a".equals(obj[h].toString().split(":")[1]))) link = "AND ";
      else if (("O".equals(obj[h].toString().split(":")[1])) || ("o".equals(obj[h].toString().split(":")[1]))) link = "OR "; else
        link = " ";
      if (h == obj.length - 1) link = " ";
      whereName = whereName + col + "=? " + link;
    }

    fieldsName = fieldsName.substring(0, fieldsName.length() - 1);
    String sql = "update " + tableName + " set " + fieldsName + 
      " where " + whereName;

    System.out.println(sql);

    Connection con = null; PreparedStatement pst = null; Object propertyObj = null;
    try
    {
      con = this.dbAccess.getTransaction();
      if (con != null) {
        pst = con.prepareStatement(sql);
        int index = 1;

        for (int j = 0; j < fields.length; j++)
        {
          String str = "get" + fields[j].getName();
          String type = fields[j].getType().toString();
          for (int k = 0; k < methods.length; k++) {
            if (!str.equalsIgnoreCase(methods[k].getName()))
              continue;
            propertyObj = methods[k].invoke(entity, new Object[0]);

            if ((propertyObj == null) || ("null".equals(propertyObj))) {
              propertyObj = " ";
            }
            if ("class java.util.Date".equals(type))
            {
              if (" ".equals(propertyObj)) pst.setNull(index++, 91); else
                pst.setObject(index++, new java.sql.Date(((java.util.Date)propertyObj).getTime()));
            }
            else if ("int".equals(type))
            {
              if (" ".equals(propertyObj)) pst.setNull(index++, 4); else {
                pst.setObject(index++, propertyObj);
              }
            }
            else {
              pst.setObject(index++, propertyObj);
            }
          }

        }

        for (int h = 0; h < obj.length; h++)
        {
          pst.setObject(index++, val[h]);
        }
        pst.executeUpdate();

        System.out.println("---------------------------------");
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
      pst.close();
      con.rollback();
      try {
        endTransaction(); } catch (Exception localException) {
      }System.out.println("发生严重错误!事务处理失败!");
      try
      {
        pst.close();
      }
      catch (SQLException ex1) {
        ex1.printStackTrace();
      }
    }
    finally
    {
      try
      {
        pst.close();
      }
      catch (SQLException ex) {
        ex.printStackTrace();
      }
    }
  }

  public void Jdbc_modifyDao(Object entity, Object[] obj)
    throws SQLException, IllegalArgumentException, InvocationTargetException, IllegalAccessException
  {
    Class clazz = entity.getClass();
    Method[] methods = clazz.getDeclaredMethods();
    Field[] fields = clazz.getDeclaredFields();
    String tableName = clazz.getSimpleName().toLowerCase();

    String fieldsName = ""; String whereName = "";
    Object[] val = new Object[obj.length];

    for (int i = 0; i < fields.length; i++)
    {
      for (int j = 0; j < methods.length; j++)
      {
        String getFieldName = "get" + fields[i].getName();
        if (!getFieldName.equalsIgnoreCase(methods[j].getName())) {
          continue;
        }
        fieldsName = fieldsName + fields[i].getName() + "=?,";
      }

    }

    for (int h = 0; h < obj.length; h++)
    {
      String tc = ""; String link = "";
      String col = obj[h].toString().split(":")[0].toUpperCase();
      tc = "get" + col;
      for (int j = 0; j < methods.length; j++) {
        if (tc.equalsIgnoreCase(methods[j].getName())) {
          val[h] = methods[j].invoke(entity, new Object[0]);
        }
      }
      if (("A".equals(obj[h].toString().split(":")[1])) || ("a".equals(obj[h].toString().split(":")[1]))) link = "AND ";
      else if (("O".equals(obj[h].toString().split(":")[1])) || ("o".equals(obj[h].toString().split(":")[1]))) link = "OR "; else
        link = " ";
      if (h == obj.length - 1) link = " ";
      whereName = whereName + col + "=? " + link;
    }

    fieldsName = fieldsName.substring(0, fieldsName.length() - 1);
    String sql = "update " + tableName + " set " + fieldsName + 
      " where " + whereName;

    System.out.println(sql);

    Connection con = null; PreparedStatement pst = null; Object propertyObj = null;
    try
    {
      con = getConnection();
      pst = con.prepareStatement(sql);
      int index = 1;

      for (int j = 0; j < fields.length; j++)
      {
        String str = "get" + fields[j].getName();
        String type = fields[j].getType().toString();
        for (int k = 0; k < methods.length; k++) {
          if (!str.equalsIgnoreCase(methods[k].getName()))
            continue;
          propertyObj = methods[k].invoke(entity, new Object[0]);

          if ((propertyObj == null) || ("null".equals(propertyObj))) {
            propertyObj = " ";
          }
          if ("class java.util.Date".equals(type))
          {
            if (" ".equals(propertyObj)) pst.setNull(index++, 91); else
              pst.setObject(index++, new java.sql.Date(((java.util.Date)propertyObj).getTime()));
          }
          else if ("int".equals(type))
          {
            if (" ".equals(propertyObj)) pst.setNull(index++, 4); else {
              pst.setObject(index++, propertyObj);
            }
          }
          else {
            pst.setObject(index++, propertyObj);
          }
        }

      }

      for (int h = 0; h < obj.length; h++)
      {
        pst.setObject(index++, val[h]);
      }
      pst.executeUpdate();
      con.commit();
      System.out.println("---------------------------------");
    } catch (SQLException ex) {
      ex.printStackTrace();
      try
      {
        pst.close();
        con.close();
      } catch (SQLException ex1) {
        ex1.printStackTrace();
      }
    }
    finally
    {
      try
      {
        pst.close();
        con.close();
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    }
  }

  public void Jdbc_deleteDao(Object entity, Object[] obj)
    throws SQLException, IllegalArgumentException, InvocationTargetException, IllegalAccessException
  {
    Class clazz = entity.getClass();
    Method[] methods = clazz.getDeclaredMethods();
    Field[] fields = clazz.getDeclaredFields();
    String tableName = clazz.getSimpleName().toLowerCase();

    String fieldsName = ""; String whereName = "";
    Object[] val = new Object[obj.length];

    for (int h = 0; h < obj.length; h++)
    {
      String tc = ""; String link = "";
      String col = obj[h].toString().split(":")[0].toUpperCase();
      tc = "get" + col;
      for (int j = 0; j < methods.length; j++) {
        if (tc.equalsIgnoreCase(methods[j].getName())) {
          val[h] = methods[j].invoke(entity, new Object[0]);
        }
      }
      if (("A".equals(obj[h].toString().split(":")[1])) || ("a".equals(obj[h].toString().split(":")[1]))) link = "AND ";
      else if (("O".equals(obj[h].toString().split(":")[1])) || ("o".equals(obj[h].toString().split(":")[1]))) link = "OR "; else
        link = " ";
      if (h == obj.length - 1) link = " ";
      whereName = whereName + col + "=? " + link;
    }

    String sql = "delete from " + tableName + " where " + whereName;

    System.out.println(sql);

    Connection con = null; PreparedStatement pst = null;
    try
    {
      con = getConnection();
      pst = con.prepareStatement(sql);
      int index = 1;
      for (int h = 0; h < obj.length; h++)
      {
        pst.setObject(index++, val[h]);
      }
      pst.executeUpdate();
      con.commit();
      System.out.println("---------------------------------");
    } catch (SQLException ex) {
      ex.printStackTrace();
      try
      {
        pst.close();
        con.close();
      } catch (SQLException ex1) {
        ex1.printStackTrace();
      }
    }
    finally
    {
      try
      {
        pst.close();
        con.close();
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    }
  }

  public void Jdbc_deleteDaoTransaction(Object entity, Object[] obj)
    throws SQLException, IllegalArgumentException, InvocationTargetException, IllegalAccessException
  {
    Class clazz = entity.getClass();
    Method[] methods = clazz.getDeclaredMethods();
    Field[] fields = clazz.getDeclaredFields();
    String tableName = clazz.getSimpleName().toLowerCase();

    String fieldsName = ""; String whereName = "";
    Object[] val = new Object[obj.length];

    for (int h = 0; h < obj.length; h++)
    {
      String tc = ""; String link = "";
      String col = obj[h].toString().split(":")[0].toUpperCase();
      tc = "get" + col;
      for (int j = 0; j < methods.length; j++) {
        if (tc.equalsIgnoreCase(methods[j].getName())) {
          val[h] = methods[j].invoke(entity, new Object[0]);
        }
      }
      if (("A".equals(obj[h].toString().split(":")[1])) || ("a".equals(obj[h].toString().split(":")[1]))) link = "AND ";
      else if (("O".equals(obj[h].toString().split(":")[1])) || ("o".equals(obj[h].toString().split(":")[1]))) link = "OR "; else
        link = " ";
      if (h == obj.length - 1) link = " ";
      whereName = whereName + col + "=? " + link;
    }

    String sql = "delete from " + tableName + " where " + whereName;

    System.out.println(sql);

    Connection con = null; PreparedStatement pst = null;
    try
    {
      con = this.dbAccess.getTransaction();
      if (con != null) {
        pst = con.prepareStatement(sql);
        int index = 1;
        for (int h = 0; h < obj.length; h++)
        {
          pst.setObject(index++, val[h]);
        }
        pst.executeUpdate();

        System.out.println("---------------------------------");
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
      pst.close();
      con.rollback();
      try {
        endTransaction(); } catch (Exception localException) {
      }System.out.println("发生严重错误!事务处理失败!");
      try
      {
        pst.close();
      }
      catch (SQLException ex1) {
        ex1.printStackTrace();
      }
    }
    finally
    {
      try
      {
        pst.close();
      }
      catch (SQLException ex) {
        ex.printStackTrace();
      }
    }
  }

  private int SqlCount(String sql)
    throws Exception
  {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Connection con = null;
    int numCols = 0;
    try
    {
      con = this.dbAccess.getConnection();

      stmt = con.prepareStatement(sql);

      rs = stmt.executeQuery(sql);
      ResultSetMetaData rsmd = rs.getMetaData();
      numCols = rsmd.getColumnCount();
    }
    catch (SQLException e)
    {
      System.out.println(e + "\n\nError sql is:\n" + sql);
      try
      {
        if (rs != null)
          rs.close();
        if (stmt != null)
          stmt.close();
        if (con != null)
          try
          {
            if (!con.isClosed())
              con.close();
          }
          catch (Exception e1)
          {
            System.out.println("链接池关闭失败!");
          }
      }
      catch (SQLException ex)
      {
        ex.printStackTrace();
      }
    }
    finally
    {
      try
      {
        if (rs != null)
          rs.close();
        if (stmt != null)
          stmt.close();
        if (con != null)
          try
          {
            if (!con.isClosed())
              con.close();
          }
          catch (Exception e)
          {
            System.out.println("链接池关闭失败!");
          }
      }
      catch (SQLException ex)
      {
        ex.printStackTrace();
      }
    }

    return numCols;
  }

  public void writeExcelTitle(OutputStream os, String sqle, String[] title)
    throws Exception
  {
    WritableWorkbook wwb = Workbook.createWorkbook(os);
    WritableSheet ws = wwb.createSheet("TestSheet1", 0);

    Label[] t = new Label[title.length];
    for (int k = 0; k < t.length; k++)
    {
      t[k] = new Label(k, 0, title[k]);
      ws.addCell(t[k]);
    }

    sqle = Component.UnicodeToGB2312(sqle);
    Vector sql = getDbAccess().select(sqle);
    int count = SqlCount(sqle);
    Label[] label = new Label[sql.size()];

    for (int i = 0; i < sql.size(); i++)
    {
      for (int j = 0; j < count; j++)
      {
        try
        {
          label[i] = new Label(j, i + 1, ((Vector)sql.elementAt(i)).elementAt(j).toString().trim());
        }
        catch (Exception ex)
        {
          label[i] = new Label(j, i + 1, "");
        }
        ws.addCell(label[i]);
      }

    }

    wwb.write();
    wwb.close();
  }

  public void writeExcel(OutputStream os, String sqle)
    throws Exception
  {
    sqle = Component.UnicodeToGB2312(sqle);
    Vector sql = getDbAccess().select(sqle);
    int count = SqlCount(sqle);
    Label[] label = new Label[sql.size()];

    WritableWorkbook wwb = Workbook.createWorkbook(os);
    WritableSheet ws = wwb.createSheet("TestSheet1", 0);
    for (int i = 0; i < sql.size(); i++)
    {
      for (int j = 0; j < count; j++)
      {
        try
        {
          label[i] = new Label(j, i, ((Vector)sql.elementAt(i)).elementAt(j).toString().trim());
        }
        catch (Exception ex)
        {
          label[i] = new Label(j, i, "");
        }
        ws.addCell(label[i]);
      }

    }

    wwb.write();
    wwb.close();
  }
}