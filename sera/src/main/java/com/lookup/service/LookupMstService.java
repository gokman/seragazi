package com.lookup.service;

import java.util.List;

import com.lookup.model.LookupMst;

public interface LookupMstService {
	
	public void saveLookupMst(LookupMst lookupMst);
	public List<LookupMst> searchLookupMst(String baslik);
	public List<LookupMst> listLookupMst();
}
