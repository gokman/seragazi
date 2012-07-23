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

	@Override
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

	@Override
	public void updateDonemSonuc(SeraCenvDonemSonuc donemsonuc) {
		sessionFactory.getCurrentSession().update(donemsonuc);
		
	}



}
