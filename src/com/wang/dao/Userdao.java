package com.wang.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.wang.domain.Administrantor;
import com.wang.domain.product;
import com.wang.domain.user;
import com.wang.utils.JDBCUtils;

public class Userdao {
	private QueryRunner runner = new QueryRunner(JDBCUtils.getdatasource());

	// 注册
	public int register(user user) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "insert into user values (?,?,?,?,?,?,?,?,?,?)";
		int rows = runner.update(sql, user.getUid(), user.getUsername(), user.getPassword(), user.getName(),
				user.getEmail(), user.getTell(), user.getBirthday(), user.getSex(), user.getState(), user.getCode());
		return rows;
	}

	// 检测用户名称的是否存在
	public user checkusername(String username) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select username from user where username = ?";
		user user = runner.query(sql, new BeanHandler<user>(user.class), username);
		return user;
	}

	// 激活
	public int active(String activecode) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "update user set state=? where code=?";
		int row = runner.update(sql, 1, activecode);
		return row;
	}

	// 检查登录情况
	public user checklogin(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select * from user where username=? and password=?";
		return runner.query(sql, new BeanHandler<user>(user.class), username, password);

	}

	// 获取热门商品
	public List<product> findHotProducts() throws SQLException {
		String sql = "select * from product where is_hot =? limit ?,?";
		return runner.query(sql, new BeanListHandler<product>(product.class), 1, 0, 9);
	}

	// 获取最新商品
	public List<product> findNewProducts() throws SQLException {
		String sql = "select * from product order by pdate desc limit ?,?";
		return runner.query(sql, new BeanListHandler<product>(product.class), 0, 9);

	}

	public List<user> selectall() throws SQLException {
		String sql = "select * from user ";
		List<user> users = runner.query(sql, new BeanListHandler<user>(user.class));
		return users;
	}

	public Administrantor checkadminloginBynameAndpass(String name, String pass) throws SQLException {
		String sql = "select * from user where username = ? and password = ?";
		Object[] param = { name, pass };
		return runner.query(sql, new BeanHandler<Administrantor>(Administrantor.class), param);

	}

	public com.wang.domain.user getuserByuid(String uid) throws SQLException {
		String sql = "select * from user where uid = ?";
		return runner.query(sql, new BeanHandler<user>(user.class), uid);
	}

	public int submitadduser(user user) throws SQLException {
		String sql = "insert into user values (?,?,?,?,?,?,?,?,?,?)";
		int row = runner.update(sql, user.getUid(), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(),
				user.getTell(), user.getBirthday(), user.getSex(), user.getState(), user.getCode());
         return row;
	}

	public void deleteuser(String uid) throws SQLException {
		String sql = "delete from user where uid =?";
		runner.update(sql,uid);
		
	}

	public void submitedit(user user) throws SQLException {
		String sql = "update user set username=?,state=?,password=? where uid =?";
		 runner.update(sql,  user.getUsername(), user.getState(), user.getPassword(),user.getUid());
	}
	public void updateadmin(user user) throws  SQLException{
		String sql ="INSERT INTO admin (username,password,name) VALUES(?,?,?) ";
		runner.update(sql,user.getUsername(),user.getPassword(),user.getName());
	}

	public List<user> getalluser() throws SQLException {
		String sql ="select * from user ";
		List<user> list = runner.query(sql, new BeanListHandler<user>(user.class));
		return list;
	}

	public String getuidByoid(String oid) throws SQLException {
		String sql ="select uid from orders where oid =? ";
		user user = runner.query(sql, new BeanHandler<user>(user.class),oid);
		return user.getUid();
	}

}
