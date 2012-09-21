package com.membership.service;

import java.util.List;


import com.membership.model.User;

public interface LoginService {

		public void addUser(User user);
		
		public void updateMembershipStatus(Long userID); 
		
		public List<User> listUsers();
		
		public List<User> getByUsername(String username);
		
		public User getWaitingMember(User user);
		
		public User getUser(User user) ;

		public List<User> listActiveCustomers();
		
		public User loadUserObject(String username);
	}