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

	// ��ȡ������Ʒ
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

	// ��ȡ������Ʒ
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

	// ����Cid��ȡ��Ӧ������
	@SuppressWarnings("rawtypes")
	public PageBean findProductListByCid(String cid, int currentPage, int currentCount) {
		Productdao dao = new Productdao();

		// ��װһ��PageBean ����web��
		PageBean<product> pageBean = new PageBean<product>();

		// 1����װ��ǰҳ
		pageBean.setCurrentPage(currentPage);
		// 2����װÿҳ��ʾ������
		pageBean.setCurrentCount(currentCount);
		// 3����װ������
		int totalCount = 0;
		try {
			totalCount = dao.getCount(cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pageBean.setTotalCount(totalCount);
		// 4����װ��ҳ��
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);

		// 5����ǰҳ��ʾ������
		// select * from product where cid=? limit ?,?
		// ��ǰҳ����ʼ����index�Ĺ�ϵ
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

	// ͨ��pid�ҵ���ʷ�������Ʒ��¼
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

	// ����pimage��pname��ѯ��Ʒ����Ϣ
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
	// ����pid��ȡ���ﳵ��Ʒ����Ϣ
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

	// �����ﳵ����Ʒ��Ϣ���뵽���ݿ���
	/*
	 * public void addorderinfotoDB(Orders orders) { // TODO Auto-generated
	 * method stub try { productdao.addorderinfotoDB(orders); } catch
	 * (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } }
	 */
	/*
	 * //����oid��ȡ�����д����cart��Ʒ��Ϣ public void getcartinfoByoid(Orders order) { try
	 * { productdao.addOrderItem(order); } catch (SQLException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * }
	 */
	// ����orderitem����cart��Ʒ
	/*
	 * public void addcartinfotoDB(Orders order) { // TODO Auto-generated method
	 * stub try { productdao.addorderinfotoDB(order); } catch (SQLException e) {
	 * // TODO Auto-generated catch block e.printStackTrace(); } }
	 */
	// ���ָ���û��Ķ�������
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

	// �ύ���� �����������ݺͶ���������ݴ洢�����ݿ���
	public void submitOrder(Orders order) {

		try {

			// 2������dao�洢order�����ݵķ���
			productdao.addOrders(order);
			// 3������dao�洢orderitem�����ݵķ���
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

	// ��ȥ���ύ��������Ʒ����
	public void updateproductcount(String pid, int oldBuyNum) {
		try {
			productdao.updateproductcount(pid, oldBuyNum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public PageBean<product> getmobileBypname(String pname, String cid, int currentPage, int currentCount) {
		// ��װһ��PageBean ����web��
		PageBean<product> pageBean = new PageBean<product>();

		// 1����װ��ǰҳ
		pageBean.setCurrentPage(currentPage);
		// 2����װÿҳ��ʾ������
		pageBean.setCurrentCount(currentCount);
		// 3����װ������
		int totalCount = 0;
		try {
			totalCount = productdao.getproductBynametotalCount(cid, pname);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pageBean.setTotalCount(totalCount);
		// 4����װ��ҳ��
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);

		// 5����ǰҳ��ʾ������
		// select * from product where cid=? limit ?,?
		// ��ǰҳ����ʼ����index�Ĺ�ϵ
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
		// ��װһ��PageBean ����web��
		PageBean<product> pageBean = new PageBean<product>();

		// 1����װ��ǰҳ
		pageBean.setCurrentPage(currentPage);
		// 2����װÿҳ��ʾ������
		pageBean.setCurrentCount(currentCount);
		// 3����װ������
		int totalCount = 0;
		try {
			totalCount = productdao.getproductBypricetotalCount(Double.valueOf(price), cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pageBean.setTotalCount(totalCount);
		// 4����װ��ҳ��
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);

		// 5����ǰҳ��ʾ������
		// select * from product where cid=? limit ?,?
		// ��ǰҳ����ʼ����index�Ĺ�ϵ
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
		// ��װһ��PageBean ����web��
		PageBean<product> pageBean = new PageBean<product>();

		// 1����װ��ǰҳ
		pageBean.setCurrentPage(currentPage);
		// 2����װÿҳ��ʾ������
		pageBean.setCurrentCount(currentCount);
		// 3����װ������
		int totalCount = 0;
		try {
			totalCount = productdao.getproductBynametotalCount(cid, pname);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pageBean.setTotalCount(totalCount);
		// 4����װ��ҳ��
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);

		// 5����ǰҳ��ʾ������
		// select * from product where cid=? limit ?,?
		// ��ǰҳ����ʼ����index�Ĺ�ϵ
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
		// ��װһ��PageBean ����web��
		PageBean<product> pageBean = new PageBean<product>();

		// 1����װ��ǰҳ
		pageBean.setCurrentPage(currentPage);
		// 2����װÿҳ��ʾ������
		pageBean.setCurrentCount(currentCount);
		// 3����װ������
		int totalCount = 0;
		try {
			totalCount = productdao.getproductcBypricetotalCount(Double.valueOf(price), cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pageBean.setTotalCount(totalCount);
		// 4����װ��ҳ��
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);

		// 5����ǰҳ��ʾ������
		// select * from product where cid=? limit ?,?
		// ��ǰҳ����ʼ����index�Ĺ�ϵ
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
		// ��װһ��PageBean ����web��
		PageBean<product> pageBean = new PageBean<product>();

		// 1����װ��ǰҳ
		pageBean.setCurrentPage(currentPage);
		// 2����װÿҳ��ʾ������
		pageBean.setCurrentCount(currentCount);
		// 3����װ������
		int totalCount = 0;
		try {
			totalCount = productdao.getproductBynametotalCount(cid, pname);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pageBean.setTotalCount(totalCount);
		// 4����װ��ҳ��
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);

		// 5����ǰҳ��ʾ������
		// select * from product where cid=? limit ?,?
		// ��ǰҳ����ʼ����index�Ĺ�ϵ
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
		// ��װһ��PageBean ����web��
		PageBean<product> pageBean = new PageBean<product>();

		// 1����װ��ǰҳ
		pageBean.setCurrentPage(currentPage);
		// 2����װÿҳ��ʾ������
		pageBean.setCurrentCount(currentCount);
		// 3����װ������
		int totalCount = 0;
		try {
			totalCount = productdao.getproductTabletBypricetotalCount(cid, Double.valueOf(price));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pageBean.setTotalCount(totalCount);
		// 4����װ��ҳ��
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);

		// 5����ǰҳ��ʾ������
		// select * from product where cid=? limit ?,?
		// ��ǰҳ����ʼ����index�Ĺ�ϵ
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
		// ��װһ��PageBean ����web��
		PageBean<product> pageBean = new PageBean<product>();

		// 1����װ��ǰҳ
		pageBean.setCurrentPage(currentPage);
		// 2����װÿҳ��ʾ������
		pageBean.setCurrentCount(currentCount);
		// 3����װ������
		int totalCount = 0;
		try {
			totalCount = productdao.getproductBynametotalCount(cid, pname);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pageBean.setTotalCount(totalCount);
		// 4����װ��ҳ��
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);

		// 5����ǰҳ��ʾ������
		// select * from product where cid=? limit ?,?
		// ��ǰҳ����ʼ����index�Ĺ�ϵ
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
		// ��װһ��PageBean ����web��
		PageBean<product> pageBean = new PageBean<product>();

		// 1����װ��ǰҳ
		pageBean.setCurrentPage(currentPage);
		// 2����װÿҳ��ʾ������
		pageBean.setCurrentCount(currentCount);
		// 3����װ������
		int totalCount = 0;
		try {
			totalCount = productdao.getproductSoudBypricetotalCount(cid, Double.valueOf(price));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pageBean.setTotalCount(totalCount);
		// 4����װ��ҳ��
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);

		// 5����ǰҳ��ʾ������
		// select * from product where cid=? limit ?,?
		// ��ǰҳ����ʼ����index�Ĺ�ϵ
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
