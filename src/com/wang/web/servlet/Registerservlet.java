package com.wang.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.wang.domain.user;
import com.wang.service.Userservice;
import com.wang.utils.CommonUtils;
import com.wang.utils.MailUtils;

@WebServlet("/register")
public class Registerservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Registerservlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		// 获取传递来的数据
		Map<String, String[]> properties = request.getParameterMap();
		user user = new user();
		try {
			BeanUtils.populate(user, properties);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		// 手动封装其余属性
		user.setUid(CommonUtils.getUUid());
		user.setState(0);
		String activecode = CommonUtils.getUUid();
		user.setCode(activecode);
		
		String checkcode= request.getParameter("checkCode");
		
		String code = (String) request.getSession().getAttribute("checkcode_session");
		//System.out.println(checkcode.equals(code));
		// 将封装好的数据传递给下一级service
		Userservice userservice = new Userservice();
		boolean isregsuccess = false;
	  if(!checkcode.equals(code)){
		  request.getRequestDispatcher("register.jsp").forward(request, response);
	  }
		try {
			isregsuccess = userservice.register(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 判断注册成功
		if (isregsuccess) {
			// 发送邮件,并且点击相应链接返回激活业务
			String emailMsg = "恭喜您注册成功，请点击下面的连接进行激活账户"
					+ "<a href='http://localhost:8080/Shop/checkactive?activecode="+activecode+"'>"
							+ "点我"+"</a>";
			try {
				MailUtils.sendMail(user.getEmail(), emailMsg);
			} catch (AddressException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			// 激活成功跳转到相应页面
			response.sendRedirect(this.getServletContext().getContextPath() + "/registerSuccess.jsp");
		} else {
			request.getRequestDispatcher("/registerFail.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
