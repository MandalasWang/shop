package com.wang.web.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.wang.domain.Cart;
import com.wang.domain.CartItem;
import com.wang.domain.Category;
import com.wang.domain.OrderItems;
import com.wang.domain.Orders;
import com.wang.domain.product;
import com.wang.domain.user;
import com.wang.service.Categoryservice;
import com.wang.service.Productservice;
import com.wang.service.orderservice;
import com.wang.vo.PageBean;

@WebServlet("/product")
public class Productservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Productservlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��������ת��
		request.setCharacterEncoding("UTF-8");
		//�ش����������չʾ����
		response.setContentType("text/html;charset=UTF-8");
		String methodName = request.getParameter("method");
		if ("category".equals(methodName)) {
			category(request, response);
		} else if ("index".equals(methodName)) {
			index(request, response);
		} else if ("product_list".equals(methodName)) {
			product_list(request, response);
		} else if ("product_info".equals(methodName)) {
			product_info(request, response);
		} else if ("cart".equals(methodName)) {
			cart(request, response);
		} else if ("submitOrder".equals(methodName)) {
			submitOrder(request, response);
		} else if ("confirmOrder".equals(methodName)) {
			 confirmOrder(request, response);		
		} else if ("clearCart".equals(methodName)) {
			clearCart(request, response);
		} else if ("delProFromCart".equals(methodName)) {
			delProFromCart(request, response);
		} else if ("searchword".equals(methodName)) {
			List<Object> list = searchword(request, response);
			Gson gson = new Gson();
			String json = gson.toJson(list);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(json);
		} else if ("searchBypname".equals(methodName)) {
			searchBypname(request, response);
			request.getRequestDispatcher("/product_info.jsp").forward(request, response);
		} else if ("getmobileBypname".equals(methodName)) {
			getmobileBypname(request, response);
			request.getRequestDispatcher("/product_list.jsp").forward(request, response);
		} else if ("getmobileByPrice".equals(methodName)) {
			getmobileByPrice(request, response);
			request.getRequestDispatcher("/product_list.jsp").forward(request, response);
		} else if ("getConputerBypname".equals(methodName)) {
			getConputerBypname(request, response);
			request.getRequestDispatcher("/product_list.jsp").forward(request, response);
		} else if ("getcomputerByPrice".equals(methodName)) {
			getcomputerByPrice(request, response);
			request.getRequestDispatcher("/product_list.jsp").forward(request, response);
		} else if ("getTabletPCBypname".equals(methodName)) {
			getTabletPCBypname(request, response);
			request.getRequestDispatcher("/product_list.jsp").forward(request, response);
		} else if ("getTabletPCBytype".equals(methodName)) {
			getTabletPCBytype(request, response);
			request.getRequestDispatcher("/product_list.jsp").forward(request, response);
		} else if ("getTabletPCByprice".equals(methodName)) {
			getTabletPCByprice(request, response);
			request.getRequestDispatcher("/product_list.jsp").forward(request, response);
		} else if ("getSoudBypname".equals(methodName)) {
			getSoudBypname(request, response);
			request.getRequestDispatcher("/product_list.jsp").forward(request, response);
		} else if ("getSoudByprice".equals(methodName)) {
			getSoudByprice(request, response);
			request.getRequestDispatcher("/product_list.jsp").forward(request, response);
		} else if("getMyorder_list".equals(methodName)){
			try {
				getMyorder_list(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("/order_list.jsp").forward(request, response);
		}
	}

	private void getMyorder_list(HttpServletRequest request, HttpServletResponse response) throws Exception 
			  {
		request.setCharacterEncoding("UTF-8");
		String uid = request.getParameter("uid");
		//System.out.println(uid);
		orderservice orderservice = new orderservice();
		Productservice service = new Productservice();
		List<Orders> list = orderservice.getorderlistByuid(uid);
		//System.out.println(list);
		if(list==null){
			response.getWriter().write("����δ�µ�,��Ͽ�ȥ�µ��ɣ�");
		}
		Orders order = new Orders();
		for(Orders orders : list){
			@SuppressWarnings("static-access")
			List<OrderItems> list2 = orderservice.getorderitemsByoid(orders.getOid());				
			for(OrderItems items : list2){
				items.setOrder(orders);	
				product product = service.getpidByitem(orders.getOid());
				//System.out.println(pid);
			    items.setProduct(product);
				order.getOrderItems().add(items);
				
			}	
			
		}
		request.setAttribute("order", order);
	}

	private void getSoudByprice(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		String price = request.getParameter("price");
		String cid = request.getParameter("cid");
		String currentPageStr = request.getParameter("currentPage");
		if (currentPageStr == null)
			currentPageStr = "1";
		int currentPage = Integer.parseInt(currentPageStr);
		int currentCount = 12;
		Productservice productservice = new Productservice();
		PageBean<product> pageBean = new PageBean<product>();
		pageBean = productservice.getSoudByprice(price, cid, currentPage, currentCount);
		// System.out.println(price+".."+list+cid);
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("cid", cid);
		// ����һ����¼��ʷ��Ʒ��Ϣ�ļ���
		List<product> historyProductList = new ArrayList<product>();

		// ��ÿͻ���Я�����ֽ�pids��cookie
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("pids".equals(cookie.getName())) {
					String pids = cookie.getValue();// 3-2-1
					String[] split = pids.split("-");
					for (String pid : split) {
						product pro = productservice.findProductByPid(pid);
						historyProductList.add(pro);
					}
				}
			}
		}
		// ����ʷ��¼�ļ��Ϸŵ�����
		request.setAttribute("historyProductList", historyProductList);

	}

	private void getSoudBypname(HttpServletRequest request, HttpServletResponse response) {
		String pname = request.getParameter("pname");
		String cid = request.getParameter("cid");
		String currentPageStr = request.getParameter("currentPage");
		if (currentPageStr == null)
			currentPageStr = "1";
		int currentPage = Integer.parseInt(currentPageStr);
		int currentCount = 12;
		Productservice productservice = new Productservice();
		PageBean<product> pageBean = new PageBean<product>();
		pageBean = productservice.getSoudBypname(pname, cid, currentPage, currentCount);
		// System.out.println(pname+".."+list+cid);

		request.setAttribute("pageBean", pageBean);
		request.setAttribute("cid", cid);
		// ����һ����¼��ʷ��Ʒ��Ϣ�ļ���
		List<product> historyProductList = new ArrayList<product>();

		// ��ÿͻ���Я�����ֽ�pids��cookie
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("pids".equals(cookie.getName())) {
					String pids = cookie.getValue();// 3-2-1
					String[] split = pids.split("-");
					for (String pid : split) {
						product pro = productservice.findProductByPid(pid);
						historyProductList.add(pro);
					}
				}
			}
		}

		// ����ʷ��¼�ļ��Ϸŵ�����
		request.setAttribute("historyProductList", historyProductList);

	}

	private void getTabletPCByprice(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		String price = request.getParameter("price");
		String cid = request.getParameter("cid");
		String currentPageStr = request.getParameter("currentPage");
		if (currentPageStr == null)
			currentPageStr = "1";
		int currentPage = Integer.parseInt(currentPageStr);
		int currentCount = 12;
		Productservice productservice = new Productservice();
		PageBean<product> pageBean = new PageBean<product>();
		pageBean = productservice.getTabletPCByprice(price, cid, currentPage, currentCount);
		// System.out.println(price+".."+list+cid);
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("cid", cid);
		// ����һ����¼��ʷ��Ʒ��Ϣ�ļ���
		List<product> historyProductList = new ArrayList<product>();

		// ��ÿͻ���Я�����ֽ�pids��cookie
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("pids".equals(cookie.getName())) {
					String pids = cookie.getValue();// 3-2-1
					String[] split = pids.split("-");
					for (String pid : split) {
						product pro = productservice.findProductByPid(pid);
						historyProductList.add(pro);
					}
				}
			}
		}
		// ����ʷ��¼�ļ��Ϸŵ�����
		request.setAttribute("historyProductList", historyProductList);

	}

	private void getTabletPCBytype(HttpServletRequest request, HttpServletResponse response) {
		String pname = request.getParameter("pname");
		String cid = request.getParameter("cid");
		String currentPageStr = request.getParameter("currentPage");
		if (currentPageStr == null)
			currentPageStr = "1";
		int currentPage = Integer.parseInt(currentPageStr);
		int currentCount = 12;
		Productservice productservice = new Productservice();
		PageBean<product> pageBean = new PageBean<product>();
		pageBean = productservice.getTabletPCBypname(pname, cid, currentPage, currentCount);
		// System.out.println(pname+".."+list+cid);

		request.setAttribute("pageBean", pageBean);
		request.setAttribute("cid", cid);
		// ����һ����¼��ʷ��Ʒ��Ϣ�ļ���
		List<product> historyProductList = new ArrayList<product>();

		// ��ÿͻ���Я�����ֽ�pids��cookie
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("pids".equals(cookie.getName())) {
					String pids = cookie.getValue();// 3-2-1
					String[] split = pids.split("-");
					for (String pid : split) {
						product pro = productservice.findProductByPid(pid);
						historyProductList.add(pro);
					}
				}
			}
		}

		// ����ʷ��¼�ļ��Ϸŵ�����
		request.setAttribute("historyProductList", historyProductList);

	}

	private void getTabletPCBypname(HttpServletRequest request, HttpServletResponse response) {
		String pname = request.getParameter("pname");
		String cid = request.getParameter("cid");
		String currentPageStr = request.getParameter("currentPage");
		if (currentPageStr == null)
			currentPageStr = "1";
		int currentPage = Integer.parseInt(currentPageStr);
		int currentCount = 12;
		Productservice productservice = new Productservice();
		PageBean<product> pageBean = new PageBean<product>();
		pageBean = productservice.getTabletPCBypname(pname, cid, currentPage, currentCount);
		// System.out.println(pname+".."+list+cid);

		request.setAttribute("pageBean", pageBean);
		request.setAttribute("cid", cid);
		// ����һ����¼��ʷ��Ʒ��Ϣ�ļ���
		List<product> historyProductList = new ArrayList<product>();

		// ��ÿͻ���Я�����ֽ�pids��cookie
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("pids".equals(cookie.getName())) {
					String pids = cookie.getValue();// 3-2-1
					String[] split = pids.split("-");
					for (String pid : split) {
						product pro = productservice.findProductByPid(pid);
						historyProductList.add(pro);
					}
				}
			}
		}

		// ����ʷ��¼�ļ��Ϸŵ�����
		request.setAttribute("historyProductList", historyProductList);

	}

	// ���ݵ��Եļ۸������ȡ����
	private void getcomputerByPrice(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		String price = request.getParameter("price");
		String cid = request.getParameter("cid");
		String currentPageStr = request.getParameter("currentPage");
		if (currentPageStr == null)
			currentPageStr = "1";
		int currentPage = Integer.parseInt(currentPageStr);
		int currentCount = 12;
		Productservice productservice = new Productservice();
		PageBean<product> pageBean = new PageBean<product>();
		pageBean = productservice.getcomputerByPrice(price, cid, currentPage, currentCount);
		// System.out.println(price+".."+list+cid);
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("cid", cid);
		// ����һ����¼��ʷ��Ʒ��Ϣ�ļ���
		List<product> historyProductList = new ArrayList<product>();

		// ��ÿͻ���Я�����ֽ�pids��cookie
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("pids".equals(cookie.getName())) {
					String pids = cookie.getValue();// 3-2-1
					String[] split = pids.split("-");
					for (String pid : split) {
						product pro = productservice.findProductByPid(pid);
						historyProductList.add(pro);
					}
				}
			}
		}
		// ����ʷ��¼�ļ��Ϸŵ�����
		request.setAttribute("historyProductList", historyProductList);

	}

	// ���ݵ���Ʒ�Ʋ�ѯ����
	private void getConputerBypname(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		String pname = request.getParameter("pname");
		String cid = request.getParameter("cid");
		String currentPageStr = request.getParameter("currentPage");
		if (currentPageStr == null)
			currentPageStr = "1";
		int currentPage = Integer.parseInt(currentPageStr);
		int currentCount = 12;
		Productservice productservice = new Productservice();
		PageBean<product> pageBean = new PageBean<product>();
		pageBean = productservice.getcomputerBypname(pname, cid, currentPage, currentCount);
		// System.out.println(pname+".."+list+cid);

		request.setAttribute("pageBean", pageBean);
		request.setAttribute("cid", cid);
		// ����һ����¼��ʷ��Ʒ��Ϣ�ļ���
		List<product> historyProductList = new ArrayList<product>();

		// ��ÿͻ���Я�����ֽ�pids��cookie
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("pids".equals(cookie.getName())) {
					String pids = cookie.getValue();// 3-2-1
					String[] split = pids.split("-");
					for (String pid : split) {
						product pro = productservice.findProductByPid(pid);
						historyProductList.add(pro);
					}
				}
			}
		}

		// ����ʷ��¼�ļ��Ϸŵ�����
		request.setAttribute("historyProductList", historyProductList);

	}

	// �����ֻ��۸��ѯ�ֻ�
	private void getmobileByPrice(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		String price = request.getParameter("price");
		String cid = request.getParameter("cid");
		String currentPageStr = request.getParameter("currentPage");
		if (currentPageStr == null)
			currentPageStr = "1";
		int currentPage = Integer.parseInt(currentPageStr);
		int currentCount = 12;
		Productservice productservice = new Productservice();
		PageBean<product> pageBean = new PageBean<product>();
		pageBean = productservice.getmobileByPrice(price, cid, currentPage, currentCount);
		// System.out.println(price+".."+list+cid);

		request.setAttribute("pageBean", pageBean);
		request.setAttribute("cid", cid);
		// ����һ����¼��ʷ��Ʒ��Ϣ�ļ���
		List<product> historyProductList = new ArrayList<product>();

		// ��ÿͻ���Я�����ֽ�pids��cookie
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("pids".equals(cookie.getName())) {
					String pids = cookie.getValue();// 3-2-1
					String[] split = pids.split("-");
					for (String pid : split) {
						product pro = productservice.findProductByPid(pid);
						historyProductList.add(pro);
					}
				}
			}
		}
		// ����ʷ��¼�ļ��Ϸŵ�����
		request.setAttribute("historyProductList", historyProductList);

	}

	// �����ֻ���Ʒ�Ʋ�ѯ�ֻ�
	private void getmobileBypname(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		String pname = request.getParameter("pname");
		String cid = request.getParameter("cid");
		String currentPageStr = request.getParameter("currentPage");
		if (currentPageStr == null)
			currentPageStr = "1";
		int currentPage = Integer.parseInt(currentPageStr);
		int currentCount = 12;
		Productservice productservice = new Productservice();
		PageBean<product> pageBean = new PageBean<product>();
		pageBean = productservice.getmobileBypname(pname, cid, currentPage, currentCount);
		// System.out.println(pname+".."+list+cid);

		request.setAttribute("pageBean", pageBean);
		request.setAttribute("cid", cid);
		// ����һ����¼��ʷ��Ʒ��Ϣ�ļ���
		List<product> historyProductList = new ArrayList<product>();

		// ��ÿͻ���Я�����ֽ�pids��cookie
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("pids".equals(cookie.getName())) {
					String pids = cookie.getValue();// 3-2-1
					String[] split = pids.split("-");
					for (String pid : split) {
						product pro = productservice.findProductByPid(pid);
						historyProductList.add(pro);
					}
				}
			}
		}

		// ����ʷ��¼�ļ��Ϸŵ�����
		request.setAttribute("historyProductList", historyProductList);

	}
	private void searchBypname(HttpServletRequest request, HttpServletResponse response) {
		String pname = request.getParameter("pname");	
		Productservice productservice = new Productservice();
		product product = productservice.getproductBypname(pname);

		request.setAttribute("product", product);

	}

	private List<Object> searchword(HttpServletRequest request, HttpServletResponse response) {
		String word = request.getParameter("word");
		Productservice productservice = new Productservice();
		List<Object> pnamelist = null;
		try {
			pnamelist = productservice.serchpnameByword(word);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ʹ��json��ת�����߽�����򼯺�ת��json��ʽ���ַ���
		/*
		 * JSONArray fromObject = JSONArray.fromObject(productList); String
		 * string = fromObject.toString(); System.out.println(string);
		 */
		return pnamelist;
		// ����json�������õ�����ת��Ϊjson�����ַ�����ʽ

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	// ��ȡ��������Ϣ
	protected void category(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Categoryservice categoryservice = new Categoryservice();
		// ��ȡ��������Ϣ
		List<Category> categories = categoryservice.getcategory();
		// ����request����
		request.getServletContext().setAttribute("categories", categories);
		request.getRequestDispatcher("index.jsp").forward(request, response);

	}

	// ��ȡ��ҳ����Ʒ��Ϣ
	protected void index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Productservice productservice = new Productservice();

		// ��ȡ������Ʒ
		List<product> hotProducts = productservice.findHotProducts();
		// ��ȡ������Ʒ
		List<product> newproducts = productservice.findNewProducts();

		request.setAttribute("hotProducts", hotProducts);
		request.setAttribute("newproducts", newproducts);
		category(request, response);
		// request.getRequestDispatcher("/category").forward(request, response);

	}

	// ��ȡ��Ʒ�б�
	protected void product_list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		// ��ȡcid
		String cid = request.getParameter("cid");
		String currentPageStr = request.getParameter("currentPage");
		if (currentPageStr == null)
			currentPageStr = "1";
		int currentPage = Integer.parseInt(currentPageStr);
		int currentCount = 12;

		Productservice service = new Productservice();
		@SuppressWarnings("rawtypes")
		PageBean pageBean = service.findProductListByCid(cid, currentPage, currentCount);
		// System.out.println(pageBean.getList());
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("cid", cid);
		// ����һ����¼��ʷ��Ʒ��Ϣ�ļ���
		List<product> historyProductList = new ArrayList<product>();

		// ��ÿͻ���Я�����ֽ�pids��cookie
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("pids".equals(cookie.getName())) {
					String pids = cookie.getValue();// 3-2-1
					String[] split = pids.split("-");
					for (String pid : split) {
						product pro = service.findProductByPid(pid);
						historyProductList.add(pro);
					}
				}
			}
		}

		// ����ʷ��¼�ļ��Ϸŵ�����
		request.setAttribute("historyProductList", historyProductList);

		request.getRequestDispatcher("/product_list.jsp").forward(request, response);

	}

	// ��ȡ��Ʒ��ϸ��Ϣ
	protected void product_info(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// ��ȡ����
		String pimage = request.getParameter("pimage");
		String cid = request.getParameter("cid");

		// ���Ҫ��ѯ����Ʒ��pid
		String pid = request.getParameter("pid");

		// ���ݸ�service��
		Productservice productservice = new Productservice();
		Categoryservice categoryservice = new Categoryservice();
		Category category = categoryservice.getcategoryBycid(cid);
		product product = productservice.findproBypimgandpname(pimage);
		request.setAttribute("category", category);
		request.setAttribute("product", product);

		// ��ÿͻ���Я��cookie---���������pids��cookie
		String pids = pid;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("pids".equals(cookie.getName())) {
					pids = cookie.getValue();
					// 1-3-2 ���η�����Ʒpid��8----->8-1-3-2
					// 1-3-2 ���η�����Ʒpid��3----->3-1-2
					// 1-3-2 ���η�����Ʒpid��2----->2-1-3
					// ��pids���һ������
					String[] split = pids.split("-");// {3,1,2}
					List<String> asList = Arrays.asList(split);// [3,1,2]
					LinkedList<String> list = new LinkedList<String>(asList);// [3,1,2]
					// �жϼ������Ƿ���ڵ�ǰpid
					if (list.contains(pid)) {
						// ������ǰ�鿴��Ʒ��pid
						list.remove(pid);
						list.addFirst(pid);
					} else {
						// ��������ǰ�鿴��Ʒ��pid ֱ�ӽ���pid�ŵ�ͷ��
						list.addFirst(pid);
					}
					// ��[3,1,2]ת��3-1-2�ַ���
					StringBuffer sb = new StringBuffer();
					for (int i = 0; i < list.size() && i < 7; i++) {
						sb.append(list.get(i));
						sb.append("-");// 3-1-2-
					}
					// ȥ��3-1-2-���-
					pids = sb.substring(0, sb.length() - 1);
				}
			}
		}
		Cookie cookie_pids = new Cookie("pids", pids);
		response.addCookie(cookie_pids);

		request.getRequestDispatcher("/product_info.jsp").forward(request, response);

	}

	// ��ù��ﳵ��Ʒ��Ϣ
	protected void cart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡǰ̨����������
		request.setCharacterEncoding("UTF-8");
		// String pid = request.getParameter("pid");
		//
		// Productservice productinfo = new Productservice();
		// product product = productinfo.getcartproduct(pid);
		// request.setAttribute("product", product);
		// request.getRequestDispatcher("/cart.jsp").forward(request, response);
		HttpSession session = request.getSession();

		Productservice service = new Productservice();

		// ���Ҫ�ŵ����ﳵ����Ʒ��pid
		String pid = request.getParameter("pid");
		// ���product����
		product product = service.findProductByPid(pid);

		// ��ø���Ʒ�Ĺ�������
		String bNum = request.getParameter("buyNum");
		int buyNum = Integer.parseInt(bNum);

		// ����С��
		double subtotal = product.getShop_price() * buyNum;
		// ��װCartItem
		CartItem item = new CartItem();
		item.setProduct(product);
		item.setBuyNum(buyNum);
		item.setSubtotal(subtotal);

		// ��ù��ﳵ---�ж��Ƿ���session���Ѿ����ڹ��ﳵ
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
		}
         
         
		// ��������ŵ�����---key��pid
		// ���жϹ��ﳵ���Ƿ��ѽ������˹������� ----- �ж�key�Ƿ��Ѿ�����
		// ������ﳵ���Ѿ����ڸ���Ʒ----���������������ԭ�е�����������Ӳ���
		Map<String, CartItem> cartItems = cart.getCartItems();

		double newsubtotal = 0.0;

		if (cartItems.containsKey(pid)) {
			// ȡ��ԭ����Ʒ������
			CartItem cartItem = cartItems.get(pid);
			int oldBuyNum = cartItem.getBuyNum();

			// ��ȡÿһ�����Ʒ�������������ҽ����Ǵӿ���ȥ
			service.updateproductcount(pid, oldBuyNum);

			oldBuyNum += buyNum;
			cartItem.setBuyNum(oldBuyNum);
			cart.setCartItems(cartItems);
			// �޸�С��
			// ԭ������Ʒ��С��
			double oldsubtotal = cartItem.getSubtotal();
			// �������Ʒ��С��
			newsubtotal = buyNum * product.getShop_price();
			cartItem.setSubtotal(oldsubtotal + newsubtotal);

		} else {
			// �������û�и���Ʒ
			cart.getCartItems().put(product.getPid(), item);
			newsubtotal = buyNum * product.getShop_price();

			// System.out.println(product .getCounts());
		}

		// �����ܼ�
		double total = cart.getTotal() + newsubtotal;
		cart.setTotal(total);

		// �����ٴη���session
		session.setAttribute("cart", cart);
		// ֱ����ת�����ﳵҳ��
		response.sendRedirect(request.getContextPath() + "/cart.jsp");
	}

	// ��ȡ���ﳵ������Ϣ
	protected void submitOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		user user = (user) session.getAttribute("user");

		if (user != null) {
			// Ŀ�ģ���װ��һ��Order���� ���ݸ�service��
			Orders order = new Orders();

			// 1��private String oid;//�ö����Ķ�����
			String oid = UUID.randomUUID().toString();
			order.setOid(oid);

			// 2��private Date ordertime;//�µ�ʱ��
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String orderdate = format.format(date);
			order.setOrdertime(orderdate);

			// 3��private double total;//�ö������ܽ��
			// ���session�еĹ��ﳵ
			Cart cart = (Cart) session.getAttribute("cart");
			double total = cart.getTotal();
			order.setTotal(total);

			// 4��private int state;//����֧��״̬ 1�����Ѹ��� 0����δ����
			order.setState(0);

			// 5��private String address;//�ջ���ַ
			order.setAddress(null);

			// 6��private String name;//�ջ���
			order.setName(null);

			// 7��private String telephone;//�ջ��˵绰
			order.setTelephone(null);

			// 8��private User user;//�ö��������ĸ��û�
			order.setUser(user);

			// 9���ö������ж��ٶ�����List<OrderItem> orderItems = new
			// ArrayList<OrderItem>();
			// ��ù��ﳵ�еĹ�����ļ���map
			Map<String, CartItem> cartItems = cart.getCartItems();
			// �������ݵ�service��
			Productservice service = new Productservice();
			for (Map.Entry<String, CartItem> entry : cartItems.entrySet()) {
				// ȡ��ÿһ��������
				CartItem cartItem = entry.getValue();
				// �����µĶ�����
				OrderItems orderItem = new OrderItems();
				// 1)private String itemid;//�������id
				orderItem.setItemid(UUID.randomUUID().toString());
				// 2)private int count;//����������Ʒ�Ĺ�������
				orderItem.setCount(cartItem.getBuyNum());
				//System.out.println(cartItem.getProduct().getPid()+"..."+cartItem.getBuyNum()+"..."+cartItem.getProduct().getCounts());
				// ��ȥ��Ʒ������
				/*service.updateproductcount(cartItem.getProduct().getPid(),
				 cartItem.getBuyNum());					
					service.updateproductByid(cartItem.getProduct().getPid(), cartItem.getBuyNum());				
					 service.insertproductByid(cartItem.getProduct().getPid(), orderItem.getSubtotal());
				*///System.out.println(cartItem.getProduct().getPid()+ cartItem.getBuyNum());			
							
				// System.out.println(cartItem.getProduct().getCounts());
				// 3)private double subtotal;//������С��
				orderItem.setSubtotal(cartItem.getSubtotal());
				// 4)private Product product;//�������ڲ�����Ʒ
				orderItem.setProduct(cartItem.getProduct());
				// 5)private Order order;//�ö����������ĸ�����
				orderItem.setOrder(order);
			   
	            List<OrderItems> orderItemslist = new LinkedList<>();
	            orderItemslist.add(orderItem);
	            request.getSession().setAttribute("list", orderItemslist);
				// ���ö�������ӵ������Ķ��������
				order.getOrderItems().add(orderItem);
			}

			// order�����װ���

			service.submitOrder(order);
            //System.out.println(order);
			session.setAttribute("order", order);
			//����oid��ѯorderitems����
			

			// ҳ����ת
			response.sendRedirect(request.getContextPath() + "/order_info.jsp");
		} else {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		}

	}

	// ��չ��ﳵ
	public void clearCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("cart");

		// ��ת��cart.jsp
		response.sendRedirect(request.getContextPath() + "/cart.jsp");

	}

	// ɾ����һ��Ʒ
	public void delProFromCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���Ҫɾ����item��pid
		String pid = request.getParameter("pid");
		// ɾ��session�еĹ��ﳵ�еĹ�������е�item
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart != null) {
			Map<String, CartItem> cartItems = cart.getCartItems();
			// ��Ҫ�޸��ܼ�
			cart.setTotal(cart.getTotal() - cartItems.get(pid).getSubtotal());
			// ɾ��
			cartItems.remove(pid);
			cart.setCartItems(cartItems);

		}

		session.setAttribute("cart", cart);

		// ��ת��cart.jsp
		response.sendRedirect(request.getContextPath() + "/cart.jsp");
	}

	// ȷ�϶���---�����ջ�����Ϣ+����֧��
	public void confirmOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Productservice service = new Productservice();
		@SuppressWarnings("unchecked")
		List<OrderItems> orderItemslist = (List<OrderItems>) session.getAttribute("list");
		for(OrderItems items : orderItemslist){
			if(service.getproductcount(items.getProduct().getPid())>0){
				service.updateproductcount(items.getProduct().getPid(),items.getCount());
				service.updateproductByid(items.getProduct().getPid(), items.getCount());				
				service.updateproduct(items.getProduct().getPid(),items.getSubtotal());
			}else {
				service.updateproductcount(items.getProduct().getPid(),0);
				service.updateproductByid(items.getProduct().getPid(), 0);				
				service.updateproduct(items.getProduct().getPid(),0.0);
			}
			
		}
		// 1�������ջ�����Ϣ
		Orders order = new Orders();
		//String address=new String(request.getParameter("address").getBytes("UTF-8"),"GBK");
		order.setAddress(request.getParameter("address"));
		order.setName(request.getParameter("name"));
		order.setTelephone(request.getParameter("telephone"));
		//order.setOid(CommonUtils.getUUid());		
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String orderdate = format.format(date);
		order.setOrdertime(orderdate);	

		//��ȡ����session���е�order����
		Orders orders = (Orders) request.getSession().getAttribute("order");
		order.setOid(orders.getOid());
		order.setTotal(orders.getTotal());
		//System.out.println(order+"...."+orders);
		//System.out.println(orders);
			request.setAttribute("order", orders);	
			
		boolean isConfirmSuccessful = service.updateOrderAdrr(order);
					
			if (isConfirmSuccessful) {
						response.sendRedirect(request.getContextPath() + "/order_list.jsp");
			} else {
				response.getWriter().write("failued!!please by it again.");
			}

	}
}
