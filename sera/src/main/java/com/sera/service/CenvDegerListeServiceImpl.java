package com.sera.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lookup.dao.LookupMstDao;
import com.sera.dao.CenvDegerListeDao;
import com.sera.model.SeraCenvDegerListe;

@Service("cenvdegerlisteservice")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CenvDegerListeServiceImpl implements CenvDegerListeService {
	
	@Autowired
	private CenvDegerListeDao cenvDegerListeDao;

	@Override
	public void saveCenvDegerListe(SeraCenvDegerListe cenvDegerListe) {
		cenvDegerListeDao.saveCenvDeger(cenvDegerListe);

	}

	@Override
	public List<SeraCenvDegerListe> listcenv() {
		return cenvDegerListeDao.listcenv();
	}

}
