package com.ashish.org.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;

import com.ashish.org.exception.HandledException;
import com.ashish.org.pojo.Category;
import com.ashish.org.pojo.Seller;
import com.ashish.org.pojo.Product;

public class ProductDAO extends DAO{
	
	

	public ProductDAO(){
		
	}
	
	public Product addProduct(String productName,String company,double price,
			String description,int quantity,String photoName,Seller seller,
			String categoryName,Category category,String sellerName) throws HandledException{
		 try {
	            begin();
	            Product product = new Product(productName, company, seller,  
	            		categoryName, price, description, quantity, photoName,category,sellerName);
	            getSession().save(product);
	            commit();
	            return product;
	        } catch (HibernateException e) {
	            rollback();
	            //throw new AdException("Could not create advert", e);
	            throw new HandledException("Exception while creating advert: " + e.getMessage());
	        }
		 finally {
				close();
			}
		
	}
	
	
	 public List<Product> productList() throws HandledException {
	    	List<Product> list = new ArrayList<Product>();
	        try {
	            begin();
	            Query q = getSession().createQuery("from Product");
	            list = q.list();
	            commit();
	            return list;
	        } catch (HibernateException e) {
	            rollback();
	            throw new HandledException("Could not list the products", e);
	        }
	        finally {
				close();
			}
	    }
	 
	 // admin work area product list by using pagination 
	 
	 public List<Product> getProductByPaginationAdmin(int pageCount,int recordsPerPage) throws HandledException{
		 
		 try{
			 begin();
			 Criteria criteria = getSession().createCriteria(Product.class);
			 criteria.setFirstResult(pageCount);
			 criteria.setMaxResults(recordsPerPage);
			 
			 List<Product> productList = (List<Product>)criteria.list();
			
			 commit();
			 return productList;
		 }
		 catch (HibernateException e) {
	            rollback();
	            throw new HandledException("Could not find the  product", e);
	        }
		 finally {
			close();
		}
		 
	 }
	 
	 
	 // number of products in product table for pagination purpose in admin work area
	 
		public long getTotalNumberOfProducts(){
				 
				 Query query = getSession()
						 .createQuery("select count(*) from Product");
				 long numberOfrecords= (Long) query.uniqueResult();
				 return numberOfrecords;
			 }
		 
	 
	 // when user selects a particular category for list of products
	 public List<Product> selectedProductList(String category_name) throws HandledException {
	    	List<Product> list = new ArrayList<Product>();
	        try {
	            begin();
	            Query q = getSession().createQuery("from Product where category_name = :category_name");
	            q.setString("category_name", category_name);
	            list = q.list();
	            commit();
	            return list;
	        } catch (HibernateException e) {
	            rollback();
	            throw new HandledException("Could not find the list of products", e);
	        }
	        finally {
				close();
			}
	        
	    }
	 
	 
	 public Product getProductById(long productId) throws HandledException{
		 Product product = new Product();
		 try {
	            begin();
	            Query q = getSession().createQuery("from Product where productId = :productId");
	            
	            q.setLong("productId", productId);
	            product = (Product)q.uniqueResult();
	            commit();
	            return product;
	        } catch (HibernateException e) {
	            rollback();
	            throw new HandledException("Could not find the  product", e);
	        }
		 finally {
			close();
		}
	 }
	 
	 // Ajax search for product
	 public List<Product> getProductBySearch(String name) throws HandledException{
		 
		 try{
			 begin();
			 //'%"+name+"%'
			 System.out.println(name);
			 Query q = getSession().createQuery("from Product where productName like :productName");
			 q.setString("productName", "%"+name+"%");
			 List<Product> list = q.list();
			 if(list.isEmpty()){
				 System.out.println("List empty");
			 }
			 return list;
		 }
		 catch (HibernateException e) {
	            rollback();
	            throw new HandledException("Could not find the  product", e);
	        }
		 finally {
			close();
		}
		 
		 
	 }
	 
	 public int updateProductQuantity(long productId,int quantity) throws HandledException{
		 int rowCount=0;
		 try{
			 begin();
			 Query q = getSession().createQuery("update Product set quantity =:quantity "
			 		+ "where productId = :productId");
			 q.setInteger("quantity", quantity);
			 q.setLong("productId", productId);
			 rowCount = q.executeUpdate();
			 
		 }
		 catch (HibernateException e) {
	            rollback();
	            throw new HandledException("Could not find the  product", e);
	        }
		 finally {
			close();
		}
		return rowCount;
	 }
	 
	 //pagination  using criteria
	 public List<Product> getProductByPagination(String sellerName,int pageCount,int recordsPerPage) throws HandledException{
	 
		 try{
			 begin();
			 Criteria criteria = getSession().createCriteria(Product.class);
			 criteria.add(Restrictions.like("sellerName",sellerName));
			 criteria.setFirstResult(pageCount);
			 criteria.setMaxResults(recordsPerPage);
			 
			 List<Product> productList = (List<Product>)criteria.list();
			
			 commit();
			 return productList;
		 }
		 catch (HibernateException e) {
	            rollback();
	            throw new HandledException("Could not find the  product", e);
	        }
		 finally {
			close();
		}
		 
	 }
	 
	 public long getnumberofRecords(String sellerName){
		 
		 Query query = getSession()
				 .createQuery("select count(*) from Product where sellerName= :sellerName");
		 query.setString("sellerName", sellerName);
		 long numberOfrecords= (Long) query.uniqueResult();
		 return numberOfrecords;
	 }
		 
	 
	 public Seller getProductSeller(long sellerId){
		 
		 try{
			 begin();
			 Query q = getSession().createQuery("from Seller where sellerId= :sellerId");
			 q.setLong("sellerId", sellerId);
			 Seller seller = (Seller) q.uniqueResult();
			 return seller;
		 }
		 catch(HibernateException e){
			 
		 }
		 finally {
				close();
			}
		return null;
	 }
	 
	 // get products by seller 
	 public List<Product> getproductListbySeller(String sellerName) throws HandledException {
	    	List<Product> list = new ArrayList<Product>();
	        try {
	            begin();
	            Query q = getSession().createQuery("from Product where sellerName = :sellerName");
	            q.setString("sellerName", sellerName);
	            list = q.list();
	            commit();
	            
	            return list;
	        } catch (HibernateException e) {
	            rollback();
	            throw new HandledException("Could not list the products", e);
	        }
	        finally {
				close();
			}
	    }
	 
	 //get product by cat and seller
	 public List<Product> getproductListbySellerAndCat(String sellerName, String category_name) throws HandledException {
	    	List<Product> list = new ArrayList<Product>();
	    	
	    	Query q = getSession().createQuery("from Product where sellerName = :sellerName and category_name =:category_name");
            q.setString("sellerName", sellerName);
            q.setString("category_name", category_name);
            list = q.list();
            return list;
	 }
	 
	 
	 // get seller by username 
	 
	 public Seller getSellerbyUname(String username) throws HandledException{
			System.out.println("Inside seller login verification");
			try{
				begin();
				Query q = getSession().createQuery
						("from Seller e where e.userName = :username");
				q.setString("username", username);
				
				Seller seller = (Seller) q.uniqueResult();
				return seller;
			}
			catch(HibernateException e){
				rollback();
				throw new HandledException("Username not valid" +username,e);
			}
			finally {
				close();
			}
			
		}
	 
	 
	 // Update product by seller 
	 
	 public int updateProductBySeller(long productId,double price,int quantity) throws HandledException{
		 
		 int rowCount=0;
		 try{
			 begin();
			 Query q = getSession().createQuery("update Product set quantity =:quantity,"
			 		+ "price= :price where productId = :productId");
			 q.setInteger("quantity", quantity);
			 q.setDouble("price", price);
			 q.setLong("productId", productId);
			 rowCount = q.executeUpdate();
			 
		 }
		 catch (HibernateException e) {
	            rollback();
	            throw new HandledException("Could not find the  product", e);
	        }
		 finally {
			close();
		}
		return rowCount;
	 } 
	 
	 //delete product
	 public int deleteProduct(long productId) throws HandledException{
		 
		 int deleteCount=0;
		 try{
			 begin();
			 Query q = getSession().createQuery("delete from Product where productId= :productId ");
			 q.setLong("productId", productId);
			 deleteCount = q.executeUpdate();
			 
		 }
		 catch(HibernateException e){
			 rollback();
	         throw new HandledException("Could not find the  product", e);
		 }
		 finally {
			close();
		}
		return deleteCount;
		 
	 }
	 
	 
}
