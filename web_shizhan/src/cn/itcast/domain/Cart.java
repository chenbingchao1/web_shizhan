package cn.itcast.domain;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart implements Serializable {
	private double total=0.0;
	private Map<String,CartItem> map=new LinkedHashMap<>();
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Map<String, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
	public Cart() {
		// TODO Auto-generated constructor stub
	}
	/*public static Map<String,CartItem> addCartItem(CartItem cartItem){
		
		
		return null;
	}*/
	public void addCartItem(CartItem cartItem){
		String pid = cartItem.getProduct().getPid();
		CartItem oldCartItem = this.getMap().get(pid);
		if(oldCartItem==null){
			this.getMap().put(pid, cartItem);
		}else{
			oldCartItem.setCount(oldCartItem.getCount()+cartItem.getCount());
		}
			
		this.total+=cartItem.getSubtotal();
			
	}
	public void delCartItem(CartItem cartItem){
		String pid = cartItem.getProduct().getPid();
		
			this.getMap().remove(pid);
		
		this.total=this.total-cartItem.getSubtotal();
			
	}
	public void ClearCartItem(){
		this.map.clear();
		this.total=0.0;
	}
	
}
