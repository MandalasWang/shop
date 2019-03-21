package com.wang.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.wang.domain.OrderItems;
import com.wang.domain.Orders;
import com.wang.utils.JDBCUtils;

public class orderDao {
	private QueryRunner runner = new QueryRunner(JDBCUtils.getdatasource());

	// 获取所有订单信息
	public List<Orders> getorderlist() throws SQLException {
		String sql = "select * from orders ";
		List<Orders> list = runner.query(sql, new BeanListHandler<Orders>(Orders.class));
		return list;
	}

	// 根据oid 获取items订单项
	public List<OrderItems> getorderitemsByoid(String oid) throws SQLException {
		String sql = "select * from orderitem where oid =? ";
		List<OrderItems> list = runner.query(sql, new BeanListHandler<OrderItems>(OrderItems.class), oid);
		return list;
	}

	public Orders getorderByoid(String oid) throws SQLException {
		String sql = "select * from orders where oid =?";
		Orders orders = runner.query(sql, new BeanHandler<Orders>(Orders.class), oid);
		return orders;
	}
	public int delOrderitemByoid(String oid) throws SQLException {
		String sql ="delete from orderitem where oid =?";
		 return runner.update(sql,oid);
		
	}
	public int delOrderByoid(String oid) throws SQLException {
		String sql ="delete from orders where oid =?";
		int i = runner.update(sql,oid);
        return i;
	}
	public List<Orders> getorderlistByuid(String uid) throws SQLException {
		String sql = "select * from orders where uid =?";
		List<Orders> list = runner.query(sql, new BeanListHandler<Orders>(Orders.class), uid);
		return list;
	}

	

}
