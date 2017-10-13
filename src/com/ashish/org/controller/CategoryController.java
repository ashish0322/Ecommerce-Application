package com.ashish.org.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ashish.org.dao.CategoryDAO;
import com.ashish.org.exception.HandledException;
import com.ashish.org.pojo.Category;
import com.ashish.org.pojo.Customer;
import com.ashish.org.pojo.Product;
import com.ashish.org.validator.CategoryValidator;

@Controller
public class CategoryController {

	
	// Category Mapping and methods
	
	@Autowired
	@Qualifier("categoryValidator")
	private CategoryValidator categoryValidator;
	
	@Autowired
	@Qualifier("categoryDao")
	private CategoryDAO categoryDao;
			
	@InitBinder
	private void initBinder(WebDataBinder binder){
		binder.setValidator(categoryValidator);
	}
	
	@RequestMapping(value="/addCategory.htm",method=RequestMethod.GET)
	public String initilizeCategoryForm(@ModelAttribute("category")Category category,
			HttpServletRequest request){
		
		request.setAttribute("task", "addCategory");
		return"SellerHome";
	}
	
	@RequestMapping(value="/addCategory.htm",method=RequestMethod.POST)
	public String addCategoryForm(@ModelAttribute("category") Category category,BindingResult result,
			HttpServletRequest request) throws HandledException{
		
		categoryValidator.validate(category, result);
		if(result.hasErrors()){
			request.setAttribute("task", "addCategory");
			return"SellerHome";
		}
		String name = category.getTitle();
		
		Category checkIfCategoryExists = categoryDao.get(name);
		if(checkIfCategoryExists!=null){
		if(name.equalsIgnoreCase(checkIfCategoryExists.getTitle())){
			
			request.setAttribute("categoryExists", "Category already present");
			request.setAttribute("task", "addCategory");
			return"SellerHome";
		}
		}
		System.out.println("Creating category");
		Category cat=null;
		try {
			cat= categoryDao.create(name);
		} catch (HandledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(cat!=null){
		request.setAttribute("categoryName",category.getTitle());
		request.setAttribute("status", "categoryAdded");
		return"SellerHome";
		}
		request.setAttribute("status", "categoryNotAdded");
		return"SellerHome";
	}
	
	

	// Ajax check for category	
	@RequestMapping(value="/checkCategory.htm",method = RequestMethod.POST)
	 public @ResponseBody
	 String ajaxResponse(HttpServletRequest request, HttpServletResponse response)
	   throws Exception {
		
	
		String category = request.getParameter("categoryId");
		System.out.println("Inside ajax call	"+category );
		Category Category = categoryDao.categoryExists(category);
		
		if(Category!= null){
	        
	           return JSONObject.quote("Category already exists") ;
	         
	       }
	      else{
	    	  System.out.println("inside category not exists");
	    	  return JSONObject.quote("") ;
	      }
		
	}
	
}
