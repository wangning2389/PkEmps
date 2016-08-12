package core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import core.constants.UserConstant;
import core.exception.BusinessException;
import core.vo.User;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	 
	  private static final String LOGIN_URL = "/core/login.do";  
	  
	    @Override  
	    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {  
	        HttpSession session = req.getSession(true);  
	        // 从session 里面获取用户名的信息  
	        String url = req.getServletPath();
	        String user = (String) session.getAttribute("userid");
	        if(!LOGIN_URL.equals(url)){
			if (!StringUtils.isNotBlank(user)) {
				throw new BusinessException(BusinessException.LOGON_EXC,"请登录后进行操作！");
			}
	        }
	        return true; 
	    }  
	  
	    @Override  
	    public void postHandle(HttpServletRequest req, HttpServletResponse res, Object arg2, ModelAndView arg3) throws Exception {  
	    }  
	  
	    @Override  
	    public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object arg2, Exception arg3) throws Exception {  
	    }  
	  
	}

