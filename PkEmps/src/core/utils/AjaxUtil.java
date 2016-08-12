package core.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * AJAX工具类
 * 
 * @author wubin
 * 
 */
public class AjaxUtil {

	/**
	 * 是否为异步请求
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		String isAjaxStr = request.getHeader("X-Requested-With");
		if (isAjaxStr == null || isAjaxStr.trim().equals("")) {
			return false;
		}
		return true;
	}

}
