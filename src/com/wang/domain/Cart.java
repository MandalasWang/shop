package com.wang.domain;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	//�ù��ﳵ�д洢��n��������
		private Map<String,CartItem> cartItems = new HashMap<String,CartItem>();
		
		//��Ʒ���ܼ�
		private double total;

		public Map<String, CartItem> getCartItems() {
			return cartItems;
		}

		public void setCartItems(Map<String, CartItem> cartItems) {
			this.cartItems = cartItems;
		}

		public double getTotal() {
			return total;
		}

		public void setTotal(double total) {
			this.total = total;
		}
		
}
