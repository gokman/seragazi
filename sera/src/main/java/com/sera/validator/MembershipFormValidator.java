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

import com.membership.service.LoginService;
import com.membership.model.User;

public class MembershipFormValidator  implements Validator {
 
	
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "surname");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name");
        ValidationUtils.rejectIfEmpty(errors, "username", "membership","isim alani bos birakilamaz");
        
        User user = (User) target;
    }
 
}