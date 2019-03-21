package com.wang.domain;

public class OrderItems {
	/*`itemid` varchar(32) NOT NULL,
	  `count` int(11) DEFAULT NULL,
	  `subtotal` double DEFAULT NULL,
	  `pid` varchar(32) DEFAULT NULL,
	  `oid` varchar(32) DEFAULT NULL*/
	
	private String itemid;//订单项的id
	private int count;//订单项内商品的购买数量
	private double subtotal;//订单项小计
	private product product;//订单项内部的商品
	private Orders order;//该订单项属于哪个订单
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
