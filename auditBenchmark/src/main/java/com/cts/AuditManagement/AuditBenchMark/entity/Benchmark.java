package com.cts.AuditManagement.AuditBenchMark.entity;

public class Benchmark {

	private String auditType;
	private int benchmarkNoAnswers;

	public String getAuditType() {
		return auditType;
	}

	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}

	public int getBenchmarkNoAnswers() {
		return benchmarkNoAnswers;
	}

	public void setBenchmarkNoAnswers(int benchmarkNoAnswers) {
		this.benchmarkNoAnswers = benchmarkNoAnswers;
	}

	public Benchmark(String auditType, int benchmarkNoAnswers) {
		this.auditType = auditType;
		this.benchmarkNoAnswers = benchmarkNoAnswers;
	}

	public Benchmark() {

	}

	@Override
	public String toString() {
		return "AuditBenchmark [AuditType=" + auditType + ", BenchmarkNoAnswers=" + benchmarkNoAnswers + "]";
	}

}
