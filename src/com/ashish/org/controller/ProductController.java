package com.ashish.org.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.hibernate.annotations.SourceType;
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
import com.ashish.org.dao.ProductDAO;
import com.ashish.org.dao.UserDAO;
import com.ashish.org.exception.HandledException;
import com.ashish.org.pojo.Category;
import com.ashish.org.pojo.Seller;
import com.ashish.org.pojo.Login;
import com.ashish.org.pojo.Product;
import com.ashish.org.validator.ProductValidator;

@Controller

public class ProductController {

	HttpSession session;
	
	@Autowired
	@Qualifier("categoryDao")
	 private CategoryDAO categoryDao;
	
	@Autowired
	@Qualifier("userDao")
	private UserDAO userDao;
	
	
	@Autowired
	@Qualifier("productDao")
	 private ProductDAO productDao;
	
	@Autowired
	@Qualifier("productValidator")
	private ProductValidator productValidator;
	
	@InitBinder
	private void initBinder1(WebDataBinder binder){
		binder.setValidator(productValidator);
	} 
	
	
	
	List<Category> availableCategoriList = new ArrayList<Category>();
	
	// Product Mapping and methods
	
		@RequestMapping(value="/addProduct.htm",method=RequestMethod.GET)
		public String initilizeProductForm(@ModelAttribute("product1") Product product, BindingResult result1,
				HttpServletRequest request){
			
			
			try {
				availableCategoriList = categoryDao.list();
			} catch (HandledException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("categories", availableCategoriList);
			request.setAttribute("task", "addProduct");
			return"SellerHome";
		}
		
		
		@SuppressWarnings("finally")
		@RequestMapping(value="/addProduct.htm",method=RequestMethod.POST)
		public String addProductForm(@ModelAttribute("product1") Product product,
			 BindingResult result1,HttpServletRequest request) throws HandledException{
			try{
			System.out.println("inside post"+product);
			System.out.println(product.getProductName());
			productValidator.validate(product, result1);
			if(result1.hasErrors()){
				
				request.setAttribute("task", "addProduct");
				return"SellerHome";
			}
			
			
			String photoName = null;
			File file;
	        String check = File.separator; //Checking if system is linux based or windows based by checking seprator used.
	        String path = null;
	        if (check.equalsIgnoreCase("\\")) {
	            path = request.getSession().getServletContext().getRealPath("").replace("build\\", ""); //Netbeans projects gives real path as Lab6/build/web/ so we need to replace build in the path.
	            System.out.println(request.getSession().getServletContext().getRealPath(""));
	            
	            path += "\\resources\\uploads\\";
	            System.out.println(path);
	        }

	        if (check.equalsIgnoreCase("/")) {
	            path = request.getSession().getServletContext().getRealPath("").replace("build/", "");
	            path += "/"; //Adding trailing slash for Mac systems.

	        }
	        
	        if (product.getPhoto() != null) {

	            String fileNameWithExt = System.currentTimeMillis() + product.getPhoto().getOriginalFilename();
	            file = new File(path + fileNameWithExt);
	            String context = request.getSession().getServletContext().getContextPath();
	            photoName = (context + "/" +"/resources/uploads/"+ fileNameWithExt);
	            try {
					product.getPhoto().transferTo(file);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        
	        }   
	            String productName= product.getProductName();
	            System.out.println(productName);
	            String company=product.getCompany();
	            double price=product.getPrice();
	            String description = product.getDescription();
	            String category_name= product.getCategory_name();
	                     
	            int quantity= product.getQuantity();
	               	       	
	        	HttpSession session = request.getSession();
	            //String username= request.getParameter("empName");
	            String username = (String) session.getAttribute("validlogin");
	            System.out.println(username);
	            Seller seller = null;
	            Category category = null;
	            try {
					seller = userDao.getEmpName(username);
					category = categoryDao.get(category_name);
				} catch (HandledException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            System.out.println(seller.getFirstName());
	          
	            System.out.println(category_name);
	            
	            Product p = productDao.addProduct(productName, company, price, description, 
	            		quantity, photoName, seller, category.getTitle(),category,seller.getFirstName());
	            
	            category.addProduct(p);
	            categoryDao.save(category);

	            if(p!=null){
	            	request.setAttribute("pname", productName);
	                request.setAttribute("task", "productSuccess");
	            	return"SellerHome";
	            }
	            else{
	            	request.setAttribute("task", "productFail");
	            	return"SellerHome";
	            }
			}catch(Exception e){
				System.out.println(e);
			}
			return"SellerHome";
	   }
		

		
	// view products method
		List<Product> userSelectedCategoryProducts = new ArrayList<Product>();
		@RequestMapping(value="/viewProducts.htm",method=RequestMethod.GET)
		public String viewSelecedProducts(HttpServletRequest request) throws HandledException{
			
			String selectedCategory = request.getParameter("prod");
			
			userSelectedCategoryProducts = productDao.selectedProductList(selectedCategory);
			if(userSelectedCategoryProducts!=null){	
			
			request.setAttribute("selected", selectedCategory);
			HttpSession session = request.getSession();
			session.setAttribute("products",userSelectedCategoryProducts);
			
			String usr= request.getParameter("user");
			//request.setAttribute("selectedCat", userSelectedCategoryProducts);
			if(usr == null){
			return"home";
			}
			else{
			request.setAttribute("action", "selectedcat");
			return"customer_home";	
			}
			}
			else{
				
				return"error500";
			}
		}
		
		// view particular product 
		@RequestMapping(value="/productDetails.htm",method=RequestMethod.GET)
		public String productDetails(HttpServletRequest request) throws HandledException{
			long id = Long.parseLong((request.getParameter("id")));
			session = request.getSession();
			//String role=request.getParameter("role");
			Product product = productDao.getProductById(id);
			if(product!=null){
			String usr= request.getParameter("role");
			
			if(usr == null){
			session.setAttribute("selectedProd", product);
			return"productDetails";
			}
			else{
			session.setAttribute("selectedProd", product);
			request.setAttribute("task", "customer");
			return"productDetails";
			}
			}
			else{
				
				return"error500";
			}
		}		
		
		
		// manage product 
				@RequestMapping(value="/manageProduct.htm",method=RequestMethod.GET)
				public String manageProductDetails(HttpServletRequest request) throws HandledException {
					
					int page = 1;
			        int recordsPerPage = 4;
			        
			        
			        if(request.getParameter("page") != null)
			        page = Integer.parseInt(request.getParameter("page"));
			        System.out.println("Page value is "+page);
					String username = request.getParameter("sellerName");
					System.out.println(username);
					Seller seller = productDao.getSellerbyUname(username);
					List<Category> availableCategoriesList = categoryDao.list();
					String sellerName =  seller.getFirstName();
					System.out.println(sellerName);
					
					List<Product> paginatedProductList = productDao.getProductByPagination
			        		(sellerName, (page-1)*recordsPerPage, recordsPerPage);
					//List<Product> manageProductsList = productDao.getproductListbySeller(sellerName);
					long numberOfrecords = productDao.getnumberofRecords(sellerName);
					System.out.println("records per page"+numberOfrecords);
					
					if(paginatedProductList!=null){
					int noOfPages = (int) Math.ceil(numberOfrecords * 1.0 / recordsPerPage);
					request.setAttribute("task", "manageProductsList");
					request.setAttribute("catList", availableCategoriesList);
					request.setAttribute("manageProductsList", paginatedProductList);
					request.setAttribute("noOfPages", noOfPages);
					request.setAttribute("currentPage", page);
					return "SellerHome";
					}
					else{
					request.setAttribute("noProductsFound", " No Produccts have been added");	
					request.setAttribute("task", "manageProductsList");
					return "SellerHome";
					}
					
				}
				
				@RequestMapping(value="/manageProduct.htm",method=RequestMethod.POST)
				public String manageProductDetailsPost(HttpServletRequest request) throws HandledException {
					
					String catName = request.getParameter("category_name");
					
					
					Seller seller = userDao.getEmpName(request.getParameter("sellerName"));
					String sellerName = seller.getFirstName();
					System.out.println(sellerName);
					List<Category> availableCategoriesList = categoryDao.list();
					List<Product> manageProductsList= productDao.getproductListbySellerAndCat(sellerName, catName);
					if(manageProductsList != null || availableCategoriesList !=null){
						
						request.setAttribute("task", "manageProductsList");
						request.setAttribute("catList", availableCategoriesList);
						request.setAttribute("manageProductsList", manageProductsList);
						return "SellerHome";
					}
					request.setAttribute("noProductsFound", " No Produccts have been added");	
					request.setAttribute("task", "manageProductsList");
					return "SellerHome";
				}
				
			// update product 
			@RequestMapping(value="/updateProduct.htm",method=RequestMethod.POST)
			public String updateProductDetails(HttpServletRequest request) throws HandledException{
				
				long pid = Long.parseLong(request.getParameter("group"));
				double price = Double.parseDouble(request.getParameter("price"));
				int qty = Integer.parseInt(request.getParameter("quantity"));
				System.out.println(pid+"price"+price+"qty"+qty);
				int updateCount = productDao.updateProductBySeller(pid, price, qty);
				if(updateCount != 0){
					request.setAttribute("updateSuccessbySeller", "Update Success");
					request.setAttribute("task", "manageProductsList");
					
				return "SellerHome";
				}
				else{
					request.setAttribute("updateSuccessbySeller", "Update failed");
					request.setAttribute("task", "manageProductsList");
					
					return "SellerHome";
				}
				
			}
			
			// delete product 
			
			@RequestMapping(value ="/deleteProduct.htm",method=RequestMethod.GET)
			public String deleteProduct(HttpServletRequest request) throws HandledException{
				
				long productId = Long.parseLong(request.getParameter("deleteProd"));
				int deleteCount = productDao.deleteProduct(productId);
				if(deleteCount!=0){
					request.setAttribute("updateSuccessbySeller", "Delete Success");
					request.setAttribute("task", "manageProductsList");
					return "SellerHome";
				}
				
					request.setAttribute("updateSuccessbySeller", "Delete failed");
					request.setAttribute("task", "manageProductsList");
					return "SellerHome";
				
			}
}
