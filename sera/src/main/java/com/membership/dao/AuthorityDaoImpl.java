package com.membership.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.membership.model.Authority;
import com.membership.model.User;
import com.util.constant.ApplicationConstants;

@Repository("authorityDao")
public class AuthorityDaoImpl implements AuthorityDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveAuthority(Authority authority) {
		sessionFactory.getCurrentSession().save(authority);

	}

	@Override
	public void updateAuthority(String username,String author) {
		
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
		transaction = session.beginTransaction();
		Authority authority = (Authority) session.get(Authority.class, username);
		authority.setAuthority(author);
		session.update(authority);
		session.flush();
		transaction.commit();
		} catch (HibernateException e) {
		transaction.rollback();
		e.printStackTrace();
		} finally {
			
		}

	}

}
