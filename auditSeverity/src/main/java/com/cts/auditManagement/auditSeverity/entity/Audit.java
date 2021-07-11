package com.cts.auditManagement.auditSeverity.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Audit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int auditId;

	@Column
	private String auditType;

	@Column
	private Date auditDate;

	@Column
	private String projectName;

	@Column
	private String projectManagerName;

	@Column
	private String applicationOwnerName;

	@Column
	private String projectExecutionStatus;

	@Column
	private String remedialActionDuration;

	public int getAuditId() {
		return auditId;
	}

	public void setAuditId(int auditId) {
		this.auditId = auditId;
	}

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

	public String getProjectExecutionStatus() {
		return projectExecutionStatus;
	}

	public void setProjectExecutionStatus(String projectExecutionStatus) {
		this.projectExecutionStatus = projectExecutionStatus;
	}

	public String getRemedialActionDuration() {
		return remedialActionDuration;
	}

	public void setRemedialActionDuration(String remedialActionDuration) {
		this.remedialActionDuration = remedialActionDuration;
	}

	@Override
	public String toString() {
		return "Audit [auditId=" + auditId + ", auditType=" + auditType + ", auditDate=" + auditDate + ", projectName="
				+ projectName + ", projectManagerName=" + projectManagerName + ", applicationOwnerName="
				+ applicationOwnerName + ", projectExecutionStatus=" + projectExecutionStatus
				+ ", remedialActionDuration=" + remedialActionDuration + "]";
	}

}
