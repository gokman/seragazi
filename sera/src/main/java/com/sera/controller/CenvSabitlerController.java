package com.sera.controller;

import java.util.Calendar;
import java.util.List;



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sera.model.SeraCenvDegerListe;
import com.sera.model.SeraCenvSabitler;
import com.sera.model.SeraDegerSabitForm;
import com.sera.service.CenvDegerListeService;
import com.util.login.check.LoginCheck;

@Controller
@RequestMapping("/cenvsabit")
public class CenvSabitlerController {
	
	
		@Autowired
		private ApplicationContext appContext;
		
		@Autowired
		private CenvDegerListeService cenvdegerservice;
		
		private LoginCheck loginInfo = new LoginCheck();
		
		@RequestMapping(value = "/sabitGir.htm",method=RequestMethod.GET) 
		public ModelAndView kokGetir(HttpServletRequest req,@ModelAttribute("cenvsabit") SeraCenvSabitler cenvsabit) {
	                ModelAndView modell=new ModelAndView("cenvsabit/sabitGir");
	                SeraCenvDegerListe kok=cenvdegerservice.getKok();
	                modell.addObject("kok",kok);
	                loginInfo.getUserInfo(modell);
			return modell;
		}
		
		/*@RequestMapping(value = "/sabitGir/{id}.htm") 
		public ModelAndView listChild(@PathVariable("id")String id,HttpServletRequest req) {
	                ModelAndView modell=new ModelAndView("cenvsabit/sabitGir");
	                List<SeraCenvDegerListe> dallar=cenvdegerservice.listChildren(Long.parseLong(id));
	                modell.addObject("dallar",dallar);
			
			return modell;
		}*/
		
		
		@RequestMapping(value = "/sabitGir/dalgetir.htm", method = RequestMethod.POST)
		public @ResponseBody List<SeraCenvDegerListe> getShopInJSON(@RequestParam(value="id", required=true) String id) {
			
			List<SeraCenvDegerListe> dallar=cenvdegerservice.listChildren(Long.parseLong(id));
	 
			return dallar;
	 
		}
		
		@RequestMapping(value = "/degerkaydet.htm")
		public ModelAndView kaydetDeger(HttpServletRequest req,@ModelAttribute("cenvsabit") SeraCenvSabitler cenvsabit,
				BindingResult result) {
			   ModelAndView model=new ModelAndView("/");
			   cenvsabit.setcreateDate(Calendar.getInstance().getTime());
	           cenvdegerservice.saveKokCenvDegerSabitler(cenvsabit);
	           loginInfo.getUserInfo(model);
			return model;
	 
		}
}
