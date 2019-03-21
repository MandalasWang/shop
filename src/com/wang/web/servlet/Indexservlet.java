package com.wang.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wang.domain.Category;
import com.wang.domain.product;
import com.wang.service.Categoryservice;
import com.wang.service.Productservice;
import com.wang.service.Userservice;

/**
 * Servlet implementation class Indexservlet
 */
@WebServlet("/index")
public class Indexservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Indexservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Productservice productservice = new Productservice();
		
		//获取热门商品
		List<product> hotProducts = productservice.findHotProducts();
		//获取最新商品
		List<product> newproducts = productservice.findNewProducts();
		
		request.setAttribute("hotProducts", hotProducts);
		request.setAttribute("newproducts", newproducts);
		try {
			Productservlet.class.newInstance().category(request, response);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//request.getRequestDispatcher("/category").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
