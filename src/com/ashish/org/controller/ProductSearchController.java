package com.ashish.org.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ashish.org.dao.ProductDAO;
import com.ashish.org.exception.HandledException;
import com.ashish.org.pojo.Product;

@Controller
public class ProductSearchController {

	
	@Autowired
	@Qualifier("productDao")
	private ProductDAO productDao;
	
	HttpSession session = null;
	
	@RequestMapping(value="findProductLoad.htm",method=RequestMethod.GET)
	public String searchResult(HttpServletRequest request) throws HandledException{
		
		session = request.getSession();
		System.out.println("Inside search ajax");
		String searchName = request.getParameter("searchbar");
		List<Product> productsList = productDao.getProductBySearch(searchName);
		for(Product prod:productsList){
			System.out.println(prod.getProductName());
		}
		session.setAttribute("searchResult", productsList);
		return "searchResult";
	}
	
}
