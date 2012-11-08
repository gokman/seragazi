package com.membership.dao;

import java.util.List;

import com.membership.model.User;


public interface LoginDao {

    public void saveUser ( User user);
	
	public void updateMembershipStatus (Long userId);
	// To get list of all articles
	public List<User> listUsers();
	
	public List<User> listUsers(User user);
	
	public List<User> listUsers(String type);
	
	public List<User> listByUsername(String username);
	
	public User loadUserObject(String username);
	
	public boolean isUserExist(String username);
	
	public boolean isEmailExist(String email);
	
	public User getLoggedInUser();
}
