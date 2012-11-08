package com.sera.birim.controller;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.membership.service.LoginService;
import com.sera.birim.model.Birim;
import com.sera.birim.service.BirimService;
import com.util.login.check.LoginCheck;

@Controller
@RequestMapping("/birim")
public class BirimController {
	
	@Autowired
	private ApplicationContext appContext;
	
	@Autowired
	private BirimService birimservice;
	
	@Autowired
	private LoginService loginservice;
	
	private LoginCheck loginInfo = new LoginCheck();
	
	@RequestMapping(value = "/birimgiris.htm")
	public ModelAndView girisbirim(@ModelAttribute("birim") Birim birim){
		  
		ModelAndView model=new ModelAndView("/birim/birimgiris");
		birim.setId((long)0);
		model.addObject("birimim",birim);
		if(birimservice.listBirim().size()>0){
		  model.addObject("birimler",birimservice.listBirim());
		}
        loginInfo.getUserInfo(model);
		return model; 
	}  
	
	@RequestMapping(value = "/birimkaydet.htm")
	public ModelAndView kaydetFaktor(@ModelAttribute("birim") Birim birim){
		  
		ModelAndView model=new ModelAndView("redirect:/birim/birimgiris.htm");
		User user= getLoggedInUser();
		birim.setCreatedBy(loginservice.getLoggedInUser().getUserId());
		birim.setLastUpdatedBy(loginservice.getLoggedInUser().getUserId());
		SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy hh:MM");
		birim.setCreateDate(format.getCalendar().getInstance().getTime());
		birim.setLastUpdateDate(format.getCalendar().getInstance().getTime());
		birimservice.saveBirim(birim);
		if(birimservice.listBirim().size()>0){
			  model.addObject("birimler",birimservice.listBirim());
			}
        loginInfo.getUserInfo(model);
		return model; 
	} 
	
	public User getLoggedInUser(){
		User user= (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}
	
	@RequestMapping(value = "/birimSil.htm", method = RequestMethod.POST)
	public @ResponseBody void silBirim(@RequestParam(value="id", required=true) Long id) {	
		Birim birim = new Birim();
		birim=birimservice.getBirim(id);
		 birimservice.deleteBirim(birim);
        
	}
	
	@RequestMapping(value = "/birimguncelle/{id}.htm")
	public ModelAndView guncellegirisfaktor(@PathVariable("id") long id,@ModelAttribute("birim") Birim birim){
		  
		ModelAndView model=new ModelAndView("/birim/birimgiris");
		Birim birim2=birimservice.getBirim(id);
		model.addObject("birimim",birim2);
		if(birimservice.listBirim().size()>0){
			  model.addObject("birimler",birimservice.listBirim());
		}
        loginInfo.getUserInfo(model);
		return model; 
	} 
	
	@RequestMapping(value = "/birimguncelle.htm")
	public ModelAndView guncelleFaktor(@ModelAttribute("birim") Birim birim){
		User user= getLoggedInUser();
		ModelAndView model=new ModelAndView("/birim/birimgiris");
		SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy hh:MM");
		birim.setLastUpdateDate(format.getCalendar().getInstance().getTime());
		birim.setLastUpdatedBy(loginservice.getLoggedInUser().getUserId());
		birimservice.updateBirim(birim);
		if(birimservice.listBirim().size()>0){
			  model.addObject("birimler",birimservice.listBirim());
		}
        loginInfo.getUserInfo(model);
		return model; 
	} 
	

}
