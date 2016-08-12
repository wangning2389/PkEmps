package com;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

public class Util
{
  public static boolean isNullOrEmpty(Object obj)
  {
    return (obj == null) || ("".equals(obj.toString()));
  }
  public static String toString(Object obj) {
    if (obj == null) return "null";
    return obj.toString();
  }
  public static String join(Collection s, String delimiter) {
    StringBuffer buffer = new StringBuffer();
    Iterator iter = s.iterator();
    while (iter.hasNext()) {
      buffer.append(iter.next());
      if (iter.hasNext()) {
        buffer.append(delimiter);
      }
    }
    return buffer.toString();
  }
  public static String Encode(Object obj) {
    if ((obj == null) || (obj.toString().equals("null"))) return null;
    if ((obj != null) && (obj.getClass() == String.class)) {
      return obj.toString();
    }
    JSONSerializer serializer = new JSONSerializer();
    serializer.transform(new DateTransformer("yyyy-MM-dd'T'HH:mm:ss"), new Class[] { Date.class });
    serializer.transform(new DateTransformer("yyyy-MM-dd'T'HH:mm:ss"), new Class[] { Timestamp.class });
    return serializer.deepSerialize(obj);
  }
  public static Object Decode(String json) {
    if (isNullOrEmpty(json)) return "";
    JSONDeserializer deserializer = new JSONDeserializer();

    Object obj = deserializer.deserialize(json);
    if ((obj != null) && (obj.getClass() == String.class)) {
      return Decode(obj.toString());
    }
    return obj;
  }

  public static String initcap(String str)
  {
    char[] ch = str.toCharArray();
    if ((ch[0] >= 'a') && (ch[0] <= 'z')) {
      ch[0] = (char)(ch[0] - ' ');
    }

    return new String(ch);
  }

  public static Object MapToVo(Object o, HashMap map)
    throws Exception
  {
    Class clazz = o.getClass();

    Field[] fields = clazz.getDeclaredFields();

    for (int i = 0; i < fields.length; i++)
    {
      String getFieldName = "set" + fields[i].getName();
      String name = String.valueOf(fields[i].getName());
      String datalx = String.valueOf(fields[i].getType());

      String val = null;
      try
      {
        val = map.get(name.toLowerCase()).toString();
        if ((val == null) || ("".equals(val))) val = map.get(name.toUpperCase()).toString(); 
      }
      catch (Exception f)
      {
        val = null;
      }

      if ((val == null) || ("".equals(val)))
      {
        continue;
      }

      try
      {
        clazz.getMethod(getFieldName, new Class[] { fields[i].getType() }).invoke(o, new Object[] { val });
      }
      catch (Exception f)
      {
        try {
          int v = Integer.parseInt(val);
          clazz.getMethod(getFieldName, new Class[] { fields[i].getType() }).invoke(o, new Object[] { Integer.valueOf(v) });
        }
        catch (Exception fs)
        {
          try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date1 = new Date();
            date1 = sdf.parse(val.replaceAll("T", " "));
            clazz.getMethod(getFieldName, new Class[] { fields[i].getType() }).invoke(o, new Object[] { date1 });
          }
          catch (Exception localException1)
          {
          }

        }

      }

    }

    return o;
  }

  public static String getObject(Object[] obj)
  {
    String flag = "0";

    for (int i = 0; i < obj.length; i++)
    {
      if ((!"".equals(obj[i])) && (obj[i] != null)) {
        flag = "1"; break;
      }
    }
    return flag;
  }
}