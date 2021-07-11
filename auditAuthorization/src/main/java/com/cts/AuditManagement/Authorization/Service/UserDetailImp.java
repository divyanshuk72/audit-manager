package com.cts.AuditManagement.Authorization.Service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.AuditManagement.Authorization.Config.SecurityConfig;
import com.cts.AuditManagement.Authorization.Entity.User;
import com.cts.AuditManagement.Authorization.Repository.AuthorizationRepository;

/**
 * This class is used to load the user details from the database and check
 * whether the user resides in database or not based on the given id. 
 * This class implements the UserDetailsService interface. 
 */

@Service
public class UserDetailImp implements UserDetailsService {

	@Autowired
	AuthorizationRepository Authrepo;
	
	Logger logger = LoggerFactory.getLogger( UserDetailImp.class);
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.debug("USERID ", username);
		User user=Authrepo.findByEmail(username);
		if(user==null) {
			logger.error("User not valid");
			throw new UsernameNotFoundException("User not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),new ArrayList<>());
		
	}

}
