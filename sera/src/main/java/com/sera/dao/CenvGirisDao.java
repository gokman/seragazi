package com.sera.dao;

import java.util.List;

import com.lookup.model.LookupMst;
import com.sera.model.SeraCenvDegerListe;
import com.sera.model.SeraCenvGiris;
import com.sera.model.SeraCenvSabitler;
import com.sera.util.object.DegerGenel;

public interface CenvGirisDao {
	public void saveCenvGiris(SeraCenvGiris cenvgiris);
	public void updateCenvGiris(SeraCenvGiris cenvgiris);
	
	public SeraCenvGiris getCenvGiris(long id);
	public DegerGenel getGenelDeger(Long id); 
	public SeraCenvDegerListe getKok();
	public SeraCenvGiris getGirisKayit(String tarih,Long id);
	
	public List<SeraCenvGiris> listCenvGiris();
	public List<SeraCenvGiris> listCenvGiris(String donem);
	public int getGirisQuantity(String donem);
}
