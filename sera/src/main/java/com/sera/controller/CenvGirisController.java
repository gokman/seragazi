package com.sera.controller;


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

import com.sera.model.SeraCenvDegerListe;
import com.sera.model.SeraCenvGiris;
import com.sera.service.CenvGirisService;
import com.sera.util.object.DegerGenel;



	@Controller
	@RequestMapping("/cenvgiris")
	public class CenvGirisController {
		
		
			@Autowired
			private ApplicationContext appContext;
			
			@Autowired
			private CenvGirisService cenvgirisservice;
			   
			@RequestMapping(value = "/girisgetir.htm")
			public ModelAndView getirGiris(@ModelAttribute("cenvgiris") SeraCenvGiris cenvgiris){
				  
				ModelAndView model=new ModelAndView("/cenvgiris/giris");
				SeraCenvDegerListe kok=cenvgirisservice.getKok();	
                model.addObject("kok",kok);
				return model; 
			}   
			 
			@RequestMapping(value = "/giriskaydet.htm")
			public void kaydetGiris(HttpServletRequest req,
					    @ModelAttribute("cenvgiris") SeraCenvGiris cenvgiris,
					    BindingResult result) throws ParseException{
				   SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy hh:MM");
				   DegerGenel a= cenvgirisservice.getGenelDeger(cenvgiris.getBaslikId());
				   cenvgiris.setBaslik(a.getBaslik());
				   cenvgiris.setCreatedBy((long)1);
				   cenvgiris.setCreationDate(format.getCalendar().getInstance().getTime());
				   cenvgiris.setParent(a.getParent_baslik());
				   cenvgiris.setParentId(a.getParent_id());
				   cenvgiris.setTarih(cenvgiris.getTarih());
		           cenvgirisservice.saveCenvGiris(cenvgiris);
		           
			}
			
			@RequestMapping(value = "/girisguncelle.htm")
			public void guncelleGiris(HttpServletRequest req,
					    @ModelAttribute("cenvgiris") SeraCenvGiris cenvgiris,
					    BindingResult result) throws ParseException{
				   SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy hh:MM");
				   SeraCenvGiris cenvgir=cenvgirisservice.getCenvGiris(cenvgiris.getId());
				   cenvgir.setDeger(cenvgiris.getDeger());
				   cenvgir.setCreatedBy((long)1);
				   cenvgir.setCreationDate(format.getCalendar().getInstance().getTime());
		           cenvgirisservice.updateCenvGiris(cenvgir);
		           
			}
			
			@RequestMapping(value = "/kayitkontrol.htm", method = RequestMethod.POST)
			public @ResponseBody SeraCenvGiris getShopInJSON(@RequestParam(value="tarih", required=true) String tarih,
					@RequestParam(value="id", required=true) Long id) {
				SeraCenvGiris seracenv=cenvgirisservice.girisKayitKontrol(tarih,id);
				return seracenv;		
				
		 
			}
			
			@RequestMapping(value = "/donemhesapla.htm",method=RequestMethod.POST)
			public void hesaplaDonem(@RequestParam(value="donem", required=true) String donem) {
				System.out.println("program çalıştı");		
		           
			}
			
   }