package com.wang.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wang.domain.user;
import com.wang.service.Userservice;

/**
 * Servlet implementation class Loginservlet
 */
@WebServlet("/login")
public class Loginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Loginservlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//获取前端传递的登陆数据
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String checkcode = request.getParameter("checkcode");
		String autologin = request.getParameter("autologin");
		String remenberusername = request.getParameter("remenberusername");
		//把传来的数据传给下一层service层	
		String code = (String) request.getSession().getAttribute("checkcode_session");
		Userservice userservice = new Userservice();
		user user = new user();
		try {
			user = userservice.checkLogin(username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		int state = user.getState();
		if(user!=null&&checkcode.equals(code)){
			//登录成功后判断是否点击自动登录
			if(autologin!=null){
				Cookie cookie_username = new Cookie("Cookie_username", user.getUsername());
				Cookie cookie_password = new Cookie("Cookie_password", user.getPassword());
				cookie_username.setMaxAge(60*60*60*72);
				cookie_password.setMaxAge(60*60*60*72);
				cookie_username.setPath(request.getContextPath());
				cookie_password.setPath(request.getContextPath());
				response.addCookie(cookie_username);
				response.addCookie(cookie_password);
			}else if(remenberusername!=null){
				Cookie cookie_username = new Cookie("Cookie_username", user.getUsername());
				cookie_username.setMaxAge(60*60*60*72);
				cookie_username.setPath(request.getContextPath());
				response.addCookie(cookie_username);
			}
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setMaxInactiveInterval(900);
			response.sendRedirect(request.getContextPath()+"/index");
		}else{
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
