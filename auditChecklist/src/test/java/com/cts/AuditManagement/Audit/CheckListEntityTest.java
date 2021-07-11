package com.cts.AuditManagement.Audit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.AuditManagement.AuditChecklist.Entity.CheckListEntity;



@SpringBootTest
class CheckListEntityTest {

	CheckListEntity checkListEntity=new CheckListEntity(0, "Internal", "Select Financila Year");
	
	@Test
	public void testCheckListEntityConstructor() {
		CheckListEntity cke=new CheckListEntity(102,"Internal","select Financial Year");
		assertEquals("Internal",cke.getId());
		
	}
	
	@Test
	public void testId()
	{
		checkListEntity.setId(101);
		assertEquals(102 , checkListEntity.getId() );
	}
	
	
	@Test
	public void testgetAuditType()
	{
		checkListEntity.setAuditType("Internal");
		assertEquals("Internal" , checkListEntity.getAuditType() );
	}
	
	
	
	
	
	
	
}
