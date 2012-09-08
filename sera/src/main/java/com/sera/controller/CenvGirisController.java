package com.sera.controller;


	import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
	import java.util.List;
import java.util.Map;

	import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
	import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
import com.util.login.check.LoginCheck;




	@Controller
	@RequestMapping("/cenvgiris")
	public class CenvGirisController {
		
		
			@Autowired
			private ApplicationContext appContext;
			
			@Autowired
			private CenvGirisService cenvgirisservice;
			
			private LoginCheck loginInfo = new LoginCheck();
			
			//User user= (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			   
			@RequestMapping(value = "/girisgetir.htm")
			public ModelAndView getirGiris(@ModelAttribute("cenvgiris") SeraCenvGiris cenvgiris){
				  
				ModelAndView model=new ModelAndView("/cenvgiris/giris");
				SeraCenvDegerListe kok=cenvgirisservice.getKok();	
                model.addObject("kok",kok);
                loginInfo.getUserInfo(model);
				return model; 
			}   
			
			public User getLoggedInUser(){
				User user= (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				return user;
			} 
			 
			@RequestMapping(value = "/giriskaydet.htm",method = RequestMethod.POST)
			public ModelAndView kaydetGiris(HttpServletRequest req,
					    @ModelAttribute("cenvgiris") SeraCenvGiris cenvgiris,
					    BindingResult result) throws ParseException{
				User user= getLoggedInUser();
				ModelAndView model=new ModelAndView("/cenvgiris/giris");
				
				   SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy hh:MM");
				   DegerGenel a= cenvgirisservice.getGenelDeger(cenvgiris.getBaslikId());
				   cenvgiris.setBaslik(a.getBASLIK());
				   cenvgiris.setCreatedBy(user.getUsername());
				   cenvgiris.setCreationDate(format.getCalendar().getInstance().getTime());
				   cenvgiris.setParent(a.getPARENT_BASLIK());
				   cenvgiris.setParentId(a.getPARENT_ID());
				   cenvgiris.setTarih(cenvgiris.getTarih());
		           cenvgirisservice.saveCenvGiris(cenvgiris);
		           
		          SeraCenvDegerListe kok=cenvgirisservice.getKok();	
	              model.addObject("kok",kok);
	              loginInfo.getUserInfo(model);
		          return model;
		           
			}
			
			
			@RequestMapping(value = "/girisguncelle.htm")
			public ModelAndView guncelleGiris(HttpServletRequest req,
					    @ModelAttribute("cenvgiris") SeraCenvGiris cenvgiris,
					    BindingResult result) throws ParseException{
				User user= getLoggedInUser();
				ModelAndView model=new ModelAndView("/cenvgiris/giris");
				 
				   SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy hh:MM");
				   SeraCenvGiris cenvgir=cenvgirisservice.getCenvGiris(cenvgiris.getId());
				   DegerGenel a= cenvgirisservice.getGenelDeger(cenvgiris.getId());
				   cenvgir.setDeger(cenvgiris.getDeger());
				   cenvgir.setCreatedBy(user.getUsername());
				   cenvgiris.setParent(a.getPARENT_BASLIK());
				   cenvgiris.setParentId(a.getPARENT_ID());
				   cenvgir.setCreationDate(format.getCalendar().getInstance().getTime());
		           cenvgirisservice.updateCenvGiris(cenvgir);
		           
		           SeraCenvDegerListe kok=cenvgirisservice.getKok();	
		              model.addObject("kok",kok);
		              loginInfo.getUserInfo(model);
			          return model;
		           
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
			
			@RequestMapping(value = "/girisrapor.htm",method=RequestMethod.GET)
			public ModelAndView getirGirisRapor(ModelMap model) throws JRException, IOException{
				JasperReport report=null;
				JasperPrint print;
				JasperDesign design=null;
				try {
					
					design=JRXmlLoader.
					load(new FileInputStream
					(appContext.getResource("/WEB-INF/jsp/report/cenvgirisreport.jrxml").getFile()));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JRException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					report=JasperCompileManager.compileReport(design);
				} catch (JRException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				ModelAndView modelAna=new ModelAndView("/report/cenvgirisreport");
				loginInfo.getUserInfo(modelAna);
				
			/*	model.addAttribute("dataSource",cenvgirisservice.getCenvGirisReport());
				model.addAttribute("format","html");
				ModelAndView modelAndView=new ModelAndView("multiViewReport", model);*/
			
				print=JasperFillManager.fillReport(report, new HashMap(),cenvgirisservice.getCenvGirisReport());
				JasperExportManager.exportReportToHtmlFile
				(print,appContext.getResource("/WEB-INF/jsp/report/cenvgirisreport.htm").
						getFile().getAbsolutePath());
				
				//modelAna.addAllObjects(modelAndView.getModel());
				
				
				return modelAna; 
		    }   
			
   }