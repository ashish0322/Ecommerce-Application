package com.ashish.org.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.ashish.org.exception.HandledException;
import com.ashish.org.pojo.AdministratorLogin;
import com.ashish.org.pojo.Seller;
import com.ashish.org.pojo.Customer;

public class LoginDAO extends DAO {
	
	public LoginDAO(){
		
	}

	public AdministratorLogin validateLogin(String email,String password) throws HandledException{
		
		try {
			
            Query q=getSession().createQuery
            		("from AdministratorLogin l where l.email= :email and l.password = :password");
            q.setString("email",email);
            q.setString("password", password);
            AdministratorLogin administratorLogin = (AdministratorLogin) q.uniqueResult();
            return administratorLogin;
		}
		catch(HibernateException e){
			rollback();
			throw new HandledException("Username and password not matching"+email,e);
		}
		finally {
			close();
		}
				
	}
	
	public Seller validateSellerLogin(String username,String password) throws HandledException{
		System.out.println("Inside seller login verification");
		try{
			
			Query q = getSession().createQuery
					("from Seller e where e.userName = :username and e.password = :password");
			q.setString("username", username);
			q.setString("password", password);
			Seller seller = (Seller) q.uniqueResult();
			return seller;
		}
		catch(HibernateException e){
			rollback();
			throw new HandledException("Username and password not matching" +username,e);
		}
		finally {
			close();
		}
		
	}
	
	@SuppressWarnings("unused")
	public Customer validateCustLogin(String username,String password) throws HandledException{
		System.out.println("Inside cust login verification");
		try{
			
			Query q = getSession().createQuery
					("from Customer e where e.userName = :username and e.password = :password");
			q.setString("username", username);
			q.setString("password", password);
			Customer customer = (Customer) q.uniqueResult();
			System.out.println("Inside validate customer login");
			
			if(customer!=null){
				return customer;
			}
	}
		catch(HibernateException e){
			rollback();
			throw new HandledException("Username and password not matching" +username,e);
		}
		finally {
			close();
		}
		return null;
	}
}
