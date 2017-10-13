package com.ashish.org.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ashish.org.dao.CartDAO;
import com.ashish.org.dao.ProductDAO;
import com.ashish.org.dao.UserDAO;
import com.ashish.org.exception.HandledException;
import com.ashish.org.pojo.Customer;
import com.ashish.org.pojo.Login;
import com.ashish.org.pojo.Product;
import com.ashish.org.pojo.Order;

@Controller
public class CartController {

	@Autowired
	@Qualifier("productDao")
	 private ProductDAO productDao;
	
	@Autowired
	@Qualifier("userDao")
	private UserDAO userDao;
	
	@Autowired
	@Qualifier("cartDao")
	private CartDAO cartDao;
	
	HttpSession session;
	
	//ArrayList of cart 
	List<Product> cartProductsList;
	
	// Cart Total 
	List<Double> cartTotal;
	
	//buy product
	
			@SuppressWarnings("unchecked")
			@RequestMapping(value="/addProductToCart.htm",method=RequestMethod.GET)
			public String addProductToCart(HttpServletRequest request,@ModelAttribute("login")Login login) throws HandledException{
				
				
				
				session = request.getSession();
				if(null!= session.getAttribute("customer")){
				System.out.println("inside add to cart method");
				long id = Long.parseLong((request.getParameter("id")));
				Product product = productDao.getProductById(id);
				if(product!=null){
				String usr= request.getParameter("role");
				
				if(usr.equalsIgnoreCase("guest")){
				request.setAttribute("pleaseLogin", "Please Login to continue shopping");
				request.setAttribute("action","login");
				return "home";
				}
				else{
			
				if((product.getQuantity()!=0)){
				System.out.println("inside add to cart method"+usr);
				
				long cartValue= (Long) session.getAttribute("cartValue");
				
				System.out.println("inside add to cart method"+cartValue);
				System.out.println("Updated cart value after addition of new product");
				int prodQty = product.getQuantity();
				System.out.println("inside add to cart method"+prodQty);
				
				cartValue = cartValue+1;
				int newProdQty = prodQty-1;
				System.out.println(newProdQty);
				session.setAttribute("modifiedQty", newProdQty);
				int updateCount = productDao.updateProductQuantity(id, newProdQty);
				if(updateCount!=0){
				request.setAttribute("action", "addedtoCart");
				request.setAttribute("task", "customer");
				// updating cart value in session 
				session.setAttribute("cartValue", cartValue);
			
				cartProductsList = (List<Product>) session.getAttribute("cart");
				cartProductsList.add(product);
				System.out.println("You have added the product"+product.getProductName());
				//Updating cart total
				double price = product.getPrice();
				
				 cartTotal = (List<Double>)session.getAttribute("cartTotal");
				 cartTotal.add(price);
				 Double sum;
				
				 sum = new Double(0);
                  for(Double cartSum : cartTotal){
                      
                      sum = sum+cartSum;
                  }
                  System.out.println("total is"+sum);
				
				session.setAttribute("cartTotal",cartTotal);
				session.setAttribute("finalTotal", sum);
				// updating cart products in session 
				session.setAttribute("cart", cartProductsList);
				return"productDetails";
				}
				}else{
					request.setAttribute("action", "outOfStock");
					request.setAttribute("task", "customer");
					
					return"productDetails";
				}
				
				}
				}
				else{
					
					return"error500";
				}
				return null;
			}
				request.setAttribute("InvalidLogin", "InvalidLogin");
				return "home";
			}
			
			
			// view cart items 
			
			@RequestMapping(value="/viewCart.htm",method=RequestMethod.GET)
			public String viewItemsInsideCart(HttpServletRequest request){
				
				HttpSession session = request.getSession();
				if(null!= session.getAttribute("customer")){
				request.setAttribute("action", "viewCart");
				return"customer_home";
				}
				request.setAttribute("InvalidLogin", "InvalidLogin");
				return "home";
			}
			
			
			// remove items form cart
			
						@RequestMapping(value="/removeItem.htm",method=RequestMethod.GET)
						public String removeItemsInsideCart(HttpServletRequest request) throws HandledException{
							
							HttpSession session = request.getSession();
							if(null!= session.getAttribute("customer")){
							System.out.println("Inside removeitems");
							session = request.getSession();
							long cartValue= 0;
							if(null!= session.getAttribute("cartValue")){
							cartValue= (Long) session.getAttribute("cartValue");
							System.out.println(cartValue);
							}
							System.out.println("InsideItems currently in cart");
							
							cartProductsList = (List<Product>) session.getAttribute("cart");
							Iterator<Product> iterator = cartProductsList.iterator();
							long id = Long.parseLong(request.getParameter("id"));
							System.out.println(id);
							cartTotal = (List<Double>) session.getAttribute("cartTotal");
							
							while(iterator.hasNext()){
								Product product  = iterator.next();
								if(product.getProductId() == id){
									iterator.remove();
									System.out.println(product.getPrice());
									System.out.println("product removeed successfully");
									cartValue = cartValue-1;
									System.out.println(product.getQuantity());
									
									//modifying product qty after removing items from cart
									
									Product pro= productDao.getProductById(id);
									int updateProductCountInDb = pro.getQuantity();
									int updateQty = updateProductCountInDb+1;
									int updateCount = productDao.updateProductQuantity(id,updateQty);	
									Iterator<Double> iter1 = cartTotal.iterator();
									//System.out.println(request.getParameter("price"));
									double removedItemPrice =(product.getPrice());
				                      System.out.println(""+removedItemPrice);
				                      while(iter1.hasNext()){
				                            Double d = iter1.next();
				                            if(d == removedItemPrice ){
				                                iter1.remove();
				                              Double sum = new Double(0);
				  				              for(Double cartSum : cartTotal){
				  				            
				  				              sum = sum+cartSum;
				  				              System.out.println("sum after a product delete" +sum);
				  				                                }
				  				              System.out.println("sum after all products delete" +sum);
				  				              session.setAttribute("finalTotal",sum);
				                                break;
				                            }
				                        }break;
								}
								
							}
							
				               
				              session.setAttribute("cart",cartProductsList);
				              session.setAttribute("cartValue",cartValue);
							
							request.setAttribute("action", "viewCart");
							return"customer_home";
							}
							request.setAttribute("InvalidLogin", "InvalidLogin");
							return "home";
						}
						
			
			// Checkout cart items
				@RequestMapping(value="/checkout.htm",method= RequestMethod.GET)
				public String checkOutCart(HttpServletRequest request){
					
					session = request.getSession();
					if(null!= session.getAttribute("customer")){
					System.out.println("cart value is"+session.getAttribute("cartValue"));
					long k = (Long) session.getAttribute("cartValue");
					
					if(k!= 0){
					request.setAttribute("action", "checkOutCart");
					return"customer_home";
					}
					else{
					    request.setAttribute("nullCart", "Ooops!!! your cart is empty");
						request.setAttribute("action", "viewCart");
						return"customer_home";
					}
					}
					request.setAttribute("InvalidLogin", "InvalidLogin");
					return "home";
				}
			
				// place order
				@RequestMapping(value="/placeorder.htm",method= RequestMethod.GET)
				public String placeOrder(HttpServletRequest request){
					
					HttpSession session = request.getSession();
					if(null!= session.getAttribute("customer")){
					List<Product> finalOrderItems = (List<Product>) session.getAttribute("cart");
					
					for(Product p : finalOrderItems){
						System.out.println("Inside for loop of final order items");
						System.out.println(p.getProductName());
					}
					
					String customerName = request.getParameter("customerName");
					double orderTotal = (Double) session.getAttribute("finalTotal");
					
					System.out.println(orderTotal);
					
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					//get current date time with Date()
					   Date date = new Date();
					   System.out.println(dateFormat.format(date));
					   
					   
					String orderedDate = dateFormat.format(date);   
					String initialStatus = "pending";
					String shipperName = "ProKart Logistics";
					Customer customer =null;
					try {
						 customer = userDao.getCustomerName(customerName);
						 
						 System.out.println("Inside get cust for order");
						 System.out.println(customer.getFirstName());
					} catch (HandledException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					Long i = cartDao.placeOrder(initialStatus, shipperName, orderedDate, 
							customer,finalOrderItems,orderTotal);
					if(i != 0){
					
						// deleteing items from cart after after placed successfully
						cartProductsList = (List<Product>) session.getAttribute("cart");
						cartProductsList.clear();
						session.setAttribute("cart", cartProductsList);
						
						cartTotal = (List<Double>) session.getAttribute("cartTotal");
						cartTotal.clear();
						session.setAttribute("cartTotal", cartTotal);
						session.setAttribute("cartValue", new Long(0));
						session.setAttribute("finalTotal", new Double(0));
						cartDao.refreshCart(customer.getUserId());
						
					    request.setAttribute("action", "orderplacedSuccessfully");
					
					
					
					return"customer_home";
					}
					else{
					request.setAttribute("action", "orderFailed");
					return "customer_home";
					}
					}
					request.setAttribute("InvalidLogin", "InvalidLogin");
					return "home";
				}
}
