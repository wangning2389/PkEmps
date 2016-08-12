package com;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Component
{
  public static String getQuest(String str, HttpServletRequest request)
  {
    if ((request.getParameter(str) == null) || ("".equals(request.getParameter(str)))) return "";
    return request.getParameter(str).replaceAll(".*([';]+|(--)+).*", " ");
  }

  public static String getSession(String name, HttpServletRequest request)
  {
    return (String)request.getSession().getAttribute(name);
  }

  public static String gbEncoding(String gbString)
  {
    char[] utfBytes = gbString.toCharArray();
    String unicodeBytes = "";
    for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
      String hexB = Integer.toHexString(utfBytes[byteIndex]);
      if (hexB.length() <= 2) {
        hexB = "00" + hexB;
      }
      unicodeBytes = unicodeBytes + "@" + hexB;
    }

    return unicodeBytes;
  }

  public static String UnicodeToGB2312(String str)
  {
    String res = null;
    StringBuffer sb = new StringBuffer();
    try
    {
      while (str.length() > 0) {
        if (str.startsWith("@"))
        {
          int x = Integer.parseInt(str.substring(1, 5), 16);
          sb.append((char)x);
          str = str.substring(5);
        }
        else {
          sb.append(str.charAt(0));
          str = str.substring(1);
        }
      }
      res = sb.toString();
    }
    catch (Exception e)
    {
      System.err.println("in UnicodeToGB2312:" + e.toString());
    }

    return res;
  }

  public static String decodeBase64(byte[] data)
  {
    int len = data.length;
    StringBuffer ret = new StringBuffer(len * 3 / 4);
    for (int i = 0; i < len; i++)
    {
      int c = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(data[i]);
      i++;
      int c1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(data[i]);
      c = c << 2 | c1 >> 4 & 0x3;
      ret.append((char)c);
      i++; if (i < len)
      {
        c = data[i];
        if (61 == c)
          break;
        c = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf((char)c);
        c1 = c1 << 4 & 0xF0 | c >> 2 & 0xF;
        ret.append((char)c1);
      }
      i++; if (i >= len)
        continue;
      c1 = data[i];
      if (61 == c1)
        break;
      c1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf((char)c1);
      c = c << 6 & 0xC0 | c1;
      ret.append((char)c);
    }

    return ret.toString();
  }

  public static String encodeBase64(byte[] data) {
    int len = data.length;
    StringBuffer ret = new StringBuffer((len / 3 + 1) * 4);
    for (int i = 0; i < len; i++)
    {
      int c = data[i] >> 2 & 0x3F;
      ret.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(c));
      c = data[i] << 4 & 0x3F;
      i++; if (i < len)
        c |= data[i] >> 4 & 0xF;
      ret.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(c));
      if (i < len)
      {
        c = data[i] << 2 & 0x3F;
        i++; if (i < len)
          c |= data[i] >> 6 & 0x3;
        ret.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(c));
      }
      else {
        i++;
        ret.append('=');
      }
      if (i < len)
      {
        c = data[i] & 0x3F;
        ret.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(c));
      }
      else {
        ret.append('=');
      }
    }

    return ret.toString();
  }

  public void message(String nr, String lx, HttpServletResponse response)
    throws Exception
  {
    PrintWriter out = response.getWriter();

    if ("0".equals(lx))
    {
      out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
      out.println("alert(\"" + nr + "!\\n点击确认返回!\");");
      out.println("javascript:history.back();");
      out.println("</SCRIPT>");
      out.flush();
      out.close();
    }
    else if ("1".equals(lx))
    {
      out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
      out.println("alert(\"" + nr + "!\\n点击确认关闭!\");");
      out.println("javascript:window.close();");
      out.println("</SCRIPT>");
      out.flush();
      out.close();
    }
  }

  public static void messageUrl(String nr, String url, HttpServletResponse response)
    throws Exception
  {
    PrintWriter out = response.getWriter();
    out.print("<script>alert('" + nr + "')</script>" + 
      "<meta http-equiv='refresh' content='0; url=" + url + "'>");
  }

  public static void messageUrlFrme(String nr, String url, HttpServletResponse response)
    throws Exception
  {
    PrintWriter out = response.getWriter();
    out.print("<script>alert('" + nr + "');window.parent.location='" + url + "';</script>");
  }

  public static void print(Object o, HttpServletResponse response)
    throws Exception
  {
    PrintWriter out = response.getWriter();
    out.print(o);
  }

  public static String systime()
    throws Exception
  {
    Date de = new Date();
    DateFormat dated = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
    String s_time = dated.format(de);

    String id_temp = s_time.substring(0, 4) + '-' + s_time.substring(5, 7) + '-' + s_time.substring(8, 10);
    return id_temp;
  }

  public static String systime_all()
    throws Exception
  {
    Date de = new Date();
    DateFormat dated = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String s_time = dated.format(de);
    return s_time;
  }

  public static String Sumtime(String ssj, String dsj)
    throws Exception
  {
    String time = "";
    long quot = 0L;
    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
    try
    {
      Date date1 = ft.parse(dsj);
      Date date2 = ft.parse(ssj);
      quot = date1.getTime() - date2.getTime();
      quot = quot / 1000L / 60L / 60L / 24L;
      time = String.valueOf(quot);
    }
    catch (Exception e) {
      time = "时间逻辑有误";
    }
    time = String.valueOf(Integer.parseInt(time) + 1);

    return time;
  }

  public static String getTimeAction(String rq1, String rq2)
    throws Exception
  {
    String res = "";
    try
    {
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Date now = df.parse(rq1);
      Date date = df.parse(rq2);
      long l = now.getTime() - date.getTime();
      long day = l / 86400000L;
      long hour = l / 3600000L - day * 24L;
      long min = l / 60000L - day * 24L * 60L - hour * 60L;
      long s = l / 1000L - day * 24L * 60L * 60L - hour * 60L * 60L - min * 60L;
      res = Math.abs(day) + "天" + Math.abs(hour) + "时" + Math.abs(min) + "分" + Math.abs(s) + "秒";
    }
    catch (Exception ex)
    {
      res = "0";
    }

    return res;
  }

  public static boolean is_FriDay()
  {
    Date de = new Date();
    Calendar ca = Calendar.getInstance();
    ca.setTime(de);
    System.out.println(ca.get(7));

    return (ca.get(7) == 6) || (ca.get(7) == 7) || (ca.get(7) == 1);
  }

  public static boolean isValidDate(String str)
  {
    boolean convertSuccess = true;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try {
      format.setLenient(false);
      format.parse(str);
    } catch (Exception e) {
      convertSuccess = false;
    }
    return convertSuccess;
  }
}