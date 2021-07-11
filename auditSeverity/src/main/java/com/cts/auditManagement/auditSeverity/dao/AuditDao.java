package com.cts.auditManagement.auditSeverity.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.auditManagement.auditSeverity.entity.Audit;

//Repository for Audit

@Repository
public interface AuditDao extends JpaRepository<Audit, Integer> {

}
