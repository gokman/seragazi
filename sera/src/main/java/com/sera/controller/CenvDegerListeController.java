package com.sera.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lookup.model.LookupMst;
import com.lookup.service.LookupMstService;
import com.sera.model.SeraCenvDegerListe;
import com.sera.model.SeraCenvSabitler;
import com.sera.model.SeraDegerSabitForm;
import com.sera.service.CenvDegerListeService;
import com.sera.util.ElemanTip;
import com.sera.validator.CenvDegerListeValidator;

@Controller
@RequestMapping("/cenvyapi")
public class CenvDegerListeController {
	
	@Autowired
	private ApplicationContext appContext;
	
	@Autowired
	private CenvDegerListeService cenvdegerservice;
	
	@RequestMapping(value = {"/yapiGiris/{id}.htm"},method=RequestMethod.GET) 
	public ModelAndView girisCenvDegerListe(HttpServletRequest req,
			@PathVariable("id") String id,@ModelAttribute("cenvdeger") SeraDegerSabitForm seragazi,BindingResult result) {
		ModelAndView model;
		
		List<SeraCenvDegerListe> dalkokliste=cenvdegerservice.listDalKokCenv();
		
		
		
		
		if(id.equals("ana")){
			model=new ModelAndView("cenvyapi/yapiGiris");
			model.addObject("parentOlayi", dalkokliste);
			//ilk aşamada boş olacağı için cenvDoluVeriler değişkenini boş şekilde
			//göndermek gerekiyor yoksa patlar
			SeraDegerSabitForm seralar=new SeraDegerSabitForm();
			seralar.setId((long)0);
			model.addObject("cenvDoluVeriler",seralar);
		
		}
		else{
			model=new ModelAndView("cenvyapi/yapiGuncelle");
			model.addObject("parentOlayi", dalkokliste);
			SeraCenvDegerListe detay=cenvdegerservice.detayCenvDeger(Long.parseLong(id));
            model.addObject("cenvDoluVeriler",detay);
            //değer sabit ise o zaman sabit nesnemizi çekmeliyiz.
            if(detay.gettip2()=="Sabit"){
            SeraCenvSabitler sabit=cenvdegerservice.getSabit(Long.parseLong(id));
            
            model.addObject("sabitdeger",sabit);
            }
          //var olan parent i göster
            if(!detay.gettip1().equals("Kök")){
			List<SeraCenvDegerListe> parent=cenvdegerservice.getParent(Long.parseLong(id));
			//parent.get(0) değişecek. liste olmasına gerek yok. kopyala yapıştır yaptık üşendik değiştirmeye :D
			model.addObject("parentOlayi2", parent.get(0));
            }else{
            model.addObject("parentOlayi2", new SeraCenvDegerListe());
            }
            
		}
        return model;
	}
	
	@RequestMapping(value = "/yapiKaydet.htm") 
	public ModelAndView saveCenvDegerListe(HttpServletRequest req,@ModelAttribute("cenvdeger") SeraDegerSabitForm seragazi,
			BindingResult result) {
		ModelAndView model=new ModelAndView("cenvyapi/hello");
	    SeraCenvDegerListe deger=new SeraCenvDegerListe(seragazi);
	    
	    CenvDegerListeValidator validator=new CenvDegerListeValidator();
		validator.validate(deger, result);
		
		if(result.hasErrors()){
			List<SeraCenvDegerListe> dalkokliste=cenvdegerservice.listDalKokCenv();
			ModelAndView returnview=new ModelAndView("cenvyapi/yapiGiris");
			returnview.addObject("parentOlayi", dalkokliste);
			return returnview;
		}
		if(deger.gettip1().equals("Kök")){
			deger.setParentId((long)0);
			deger.setSeviye((long)0);
		}else{
			deger.setSeviye(cenvdegerservice.getSeviye(deger.getParentId()).get(0)+1);
		}
			
		
        cenvdegerservice.saveKokCenvDegerListe(deger);
        SeraCenvSabitler sabit=new SeraCenvSabitler(seragazi);
        
	        if (deger.gettip1().equals("Yaprak")&&deger.gettip2().equals("Sabit")){ 	
		        sabit.sethasId(deger.getId());
		        sabit.setcreateDate(Calendar.getInstance().getTime());
				cenvdegerservice.saveKokCenvDegerSabitler(sabit);
	        }
	   
		//model.addObject("aydi", seragazi.getId());
		//model.addObject("baslik", seragazi.getBaslik());
        return model;
	}
	
	@RequestMapping(value = "/yapiGuncelle.htm") 
	public ModelAndView updateCenvDegerListe(HttpServletRequest req,@ModelAttribute("cenvdeger") SeraDegerSabitForm seragazi,
			BindingResult result) {
		ModelAndView model=new ModelAndView("cenvyapi/hello");
	    SeraCenvDegerListe deger=new SeraCenvDegerListe(seragazi);
	    
	    CenvDegerListeValidator validator=new CenvDegerListeValidator();
		validator.validate(deger, result);
		
		if(result.hasErrors()){
			List<SeraCenvDegerListe> dalkokliste=cenvdegerservice.listDalKokCenv();
			ModelAndView returnview=new ModelAndView("cenvyapi/yapiGiris");
			returnview.addObject("parentOlayi", dalkokliste);
			return returnview;
		}
		if(deger.gettip1().equals("Kök")){
			deger.setParentId((long)0);
			deger.setSeviye((long)0);
		}else{
			deger.setSeviye(cenvdegerservice.getSeviye(deger.getParentId()).get(0)+1);
		}
		
			SeraCenvSabitler sabit=new SeraCenvSabitler(seragazi);
			if(sabit.getsabit()!=null){
				sabit.setid(seragazi.getsabitId());
		        sabit.sethasId(seragazi.getId());
		        sabit.setsabit(seragazi.getsabit());
		        sabit.setcreateDate(Calendar.getInstance().getTime());
		        cenvdegerservice.updateCenvSabit(sabit);
		        
			}
			cenvdegerservice.updateCenvDeger(deger);
		//model.addObject("aydi", seragazi.getId());
		//model.addObject("baslik", seragazi.getBaslik());
        return model;
	}
	
	@RequestMapping(value = "/yapiSearch.htm") 
	public ModelAndView araCenvDeger(HttpServletRequest req,@ModelAttribute("cenvDegerSearch")SeraCenvDegerListe cenvdegerSearch,BindingResult result) {
                ModelAndView modell=new ModelAndView("cenvyapi/yapiAra");
                List<SeraCenvDegerListe> cenvdeger=cenvdegerservice.searchCenvDeger(cenvdegerSearch.getBaslik());
		        modell.addObject("cenvdegerler",cenvdeger);
		
		return modell;
	}
	
	@RequestMapping(value = "/yapiDetail.htm") 
	public ModelAndView detailCenvDegerForm(HttpServletRequest req,@RequestParam("id") long aydi ,@ModelAttribute("cenvDegerDetail")SeraCenvDegerListe cenvDegerDetail,BindingResult result) {
                ModelAndView modell=new ModelAndView("cenvyapi/yapiDetail");
                String a=Long.toString(aydi);
               
                SeraCenvDegerListe detay=cenvdegerservice.detayCenvDeger(aydi);
                modell.addObject("cenvDetay",detay);
		
		return modell;
	}
	

}
