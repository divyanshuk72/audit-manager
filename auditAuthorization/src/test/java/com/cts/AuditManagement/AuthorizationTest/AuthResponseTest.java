package com.cts.AuditManagement.AuthorizationTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.AuditManagement.Authorization.Entity.AuthResponse;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = AuthResponseTest.class)

class AuthResponseTest {

	AuthResponse authResponse = new AuthResponse();

	@Test
	public void testAuthResponseConstructor() {
		AuthResponse response = new AuthResponse("abc", true);
		assertEquals("abc", response.getUid());
	}

	@Test
	public void testUid() {
		authResponse.setUid("abc");
		assertEquals("abc", authResponse.getUid());
	}

	@Test
	public void testIsValid() {
		authResponse.setValid(true);
		assertEquals(true, authResponse.isValid());
	}

	@Test
	public void testtoString() {
		String s = authResponse.toString();
		assertEquals(s, authResponse.toString());
	}

}
