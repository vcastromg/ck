package com.github.mauricioaniche.ck;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.github.mauricioaniche.ck.result.CKClassResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LCOMNormalizedTest extends BaseTest{
	
	@BeforeAll
	public void setUp() {
		report = run(fixturesDir() + "/lcom");
	}
	
	@Test
	public void should_count_lcom() {
				
		CKClassResult a = report.get("lcom.TripStatusBean");
		BigDecimal db = new BigDecimal(a.ooResult.getLcomNormalized()).setScale(4, RoundingMode.HALF_UP);
		Assertions.assertEquals(0.9615, db.doubleValue());

		CKClassResult b = report.get("lcom.SimpleGetterAndSetter");
		Assertions.assertEquals(0.0, b.ooResult.getLcomNormalized());

		CKClassResult c = report.get("lcom.SimpleGetterAndSetter2");
		Assertions.assertEquals(0.5, c.ooResult.getLcomNormalized());

		CKClassResult d = report.get("lcom.TermsOfServiceController");
		Assertions.assertEquals(0.0, d.ooResult.getLcomNormalized());

	}
	
}
