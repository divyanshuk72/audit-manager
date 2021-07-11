package com.cts.AuditManagement.AuditChecklist.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cts.AuditManagement.AuditChecklist.Entity.CheckListEntity;

public interface CheckListRepo extends JpaRepository<CheckListEntity, Integer> {

	List<CheckListEntity> findByAuditType(String i);
}
