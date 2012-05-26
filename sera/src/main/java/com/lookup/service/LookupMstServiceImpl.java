package com.lookup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lookup.dao.LookupMstDao;
import com.lookup.model.LookupMst;

@Service("lookupMstService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class LookupMstServiceImpl implements LookupMstService{
	
	@Autowired
	private LookupMstDao lookupMstDao;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void saveLookupMst(LookupMst lookupMst) {
		lookupMstDao.saveLookupMst(lookupMst);
		
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<LookupMst> searchLookupMst(String baslik) {
		return lookupMstDao.searchLookupMst(baslik);
		
	}

	@Override
	public List<LookupMst> listLookupMst() {
		// TODO Auto-generated method stub
		return lookupMstDao.listLookupMst();
	}

}
