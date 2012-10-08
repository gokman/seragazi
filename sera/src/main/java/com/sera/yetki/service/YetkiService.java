package com.sera.yetki.service;

public interface YetkiService {
	
	   public boolean controlYetkiVarMi(Long userId,Long id);
       
	   public void degistirYetki(Long userId,Long id);

	}