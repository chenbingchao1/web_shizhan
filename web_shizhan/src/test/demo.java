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
		System.out.println(list);
	}
	@Test
	public void getOne1() {
		List<Category> list = service.getCategory();
		System.out.println(list);
	}
	@Test
	public void getOne2() {
		List<Category> list = service.getCategory();
		System.out.println("真正的实战项目");
	}
	@Test
	public void getOne3() {
		List<Category> list = service.getCategory();
		System.out.println("1111再次提交，此时已经忽略class文件，推送后，class文件应该不会冲突");
	}
}
