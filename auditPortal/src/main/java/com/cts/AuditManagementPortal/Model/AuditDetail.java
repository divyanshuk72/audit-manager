package com.cts.AuditManagementPortal.Model;

import java.util.Date;

import org.springframework.stereotype.Component;

//@Getter
//@Setter

@Component
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

	
	  public Date getAuditDate() { return auditDate; }
	  
	  public void setAuditDate(Date auditDate) { this.auditDate = auditDate; }
	 
	public int getAuditQuestions() {
		return auditQuestions;
	}

	public void setAuditQuestions(int auditQuestions) {
		this.auditQuestions = auditQuestions;
	}

	
	  @Override public String toString() { return "AuditDetail [auditType=" +
	  auditType + ", auditDate=" + auditDate + ", auditQuestions=" + auditQuestions
	  + "]"; }
	 

}
