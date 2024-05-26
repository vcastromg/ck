package com.github.mauricioaniche.ck;

import com.github.mauricioaniche.ck.result.CKClassResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NOSITest extends BaseTest {

	@BeforeAll
	public void setUp() {
		report = run(fixturesDir() + "/nosi");
	}
	
	@Test
	public void staticInvocations() {
		CKClassResult a = report.get("nosi.Class2");
		Assertions.assertEquals(1, a.getNosi());
	}

	@Test
	public void staticInvocationsToMethodsInTheSameClass() {
		CKClassResult a = report.get("nosi.Class3");
		Assertions.assertEquals(2, a.getNosi());
	}

	@Test
	public void doesNotUnderstandWhenNotResolvable() {
		CKClassResult a = report.get("nosi.Class1");
		Assertions.assertEquals(0, a.getNosi());
	}
}
