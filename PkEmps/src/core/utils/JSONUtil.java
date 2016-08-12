package core.utils;

import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.apache.commons.lang.StringUtils;

import core.exception.JSONException;



/**
 * JSON工具类
 * 
 * @author {李新}
 * 
 */
public final class JSONUtil {
//	private static Logger logger = LogManager.getLogger(JSONUtil.class);

	/**
	 * json字符串转换成对象
	 * 
	 * @param jsonStr
	 * @param obj
	 * @return
	 * @throws JSONException
	 */
	public static Object jsonStr2Object(String jsonStr, Class<?> obj)
			throws JSONException {
		if (StringUtils.isBlank(jsonStr) || null == obj) {
			return null;
		}

		Object returnObj = null;
		try {
			JSONObject jsonObj = JSONObject.fromObject(jsonStr);

			returnObj = JSONObject.toBean(jsonObj, obj);
		} catch (Exception e) {
			String errMsg = "JSON字符串转换成对象出错，JSON字符串：" + jsonStr + ", 目标对象："
					+ obj.getName();
//			logger.error(errMsg, e);
			throw new JSONException("JSON字符串转换成对象出错");
		}

		return returnObj;

	}

	/**
	 * Map对象转换成JSON字符串
	 * 
	 * @param map
	 * @return
	 */
	public static String map2JsonStr(Map<String, Object> map) {
		if (null == map || map.isEmpty()) {
			return StringUtils.EMPTY;
		}
		String resultStr = StringUtils.EMPTY;
		JSONObject jsonObj = JSONObject.fromObject(map);
		resultStr = JSONUtils.valueToString(jsonObj);
		return resultStr;
	}

	/**
	 * 对象转换成JSON字符串，适用于对象属性都为原子类型
	 * 
	 * @param obj
	 * @return
	 */
	public static String bean2JsonStr(Object obj) {
		if (null == obj) {
			return null;
		}

		JSONObject jsonObj = JSONObject.fromObject(obj);
		String resultStr = JSONUtils.valueToString(jsonObj);
		return resultStr;
	}

}
