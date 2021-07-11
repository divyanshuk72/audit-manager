package com.cts.AuditManagementPortal.Model;

public class UserDetails {

	@Override
	public String toString() {
		return "AuditRequest [pname=" + pname + ", pmname=" + pmname + ", aoname=" + aoname + ", auditType=" + auditType
				+ "]";
	}

	public UserDetails() {
		super();
	}

	private String pname;
	private String pmname;
	private String aoname;
	private String auditType;

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPmname() {
		return pmname;
	}

	public void setPmname(String pmname) {
		this.pmname = pmname;
	}

	public String getAoname() {
		return aoname;
	}

	public void setAoname(String aoname) {
		this.aoname = aoname;
	}

	public String getAuditType() {
		return auditType;
	}

	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}
}
