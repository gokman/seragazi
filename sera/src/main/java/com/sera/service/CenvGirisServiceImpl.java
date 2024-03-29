package com.sera.service;

import java.util.List;


import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lookup.dao.LookupMstDao;
import com.lookup.model.LookupMst;
import com.sera.dao.CenvDegerListeDao;
import com.sera.dao.CenvGirisDao;
import com.sera.model.SeraCenvDegerListe;
import com.sera.model.SeraCenvGiris;
import com.sera.model.SeraCenvSabitler;
import com.sera.util.object.DegerGenel;

@Service("cenvgirisservice")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CenvGirisServiceImpl implements CenvGirisService {
	
	@Autowired
	private CenvGirisDao cenvgirisDao;

	@Override
	public void saveCenvGiris(SeraCenvGiris cenvgiris) {
		cenvgirisDao.saveCenvGiris(cenvgiris);

	}
	@Override
	public SeraCenvDegerListe getKok() {
		// TODO Auto-generated method stub
		return cenvgirisDao.getKok();
	}

	@Override
	public List<SeraCenvGiris> listCenvGiris() {
		return cenvgirisDao.listCenvGiris();
	}

	
	@Override
	public SeraCenvGiris getCenvGiris(Long id) {
		// TODO Auto-generated method stub
		return cenvgirisDao.getCenvGiris(id);
	}

	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false) 
	public void updateCenvGiris(SeraCenvGiris cenvgiris) {
		cenvgirisDao.updateCenvGiris(cenvgiris);
		
	}

	@Override
	public DegerGenel getGenelDeger(Long id) {
		// TODO Auto-generated method stub
		return cenvgirisDao.getGenelDeger(id);
	}
	@Override
	public DegerGenel getGenelDeger(Long id,String tarih) {
		// TODO Auto-generated method stub
		return cenvgirisDao.getGenelDeger(id,tarih);
	}
	@Override
	public SeraCenvGiris girisKayitKontrol(String tarih, Long id) {
		
		return cenvgirisDao.getGirisKayit(tarih,id);
	}
	@Override
	public List<SeraCenvGiris> listCenvGiris(String donem) {
		// TODO Auto-generated method stub
		return cenvgirisDao.listCenvGiris(donem);
	}
	@Override
	public JRDataSource getCenvGirisReport() {
		// TODO Auto-generated method stub
		return cenvgirisDao.getCenvGirisReport();
		
	}
	@Override
	public JRDataSource getCenvGirisReport(String basla, String bitir) {
		
		return cenvgirisDao.listCenvGiris(basla, bitir);
	}

	
}
