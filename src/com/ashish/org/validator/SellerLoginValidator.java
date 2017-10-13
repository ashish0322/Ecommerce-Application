package com.ashish.org.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


import com.ashish.org.pojo.Login;

public class SellerLoginValidator implements Validator {

	
	public boolean supports(Class clazz)
    {
        return Login.class.equals(clazz);
    }

	
	public void validate(Object obj, Errors errors)
    {
		Login login = (Login) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "error.invalid.userName", "userName Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "password Required");
        
        
    }

}
