package com.wang.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wang.service.Userservice;

/**
 * Servlet implementation class CheckUsernamesevlet
 */
@WebServlet("/checkUsername")
public class CheckUsernamesevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CheckUsernamesevlet() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//��ȡregister������username���ݽ����첽У��
		String username = request.getParameter("username");
		//�����ݴ��ݸ�service��
		Userservice userservice = new Userservice();
		boolean usernameisext = false;
		try {
			usernameisext = userservice.checkusername(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String json = "{\"isExist\":"+usernameisext+"}";
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
