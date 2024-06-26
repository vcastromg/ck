package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.result.CKClassResult;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;

public class NumberOfMethods implements CKASTVisitor, ClassLevelMetric {

	private int methods;
	private int staticMethods;
	private int publicMethods;
	private int privateMethods;
	private int protectedMethods;
	private int defaultMethods;
	private int abstractMethods;
	private int finalMethods;
	private int synchronizedMethods;

	@Override
	public void visit(MethodDeclaration node) {
		methods++;

		// visibility
		boolean isPublic = Modifier.isPublic(node.getModifiers());
		boolean isPrivate = Modifier.isPrivate(node.getModifiers());
		boolean isProtected = Modifier.isProtected(node.getModifiers());

		if(isPublic)
			publicMethods++;
		else if(isPrivate)
			privateMethods++;
		else if(isProtected)
			protectedMethods++;
		else
			defaultMethods++;

		// other characteristics

		boolean isFinal = Modifier.isFinal(node.getModifiers());
		boolean isSynchronized = Modifier.isSynchronized(node.getModifiers());
		boolean isAbstract = Modifier.isAbstract(node.getModifiers());
		boolean isStatic = Modifier.isStatic(node.getModifiers());

		if(isStatic)
			staticMethods++;

		if(isAbstract)
			abstractMethods++;

		if(isFinal)
			finalMethods++;

		if(isSynchronized)
			synchronizedMethods++;
	}

	@Override
	public void setResult(CKClassResult result) {
		result.methodCountingResult.setNumberOfMethods(methods);
		result.methodCountingResult.setNumberOfStaticMethods(staticMethods);
		result.methodCountingResult.setNumberOfPublicMethods(publicMethods);
		result.methodCountingResult.setNumberOfPrivateMethods(privateMethods);
		result.methodCountingResult.setNumberOfProtectedMethods(protectedMethods);
		result.methodCountingResult.setNumberOfDefaultMethods(defaultMethods);
		result.methodCountingResult.setNumberOfAbstractMethods(abstractMethods);
		result.methodCountingResult.setNumberOfFinalMethods(finalMethods);
		result.methodCountingResult.setNumberOfSynchronizedMethods(synchronizedMethods);

	}
}
