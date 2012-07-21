package com.sera.validator;


import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.bean.ViewScoped;
import javax.servlet.jsp.tagext.ValidationMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sera.model.SeraCenvDegerListe;
import com.sera.util.object.Donem;


public class DonemValidator  implements Validator {
 
	
    @Override
    public boolean supports(Class<?> clazz) {
        return Donem.class.isAssignableFrom(clazz);
    }
    
    
    public void validate(Object target, Errors errors) {
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "donem", "required");
  
        
       
        
    }
 
}