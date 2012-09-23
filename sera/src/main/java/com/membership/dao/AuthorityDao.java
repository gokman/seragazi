package com.membership.dao;

import com.membership.model.Authority;

public interface AuthorityDao {

	 public void saveAuthority ( Authority authority);	
	 public void updateAuthority (String username,String author);
}
