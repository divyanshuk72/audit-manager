package com.cts.AuditManagement.Authorization.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.AuditManagement.Authorization.Entity.User;

public interface AuthorizationRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
