package com.lookup.controller;

import java.util.List;



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.lookup.model.LookupMst;
import com.lookup.service.LookupMstService;
import com.sera.model.SeraCenvDegerListe;
import com.sera.util.ElemanTip;

@Controller
@RequestMapping("/birinci")
public class LookupMstController {
	
	
	@Autowired
	private ApplicationContext appContext;

	@RequestMapping(value = "/deneme.htm",method = RequestMethod.GET) 
	public ModelAndView getLookupForm(@ModelAttribute("seragazi") SeraCenvDegerListe seragazi,BindingResult result) {

		return new ModelAndView("hello");
	}	
	

}
