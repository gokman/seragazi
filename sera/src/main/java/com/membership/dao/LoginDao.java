package com.membership.dao;

import java.util.List;

import com.membership.model.User;


public interface LoginDao {

    public void saveUser ( User user);
	
	public void updateMembershipStatus (Long userId);
	// To get list of all articles
	public List<User> listUsers();
	
	public List<User> listUsers(User user);
	
	public List<User> listByUsername(String username);
}
