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
		//请求内容转码
		request.setCharacterEncoding("UTF-8");
		//回传告诉浏览器展示编码
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
			response.getWriter().write("您还未下单,请赶快去下单吧！");
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
		// 定义一个记录历史商品信息的集合
		List<product> historyProductList = new ArrayList<product>();

		// 获得客户端携带名字叫pids的cookie
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
		// 将历史记录的集合放到域中
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
		// 定义一个记录历史商品信息的集合
		List<product> historyProductList = new ArrayList<product>();

		// 获得客户端携带名字叫pids的cookie
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

		// 将历史记录的集合放到域中
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
		// 定义一个记录历史商品信息的集合
		List<product> historyProductList = new ArrayList<product>();

		// 获得客户端携带名字叫pids的cookie
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
		// 将历史记录的集合放到域中
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
		// 定义一个记录历史商品信息的集合
		List<product> historyProductList = new ArrayList<product>();

		// 获得客户端携带名字叫pids的cookie
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

		// 将历史记录的集合放到域中
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
		// 定义一个记录历史商品信息的集合
		List<product> historyProductList = new ArrayList<product>();

		// 获得客户端携带名字叫pids的cookie
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

		// 将历史记录的集合放到域中
		request.setAttribute("historyProductList", historyProductList);

	}

	// 根据电脑的价格区间获取电脑
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
		// 定义一个记录历史商品信息的集合
		List<product> historyProductList = new ArrayList<product>();

		// 获得客户端携带名字叫pids的cookie
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
		// 将历史记录的集合放到域中
		request.setAttribute("historyProductList", historyProductList);

	}

	// 根据电脑品牌查询电脑
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
		// 定义一个记录历史商品信息的集合
		List<product> historyProductList = new ArrayList<product>();

		// 获得客户端携带名字叫pids的cookie
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

		// 将历史记录的集合放到域中
		request.setAttribute("historyProductList", historyProductList);

	}

	// 根据手机价格查询手机
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
		// 定义一个记录历史商品信息的集合
		List<product> historyProductList = new ArrayList<product>();

		// 获得客户端携带名字叫pids的cookie
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
		// 将历史记录的集合放到域中
		request.setAttribute("historyProductList", historyProductList);

	}

	// 根据手机的品牌查询手机
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
		// 定义一个记录历史商品信息的集合
		List<product> historyProductList = new ArrayList<product>();

		// 获得客户端携带名字叫pids的cookie
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

		// 将历史记录的集合放到域中
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
		// 使用json的转换工具将对象或集合转成json格式的字符串
		/*
		 * JSONArray fromObject = JSONArray.fromObject(productList); String
		 * string = fromObject.toString(); System.out.println(string);
		 */
		return pnamelist;
		// 利用json插件将获得的数据转换为json数据字符串格式

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	// 获取导航条信息
	protected void category(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Categoryservice categoryservice = new Categoryservice();
		// 获取导航条信息
		List<Category> categories = categoryservice.getcategory();
		// 存入request域中
		request.getServletContext().setAttribute("categories", categories);
		request.getRequestDispatcher("index.jsp").forward(request, response);

	}

	// 获取首页的商品信息
	protected void index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Productservice productservice = new Productservice();

		// 获取热门商品
		List<product> hotProducts = productservice.findHotProducts();
		// 获取最新商品
		List<product> newproducts = productservice.findNewProducts();

		request.setAttribute("hotProducts", hotProducts);
		request.setAttribute("newproducts", newproducts);
		category(request, response);
		// request.getRequestDispatcher("/category").forward(request, response);

	}

	// 获取商品列表
	protected void product_list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		// 获取cid
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
		// 定义一个记录历史商品信息的集合
		List<product> historyProductList = new ArrayList<product>();

		// 获得客户端携带名字叫pids的cookie
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

		// 将历史记录的集合放到域中
		request.setAttribute("historyProductList", historyProductList);

		request.getRequestDispatcher("/product_list.jsp").forward(request, response);

	}

	// 获取商品详细信息
	protected void product_info(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 获取数据
		String pimage = request.getParameter("pimage");
		String cid = request.getParameter("cid");

		// 获得要查询的商品的pid
		String pid = request.getParameter("pid");

		// 传递给service层
		Productservice productservice = new Productservice();
		Categoryservice categoryservice = new Categoryservice();
		Category category = categoryservice.getcategoryBycid(cid);
		product product = productservice.findproBypimgandpname(pimage);
		request.setAttribute("category", category);
		request.setAttribute("product", product);

		// 获得客户端携带cookie---获得名字是pids的cookie
		String pids = pid;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("pids".equals(cookie.getName())) {
					pids = cookie.getValue();
					// 1-3-2 本次访问商品pid是8----->8-1-3-2
					// 1-3-2 本次访问商品pid是3----->3-1-2
					// 1-3-2 本次访问商品pid是2----->2-1-3
					// 将pids拆成一个数组
					String[] split = pids.split("-");// {3,1,2}
					List<String> asList = Arrays.asList(split);// [3,1,2]
					LinkedList<String> list = new LinkedList<String>(asList);// [3,1,2]
					// 判断集合中是否存在当前pid
					if (list.contains(pid)) {
						// 包含当前查看商品的pid
						list.remove(pid);
						list.addFirst(pid);
					} else {
						// 不包含当前查看商品的pid 直接将该pid放到头上
						list.addFirst(pid);
					}
					// 将[3,1,2]转成3-1-2字符串
					StringBuffer sb = new StringBuffer();
					for (int i = 0; i < list.size() && i < 7; i++) {
						sb.append(list.get(i));
						sb.append("-");// 3-1-2-
					}
					// 去掉3-1-2-后的-
					pids = sb.substring(0, sb.length() - 1);
				}
			}
		}
		Cookie cookie_pids = new Cookie("pids", pids);
		response.addCookie(cookie_pids);

		request.getRequestDispatcher("/product_info.jsp").forward(request, response);

	}

	// 获得购物车商品信息
	protected void cart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取前台传来的数据
		request.setCharacterEncoding("UTF-8");
		// String pid = request.getParameter("pid");
		//
		// Productservice productinfo = new Productservice();
		// product product = productinfo.getcartproduct(pid);
		// request.setAttribute("product", product);
		// request.getRequestDispatcher("/cart.jsp").forward(request, response);
		HttpSession session = request.getSession();

		Productservice service = new Productservice();

		// 获得要放到购物车的商品的pid
		String pid = request.getParameter("pid");
		// 获得product对象
		product product = service.findProductByPid(pid);

		// 获得该商品的购买数量
		String bNum = request.getParameter("buyNum");
		int buyNum = Integer.parseInt(bNum);

		// 计算小计
		double subtotal = product.getShop_price() * buyNum;
		// 封装CartItem
		CartItem item = new CartItem();
		item.setProduct(product);
		item.setBuyNum(buyNum);
		item.setSubtotal(subtotal);

		// 获得购物车---判断是否在session中已经存在购物车
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
		}
         
         
		// 将购物项放到车中---key是pid
		// 先判断购物车中是否已将包含此购物项了 ----- 判断key是否已经存在
		// 如果购物车中已经存在该商品----将现在买的数量与原有的数量进行相加操作
		Map<String, CartItem> cartItems = cart.getCartItems();

		double newsubtotal = 0.0;

		if (cartItems.containsKey(pid)) {
			// 取出原有商品的数量
			CartItem cartItem = cartItems.get(pid);
			int oldBuyNum = cartItem.getBuyNum();

			// 获取每一项的商品购买数量，并且将他们从库存减去
			service.updateproductcount(pid, oldBuyNum);

			oldBuyNum += buyNum;
			cartItem.setBuyNum(oldBuyNum);
			cart.setCartItems(cartItems);
			// 修改小计
			// 原来该商品的小计
			double oldsubtotal = cartItem.getSubtotal();
			// 新买的商品的小计
			newsubtotal = buyNum * product.getShop_price();
			cartItem.setSubtotal(oldsubtotal + newsubtotal);

		} else {
			// 如果车中没有该商品
			cart.getCartItems().put(product.getPid(), item);
			newsubtotal = buyNum * product.getShop_price();

			// System.out.println(product .getCounts());
		}

		// 计算总计
		double total = cart.getTotal() + newsubtotal;
		cart.setTotal(total);

		// 将车再次访问session
		session.setAttribute("cart", cart);
		// 直接跳转到购物车页面
		response.sendRedirect(request.getContextPath() + "/cart.jsp");
	}

	// 获取购物车订单信息
	protected void submitOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		user user = (user) session.getAttribute("user");

		if (user != null) {
			// 目的：封装好一个Order对象 传递给service层
			Orders order = new Orders();

			// 1、private String oid;//该订单的订单号
			String oid = UUID.randomUUID().toString();
			order.setOid(oid);

			// 2、private Date ordertime;//下单时间
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String orderdate = format.format(date);
			order.setOrdertime(orderdate);

			// 3、private double total;//该订单的总金额
			// 获得session中的购物车
			Cart cart = (Cart) session.getAttribute("cart");
			double total = cart.getTotal();
			order.setTotal(total);

			// 4、private int state;//订单支付状态 1代表已付款 0代表未付款
			order.setState(0);

			// 5、private String address;//收货地址
			order.setAddress(null);

			// 6、private String name;//收货人
			order.setName(null);

			// 7、private String telephone;//收货人电话
			order.setTelephone(null);

			// 8、private User user;//该订单属于哪个用户
			order.setUser(user);

			// 9、该订单中有多少订单项List<OrderItem> orderItems = new
			// ArrayList<OrderItem>();
			// 获得购物车中的购物项的集合map
			Map<String, CartItem> cartItems = cart.getCartItems();
			// 传递数据到service层
			Productservice service = new Productservice();
			for (Map.Entry<String, CartItem> entry : cartItems.entrySet()) {
				// 取出每一个购物项
				CartItem cartItem = entry.getValue();
				// 创建新的订单项
				OrderItems orderItem = new OrderItems();
				// 1)private String itemid;//订单项的id
				orderItem.setItemid(UUID.randomUUID().toString());
				// 2)private int count;//订单项内商品的购买数量
				orderItem.setCount(cartItem.getBuyNum());
				//System.out.println(cartItem.getProduct().getPid()+"..."+cartItem.getBuyNum()+"..."+cartItem.getProduct().getCounts());
				// 减去商品的数量
				/*service.updateproductcount(cartItem.getProduct().getPid(),
				 cartItem.getBuyNum());					
					service.updateproductByid(cartItem.getProduct().getPid(), cartItem.getBuyNum());				
					 service.insertproductByid(cartItem.getProduct().getPid(), orderItem.getSubtotal());
				*///System.out.println(cartItem.getProduct().getPid()+ cartItem.getBuyNum());			
							
				// System.out.println(cartItem.getProduct().getCounts());
				// 3)private double subtotal;//订单项小计
				orderItem.setSubtotal(cartItem.getSubtotal());
				// 4)private Product product;//订单项内部的商品
				orderItem.setProduct(cartItem.getProduct());
				// 5)private Order order;//该订单项属于哪个订单
				orderItem.setOrder(order);
			   
	            List<OrderItems> orderItemslist = new LinkedList<>();
	            orderItemslist.add(orderItem);
	            request.getSession().setAttribute("list", orderItemslist);
				// 将该订单项添加到订单的订单项集合中
				order.getOrderItems().add(orderItem);
			}

			// order对象封装完毕

			service.submitOrder(order);
            //System.out.println(order);
			session.setAttribute("order", order);
			//利用oid查询orderitems集合
			

			// 页面跳转
			response.sendRedirect(request.getContextPath() + "/order_info.jsp");
		} else {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		}

	}

	// 清空购物车
	public void clearCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("cart");

		// 跳转回cart.jsp
		response.sendRedirect(request.getContextPath() + "/cart.jsp");

	}

	// 删除单一商品
	public void delProFromCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获得要删除的item的pid
		String pid = request.getParameter("pid");
		// 删除session中的购物车中的购物项集合中的item
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart != null) {
			Map<String, CartItem> cartItems = cart.getCartItems();
			// 需要修改总价
			cart.setTotal(cart.getTotal() - cartItems.get(pid).getSubtotal());
			// 删除
			cartItems.remove(pid);
			cart.setCartItems(cartItems);

		}

		session.setAttribute("cart", cart);

		// 跳转回cart.jsp
		response.sendRedirect(request.getContextPath() + "/cart.jsp");
	}

	// 确认订单---更新收货人信息+在线支付
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
		// 1、更新收货人信息
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

		//获取存在session域中的order对象
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
