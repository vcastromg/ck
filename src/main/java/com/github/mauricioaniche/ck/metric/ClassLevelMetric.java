package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.result.CKClassResult;

public interface ClassLevelMetric {
	void setResult(CKClassResult result);
	
	default void setClassName(String className) {

	}
}
