package com.sera.hesaplama.controller;


	import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
	import java.util.List;
	import javax.servlet.http.HttpServletRequest;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
	import org.springframework.stereotype.Controller;
	import org.springframework.validation.BindingResult;
	import org.springframework.web.bind.annotation.ModelAttribute;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestParam;
	import org.springframework.web.bind.annotation.RequestMethod;
	import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.sera.donemsonuc.service.DonemSonucService;
import com.sera.hesaplama.dao.HesaplamaDao;
import com.sera.hesaplama.service.HesaplamaService;
import com.sera.model.SeraCenvDegerListe;
import com.sera.model.SeraCenvDonemSonuc;
import com.sera.model.SeraCenvGiris;
import com.sera.model.SeraCenvHesaplama;
import com.sera.service.CenvDegerListeService;
import com.sera.service.CenvGirisService;
import com.sera.util.HesaplamaKutucuk;
import com.sera.util.object.Donem;
import com.sera.validator.DonemValidator;
import com.util.login.check.LoginCheck;




	@Controller
	@RequestMapping("/hesaplama")
	public class HesaplamaController {
		
		
			@Autowired
			private ApplicationContext appContext;
			
			@Autowired
			private CenvGirisService cenvgirisservice;
		
			@Autowired
			private HesaplamaService hesaplamaservice;
			
			private LoginCheck loginInfo = new LoginCheck();
			
			
		
			
			@RequestMapping(value = "/kontrolEnAltDal.htm", method = RequestMethod.POST)
			public @ResponseBody int isDonemSonucExist(
					@RequestParam(value="id", required=true) Long id) {
				
				return hesaplamaservice.enAltDalMi(id);	
		 
			}
			
			@RequestMapping(value = "/kayitVarMi.htm", method = RequestMethod.POST)
			public @ResponseBody int kayitVarMi(
					@RequestParam(value="id", required=true) Long id,
					@RequestParam(value="detay", required=true) String detay) {
				
				return hesaplamaservice.kayitKontrol(id,detay);	
		 
			}
			
			@RequestMapping(value = "/yaprakFaktorGetir.htm", method = RequestMethod.POST)
			public @ResponseBody List<HesaplamaKutucuk> listYaprakFaktor(
					@RequestParam(value="id", required=true) Long id) {
				
				return hesaplamaservice.listYaprakFaktor(id);	
		 
			}
			//not: burada modelandview nesnesi döndürmek istedim ama patladı
			@RequestMapping(value = "/islemkaydet.htm", method = RequestMethod.POST)
			public @ResponseBody int hesaplamaKaydet(
					@RequestParam(value="id", required=true) Long id,
					@RequestParam(value="hesap", required=true) String hesap,
					@RequestParam(value="detay", required=true) String detay) {
				
				User user= getLoggedInUser();
				SeraCenvHesaplama hesaplama=new SeraCenvHesaplama();
				hesaplama.setParentId(id); 
				hesaplama.setHesaplama(hesap);
				hesaplama.setCreationDate(Calendar.getInstance().getTime());
				hesaplama.setCreatedBy(user.getUsername());
				hesaplama.setDetay(detay);
				hesaplamaservice.saveHesaplama(hesaplama);
				
			//	ModelAndView model=new ModelAndView("redirect:/hesaplama/hesaplamagiris");
				//model.setView(new MappingJacksonJsonView());
				
				return 1;	
		 
			}
			
			@RequestMapping(value = "/islemguncelle.htm", method = RequestMethod.POST)
			public @ResponseBody int hesaplamaGuncelle(
					@RequestParam(value="id", required=true) Long id,
					@RequestParam(value="hesap", required=true) String hesap,
					@RequestParam(value="detay", required=true) String detay) {
				
                /*
                not: nedendir bilinmez jsp sayfasında hesap parametresinin içinde + olduğu halde burada
				içi boş gözüküyor. bu yüzden boş görünen yere + koy diyeceğiz.
				*/
				hesap=hesap.replace(" ","+");
				User user= getLoggedInUser();
				SeraCenvHesaplama hesaplama=new SeraCenvHesaplama();
				hesaplama.setParentId(id); 
				hesaplama.setHesaplama(hesap);
				hesaplama.setCreationDate(Calendar.getInstance().getTime());
				hesaplama.setCreatedBy(user.getUsername());
				hesaplama.setDetay(detay);
				hesaplamaservice.updateHesaplama(hesaplama);
				
			//	ModelAndView model=new ModelAndView("redirect:/hesaplama/hesaplamagiris");
				//model.setView(new MappingJacksonJsonView());
				
				return 1;	
		 
			}
			
			public User getLoggedInUser(){
				User user= (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				return user;
			}
			
			@RequestMapping(value = "/hesaplamaGiris.htm",method = RequestMethod.GET) 
			public ModelAndView getHesaplamaGiris() {
                ModelAndView model=new ModelAndView("/hesaplama/hesaplamagiris");
                SeraCenvDegerListe kok=cenvgirisservice.getKok();	
                model.addObject("kok",kok);
				loginInfo.getUserInfo(model);
				return model; 
			}	
			
   }