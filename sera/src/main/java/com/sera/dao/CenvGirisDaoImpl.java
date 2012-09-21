package com.sera.dao;

import java.math.BigInteger;
import java.util.Iterator;


import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lookup.model.LookupMst;
import com.sera.model.SeraCenvDegerListe;
import com.sera.model.SeraCenvGiris;
import com.sera.model.SeraCenvSabitler;
import com.sera.util.object.DegerGenel;

@Repository("cenvgirisdao")
public class CenvGirisDaoImpl implements CenvGirisDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveCenvGiris(SeraCenvGiris cenvgiris) {
        sessionFactory.getCurrentSession().save(cenvgiris);

	}
	
	@Override
	public List<SeraCenvGiris> listCenvGiris() {
		List<SeraCenvGiris> list=(List<SeraCenvGiris>)sessionFactory.getCurrentSession().createQuery("from SeraCenvGiris").list();
	
		return list;
	}
	
	@Override
	public List<SeraCenvGiris> listCenvGiris(String donem) {
		List<SeraCenvGiris> list=(List<SeraCenvGiris>)sessionFactory.getCurrentSession().
		createQuery("from SeraCenvGiris where tarih= :tarih").setParameter("tarih", donem).list();
	
		return list;
	}
	
	@Override
	public SeraCenvGiris getCenvGiris(long id){
     
		SeraCenvGiris seracenv=(SeraCenvGiris)sessionFactory.getCurrentSession().
		createCriteria(SeraCenvGiris.class).add(Restrictions.eq("id", id)).list().get(0);
         
         return seracenv;

	}

	@Override
	public void updateCenvGiris(SeraCenvGiris cenvgiris) {
		sessionFactory.getCurrentSession().update(cenvgiris);
		
	}

	@Override
	public DegerGenel getGenelDeger(Long id) {
		DegerGenel geneldeger=new DegerGenel();
		Query query=sessionFactory.getCurrentSession().createQuery(
				"select a.id as id, a.baslik as baslik, a.parentId as parent_id, b.baslik as parent_baslik from SeraCenvDegerListe a," +
				"SeraCenvDegerListe b"+
                " where a.parentId=b.id"+
                " and a.id="+id);
		
	
		geneldeger=(DegerGenel)query.setResultTransformer( Transformers.aliasToBean(DegerGenel.class)).list().get(0);
		
		
//		Iterator ite=query.list().iterator();
//		while(ite.hasNext()){
//			Object[] results=(Object[])ite.next();
//			geneldeger.setId( ((BigInteger)results[0]).longValue() );
//			geneldeger.setParent_id((Long)results[2]);
//			geneldeger.setBaslik((String)results[1]);
//			geneldeger.setParent_baslik((String)results[3]);
//		}
        
		return geneldeger;
	}
	
	@Override
	public DegerGenel getGenelDeger(Long id,String tarih) {
		DegerGenel geneldeger=new DegerGenel();
		Query query=sessionFactory.getCurrentSession().createQuery(
				"select a.id as id, a.baslik as baslik, a.parentId as parent_id, b.baslik as parent_baslik from SeraCenvDegerListe a," +
				"SeraCenvDegerListe b"+
                " where a.parentId=b.id"+
                " and a.id="+id+" and a.tarih='"+tarih+"'");
		
	
		geneldeger=(DegerGenel)query.setResultTransformer( Transformers.aliasToBean(DegerGenel.class)).list().get(0);
		
		
//		Iterator ite=query.list().iterator();
//		while(ite.hasNext()){
//			Object[] results=(Object[])ite.next();
//			geneldeger.setId( ((BigInteger)results[0]).longValue() );
//			geneldeger.setParent_id((Long)results[2]);
//			geneldeger.setBaslik((String)results[1]);
//			geneldeger.setParent_baslik((String)results[3]);
//		}
        
		return geneldeger;
	}

	@Override
	public SeraCenvDegerListe getKok() {
		SeraCenvDegerListe kok=(SeraCenvDegerListe)sessionFactory.getCurrentSession().createQuery("from SeraCenvDegerListe where tip1='Kok'").list().get(0);
		return kok;
	}

	@Override
	public SeraCenvGiris getGirisKayit(String tarih, Long id) {
		try{
		SeraCenvGiris giriskayit=(SeraCenvGiris)sessionFactory.
		getCurrentSession().createCriteria(SeraCenvGiris.class).
		add(Restrictions.eq("baslikId", id)).add(Restrictions.eq("tarih", tarih)).
		list().get(0);
		return giriskayit;
		}catch(Exception e){
		return null;
		}
		
		
	}

	@Override
	public int getGirisQuantity(String donem) {
		return (Integer)sessionFactory.getCurrentSession().createCriteria(SeraCenvGiris.class).
		add(Restrictions.eq("tarih", donem)).
		setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	public JRDataSource getCenvGirisReport() {
		List<SeraCenvGiris> listGiris=sessionFactory.getCurrentSession().
		createCriteria(SeraCenvGiris.class).list();
			
		return new JRBeanCollectionDataSource(listGiris);
		
	}

	@Override
	public JRDataSource listCenvGiris(String baslangicDonem,
			String bitisDonem) {
		List<SeraCenvGiris> listGiris=sessionFactory.getCurrentSession().
		createCriteria(SeraCenvGiris.class).add(Restrictions.gt("tarih", baslangicDonem)).
		add(Restrictions.lt("tarih", bitisDonem)).list();
		return new JRBeanCollectionDataSource(listGiris);
	}

}