package com.membership.controller;

import java.io.File;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sera.validator.MembershipFormValidator;
import com.util.constant.ApplicationConstants;
import com.util.login.check.LoginCheck;
import com.util.mailing.MailSender;
import com.membership.service.LoginService;
import com.membership.model.Authority;
import com.membership.model.User;


@Controller
@RequestMapping("/login")
public class LoginLogoutController{

	@Autowired
	private LoginService loginService;
	
    private static final String destinationDir = "C:/temp/";
	
	
    @Autowired
    private LoginCheck loginInfo;
    
	@RequestMapping(value = "/login.htm", method = RequestMethod.GET)
	public ModelAndView login(
			BindingResult result) {

		return new ModelAndView("login");
	}
	@RequestMapping(value = "/accessDenied.htm")
	public ModelAndView accessDenied() {

		return new ModelAndView("accessDenied");
	}	
	
	@RequestMapping(value = "/membershipForm.htm",method = RequestMethod.GET) 
	public ModelAndView getMemberForm(@ModelAttribute("user") User user,BindingResult result) {

		return new ModelAndView("/membership/membershipForm");
	}	

	@RequestMapping(value = "/membershipFormSave.htm",method = RequestMethod.POST)
	public ModelAndView saveMemberForm(HttpServletRequest req,@ModelAttribute("user")User user ,BindingResult result){
		
		MembershipFormValidator validator = new MembershipFormValidator();
//		applicationContext.getAutowireCapableBeanFactory().autowireBean(object);
		user.setMembershipStatus(ApplicationConstants.MEMBERSHIP_STATUS_CODES.PASIVE);
		validator.validate(user, result);
		//TODO user validasyonu ve sifresi gonderilecek sekilde bir  servis yazilmali.
		

		List <User> usersInDB = loginService.getByUsername(user.getUsername());
		if(usersInDB.size() > 0){
			result.rejectValue("username", "dublicateUsername");
		}	
		
		
		if(result.hasErrors()){
			ModelAndView returnView = new ModelAndView("/membership/membershipForm");
			returnView.addObject("user", user);
			return returnView;
		}
		
		
		String to = user.getEmail();	
		try {
			String activationURL = "http://localhost:8080/sera/login/activateUserAccount";
					//context.getMessage(activationURL, null, Locale.getDefault())
			String activationString = MailSender.sendActivationEmail(user,activationURL);
			user.setActivationString(activationString);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		loginService.addUser(user);
			

		ModelAndView mv = new ModelAndView("/membership/membershipSuccessPage");
		mv.addObject("email", user.getEmail());
		return mv;
	}	
	
	@RequestMapping(value = "/activateUserAccount/{username}/{activationString}.htm")
	public ModelAndView activateUserAccount(@PathVariable("username")String username,@PathVariable("activationString") String activationString,@ModelAttribute("user")User user ,BindingResult result){
//		User kaydinin membership statusu aktife cekilicek.
		
		User exmpUser = new User();
		exmpUser=loginService.loadUserObject(username);
		User waitingUser = loginService.getWaitingMember(exmpUser);
		
		if(waitingUser != null){
			ModelAndView successPage = new ModelAndView("membership/activationSuccessPage");
			loginInfo.getUserInfo(successPage);
			loginService.activateMembershipStatus(waitingUser.getUserId(),username);
			return successPage;
		}else{
			return new ModelAndView("/index.htm");
		}
	}	

	
	
	@RequestMapping(value = "/requestPassword.htm",method = RequestMethod.GET) 
	public ModelAndView getRequestPassowrdForm() {


		ModelAndView requestPasswordPage = new ModelAndView("/membership/requestPassword");
		loginInfo.getUserInfo(requestPasswordPage);
		return requestPasswordPage;
	}	
	
	
	
	@RequestMapping(value = "/sendForgottenPassword.htm")
	public ModelAndView sendForgottenPassword(@RequestParam("email") String email) throws Exception{
		
		
		User exmpUser = new User();
		exmpUser.setEmail(email);
		exmpUser.setMembershipStatus(ApplicationConstants.MEMBERSHIP_STATUS_CODES.ACTIVE);
		User existingUser = loginService.getUser(exmpUser);
		
		if(existingUser != null){
			ModelAndView mv = new ModelAndView("membership/requestPasswordSuccessPage");
			mv.addObject("email", existingUser.getEmail());
			//mail gönder
			MailSender.sendForgottenPassword(existingUser);
			return mv;
		}else{
			//TODO bu mail adresiyle kayitli kullanici bulunmamaktadir
			
			return new ModelAndView("/index.htm");
		}
	}
	
	@RequestMapping(value="/usernameUnique.htm", method = RequestMethod.GET)
	public @ResponseBody boolean usernameUniqueControl(@RequestParam(value="username", required=true) String username){	
		
				return  loginService.isUserExist(username);
				
	}
	
	@RequestMapping(value="/emailUnique.htm", method = RequestMethod.GET)
	public @ResponseBody boolean emailUniqueControl(@RequestParam(value="email", required=true) String email){	
		
				return  loginService.isEmailExist(email);
				
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/accessDenied.do")
	public void accessDenied(ModelMap model,HttpServletRequest request) {
	}
	
}