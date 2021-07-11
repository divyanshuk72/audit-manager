package com.cts.AuditManagement.Audit;


import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.cts.AuditManagement.AuditChecklist.AuditChecklistApplication;
import com.cts.AuditManagement.AuditChecklist.Service.AuditChecklistService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = AuditChecklistApplication.class)

@AutoConfigureMockMvc
class AuditCheckListControllerTest {

	
	@Autowired
 	private MockMvc mockMvc;
    
 
    @MockBean
	private AuditChecklistService auditChecklistServiceMock;
    
    
	@Test
	public void testAuditCheckListQuestions() throws Exception {
    	
    		when(auditChecklistServiceMock.getQuestions("Internal")).thenReturn(getAuditCheckList());
    		mockMvc.perform(get("/AuditChecklist/Internal")
    				.contentType(MediaType.APPLICATION_JSON))
             .andExpect(jsonPath("$.size()", is(2)))
             .andDo(print());
    
    }
	
	private List<String> getAuditCheckList() {
		List<String> auditChecklist = new ArrayList<>();
		auditChecklist.add("Question 1");
		auditChecklist.add("Question 2");
		return auditChecklist;
	}

}