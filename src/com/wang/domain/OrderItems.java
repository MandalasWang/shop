package com.wang.domain;

public class OrderItems {
	/*`itemid` varchar(32) NOT NULL,
	  `count` int(11) DEFAULT NULL,
	  `subtotal` double DEFAULT NULL,
	  `pid` varchar(32) DEFAULT NULL,
	  `oid` varchar(32) DEFAULT NULL*/
	
	private String itemid;//�������id
	private int count;//����������Ʒ�Ĺ�������
	private double subtotal;//������С��
	private product product;//�������ڲ�����Ʒ
	private Orders order;//�ö����������ĸ�����
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public product getProduct() {
		return product;
	}
	public void setProduct(product product) {
		this.product = product;
	}
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	@Override
	public String toString() {
		return "OrderItems [itemid=" + itemid + ", count=" + count + ", subtotal=" + subtotal + ", product=" + product
				+ ", order=" + order + "]";
	}
	
	
}
