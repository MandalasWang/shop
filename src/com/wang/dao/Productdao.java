package com.wang.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.wang.domain.OrderItems;
import com.wang.domain.Orders;
import com.wang.domain.product;
import com.wang.utils.JDBCUtils;
import com.wang.vo.condition;

public class Productdao {
	private QueryRunner runner = new QueryRunner(JDBCUtils.getdatasource());

	// 获取热门商品
	public List<product> findHotProducts() throws SQLException {
		String sql = "select * from product where is_hot =? limit ?,?";
		return runner.query(sql, new BeanListHandler<product>(product.class), 1, 0, 9);
	}

	// 获取最新商品
	public List<product> findNewProducts() throws SQLException {
		String sql = "select * from product order by pdate desc limit ?,?";
		return runner.query(sql, new BeanListHandler<product>(product.class), 0, 9);

	}

	// 根据cid获取商品数
	public int getCount(String cid) throws SQLException {
		String sql = "select count(*) from product where cid=?";
		Long query = (Long) runner.query(sql, new ScalarHandler(), cid);
		return query.intValue();
	}

	// 根据cid获取商品并且进行分类
	public List<product> findProductByPage(String cid, int index, int currentCount) throws SQLException {
		String sql = "select * from product where cid = ? limit ?,?";
		return runner.query(sql, new BeanListHandler<product>(product.class), cid, index, currentCount);
	}

	// 根据pid查找浏览过的商品记录
	public product findProductByPid(String pid) throws SQLException {
		String sql = "select * from product where pid = ?";
		return runner.query(sql, new BeanHandler<product>(product.class), pid);
	}

	// 根据pimage、pname查询商品的信息
	public product findproBypimgandpname(String pimage) throws SQLException {
		String sql = "select * from product where pimage = ? ";
		return runner.query(sql, new BeanHandler<product>(product.class), pimage);

	}
    //根据pname获取商品的信息
	public product findproByandpname(String pname) throws SQLException {
		String sql = "select * from product where pname = ? ";
		return runner.query(sql, new BeanHandler<product>(product.class), pname);

	}
	// 根据Pid获取购物车商品的信息
	public product getcartproduct(String pid) throws SQLException {
		String sql = "select * from product where pid = ?";
		return runner.query(sql, new BeanHandler<product>(product.class), pid);

	}

	// 向orders表插入数据
	public void addOrders(Orders order) throws SQLException {
		String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
		runner.update(sql, order.getOid(), order.getOrdertime(), order.getTotal(), order.getState(), order.getAddress(),
				order.getName(), order.getTelephone(), order.getUser().getUid());
	}

	// 向orderitem表插入数据
	public void addOrderItem(Orders order) throws SQLException {

		String sql = "insert into orderitem values(?,?,?,?,?)";
		List<OrderItems> orderItems = order.getOrderItems();
		for (OrderItems item : orderItems) {
			runner.update(sql, item.getItemid(), item.getCount(), item.getSubtotal(), item.getProduct().getPid(),
					item.getOrder().getOid());
		}

	}

	public List<Orders> findAllOrders(String uid) throws SQLException {
		String sql = "select * from orders where uid=?";
		return runner.query(sql, new BeanListHandler<Orders>(Orders.class), uid);
	}

	public List<Map<String, Object>> findAllOrderItemByOid(String oid) throws SQLException {
		String sql = "select i.count,i.subtotal,p.pimage,p.pname,p.shop_price from orderitem i,product p where i.pid=p.pid and i.oid=?";
		List<Map<String, Object>> mapList = runner.query(sql, new MapListHandler(), oid);
		return mapList;
	}

	public int updateOrderAdrr(Orders order) throws SQLException {

		String sql = "update orders set address=?,name =?,telephone=? where oid =?";
		int row = runner.update(sql, order.getAddress(), order.getName(), order.getTelephone(),order.getOid());
		return row;
	}

	public List<product> getallProduct() throws SQLException {
		String sql = "select * from product ";
		return runner.query(sql, new BeanListHandler<product>(product.class));

	}

	public void saveAdminaddproduct(product product) throws SQLException {
		String sql = "insert into product values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		runner.update(sql, product.getPid(), product.getPname(), product.getMarket_price(), product.getShop_price(),
				product.getPimage(), product.getPdate(), product.getIs_hot(), product.getPdesc(), product.getPflag(),
				product.getCid(),product.getCounts(),product.getSalecounts(),product.getImportconts(),product.getCost(),
				product.getSales());

	}

	public void DelproductBypid(String pid) throws SQLException {
		String sql = "Delete from product where pid = ?";
		runner.update(sql, pid);

	}

	public List<product> showproductBycon(condition condition) throws SQLException {
		List<String> list = new ArrayList<String>();
		String sql = "select * from product where 1=1 ";
		if (condition.getPname() != null && !condition.getPname().trim().equals("")) {
			sql += " and pname like ?";
			list.add("%" + condition.getPname().trim() + "%");
		}
		if (condition.getIsHot() != null && !condition.getIsHot().trim().equals("")) {
			sql += " and is_hot=?";
			list.add(condition.getIsHot().trim());
		}
		if (condition.getCid() != null && !condition.getCid().trim().equals("")) {
			sql += " and cid=? ";
			list.add(condition.getCid().trim());
		}
		return runner.query(sql, new BeanListHandler<product>(product.class), list.toArray());

	}

	public void updateproduct(product product) throws SQLException {
		String sql = "update product set pname=?,market_price=?,shop_price=?,pimage=?,pdate=?,is_hot=?,pdesc=?,pflag=?,cid=?,counts=?,salecounts=?,importconts=?,cost=?,sales=? where pid = ?";
		runner.update(sql, product.getPname(), product.getMarket_price(), product.getShop_price(),
				product.getPimage(), product.getPdate(), product.getIs_hot(), product.getPdesc(), product.getPflag(),
				product.getCid(),product.getCounts(),product.getSalecounts(),product.getImportconts(),
				product.getCost(),product.getSales(),product.getPid());
	}

	public List<Object> serchpnameByword(String word) throws SQLException {
		String sql = "select * from product where pname like ? limit 0,7";
		List<Object> object = runner.query(sql, new ColumnListHandler("pname"), "%" + word + "%");
		return object;
	}

	public product getproductBypname(String pname) throws SQLException {
		String sql = "select * from product where pname =?";
		product product = runner.query(sql, new BeanHandler<product>(product.class), pname);
		return product;
	}

	// 减去提交订单的商品的数量
	public void updateproductcount(String pid, int oldBuyNum) throws SQLException {
		String sql = "update product set counts=counts-? where pid = ?";
		runner.update(sql, oldBuyNum, pid);

	}

	public List<product> getmobileBypname(String pname, String cid, int index, int currentCount) throws SQLException {
		String sql = "select * from product where cid = " + cid + " and pname like ? limit ?,?";
		List<product> list = runner.query(sql, new BeanListHandler<product>(product.class), "%" + pname + "%", index,
				currentCount);
		return list;
	}

	// 根据价格区间查询手机
	public List<product> getmobileByPrice(Double price, String cid, int index, int currentCount) throws SQLException {
		List<product> list = new ArrayList<product>();
		String sql = "select * from product where cid =" + cid + " ";
		if (price <= 1000d) {
			sql += " and shop_price < ? limit ?,? ";
			list = runner.query(sql, new BeanListHandler<product>(product.class), price, index, currentCount);
		} else if (price <= 1500d) {
			sql += " and shop_price between 1000 and ? limit ?,? ";
			list = runner.query(sql, new BeanListHandler<product>(product.class), price, index, currentCount);
		} else if (price <= 2500d) {
			sql += " and shop_price between 1500 and ? limit ?,?";
			list = runner.query(sql, new BeanListHandler<product>(product.class), price, index, currentCount);
		} else if (price <= 3500d) {
			sql += " and shop_price between 2500 and ? limit ?,?  ";
			list = runner.query(sql, new BeanListHandler<product>(product.class), price, index, currentCount);
		} else if (price <= 4500d) {
			sql += " and shop_price between 3500 and ? limit ?,?  ";
			list = runner.query(sql, new BeanListHandler<product>(product.class), price, index, currentCount);
		}

		else if (price > 4500.1d) {
			sql += " and shop_price > ? limit ?,?";
			list = runner.query(sql, new BeanListHandler<product>(product.class), price, index, currentCount);
		}
		return list;
	}

	// 根据电脑品牌查询电脑
	public List<product> getcomputerBypname(String pname, String cid, int index, int currentCount) throws SQLException {
		String sql = "select * from product where cid= " + cid + " and pname like ? limit ?,?";
		List<product> list = runner.query(sql, new BeanListHandler<product>(product.class), "%" + pname + "%", index,
				currentCount);
		return list;
	}

	public List<product> getcomputerByPrice(Double price, String cid, int index, int currentCount) throws SQLException {
		List<product> list = new ArrayList<product>();
		String sql = "select * from product where cid=" + cid + " ";
		if (price <= 2000d) {
			sql += " and shop_price < ? limit ?,?";
			list = runner.query(sql, new BeanListHandler<product>(product.class), price, index, currentCount);
		} else if (price <= 3000d) {
			sql += " and shop_price between 2000 and ? limit ?,? ";
			list = runner.query(sql, new BeanListHandler<product>(product.class), price, index, currentCount);
		} else if (price <= 4500d) {
			sql += " and shop_price between 3000 and ? limit ?,? ";
			list = runner.query(sql, new BeanListHandler<product>(product.class), price, index, currentCount);
		}

		else if (price > 4500.1d) {
			sql += " and shop_price > ? limit ?,?";
			list = runner.query(sql, new BeanListHandler<product>(product.class), price, index, currentCount);
		}
		return list;
	}

	public int getproductBynametotalCount(String cid, String pname) throws SQLException {
		String sql = "select count(*) from product where cid =? and pname like ?";
		Long qLong = (Long) runner.query(sql, new ScalarHandler(), cid, "%" + pname + "%");
		return qLong.intValue();
	}

	public int getproductBypricetotalCount(Double price, String cid) throws SQLException {
		Long qLong = 0L;
		String sql = "select count(*) from product where cid= ?  ";
		if (price <= 1000d) {
			sql += " and shop_price < ? ";
			qLong = (Long) runner.query(sql, new ScalarHandler(), cid, price);
		} else if (price <= 1500d) {
			sql += "  and shop_price between 1000 and ? ";
			qLong = (Long) runner.query(sql, new ScalarHandler(), cid, price);
		} else if (price <= 2500d) {
			sql += "  and shop_price between 1500 and ?  ";
			qLong = (Long) runner.query(sql, new ScalarHandler(), cid, price);
		} else if (price <= 4000d) {
			sql += "  and shop_price between 2500 and ?  ";
			qLong = (Long) runner.query(sql, new ScalarHandler(), cid, price);
		} else if (price > 4000.1d) {
			sql += " and shop_price > ? ";
			qLong = (Long) runner.query(sql, new ScalarHandler(), cid, price);
		}
		return qLong.intValue();
	}

	public int getproductcBypricetotalCount(Double price, String cid) throws SQLException {
		Long qLong = 0L;
		String sql = "select count(*) from product where cid= ?  ";
		if (price <= 2000d) {
			sql += " and shop_price < ? ";
			qLong = (Long) runner.query(sql, new ScalarHandler(), cid, price);
		} else if (price <= 3000d) {
			sql += " and shop_price between 2000 and ?";
			qLong = (Long) runner.query(sql, new ScalarHandler(), cid, price);
		} else if (price <= 4500d) {
			sql += " and shop_price between 3000 and ?  ";
			qLong = (Long) runner.query(sql, new ScalarHandler(), cid, price);
		}

		else if (price > 4500.1d) {
			sql += " and shop_price > ? ";
			qLong = (Long) runner.query(sql, new ScalarHandler(), cid, price);
		}
		return qLong.intValue();
	}

	public List<product> getTabletPCBypname(String pname, String cid, int index, int currentCount) throws SQLException {
		String sql = "select * from product where cid= " + cid + " and pname like ? limit ?,?";
		List<product> list = runner.query(sql, new BeanListHandler<product>(product.class), "%" + pname + "%", index,
				currentCount);
		return list;
	}

	public int getproductTabletBypricetotalCount(String cid, Double price) throws SQLException {
		Long qLong = 0L;
		String sql = "select count(*) from product where cid = " + cid + "";
		if (price <= 1000d) {
			sql += " and shop_price < ? ";
			qLong = (Long) runner.query(sql, new ScalarHandler(), price);
		} else if (price <= 2000d) {
			sql += " and shop_price between 1000 and ?";
			qLong = (Long) runner.query(sql, new ScalarHandler(), price);
		} else if (price <= 3000d) {
			sql += " and shop_price between 2000 and ?  ";
			qLong = (Long) runner.query(sql, new ScalarHandler(), price);
		}

		else if (price > 3001d) {
			sql += " and shop_price > ? ";
			qLong = (Long) runner.query(sql, new ScalarHandler(), price);
		}
		return qLong.intValue();
	}

	public List<product> getTabletPCByprice(Double price, String cid, int index, int currentCount) throws SQLException {
		List<product> list = new ArrayList<product>();
		String sql = "select * from product where cid=" + cid + " ";
		if (price <= 1000d) {
			sql += " and shop_price < ? limit ?,?";
			list = runner.query(sql, new BeanListHandler<product>(product.class), price, index, currentCount);
		} else if (price <= 2000d) {
			sql += " and shop_price between 1000 and ? limit ?,? ";
			list = runner.query(sql, new BeanListHandler<product>(product.class), price, index, currentCount);
		} else if (price <= 3000d) {
			sql += " and shop_price between 2000 and ? limit ?,? ";
			list = runner.query(sql, new BeanListHandler<product>(product.class), price, index, currentCount);
		}

		else if (price > 3001d) {
			sql += " and shop_price > ? limit ?,?";
			list = runner.query(sql, new BeanListHandler<product>(product.class), price, index, currentCount);
		}
		return list;
	}

	public List<product> getSoudBypname(String pname, String cid, int index, int currentCount) throws SQLException {
		String sql = "select * from product where cid= " + cid + " and pname like ? limit ?,?";
		List<product> list = runner.query(sql, new BeanListHandler<product>(product.class), "%" + pname + "%", index,
				currentCount);
		return list;
	}

	public int getproductSoudBypricetotalCount(String cid, Double price) throws SQLException {
		Long qLong = 0L;
		String sql = "select count(*) from product where cid = " + cid + "";
		if (price <= 100d) {
			sql += " and shop_price < ? ";
			qLong = (Long) runner.query(sql, new ScalarHandler(), price);
		} else if (price <= 300d) {
			sql += " and shop_price between 100 and ?";
			qLong = (Long) runner.query(sql, new ScalarHandler(), price);
		} else if (price <= 500d) {
			sql += " and shop_price between 300 and ?  ";
			qLong = (Long) runner.query(sql, new ScalarHandler(), price);
		} else if (price <= 1000d) {
			sql += " and shop_price between 500 and ?  ";
			qLong = (Long) runner.query(sql, new ScalarHandler(), price);
		} else if (price > 1001d) {
			sql += " and shop_price > ? ";
			qLong = (Long) runner.query(sql, new ScalarHandler(), price);
		}
		return qLong.intValue();
	}

	public void updateproductByid(String pid, int salescount) throws SQLException {
		String sql = "UPDATE product SET salecounts = salecounts+ ? WHERE pid =?";
		runner.update(sql, salescount, pid);

	}

	public int getsalecounts(String pid) throws SQLException {
		String sql = "select salecounts from product where pid = ?";
		Long query = (Long) runner.query(sql, new ScalarHandler(), pid);
		return query.intValue();
	}

	public void updateproductsales(String pid, double subtotal) throws SQLException {
		String sql = "UPDATE product SET sales = sales+ ? WHERE pid =?";
		runner.update(sql, subtotal, pid);

	}

	public int getproductcounts(String pid) throws SQLException {
		String sql = "select counts from product where pid =?";
		Integer query = (Integer) runner.query(sql, new ScalarHandler(), pid);
		return query.intValue();
	}

	public int getallproductcountsBycid(String cid) throws SQLException {
		String sql = "select sum(salecounts) from product where cid =?";
		product product =runner.query(sql, new BeanHandler<product>(product.class),cid);
		return product.getSalecounts();
	}

	public double  getallproductsalesBycid(String cid) throws SQLException {
		String sql = "select sum(sales) from product where cid =?";
		product product =runner.query(sql, new BeanHandler<product>(product.class),cid);
		return product.getSales();
	}

	public product getpidByitem(String id) throws SQLException {
		String sql ="SELECT * FROM product WHERE pid ="
				+ "(SELECT pid FROM orderitem WHERE oid= ?)";
		 product product = runner.query(sql, new BeanHandler<product>(product.class),id);
		
		 return product;
	}

	public void updateproductBypid(String pid, Integer importconts) throws SQLException {
	 String sql = "update product set importconts =importconts+? where pid = ? ";
		runner.update(sql,importconts,pid);
	}

	public void updateproductcountBypid(String pid, Integer importconts) throws SQLException {
		String sql = "update product set counts = counts+? where pid =?";
		runner.update(sql,importconts,pid);
	}

	public List<product> showimportproductBycon(condition condition) throws SQLException {
		List<String> list = new ArrayList<String>();
		String sql = "select * from product where 1=1 ";
		if (condition.getPname() != null && !condition.getPname().trim().equals("")) {
			sql += " and pname like ?";
			list.add("%" + condition.getPname().trim() + "%");
		}
		if (condition.getIsHot() != null && !condition.getIsHot().trim().equals("")) {
			sql += " and is_hot=? ";
			list.add(condition.getIsHot().trim());
		}
		if (condition.getCid() != null && !condition.getCid().trim().equals("")) {
			sql += " and cid=? ";
			list.add(condition.getCid().trim());
		}
		return runner.query(sql, new BeanListHandler<product>(product.class), list.toArray());

	}

	public List<product> getallProductordered() throws SQLException {
		String sql = "select * from product order by salecounts desc ";
		 List<product> list = runner.query(sql, new BeanListHandler<product>(product.class));
		return list;
	}

}
