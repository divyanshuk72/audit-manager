package com.cts.AuditManagementPortal.Model;





public class AuditResponse {

	private int auditId;

	private String projectExecutionStatus;


	private int remedialActionDuration;

	public int getAuditId() {
		return auditId;
	}

	public void setAuditId(int auditId) {
		this.auditId = auditId;
	}

	public String getProjectExecutionStatus() {
		return projectExecutionStatus;
	}

	public void setProjectExecutionStatus(String projectExecutionStatus) {
		this.projectExecutionStatus = projectExecutionStatus;
	}

	public int getRemedialActionDuration() {
		return remedialActionDuration;
	}

	public void setRemedialActionDuration(int remedialActionDuration) {
		this.remedialActionDuration = remedialActionDuration;
	}


	@Override
	public String toString() {
		return "AuditResponse [auditId=" + auditId + ", projectExecutionStatus=" + projectExecutionStatus
				+ ", remedialActionDuration=" + remedialActionDuration + "]";
	}

}
