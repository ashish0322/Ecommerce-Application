package com.ashish.org.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.type.DoubleType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import com.ashish.org.exception.HandledException;
import com.ashish.org.pojo.Customer;
import com.ashish.org.pojo.Order;
import com.ashish.org.pojo.Product;

public class OrdersDAO extends DAO{

	
	
	// seller view orders 
	
	 public List<Object[]> getPrndingOrders(int sellerName,String status){
			
	 	 
		 	String sql = "select o.orderId,od.orderDetalisId,od.productName,od.unitPrice,o.orderDate,o.userId,od.status "
		 			+ "from orderstable o inner join orderdetails  od on od.orderId = o.orderId where sellerName= :sellerName and od.status = :od.status";
		    SQLQuery query = getSession().createSQLQuery(sql);
		    query.addScalar("o.orderId", new LongType());
		    query.addScalar("od.orderDetalisId", new LongType());
		    query.addScalar("od.productName", new StringType());
		    query.addScalar("od.unitPrice", new DoubleType());
		    query.addScalar("o.userId", new IntegerType());
		    query.addScalar("o.orderDate", new StringType());
		    
		    query.addScalar("od.status", new StringType());
		    query.setInteger("sellerName", sellerName);
		    query.setString("od.status", status);
		    List<Object[]> rows = query.list();
		    		    
			return rows;
		 	    
		 	 }
	 
	 
 public int appproveOrderBySeller(long orderDetailsId) throws HandledException{
		 
		 int rowCount=0;
		 String status = "Approved";
		 try{
			 begin();
			 Query q = getSession().createQuery("update OrderDetails set status = :status where orderDetalisId = :orderDetalisId");
			 q.setString("status", status);
			 
			 q.setLong("orderDetalisId", orderDetailsId);
			 rowCount = q.executeUpdate();
			 
		 }
		 catch (HibernateException e) {
	            rollback();
	            throw new HandledException("Could not find the  order", e);
	        }
		 finally {
			close();
		}
		return rowCount;
	 } 
	
 //getting content for generating bill
 
  public Order getOrderDetails(long orderId){
	  
	  Query query = getSession().createQuery("from Order where orderId = :orderId");
	  query.setLong("orderId", orderId);
	  Order o = (Order) query.uniqueResult();
	  return o;
	
  }
  
  public Product getProductDetails(long productId){
	  
	  Query query = getSession().createQuery("from Product where productId = :productId");
	  query.setLong("productId", productId);
	  Product p = (Product) query.uniqueResult();
	  return p;
	
  }
}
