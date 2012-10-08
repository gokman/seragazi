package com.sera.birim.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sera.birim.dao.BirimDao;
import com.sera.birim.model.Birim;

@Service("BirimService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class BirimServiceImpl implements BirimService{

	@Autowired
	private BirimDao birimdao;
	

	public BirimServiceImpl() {
	}


	@Override
	public void saveBirim(Birim Birim) {
		// TODO Auto-generated method stub
		birimdao.saveBirim(Birim);
		
	}


	@Transactional(propagation = Propagation.REQUIRED, readOnly = false) 
	public void updateBirim(Birim Birim) {
		// TODO Auto-generated method stub
		birimdao.updateBirim(Birim);
	}


	@Transactional(propagation = Propagation.REQUIRED, readOnly = false) 
	public void deleteBirim(Birim birim) {
		// TODO Auto-generated method stub
		birimdao.deleteBirim(birim);
	}


	@Override
	public List<Birim> listBirim() {
		// TODO Auto-generated method stub
		return birimdao.listBirim();
	}


	@Override
	public Birim getBirim(long id) {
		// TODO Auto-generated method stub
		return birimdao.getBirim(id);
	}

	
	
}