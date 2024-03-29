package com.sera.service;

import java.util.ArrayList;
import java.util.List;


import net.sf.jasperreports.engine.JRDataSource;

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

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false) 
	public void updateCenvSabit(SeraCenvSabitler cenvsabit) {
		cenvDegerListeDao.updateCenvSabit(cenvsabit);
		
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false) 
	public void updateCenvDeger(SeraCenvDegerListe cenvdeger) {
		cenvDegerListeDao.updateCenvDeger(cenvdeger);
		
	}


	@Override
	public SeraCenvSabitler getSabit(Long id) {
		
		return cenvDegerListeDao.getCenvSabit(id);
	}

	@Override
	public int getYaprakQuantiy() {
		// TODO Auto-generated method stub
		return cenvDegerListeDao.getYaprakQuantity();
	}

	@Override
	public boolean isKokExist() {
		// TODO Auto-generated method stub
		return cenvDegerListeDao.isKokExist();
	}

	@Override
	public String checkChildType(Long parentId) {
		String childType="";
		List<SeraCenvDegerListe> children=cenvDegerListeDao.listChildren(parentId);
		if(children.size()>0){
		childType=children.get(0).gettip1();
		}
		return childType;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteAllDescendant(Long id) {
		List<SeraCenvDegerListe> alldescendant=new ArrayList<SeraCenvDegerListe>();
		alldescendant=cenvDegerListeDao.listDescendant(id);
		for(int i=0;i<alldescendant.size();i++){
			cenvDegerListeDao.deleteCenvDeger(alldescendant.get(i));
		}
		//en son kendisini sil
		cenvDegerListeDao.deleteCenvDeger(cenvDegerListeDao.getCenvDeger(id));
		
	}

	@Override
	public List<SeraCenvDegerListe> listEnAltDal() {
		
		return cenvDegerListeDao.ListEnAltDal();
	}
	
	@Override
	public JRDataSource getCenvDegerListeReport() {
		// TODO Auto-generated method stub
		return cenvDegerListeDao.getCenvDegerListeReport();
		
	}
	

	@Override
	public List<SeraCenvDegerListe> listAncestors(long id) {
		// TODO Auto-generated method stub
		return cenvDegerListeDao.getAncestors(id);
	}

	@Override
	public List<SeraCenvDegerListe> listDescendants(long id) {
		// TODO Auto-generated method stub
		return cenvDegerListeDao.listDescendant(id);
	}

	@Override
	public List<SeraCenvDegerListe> listTumYapi() {
		
		return cenvDegerListeDao.listTumYapi();
	}
}