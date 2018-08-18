package cn.itcast.Dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mysql.fabric.xmlrpc.base.Array;

import cn.itcast.Utils.C3P0Utils;
import cn.itcast.domain.Category;
import cn.itcast.domain.Order;
import cn.itcast.domain.OrderItem;
import cn.itcast.domain.PageBean;
import cn.itcast.domain.Product;
import cn.itcast.domain.User;




public class UserDao {
	public Product getproductbyPid(String pid) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from product where pid=?";
		Product plist=null;
		try {
			plist = qr.query(sql, new BeanHandler<>(Product.class),pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return plist;
	}
	
	//根据分类查询所有商品信息
	public List<Product> getAllproduct(String cid) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from product where cid=?";
		List<Product> plist=null;
		try {
			plist = qr.query(sql, new BeanListHandler<>(Product.class),cid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return plist;
	}
	//查询所有分类
	public List<Category> getCategory() {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from category";
		List<Category> query =null;
		try {
			query = qr.query(sql, new BeanListHandler<>(Category.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return query;
	}

    public User login(User user) {
        User exsitUser = null;
        try {
            // 1.获得QueryRunner核心对象
            QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
            // 2.编写SQL语句
            String sql = "select * from user where username=? and password=?";
            // 3.设置实际参数
            Object[] params = { user.getUsername(), user.getPassword() };
            // 4.执行查询操作
            exsitUser = qr.query(sql, new BeanHandler<>(User.class), params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exsitUser;
    }
    public User findUserByUsername(String username) {
        User exsitUser = null;
        try {
            // 1.获得QueryRunner核心对象
            QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
            // 2.编写SQL语句
            String sql = "select * from user where username=?";
            // 3.设置实际参数
            Object[] params = { username };
            // 4.执行查询操作
            exsitUser = qr.query(sql, new BeanHandler<>(User.class), params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exsitUser;
    }
	public int regist(User user) {
		 int row = 0;
	        try {
	            // 1.获得QueryRunner核心对象
	            QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
	            // 2.编写SQL语句
	            String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?) ";
	            // 3.设置实际参数
	            Object[] params = { user.getUid(),user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode() };
	            // 4.执行查询操作
	            row = qr.update(sql, params);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return row;
		
	}
	//查询所有商品
	public List<Product> getAllproduct() {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from product";
		List<Product> list=null;
		try {
			list=qr.query(sql, new BeanListHandler<>(Product.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}
	///////////
	public List<Product> FindAllProductServletLimit(PageBean pagebean) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from product limit ?,?";
		List<Product> list=null;
		try {
			list=qr.query(sql, new BeanListHandler<>(Product.class),pagebean.getStartIndex(),pagebean.getPageSize());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}
	//用户名查找
	public User getuserByname(String username) {
        User exsitUser = null;
        try {
            // 1.获得QueryRunner核心对象
            QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
            // 2.编写SQL语句
            String sql = "select * from user where username=?";
            // 3.设置实际参数
            Object[] params = { username };
            // 4.执行查询操作
            exsitUser = qr.query(sql, new BeanHandler<>(User.class), params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exsitUser;
	}
	public User checkCode(User user) {
		   User exsitUser = null;
	        try {
	            // 1.获得QueryRunner核心对象
	            QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
	            // 2.编写SQL语句
	            String sql = "select * from user where username=? and password=? ";
	            // 3.设置实际参数
	            Object[] params = { user.getUsername(),user.getPassword()};
	            // 4.执行查询操作
	            exsitUser = qr.query(sql, new BeanHandler<>(User.class), params);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return exsitUser;
	}
	public User getUser(String code) {
		   User exsitUser = null;
	        try {
	            // 1.获得QueryRunner核心对象
	            QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
	            // 2.编写SQL语句
	            String sql = "select * from user where code=? ";
	            // 3.设置实际参数
	            Object[] params = { code};
	            // 4.执行查询操作
	            exsitUser = qr.query(sql, new BeanHandler<>(User.class), params);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return exsitUser;
	}
	public int modify(User user) {
		 int row = 0;
	        try {
	            // 1.获得QueryRunner核心对象
	            QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
	            // 2.编写SQL语句
	            String sql = "update  user set code=? ,state=? where code=? ";
	            // 3.设置实际参数
	            Object[] params = { null,1,user.getCode()};
	            // 4.执行查询操作
	            row = qr.update(sql, params);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return row;
	}
	public User getUser(User user) {
		 User exsitUser = null;
	        try {
	            // 1.获得QueryRunner核心对象
	            QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
	            // 2.编写SQL语句
	            String sql = "select * from user where username=? and password=? ";
	            // 3.设置实际参数
	            Object[] params = { user.getUsername(),user.getPassword()};
	            // 4.执行查询操作
	            exsitUser = qr.query(sql, new BeanHandler<>(User.class), params);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return exsitUser;
	}
	public int getAllCountproduct(String cid) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select count(*) from product where cid=?";
		Long plist=null;
		try {
			plist = (Long) qr.query(sql, new ScalarHandler(),cid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return plist.intValue();
	}
	public List<Product> getAllproductByLimit(String cid, PageBean pagebean) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from product where cid=? limit ?,?";
		List<Product> plist=null;
		try {
			System.out.println(pagebean.getStartIndex());
			plist = qr.query(sql, new BeanListHandler<>(Product.class),cid,pagebean.getStartIndex(),pagebean.getPageSize());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return plist;
	}

	public List<Product> FindHotProduct() {
		
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from product where is_hot=? limit 9";
		List<Product> list=null;
		try {
			list=qr.query(sql, new BeanListHandler<>(Product.class),1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
		
	}
	
	
	
    public void makeOrder(Order order) {
        try {
            // 1.获得QueryRunner核心对象
            QueryRunner qr = new QueryRunner();
            // 2.编写SQL语句
            String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
            // 3.设置实际参数
            Object[] params = { order.getOid(), order.getOrdertime(), order.getTotal(), order.getState(),
                    order.getAddress(), order.getName(), order.getTelephone(), order.getUser().getUid() };
            // 4.执行插入操作
            qr.update(C3P0Utils.getConnection(), sql, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成订单项
     */
    
    public void makeOrderItem(OrderItem orderItem) {
        try {
            // 1.获得QueryRunner核心对象
            QueryRunner qr = new QueryRunner();
            // 2.编写SQL语句
            String sql = "insert into orderitem values(?,?,?,?,?)";
            // 3.设置实际参数
            Object[] params = { orderItem.getItemid(), orderItem.getCount(), orderItem.getSubtotal(),
                    orderItem.getProduct().getPid(), orderItem.getOrder().getOid() };
            // 4.执行插入操作
            qr.update(C3P0Utils.getConnection(), sql, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//根据UID查询个人订单
	@SuppressWarnings("all")
	public List<Order> FindOrderByUid(String uid, int startIndex, int pageSize) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from orders where uid=? limit ?,?";
		List<Order> plist=null;
		try {
			plist =  qr.query(sql, new BeanListHandler(Order.class),uid,startIndex,pageSize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return plist ;
	}

	public List<OrderItem> FindOrderByOid(String oid) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from orderitem where oid=?";
		List<OrderItem>  plist=null;
		try {
			plist =  qr.query(sql, new BeanListHandler(OrderItem.class),oid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return plist ;
	}

	public String FindOrderItemByorderItemId(String itemid) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select pid from orderitem where itemid=?";
		String  pid=null;
		try {
			pid =  (String) qr.query(sql, new ScalarHandler(),itemid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pid ;
	}

	public int gettotal(String uid) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select count(*) from orders where uid=?";
		Long plist=null;
		try {
			plist = (Long) qr.query(sql, new ScalarHandler(),uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return plist.intValue();
	}

    public List<Order> FindOrderByUid(String uid, PageBean<Order> pagebean) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from orders where uid=? limit ?,?";
		String sql1="select * from orderitem o,product p where o.pid=p.pid and o.oid=?";
		List<Order> Orders=null;
		try {
			Orders =  qr.query(sql, new BeanListHandler<Order>(Order.class),uid,pagebean.getStartIndex(),pagebean.getPageSize());
			if(Orders!=null && Orders.size()>0){
				for(Order order:Orders){
					List<OrderItem>orderItems=new ArrayList<>();
					List<Map<String, Object>> listmap = qr.query(sql1, new MapListHandler(), order.getOid());
					for(Map<String,Object> map:listmap){
						Product product=new Product();
						BeanUtils.populate(product,map);
						OrderItem orderItem=new OrderItem();
						BeanUtils.populate(orderItem,map);	
						orderItem.setProduct(product);
						orderItems.add(orderItem);
					}
					
					order.setList(orderItems);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Orders ;
	}

	@SuppressWarnings("deprecation")
	public Order FindANOrderByOid(String oid) {
		Order order=null;
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		try {
		String sql1="select * from orders where oid=?";
		String sql="select * from orderitem o,product p where o.pid=p.pid and oid=?";
		 order = qr.query(sql1, oid, new BeanHandler<>(Order.class));
		
		List<OrderItem>orderitems=new ArrayList<>();
		
			List<Map<String, Object>> listmap = qr.query(sql, oid, new MapListHandler());
			for(Map<String, Object> map:listmap){
				OrderItem orderitem=new OrderItem();
				Product product=new Product();
				BeanUtils.populate(product, map);
				BeanUtils.populate(orderitem, map);
				orderitem.setProduct(product);
				orderitems.add(orderitem);
			}
			order.setList(orderitems);
			return order;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}

	public int ModifyOrderMessage_PayFor(Order order) {
		int rows=0;
		try {
			QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
			String sql="update orders set state=?,address=?,name=?,telephone=? where oid=?";
			Object[]param={1,order.getAddress(),order.getName(),order.getTelephone(),order.getOid()}; 
			rows = qr.update(sql, param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rows;
	}

	public Order FindOrderByOid_Byoid(String oid) {
		Order order=null;
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		try {
		String sql1="select * from orders where oid=?";
		
		 order = qr.query(sql1, oid, new BeanHandler<>(Order.class));

		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return order;
	}

	public int FindAllProductCount() {

		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		Long row=null;
		try {
		String sql1="select count(*) from product ";
		
		row = (Long)qr.query(sql1, new ScalarHandler());

		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return row.intValue();
	}



}
