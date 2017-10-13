package com.ashish.org.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ashish.org.dao.CartDAO;
import com.ashish.org.pojo.Login;
import com.ashish.org.pojo.Product;
import com.ashish.org.pojo.Customer;

@Controller
public class LogoutController {
	
	
	@Autowired
	@Qualifier("cartDao")
	private CartDAO cartDao;
	
	@RequestMapping(value="/adminlogout.htm",method = RequestMethod.GET)
	public String adminLogout(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		return"admin_login";
	}
	
	@RequestMapping(value="/siteAdminlogout.htm",method = RequestMethod.GET)
	public String siteAdminLogout(HttpServletRequest request,@ModelAttribute("login") Login login){
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		return"SellerLogin";
	}
	
	@RequestMapping(value="/customerLogout.htm",method = RequestMethod.GET)
	public  String customerLogout(HttpServletRequest request,@ModelAttribute("login") Login login,
			HttpServletResponse response) throws IOException{
		
		HttpSession session = request.getSession();
		if(null!= session.getAttribute("customer")){
		Customer customer = (Customer) session.getAttribute("loggedInUser");
		System.out.println("Inside customer logout");
		System.out.println("Inside customer logout" +customer.getUserId());
		cartDao.refreshCart(customer.getUserId());
		@SuppressWarnings("unchecked")
		
		List<Product> cartProductsList  = (List<Product>) session.getAttribute("cart");
		if(cartProductsList!=null){
		cartDao.saveCartItems(cartProductsList,customer);
		}
		
		session.invalidate();
		
		request.setAttribute("action","login");
		return"home";
	}
		request.setAttribute("InvalidLogin", "InvalidLogin");
		return "home";
	}
}
