package com.cts.AuditManagement.AuditBenchMark.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.AuditManagement.AuditBenchMark.Util.BenchmarkConstant;
import com.cts.AuditManagement.AuditBenchMark.service.AuditBenchmarkService;
import com.cts.AuditManagement.AuditBenchMark.service.BenchmarkTokenService;


@RestController
public class AuditBenchmarkController {

	
	
	@Autowired
	private BenchmarkTokenService  benchmarkTokenService;
	
	@Autowired
	private AuditBenchmarkService benchmarkService;
	
	Logger logger = LoggerFactory.getLogger(AuditBenchmarkController.class);
	
	
	/* This method returns List of Benchmark Objects */
	/* @RequestHeader("Authorization") String token */

	@GetMapping(path="/AuditBenchmark")
	public ResponseEntity<?> AuditBenchmark(@RequestHeader("Authorization") String token){
	
		logger.info("Inside Benchmark Controller");
		
		if(benchmarkTokenService.validateToken(token)) {
			try {
				
				return new ResponseEntity<>(benchmarkService.auditBenchMark(), HttpStatus.OK);
				
			}catch(Exception e) {
				
				logger.error("Exception in Benchmark Controller "+e);
				return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(BenchmarkConstant.BENCHMARK_CONTROLLER_ERROR);
			}
		}else {
			
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(BenchmarkConstant.BENCHMARK_VALIDATION_ERROR);
		}
		
	}
	
}



/*
 * try {
 * 
 * if(benchmarkTokenService.validateToken(token)) return new
 * ResponseEntity<>(benchmarkService.auditBenchMark(), HttpStatus.OK);
 * 
 * } catch (Exception e) { logger.error("Exception in Benchmark Controller "+e);
 * return ResponseEntity.status(HttpStatus.FORBIDDEN).body(AuditConstant.
 * AUDIT_CONTROLLER_BENCHMARK_ERROR1); }
 * 
 * return
 * ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(AuditConstant.
 * AUDIT_CONTROLLER_BENCHMARK_ERROR2);
 */
