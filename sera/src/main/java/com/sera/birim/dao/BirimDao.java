package com.sera.birim.dao;

import java.util.List;


import com.sera.birim.model.Birim;

public interface BirimDao {
	
	public void saveBirim(Birim birim);
	
	public void updateBirim(Birim birim);
	
	public void deleteBirim(Birim birim);
	
	public List<Birim> listBirim();
	
	public Birim getBirim(long id);
	
	
	
}