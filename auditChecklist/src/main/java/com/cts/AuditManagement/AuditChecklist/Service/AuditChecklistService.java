package com.cts.AuditManagement.AuditChecklist.Service;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cts.AuditManagement.AuditChecklist.Entity.CheckListEntity;
import com.cts.AuditManagement.AuditChecklist.Exception.CheckListException;
import com.cts.AuditManagement.AuditChecklist.Repo.CheckListRepo;

/*This method fetches List of Questions from DB
based on AuditType and returns it to controller */

@Service
public class AuditChecklistService{

	@Autowired
	private CheckListRepo checkListRepo;
	
	Logger logger = LoggerFactory.getLogger(AuditChecklistService.class);
	
	public List<String> getQuestions(String auditType) throws CheckListException{

		List<String> AuditQuestionsList=new ArrayList<>();
		
		if(checkListRepo.findByAuditType(auditType).isEmpty()) {
			logger.info("Questions not found");
			throw new CheckListException("Exception In CheckList Service");
		}
		
		else {
			  List<CheckListEntity> checkListEntity = checkListRepo.findByAuditType(auditType);
			  for(CheckListEntity checkList:checkListEntity ) {
				  AuditQuestionsList.add(checkList.getAuditQuestions());
			  }
		}
		logger.info("CheckList Response");
		return AuditQuestionsList;
	}
	
	
	
}
