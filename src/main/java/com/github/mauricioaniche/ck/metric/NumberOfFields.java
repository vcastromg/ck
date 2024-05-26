package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.result.CKClassResult;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.Modifier;

import java.util.HashSet;
import java.util.Set;

import static com.github.mauricioaniche.ck.util.JDTUtils.getVariableName;

public class NumberOfFields implements CKASTVisitor, ClassLevelMetric {

	private Set<String> fieldNames = new HashSet<>();
	private int fields;
	private int staticFields;
	private int publicFields;
	private int privateFields;
	private int protectedFields;
	private int defaultFields;
	private int finalFields;
	private int synchronizedFields;

	@Override
	public void visit(FieldDeclaration node) {
		fields++;
		fieldNames.addAll(getVariableName(node.fragments()));

		boolean isPublic = Modifier.isPublic(node.getModifiers());
		boolean isPrivate = Modifier.isPrivate(node.getModifiers());
		boolean isProtected = Modifier.isProtected(node.getModifiers());

		if(isPublic)
			publicFields++;
		else if(isPrivate)
			privateFields++;
		else if(isProtected)
			protectedFields++;
		else
			defaultFields++;

		// other characteristics rather than visibility
		boolean isStatic = Modifier.isStatic(node.getModifiers());
		boolean isFinal = Modifier.isFinal(node.getModifiers());
		boolean isSynchronized = Modifier.isSynchronized(node.getModifiers());
		
		if(isStatic)
			staticFields++;

		if(isFinal)
			finalFields++;

		if(isSynchronized)
			synchronizedFields++;

	}

	@Override
	public void setResult(CKClassResult result) {
		result.fieldCountingResult.setNumberOfFields(fields);
		result.setFieldNames(fieldNames);
		result.fieldCountingResult.setNumberOfStaticFields(staticFields);
		result.fieldCountingResult.setNumberOfPublicFields(publicFields);
		result.fieldCountingResult.setNumberOfPrivateFields(privateFields);
		result.fieldCountingResult.setNumberOfProtectedFields(protectedFields);
		result.fieldCountingResult.setNumberOfDefaultFields(defaultFields);
		result.fieldCountingResult.setNumberOfFinalFields(finalFields);
		result.fieldCountingResult.setNumberOfSynchronizedFields(synchronizedFields);
	}
}