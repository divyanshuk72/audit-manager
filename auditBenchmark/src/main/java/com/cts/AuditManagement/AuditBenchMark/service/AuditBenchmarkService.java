package com.cts.AuditManagement.AuditBenchMark.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cts.AuditManagement.AuditBenchMark.controller.AuditBenchmarkController;
import com.cts.AuditManagement.AuditBenchMark.entity.Benchmark;

/*Service method returns List of Benchmark 
objects to Controller*/

@Service
public class AuditBenchmarkService {

	Logger logger = LoggerFactory.getLogger(AuditBenchmarkService.class);
	
	public List<Benchmark> auditBenchMark() {
		logger.info("START");
		logger.info("Inside Benchmark Service");
		List<Benchmark> auditObjList = new ArrayList<>();

		Benchmark auditInternal = new Benchmark();
		Benchmark auditSox = new Benchmark();

		auditInternal.setAuditType("Internal");
		auditInternal.setBenchmarkNoAnswers(3);
		auditObjList.add(auditInternal);

		auditSox.setAuditType("SOX");
		auditSox.setBenchmarkNoAnswers(1);
		auditObjList.add(auditSox);
        logger.debug("{Benchmark Object List} ",auditObjList);
		return auditObjList;
	}
}
