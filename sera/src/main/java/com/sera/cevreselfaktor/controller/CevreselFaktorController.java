package com.sera.cevreselfaktor.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.lookup.model.LookupMst;
import com.lookup.service.LookupMstService;
import com.membership.service.LoginService;
import com.sera.cevreselfaktor.model.SeraCevreselFaktor;
import com.sera.cevreselfaktor.service.CevreselFaktorService;
import com.sera.model.SeraCenvDegerListe;
import com.sera.model.SeraCenvGiris;
import com.sera.model.SeraCenvSabitler;
import com.sera.model.SeraDegerSabitForm;
import com.sera.service.CenvDegerListeService;
import com.sera.service.CenvGirisService;
import com.sera.util.DonemSonucRaporParams;
import com.sera.util.ElemanTip;
import com.sera.validator.CenvDegerListeValidator;
import com.util.login.check.LoginCheck;

@Controller
@RequestMapping("/faktor")
public class CevreselFaktorController {
	
	@Autowired
	private ApplicationContext appContext;
	
	@Autowired
	private CevreselFaktorService cevreselfaktorservice;
	
	@Autowired
	private LoginService loginservice;
	
	private LoginCheck loginInfo = new LoginCheck();
	
	@RequestMapping(value = "/faktorgiris.htm")
	public ModelAndView girisfaktor(@ModelAttribute("cevreselfaktor") SeraCevreselFaktor cevreselFaktor){
		  
		ModelAndView model=new ModelAndView("/cevreselfaktor/faktorgiris");
		cevreselFaktor.setId((long)0);
		model.addObject("faktorum",cevreselFaktor);
		if(cevreselfaktorservice.listCevreselFaktor().size()>0){
		  model.addObject("faktorler",cevreselfaktorservice.listCevreselFaktor());
		}
        loginInfo.getUserInfo(model);
		return model; 
	}  
	
	@RequestMapping(value = "/faktorkaydet.htm")
	public ModelAndView kaydetFaktor(@ModelAttribute("cevreselfaktor") SeraCevreselFaktor cevreselFaktor){
		  
		ModelAndView model=new ModelAndView("redirect:/faktor/faktorgiris.htm");
		User user= getLoggedInUser();
		cevreselFaktor.setCreatedBy(loginservice.getLoggedInUser().getUserId());
		cevreselFaktor.setLastUpdatedBy(loginservice.getLoggedInUser().getUserId());
		SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy hh:MM");
		cevreselFaktor.setCreateDate(format.getCalendar().getInstance().getTime());
		cevreselFaktor.setLastUpdateDate(format.getCalendar().getInstance().getTime());
		cevreselfaktorservice.saveCevreselFaktor(cevreselFaktor);
		if(cevreselfaktorservice.listCevreselFaktor().size()>0){
			  model.addObject("faktorler",cevreselfaktorservice.listCevreselFaktor());
			}
        loginInfo.getUserInfo(model);
		return model; 
	} 
	
	public User getLoggedInUser(){
		User user= (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}
	
	@RequestMapping(value = "/faktorSil.htm", method = RequestMethod.POST)
	public @ResponseBody void silFaktor(@RequestParam(value="id", required=true) Long id) {	
		SeraCevreselFaktor cevreselFaktor = new SeraCevreselFaktor();
		cevreselFaktor=cevreselfaktorservice.getCevreselFaktor(id);
		 cevreselfaktorservice.deleteCevreselFaktor(cevreselFaktor);
        
	}
	
	@RequestMapping(value = "/faktorguncelle/{id}.htm")
	public ModelAndView guncellegirisfaktor(@PathVariable("id") long id,@ModelAttribute("cevreselfaktor") SeraCevreselFaktor cevreselFaktor){
		  
		ModelAndView model=new ModelAndView("/cevreselfaktor/faktorgiris");
		SeraCevreselFaktor cevreselfaktor=cevreselfaktorservice.getCevreselFaktor(id);
		model.addObject("faktorum",cevreselfaktor);
		if(cevreselfaktorservice.listCevreselFaktor().size()>0){
			  model.addObject("faktorler",cevreselfaktorservice.listCevreselFaktor());
		}
        loginInfo.getUserInfo(model);
		return model; 
	} 
	
	@RequestMapping(value = "/faktorguncelle.htm")
	public ModelAndView guncelleFaktor(@ModelAttribute("cevreselfaktor") SeraCevreselFaktor cevreselFaktor){
		  
		User user= getLoggedInUser();
		ModelAndView model=new ModelAndView("/cevreselfaktor/faktorgiris");
		SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy hh:MM");
		cevreselFaktor.setLastUpdateDate(format.getCalendar().getInstance().getTime());
		cevreselFaktor.setLastUpdatedBy(loginservice.getLoggedInUser().getUserId());
		cevreselfaktorservice.updateCevreselFaktor(cevreselFaktor);
		if(cevreselfaktorservice.listCevreselFaktor().size()>0){
			  model.addObject("faktorler",cevreselfaktorservice.listCevreselFaktor());
		}
        loginInfo.getUserInfo(model);
		return model; 
	} 
	
	/*Raporlama amacıyla eklenen metod*/
	@RequestMapping(value = "/cevreselfaktorrapor.htm") //,method=RequestMethod.POST
	public ModelAndView CevreselFaktorRapor(ModelMap model) throws JRException, IOException{
		JasperReport report=null;
		JasperPrint print;
		JasperDesign design=null;
		try {
			
			design=JRXmlLoader.
			load(new FileInputStream
			(appContext.getResource("/WEB-INF/jsp/report/cevreselfaktorreport.jrxml").getFile()));
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
		
		ModelAndView modelAna=new ModelAndView("/report/cevreselfaktorreport");
		loginInfo.getUserInfo(modelAna);
		
	/*	model.addAttribute("dataSource",cenvgirisservice.getCenvGirisReport());
		model.addAttribute("format","html");
		ModelAndView modelAndView=new ModelAndView("multiViewReport", model);*/
	
	
		print=JasperFillManager.fillReport(report, 
				new HashMap(),
				cevreselfaktorservice.getCevreselFaktorReport());
		
		
		
		JRExporter exporter=new JRHtmlExporter();
	//	exporter.setParameter(JRHtmlExporterParameter.CHARACTER_ENCODING,"UTF-8");
		exporter.setParameter(JRHtmlExporterParameter.JASPER_PRINT, print); 
		exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "" +
				"<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%><html><head><title>Rapor Sayfası</title></head>"); 
		exporter.setParameter(JRHtmlExporterParameter.OUTPUT_FILE_NAME, appContext.getResource("/WEB-INF/jsp/report/cevreselfaktorreport.htm").
				getFile().getAbsolutePath());
		exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
		exporter.exportReport();
		
	
		
		
		
		return modelAna; 
    }
	

}
