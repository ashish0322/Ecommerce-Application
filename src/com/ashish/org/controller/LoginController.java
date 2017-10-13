package com.ashish.org.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ashish.org.dao.LoginDAO;
import com.ashish.org.exception.HandledException;
import com.ashish.org.pojo.AdministratorLogin;



import com.ashish.org.validator.PasswordEncrypt;





@Controller
public class LoginController {

		
	@Autowired
	@Qualifier("loginDao")
	private LoginDAO loginDao;
	
	
	 @RequestMapping(value="/adminlogin.htm",method = RequestMethod.POST) 
	 public String Login(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException, UnsupportedEncodingException, HandledException{
		 
		 HttpSession session = request.getSession();
		 
		 String auth = request.getHeader("Authorization");
		 System.out.println("authorization is " +auth);
		 
		 String userRole = request.getRemoteUser();
		 System.out.println("user role is"+userRole);
		 
		 	 
		 if(auth.equals(null)){
				System.out.println("Inside auth null");
				askForPassword(response);
			}
			else {
				// checking if the user role exists in the web.xml 
				if(request.isUserInRole(userRole)){
				
				System.out.println("Inside auth not null");
				String userInfo =
				auth.substring(6).trim();
				System.out.println("user info is "+userInfo);
				
				// decodes user info to byte array i.e byte code
				byte[] bytearray = Base64.decodeBase64(userInfo);
				
				//string(byte array) will convert byte code to string(human readable output)
				String nameAndPassword = new String(bytearray);
				
				System.out.println("output is "+nameAndPassword);
				
				int index = nameAndPassword.indexOf(":");
				String user =nameAndPassword.substring(0, index);
				System.out.println("username is" +user);
				
				String passwordd =nameAndPassword.substring(index+1);
				System.out.println("password is" +passwordd);
				
				// passing the user name and password to user defined method 
				if (areEqualReversed(user, passwordd)) {
					
				if(userRole.equalsIgnoreCase("admin")){	
				String username= request.getParameter("email");
				String password = request.getParameter("pass");
				String encryptedPass = PasswordEncrypt.getSHA1(password);
				
				//username = username.replaceAll("[^\\dA-Za-z ]", "").replaceAll("\\s+", "+").trim();
				System.out.println("santized uname"+username);
				System.out.println(encryptedPass);
				AdministratorLogin result= loginDao.validateLogin(username, encryptedPass);
				if(result!=null){
				session.setAttribute("validlogin", username);
				return "admin_home";}
				else{
					request.setAttribute("error", "Invalid login");
					System.out.println("inside invalid login");
					return"admin_login";
				    }
				  }
				}
			}
		}
		 return "admin_home";
	 }
	 
	 		private boolean areEqualReversed(String s1,String s2) {
				s2 = (new StringBuffer(s2)).reverse().toString();
				return((s1.length() > 0) && s1.equals(s2));
				}

			
			private void askForPassword(HttpServletResponse response){
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}
	 
}
