package com.ashish.org.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.ashish.org.pojo.Product;

public class ProductValidator implements Validator{
	
	private static final String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";

	@Override
	public boolean supports(Class clazz) {
		// TODO Auto-generated method stub
		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		// TODO Auto-generated method stub
		
		 Pattern pattern = Pattern.compile(IMAGE_PATTERN);
	        Matcher matcher;
	        MultipartFile photo;
	       
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productName","error.invalid.productName", "Product Name cannot be empty");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "company","error.invalid.company", "Company cannot be empty");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price","error.invalid.price", "Price cannot be empty");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity","error.invalid.quantity", "Quantity cannot be empty");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description","error.invalid.description", "Description cannot be empty");
	        
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "photo","error.invalid.photo", "Please upload a photo");
	        
	        
	        
	        Product product = (Product) object;
	        photo = product.getPhoto();
	        matcher = pattern.matcher(photo.getOriginalFilename());
	        
	        if(0 == photo.getSize()) {
	           errors.rejectValue("photo","Test","File is empty");
	        }
	              if(!matcher.matches()) {
	             errors.rejectValue("photo","Test","Invalid Image Format");
	        }
	        
	        if(5000000 < photo.getSize()) {
	             errors.rejectValue("photo","Test","File size is over 5mb !");
	        }
		
	}

}
