package com.membership.model;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "user")
public class User {

	//Kimlik bilgileri
	@NotNull
	@Column(name = "user_id")
    @GeneratedValue
    @Id
	private Long userId;

	@Column(name = "name", nullable = false, length=20)
	private String name;

	@Column(name = "surname", nullable = false, length=20)
	private String surname;
	
	@Column( name = "password",nullable = false)
	private String password ;
	
	@Column(name = "username", nullable = false,length = 20)
	private String username;
	
	
	@Column(name = "membership_status")	//aktif,pasif,beklemede vs
	private String membershipStatus;	

	
	@Column ( name = "email")
	private String email;
	
	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public User() {		
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getMembershipStatus() {
		return membershipStatus;
	}


	public void setMembershipStatus(String membershipStatus) {
		this.membershipStatus = membershipStatus;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}
	
	
}