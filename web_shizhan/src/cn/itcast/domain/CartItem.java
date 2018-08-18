package cn.itcast.domain;

import java.io.Serializable;

public class CartItem implements Serializable {
	
	private Product product;
	private double subtotal;
	private int count;

	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public double getSubtotal() {
		return this.getProduct().getShop_price()*this.getCount();
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public CartItem() {
	//空参构造	
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
