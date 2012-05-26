package com.sera.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lookup.model.LookupMst;
import com.sera.model.SeraCenvDegerListe;

@Repository("cenvdegerlistedao")
public class CenvDegerListeDaoImpl implements CenvDegerListeDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveCenvDeger(SeraCenvDegerListe cenvDegerListe) {
        sessionFactory.getCurrentSession().save(cenvDegerListe);

	}
	
	@Override
	public List<SeraCenvDegerListe> listcenv() {
		List<SeraCenvDegerListe> list=(List<SeraCenvDegerListe>)sessionFactory.getCurrentSession().createQuery("from SeraCenvDegerListe").list();
		
		
		return list;
	}

}