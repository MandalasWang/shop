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

		// ��ȡ������������
		Map<String, String[]> properties = request.getParameterMap();
		user user = new user();
		try {
			BeanUtils.populate(user, properties);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		// �ֶ���װ��������
		user.setUid(CommonUtils.getUUid());
		user.setState(0);
		String activecode = CommonUtils.getUUid();
		user.setCode(activecode);
		
		String checkcode= request.getParameter("checkCode");
		
		String code = (String) request.getSession().getAttribute("checkcode_session");
		//System.out.println(checkcode.equals(code));
		// ����װ�õ����ݴ��ݸ���һ��service
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
		
		// �ж�ע��ɹ�
		if (isregsuccess) {
			// �����ʼ�,���ҵ����Ӧ���ӷ��ؼ���ҵ��
			String emailMsg = "��ϲ��ע��ɹ���������������ӽ��м����˻�"
					+ "<a href='http://localhost:8080/Shop/checkactive?activecode="+activecode+"'>"
							+ "����"+"</a>";
			try {
				MailUtils.sendMail(user.getEmail(), emailMsg);
			} catch (AddressException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			// ����ɹ���ת����Ӧҳ��
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
