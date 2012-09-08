package com.sera.donemsonuc.dao;

import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.membership.model.User;
import com.sera.model.SeraCenvDegerListe;
import com.sera.model.SeraCenvDonemSonuc;
import com.sera.model.SeraCenvGiris;
import com.util.constant.ApplicationConstants;

@Repository("donemsonucDao")
public class DonemsonucDaoImpl implements DonemSonucDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean isDonemSonucExist(String donem) {
		int kontrol;
		kontrol=(Integer)sessionFactory.getCurrentSession().createCriteria(SeraCenvDonemSonuc.class).
		add(Restrictions.eq("donem", donem)).setProjection(Projections.rowCount()).uniqueResult();
		if (kontrol>0){
			return true;
		}else{
			return false;
		}
		
	}


	public void saveDonemSonuc(SeraCenvDonemSonuc donemsonuc) {
		sessionFactory.getCurrentSession().save(donemsonuc);
		
	     
		
		
	}

	@Override
	public SeraCenvDonemSonuc getDonemSonuc(String donem, long id) {
		SeraCenvDonemSonuc donemsonuc;
		int a=sessionFactory.getCurrentSession().
		createCriteria(SeraCenvDonemSonuc.class).add(Restrictions.eq("donem", donem)).
		add(Restrictions.eq("baslikId", id)).list().size();
		if (a>0){
			donemsonuc=(SeraCenvDonemSonuc)sessionFactory.getCurrentSession().
			createCriteria(SeraCenvDonemSonuc.class).add(Restrictions.eq("donem", donem)).
			add(Restrictions.eq("baslikId", id)).list().get(0);
		}else{
			donemsonuc=null;
		}
		return donemsonuc;
	}

	
	public void updateDonemSonuc(SeraCenvDonemSonuc donemsonuc) {
		sessionFactory.getCurrentSession().update(donemsonuc);
		
	}

	@Override
	public List<SeraCenvDonemSonuc> listDonemSonuc(String donem) {
		
		return sessionFactory.getCurrentSession().createCriteria(SeraCenvDonemSonuc.class).
		add(Restrictions.eq("donem", donem)).list();
	}

	@Override
	public List<SeraCenvDonemSonuc> listDonemSonuc(String donem, Long id) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createCriteria(SeraCenvDonemSonuc.class).
		add(Restrictions.eq("donem", donem)).add(Restrictions.eq("baslikId", id)).list();
	}

	@Override
	public SeraCenvDonemSonuc getDonemSonuc(String donem, long id, String deger) {
		// TODO Auto-generated method stub
		if (sessionFactory.getCurrentSession().
		createCriteria(SeraCenvDonemSonuc.class).
		add(Restrictions.eq("donem", donem)).
		add(Restrictions.eq("baslikId", id)).
		add(Restrictions.eq("deger", deger)).
		list().size()>0){
		return (SeraCenvDonemSonuc)sessionFactory.getCurrentSession().
				createCriteria(SeraCenvDonemSonuc.class).
				add(Restrictions.eq("donem", donem)).
				add(Restrictions.eq("baslikId", id)).
				add(Restrictions.eq("deger", deger)).
				list().get(0);
		}else{
			return null;
		}
			
	}



}
