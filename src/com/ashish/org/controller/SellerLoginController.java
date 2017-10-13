package com.ashish.org.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ashish.org.dao.CartDAO;
import com.ashish.org.dao.LoginDAO;
import com.ashish.org.dao.OrdersDAO;
import com.ashish.org.dao.ProductDAO;
import com.ashish.org.dao.UserDAO;
import com.ashish.org.exception.HandledException;
import com.ashish.org.pojo.Seller;
import com.ashish.org.pojo.CustomerOrderDetails;
import com.ashish.org.pojo.Login;
import com.ashish.org.pojo.Order;
import com.ashish.org.pojo.OrderDetails;
import com.ashish.org.pojo.Product;
import com.ashish.org.validator.SellerLoginValidator;
import com.ashish.org.validator.SellerRegistrationValidator;
import com.ashish.org.validator.PasswordEncrypt;

@Controller
public class SellerLoginController {

	
		@Autowired
		@Qualifier("loginDao")
		private LoginDAO loginDao;
	
	    @Autowired
		@Qualifier("sellerLoginValidator")
		SellerLoginValidator sellerLoginValidator;
	    
	    @Autowired
		@Qualifier("cartDao")
		private CartDAO cartDao;
	    
	    @Autowired
		@Qualifier("ordersDao")
		private OrdersDAO ordersDao;
	    
	    @Autowired
		@Qualifier("productDao")
		 private ProductDAO productDao;
		
		@InitBinder
		private void initBinder1(WebDataBinder binder1){
			binder1.setValidator(sellerLoginValidator);
		} 
	 
		@RequestMapping(value="/empLoginHome.htm",method = RequestMethod.GET) 
	    public String empHome(@ModelAttribute("login") Login login,BindingResult result) { 
		 System.out.println("inside seller login get");
	        return "SellerLogin"; 
	    } 
	 
	 @RequestMapping(value="/empHome.htm",method = RequestMethod.GET) 
	    public String empHome1(HttpServletRequest request) { 
		 	
		 String id = request.getParameter("id");
		 System.out.println(id);
		 	if(null == id){
	        return "index"; 
		 	}
		 	else{
		 		return "SellerHome";
		 	}
	    } 
	 
	 	@RequestMapping(value="/employee_login.htm",method = RequestMethod.POST) 
	    public String empLogin(@ModelAttribute("login") Login login,BindingResult result,
	    		HttpServletRequest request) throws NoSuchAlgorithmException, UnsupportedEncodingException { 
		 
		 System.out.println("inside seller login post");
		 System.out.println("Seller name"+login.getUserName());
		 
		 String role = request.getParameter("role");
		 HttpSession session = request.getSession();
		 sellerLoginValidator.validate(login, result);
		 if(result.hasErrors()){
			return "SellerLogin";
		 }
		 System.out.println("Seller name"+login.getPassword());
		 try{
		 String username = login.getUserName();
		 String pass = login.getPassword();
		
		 String password = PasswordEncrypt.getSHA1(pass);
		 System.out.println("inside seller login try");
		 Seller seller = loginDao.validateSellerLogin(username, password);
		 if(seller!=null){
		 if(role.equalsIgnoreCase("Seller")){
		 session.setAttribute("validlogin", username);
		 return "SellerHome";
		 }
		 else{
			 
		 }
		
		 }
		 else{
		  request.setAttribute("error", "Invalid login");
		  System.out.println("inside invalid login");
		  return "SellerLogin";
		 }
	}
		 catch(Exception e){
			 System.err.println(e);
		 }
	 
		 return "";
	    } 

	 	// Seller view pending & approved orders 
	 	@RequestMapping(value="/sellerViewOrders.htm",method=RequestMethod.GET)
	 	public String viewPendingOrders(HttpServletRequest request) throws HandledException{
	 		HttpSession session = request.getSession();
	 		String username = request.getParameter("sellerName");
			System.out.println(username);
			Seller seller = productDao.getSellerbyUname(username);
			
			String status1 = request.getParameter("status");
			
			String sellerName =  seller.getFirstName();
			System.out.println(sellerName);
			System.out.println("Seller id is"+seller.getSellerId());
			List<Object[]> pendingOrdersList = ordersDao.getPrndingOrders(seller.getSellerId(),status1);
			List<CustomerOrderDetails> listOfOrders = new ArrayList<CustomerOrderDetails>();
			System.out.println("got orders details of user from order Dao");
			if(pendingOrdersList!=null){
				
						
				for(Object[] row : pendingOrdersList){
					CustomerOrderDetails order = new CustomerOrderDetails();
					long orderId = Long.parseLong(row[0].toString());
					long orderDetailsId = Long.parseLong(row[1].toString());
			    	String productName= (row[2].toString());
			    	double unitPrice =(Double.parseDouble(row[3].toString()));
			    	int userId = (Integer.parseInt(row[4].toString()));
			    	String orderDate =(row[5].toString());
			    	
			    	String status =(row[6].toString());
			    	System.out.println(Long.parseLong(row[0].toString()));
			    	
			    	String pname = row[1].toString();
			    	System.out.println(pname);
			    	
			    	order.setOrderId(orderId);
			    	order.setOrderDetailId(orderDetailsId);
			    	order.setProductName(productName);
			    	order.setUnitPrice(unitPrice);
			    	order.setUserId(userId);
			    	order.setOrderDate(orderDate);
			    	
			    	order.setStatus(status);
			  
			    	listOfOrders.add(order);
					
				}
				if(status1.equalsIgnoreCase("pending")){
				
				
				request.setAttribute("sellerOrdersList",listOfOrders);
				request.setAttribute("task", "pendingOrders");
				return"SellerHome";
			}
				System.out.println("Inside approved orders view controller");
				request.setAttribute("sellerOrdersList",listOfOrders);
				request.setAttribute("task", "approvedOrders");
				return"SellerHome";
			}
			return "SellerHome";
	
			}
		
	 	
	 	
	 	// product approval by seller
	 	
	 	@RequestMapping(value="/approveProduct.htm",method = RequestMethod.GET) 
	    public String approveOrderItem(HttpServletRequest request) throws HandledException { 
		 	
		long id = Long.parseLong(request.getParameter("approveProd"));
		int i = ordersDao.appproveOrderBySeller(id);
		 if(i != 0){
		request.setAttribute("approveSuccess", "Processed Successfully");
		request.setAttribute("task", "pendingOrders");
		return"SellerHome";
		
	    } 
		request.setAttribute("approveFailed", "Processing Failed");
		request.setAttribute("task", "pendingOrders");
		return"SellerHome";
	 	}
}
