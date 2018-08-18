package cn.itcast.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.mail.MessagingException;

import cn.itcast.Dao.UserDao;
import cn.itcast.Utils.C3P0Utils;
import cn.itcast.domain.Category;
import cn.itcast.domain.Order;
import cn.itcast.domain.OrderItem;
import cn.itcast.domain.PageBean;
import cn.itcast.domain.Product;
import cn.itcast.domain.User;

public class UserService {
     UserDao dao=new UserDao();
	
    public Product getbyidproduct(String pid) {
		
		Product plist=dao.getproductbyPid(pid);
		return plist;
	}
	//根据分类查询商品信息
	public List<Product> getidproduct(String cid) {
		
		List<Product> plist=dao.getAllproduct(cid);
		return plist;
	}
	
	//查询所有分类
	public List<Category> getCategory() {
		List<Category> list=dao.getCategory();
		return list;
	}
	//用户登陆
    public User login(User user) {
        UserDao dao = new UserDao();
        return dao.login(user);
    }
    public User findUserByUsername(String username) {
        UserDao dao = new UserDao();
        return dao.findUserByUsername(username);
    }
	public int regist(User user) throws Exception, MessagingException {	
	    int row=dao.regist(user);
	   // MailUtils.sendMail(user.getEmail(), user.getCode());
		return row;
	}
	//查询所有
	public List<Product> getAllproduct() {
		List<Product> products=	dao.getAllproduct();
		return products;
	}
	/////////////
	public PageBean FindAllProductServletLimit(PageBean pagebean) {
		int total=dao.FindAllProductCount();
		pagebean.setTotal(total);
		List<Product> rows=	dao.FindAllProductServletLimit(pagebean);
		pagebean.setRows(rows);
		return pagebean;
	}
	//用户名查找
	public User getUserByname(String username) {
		User user=dao.getuserByname(username);
		return user;
	}
	public User checkCode(User user) {
		User userCode=dao.checkCode(user);
		return userCode;
	}
	public User getUser(String code) {
		User user=dao.getUser(code);
		return user;
	}
	public int modify(User user) {
		int row=dao.modify(user);
		return row;
	}
	public User getUser(User user) {
		User user1=dao.getUser(user);
		return user1;
	}
//分页查询商品
	public PageBean  getbyidproductLimit(String cid, PageBean pagebean) {	
		int pageSize=12;
		 pagebean.setPageSize(pageSize);
		int total=dao.getAllCountproduct(cid);
		pagebean.setTotal(total);
		 
		List<Product> plist=dao.getAllproductByLimit(cid,pagebean);
		
		pagebean.setRows(plist);

		return pagebean;
	}
	//查询热门
	public List<Product> FindHotProduct() {
		List<Product> plist=dao.FindHotProduct();
		return plist;
	}
	//订单生成
	public int makeOrder(Order order) {
		Connection con = C3P0Utils.getConnection();
		try {
			con.setAutoCommit(false);
			dao.makeOrder(order);
			//System.out.println(1/0);
			for(OrderItem orderItem:order.getList()){
				dao.makeOrderItem(orderItem);
			}
			con.commit();
			return 3;
		} catch (SQLException e) {
			System.out.println("-------------");
			try {
				con.rollback();
			} catch (SQLException e1) {
				 throw new RuntimeException(e);
			}
			return -1;
		}
		
		/* try {
            con.setAutoCommit(false);
            dao.makeOrder(order);
            for(OrderItem orderItem:order.getList()){
                dao.makeOrderItem(orderItem);
            }
            con.commit();
            return 3;
        } catch (Exception e) {
            System.out.println("-------------");
            try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
            return -1;
        }*/
		 
		
	
	}
	public List<Order> FindOrderByUid(String uid, int pageNumber) {
		int pageSize=1;
		int startIndex=(pageNumber-1)*pageSize;
		List<Order> order=dao.FindOrderByUid(uid,startIndex,pageSize);
		return order;
	}
	public List<OrderItem> FindOrderItemByOid(String oid) {
		List<OrderItem> OrderItem=dao.FindOrderByOid(oid);
		return OrderItem;
	}
	
	public String FindOrderItemByorderItemId(String itemid) {
		String pid=dao.FindOrderItemByorderItemId(itemid);
		return pid;
	}
	///////////////////////查询订单///////////////////////////////
	public PageBean<Order> FindOrderByUidPageFor(String uid, PageBean pagebean) {
		int pageSize=2;
		int startIndex=(pagebean.getPageNumber()-1)*pageSize;
		pagebean.setPageSize(pageSize);
		pagebean.setStartIndex(startIndex);
		
		int total=dao.gettotal(uid);
		pagebean.setTotal(total);
		
		
		List<Order> orders=dao.FindOrderByUid(uid,pagebean);
		pagebean.setRows(orders);
		
		return pagebean;
	}
	public Order FindANOrderByOid(String oid) {
		Order order=dao.FindANOrderByOid(oid);
		return order;
	}
	public int ModifyOrderMessage_PayFor(Order order) {
		int rows=dao.ModifyOrderMessage_PayFor(order);
		return rows;
	}
	public Order FindOrderByOid(String oid) {
		Order order=dao.FindOrderByOid_Byoid(oid);
		return order;
	}


}
