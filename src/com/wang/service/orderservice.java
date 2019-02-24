package com.wang.service;

import java.sql.SQLException;
import java.util.List;

import com.wang.dao.orderDao;
import com.wang.domain.OrderItems;
import com.wang.domain.Orders;


public class orderservice {
 private orderDao dao = new orderDao();
	public List<Orders> getorderslist() {
		List<Orders> list = null;
		
		try {
			list = dao.getorderlist();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	public static  List<OrderItems> getorderitemsByoid(String oid) {
		orderDao dao = new orderDao();
		List<OrderItems> items = null;
		try {
			items = dao.getorderitemsByoid(oid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return items;
	}
	
	public static Orders getorderByoid(String oid) {
		orderDao dao = new orderDao();
		Orders orders = null;
		try {
			orders = dao.getorderByoid(oid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}
	public int delOrderByoid(String oid) {
		orderDao dao = new orderDao();
		int i =0;
		try {
			i = dao.delOrderitemByoid(oid);
			i = dao.delOrderByoid(oid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	public List<Orders> getorderlistByuid(String uid) {
		orderDao dao = new orderDao();
		List<Orders> list = null;
		try {
			list = dao.getorderlistByuid(uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	

}
