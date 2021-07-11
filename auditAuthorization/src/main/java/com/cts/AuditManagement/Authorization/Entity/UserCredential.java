package com.cts.AuditManagement.Authorization.Entity;

public class UserCredential {

	String password;
	public UserCredential() {
		super();
	}
	String username;
	
	
	public UserCredential(String password, String username) {
		super();
		this.password = password;
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	//String password;
}
