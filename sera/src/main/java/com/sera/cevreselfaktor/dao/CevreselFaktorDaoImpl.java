package com.sera.cevreselfaktor.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sera.cevreselfaktor.model.SeraCevreselFaktor;

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
		
		sessionFactory.getCurrentSession().update(cevreselFaktor);
		
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
	
}