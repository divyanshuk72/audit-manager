package com.cts.auditManagement.auditSeverity.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cts.auditManagement.auditSeverity.entity.AuditRequest;
import com.cts.auditManagement.auditSeverity.entity.AuditResponse;

public interface AuditSeverityController {

	// for generating AuditRespose and sending to AuditManagement Service

	@PostMapping("/ProjectExecutionStatus")
	public ResponseEntity<?> auditResponse(@RequestHeader(name = "Authorization",required = true)String token,
			@RequestBody AuditRequest auditRequest) throws Exception;

}
