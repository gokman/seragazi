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




	@Controller
	@RequestMapping("/donemsonuc")
	public class CenvDonemSonucController {
		
		
			@Autowired
			private ApplicationContext appContext;
			
			@Autowired
			private DonemSonucService donemservice;
			
			@Autowired
			private CenvDegerListeService degerlisteservice;
			
			
			
			@RequestMapping(value = "/donemsonuchesapla.htm",method=RequestMethod.POST)
			public ModelAndView hesaplaDonem(HttpServletRequest req,
				    @ModelAttribute("donemsonuc") Donem donem,
				    BindingResult result) {
				DonemValidator donemvalid=new DonemValidator();
				donemvalid.validate(donem, result);
				
				if(result.hasErrors()){
					ModelAndView returnView = new ModelAndView("/donemsonuc/donemsonucgiris");
					return returnView;
				}else{
					//kayıt işlemi başlacak
					//o döneme ait yaprakların değerleri girildi ise ve 
					//o döneme ait dönem sonuç tablosunda değer yok ise devam
					ModelAndView returnView = new ModelAndView("cenvyapi/hello");
					returnView.addObject("dd",degerlisteservice.getYaprakQuantiy());
					return returnView;
					/*if(donemservice.isDonemSonucRecordFull(donem.getDonem())==false&&
					   donemservice.isYaprakDonemRecordFull(donem.getDonem())==true){
					     
						
						return new ModelAndView("/index.htm");
					}else{
						return new ModelAndView("/donemsonuc/donemsonucgiris");
					}*/
				}
				
		           
			}
			
			@RequestMapping(value = "/donemsonucgir.htm")
			public ModelAndView getirDonem(@ModelAttribute("donemsonuc") Donem donemsonuc) {
				
				   return new ModelAndView("/donemsonuc/donemsonucgiris");
		           
			}
			
			@RequestMapping(value = "/girisKayitKontrol.htm", method = RequestMethod.POST)
			public @ResponseBody Boolean isGirisDonemFull(
					@RequestParam(value="donem", required=true) String donem) {
				boolean a=donemservice.isControlGirisFull(donem);
				return a;		
				
		 
			}
			
   }