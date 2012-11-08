package com.sera.cevreselfaktor.dao;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sera.birim.model.Birim;
import com.sera.cevreselfaktor.model.SeraCevreselFaktor;
import com.sera.util.CevreselFaktorRep;
import com.sera.util.DegerListeRep;

@Repository("cevreselfaktordao")
public class CevreselFaktorDaoImpl implements CevreselFaktorDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	

	
	@Override
	public void saveCevreselFaktor(SeraCevreselFaktor cevreselFaktor) {
		sessionFactory.getCurrentSession().save(cevreselFaktor);
		
	}

	
	@Override
	public void updateCevreselFaktor(SeraCevreselFaktor cevreselFaktor) {
		SeraCevreselFaktor faktortemp=(SeraCevreselFaktor)sessionFactory.getCurrentSession().load(SeraCevreselFaktor.class, cevreselFaktor.getId());
		faktortemp.setLastUpdateDate(cevreselFaktor.getLastUpdateDate());
		faktortemp.setLastUpdatedBy(cevreselFaktor.getLastUpdatedBy());
		faktortemp.setDeger(cevreselFaktor.getDeger());
		faktortemp.setAciklama(cevreselFaktor.getAciklama());
		sessionFactory.getCurrentSession().update(faktortemp);
		
	}

	@Override
	public SeraCevreselFaktor getCevreselFaktor(long id) {
		
		return (SeraCevreselFaktor)sessionFactory.getCurrentSession().
		createCriteria(SeraCevreselFaktor.class).add(Restrictions.eq("id", id)).list().get(0);
	}

	
	@Override
	public List<SeraCevreselFaktor> listCevreselFaktor() {
		List<SeraCevreselFaktor> faktorListe;
		
		faktorListe=sessionFactory.getCurrentSession().createCriteria(SeraCevreselFaktor.class).list();
		return faktorListe;
	}
	

	@Override
	public void deleteCevreselFaktor(SeraCevreselFaktor cevreselFaktor) {
		sessionFactory.getCurrentSession().delete(cevreselFaktor);		
	}
	
	@Override
	public JRDataSource getCevreselFaktorReport() {
		@SuppressWarnings("unchecked")
		List<CevreselFaktorRep> listFaktor=new ArrayList<CevreselFaktorRep>();
		Query query=sessionFactory.getCurrentSession().createQuery("select a.aciklama as aciklama,a.deger as deger,a.createDate as createDate,b.username as createdBy," +
				"c.username as lastUpdatedBy,a.lastUpdateDate as lastUpdateDate from SeraCevreselFaktor a,User b,User c "+
                "where a.createdBy=b.userId "+
                "and a.lastUpdatedBy=c.userId");
		listFaktor=query.setResultTransformer(Transformers.aliasToBean(CevreselFaktorRep.class)).list();	
		return new JRBeanCollectionDataSource(listFaktor);
		
	}
	
	
}