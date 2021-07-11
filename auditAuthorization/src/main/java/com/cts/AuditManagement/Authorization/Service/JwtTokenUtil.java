package com.cts.AuditManagement.Authorization.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

//import com.jwt.helper.String;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * This is a Utility class which is used to carry out token related operations
 * like creating tokens, checking for validation, setting time limit of a token
 * etc.
 *
 */
@Component
public class JwtTokenUtil implements Serializable {

	
	private static final long serialVersionUID = -2550185165626007488L;
	
	public static final long JWT_TOKEN_VALIDITY = 5*60;

	@Value("${jwt.secret}")
	private String secret;

	 /**
	 * Define claims of the token, like Issuer, Expiration, Subject, and the ID
	 * 
	 * @param userDetails
	 * @return 
	 * */
	
      public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	 public String getUsernameFromToken(String token) {
	        return extractClaim(token, Claims::getSubject);
	    }
	 
	 
	 private Claims extractAllClaims(String token) {
			
			Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
			
			return claims;
		}
	 
	 public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
			
			final Claims claims = extractAllClaims(token);
			
			T apply = claimsResolver.apply(claims);
			
			return apply;
		}
	 
	 
	 /**
		 * 
		 * 
		 * @param token
		 * @param userDetails
		 * @return whether the token is from valid user and is not expired
	 */
	 public Boolean validateToken(String token, UserDetails userDetails) {

			final String username = getUsernameFromToken(token);
			
			return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
		}
	 
	 
	 /**
		 * Extracts the Expiry date from the token using getExpiration from Claims
		 * 
		 * @param token
		 * @return the expiry date of token
	 */
	 public Date extractExpiration(String token) {
			
			Date expiryDate = extractClaim(token, Claims::getExpiration);
			
			return expiryDate;

		}
	 
	 private Boolean isTokenExpired(String token) {
			boolean isTokenExpired = extractExpiration(token).before(new Date());
			return isTokenExpired;
		}
	 
	 
	 /**
		 * Token is generated for specified time. The signature is used to verify the message
		 * wasn't changed along the way
		 * 
		 * @return Token
	 **/
	private String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	
	
	public Boolean validateToken(String token) {

		return !isTokenExpired(token);
	}

}
