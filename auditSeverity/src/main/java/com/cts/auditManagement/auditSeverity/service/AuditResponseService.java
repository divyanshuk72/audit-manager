package com.cts.auditManagement.auditSeverity.service;

import com.cts.auditManagement.auditSeverity.entity.AuditRequest;
import com.cts.auditManagement.auditSeverity.entity.AuditResponse;

public interface AuditResponseService {
	
	public AuditResponse generateAuditResponse(AuditRequest auditRequest,String token) throws Exception;
	public void saveAudit(AuditRequest auditRequest, AuditResponse auditResponse);
}
