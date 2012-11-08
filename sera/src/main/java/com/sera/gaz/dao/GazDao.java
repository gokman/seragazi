package com.sera.gaz.dao;

import java.util.List;

import com.sera.gaz.model.Gaz;



public interface GazDao {
	
	public void saveGaz(Gaz gaz);
	
	public void updateGaz(Gaz gaz);
	
	public void deleteGaz(Gaz gaz);
	
	public List<Gaz> listGaz();
	
	public Gaz getGaz(long id);
	
	
	
}