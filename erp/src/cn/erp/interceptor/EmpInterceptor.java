package cn.erp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.erp.pojo.TblEmp;
import cn.erp.util.session.Constants;

public class EmpInterceptor implements HandlerInterceptor{

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	     String url = request.getRequestURI();  
	       
	      //判断是否是公开地址  
	      //实际开发中需要公开地址配置在配置文件中  
	      if(url.indexOf("/login.do")>=0){  
	         //如果是公开地址则放行  
	         return true;  
	      }  
	      //判断用户身份在session中是否存在  
	      HttpSession session = request.getSession();  
	      TblEmp emp= (TblEmp) session.getAttribute(Constants.tblEmp_SESSION);  
	      //如果用户身份在session中存在放行  
	      if(emp!=null){  
	         return true;  
	      }  
	      //执行到这里拦截，跳转到登陆页面，用户进行身份认证  
	      request.getRequestDispatcher("/index3.jsp").forward(request, response);  
	      //如果返回false表示拦截不继续执行handler，如果返回true表示放行  
	      return false;  
	}

}
