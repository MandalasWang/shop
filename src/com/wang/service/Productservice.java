package com.wang.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


import com.wang.dao.Productdao;
import com.wang.domain.Orders;
import com.wang.domain.product;
import com.wang.vo.PageBean;
import com.wang.vo.condition;

public class Productservice {
	private Productdao productdao = new Productdao();

	// 获取热门商品
	public List<product> findHotProducts() {
		List<product> hotproducts = null;
		try {
			hotproducts = productdao.findHotProducts();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hotproducts;
	}

	// 获取最新商品
	public List<product> findNewProducts() {
		List<product> Newproducts = null;
		try {
			Newproducts = productdao.findNewProducts();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Newproducts;
	}

	// 根据Cid获取相应的数据
	@SuppressWarnings("rawtypes")
	public PageBean findProductListByCid(String cid, int currentPage, int currentCount) {
		Productdao dao = new Productdao();

		// 封装一个PageBean 返回web层
		PageBean<product> pageBean = new PageBean<product>();

		// 1、封装当前页
		pageBean.setCurrentPage(currentPage);
		// 2、封装每页显示的条数
		pageBean.setCurrentCount(currentCount);
		// 3、封装总条数
		int totalCount = 0;
		try {
			totalCount = dao.getCount(cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pageBean.setTotalCount(totalCount);
		// 4、封装总页数
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);

		// 5、当前页显示的数据
		// select * from product where cid=? limit ?,?
		// 当前页与起始索引index的关系
		int index = (currentPage - 1) * currentCount;
		List<product> list = null;
		try {
			list = dao.findProductByPage(cid, index, currentCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pageBean.setList(list);

		return pageBean;
	}

	// 通过pid找到历史浏览的商品记录
	public product findProductByPid(String pid) {
		// TODO Auto-generated method stub
		product product = null;
		try {
			product = productdao.findProductByPid(pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}

	// 根据pimage、pname查询商品的信息
	public com.wang.domain.product findproBypimgandpname(String pimage) {
		product product = null;
		try {
			product = productdao.findproBypimgandpname(pimage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}
	public com.wang.domain.product findproByandpname(String pname) {
		product product = null;
		try {
			product = productdao.findproByandpname(pname);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}
	// 根据pid获取购物车商品的信息
	public com.wang.domain.product getcartproduct(String pid) {
		product product = null;
		try {
			product = productdao.getcartproduct(pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}

	// 将购物车的商品信息存入到数据库中
	/*
	 * public void addorderinfotoDB(Orders orders) { // TODO Auto-generated
	 * method stub try { productdao.addorderinfotoDB(orders); } catch
	 * (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } }
	 */
	/*
	 * //根据oid获取数据中存入的cart商品信息 public void getcartinfoByoid(Orders order) { try
	 * { productdao.addOrderItem(order); } catch (SQLException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * }
	 */
	// 根据orderitem存入cart商品
	/*
	 * public void addcartinfotoDB(Orders order) { // TODO Auto-generated method
	 * stub try { productdao.addorderinfotoDB(order); } catch (SQLException e) {
	 * // TODO Auto-generated catch block e.printStackTrace(); } }
	 */
	// 获得指定用户的订单集合
	public List<Orders> findAllOrders(String uid) {
		List<Orders> orderList = null;
		try {
			orderList = productdao.findAllOrders(uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}

	public List<Map<String, Object>> findAllOrderItemByOid(String oid) {

		List<Map<String, Object>> mapList = null;
		try {
			mapList = productdao.findAllOrderItemByOid(oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mapList;
	}

	// 提交订单 将订单的数据和订单项的数据存储到数据库中
	public void submitOrder(Orders order) {

		try {

			// 2、调用dao存储order表数据的方法
			productdao.addOrders(order);
			// 3、调用dao存储orderitem表数据的方法
			productdao.addOrderItem(order);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public boolean updateOrderAdrr(Orders order) {
		int row = 0;
		try {
			row = productdao.updateOrderAdrr(order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row > 0 ? true : false;
	}

	public List<product> getallProduct() {
		List<product> list = null;
		try {
			list = productdao.getallProduct();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public void saveAdminaddproduct(product product) {
		try {
			productdao.saveAdminaddproduct(product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void DelproductBypid(String pid) {
		try {
			productdao.DelproductBypid(pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<product> showproductBycon(condition condition) {
		List<product> list = null;
		try {
			list = productdao.showproductBycon(condition);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public void updateproduct(product product) {
		try {
			productdao.updateproduct(product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<Object> serchpnameByword(String word) throws SQLException {

		return productdao.serchpnameByword(word);

	}

	public product getproductBypname(String pname) {
		product p = null;
		try {
			p = productdao.getproductBypname(pname);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}

	// 减去已提交订单的商品数量
	public void updateproductcount(String pid, int oldBuyNum) {
		try {
			productdao.updateproductcount(pid, oldBuyNum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public PageBean<product> getmobileBypname(String pname, String cid, int currentPage, int currentCount) {
		// 封装一个PageBean 返回web层
		PageBean<product> pageBean = new PageBean<product>();

		// 1、封装当前页
		pageBean.setCurrentPage(currentPage);
		// 2、封装每页显示的条数
		pageBean.setCurrentCount(currentCount);
		// 3、封装总条数
		int totalCount = 0;
		try {
			totalCount = productdao.getproductBynametotalCount(cid, pname);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pageBean.setTotalCount(totalCount);
		// 4、封装总页数
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);

		// 5、当前页显示的数据
		// select * from product where cid=? limit ?,?
		// 当前页与起始索引index的关系
		int index = (currentPage - 1) * currentCount;
		List<product> list = null;
		try {
			list = productdao.getmobileBypname(pname, cid, index, currentCount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<product> getmobileByPrice(String price, String cid, int currentPage, int currentCount) {
		// 封装一个PageBean 返回web层
		PageBean<product> pageBean = new PageBean<product>();

		// 1、封装当前页
		pageBean.setCurrentPage(currentPage);
		// 2、封装每页显示的条数
		pageBean.setCurrentCount(currentCount);
		// 3、封装总条数
		int totalCount = 0;
		try {
			totalCount = productdao.getproductBypricetotalCount(Double.valueOf(price), cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pageBean.setTotalCount(totalCount);
		// 4、封装总页数
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);

		// 5、当前页显示的数据
		// select * from product where cid=? limit ?,?
		// 当前页与起始索引index的关系
		int index = (currentPage - 1) * currentCount;
		List<product> list = null;
		try {
			list = productdao.getmobileByPrice(Double.valueOf(price), cid, index, currentCount);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<product> getcomputerBypname(String pname, String cid, int currentPage, int currentCount) {
		// 封装一个PageBean 返回web层
		PageBean<product> pageBean = new PageBean<product>();

		// 1、封装当前页
		pageBean.setCurrentPage(currentPage);
		// 2、封装每页显示的条数
		pageBean.setCurrentCount(currentCount);
		// 3、封装总条数
		int totalCount = 0;
		try {
			totalCount = productdao.getproductBynametotalCount(cid, pname);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pageBean.setTotalCount(totalCount);
		// 4、封装总页数
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);

		// 5、当前页显示的数据
		// select * from product where cid=? limit ?,?
		// 当前页与起始索引index的关系
		int index = (currentPage - 1) * currentCount;
		List<product> list = null;
		try {
			list = productdao.getcomputerBypname(pname, cid, index, currentCount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<product> getcomputerByPrice(String price, String cid, int currentPage, int currentCount) {
		// 封装一个PageBean 返回web层
		PageBean<product> pageBean = new PageBean<product>();

		// 1、封装当前页
		pageBean.setCurrentPage(currentPage);
		// 2、封装每页显示的条数
		pageBean.setCurrentCount(currentCount);
		// 3、封装总条数
		int totalCount = 0;
		try {
			totalCount = productdao.getproductcBypricetotalCount(Double.valueOf(price), cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pageBean.setTotalCount(totalCount);
		// 4、封装总页数
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);

		// 5、当前页显示的数据
		// select * from product where cid=? limit ?,?
		// 当前页与起始索引index的关系
		int index = (currentPage - 1) * currentCount;
		List<product> list = null;
		try {
			list = productdao.getcomputerByPrice(Double.valueOf(price), cid, index, currentCount);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<product> getTabletPCBypname(String pname, String cid, int currentPage, int currentCount) {
		// 封装一个PageBean 返回web层
		PageBean<product> pageBean = new PageBean<product>();

		// 1、封装当前页
		pageBean.setCurrentPage(currentPage);
		// 2、封装每页显示的条数
		pageBean.setCurrentCount(currentCount);
		// 3、封装总条数
		int totalCount = 0;
		try {
			totalCount = productdao.getproductBynametotalCount(cid, pname);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pageBean.setTotalCount(totalCount);
		// 4、封装总页数
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);

		// 5、当前页显示的数据
		// select * from product where cid=? limit ?,?
		// 当前页与起始索引index的关系
		int index = (currentPage - 1) * currentCount;
		List<product> list = null;
		try {
			list = productdao.getTabletPCBypname(pname, cid, index, currentCount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<product> getTabletPCByprice(String price, String cid, int currentPage, int currentCount) {
		// 封装一个PageBean 返回web层
		PageBean<product> pageBean = new PageBean<product>();

		// 1、封装当前页
		pageBean.setCurrentPage(currentPage);
		// 2、封装每页显示的条数
		pageBean.setCurrentCount(currentCount);
		// 3、封装总条数
		int totalCount = 0;
		try {
			totalCount = productdao.getproductTabletBypricetotalCount(cid, Double.valueOf(price));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pageBean.setTotalCount(totalCount);
		// 4、封装总页数
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);

		// 5、当前页显示的数据
		// select * from product where cid=? limit ?,?
		// 当前页与起始索引index的关系
		int index = (currentPage - 1) * currentCount;
		List<product> list = null;
		try {
			list = productdao.getTabletPCByprice(Double.valueOf(price), cid, index, currentCount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<product> getSoudBypname(String pname, String cid, int currentPage, int currentCount) {
		// 封装一个PageBean 返回web层
		PageBean<product> pageBean = new PageBean<product>();

		// 1、封装当前页
		pageBean.setCurrentPage(currentPage);
		// 2、封装每页显示的条数
		pageBean.setCurrentCount(currentCount);
		// 3、封装总条数
		int totalCount = 0;
		try {
			totalCount = productdao.getproductBynametotalCount(cid, pname);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pageBean.setTotalCount(totalCount);
		// 4、封装总页数
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);

		// 5、当前页显示的数据
		// select * from product where cid=? limit ?,?
		// 当前页与起始索引index的关系
		int index = (currentPage - 1) * currentCount;
		List<product> list = null;
		try {
			list = productdao.getSoudBypname(pname, cid, index, currentCount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<product> getSoudByprice(String price, String cid, int currentPage, int currentCount) {
		// 封装一个PageBean 返回web层
		PageBean<product> pageBean = new PageBean<product>();

		// 1、封装当前页
		pageBean.setCurrentPage(currentPage);
		// 2、封装每页显示的条数
		pageBean.setCurrentCount(currentCount);
		// 3、封装总条数
		int totalCount = 0;
		try {
			totalCount = productdao.getproductSoudBypricetotalCount(cid, Double.valueOf(price));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pageBean.setTotalCount(totalCount);
		// 4、封装总页数
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);

		// 5、当前页显示的数据
		// select * from product where cid=? limit ?,?
		// 当前页与起始索引index的关系
		int index = (currentPage - 1) * currentCount;
		List<product> list = null;
		try {
			list = productdao.getTabletPCByprice(Double.valueOf(price), cid, index, currentCount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pageBean.setList(list);
		return pageBean;
	}

	public void updateproductByid(String pid, int salescount) {
		try {
			productdao.updateproductByid(pid, salescount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int getsalecounts(String pid) {
		int salecounts = 0;
		try {
			salecounts = productdao.getsalecounts(pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return salecounts;
	}

	public void updateproduct(String pid, double subtotal) {
		try {
			productdao.updateproductsales(pid, subtotal);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int getproductcount(String pid) {
		int count = 0;
		try {
			count = productdao.getproductcounts(pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public int getallproductcountsBycid(int i) {
		int counts =0;
		try {
			counts = productdao.getallproductcountsBycid(Integer.toString(i));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return counts;
	}

	public double getallproductsalecountsBycid(int i) {
		double sales =0.0d;
		try {
			sales = productdao.getallproductsalesBycid(Integer.toString(i));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sales;
	}

	public product getpidByitem(String id) {
		product product = null;
		try {
			product = productdao.getpidByitem(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}


	public void updateproductBypid(String pid, String importconts) {
		
		try {
			productdao.updateproductBypid(pid,Integer.valueOf(importconts));
			productdao.updateproductcountBypid(pid, Integer.valueOf(importconts));
		} catch (NumberFormatException e) {	
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public List<product> showimportproductBycon(condition condition) {
		List<product> list = null;
		try {
			list = productdao.showimportproductBycon(condition);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<product> getallProductordered() {
		List<product> products = null;
		try {
			products = productdao.getallProductordered();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}

}
