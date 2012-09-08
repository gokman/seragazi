package com.sera.hesaplama.service;

import java.util.ArrayList;
import java.util.List;


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
import com.sera.hesaplama.dao.HesaplamaDao;
import com.sera.model.SeraCenvDegerListe;
import com.sera.model.SeraCenvDonemSonuc;
import com.sera.model.SeraCenvGiris;
import com.sera.model.SeraCenvHesaplama;
import com.sera.model.SeraCenvSabitler;
import com.sera.util.HesaplamaKutucuk;
import com.util.constant.ApplicationConstants;
import com.membership.dao.LoginDao;
import com.membership.model.User;

@Service("hesaplamaService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class HesaplamaServiceImpl implements HesaplamaService{

	@Autowired
	private HesaplamaDao hesaplamadao;
	
	@Autowired 
	private CenvDegerListeDao cenvdegerlistedao;
	
	@Autowired
	private CevreselFaktorDao cevreselfaktordao;
	

	public HesaplamaServiceImpl() {
	}


	@Override
	public int enAltDalMi(Long id) {
		// TODO Auto-generated method stub
		return hesaplamadao.enAltDalMi(id);
	}


	@Override
	public List<HesaplamaKutucuk> listYaprakFaktor(Long id) {
		//yaprakları al
		List<SeraCenvDegerListe> yapraklar=cenvdegerlistedao.listChildren(id);
		//cevresel faktorleri al
		List<SeraCevreselFaktor> faktorler=cevreselfaktordao.listCevreselFaktor();
		//bunları bizim nesneye at
		List<HesaplamaKutucuk> kutucuklar=new ArrayList<HesaplamaKutucuk>();
		//yaprakları at
		for(int i=0;i<yapraklar.size();i++){
			kutucuklar.add(new HesaplamaKutucuk("yaprak",yapraklar.get(i).getBaslik(), yapraklar.get(i).getId()));
		}
		//çevresel faktörleri at
		for(int i=0;i<faktorler.size();i++){
			kutucuklar.add(new HesaplamaKutucuk("faktor",faktorler.get(i).getAciklama(),faktorler.get(i).getId()));
		}
		return kutucuklar;
	}


	@Override
	public void saveHesaplama(SeraCenvHesaplama hesaplama) {
		hesaplamadao.saveHesaplama(hesaplama);
		
	}


	@Override
	public int kayitKontrol(Long id, String detay) {
		// TODO Auto-generated method stub
		return hesaplamadao.kayitKontrol(id,detay);
	}


	@Transactional(propagation = Propagation.REQUIRED, readOnly = false) 
	public void updateHesaplama(SeraCenvHesaplama hesaplama) {
		SeraCenvHesaplama hesap=hesaplamadao.getRow(hesaplama.getDetay(), hesaplama.getParentId());
		hesap.setHesaplama(hesaplama.getHesaplama());
		hesap.setCreationDate(hesaplama.getCreationDate());
		hesap.setCreatedBy(hesaplama.getCreatedBy());
		hesaplamadao.updateHesaplama(hesap);
		
	}

	
}