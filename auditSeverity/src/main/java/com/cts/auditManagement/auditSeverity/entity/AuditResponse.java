package com.cts.auditManagement.auditSeverity.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class AuditResponse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int auditId;

	@Column
	private String projectExecutionStatus;

	@Column
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
