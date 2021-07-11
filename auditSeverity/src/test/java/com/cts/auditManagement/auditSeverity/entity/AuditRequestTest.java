package com.cts.auditManagement.auditSeverity.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AuditRequestTest {

private AuditRequest auditRequest;

	Logger logger = LoggerFactory.getLogger(AuditRequestTest.class);
	
	@Test
	public void testGetSetProjectName() {
		auditRequest.setProjectName("ProjectName");
		assertEquals("ProjectName", auditRequest.getProjectName());
		logger.debug("AuditRequest.projectName" + auditRequest.getProjectName());
	}
	
	@Test
	public void testGetSetProjectManagerName() {
		auditRequest.setProjectManagerName("ManagerName");
		assertEquals("ManagerName", auditRequest.getProjectManagerName());
		logger.debug("AuditRequest.projectManagerName" + auditRequest.getProjectManagerName());
	}
	
	@Test
	public void testGetSetAuditDetails() {
		AuditDetail details = new AuditDetail();
		auditRequest.setAuditDetail(details);
		assertEquals(details, auditRequest.getAuditDetail());
		logger.debug("AuditRequest.auditdetail" + auditRequest.getAuditDetail());
	}

}
