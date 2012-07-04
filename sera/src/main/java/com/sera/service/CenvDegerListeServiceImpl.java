package com.sera.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lookup.dao.LookupMstDao;
import com.lookup.model.LookupMst;
import com.sera.dao.CenvDegerListeDao;
import com.sera.model.SeraCenvDegerListe;
import com.sera.model.SeraCenvSabitler;

@Service("cenvdegerlisteservice")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CenvDegerListeServiceImpl implements CenvDegerListeService {
	
	@Autowired
	private CenvDegerListeDao cenvDegerListeDao;

	@Override
	public void saveKokCenvDegerListe(SeraCenvDegerListe cenvDegerListe) {
		cenvDegerListeDao.saveKokCenvDeger(cenvDegerListe);

	}

	@Override
	public List<SeraCenvDegerListe> listDalKokCenv() {
		return cenvDegerListeDao.listDalKokCenv();
	}

	@Override
	public List<Long> getSeviye(long id) {
		
		return cenvDegerListeDao.getSeviye(id);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<SeraCenvDegerListe> searchCenvDeger(String baslik) {
		return cenvDegerListeDao.searchCenvDeger(baslik);
		
	}

	@Override
	public SeraCenvDegerListe detayCenvDeger(long id) {
		
		return cenvDegerListeDao.getCenvDeger(id);
	}
	
	public List<SeraCenvDegerListe> getParent(long id){
		return cenvDegerListeDao.getParent(id);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false) 
	public void updateCenvDeger(SeraCenvDegerListe cenvdeger) {
		cenvDegerListeDao.updateCenvDeger(cenvdeger);
		
	}

	@Override
	public SeraCenvDegerListe getKok() {
		// TODO Auto-generated method stub
		return cenvDegerListeDao.getKok();
	}

	@Override
	public List<SeraCenvDegerListe> listChildren(long id) {
		// TODO Auto-generated method stub
		return cenvDegerListeDao.listChildren(id);
	}

	@Override
	public void saveKokCenvDegerSabitler(SeraCenvSabitler cenvDegerSabit) {
		cenvDegerListeDao.saveKokCenvSabit(cenvDegerSabit);
		
	}

	@Override
	public void updateCenvSabit(SeraCenvSabitler cenvsabit) {
		cenvDegerListeDao.updateCenvSabit(cenvsabit);
		
	}

}
