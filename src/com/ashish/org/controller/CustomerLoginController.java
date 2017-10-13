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
import org.springframework.web.servlet.ModelAndView;

import com.ashish.org.dao.CartDAO;
import com.ashish.org.dao.CategoryDAO;
import com.ashish.org.dao.LoginDAO;
import com.ashish.org.dao.ProductDAO;
import com.ashish.org.exception.HandledException;
import com.ashish.org.pojo.Category;
import com.ashish.org.pojo.Login;
import com.ashish.org.pojo.Product;
import com.ashish.org.pojo.Customer;
import com.ashish.org.pojo.CustomerCart;
import com.ashish.org.validator.SellerLoginValidator;
import com.ashish.org.validator.PasswordEncrypt;


@Controller
public class CustomerLoginController {

	HttpSession session = null;
	long cartValue=0;
	
	//ArrayList of cart 
	List<Product> cartProductsList;
	
	// Cart Total 
	List<Double> cartTotal;
	
	@Autowired
	@Qualifier("loginDao")
	private LoginDAO loginDao;
	
	@Autowired
	@Qualifier("cartDao")
	private CartDAO cartDao;

    @Autowired
	@Qualifier("sellerLoginValidator")
	SellerLoginValidator loginValidator;
    
    @Autowired
	@Qualifier("productDao")
	 private ProductDAO productDao;
    
    @Autowired
	@Qualifier("categoryDao")
	private CategoryDAO categoryDao;
	
	@InitBinder
	private void initBinder1(WebDataBinder binder1){
		binder1.setValidator(loginValidator);
	} 
	
	
	List<Category> availableCategoriesList = new ArrayList<Category>();
	List<Product> availableProducts = new ArrayList<Product>();

	@RequestMapping(value="/login.htm",method= RequestMethod.GET) 
    public String loginForm(@ModelAttribute("login")Login login,
    		HttpServletRequest request) { 
		
		System.out.println("Inside cust login get");
		request.setAttribute("action","login");
		return "home";
	}
	
	@RequestMapping(value="/customerlogin.htm",method= RequestMethod.POST) 
    public String custLogin(@ModelAttribute("login")Login login,BindingResult result,
    	HttpServletRequest request) throws HandledException, NoSuchAlgorithmException, UnsupportedEncodingException { 
		session = request.getSession();
		
		String role = request.getParameter("role");
		session.setAttribute("role", role);
		//if(role.equalsIgnoreCase("customer")){
		System.out.println("Inside cust login post");
		loginValidator.validate(login, result);
		if(result.hasErrors()){
			
			request.setAttribute("action","login");
			return "home";
		}
		String username = login.getUserName();
		String pass = login.getPassword();
		
		String password = PasswordEncrypt.getSHA1(pass);
		Customer customer = loginDao.validateCustLogin(username, password);
		
		
		
		if(customer!=null){
			session.setAttribute("customer", customer);
			System.out.println("customer  placed inside sessoin scope");
			cartValue = cartDao.getSavedItemsInCart(customer.getUserId());
			
			System.out.println(cartValue);
			List<CustomerCart> listCart = cartDao.getSavedListOfProductsInCart(customer.getUserId());
			
			/*if(null != session.getAttribute("cart")){
				System.out.println("Insisde not null cart");
				cartProductsList = (List<Product>) session.getAttribute("cart");
			}
			else{*/
				// Creating new cart list
				
				cartProductsList = new ArrayList<Product>();
				System.out.println("cart created successfully");
			

			double priceT = 0;
			
			for(CustomerCart cCart: listCart){
				long pId = cCart.getProductId();
				Product product = productDao.getProductById(pId);
				cartProductsList.add(product);
				double price = product.getPrice();
				priceT = priceT+price;
			}
			
			if(null != session.getAttribute("cartTotal")){
				 cartTotal = (List<Double>)session.getAttribute("cartTotal");
				 System.out.println(cartTotal);
                }
                else{
               	 cartTotal = new ArrayList<Double>();
                }
			cartTotal.add(priceT);
			
			Double sum = new Double(0);
            for(Double cartSum : cartTotal){
                
                sum = sum+cartSum;
            }
            System.out.println("total is"+sum);
			
			session.setAttribute("cartTotal",cartTotal);
			session.setAttribute("finalTotal", sum);
			session.setAttribute("cart", cartProductsList);			
			
			session = request.getSession();
			session.setAttribute("loggedInUser", customer);
			try {
				availableCategoriesList = categoryDao.list();
				availableProducts = productDao.productList();
			} catch (HandledException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		session.setAttribute("categories", availableCategoriesList);
		session.setAttribute("products", availableProducts);
		request.setAttribute("action", "showall");
		request.setAttribute("result", "successLogin");
		session.setAttribute("customerName", username);
		session.setAttribute("cartValue",cartValue);
		session.setAttribute("isCusIn", "custIn");
		System.out.println("created isCusin session attribute");
		return "customer_home";
		}
		else{
			request.setAttribute("action","login");
			System.out.println("inside invalid cust login");
			request.setAttribute("result", "Invalid Credentials");
			return "home";
		}
		
		
	}
}
