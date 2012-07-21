package com.welcome;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.*;

import com.membership.service.LoginService;


import java.io.IOException ;
import java.util.List;
@Controller
@RequestMapping("index")
public class WelcomePageController extends AbstractController{

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private LoginService loginService;
	
	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,HttpServletResponse arg1) throws Exception {
		logger.info("Returning hello view");
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
		System.out.println("stdout - Returning hello view");
		ModelAndView mv = new ModelAndView("ana_sayfa/main");
		mv.addObject("isAuthenticated", isAuthenticated);
		mv.addObject("username", principalResult);

		return mv;
		}
}
