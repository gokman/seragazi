package com.sera.birim.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sera.birim.model.Birim;
import com.sera.cevreselfaktor.model.SeraCevreselFaktor;

@Repository("birimdao")
public class BirimDaoImpl implements BirimDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	
	@Override
	public void saveBirim(Birim birim) {
		sessionFactory.getCurrentSession().save(birim);
		
	}

	
	@Override
	public void updateBirim(Birim birim) {
		Birim birimtemp=(Birim)sessionFactory.getCurrentSession().load(Birim.class, birim.getId());
		birimtemp.setLastUpdateDate(birim.getLastUpdateDate());
		birimtemp.setLastUpdatedBy(birim.getLastUpdatedBy());
		birimtemp.setDeger(birim.getDeger());
		sessionFactory.getCurrentSession().update(birimtemp);
		
	}

	@Override
	public Birim getBirim(long id) {
		
		return (Birim)sessionFactory.getCurrentSession().
		createCriteria(Birim.class).add(Restrictions.eq("id", id)).list().get(0);
	}

	
	@Override
	public List<Birim> listBirim() {
		
		return sessionFactory.getCurrentSession().createCriteria(Birim.class).list();	
	}
	

	@Override
	public void deleteBirim(Birim birim) {
		sessionFactory.getCurrentSession().delete(birim);		
	}
	
}