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


public class CenvDegerListeValidator  implements Validator {
 
	
    @Override
    public boolean supports(Class<?> clazz) {
        return SeraCenvDegerListe.class.isAssignableFrom(clazz);
    }
    
    
    public void validate(Object target, Errors errors) {
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tip1", "tip1.hata");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tip2", "tip2.hata");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "baslik", "baslik.hata2");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birim", "birim.hata1");
        SeraCenvDegerListe seradeger=(SeraCenvDegerListe)target;
        
        if(seradeger.gettip1().equals("Yaprak")||seradeger.gettip1().equals("Dal")){
        	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "parentId", "parentId.hata1");
        }
        
        
        /*if(seradeger.getBaslik().length()<5){
        	errors.rejectValue("baslik", "baslik.hata1");
        }*/ 
        
    }
 
}