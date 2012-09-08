package com.sera.hesaplama.dao;

import java.util.List;

import com.membership.model.User;
import com.sera.model.SeraCenvDegerListe;
import com.sera.model.SeraCenvDonemSonuc;
import com.sera.model.SeraCenvHesaplama;
import com.sera.util.HesaplamaKutucuk;


public interface HesaplamaDao {
     
	public int enAltDalMi(long id);
	public int kayitKontrol(Long id,String detay);
	
	public void saveHesaplama(SeraCenvHesaplama hesaplama);
	
	public void updateHesaplama(SeraCenvHesaplama hesaplama);
	
	public SeraCenvHesaplama getRow(String detay,Long id);
	public String getcocukTipi(long id);
	
	public List<SeraCenvHesaplama> listHesaplama(Long id);
  
}
