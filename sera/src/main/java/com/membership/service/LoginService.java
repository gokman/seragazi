package com.membership.service;

import java.util.List;


import com.membership.model.User;

public interface LoginService {

		public void addUser(User user);
		
		public void updateMembershipStatus(Long userID); 
		
		public void activateMembershipStatus(Long userID,String username);
		
		public List<User> listUsers();
		
		public List<User> listUsers(String type);
		
		public List<User> getByUsername(String username);
		
		public User getWaitingMember(User user);
		
		public User getUser(User user) ;

		public List<User> listActiveCustomers();
		
		public User loadUserObject(String username);
		
		public boolean isUserExist(String username);
		
		public boolean isEmailExist(String email);
		
		public User getLoggedInUser();
		
	}