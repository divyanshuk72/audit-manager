package com.cts.auditManagement.auditSeverity.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cts.auditManagement.auditSeverity.dao.AuditDao;
import com.cts.auditManagement.auditSeverity.dao.AuditResponseDao;
import com.cts.auditManagement.auditSeverity.entity.Audit;
import com.cts.auditManagement.auditSeverity.entity.AuditRequest;
import com.cts.auditManagement.auditSeverity.entity.AuditResponse;
import com.cts.auditManagement.auditSeverity.entity.Benchmark;
import com.cts.auditManagement.auditSeverity.exception.RequestException;
import com.cts.auditManagement.auditSeverity.util.AuditSeverityConstant;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@Service
public class AuditResponseServiceImpl implements AuditResponseService {

	Logger logger = LoggerFactory.getLogger(AuditResponseServiceImpl.class);

	@Autowired
	private AuditResponseDao auditResponseDao;

	@Autowired
	private AuditDao auditDao;

	@Autowired
	private EurekaClient eurekaClient;

	@Autowired
	private RestTemplate restTemplate;

	// Generating Audit Response
	@Override
	public AuditResponse generateAuditResponse(AuditRequest auditRequest,String token) throws RequestException {

		logger.debug("AuditRequest" + auditRequest);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);
		HttpEntity<Boolean> requestEntity = new HttpEntity<>(null, headers);

		if (auditRequest == null)
			throw new RequestException(AuditSeverityConstant.NO_REQUEST_ERROR);

		AuditResponse auditResponse = new AuditResponse();

		List<Benchmark> benchmarks;

		try {
			InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("audit-benchmark-service", false);
			String benchMarkUrl = instanceInfo.getHomePageUrl() + "/AuditBenchmark";
			ResponseEntity<List<Benchmark>> response = restTemplate.exchange(benchMarkUrl, HttpMethod.GET,requestEntity,
					new ParameterizedTypeReference<List<Benchmark>>(){});
			logger.debug("responce.getBody()" + response.getBody());
			benchmarks = response.getBody();

			logger.info("Benchmark List recieved from Audit Benchmark Microservice");
		} catch (Exception e) {
			logger.error("Exception occured!");
			throw new RequestException(AuditSeverityConstant.AUDIT_BENCHMARK_SERVICE_ERROR);
		}

		logger.debug("BenchmarkList" + benchmarks);

		for (Benchmark benchmark : benchmarks) {
			if (benchmark.getAuditType().equalsIgnoreCase(auditRequest.getAuditDetail().getAuditType())) {
				int benchmarkNoAnswers = benchmark.getBenchmarkNoAnswers();
				if (benchmark.getAuditType().equalsIgnoreCase("Internal")) {
					if (auditRequest.getAuditDetail().getAuditQuestions() <= benchmarkNoAnswers) {
						auditResponse.setProjectExecutionStatus("GREEN");
						auditResponse.setRemedialActionDuration(0);
					} else {
						auditResponse.setProjectExecutionStatus("RED");
						auditResponse.setRemedialActionDuration(2);
					}
				} else {
					if (auditRequest.getAuditDetail().getAuditQuestions() <= benchmarkNoAnswers) {
						auditResponse.setProjectExecutionStatus("GREEN");
						auditResponse.setRemedialActionDuration(0);
					} else {
						auditResponse.setProjectExecutionStatus("RED");
						auditResponse.setRemedialActionDuration(1);
					}
				}
				logger.info("Audit Response generated");
				logger.info("AuditResponse" + auditResponse);

				auditResponseDao.save(auditResponse);
				saveAudit(auditRequest, auditResponse);
				return auditResponse;
			}

		}
		throw new RequestException(AuditSeverityConstant.AUDIT_SEVERITY_SERVICE_ERROR);
	}

	// Saving Audit into Database
	@Override
	public void saveAudit(AuditRequest auditRequest, AuditResponse auditResponse) {

		Audit audit = new Audit();

		audit.setAuditType(auditRequest.getAuditDetail().getAuditType());
		audit.setApplicationOwnerName(auditRequest.getApplicationOwnerName());
		audit.setAuditDate(auditRequest.getAuditDetail().getAuditDate());
		audit.setProjectExecutionStatus(auditResponse.getProjectExecutionStatus());
		audit.setProjectManagerName(auditRequest.getProjectManagerName());
		audit.setProjectName(auditRequest.getProjectName());
		int remActionDur = auditResponse.getRemedialActionDuration();
		if (remActionDur < 2)
			audit.setRemedialActionDuration(Integer.toString(auditResponse.getRemedialActionDuration()) + " Week");
		else
			audit.setRemedialActionDuration(Integer.toString(auditResponse.getRemedialActionDuration()) + " Weeks");
		auditDao.save(audit);
		logger.info("Audit saved into database");
		logger.debug("Audit:- " + audit);
	}

}
