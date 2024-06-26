package com.github.mauricioaniche.ck;

import com.github.mauricioaniche.ck.result.CKClassResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LCOMTest extends BaseTest {

	@BeforeAll
	public void setUp() {
		report = run(fixturesDir() + "/lcom");
	}
	
	@Test
	public void should_count_lcom() {
		
		CKClassResult a = report.get("lcom.TripStatusBean");
		Assertions.assertEquals(1415, a.ooResult.getLcom());

		CKClassResult b = report.get("lcom.SimpleGetterAndSetter");
		Assertions.assertEquals(0, b.ooResult.getLcom());

		CKClassResult c = report.get("lcom.SimpleGetterAndSetter2");
		Assertions.assertEquals(2, c.ooResult.getLcom());

		CKClassResult d = report.get("lcom.TermsOfServiceController");
		Assertions.assertEquals(0, d.ooResult.getLcom());

	}
	
}
