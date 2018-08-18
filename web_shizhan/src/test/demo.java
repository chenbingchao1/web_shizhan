package test;

import java.util.List;

import org.junit.Test;

import cn.itcast.Service.UserService;
import cn.itcast.domain.Category;
import cn.itcast.domain.Product;

public class demo {
	UserService service=new UserService();
	@Test
	public void getALl() {
		List<Product> list = service.getAllproduct();
		System.out.println(list);
	}
	@Test
	public void getOne() {
		List<Category> list = service.getCategory();
		System.out.println(list);
	}
	@Test
	public void getOne1() {
		List<Category> list = service.getCategory();
		System.out.println(list);
	}
}
