package com.sera.yetki.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.sera.yetki.dao.YetkiDao;
import com.util.constant.ApplicationConstants;
import com.membership.dao.LoginDao;
import com.membership.model.User;

@Service("yetkiService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class YetkiServiceImpl implements YetkiService{

	@Autowired
	private YetkiDao yetkiDao;
	
	@Autowired
	private CenvDegerListeDao seracenvdegerlisteDao;
	
	@Autowired
	private LoginDao loginDao;

	public YetkiServiceImpl() {
	}


	@Override
	public boolean controlYetkiVarMi(Long userId, Long id) {
	
		return yetkiDao.controlYetkiVarMi(userId, id);
	}

	@Override
	public void degistirYetki(Long userId, Long id) {
		List<SeraCenvDegerListe> ancestors=seracenvdegerlisteDao.getAncestors(id);
		List<SeraCenvDegerListe> descendants=seracenvdegerlisteDao.listDescendant(id);
		List<Long> parentIdler=new ArrayList<Long>();
		List<Long> childIdler=new ArrayList<Long>();
		//giriş yapanın id sini çeker
		Long loggedId=loginDao.loadUserObject(SecurityContextHolder.getContext().getAuthentication().getName()).getUserId();
		//yetki var ise tablodaki kayıtları sil
		if(yetkiDao.controlYetkiVarMi(userId, id)==true){
			//child id leri al
			//çünkü silme işleminde bunun tüm torunlarına(descendant) gitmeyi engellemem lazım
			for(int i=0;i<descendants.size();i++){
				childIdler.add(descendants.get(i).getId());
			}
			//kendi kaydını sil
			yetkiDao.deleteYetki(userId, id);
			//tüm parent kayıtlarını sil
			yetkiDao.deleteYetki(userId, childIdler);
		//yetki yok ise tabloya kayıt ekle
		}else{
			//parent id leri al
			//çünkü kaydetme işleminde bunun tüm babalarına(parent) gitmem lazım ki o yolu izleyip aşağı kadar inebilsin
			for(int i=0;i<ancestors.size();i++){
				parentIdler.add(ancestors.get(i).getId());
			}
			//kendi kaydını ekle
			//kullanıcı id yi de gönder çek
			yetkiDao.saveYetki(userId, id,loggedId);
			//tüm parent kayıtlarını ekle
			yetkiDao.saveYetki(userId, parentIdler,loggedId);
		}
		
	}
	
}