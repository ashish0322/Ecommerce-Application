package com.ashish.org.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import com.ashish.org.exception.HandledException;
import com.ashish.org.pojo.Seller;
import com.ashish.org.pojo.Address;
import com.ashish.org.pojo.Customer;
import com.ashish.org.pojo.CustomerCart;
import com.ashish.org.pojo.Order;
import com.ashish.org.pojo.OrderDetails;



public class UserDAO extends DAO{

	public UserDAO(){
		
	}
	
	public int registerUser(String fname,String lname,String email,String username,String password,
			String street,String city,String state,String country,String contact,String pinCode){
		int i=0;
		Customer customer = null;
		try {
			begin();
			Address address = new Address(street,city,state,country,contact,pinCode);
			customer = new Customer(fname, lname,email,username,password);
			
			customer.setFirstName(fname);
			customer.setLastName(lname);
			customer.setEmailId(email);
			customer.setUserName(username);
			customer.setPassword(password);
			customer.setAddress(address);
			
			address.setCustomer(customer);
			
			 i = (Integer) getSession().save(customer);
			commit();
			return i;
		}
		catch(HibernateException e){
			e.printStackTrace();
		}
		finally{
            close();
        }
		
		return i;
	}
	
	
	public Seller registerEmployee(String fname,String lname,String email,String username,String password){
		
		Seller seller = null;
		try {
			begin();
			seller = new Seller(fname, lname,email,username,password);
			
			getSession().save(seller);
			commit();
			return seller;
		}
		catch(HibernateException e){
			e.printStackTrace();
		}
		finally{
            close();
        }
		
		return seller;
	}
	
	public Seller getEmpName(String userName)
            throws HandledException {
        try {
            begin();
            Query q = getSession().createQuery("from Seller where userName = :userName");
            q.setString("userName", userName);
            Seller seller = (Seller) q.uniqueResult();
            commit();
            return seller;
        } catch (HibernateException e) {
            rollback();
            throw new HandledException("Could not get seller " + userName, e);
        }
        finally{
            close();
        }
    }
	
	// used to get customer while placing order 
	public Customer getCustomerName(String userName)
            throws HandledException {
        try {
            begin();
            Query q = getSession().createQuery("from Customer where userName = :userName");
            q.setString("userName", userName);
            Customer customer = (Customer) q.uniqueResult();
            commit();
            return customer;
        } catch (HibernateException e) {
            rollback();
            throw new HandledException("Could not get customer " + userName, e);
        }
        finally{
            close();
        }
    }
	
	// Ajx check for username
	 public Customer userExists(String userName){
	   
	            Query q = getSession().createQuery("from Customer where userName= :userName");
	            q.setString("userName",userName);
	            Customer c = (Customer) q.uniqueResult();
	        	          
	            return c;
	 
	    }
	 
	 // Ajax check for email
	 public Customer emailExists(String emailId){
	  
		 Query q = getSession().createQuery("from Customer where emailId= :emailId");
         q.setString("emailId",emailId);
         Customer c = (Customer) q.uniqueResult();
     	        
         return c;
		 
	 }
	 
	// view placed orders
	 public List<Object[]> getOrdersPlaced(int userId){
		
		 	 
		 	String sql = "select o.orderId,od.productName,od.unitPrice,o.orderTotal,o.orderDate,o.shipperName,od.status from orderstable o inner join orderdetails  od on od.orderId = o.orderId where userId= :userId";
		    SQLQuery query = getSession().createSQLQuery(sql);
		    query.addScalar("o.orderId", new LongType());
		    query.addScalar("od.productName", new StringType());
		    query.addScalar("od.unitPrice", new DoubleType());
		    query.addScalar("o.orderTotal", new DoubleType());
		    query.addScalar("o.orderDate", new StringType());
		    query.addScalar("o.shipperName", new StringType());
		    query.addScalar("od.status", new StringType());
		    query.setInteger("userId", userId);
		    List<Object[]> rows = query.list();
		    		    
			return rows;
		 	    
		 	 }
	 
	 public List<OrderDetails> getOrderDetailsPlaced(int userId){
			
		 	String sql = "SELECT * FROM orderdetails WHERE userId = :userId";
			SQLQuery query = getSession().createSQLQuery(sql);
			query.addEntity(Order.class);
			query.setInteger("userId", userId);
			List<OrderDetails> results = query.list();
		 
		    return results;
		 
	 }
	 
	 //  get seller names list
	 public List<Seller> getSellersList(){
		 
		 List<Seller> sellerList = new ArrayList<Seller>();
		 sellerList= getSession().createQuery("from Seller").list();
		 if(sellerList!=null){
			 return sellerList;
		 }
		 return sellerList;
	 }
}
