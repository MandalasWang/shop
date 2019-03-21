package com.wang.service;

import java.sql.SQLException;
import java.util.List;

import com.wang.dao.Userdao;
import com.wang.domain.Administrantor;
import com.wang.domain.user;


public class Userservice {
private Userdao userdao = new Userdao();
     //注册
	public boolean register(user user) throws SQLException {
		// TODO Auto-generated method stub
		int rows = userdao.register(user);
		return rows>0?true:false;
	}
	//检查username
	public boolean checkusername(String username) throws SQLException {
		// TODO Auto-generated method stub
		user user = userdao.checkusername(username);
		 return user!= null?true:false;
	}
	//激活
	public boolean active(String activecode) {
		// TODO Auto-generated method stub
		int row = 0;
		try {
			row = userdao.active(activecode);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row>0?true:false;
	}
	//检查登录
	public user checkLogin(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return userdao.checklogin(username,password);
		
	}
	public List<user> selectall() {
		try {
			return userdao.selectall();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			throw new RuntimeException("数据库查询异常");
		}
	}
	//管理员登录
	public Administrantor checkadminloginBynameAndpass(String name, String pass) {
		Administrantor admin = null;
		try {
			admin = userdao.checkadminloginBynameAndpass(name,pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return admin;
	}
	//admin
	public com.wang.domain.user getuserByuid(String uid) {
		user user = null;
		try {
			user = userdao.getuserByuid(uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	public int submitadduser(user user) {
		int row =0;
		try {
			row = userdao.submitadduser(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
	public void deleteuser(String uid) {
		try {
			userdao.deleteuser(uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void submitedit(user user) {
		try {
			userdao.submitedit(user);
			userdao.updateadmin(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public List<com.wang.domain.user> getalluser() {
		List<user> users = null;
		try {
			users = userdao.getalluser();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	public String getuidByoid(String oid) {
		String uid = null;
		try {
			uid =userdao.getuidByoid(oid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uid;
	}
	
	

}
