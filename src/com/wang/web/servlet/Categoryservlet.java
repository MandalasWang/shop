package com.wang.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wang.domain.Category;
import com.wang.service.Categoryservice;

/**
 * Servlet implementation class Categoryservlet
 */
@WebServlet("/category")
public class Categoryservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Categoryservlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Categoryservice categoryservice = new Categoryservice();
		// 获取导航条信息
		List<Category> categories = categoryservice.getcategory();
		// 存入request域中
		request.getServletContext().setAttribute("categories", categories);
		request.getRequestDispatcher("index.jsp").forward(request, response);
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
