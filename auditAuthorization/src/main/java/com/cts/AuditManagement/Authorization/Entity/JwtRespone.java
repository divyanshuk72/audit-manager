package com.cts.AuditManagement.Authorization.Entity;

public class JwtRespone {

	String token;

	@Override
	public String toString() {
		return "JwtRespone [token=" + token + "]";
	}

	public JwtRespone() {
		super();
	}

	public JwtRespone(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
