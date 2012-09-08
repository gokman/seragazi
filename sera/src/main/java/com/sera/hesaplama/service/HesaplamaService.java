package com.sera.hesaplama.service;

import java.util.List;


import com.membership.model.User;
import com.sera.cevreselfaktor.model.SeraCevreselFaktor;
import com.sera.model.SeraCenvHesaplama;
import com.sera.util.HesaplamaKutucuk;

public interface HesaplamaService {
    
   public int enAltDalMi(Long id);
   public List<HesaplamaKutucuk> listYaprakFaktor(Long id);
   public void saveHesaplama(SeraCenvHesaplama hesaplama);
   public int kayitKontrol(Long id,String detay);
   public void updateHesaplama(SeraCenvHesaplama hesaplama);

	}