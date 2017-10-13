package com.ashish.org.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.junit.runner.Request;
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
import com.ashish.org.dao.ProductDAO;
import com.ashish.org.dao.UserDAO;
import com.ashish.org.exception.HandledException;
import com.ashish.org.pojo.Seller;
import com.ashish.org.pojo.Login;
import com.ashish.org.pojo.Product;
import com.ashish.org.pojo.Customer;
import com.ashish.org.validator.SellerLoginValidator;
import com.ashish.org.validator.SellerRegistrationValidator;
import com.ashish.org.validator.PasswordEncrypt;
import com.ashish.org.validator.UserValidator;
import com.ashish.org.pojo.Category;




@Controller
public class RegisterUserController {

	@Autowired
	@Qualifier("userDao")
	private UserDAO userDao;
	
	
	@Autowired
	@Qualifier("userValidator")
	private UserValidator validator;
	
	@Autowired
	@Qualifier("productDao")
	 private ProductDAO productDao;
	
	
	@Autowired
	@Qualifier("categoryDao")
	private CategoryDAO categoryDao;
	
	@InitBinder
	private void initBinder(WebDataBinder binder){
		binder.setValidator(validator);
	} 
	
	
	// to display available categories on home page	
	HttpSession categories = null; 
	List<Category> availableCategoriesList = new ArrayList<Category>();
	
	// to display available products on home page
	List<Product> availableProducts = new ArrayList<Product>();
	
	@RequestMapping(value="/prokarthome.htm",method = RequestMethod.GET) 
    public String proKartHome(HttpServletRequest request) { 
   
		categories = request.getSession();
		
		try {
			availableCategoriesList = categoryDao.list();
			availableProducts = productDao.productList();
			
		
			
		} catch (HandledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		categories.setAttribute("categories", availableCategoriesList);
		categories.setAttribute("products", availableProducts);
		
        return "home"; 
    } 
	
	
	@RequestMapping(value="/signUp.htm",method = RequestMethod.GET) 
    public String signUpForm(@ModelAttribute("user")Customer customer,
    		
    		HttpServletRequest request) { 
		
		request.setAttribute("action", "signUp");
		return "home";
	}
	
	@RequestMapping(value="/adduser.htm", method=RequestMethod.POST)
    protected String doSubmitAction(@ModelAttribute("user")Customer customer, BindingResult result,
    		HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		String action = request.getParameter("action");
    	validator.validate(customer, result);
    	if(result.hasErrors()){
    		
    		request.setAttribute("action", "signUp");
    		return "home";
    	}
    	
        try
        {
        	
        	String fname = customer.getFirstName();
        	String lname = customer.getLastName();
        	String email=customer.getEmailId();
        	String uname = customer.getUserName();
        	String pass = customer.getPassword();
        	
        	Customer customerUsernameCheck =userDao.userExists(uname);
        	Customer customerEmailCheck=(Customer) userDao.emailExists(email);
        	if(null !=customerUsernameCheck || null!= customerEmailCheck){
        		
        	if(email.equalsIgnoreCase(customerEmailCheck.getEmailId())){
        		
        		request.setAttribute("emailId_UsernameExists", "Email  already registered");
        		request.setAttribute("action", "signUp");
        		return "home";
        	}else if(uname.equalsIgnoreCase(customerUsernameCheck.getUserName())){
        		
        		request.setAttribute("emailId_UsernameExists", "Username already registered");
        		request.setAttribute("action", "signUp");
        		return "home";
        	}else if(email.equalsIgnoreCase(customerEmailCheck.getEmailId()) &&
        			uname.equalsIgnoreCase(customerUsernameCheck.getUserName())){
        		
        		request.setAttribute("emailId_UsernameExists", "Email and Usename already registered");
        		request.setAttribute("action", "signUp");
        		return "home";
        	}
        	}
        	// encrypting password using secure hash algorithm
        	String encryptedPass = PasswordEncrypt.getSHA1(pass);
        	
        	int i= userDao.registerUser(fname,lname,email,uname,encryptedPass,
        			customer.getAddress().getStreet(),
        			customer.getAddress().getCity(),
        			customer.getAddress().getState(),
        			customer.getAddress().getCountry(),
        			customer.getAddress().getContactNumber(),
        			customer.getAddress().getPinCode());
        	if(i!= 0){
        		 request.setAttribute("action", "regSuccess");
        	     return "home";
        	}
            //DAO.close();
        	request.setAttribute("action", "regFail");
            return "home";
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e.getMessage());
        }
        request.setAttribute("action", "regFail");
        return "home";
    }
	
	// Ajax check for username	
	@RequestMapping(value="/checkuser.htm",method = RequestMethod.POST)
	 public @ResponseBody
	 String ajaxResponse(HttpServletRequest request, HttpServletResponse response)
	   throws Exception {
		
	
		String username = request.getParameter("userName");
		System.out.println("Inside ajax call	"+username );
		Customer customer =userDao.userExists(username);
		
		if(customer!= null){
	        
	           return JSONObject.quote("Username already exists") ;
	         
	       }
	      else{
	    	  System.out.println("inside user not exists");
	    	  return JSONObject.quote("") ;
	      }
		
	}
	
// Ajax check for email 
	@RequestMapping(value="/checkemail.htm",method = RequestMethod.POST)
	 public @ResponseBody
	 String ajaxResponseEmail(HttpServletRequest request, HttpServletResponse response)
	   throws Exception {
		
	
		String email = request.getParameter("emailId");
		System.out.println("Inside ajax call	"+email );
		Customer customer=(Customer) userDao.emailExists(email);
		
		
		if(customer!=null){
	          //System.out.println(user.getUserName());
	         
	          
	           return JSONObject.quote("User with this email already exists") ;
	          
	           
	       }
	      else{
	    	  System.out.println("inside user not exists");
	    	  return JSONObject.quote("") ;
	      }
		
	}
	
	
	}



