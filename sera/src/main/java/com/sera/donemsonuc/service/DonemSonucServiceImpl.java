package com.sera.donemsonuc.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sera.dao.CenvDegerListeDao;
import com.sera.dao.CenvGirisDao;
import com.sera.donemsonuc.dao.DonemSonucDao;
import com.util.constant.ApplicationConstants;
import com.membership.dao.LoginDao;
import com.membership.model.User;

@Service("donemsonucService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DonemSonucServiceImpl implements DonemSonucService{

	@Autowired
	private DonemSonucDao donemsonucdao;
	
	@Autowired
	private CenvGirisDao girisdao;
	
	@Autowired
	private CenvDegerListeDao degerlistedao;

	public DonemSonucServiceImpl() {
	}

	@Override
	public boolean isControlGirisFull(String donem) {
		//sistemdeki elle değer girilen yaprak sayısı
		int quantityYaprakElle=degerlistedao.getYaprakQuantity("Elle");
		//belirli döneme ait girilen değer sayısı
		int quantityGiris=girisdao.getGirisQuantity(donem);
		//iki değer de eşit ise anlayacağız ki yaprakların değerleri o döneme girilmiş.
		if(quantityGiris==quantityYaprakElle)
			return true;
		else
		    return false;
	}



}
