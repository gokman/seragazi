package com.sera.donemsonuc.controller;


	import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

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
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestParam;
	import org.springframework.web.bind.annotation.RequestMethod;
	import org.springframework.web.bind.annotation.ResponseBody;
    import org.springframework.web.servlet.ModelAndView;
	import com.sera.donemsonuc.service.DonemSonucService;
	import com.sera.service.CenvDegerListeService;
import com.sera.util.CenvGirisRaporParams;
import com.sera.util.DonemSonucRaporParams;
	import com.sera.util.object.Donem;
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
					ModelAndView returnView = new ModelAndView("redirect:/donemsonuc/donemsonucgir.htm");
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
			
			@RequestMapping(value = "/donemsonucsil.htm")
			public @ResponseBody boolean deleteDonem(@ModelAttribute("donemsonuc") Donem donemsonuc) {
				try{
				donemservice.deleteDonemSonuc(donemsonuc.getDonem());
				}catch(Exception e){
					return false;
				}
				return true;
		           
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
			
			@RequestMapping(value = "/donemsonucrapor.htm")
			public ModelAndView getirDonemSonucRapor(@ModelAttribute("raporParam") DonemSonucRaporParams raporParam){
		
				ModelAndView modelAna=new ModelAndView("/report/cenvdonemsonucreport");
				loginInfo.getUserInfo(modelAna);
				
				return modelAna; 
		    }   
			
			/*Raporlama amacıyla eklenen metod*/
			@RequestMapping(value = "/donemsonucraporsorgu.htm") //,method=RequestMethod.POST
			public ModelAndView sorguDonemSonucRapor(@ModelAttribute("raporParam") DonemSonucRaporParams raporParam,ModelMap model) throws JRException, IOException{
				JasperReport report=null;
				JasperPrint print;
				JasperDesign design=null;
				try {
					
					design=JRXmlLoader.
					load(new FileInputStream
					(appContext.getResource("/WEB-INF/jsp/report/cenvdonemsonucreport.jrxml").getFile()));
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
				
				ModelAndView modelAna=new ModelAndView("/report/cenvdonemsonucreport");
				loginInfo.getUserInfo(modelAna);
				
			/*	model.addAttribute("dataSource",cenvgirisservice.getCenvGirisReport());
				model.addAttribute("format","html");
				ModelAndView modelAndView=new ModelAndView("multiViewReport", model);*/
			
			
				print=JasperFillManager.fillReport(report, 
						new HashMap(),
						donemservice.getCenvDonemSonucReport(raporParam));
				
				
				
				JRExporter exporter=new JRHtmlExporter();
			//	exporter.setParameter(JRHtmlExporterParameter.CHARACTER_ENCODING,"UTF-8");
				exporter.setParameter(JRHtmlExporterParameter.JASPER_PRINT, print); 
				exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "" +
						"<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%><html><head><title>Rapor Sayfası</title></head>"); 
				exporter.setParameter(JRHtmlExporterParameter.OUTPUT_FILE_NAME, appContext.getResource("/WEB-INF/jsp/report/cenvdonemsonucreport.htm").
						getFile().getAbsolutePath());
				exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
				exporter.exportReport();
				
			
				
				
				
				return modelAna; 
		    }
			
   }