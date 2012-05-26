package com.lookup.dao;

import java.util.List;

import com.lookup.model.LookupMst;

public interface LookupMstDao {
    
	public void saveLookupMst(LookupMst lookupMst);

	List<LookupMst> searchLookupMst(String baslik);
	
	List<LookupMst> listLookupMst();
}
