package com.ashish.org.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ashish.org.dao.CategoryDAO;
import com.ashish.org.dao.MainAdminDao;
import com.ashish.org.dao.ProductDAO;
import com.ashish.org.dao.UserDAO;
import com.ashish.org.exception.HandledException;
import com.ashish.org.pojo.Category;
import com.ashish.org.pojo.Product;
import com.ashish.org.pojo.Seller;
import com.ashish.org.validator.SellerRegistrationValidator;
import com.ashish.org.validator.PasswordEncrypt;


@Controller
public class MainAdminController {
		
	@Autowired
	@Qualifier("mainAdminDao")
	MainAdminDao mainAdminDao;
	
	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;
	
	@Autowired
	@Qualifier("productDao")
	 private ProductDAO productDao;
	
	@Autowired
	@Qualifier("categoryDao")
	private CategoryDAO categoryDao;
	
	@Autowired
	@Qualifier("sellerValidator")
	SellerRegistrationValidator sellerValidator;
	
	@InitBinder
	private void initBinder1(WebDataBinder binder1){
		binder1.setValidator(sellerValidator);
	} 
	
	
	@RequestMapping(value="/adminlogin.htm",method = RequestMethod.GET) 
    public String initializeForm() { 
   
        return "admin_login"; 
    } 
	
	@RequestMapping(value="/home.htm",method= RequestMethod.GET)
	public String returnHome(){
		
	return"admin_home";
	}
	
	
	@RequestMapping(value="/faq.htm",method= RequestMethod.GET)
	public String returnfaq(){
		
	return"FAQ";
	}
	
	@RequestMapping(value="/addSiteAdmin.htm",method= RequestMethod.GET)
	public String initilizeAdminRegForm(@ModelAttribute("employee") Seller seller,HttpServletRequest request){
		
				
		request.setAttribute("action", "addAdmin");
		return"admin_home";
	}
	
	
	@RequestMapping(value="/addHelpDesk.htm",method= RequestMethod.GET)
	public String initilizeHelpDeskRegForm(@ModelAttribute("employee") Seller seller,HttpServletRequest request){
		
		System.out.println("path is " +request.getContextPath());
		request.setAttribute("action", "addHelpDesk");
		return"admin_home";
	}
	
	@RequestMapping(value="/addEmployee.htm", method=RequestMethod.POST)
    protected String addEmployee(@ModelAttribute("employee")Seller seller, BindingResult result1,
    		HttpServletRequest request) throws Exception
    {
		String regFor= request.getParameter("regFor");
		sellerValidator.validate(seller, result1);
    	if(result1.hasErrors()){
    		if(regFor.equalsIgnoreCase("adminReg")){
    		    			
    		request.setAttribute("action", "addAdmin");
    		return "admin_home"; }
    		else{
    			request.setAttribute("action", "addHelpDesk");
        		return "admin_home"; 	
    		}
    	}
        try
        {
        	String fname = seller.getFirstName();
        	String lname = seller.getLastName();
        	String email=seller.getEmailId();
        	String uname = seller.getUserName();
        	String pass = seller.getPassword();
        	
        	// encrypting password using secure hash algorithm
        	String encryptedPass = PasswordEncrypt.getSHA1(pass);
        	
            Seller emp = userDao.registerEmployee(fname,lname,email,uname,encryptedPass);
            if(emp!=null){
            String empName=emp.getFirstName();
            request.setAttribute("empName",empName);
            request.setAttribute("action", "regSuccess");
            return "admin_home";
            }
            else{
            	request.setAttribute("action", "regFail");	
            }
            //DAO.close();
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e.getMessage());
        }
        
        return "admin_home";
    }
	
	// admin view Products 
	@RequestMapping(value="/adminViewProducts.htm",method=RequestMethod.GET)
	public String adminViewProducts(HttpServletRequest request) throws HandledException{
		
		int page = 1;
        int recordsPerPage = 5;
		
        if(request.getParameter("page") != null)
	    page = Integer.parseInt(request.getParameter("page"));
		List<Product> availableProducts = productDao.
				getProductByPaginationAdmin((page-1)*recordsPerPage, recordsPerPage);
		long numberOfrecords = productDao.getTotalNumberOfProducts();
		List<Category> availableCategoriesList = categoryDao.list();
		List<Seller> availableSellerList = userDao.getSellersList();
		if(availableProducts != null || availableCategoriesList !=null || availableSellerList !=null){
			int noOfPages = (int) Math.ceil(numberOfrecords * 1.0 / recordsPerPage);
			System.out.println("inside noy null loop");
			for(Seller s: availableSellerList){
				
				System.out.println(s.getFirstName());
			}
			request.setAttribute("catList", availableCategoriesList);
			request.setAttribute("prodList", availableProducts);
			request.setAttribute("sellList", availableSellerList);
			request.setAttribute("noOfPages", noOfPages);
			request.setAttribute("currentPage", page);
			
			request.setAttribute("action", "viewProducts");
			return"admin_home";
		}
		request.setAttribute("errorrr", "Something went wrong!!!!!!!");
		request.setAttribute("action", "viewProducts");
		return"admin_home";
	}
	
	// admin view Products 
		@RequestMapping(value="/adminViewProducts.htm",method=RequestMethod.POST)
		public String adminFilterProducts(HttpServletRequest request) throws HandledException{
		
			String catName = request.getParameter("category_name");
			String sellerName = request.getParameter("sellerName");
			
			List<Category> availableCategoriesList = categoryDao.list();
			List<Seller> availableSellerList = userDao.getSellersList();
			List<Product> productsListttt= productDao.getproductListbySellerAndCat(sellerName, catName);
			if(productsListttt != null || availableCategoriesList !=null || availableSellerList !=null){
				request.setAttribute("catList", availableCategoriesList);
				request.setAttribute("sellList", availableSellerList);
				request.setAttribute("prodList", productsListttt);
				request.setAttribute("action", "viewProducts");
				return"admin_home";
			}
			request.setAttribute("errorrr", "Something went wrong!!!!!!!");
			request.setAttribute("action", "viewProducts");
			return"admin_home";
		}
	
}
