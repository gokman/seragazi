package com.sera.yetki.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.membership.model.User;
import com.membership.service.LoginService;
import com.sera.model.SeraCenvDegerListe;
import com.sera.model.SeraDegerSabitForm;
import com.sera.service.CenvDegerListeService;
import com.sera.service.CenvGirisService;
import com.sera.yetki.service.YetkiService;
import com.util.constant.ApplicationConstants;
import com.util.login.check.LoginCheck;

@Controller
@RequestMapping("/yetki")
public class YetkiController {
	
	@Autowired
	private ApplicationContext appContext;
	
	@Autowired
	private CenvGirisService cenvgirisservice;
	
	@Autowired
	private LoginService userservice;
	
	@Autowired
	private YetkiService yetkiservice;
	
	@Autowired
	private CenvDegerListeService degerlisteservice;
	
	private LoginCheck loginInfo = new LoginCheck();
	
	@RequestMapping(value = {"/yetkiver.htm"}) 
	public ModelAndView yetkivermeGiris(@ModelAttribute("user") User user) {
		ModelAndView model=new ModelAndView("yetki/yetkiver");
		SeraCenvDegerListe kok=cenvgirisservice.getKok();	
		//yetki vereceğimiz kullanıcılar aktif olmalı. aktif değil ise işlem yapmaya gerek yok
		List<User> users=userservice.listUsers(ApplicationConstants.MEMBERSHIP_STATUS_CODES.ACTIVE);
		model.addObject("users",users);
        model.addObject("kok",kok);
        
       
		loginInfo.getUserInfo(model);
		return model;
	}
	
	public User getLoggedInUser(){
		User user= (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	} 
	
	@RequestMapping(value = "/controlyetkiVarMi.htm", method = RequestMethod.POST)
	public @ResponseBody boolean controlYetkiVarMi(@RequestParam(value="id", required=true) Long elemanId,
			@RequestParam(value="userId", required=true) Long userId) {
		
		
        return yetkiservice.controlYetkiVarMi(userId, elemanId);
	}
	
	@RequestMapping(value = "/controlkokyetkiVarMi.htm", method = RequestMethod.POST)
	public @ResponseBody boolean controlKokYetkiVarMi(@RequestParam(value="userId", required=true) Long userId) {
		
		
        return yetkiservice.controlYetkiVarMi(userId, degerlisteservice.getKok().getId());
	}
	
	@RequestMapping(value = "/yetkiDegistir.htm", method = RequestMethod.POST)
	public @ResponseBody void yetkiDegistir(@RequestParam(value="id", required=true) Long elemanId,
			@RequestParam(value="userId", required=true) Long userId) {
		
           yetkiservice.degistirYetki(userId, elemanId);
	}

}
