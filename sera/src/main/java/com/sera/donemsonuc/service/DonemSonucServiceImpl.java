package com.sera.donemsonuc.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

@Service("donemsonucService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DonemSonucServiceImpl implements DonemSonucService{

	@Autowired
	private DonemSonucDao donemsonucdao;
	
	@Autowired
	private CenvGirisDao girisdao;
	
	@Autowired
	private CenvDegerListeDao degerlistedao;
	
	@Autowired
	private CenvSabitlerDao sabitlerdao;

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

	@Override
	public boolean isDonemSonucExist(String donem) {
		// TODO Auto-generated method stub
		return donemsonucdao.isDonemSonucExist(donem);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false) 
	public void fillDonemSonuc(String donem) {
		
		List<SeraCenvSabitler> sabitler=sabitlerdao.listSabitler();
		List<SeraCenvGiris> girisler=girisdao.listCenvGiris(donem); 
		List<SeraCenvDegerListe> dalveKokler;
		SeraCenvDonemSonuc donemsonuctemp;
		long sonuc;
		//sabit değerleri yukarı doğru dallara ve köke ekle
		//başla
		for(int i=0;i<sabitler.size();i++){
			dalveKokler=degerlistedao.getAncestors(sabitler.get(i).gethasId());
			//elimizdeki sabit değerli yaprakın tüm dedelerini çektik ve şimdi
			//onlara bundaki değeri ekleyeceğiz
			for(int j=0;j<dalveKokler.size();j++){
				//kayıt var mı diye kontrol et
				donemsonuctemp=donemsonucdao.getDonemSonuc(donem,dalveKokler.get(j).getId());
				//kaydet
				if(donemsonuctemp==null){
					SeraCenvDonemSonuc donemsonuc=new SeraCenvDonemSonuc();
				    donemsonuc.setSonuc(sabitler.get(i).getsabit());
				    donemsonuc.setBaslikId(dalveKokler.get(j).getId());
				    donemsonuc.setDonem(donem);
				    donemsonucdao.saveDonemSonuc(donemsonuc);
			    //güncelle
				}else{
					//mevcut sonucu al
					sonuc=donemsonuctemp.getSonuc();
					sonuc=sonuc+sabitler.get(i).getsabit();
					donemsonuctemp.setSonuc(sonuc);
					donemsonucdao.updateDonemSonuc(donemsonuctemp);
					
				}
			    	    
			}
		}	
		//bitir
		//elle girilen değerleri yukarı doğru dallara ve köke ekle
		//başla
		for(int i=0;i<girisler.size();i++){
			dalveKokler=degerlistedao.getAncestors(girisler.get(i).getBaslikId());
			//elimizdeki sabit değerli yaprakın tüm dedelerini çektik ve şimdi
			//onlara bundaki değeri ekleyeceğiz
			for(int j=0;j<dalveKokler.size();j++){
				//kayıt var mı diye kontrol et
				donemsonuctemp=donemsonucdao.getDonemSonuc(donem,dalveKokler.get(j).getId());
				//kaydet
				if(donemsonuctemp==null){
					SeraCenvDonemSonuc donemsonuc=new SeraCenvDonemSonuc();
				    donemsonuc.setSonuc(girisler.get(i).getDeger());
				    donemsonuc.setBaslikId(dalveKokler.get(j).getId());
				    donemsonuc.setDonem(donem);
				    donemsonucdao.saveDonemSonuc(donemsonuc);
			    //güncelle
				}else{
					//mevcut sonucu al
					sonuc=donemsonuctemp.getSonuc();
					sonuc=sonuc+girisler.get(i).getDeger();
					donemsonuctemp.setSonuc(sonuc);
					donemsonucdao.updateDonemSonuc(donemsonuctemp);
					
				}
			    	    
			}
		}	
		//bitir
		
	}
}