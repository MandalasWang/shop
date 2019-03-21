package com.wang.web.filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wang.domain.user;
import com.wang.service.Userservice;

/**
 * Servlet Filter implementation class Autologin
 */
@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }
					, urlPatterns = { "/autologinF" })
public class Autologin implements Filter {

    /**
     * Default constructor. 
     */
    public Autologin() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
        //强制转换filter中的request、response 
		HttpServletRequest req = (HttpServletRequest)request;
         HttpServletResponse res = (HttpServletResponse)response;
         //获取cookie
         Cookie[] cookies = req.getCookies();
         String cookie_username = null;
         String cookie_password = null;
         if(cookies!=null){
        	 for(Cookie cookie : cookies){
        		 if("cookie_username".equals(cookie.getName())){
        			 cookie_username = cookie.getValue();
        			
        		 }
        		 if("cookie_password".equals(cookie.getName())){
        			 cookie_password = cookie.getValue();
        			 
        		 }
        	 }
         }
         //判断username、password是否为空
         if(cookie_username!=null&&cookie_password!=null){
        	 Userservice userservice = new Userservice();
        	 user user =null;
        	 try {
				user = userservice.checkLogin(cookie_username, cookie_password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	 //将登陆成功的用户存入seesion中
        	 
        	 
         }
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
