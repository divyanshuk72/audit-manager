package com.cts.AuditManagement.AuditChecklist.Entity;

public class AuthResponse {

	private String uid;

	public AuthResponse() {
		super();
	}

	public AuthResponse(String uid, boolean isValid) {
		super();
		this.uid = uid;
		this.isValid = isValid;
	}

	@Override
	public String toString() {
		return "AuthResponse [uid=" + uid + ", isValid=" + isValid + "]";
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	/**
	 * This is a private field which is used to represent whethet the token is valid
	 * or not. The data type is boolean.
	 */
	private boolean isValid;
	
}
