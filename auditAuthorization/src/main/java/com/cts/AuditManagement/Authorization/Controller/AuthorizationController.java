package com.cts.AuditManagement.Authorization.Controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.AuditManagement.Authorization.Entity.AuthResponse;
import com.cts.AuditManagement.Authorization.Entity.UserCredential;
import com.cts.AuditManagement.Authorization.Service.*;


@RestController
public class AuthorizationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailImp jwtUser;
	
	Logger logger = LoggerFactory.getLogger(AuthorizationController.class);

	/**
	 * This method is responsible for checking whether the user credentials are
	 * correct or not by calling the authenticate method. If the user credentials
	 * are correct the token will be generated and if the credentials are wrong
	 * Exception will be thrown.Uses JwtUtil class to generate token.
	 * 
	 */
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody UserCredential userCredentials) throws Exception {

		logger.info("START");
		logger.debug("USERID ",userCredentials.getUsername());
		
		try {

			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					userCredentials.getUsername(), userCredentials.getPassword()));
		} catch (Exception e) {
			logger.error("EXCEPTION: NOT A VALID USER");
			logger.error(e.getMessage());
			return new ResponseEntity<String>("Wrong Credentials", HttpStatus.BAD_REQUEST);
		}

		UserDetails user = this.jwtUser.loadUserByUsername(userCredentials.getUsername());
		String token = this.jwtTokenUtil.generateToken(user);
		logger.info("Inside Authorization Controller  ");
		logger.info(token);
		logger.debug("{Token} ",token);
		// return ResponseEntity.ok(new JwtRespone(token));
		return new ResponseEntity<String>(token, HttpStatus.OK);

	}

	/**
	 * This Method is responsible for validating the token provided as parameter by
	 * every request. It uses the JwtUtil class for that.
	 * 
	 * @param token
	 * @return JwtToken class and status as OK for valid token.
	 */
	@GetMapping(value = "/validate")
	public ResponseEntity<?> getValidity(@RequestHeader("Authorization") String token)  {
		token = token.substring(7);
		ResponseEntity<?> response=null;
		AuthResponse authResponse= new AuthResponse();
		logger.info("START");
		logger.info("Inside Validate");
		try {
				if(jwtTokenUtil.validateToken(token)) {
			         logger.info("Token Validated");
					authResponse.setUid(jwtTokenUtil.getUsernameFromToken(token));
					authResponse.setValid(true);
			}
		}catch(Exception e) {
			logger.info("Error in Validation ", e);;
			authResponse.setValid(false);
			response =  new ResponseEntity<String>("Auth Failed",HttpStatus.FORBIDDEN);
		    return response;
			
		}
	    logger.info("END");
		response = new ResponseEntity<AuthResponse>(authResponse,HttpStatus.OK);
		return response;
	}
	
	
}




	
	
	
	
	
	  




