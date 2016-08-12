package core.action.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import core.exception.BusinessException;




public class LoginFilter implements Filter{

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		String url = req.getServletPath();
		if(!"/page/logon.jsp".equals(url) && !"/index.jsp".equals(url) && !"/page/error.jsp".equals(url)&&!"/page/frames/welcome.jsp".equals(url)){
//			User user = (User) session.getAttribute(UserConstant.USER_IN_SESSION);
			String user = (String) session.getAttribute("userid");
			if (!StringUtils.isNotBlank(user)) {
				request.setAttribute("EX_TYPE", BusinessException.EX_TYPE_FILTER_LOGON);
				request.setAttribute("exMsg", "请登录后进行操作！");
				request.getRequestDispatcher("/page/error.jsp").forward(request, response);				
				return;
			} 
		}		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
