package com.cts.auditManagement.auditSeverity.entity;

public class AuditRequest {
	
	
	private String projectName;
	private String projectManagerName;
	private String applicationOwnerName;
	private AuditDetail auditDetail;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectManagerName() {
		return projectManagerName;
	}

	public void setProjectManagerName(String projectManagerName) {
		this.projectManagerName = projectManagerName;
	}

	public String getApplicationOwnerName() {
		return applicationOwnerName;
	}

	public void setApplicationOwnerName(String applicationOwnerName) {
		this.applicationOwnerName = applicationOwnerName;
	}

	public AuditDetail getAuditDetail() {
		return auditDetail;
	}

	public void setAuditDetail(AuditDetail auditDetail) {
		this.auditDetail = auditDetail;
	}

	@Override
	public String toString() {
		return "AuditRequest [projectName=" + projectName + ", projectManagerName=" + projectManagerName
				+ ", applicationOwnerName=" + applicationOwnerName + ", auditDetail=" + auditDetail + "]";
	}

}
