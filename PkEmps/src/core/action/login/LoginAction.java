package core.action.login;

import java.util.HashMap;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.constants.UserConstant;
import core.services.login.LoginServices;
import core.vo.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.*;




@Controller
@RequestMapping("/core/login.do")
public class LoginAction {
	
	LoginServices ls=new LoginServices();
	PublicSystem sys=PublicSystem.getInstance();
	
	@RequestMapping(params = "method=login1")
    public void Login(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		//取json数据转换
    	HashMap map = (HashMap)Util.Decode(request.getParameter("submitData"));
    	//登录用户session
    	HttpSession session = request.getSession();
        session.setAttribute("userid", (String)map.get("username"));
        //返回处理结果
        Component.print(ls.getUser(map),response);
    
    }
	
//	@RequestMapping(params = "method=toFrame")
//    public String toFrame(HttpServletRequest request, HttpServletResponse response) throws Exception
//    {
//		return "frames/main";
//    }
	
	/**
	 * 用户注销
	 * 
	 * @return
	 */
	@RequestMapping(params = "method=logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
        session.setAttribute("userid", null);
        String idreturn="";
        Component.print(idreturn, response);
	}
	
	@RequestMapping(params = "method=ForWard")
    public void ForWard(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String admin = (String) request.getSession().getAttribute("userid");
		String id = Component.getQuest("id",request);
		
			if (admin == null) {
				Component.messageUrl("登录已过期，请重新登陆", "/"+Const.project, response);
			} else {
				request.getRequestDispatcher(ls.getUrl(id))
						.forward(request, response);
			}
    }
   

    
}
