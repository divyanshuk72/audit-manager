package com.cts.auditManagement.auditSeverity.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.cts.auditManagement.auditSeverity.entity.AuditRequest;
import com.cts.auditManagement.auditSeverity.entity.AuditResponse;
import com.cts.auditManagement.auditSeverity.service.AuditResponseService;
import com.cts.auditManagement.auditSeverity.service.SeverityTokenService;
import com.cts.auditManagement.auditSeverity.util.AuditSeverityConstant;
import com.cts.auditManagement.auditSeverity.exception.RequestException;

@RestController
public class AuditSeverityControllerImpl implements AuditSeverityController {

	@Autowired
	SeverityTokenService tokenService;
	
	@Autowired
	private AuditResponseService auditResponseService;

	Logger logger = LoggerFactory.getLogger(AuditSeverityControllerImpl.class);

	// for generating AuditResponse and sending to AuditManagement Service

	@Override
	public ResponseEntity<?> auditResponse(String token, AuditRequest auditRequest) throws Exception {

		ResponseEntity<AuditResponse> responseEntity;

		if (tokenService.validateToken(token)) {
			try {
				AuditResponse auditResponse = auditResponseService.generateAuditResponse(auditRequest,token);
				logger.info("AuditResponse found in AuditSeverity Controller");
				return new ResponseEntity<>(auditResponse, HttpStatus.OK);
			} catch (RequestException e) {
				logger.error("RequestException occured in AuditSeverity Controller");
				return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
			} catch (Exception e) {
				logger.error("exception occured in AuditSeverity Controller");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(AuditSeverityConstant.AUDIT_SEVEARITY_CONTROLLER_ERROR);
			}
		} else {

			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(AuditSeverityConstant.AUDIT_SEVERITY_VALIDATION_ERROR);
		}

}
}
