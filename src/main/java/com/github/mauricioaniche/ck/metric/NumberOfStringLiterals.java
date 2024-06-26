package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.result.CKClassResult;
import com.github.mauricioaniche.ck.result.CKMethodResult;
import org.eclipse.jdt.core.dom.StringLiteral;

public class NumberOfStringLiterals implements CKASTVisitor, ClassLevelMetric, MethodLevelMetric {

	private int qty = 0;

	public void visit(StringLiteral node) {
		qty++;
	}

	@Override
	public void setResult(CKMethodResult result) {
		result.setStringLiteralsQty(qty);
	}

	@Override
	public void setResult(CKClassResult result) {
		result.othersResult.setStringLiteralsQty(qty);
	}
}
