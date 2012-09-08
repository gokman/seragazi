package com.sera.hesaplama.dao;

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
import com.sera.model.SeraCenvHesaplama;
import com.sera.util.HesaplamaKutucuk;
import com.util.constant.ApplicationConstants;

@Repository("hesaplamaDao")
public class HesaplamaDaoImpl implements HesaplamaDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int enAltDalMi(long id) {
		if(getcocukTipi(id).equals("Yaprak")){
			return 1;
		}else{
		return 0;
		}
	}

	@Override
	public String getcocukTipi(long id) {
		SeraCenvDegerListe cenvdeger=(SeraCenvDegerListe)sessionFactory.getCurrentSession().
		createCriteria(SeraCenvDegerListe.class).
		add(Restrictions.eq("parentId", id)).list().get(0);
		
		return cenvdeger.gettip1().toString();
	}

	@Override
	public void saveHesaplama(SeraCenvHesaplama hesaplama) {
		sessionFactory.getCurrentSession().save(hesaplama);
		
	}

	@Override
	public int kayitKontrol(Long id, String detay) {
		return sessionFactory.getCurrentSession().
		                      createCriteria(SeraCenvHesaplama.class).
						      add(Restrictions.eq("parentId", id)).
						      add(Restrictions.eq("detay", detay)).list().size();
		
	}

	@Override
	public void updateHesaplama(SeraCenvHesaplama hesaplama) {
		sessionFactory.getCurrentSession().update(hesaplama);
		
	}

	@Override
	public SeraCenvHesaplama getRow(String detay, Long id) {
		
		SeraCenvHesaplama hesaplama;
		
		if (sessionFactory.
				 getCurrentSession().createCriteria(SeraCenvHesaplama.class).
			     add(Restrictions.eq("parentId", id)).
			     add(Restrictions.eq("detay", detay)).list().size()>0){
		
		
		hesaplama=(SeraCenvHesaplama)sessionFactory.
									 getCurrentSession().createCriteria(SeraCenvHesaplama.class).
								     add(Restrictions.eq("parentId", id)).
								     add(Restrictions.eq("detay", detay)).list().get(0);
		
		}else{
			
		hesaplama=null;
		
		}
		
		return hesaplama;
		
	}

	@Override
	public List<SeraCenvHesaplama> listHesaplama(Long id) {
		
		return sessionFactory.getCurrentSession().createCriteria(SeraCenvHesaplama.class).
		add(Restrictions.eq("parentId", id)).list();
	}


}
