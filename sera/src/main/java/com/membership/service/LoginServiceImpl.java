package com.membership.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.util.constant.ApplicationConstants;
import com.membership.dao.AuthorityDao;
import com.membership.dao.LoginDao;
import com.membership.model.Authority;
import com.membership.model.User;

@Service("loginService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class LoginServiceImpl implements LoginService{

	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	private AuthorityDao authorityDao;

	public LoginServiceImpl() {
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addUser(User user) {
		loginDao.saveUser(user);
		
	}

	public void updateMembershipStatus(Long userID){
		loginDao.updateMembershipStatus(userID);
	}
	
	//burada kullanıcının üyelik durumunu pasiften aktife çekiyoruz aynı zamanda auhorities tablosuna
	//da kaydını yapıyoruz ve rol veriyoruz.
	public void activateMembershipStatus(Long userID,String username){
		loginDao.updateMembershipStatus(userID);
		//authorities tablosunu doldur. ilk aşamada kullanıcı sistemde bilinmeyen durumunda olacak.
		//Daha sonra yönetici(administrator) onu idareci(manager) veya kullanıcı(user) yapabilecek.
		Authority aut=new Authority();
		aut.setAuthority(ApplicationConstants.AUTHORITIES_STATUS_CODES.USER);
		aut.setUsername(username);
		authorityDao.saveAuthority(aut);
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
	
	public User loadUserObject(String username){
		return loginDao.loadUserObject(username);
	}

	@Override
	public boolean isUserExist(String username) {
		// TODO Auto-generated method stub
		return  loginDao.isUserExist(username);
	}

	@Override
	public boolean isEmailExist(String email) {
		// TODO Auto-generated method stub
		return  loginDao.isEmailExist(email);
	}

	@Override
	public List<User> listUsers(String type) {
		// TODO Auto-generated method stub
		return loginDao.listUsers(type);
	}


}
