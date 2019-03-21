package com.wang.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wang.service.Userservice;

/**
 * Servlet implementation class Activeservlet
 */
@WebServlet("/checkactive")
public class Activeservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public Activeservlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//��ȡactivecode
		response.setContentType("text/html;charset=UTF-8");
		String activecode = request.getParameter("activecode");
		//�����ݴ��ݸ�service��
		Userservice userservice = new Userservice();
		boolean isactive = userservice.active(activecode);
		
		if(isactive){
			//��ת����½����
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}else{
			response.getWriter().write("�˻�����ʧ�ܣ�");
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
