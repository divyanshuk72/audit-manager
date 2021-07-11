package com.cts.AuditManagement.AuditChecklist.Controller;

import java.util.*;

import javax.validation.constraints.NotBlank;
import com.cts.AuditManagement.AuditChecklist.Exception.CheckListException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.AuditManagement.AuditChecklist.Service.AuditChecklistService;
import com.cts.AuditManagement.AuditChecklist.Service.AuditChecklistTokenService;
import com.cts.AuditManagement.AuditChecklist.Util.AuditChecklistConstant;

/*This method returns List of Questions 
based on user selection of Audit Type*/

@RestController
@Validated
public class AuditCheckListController {
	
	@Autowired
	AuditChecklistTokenService tokenService;

	@Autowired
	AuditChecklistService checkListService;
	
	Logger logger = LoggerFactory.getLogger(AuditChecklistService.class);
	
	@GetMapping(path="/AuditChecklist/{AuditType}")
	public ResponseEntity<?> AuditCheckListQuestions(@RequestHeader("Authorization") String token,
			@PathVariable("AuditType") @NotBlank String AuditType){
		
		logger.info("START");
		ResponseEntity<?> responseEntity;
		List<String> questionList = new ArrayList<>();
		if (tokenService.validateToken(token)) {

			try {

				questionList = checkListService.getQuestions(AuditType);

			} catch (CheckListException e) {

				logger.error("Error in CheckList Controller "+e);
				responseEntity = new ResponseEntity<String>(AuditChecklistConstant.AUDIT_CHECKLIST_CONTROLLER_ERROR,
						HttpStatus.INTERNAL_SERVER_ERROR);

			}

			responseEntity = new ResponseEntity<List<String>>(questionList, HttpStatus.OK);
			return responseEntity;
		} else {

			responseEntity = new ResponseEntity<String>(AuditChecklistConstant.AUDIT_CHECKLIST_VALIDATION_ERROR, HttpStatus.FORBIDDEN);
			return responseEntity;
		}
		
}

	
}







/*
 * try {
 * 
 * if(tokenService.validateToken(token)) return new
 * ResponseEntity<>(checkListService.getQuestions(AuditType), HttpStatus.OK);
 * 
 * } catch (IndexOutOfBoundsException e) {
 * logger.error("Error in CheckList Controller "+e); return
 * ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
 * 
 * } catch (Exception e) { logger.error("Exception in CheckList Controller "+e);
 * return
 * ResponseEntity.status(HttpStatus.FORBIDDEN).body("Internal server error!!!");
 * }
 * 
 * return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
 * body("Session Time Out!!"); }
 */