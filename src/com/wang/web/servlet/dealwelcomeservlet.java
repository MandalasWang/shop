package com.wang.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wang.domain.Administrantor;
import com.wang.service.Userservice;

/**
 * Servlet implementation class dealwelcomeservlet
 */
@WebServlet("/dealwelcome")
public class dealwelcomeservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public dealwelcomeservlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("username");
		String pass = request.getParameter("password");
		Userservice userservice = new Userservice();
		Administrantor administrantor = userservice.checkadminloginBynameAndpass(name,pass);
		if(administrantor!=null){
		request.getSession().setAttribute("user", administrantor);
		request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("/admin/index.jsp").forward(request, response);

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
