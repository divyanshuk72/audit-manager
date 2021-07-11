package com.cts.AuditManagement.AuditChecklist.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cts.AuditManagement.AuditChecklist.Entity.AuthResponse;

@Service
public class AuditChecklistTokenService {

	@Autowired
	private RestTemplate restTemp;
	
	@Value("${AuthorisationService.Url}")
	private String AuthServiceUrl;

	public Boolean validateToken(String token) {

			try{
				HttpHeaders headers = new HttpHeaders();
				headers.set("Authorization", token);
				
				HttpEntity<Boolean> requestEntity = new HttpEntity<>(null, headers);
				AuthResponse authResponse = restTemp.exchange(AuthServiceUrl, HttpMethod.GET, requestEntity, AuthResponse.class).getBody(); 
                return authResponse.isValid();
			}
			catch(Exception e) {
				
				return false;
			}
		}
}
