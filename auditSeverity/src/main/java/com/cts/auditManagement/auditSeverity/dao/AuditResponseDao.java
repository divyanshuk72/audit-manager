package com.cts.auditManagement.auditSeverity.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.auditManagement.auditSeverity.entity.AuditResponse;

//Repository for AuditResponse

@Repository
public interface AuditResponseDao extends JpaRepository<AuditResponse, Integer> {

}
