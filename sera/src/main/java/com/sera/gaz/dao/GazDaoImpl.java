package com.sera.gaz.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sera.gaz.model.Gaz;

@Repository("gazdao")
public class GazDaoImpl implements GazDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	
	@Override
	public void saveGaz(Gaz gaz) {
		sessionFactory.getCurrentSession().save(gaz);
		
	}

	
	@Override
	public void updateGaz(Gaz gaz) {
		Gaz gaztemp=(Gaz)sessionFactory.getCurrentSession().load(Gaz.class, gaz.getId());
		gaztemp.setLastUpdateDate(gaz.getLastUpdateDate());
		gaztemp.setLastUpdatedBy(gaz.getLastUpdatedBy());
		gaztemp.setName(gaz.getName());
		sessionFactory.getCurrentSession().update(gaztemp);
		
	}

	@Override
	public Gaz getGaz(long id) {
		
		return (Gaz)sessionFactory.getCurrentSession().
		createCriteria(Gaz.class).add(Restrictions.eq("id", id)).list().get(0);
	}

	
	@Override
	public List<Gaz> listGaz() {
		
		return sessionFactory.getCurrentSession().createCriteria(Gaz.class).list();	
	}
	

	@Override
	public void deleteGaz(Gaz gaz) {
		sessionFactory.getCurrentSession().delete(gaz);		
	}
	
}