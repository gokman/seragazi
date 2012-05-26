package com.lookup.dao;

import java.util.List;


import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.lookup.model.LookupMst;

@Repository("lookupMstDao")
public class LookupMstDaoImpl implements LookupMstDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveLookupMst(LookupMst lookupMst) {
		
		sessionFactory.getCurrentSession().save(lookupMst);
	}
	
	@Override
	public List<LookupMst> searchLookupMst(String baslik){
		
	
        // Query qBuilder = sessionFactory.getCurrentSession().createCriteria(LookupMst.class).add(Restrictions.like("baslik", baslik))
         
         //createQuery("Select baslik from LookupMst l" +
         	//	"where l.baslik like '%"+baslik+"%'");
               
         List<LookupMst> lookList=(List<LookupMst>) sessionFactory.getCurrentSession().createCriteria(LookupMst.class).
         add(Restrictions.like("baslik", "%"+baslik+"%")).list();
         
         return lookList;

	}

	@Override
	public List<LookupMst> listLookupMst() {
		List<LookupMst> list=(List<LookupMst>)sessionFactory.getCurrentSession().createQuery("from LookupMst").list();
		
		
		return list;
	}

}
