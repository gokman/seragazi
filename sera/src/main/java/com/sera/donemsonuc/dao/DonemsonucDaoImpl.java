package com.sera.donemsonuc.dao;

import java.util.List;


import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.membership.model.User;
import com.sera.model.SeraCenvDegerListe;
import com.sera.model.SeraCenvDonemSonuc;
import com.sera.model.SeraCenvGiris;
import com.sera.util.DonemSonucRaporParams;
import com.sera.util.DonemSonucRep;
import com.sera.util.SeraCenvSabitlerRep;
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


	@Override
	public void deleteDonemSonuc(String donem) {
		
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
		transaction = session.beginTransaction();
		List<SeraCenvDonemSonuc> donemSonucList=
			session.createCriteria(SeraCenvDonemSonuc.class).add(Restrictions.eq("donem", donem)).list();
		
		for(int i=0;i<donemSonucList.size();i++){
			session.delete(donemSonucList.get(i));
		}
		
		session.flush();
		transaction.commit();
		
		} catch (HibernateException e) {
		transaction.rollback();
		e.printStackTrace();
		} finally {
			
		}
		
	}
	
	@Override
	public JRDataSource getDonemSonucRep(DonemSonucRaporParams params) {
		List<DonemSonucRep> donemsonuclist;
		
		if (params.getDonembasla().equals("")){
			params.setDonembasla(null);
		}
		if (params.getDonembitis().equals("")){
			params.setDonembitis(null);
		}
		
		Query query=sessionFactory.getCurrentSession().createSQLQuery(
				"select a.deger as deger,a.donem as donem,b.baslik as baslik,c.baslik as parentBaslik,a.sonuc as sonuc " +
				"from sera.sera_cenv_donem_sonuc a,sera.sera_cenv_deger_liste b left outer join "+
				"sera.sera_cenv_deger_liste c on "+
				"b.parent_id=c.id "+
				"where a.baslik_id=b.id and b.baslik like '%"+params.getBaslik()+"%' "+
				"and a.donem >= IFNULL('"+params.getDonembasla()+"','2000-01') "+
				"and a.donem <= IFNULL('"+params.getDonembitis()+"','2040-01')")
				.addScalar("deger",Hibernate.STRING)
				.addScalar("donem",Hibernate.STRING)
				.addScalar("baslik",Hibernate.STRING)
				.addScalar("parentBaslik",Hibernate.STRING)
				.addScalar("sonuc",Hibernate.DOUBLE)
				;
		
		
		
	
		donemsonuclist=(List<DonemSonucRep>)query.setResultTransformer( Transformers.aliasToBean(DonemSonucRep.class)).list();

		return new JRBeanCollectionDataSource(donemsonuclist);
	}



}
