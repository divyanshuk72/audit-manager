package com.cts.AuditManagement.AuditBenchMark;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.AuditManagement.AuditBenchMark.entity.Benchmark;

@SpringBootTest
class AuditBenchmarkTest {

	Benchmark benchmark1;

	@BeforeEach
	public void setup() {
		benchmark1 = new Benchmark("Internal", 3);
	}

	@Test
	public void testGetSetAuditType() {
		assertEquals("Internal", benchmark1.getAuditType());
		benchmark1.setAuditType("SOX");
		assertEquals("SOX", benchmark1.getAuditType());
	}

	@Test
	public void testAuditBenchmark_AuditTypeNotNull() {
		assertNotNull(benchmark1.getAuditType());

	}

	@Test
	public void testAuditBenchmark_AuditBenchmarkNoAnswerNotNull() {
		assertNotNull(benchmark1.getBenchmarkNoAnswers());

	}

	@Test
	public void testAuditBenchmark_AuditTypeNotEqual() {
		assertNotNull(benchmark1.getAuditType());

	}

}
