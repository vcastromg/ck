package com.github.mauricioaniche.ck;

import com.github.mauricioaniche.ck.result.CKClassResult;

public interface CKNotifier {
	void notify(CKClassResult result);
	default void notifyError(String sourceFilePath, Exception e) {}
}
