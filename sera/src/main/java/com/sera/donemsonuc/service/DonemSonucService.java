package com.sera.donemsonuc.service;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;


import com.membership.model.User;
import com.sera.model.SeraCenvDonemSonuc;
import com.sera.util.DonemSonucRaporParams;

public interface DonemSonucService {
    
	//o döneme ait tüm girişler tam mı onu kontrol edeceğiz
	public boolean isControlGirisFull(String donem);
	public boolean isDonemSonucExist(String donem);
	public void fillDonemSonuc(String donem);
	//en alt dalın o döneme ait değerini hesaplayıp sonucunu döndüren yöntemdir(method)
	public double GetirEnAltDalHesap(Long id,String donem);
	//en alt dallara ait hesaplama kayıtları tam mı onu denetleyeceğiz.
	public boolean HesaplamaKayitKontrol();
	public void updateDonemSonuc(SeraCenvDonemSonuc donemsonuc);
	
	public void deleteDonemSonuc(String donem);
	public JRDataSource getCenvDonemSonucReport(DonemSonucRaporParams params);

	}