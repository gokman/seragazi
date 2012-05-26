package com.sera.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lookup.model.LookupMst;
import com.lookup.service.LookupMstService;
import com.sera.model.SeraCenvDegerListe;
import com.sera.service.CenvDegerListeService;
import com.sera.util.ElemanTip;

@Controller
@RequestMapping("/degergiris")
public class CenvDegerListeController {
	
	@Autowired
	private ApplicationContext appContext;
	
	@Autowired
	private CenvDegerListeService cenvdegerservice;
	
	@RequestMapping(value = "/degerGiris.htm",method = RequestMethod.GET) 
	public ModelAndView saveCenvDegerListe(@ModelAttribute("cenvdeger") SeraCenvDegerListe seragazi,BindingResult result) {
		ModelAndView model=new ModelAndView("cenvdeger/cenvdegerGiris");
        model.addObject("tipListe",);
        return model;
	}	
	
	/*@RequestMapping(value = "/degerGiris.htm") 
	public ModelAndView listeDoldurForm(HttpServletRequest req,@ModelAttribute("cenvdeger") SeraCenvDegerListe seragazi,BindingResult result) {
                ModelAndView modell=new ModelAndView("cenvdeger/cenvdegerGiris");
                
                //List<LookupMst> detay=lookupMstService.searchLookupMst(lookupDetail.getBaslik());
                modell.addObject("tipListe",cenvdegerservice.listcenv());
		
		return modell;
	}*/
	

}
