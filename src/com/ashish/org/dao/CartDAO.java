package com.ashish.org.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ashish.org.exception.HandledException;
import com.ashish.org.pojo.CustomerCart;
import com.ashish.org.pojo.Order;
import com.ashish.org.pojo.OrderDetails;
import com.ashish.org.pojo.Product;
import com.ashish.org.pojo.Seller;
import com.ashish.org.pojo.Customer;

public class CartDAO extends DAO{

	@Autowired
	@Qualifier("productDao")
	 private ProductDAO productDao;
	
	public void saveCartItems(List<Product> cartProductsList, Customer customer){
		int totalInserts=0;
		
		CustomerCart tempcart = null;
		
		try{
			begin();	
			for(Product c : cartProductsList){
			    tempcart = new CustomerCart();
				
				tempcart.setProductName(c.getProductName());
				tempcart.setCategoryName(c.getCategory_name());
				tempcart.setCompany(c.getCompany());
				tempcart.setPhotoName(c.getPhotoName());
				tempcart.setQuantity(1);
				tempcart.setPrice(c.getPrice());
				tempcart.setCustomerId(customer.getUserId());
				tempcart.setProductId(c.getProductId());
				tempcart.setSellerName(c.getEmployee().getFirstName());
				
			
				getSession().save(tempcart);
				
				//return totalInserts++;
			}
			commit();
		}
			
		
		catch (HibernateException e) {
            rollback();
            try {
				throw new HandledException("Could not save cart");
			} catch (HandledException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
        finally {
			close();
		}
		//return totalInserts;
		
	
	}
	
	// database logic for placing order
	public long placeOrder(String status,String shipper,String orderDate,Customer customer,
			List<Product> orderDet,double total){
		Long i= (long) 0;
		try{
			begin();
			Order order = new Order(customer,status,shipper,total,orderDate);
				order.setCustomer(customer);
				order.setStatus(status);
				order.setOrderDate(orderDate);
				order.setOrderTotal(total);
				order.setShipperName(shipper);
			
			for(Product p : orderDet){
				long pid= p.getProductId();
				double price =p.getPrice();
				Seller seller = p.getEmployee();
				String productName = p.getProductName();
				System.out.println(pid);
				System.out.println(price);
				
			OrderDetails orderDetails = new OrderDetails(price,pid,order,seller,productName);
				orderDetails.setProductId(pid);
				orderDetails.setUnitPrice(price);
				orderDetails.setOrder(order);
				orderDetails.setStatus("pending");
				
		     getSession().save(orderDetails);
			//getSession().save(orderDetails);
		}
			i = (Long)getSession().save(order);
			commit();
		  return i;
		}
		catch(HibernateException e){
			
		}
		finally {
			close();
		}
		return i;
		
	}
	 
	// database logic to get saved items in cart from db 
	public long getSavedItemsInCart(long customerId){

		
			Query query  = getSession().createQuery("select count(*) from CustomerCart where customerId= :customerId");
			query.setParameter("customerId", customerId);
			long count = (Long) query.uniqueResult();
			return count;
			
		}
	
	public List<CustomerCart> getSavedListOfProductsInCart(long customerId){
		
		String sql = "SELECT * FROM customercart WHERE customerId = :customerId";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.addEntity(CustomerCart.class);
		query.setParameter("customerId", customerId);
		List<CustomerCart> results = query.list();
		
		return results;
		
	}
	
	public int refreshCart(long customerId){
		
		try{
			
		begin();
		Query query = getSession().createQuery("delete CustomerCart where customerId = :customerId");
		query.setLong("customerId", customerId);
		int result = query.executeUpdate();
		commit();
	    return result;
		}
		catch(HibernateException e){
			
		}
		finally {
			close();
		}
		return 0;
	}
	
	
}
