package com.cts.auditManagement.auditSeverity.entity;

import java.util.Date;

//@Getter
//@Setter

public class AuditDetail {
	
	private String auditType;
	private Date auditDate;
	private int auditQuestions;

	public String getAuditType() {
		return auditType;
	}

	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public int getAuditQuestions() {
		return auditQuestions;
	}

	public void setAuditQuestions(int auditQuestions) {
		this.auditQuestions = auditQuestions;
	}

	@Override
	public String toString() {
		return "AuditDetail [auditType=" + auditType + ", auditDate=" + auditDate + ", auditQuestions=" + auditQuestions
				+ "]";
	}

}
