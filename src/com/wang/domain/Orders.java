package com.wang.domain;

import java.util.ArrayList;
import java.util.List;

public class Orders {
	/*
	 * `oid` varchar(32) NOT NULL, `ordertime` datetime DEFAULT NULL, `total`
	 * double DEFAULT NULL, `state` int(11) DEFAULT NULL, `address` varchar(30)
	 * DEFAULT NULL, `name` varchar(20) DEFAULT NULL, `telephone` varchar(20)
	 * DEFAULT NULL, `uid` varchar(32) DEFAULT NULL
	 */

	private String oid;// �ö����Ķ�����
	private String ordertime;// �µ�ʱ��
	private double total;// �ö������ܽ��
	private int state;// ����֧��״̬ 1�����Ѹ��� 0����δ����

	private String address;// �ջ���ַ
	private String name;// �ջ���
	private String telephone;// �ջ��˵绰

	private user user;// �ö��������ĸ��û�

	// �ö������ж��ٶ�����
	List<OrderItems> orderItems = new ArrayList<OrderItems>();

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	

	public String getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public user getUser() {
		return user;
	}

	public void setUser(user user) {
		this.user = user;
	}

	public List<OrderItems> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public String toString() {
		return "Orders [oid=" + oid + ", ordertime=" + ordertime + ", total=" + total + ", address=" + address
				+ ", name=" + name + ", telephone=" + telephone + "]";
	}

}
