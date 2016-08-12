package core.exception.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;


import core.exception.BusinessException;
import core.exception.JSONException;
import core.exception.ParameterException;
import core.utils.AjaxUtil;
import core.utils.JSONUtil;


public class CustomExceptionHandler extends SimpleMappingExceptionResolver
		implements HandlerExceptionResolver {

//	private ExceptionLogService exceptionLogService;

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		String exMsg = null;
		// 异常分类
		if (ex instanceof BusinessException || ex instanceof JSONException
				|| ex instanceof ParameterException) {
			exMsg = ex.getMessage();
		} else {
			exMsg = "系统错误！";

		}
		ex.printStackTrace();
		if (!AjaxUtil.isAjaxRequest(request)) {
			// 非异步请求
			if(ex instanceof BusinessException && (BusinessException.LOGON_EXC).equals(((BusinessException) ex).getType())){//登陆拦截器异常
				return new ModelAndView("logon");//返回登录页
			}else{//普通业务异常
			return new ModelAndView("error").addObject("exMsg", exMsg);//访问error.jsp页面
			}
		} else {
			// 异步请求
			
			try {
				PrintWriter writer = response.getWriter();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("success", false);
				map.put("exMsg", exMsg);
				if(ex instanceof BusinessException && (BusinessException.LOGON_EXC).equals(((BusinessException) ex).getType())){
					map.put("exType", ((BusinessException) ex).getType());
					
				}
				response.setStatus(306);
				writer.write(JSONUtil.map2JsonStr(map));
				writer.flush();
				return new ModelAndView();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return new ModelAndView();
		}
	}

}
