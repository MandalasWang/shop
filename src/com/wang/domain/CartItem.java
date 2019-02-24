package com.wang.domain;

public class CartItem {
	private product product;
	private int buyNum;
	private double subtotal;
	
	public product getProduct() {
		return product;
	}
	public void setProduct(product product) {
		this.product = product;
	}
	public int getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	
}
