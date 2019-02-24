package com.wang.web.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.wang.domain.Administrantor;
import com.wang.domain.Category;
import com.wang.domain.OrderItems;
import com.wang.domain.Orders;
import com.wang.domain.product;
import com.wang.domain.user;
import com.wang.service.Categoryservice;
import com.wang.service.Productservice;
import com.wang.service.Userservice;
import com.wang.service.orderservice;
import com.wang.vo.condition;


@WebServlet("/Admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String methodName = request.getParameter("method");
		if ("updateuser".equals(methodName)) {
			user user = updateuser(request, response);
			if (user != null) {
				request.setAttribute("user", user);
				request.getRequestDispatcher("/admin/user/edit.jsp").forward(request, response);
			}
		} else if ("submitadduser".equals(methodName)) {
			int row = submitadduser(request, response);
			if (row > 0) {
				List<user> list = userlist(request, response);
				request.setAttribute("userlist", list);
				request.getRequestDispatcher("/admin/user/list.jsp").forward(request, response);
			}
		} else if ("submitedit".equals(methodName)) {
			submitedit(request, response);
			List<user> list = userlist(request, response);
			request.setAttribute("userlist", list);
			request.getRequestDispatcher("/admin/user/list.jsp").forward(request, response);
		} else if ("userlist".equals(methodName)) {
			List<user> list = userlist(request, response);
			request.setAttribute("userlist", list);
			request.getRequestDispatcher("/admin/user/list.jsp").forward(request, response);
		} else if ("DeleteuserByuid".equals(methodName)) {
			DeleteuserByuid(request, response);
			List<user> list = userlist(request, response);
			request.setAttribute("userlist", list);
			request.getRequestDispatcher("/admin/user/list.jsp").forward(request, response);
		} else if ("categorylist".equals(methodName)) {
			List<Category> categories = categorylist(request, response);
			request.setAttribute("categorylist", categories);
			request.getRequestDispatcher("/admin/category/list.jsp").forward(request, response);
		} else if ("Delcategory".equals(methodName)) {
			Delcategory(request, response);
			List<Category> categories = categorylist(request, response);
			request.setAttribute("categorylist", categories);
			request.getRequestDispatcher("/admin/category/list.jsp").forward(request, response);
		} else if ("categoryedit".equals(methodName)) {
			Category category = categoryedit(request, response);
			request.setAttribute("category", category);
			request.getRequestDispatcher("/admin/category/edit.jsp").forward(request, response);
		} else if ("Categoryupdate".equals(methodName)) {
			Categoryupdate(request, response);
			List<Category> categories = categorylist(request, response);
			request.setAttribute("categorylist", categories);
			request.getRequestDispatcher("/admin/category/list.jsp").forward(request, response);
		} else if ("addcetegory".equals(methodName)) {
			addcetegory(request, response);
			List<Category> categories = categorylist(request, response);
			request.setAttribute("categorylist", categories);
			request.getRequestDispatcher("/admin/category/list.jsp").forward(request, response);
		} else if ("productlist".equals(methodName)) {
			List<product> list = productlist(request, response);
			request.setAttribute("productlist", list);
			List<Category> categories = categorylist(request, response);
			request.getSession().setAttribute("categorylist", categories);
			request.getRequestDispatcher("/admin/product/list.jsp").forward(request, response);
		} else if ("addproduct".equals(methodName)) {
			addproduct(request, response);
			List<product> list = productlist(request, response);
			request.setAttribute("productlist", list);
			request.getRequestDispatcher("/admin/product/list.jsp").forward(request, response);
		} else if ("DelproductBypid".equals(methodName)) {
			DelproductBypid(request, response);
			List<product> list = productlist(request, response);
			request.setAttribute("productlist", list);
			request.getRequestDispatcher("/admin/product/list.jsp").forward(request, response);
		} else if ("searchproductlist".equals(methodName)) {
			searchproductlist(request, response);
			request.getRequestDispatcher("/admin/product/list.jsp").forward(request, response);
		} else if ("editproduct".equals(methodName)) {
			product product = editproduct(request, response);
			request.setAttribute("product", product);
			request.getRequestDispatcher("/admin/product/edit.jsp").forward(request, response);
		} else if ("updateproduct".equals(methodName)) {
			updateproduct(request, response);
			List<product> list = productlist(request, response);
			request.setAttribute("productlist", list);
			request.getRequestDispatcher("/admin/product/list.jsp").forward(request, response);
		} else if ("dealwelcome".equals(methodName)) {
			boolean issucessfullogin = adminlogincheck(request, response);
			if (issucessfullogin) {
				request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
			}
		} else if ("orderlist".equals(methodName)) {
			List<Orders> list = orderlist(request, response);
			request.setAttribute("orderlist", list);
			request.getRequestDispatcher("/admin/order/list.jsp").forward(request, response);
		} else if ("getOrderinfo".equals(methodName)) {
			getOrderinfo(request, response);
			request.getRequestDispatcher("/admin/order/productinfo.jsp").forward(request, response);
		} else if("DelOrderByoid".equals(methodName)){
			boolean isdelsuccessful = delOrderByoid(request,response);
			System.out.println(isdelsuccessful);
			if(isdelsuccessful){
				List<Orders> list = orderlist(request, response);
				request.setAttribute("orderlist", list);
				request.getRequestDispatcher("/admin/order/list.jsp").forward(request, response);	
			}	
		} else if("salelist".equals(methodName)){
			salelist(request,response);
			request.getRequestDispatcher("/admin/sales/salelist.jsp").forward(request, response);
		} else if("importlist".equals(methodName)){
			 importlist(request,response);		 
			request.getRequestDispatcher("/admin/imports/importlist.jsp").forward(request, response);
		} else if("importBypid".equals(methodName)){
			importBypid(request,response);
			request.getRequestDispatcher("/admin/imports/import.jsp").forward(request, response);
		} else if("updateimportBypid".equals(methodName)){
			updateimportBypid(request,response);
			importlist(request,response);		 
			request.getRequestDispatcher("/admin/imports/importlist.jsp").forward(request, response);
		} else if("importsearchprolist".equals(methodName)){
			importsearchprolist(request,response);
			request.getRequestDispatcher("/admin/imports/importlist.jsp").forward(request, response);
		} else if("searchsaleproductlist".equals(methodName)){
			searchsaleproductlist(request,response);
			request.getRequestDispatcher("/admin/sales/salelist.jsp").forward(request, response);
		}
			/*else if("salelistadd".equals(methodName)){
		}
			salelistadd(request,response);
			request.getRequestDispatcher("/admin/sales/countlist.jsp").forward(request, response);
		}*/

	}

/*
	private void salelistadd(HttpServletRequest request, HttpServletResponse response) {
		Categoryservice service = new Categoryservice();
		List<Category> list = service.getcategory();
		Productservice pservice = new Productservice();
	    int[] array = {};
	    for(int i =1 ; i<list.size()+1;i++){
	    	array[i]=pservice.getallproductcountsBycid(i);
	    }
	    double[] array1 = {};
	    for(int i =1 ; i<list.size()+1;i++){
	    	array1[i]=pservice.getallproductsalecountsBycid(i);
	    } 
        request.setAttribute("list", list);
        request.setAttribute("totalcounts",array );
        request.setAttribute("totalsales", array1);
	}*/
	private void searchsaleproductlist(HttpServletRequest request, HttpServletResponse response) {
		// 接收数据
				Map<String, String[]> properties = request.getParameterMap();
				condition condition = new condition();
				// 封装数据 进到congdition 实体
				try {
					BeanUtils.populate(condition, properties);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 把实体condition传给service层
				Productservice productservice = new Productservice();
				List<product> productlist = null;
				try {
					productlist = productservice.showproductBycon(condition);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Categoryservice protypeservice = new Categoryservice();
				List<Category> typelist = null;
				try {
					typelist = protypeservice.getcategory();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("categorylist", typelist);

				request.setAttribute("productlist", productlist);
		
	}
	private void importsearchprolist(HttpServletRequest request, HttpServletResponse response) {
		// 接收数据
		Map<String, String[]> properties = request.getParameterMap();
		condition condition = new condition();
		// 封装数据 进到congdition 实体
		try {
			BeanUtils.populate(condition, properties);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 把实体condition传给service层
		Productservice productservice = new Productservice();
		List<product> productlist = null;
		try {
			productlist = productservice.showimportproductBycon(condition);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Categoryservice protypeservice = new Categoryservice();
		List<Category> typelist = null;
		try {
			typelist = protypeservice.getcategory();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("categorylist", typelist);

		request.setAttribute("list", productlist);
		
	}
	private void updateimportBypid(HttpServletRequest request, HttpServletResponse response) {
		String pid = request.getParameter("pid");
		String importconts = request.getParameter("importconts");
		
		Productservice productservice = new Productservice();
		productservice.updateproductBypid(pid,importconts);
		
	}
	private void importBypid(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		String pid = request.getParameter("pid");
		Productservice productservice = new Productservice();
		product product = productservice.findProductByPid(pid);
		request.setAttribute("product", product);
	}
	private void importlist(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		Productservice productservice = new Productservice();
		List<product> list = productservice.getallProduct();
		request.setAttribute("list", list);
		List<Category> categories = categorylist(request, response);
		request.setAttribute("categorylist", categories);			
	}
	private void salelist(HttpServletRequest request, HttpServletResponse response) {
		Productservice service = new Productservice();
		List<product> list = service.getallProductordered();
		Categoryservice protypeservice = new Categoryservice();
		List<Category> typelist = null;
		try {
			typelist = protypeservice.getcategory();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("categorylist", typelist);
		request.setAttribute("productlist", list);
		
	}

	private boolean delOrderByoid(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		String oid = request.getParameter("oid");
		System.out.println(oid);
		orderservice orderservice = new orderservice();
		int i = orderservice.delOrderByoid(oid);
		boolean isdelsuccessful = false;
		if(i!=0){
			isdelsuccessful =true;
		}
		return isdelsuccessful;
	}
	private void getOrderinfo(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		String oid = request.getParameter("oid");	
		orderservice orderservice = new orderservice();
		Productservice service = new Productservice();
		@SuppressWarnings("static-access")
		Orders order = orderservice.getorderByoid(oid);
		@SuppressWarnings("static-access")
		List<OrderItems> lItems = orderservice.getorderitemsByoid(oid);		
			for(OrderItems items : lItems){
				items.setOrder(order);	
				product product = service.getpidByitem(oid);
				//System.out.println(pid);
			    items.setProduct(product);
				order.getOrderItems().add(items);
			}				
		request.setAttribute("order", order);

	}

	private List<Orders> orderlist(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		orderservice oservice = new orderservice();
		Userservice userservice = new Userservice();
		List<Orders> list = oservice.getorderslist();	
		
		/*user user = (user) request.getSession().getAttribute("user");*/
		for(Orders orders : list){
			String uid =userservice.getuidByoid(orders.getOid());
			user user = userservice.getuserByuid(uid);
			orders.setUser(user);
		}
		return list;
	}

	private void submitedit(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String state = request.getParameter("state");
		user user = new user();
		user.setUid(request.getParameter("uid"));
		user.setUsername(username);
		user.setPassword(password);
		user.setState(Integer.valueOf(state));
		Userservice userservice = new Userservice();
		userservice.submitedit(user);		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private boolean adminlogincheck(HttpServletRequest request, HttpServletResponse response) {
		boolean issucessfullogin = false;
		String name = request.getParameter("username");
		String pass = request.getParameter("password");
		Userservice userservice = new Userservice();
		Administrantor admin = userservice.checkadminloginBynameAndpass(name, pass);
		System.out.println(admin);
		if (admin.getUsername().equals(name) && admin.getPassword().equals(pass) ) {
			request.getSession().setAttribute("manager", admin);
			issucessfullogin = true;
		}
		return issucessfullogin;
	}

	private List<user> userlist(HttpServletRequest request, HttpServletResponse response) {
		Userservice userservice = new Userservice();
		List<user> userlist = userservice.selectall();
		return userlist;
	}

	private user updateuser(HttpServletRequest request, HttpServletResponse response) {
		String uid = request.getParameter("uid");
		Userservice userservice = new Userservice();
		user user = userservice.getuserByuid(uid);
		return user;

	}

	private int submitadduser(HttpServletRequest request, HttpServletResponse response) {
		user user = new user();
		user.setName(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		user.setState(Integer.valueOf(request.getParameter("state")));
		user.setUid(UUID.randomUUID().toString());
		Userservice userservice = new Userservice();
		return userservice.submitadduser(user);

	}

	private void DeleteuserByuid(HttpServletRequest request, HttpServletResponse response) {
		String uid = request.getParameter("uid");
		Userservice userservice = new Userservice();		
		userservice.deleteuser(uid);
				
	}

	private List<Category> categorylist(HttpServletRequest request, HttpServletResponse response) {
		Categoryservice categoryservice = new Categoryservice();
		// 获取导航条信息
		List<Category> categories = categoryservice.getcategory();
		return categories;
	}

	private void Delcategory(HttpServletRequest request, HttpServletResponse response) {
		String cid = request.getParameter("cid");
		Categoryservice categoryservice = new Categoryservice();
		categoryservice.Delcategory(cid);
	}

	private Category categoryedit(HttpServletRequest request, HttpServletResponse response) {
		String cid = request.getParameter("cid");
		Categoryservice categoryservice = new Categoryservice();
		Category category = categoryservice.getcategoryBycid(cid);
		return category;
	}

	private void Categoryupdate(HttpServletRequest request, HttpServletResponse response) {
		String cid = request.getParameter("cid");
		String cname = request.getParameter("cname");
		Category category = new Category();
		category.setCid(cid);
		category.setCname(cname);
		Categoryservice categoryservice = new Categoryservice();
		categoryservice.Categoryupdate(category);
	}

	private void addcetegory(HttpServletRequest request, HttpServletResponse response) {
		String cname = request.getParameter("cname");
		Category category = new Category();
		category.setCid(UUID.randomUUID().toString());
		category.setCname(cname);
		Categoryservice categoryservice = new Categoryservice();
		categoryservice.addcetegory(category);
	}

	private List<product> productlist(HttpServletRequest request, HttpServletResponse response) {
		Productservice productservice = new Productservice();
		List<product> list = productservice.getallProduct();
		return list;
	}

	private void addproduct(HttpServletRequest request, HttpServletResponse response) {
		//目的：收集表单的数据 封装一个Product实体 将上传图片存到服务器磁盘上
		
				product product = new product();
				
				//收集数据的容器
				Map<String,Object> map = new HashMap<String,Object>();
				
				try {
					//创建磁盘文件项工厂
					DiskFileItemFactory factory = new DiskFileItemFactory();
					//创建文件上传核心对象
					ServletFileUpload upload = new ServletFileUpload(factory);
					//解析request获得文件项对象集合

					@SuppressWarnings("unchecked")
					List<FileItem> parseRequest = upload.parseRequest(request);
					for(FileItem item : parseRequest){
						//判断是否是普通表单项
						boolean formField = item.isFormField();
						if(formField){
							//普通表单项 获得表单的数据 封装到Product实体中
							String fieldName = item.getFieldName();
							String fieldValue = item.getString("UTF-8");
							
							map.put(fieldName, fieldValue);
							
						}else{
							//文件上传项 获得文件名称 获得文件的内容
							String fileName = item.getName();
							String path = this.getServletContext().getRealPath("upload");
							System.out.println(path);
							InputStream in = item.getInputStream();
							OutputStream out = new FileOutputStream(path+"/"+fileName);//I:/xxx/xx/xxx/xxx.jpg
							IOUtils.copy(in, out);
							in.close();
							out.close();
							item.delete();
							
							map.put("pimage", "upload/"+fileName);
							
						}
						
					}
					
					BeanUtils.populate(product, map);
					//是否product对象封装数据完全
					//private String pid;
					product.setPid(UUID.randomUUID().toString());
					//private Date pdate;
					Date date = new Date();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String date1 = format.format(date);
					product.setPdate(date1);
					//private int pflag;
					product.setPflag(0);
					//private Category category;
					product.setCid(Integer.valueOf(map.get("cid").toString()));
					//private int counts;
					product.setCounts(0);
					//private int salecounts;
					product.setSalecounts(0);
					//private int importconts;
					product.setImportconts(0);
					//private int cost;
					product.setCost((int)product.getShop_price());
				   // private double sales;
					product.setSales(0);
					//将product传递给service层
					Productservice service = new Productservice();
					service.saveAdminaddproduct(product);
			
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


	}

	private void DelproductBypid(HttpServletRequest request, HttpServletResponse response) {
		String pid = request.getParameter("pid");
		Productservice productservice = new Productservice();
		productservice.DelproductBypid(pid);
	}

	private void searchproductlist(HttpServletRequest request, HttpServletResponse response) {
		// 接收数据
		Map<String, String[]> properties = request.getParameterMap();
		condition condition = new condition();
		// 封装数据 进到congdition 实体
		try {
			BeanUtils.populate(condition, properties);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 把实体condition传给service层
		Productservice productservice = new Productservice();
		List<product> productlist = null;
		try {
			productlist = productservice.showproductBycon(condition);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Categoryservice protypeservice = new Categoryservice();
		List<Category> typelist = null;
		try {
			typelist = protypeservice.getcategory();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("categorylist", typelist);

		request.setAttribute("productlist", productlist);

	}

	private product editproduct(HttpServletRequest request, HttpServletResponse response) {
		String pid = request.getParameter("pid");
		Productservice productservice = new Productservice();
		product product = productservice.findProductByPid(pid);
		return product;

	}

	private void updateproduct(HttpServletRequest request, HttpServletResponse response) {
		//目的：收集表单的数据 封装一个Product实体 将上传图片存到服务器磁盘上	
		product product = new product();
		
		String pid = request.getParameter("pid");
		product.setPid(pid);
		//收集数据的容器
		Map<String,Object> map = new HashMap<String,Object>();
		
		try {
			//创建磁盘文件项工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//创建文件上传核心对象
			ServletFileUpload upload = new ServletFileUpload(factory);
			//解析request获得文件项对象集合

			@SuppressWarnings("unchecked")
			List<FileItem> parseRequest = upload.parseRequest(request);
			for(FileItem item : parseRequest){
				//判断是否是普通表单项
				boolean formField = item.isFormField();
				if(formField){
					//普通表单项 获得表单的数据 封装到Product实体中
					String fieldName = item.getFieldName();
					String fieldValue = item.getString("UTF-8");
					
					map.put(fieldName, fieldValue);
					
				}else{
					//文件上传项 获得文件名称 获得文件的内容
					String fileName = item.getName();
					String path = this.getServletContext().getRealPath("upload");
					//System.out.println(path);
					InputStream in = item.getInputStream();
					OutputStream out = new FileOutputStream(path+"/"+fileName);//盘符:/xxx/xx/xxx/xxx.jpg
					IOUtils.copy(in, out);
					in.close();
					out.close();
					item.delete();
					
					map.put("pimage", "upload/"+fileName);
					
				}
				
			}
			
			BeanUtils.populate(product, map);
			//是否product对象封装数据完全
			//private Date pdate;
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String date1 = format.format(date);
			product.setPdate(date1);
			//private int pflag;
			product.setPflag(0);
			//private Category category;
			product.setCid(Integer.valueOf(map.get("cid").toString()));
			//private int counts;
			product.setCounts(0);
			//private int salecounts;
			product.setSalecounts(0);
			//private int importconts;
			product.setImportconts(0);
			//private int cost;
			product.setCost((int)product.getShop_price());
		   // private double sales;
			product.setSales(0);
			//将product传递给service层
			Productservice service = new Productservice();
			service.updateproduct(product);
				
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			
		
	}
	
}
