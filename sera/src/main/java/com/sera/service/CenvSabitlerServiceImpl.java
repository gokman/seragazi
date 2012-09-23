package com.sera.service;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sera.dao.CenvGirisDao;
import com.sera.dao.CenvSabitlerDao;
import com.sera.model.SeraCenvSabitler;

@Service("cenvsabitlerservice")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CenvSabitlerServiceImpl implements CenvSabitlerService {
	
	@Autowired
	private CenvSabitlerDao cenvsabitlerDao;

	@Override
	public List<SeraCenvSabitler> listSabitler() {
		// TODO Auto-generated method stub
		return cenvsabitlerDao.listSabitler();
	}
	
	@Override
	public JRDataSource getCenvSabitlerReport() {
		// TODO Auto-generated method stub
		return cenvsabitlerDao.getSabitlerRep();
		
	}

}
