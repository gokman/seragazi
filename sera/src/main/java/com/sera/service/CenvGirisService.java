package com.sera.service;

import java.util.List;

import com.lookup.model.LookupMst;
import com.sera.model.SeraCenvDegerListe;
import com.sera.model.SeraCenvGiris;
import com.sera.model.SeraCenvSabitler;
import com.sera.util.object.DegerGenel;

public interface CenvGirisService {
     public void saveCenvGiris(SeraCenvGiris cenvDegerListe);
     public List<SeraCenvGiris> listCenvGiris();
     public void updateCenvGiris(SeraCenvGiris cenvdeger);
     public SeraCenvGiris getCenvGiris(Long id);
     public DegerGenel getGenelDeger(Long id); 
     public SeraCenvDegerListe getKok();
     public SeraCenvGiris girisKayitKontrol(String tarih,Long id);
}
