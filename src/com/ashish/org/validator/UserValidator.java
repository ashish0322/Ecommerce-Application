package com.ashish.org.validator;

import com.ashish.org.pojo.Customer;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;

public class UserValidator implements Validator {

    public boolean supports(Class aClass)
    {
        return aClass.equals(Customer.class);
    }

    public void validate(Object obj, Errors errors)
    {
        Customer customer = (Customer) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.firstName", "First Name Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.invalid.lastName", "Last Name Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId", "error.invalid.emailId", "Email Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "error.invalid.userName", "userName Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "password Required");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.street", "error.invalid.street", "Street Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.city", "error.invalid.city", "City Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.state", "error.invalid.state", "State Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.country", "error.invalid.country", "Country Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.pinCode", "error.invalid.pinCode", "Pincode Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.contactNumber", "error.invalid.contactNumber", "ContactNumber Required");
        
    }
}
