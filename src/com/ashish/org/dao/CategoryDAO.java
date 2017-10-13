package com.ashish.org.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.ashish.org.exception.HandledException;
import com.ashish.org.pojo.Category;
import com.ashish.org.pojo.Customer;



public class CategoryDAO extends DAO {

    public Category get(String title) throws HandledException {
        try {
            begin();
            Query q=getSession().createQuery("from Category where title= :title");
            q.setString("title",title);
            Category category=(Category)q.uniqueResult();
            commit();
            return category;
        } catch (HibernateException e) {
            rollback();
            throw new HandledException("Could not obtain the named category " + title + " " + e.getMessage());
        }
        finally {
			close();
		}
    }

    public List<Category> list() throws HandledException {
    	List<Category> list = new ArrayList<Category>();
        try {
            begin();
            Query q = getSession().createQuery("from Category");
            list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new HandledException("Could not list the categories", e);
        }
        finally {
			close();
		}
    }

    public Category create(String title) throws HandledException {
        try {
            begin();
            Category cat = new Category(title);
            getSession().save(cat);
            commit();
            return cat;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create the category", e);
            throw new HandledException("Exception while creating category: " + e.getMessage());
        }
        finally {
			close();
		}
    }

    public void save(Category category) throws HandledException {
        try {
            begin();
            getSession().update(category);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new HandledException("Could not save the category", e);
        }
        finally {
			close();
		}
    }

    public void delete(Category category) throws HandledException {
        try {
            begin();
            getSession().delete(category);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new HandledException("Could not delete the category", e);
        }
        finally {
			close();
		}
    }
    
    // Ajax check for category
	 public Category categoryExists(String title){
	  
		Query q = getSession().createQuery("from Category where title= :title");
        q.setString("title",title);
        Category c = (Category) q.uniqueResult();
    	        
        return c;
		 
	 }
}