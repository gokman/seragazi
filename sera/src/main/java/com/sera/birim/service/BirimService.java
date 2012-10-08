package com.sera.birim.service;

import java.util.List;


import com.membership.model.User;
import com.sera.birim.model.Birim;
import com.sera.cevreselfaktor.model.SeraCevreselFaktor;

public interface BirimService {
    
    public void saveBirim(Birim birim);
	
	public void updateBirim(Birim birim);
	
	public void deleteBirim(Birim birim);
	
	public List<Birim> listBirim();
	
	public Birim getBirim(long id);

	}