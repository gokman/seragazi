package com.util.login.check;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;

@Repository("loginInfo")
public class LoginCheck {

	public void getUserInfo (ModelAndView currentPage){

		boolean isAuthenticated = false;
		String principalResult ="";
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    if (principal == null)
	    	principalResult = null;
	    if (principal instanceof String)
	    	principalResult = (String) principal;
	    if (principal instanceof User)
	    	principalResult = ((User) principal).getUsername();
		
	    if(!principalResult.equals("anonymousUser")){
	    	isAuthenticated = true ;
	    }	    
	    
		currentPage.addObject("isAuthenticated", isAuthenticated);
		currentPage.addObject("username", principalResult);

		
	}
	
}
