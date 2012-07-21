package com.membership.dao;

import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.membership.model.User;
import com.util.constant.ApplicationConstants;

@Repository("loginDao")
public class LoginDaoImpl implements LoginDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void saveUser(User user) {
		sessionFactory.getCurrentSession().save(user);

	}

	@Override
	public void updateMembershipStatus(Long userId) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
		transaction = session.beginTransaction();
		User user = (User) session.get(User.class, userId);
		user.setMembershipStatus(ApplicationConstants.MEMBERSHIP_STATUS_CODES.ACTIVE);
		session.update(user);
		session.flush();
		transaction.commit();
		} catch (HibernateException e) {
		transaction.rollback();
		e.printStackTrace();
		} finally {
			
		}

	}

	// To get list of all articles
	@SuppressWarnings("unchecked")
	public List<User> listUsers() {
		// TODO Auto-generated method stub
		return  (List<User>) sessionFactory.getCurrentSession().createCriteria(User.class).list();
	}

	@Override
	public List<User> listUsers(User user) {
		return (List<User>) sessionFactory.getCurrentSession().createCriteria(User.class).add(Example.create(user)).list();

	}

	@Override
	public List<User> listByUsername(String username) {
		User user = new User();
		user.setUsername(username);
		user.setMembershipStatus(ApplicationConstants.MEMBERSHIP_STATUS_CODES.ACTIVE);
		List<User> userList = sessionFactory.getCurrentSession().createCriteria(User.class).add(Example.create(user)).list();
		
		return  userList;
	}

}
