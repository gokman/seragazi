package com.sera.gaz.controller;

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
import com.sera.gaz.model.Gaz;
import com.sera.gaz.service.GazService;
import com.util.login.check.LoginCheck;

@Controller
@RequestMapping("/gaz")
public class GazController {
	
	@Autowired
	private ApplicationContext appContext;
	
	@Autowired
	private GazService gazservice;
	
	@Autowired
	private LoginService loginservice;
	
	private LoginCheck loginInfo = new LoginCheck();
	
	@RequestMapping(value = "/gazgiris.htm")
	public ModelAndView girisbirim(@ModelAttribute("gaz") Gaz gaz){
		  
		ModelAndView model=new ModelAndView("/gaz/gazgiris");
		gaz.setId((long)0);
		model.addObject("gazim",gaz);
		if(gazservice.listGaz().size()>0){
		  model.addObject("gazlar",gazservice.listGaz());
		}
        loginInfo.getUserInfo(model);
		return model; 
	}  
	
	@RequestMapping(value = "/gazkaydet.htm")
	public ModelAndView kaydetFaktor(@ModelAttribute("gaz") Gaz gaz){
		  
		ModelAndView model=new ModelAndView("redirect:/gaz/gazgiris.htm");
		User user= getLoggedInUser();
		gaz.setCreatedBy(loginservice.getLoggedInUser().getUserId());
		gaz.setLastUpdatedBy(loginservice.getLoggedInUser().getUserId());
		SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy hh:MM");
		gaz.setCreateDate(format.getCalendar().getInstance().getTime());
		gaz.setLastUpdateDate(format.getCalendar().getInstance().getTime());
		gazservice.saveGaz(gaz);
		if(gazservice.listGaz().size()>0){
			  model.addObject("gazlar",gazservice.listGaz());
			}
        loginInfo.getUserInfo(model);
		return model; 
	} 
	
	public User getLoggedInUser(){
		User user= (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}
	
	@RequestMapping(value = "/gazSil.htm", method = RequestMethod.POST)
	public @ResponseBody void silGaz(@RequestParam(value="id", required=true) Long id) {	
		Gaz gaz = new Gaz();
		gaz=gazservice.getGaz(id);
		gazservice.deleteGaz(gaz);
        
	}
	
	@RequestMapping(value = "/gazguncelle/{id}.htm")
	public ModelAndView guncellegirisfaktor(@PathVariable("id") long id,@ModelAttribute("gaz") Gaz gaz){
		  
		ModelAndView model=new ModelAndView("/gaz/gazgiris");
		Gaz gaz2=gazservice.getGaz(id);
		model.addObject("gazim",gaz2);
		if(gazservice.listGaz().size()>0){
			  model.addObject("gazlar",gazservice.listGaz());
		}
        loginInfo.getUserInfo(model);
		return model; 
	} 
	
	@RequestMapping(value = "/gazguncelle.htm")
	public ModelAndView guncelleFaktor(@ModelAttribute("gaz") Gaz gaz){
		User user= getLoggedInUser();
		ModelAndView model=new ModelAndView("/gaz/gazgiris");
		SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy hh:MM");
		gaz.setLastUpdateDate(format.getCalendar().getInstance().getTime());
		gaz.setLastUpdatedBy(loginservice.getLoggedInUser().getUserId());
		gazservice.updateGaz(gaz);
		if(gazservice.listGaz().size()>0){
			  model.addObject("gazlar",gazservice.listGaz());
		}
        loginInfo.getUserInfo(model);
		return model; 
	} 
	

}
