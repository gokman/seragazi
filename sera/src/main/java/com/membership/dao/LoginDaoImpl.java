package com.membership.dao;

import java.util.List;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
	public List<User> listUsers(String type) {
		return (List<User>) sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("membershipStatus", type)).list();

	}
	
	@Override
	public User getLoggedInUser(){
		return listByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get(0);
	}

	@Override
	public List<User> listByUsername(String username) {
		User user = new User();
		user.setUsername(username);
		user.setMembershipStatus(ApplicationConstants.MEMBERSHIP_STATUS_CODES.ACTIVE);
		List<User> userList = sessionFactory.getCurrentSession().createCriteria(User.class).add(Example.create(user)).list();
		
		return  userList;
	}

	@Override
	public User loadUserObject(String username) {
		
		return (User)sessionFactory.getCurrentSession().createCriteria(User.class).
		add(Restrictions.eq("username", username)).list().get(0);
		
	}

	@Override
	public boolean isUserExist(String username) {
		int sonuc;
		sonuc=(Integer)sessionFactory.getCurrentSession().createCriteria(User.class)
		.add(Restrictions.eq("username", username))
		.setProjection(Projections.rowCount()).uniqueResult();;
		if(sonuc>0){
			return true;
		}else
			return false;
	}
	
	@Override 
	public boolean isEmailExist(String email) {
		int sonuc;
		sonuc=(Integer)sessionFactory.getCurrentSession().createCriteria(User.class)
		.add(Restrictions.eq("email", email))
		.setProjection(Projections.rowCount()).uniqueResult();;
		if(sonuc>0){
			return true;
		}else
			return false;
	}

}
