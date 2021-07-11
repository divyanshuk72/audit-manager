package com.cts.AuditManagement.AuditChecklist.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//import org.springframework.boot.autoconfigure.domain.EntityScan;




@Entity
@Table(name="audit_question")
public class CheckListEntity {

	

	
	public CheckListEntity(int id, String auditType, String auditQuestions) {
		super();
		this.id = id;
		this.auditType = auditType;
		this.auditQuestions = auditQuestions;
	}

	@Column(name="Id")
	@Id
	private int id;
	
	public CheckListEntity() {
		super();
	}

	@Column(name="Question_type")
	private String auditType;
	
	@Column(name="Question")
	private String auditQuestions;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuditType() {
		return auditType;
	}

	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}

	public String getAuditQuestions() {
		return auditQuestions;
	}

	public void setAuditQuestions(String auditQuestions) {
		this.auditQuestions = auditQuestions;
	}
}
