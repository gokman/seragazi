package com.sera.cevreselfaktor.service;

import java.util.List;


import net.sf.jasperreports.engine.JRDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sera.cevreselfaktor.dao.CevreselFaktorDao;
import com.sera.cevreselfaktor.model.SeraCevreselFaktor;
import com.sera.dao.CenvDegerListeDao;
import com.sera.dao.CenvGirisDao;
import com.sera.dao.CenvSabitlerDao;
import com.sera.donemsonuc.dao.DonemSonucDao;
import com.sera.model.SeraCenvDegerListe;
import com.sera.model.SeraCenvDonemSonuc;
import com.sera.model.SeraCenvGiris;
import com.sera.model.SeraCenvSabitler;
import com.util.constant.ApplicationConstants;
import com.membership.dao.LoginDao;
import com.membership.model.User;

@Service("cevreselFaktorService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CevreselFaktorServiceImpl implements CevreselFaktorService{

	@Autowired
	private CevreselFaktorDao cevreselfaktordao;
	

	public CevreselFaktorServiceImpl() {
	}


	@Override
	public void saveCevreselFaktor(SeraCevreselFaktor cevreselFaktor) {
		// TODO Auto-generated method stub
		cevreselfaktordao.saveCevreselFaktor(cevreselFaktor);
		
	}


	@Transactional(propagation = Propagation.REQUIRED, readOnly = false) 
	public void updateCevreselFaktor(SeraCevreselFaktor cevreselFaktor) {
		// TODO Auto-generated method stub
		cevreselfaktordao.updateCevreselFaktor(cevreselFaktor);
	}


	@Transactional(propagation = Propagation.REQUIRED, readOnly = false) 
	public void deleteCevreselFaktor(SeraCevreselFaktor cevreselFaktor) {
		// TODO Auto-generated method stub
		cevreselfaktordao.deleteCevreselFaktor(cevreselFaktor);
	}


	@Override
	public List<SeraCevreselFaktor> listCevreselFaktor() {
		// TODO Auto-generated method stub
		return cevreselfaktordao.listCevreselFaktor();
	}


	@Override
	public SeraCevreselFaktor getCevreselFaktor(long id) {
		// TODO Auto-generated method stub
		return cevreselfaktordao.getCevreselFaktor(id);
	}


	@Override
	public JRDataSource getCevreselFaktorReport() {
		// TODO Auto-generated method stub
		return cevreselfaktordao.getCevreselFaktorReport();
	}

	
	
}