package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.result.CKMethodResult;

public interface MethodLevelMetric {
	void setResult(CKMethodResult result);
	
	default void setMethodName(String methodName) {

	}
}
