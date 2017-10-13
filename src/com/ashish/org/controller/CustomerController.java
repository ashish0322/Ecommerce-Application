package com.ashish.org.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ashish.org.pojo.Order;
import com.ashish.org.pojo.OrderDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ashish.org.dao.ProductDAO;
import com.ashish.org.dao.UserDAO;
import com.ashish.org.exception.HandledException;
import com.ashish.org.pojo.Customer;
import com.ashish.org.pojo.CustomerOrderDetails;
import com.ashish.org.pojo.Login;
import com.ashish.org.pojo.Product;
import com.ashish.org.validator.SellerLoginValidator;

@Controller
public class CustomerController {
	
	@Autowired
	@Qualifier("productDao")
	 private ProductDAO productDao;
	
	@Autowired
	@Qualifier("userDao")
	private UserDAO userDao;

	@Autowired
	@Qualifier("sellerLoginValidator")
	SellerLoginValidator loginValidator;
	
	@InitBinder
	private void initBinder1(WebDataBinder binder1){
		binder1.setValidator(loginValidator);
	}
	
	List<Product> availableProducts = new ArrayList<Product>();
	HttpSession products =null;
	@RequestMapping(value="/custHome.htm",method= RequestMethod.GET) 
    public String loginForm(HttpServletRequest request) throws HandledException
    		 { 
		HttpSession session = request.getSession();
		if(null!= session.getAttribute("customer")){
		products = request.getSession();
		availableProducts = productDao.productList();
		products.setAttribute("products", availableProducts);
		request.setAttribute("action", "showall");
	    return "customer_home";
	}
		
		request.setAttribute("InvalidLogin", "InvalidLogin");
		return "home";
    		 }
	
	@RequestMapping(value="/custFaq.htm",method= RequestMethod.GET)
	public String returnfaq(HttpServletRequest request){
		HttpSession session = request.getSession();
		if(null!= session.getAttribute("customer")){
	return"CustomerFaq";
		}
		request.setAttribute("InvalidLogin", "InvalidLogin");
		return "home";
	}
	
	
	@RequestMapping(value="/viewOrders.htm",method= RequestMethod.GET)
	public String returnPlacedOrders(HttpServletRequest request) throws HandledException{
	
		HttpSession session = request.getSession();
		if(null!= session.getAttribute("customer")){
		String custName = request.getParameter("name");
		Customer cust = userDao.getCustomerName(custName);
		List<Object[]> ordersList = userDao.getOrdersPlaced(cust.getUserId());
		//List<OrderDetails> orderDetailsList = userDao.getOrderDetailsPlaced(cust.getUserId());
		List<CustomerOrderDetails> listOfOrders = new ArrayList<CustomerOrderDetails>();
		System.out.println("got orders details of user from user Dao");
		
		
		if(ordersList != null){
			for(Object[] row : ordersList){
				
				CustomerOrderDetails order = new CustomerOrderDetails();
				long orderId = Long.parseLong(row[0].toString());
		    	String productName= (row[1].toString());
		    	double unitPrice =(Double.parseDouble(row[2].toString()));
		    	double orderTotal =(Double.parseDouble(row[3].toString()));
		    	String orderDate =(row[4].toString());
		    	String shipperName =(row[5].toString());
		    	String status =(row[6].toString());
		    	System.out.println(Long.parseLong(row[0].toString()));
		    	
		    	String pname = row[1].toString();
		    	System.out.println(pname);
		    	
		    	order.setOrderId(orderId);
		    	order.setProductName(productName);
		    	order.setUnitPrice(unitPrice);
		    	order.setOrderTotal(orderTotal);
		    	order.setOrderDate(orderDate);
		    	order.setShipperName(shipperName);
		    	order.setStatus(status);
		  
		    	listOfOrders.add(order);
		    }
			
			
			
			request.setAttribute("ordersList",listOfOrders);
			
			//request.setAttribute("orderDetailsList",ODetailsList);
			request.setAttribute("action1","showPlacedOrders");
			
			return"customer_home";
		}
		
		request.setAttribute("action","error500");
		return"customer_home";
	}
		
		request.setAttribute("InvalidLogin", "InvalidLogin");
		return "home";
	}
	
	
}
