package com.sera.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;



import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
import com.sera.model.SeraCenvSabitler;
import com.sera.model.SeraDegerSabitForm;
import com.sera.service.CenvDegerListeService;
import com.sera.service.CenvGirisService;
import com.sera.service.CenvSabitlerService;
import com.sera.util.CenvGirisRaporParams;
import com.util.login.check.LoginCheck;

@Controller
@RequestMapping("/cenvsabit")
public class CenvSabitlerController {
	
	
		@Autowired
		private ApplicationContext appContext;
		
		@Autowired
		private CenvDegerListeService cenvdegerservice;
		
		@Autowired
		private CenvSabitlerService cenvsabitlerservice;
		
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
		/*Raporlama amacıyla eklenen metod*/
		/*
		@RequestMapping(value = "/sabitlerrapor.htm")
		public ModelAndView getirGirisRapor(@ModelAttribute("raporParam") CenvGirisRaporParams raporParam){
	
			ModelAndView modelAna=new ModelAndView("/report/cenvsabitlerreport");
			loginInfo.getUserInfo(modelAna);
			
			return modelAna; 
	    } 
	    */
	    
		
		/*Raporlama amacıyla eklenen metod*/
		@RequestMapping(value = "/sabitlerrapor.htm") //,method=RequestMethod.POST
		public ModelAndView sorguGirisRapor(ModelMap model) throws JRException, IOException{
			JasperReport report=null;
			JasperPrint print;
			JasperDesign design=null;
			try {
				
				design=JRXmlLoader.
				load(new FileInputStream
				(appContext.getResource("/WEB-INF/jsp/report/cenvsabitreport.jrxml").getFile()));
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
			
			ModelAndView modelAna=new ModelAndView("/report/cenvsabitlerreport");
			loginInfo.getUserInfo(modelAna);
			
		/*	model.addAttribute("dataSource",cenvgirisservice.getCenvGirisReport());
			model.addAttribute("format","html");
			ModelAndView modelAndView=new ModelAndView("multiViewReport", model);*/
		
		
			print=JasperFillManager.fillReport(report, 
					new HashMap(),
					cenvsabitlerservice.getCenvSabitlerReport());
			
			
			
			JRExporter exporter=new JRHtmlExporter();
		//	exporter.setParameter(JRHtmlExporterParameter.CHARACTER_ENCODING,"UTF-8");
			exporter.setParameter(JRHtmlExporterParameter.JASPER_PRINT, print); 
			exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "" +
					"<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%><html><head><title>Rapor Sayfası</title></head>"); 
			exporter.setParameter(JRHtmlExporterParameter.OUTPUT_FILE_NAME, appContext.getResource("/WEB-INF/jsp/report/cenvsabitlerreport.htm").
					getFile().getAbsolutePath());
			exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
			exporter.exportReport();
			
			//pdf oluştur
			JasperExportManager.exportReportToPdfFile
			(print,appContext.getResource("classpath:/report/cenvsabitlerreport.pdf").
					getFile().getAbsolutePath());
			
			
			
			return modelAna; 
	    }
}
