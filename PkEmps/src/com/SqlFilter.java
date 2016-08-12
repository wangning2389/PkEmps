package com;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SqlFilter
  implements Filter
{
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws IOException, ServletException
  {
    HttpServletRequest req = (HttpServletRequest)request;
    HttpServletResponse res = (HttpServletResponse)response;

    req.setCharacterEncoding("utf-8");
    res.setContentType("text/html;charset=utf-8");

    Enumeration params = req.getParameterNames();
    String sql = "";
    while (params.hasMoreElements())
    {
      String name = params.nextElement().toString();

      String[] value = req.getParameterValues(name);
      for (int i = 0; i < value.length; i++) {
        sql = sql + value[i];
      }

    }

    if (sqlValidate(sql)) {
      throw new IOException("IP地址" + req.getRemoteAddr() + "发送请求中的参数中含有非法字符");
    }

    chain.doFilter(req, res);
  }

  protected static boolean sqlValidate(String str)
  {
    str = str.toLowerCase().trim();

    String badStr = "";
    badStr = "'|exec|execute|drop|grant|group_concat|information_schema.columns|table_schema|union|where|*|chr|master|truncate|declare|;|--|like|%";

    if ("".equals(str)) return false;

    String[] badStrs = badStr.split("\\|");

    for (int i = 0; i < badStrs.length; i++)
    {
      if (str.indexOf(badStrs[i]) >= 0) {
        System.out.println("验证字符为=========" + str);
        System.out.println("非法字符为=========" + badStrs[i]);
        return true;
      }
    }
    return false;
  }

  public void destroy()
  {
  }

  public void init(FilterConfig arg0)
    throws ServletException
  {
  }
}