package com.wang.service;

import java.sql.SQLException;
import java.util.List;

import com.wang.dao.Categorydao;
import com.wang.domain.Category;

public class Categoryservice {
	Categorydao categorydao = new Categorydao();

	// ��ȡ��Ʒ�������
	public List<Category> getcategory() {
		List<Category> categories = null;
		try {
			categories = categorydao.getcategory();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categories;
	}

	// ����cid��ȡ��Ʒ�����
	public Category getcategoryBycid(String cid) {
		// TODO Auto-generated method stub
		Category category = null;
		try {
			category = categorydao.getcategoryBycid(cid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return category;
	}

	public void Delcategory(String cid) {
		try {
			categorydao.Delcategory(cid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void Categoryupdate(Category category) {
		try {
			categorydao.Categoryupdate(category);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void addcetegory(Category category) {
		try {
			categorydao.addcetegory(category);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
