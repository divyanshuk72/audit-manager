package com.cts.auditManagement.auditSeverity.controller;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cts.auditManagement.auditSeverity.entity.AuditDetail;
import com.cts.auditManagement.auditSeverity.entity.AuditRequest;
import com.cts.auditManagement.auditSeverity.entity.AuditResponse;
import com.cts.auditManagement.auditSeverity.service.AuditResponseService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.when;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AuditSeverityControllerTest {

	@MockBean
	private AuditResponseService service;

	Logger logger = LoggerFactory.getLogger(AuditSeverityControllerTest.class);

	@Autowired
	private MockMvc mockMvc;

	// testing the POST method auditResponse() in the controller

	
	  @Test public void testAuditResponse() throws Exception { AuditRequest
	  auditRequest = getAuditRequest(); 
	  logger.debug("AuditRequest:" +auditRequest); 
	  AuditResponse auditResponse = getAuditResponse();
	  logger.debug("Audit Response:" + auditResponse);
	 when(service.generateAuditResponse(auditRequest,null)).thenReturn(auditResponse);
	  RequestBuilder request =
	  MockMvcRequestBuilders.post("/ProjectExecutionStatus")
	  .accept(MediaType.APPLICATION_JSON).content(asJsonString(auditResponse))
	  .contentType(MediaType.APPLICATION_JSON);
	  
	  MvcResult result =
	  mockMvc.perform(request).andExpect(status().isOk()).andDo(print()).andReturn(
	  );
	  
	  }
	 

	// Converting object into JSON format string
	private String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// Creating instance of AuditDetail
	private AuditDetail getAuditDetail() {
		AuditDetail auditDetail = new AuditDetail();
		auditDetail.setAuditDate(new Date());
		auditDetail.setAuditType("Internal");
		logger.debug("AuditDetail Created:" + auditDetail);
		return auditDetail;
	}

	// Creating instance of AuditRequest
	private AuditRequest getAuditRequest() {
		AuditRequest auditRequest = new AuditRequest();
		auditRequest.setApplicationOwnerName("Kumar");
		auditRequest.setAuditDetail(getAuditDetail());
		auditRequest.setProjectManagerName("Raman");
		auditRequest.setProjectName("ATM");
		logger.debug("AuditRequest Created:" + auditRequest);
		return auditRequest;
	}

	// Creating instance of AuditResponse
	private AuditResponse getAuditResponse() {
		AuditResponse auditResponse = new AuditResponse();
		auditResponse.setAuditId(100);
		auditResponse.setProjectExecutionStatus("GREEN");
		auditResponse.setRemedialActionDuration(0);
		logger.debug("AuditResponse Created" + auditResponse);
		return auditResponse;
	}

}
