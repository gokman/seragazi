package com.sera.controller;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.apache.commons.io.IOUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.membership.service.LoginService;
import com.sera.model.SeraCenvDegerListe;
import com.sera.model.SeraCenvGiris;
import com.sera.service.CenvGirisService;
import com.sera.util.CenvGirisRaporParams;
import com.sera.util.object.DegerGenel;
import com.util.login.check.LoginCheck;

	@Controller
	@RequestMapping("/cenvgiris")
	public class CenvGirisController {
		
		
			@Autowired
			private ApplicationContext appContext;
			
			@Autowired
			private CenvGirisService cenvgirisservice;
			
			@Autowired
			private LoginService loginservice;
			
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
			public void kaydetGiris(HttpServletRequest req,
					    @ModelAttribute("cenvgiris") SeraCenvGiris cenvgiris,
					    BindingResult result) throws ParseException{
				   User user= getLoggedInUser();
				   //ModelAndView model=new ModelAndView("redirect:/cenvgiris/girisgetir.htm");
				
				   SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy hh:MM");
				   DegerGenel a= cenvgirisservice.getGenelDeger(cenvgiris.getBaslikId());
				
				   cenvgiris.setCreatedBy(loginservice.getByUsername(user.getUsername()).get(0).getUserId());
				   cenvgiris.setCreationDate(format.getCalendar().getInstance().getTime());
				   cenvgiris.setTarih(cenvgiris.getTarih());
		           cenvgirisservice.saveCenvGiris(cenvgiris);
		           
		          SeraCenvDegerListe kok=cenvgirisservice.getKok();	
	              //model.addObject("kok",kok);
	              //loginInfo.getUserInfo(model);
		          //return model;
		           
			}
			
			
			@RequestMapping(value = "/girisguncelle.htm")
			public ModelAndView guncelleGiris(HttpServletRequest req,
					    @ModelAttribute("cenvgiris") SeraCenvGiris cenvgiris,
					    BindingResult result) throws ParseException{
				User user= getLoggedInUser();
				ModelAndView model=new ModelAndView("/cenvgiris/giris");
				 
				   SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy hh:MM");
				   SeraCenvGiris cenvgir=cenvgirisservice.getCenvGiris(cenvgiris.getId());
				   DegerGenel a= cenvgirisservice.getGenelDeger(cenvgir.getBaslikId());
				   cenvgir.setDeger(cenvgiris.getDeger());
				   cenvgir.setCreatedBy(loginservice.getByUsername(user.getUsername()).get(0).getUserId());
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
			
			@RequestMapping(value = "/degergirisrapor.htm")
			public ModelAndView getirGirisRapor(@ModelAttribute("raporParam") CenvGirisRaporParams raporParam){
		
				ModelAndView modelAna=new ModelAndView("/report/cenvgirisreport");
				loginInfo.getUserInfo(modelAna);
				
				return modelAna; 
		    }   
			
			@RequestMapping(value = "/degergirisraporsorgu.htm",method=RequestMethod.POST)
			public ModelAndView sorguGirisRapor(@ModelAttribute("raporParam") CenvGirisRaporParams raporParam,ModelMap model) throws JRException, IOException{
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
			
				print=JasperFillManager.
				fillReport(report, new HashMap(),
						   cenvgirisservice.getCenvGirisReport(raporParam.getBaslangic(), 
								                               raporParam.getBitis()));
				
				JRExporter exporter=new JRHtmlExporter();
			//	exporter.setParameter(JRHtmlExporterParameter.CHARACTER_ENCODING,"UTF-8");
				exporter.setParameter(JRHtmlExporterParameter.JASPER_PRINT, print); 
				exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "" +
						"<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%><html><head><title>Rapor Sayfası</title></head>"); 
				exporter.setParameter(JRHtmlExporterParameter.OUTPUT_FILE_NAME, appContext.getResource("/WEB-INF/jsp/report/cenvgirisreport.htm").
						getFile().getAbsolutePath());
				exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
				exporter.exportReport();
				
				//pdf oluştur
				JasperExportManager.exportReportToPdfFile
				(print,appContext.getResource("classpath:/report/cenvgirisreport.pdf").
						getFile().getAbsolutePath());
				
				
				
				return modelAna; 
		    }
			
			@RequestMapping( value="/pdf/{filename}", method = RequestMethod.GET )
			public void getFile(@PathVariable("filename")String file,HttpServletResponse response){
				try{
				  InputStream is=new FileInputStream(appContext.getResource("classpath:/report/cenvgirisreport.pdf").
						getFile().getAbsolutePath().toString());
				  
				  IOUtils.copy(is, response.getOutputStream());
				  response.setContentType("application/pdf");
				  response.setHeader("content", "attachment; filename=cenvgirisreport.pdf");
				  response.flushBuffer();
				  
				}catch(IOException ex){
					
				 throw new RuntimeException("pdf hata oldu");
				 
				}
			}
			
   }