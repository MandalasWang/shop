package com.wang.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.wang.domain.Category;
import com.wang.utils.JDBCUtils;

public class Categorydao {
	private QueryRunner runner = new QueryRunner(JDBCUtils.getdatasource());

	// 获取导航条信息
	public List<Category> getcategory() throws SQLException {
		String sql = "select * from category ";
		return runner.query(sql, new BeanListHandler<Category>(Category.class));

	}

	// 根据cid获取category的商品类别
	public Category getcategoryBycid(String cid) throws SQLException {
		String sql = "select * from category where cid =?";
		return runner.query(sql, new BeanHandler<Category>(Category.class), cid);
	}

	public void Delcategory(String cid) throws SQLException {
		String sql = "delete from category where cid =?";
		runner.update(sql, cid);
	}

	public void Categoryupdate(Category category) throws SQLException {
		String sql = "update category set cid =? ,cname =? where cid=?";
		Object[] param = { category.getCid(), category.getCname(), category.getCid() };
		runner.update(sql, param);

	}

	public void addcetegory(Category category) throws SQLException {
		String sql = "insert into category values(?,?)";
		runner.update(sql, category.getCid(), category.getCname());

	}

}
