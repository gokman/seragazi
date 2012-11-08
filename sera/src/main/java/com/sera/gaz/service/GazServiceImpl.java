package com.sera.gaz.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sera.gaz.dao.GazDao;
import com.sera.gaz.model.Gaz;

@Service("gazService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class GazServiceImpl implements GazService{

	@Autowired
	private GazDao gazdao;
	

	public GazServiceImpl() {
	}


	@Override
	public void saveGaz(Gaz Gaz) {
		// TODO Auto-generated method stub
		gazdao.saveGaz(Gaz);
		
	}


	@Transactional(propagation = Propagation.REQUIRED, readOnly = false) 
	public void updateGaz(Gaz Gaz) {
		// TODO Auto-generated method stub
		gazdao.updateGaz(Gaz);
	}


	@Transactional(propagation = Propagation.REQUIRED, readOnly = false) 
	public void deleteGaz(Gaz gaz) {
		// TODO Auto-generated method stub
		gazdao.deleteGaz(gaz);
	}


	@Override
	public List<Gaz> listGaz() {
		// TODO Auto-generated method stub
		return gazdao.listGaz();
	}


	@Override
	public Gaz getGaz(long id) {
		// TODO Auto-generated method stub
		return gazdao.getGaz(id);
	}

	
	
}