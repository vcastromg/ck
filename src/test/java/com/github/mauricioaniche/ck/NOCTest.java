package com.github.mauricioaniche.ck;

import com.github.mauricioaniche.ck.metric.NOCExtras;
import com.github.mauricioaniche.ck.result.CKClassResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NOCTest extends BaseTest{

	@BeforeAll
	public void setUp() {
		NOCExtras.resetInstance();
		report = run(fixturesDir() + "/dit");
	}
	
	@Test
	public void shouldDetectChildren() {
		CKClassResult a = report.get("dit.A");
		Assertions.assertEquals(1, a.ooResult.getNoc());
		
		CKClassResult b = report.get("dit.B");
		Assertions.assertEquals(2, b.ooResult.getNoc());
		
		CKClassResult c = report.get("dit.C");
		Assertions.assertEquals(1, c.ooResult.getNoc());
		
		CKClassResult d = report.get("dit.D");
		Assertions.assertEquals(0, d.ooResult.getNoc());
		
		CKClassResult e = report.get("dit.C2");
		Assertions.assertEquals(0, e.ooResult.getNoc());
		
		CKClassResult f = report.get("dit.X");
		Assertions.assertEquals(0, f.ooResult.getNoc());
	}
	
}
