package com.sera.yetki.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.sera.yetki.model.SeraCenvYetkiler;
import com.util.constant.ApplicationConstants;

@Repository("yetkiDao")
public class YetkiDaoImpl implements YetkiDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveYetki(Long userId, List<Long> id,Long loggedId) {
		
		Session session=sessionFactory.getCurrentSession();
		Transaction transaction=null;
		List<SeraCenvYetkiler> yetkiler=new ArrayList<SeraCenvYetkiler>();
		SeraCenvYetkiler yetki=new SeraCenvYetkiler();
		SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy hh:MM");
		
		for(int i=0;i<id.size();i++){
			
			yetki.setUserId(userId);
			yetki.setCreateDate(format.getCalendar().getInstance().getTime());
			yetki.setCreatedBy(loggedId);
			yetki.setBaslikId(id.get(i));
			yetkiler.add(yetki);
		}
		
		try {
			
			transaction=session.beginTransaction();
			//burada ne lazım ise onları bulup kaydet	
			session.save(yetkiler);
			session.flush();
			transaction.commit();
			
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		
		sessionFactory.getCurrentSession().save(yetkiler);
		
	}

	@Override
	public void deleteYetki(Long userId, List<Long> id) {
		
		Session session=sessionFactory.getCurrentSession();
		Transaction transaction=null;
		List<SeraCenvYetkiler> yetkiler=sessionFactory.getCurrentSession().createCriteria(SeraCenvYetkiler.class).
		add(Restrictions.eq("userId", userId)).add(Restrictions.in("baslikId", id)).list();
		
		try {
			
			transaction=session.beginTransaction();
			//burada ne lazım ise onları bulup kaydet	
			session.delete(yetkiler);
			session.flush();
			transaction.commit();
			
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean controlYetkiVarMi(Long userId, Long id) {
		int sonuc;
		sonuc=(Integer)sessionFactory.getCurrentSession().createCriteria(SeraCenvYetkiler.class).
		add(Restrictions.eq("userId", userId)).add(Restrictions.eq("baslikId", id)).setProjection(Projections.rowCount()).uniqueResult();
		
		if (sonuc>0){
			return true;
		}else{
		    return false;
		}
	}

	@Override
	public void deleteYetki(Long userId, Long id) {
		
		Session session=sessionFactory.getCurrentSession();
		Transaction transaction=null;
		
		try {
			transaction=session.beginTransaction();
			SeraCenvYetkiler yetki=(SeraCenvYetkiler)session.createCriteria(SeraCenvYetkiler.class).
			add(Restrictions.eq("userId", userId)).add(Restrictions.eq("baslikId", id)).list().get(0);
			session.delete(yetki);
			session.flush();
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
			e.printStackTrace();
		}
			
		
	}

	@Override
	public void saveYetki(Long userId, Long id,Long loggedId) {
		
		Session session=sessionFactory.getCurrentSession();
		Transaction transaction=null;
		SeraCenvYetkiler yetki=new SeraCenvYetkiler();;
		
		try {
			transaction=session.beginTransaction();
			//burada ne lazım ise onları bulup kaydet
			yetki.setUserId(userId);
			SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy hh:MM");
			yetki.setCreateDate(format.getCalendar().getInstance().getTime());
			yetki.setCreatedBy(loggedId);
			yetki.setBaslikId(id);
			
			session.save(yetki);
			session.flush();
			transaction.commit();
			
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		
	}

	

}
