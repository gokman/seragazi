package com.sera.donemsonuc.controller;


	import java.text.ParseException;

import java.text.SimpleDateFormat;
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

import com.sera.donemsonuc.service.DonemSonucService;
import com.sera.model.SeraCenvDonemSonuc;
import com.sera.model.SeraCenvGiris;
import com.sera.service.CenvDegerListeService;
import com.sera.util.object.Donem;
import com.sera.validator.DonemValidator;
import com.util.login.check.LoginCheck;




	@Controller
	@RequestMapping("/donemsonuc")
	public class CenvDonemSonucController {
		
		
			@Autowired
			private ApplicationContext appContext;
			
			@Autowired
			private DonemSonucService donemservice;
			
			@Autowired
			private CenvDegerListeService degerlisteservice;
			
			private LoginCheck loginInfo = new LoginCheck();
			
			
			
			@RequestMapping(value = "/donemsonuchesapla.htm",method=RequestMethod.POST)
			public ModelAndView hesaplaDonem(HttpServletRequest req,
				    @ModelAttribute("donemsonuc") Donem donem,
				    BindingResult result) {
				
				
			
					//kayıt işlemi başlacak
					donemservice.fillDonemSonuc(donem.getDonem());
					ModelAndView returnView = new ModelAndView("donemsonuc/donemsonucgiris");
					loginInfo.getUserInfo(returnView);
					return returnView;
				
		           
			}
			
			@RequestMapping(value = "/donemsonucgir.htm")
			public ModelAndView getirDonem(@ModelAttribute("donemsonuc") Donem donemsonuc) {
				ModelAndView mv=new ModelAndView("/donemsonuc/donemsonucgiris");
				    loginInfo.getUserInfo(mv);
				   return mv;
		           
			}
			
			@RequestMapping(value = "/girisKayitKontrol.htm", method = RequestMethod.POST)
			public @ResponseBody Boolean isGirisDonemFull(
					@RequestParam(value="donem", required=true) String donem) {
				boolean a=donemservice.isControlGirisFull(donem);
				return a;		
				
		 
			}
			
			@RequestMapping(value = "/donemSonucKayitKontrol.htm", method = RequestMethod.POST)
			public @ResponseBody Boolean isDonemSonucExist(
					@RequestParam(value="donem", required=true) String donem) {
				
				return donemservice.isDonemSonucExist(donem);		
		 
			}
			
			@RequestMapping(value = "/hesaplamaKayitKontrol.htm", method = RequestMethod.POST)
			public @ResponseBody boolean HesaplamaKayitTamMi() {
				
				return donemservice.HesaplamaKayitKontrol();		
		 
			}
			
   }