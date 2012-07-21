package com.membership.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.util.constant.ApplicationConstants;
import com.membership.dao.LoginDao;
import com.membership.model.User;

@Service("loginService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class LoginServiceImpl implements LoginService{

	@Autowired
	private LoginDao loginDao;

	public LoginServiceImpl() {
	}



	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addUser(User user) {
		loginDao.saveUser(user);
	}

	public void updateMembershipStatus(Long userID){
		loginDao.updateMembershipStatus(userID);
	}
	
	public List<User> listUsers() {
		return loginDao.listUsers();
	}



	public List<User> getByUsername(String username) {
		return loginDao.listByUsername(username);
	}
	
	@Override
	public User getWaitingMember(User user) {
		List <User>waitingUsers = loginDao.listUsers(user);
		
		if(waitingUsers.size() > 0)
			return (User)waitingUsers.get(0);
		else
			return null;
	}
	
	public User getUser(User user) {
		List <User>users = loginDao.listUsers(user);
		
		if(users.size() > 0)
			return (User)users.get(0);
		else
			return null;
	}
	


	public List<User> listActiveCustomers() {
		User user = new User();
//		user.setMembershipStatus(ApplicationConstants.MEMBERSHIP_STATUS_CODES.ACTIVE);
		return loginDao.listUsers(user);
	}




}
