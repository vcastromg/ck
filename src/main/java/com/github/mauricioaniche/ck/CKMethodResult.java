package com.github.mauricioaniche.ck;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.jdt.core.dom.Modifier;
import com.github.mauricioaniche.ck.metric.CouplingExtras;

import java.util.*;

@Getter
@Setter
public class CKMethodResult {
    private int cbo;
    private int cboModified = -1;
    private int fanin = -1;
    private int fanout = -1;
    private int rfc;
    private int wmc;
    private String methodName;
    private String qualifiedMethodName;
    private boolean isVisible;
    private int parametersQty;
    private int returnQty;
    private int loc;
    private int variablesQty;
    private Map<String, Integer> variablesUsage;
    private int startLine;
    private int loopQty;
    private int comparisonsQty;
    private int tryCatchQty;
    private int parenthesizedExpsQty;
    private int stringLiteralsQty;
    private int numbersQty;
    private int assignmentsQty;
    private int mathOperationsQty;
    private int maxNestedBlocks;
    private int anonymousClassesQty;
    private int innerClassesQty;
    private int lambdasQty;
    private int uniqueWordsQty;
    //all local field accesses
    private Map<String, Integer> fieldUsage;
    private boolean isConstructor;
    private int modifiers;
    private int logStatementsQty;
    private boolean hasJavadoc;
    //All direct invocations of methods
    private Set<String> methodInvocations;
    //All direct invocations of methods of the same class
    private Set<String> methodInvocationsLocal;
    //All indirect invocations of methods of the same class
    private Map<String, Set<String>> methodInvocationsIndirectLocal;

    public CKMethodResult(String methodName, String qualifiedMethodName, boolean isConstructor, int modifiers) {
        this.methodName = methodName;
        this.qualifiedMethodName = qualifiedMethodName;
        this.isConstructor = isConstructor;
        this.modifiers = modifiers;
        this.isVisible = !Modifier.isPrivate(modifiers);
    }

    public boolean getIsVisible() {
        return isVisible;
    }

    public int getCboModified() {
        if (this.cboModified == -1) {
            CouplingExtras extras = CouplingExtras.getInstance();
            this.setCboModified(extras.getValueCBOMethod(this.qualifiedMethodName));
        }
        return cboModified;
    }

    public int getFanin() {
        if (this.fanin == -1) {
            CouplingExtras extras = CouplingExtras.getInstance();
            this.setFanin(extras.getValueFanInMethod(this.qualifiedMethodName));
        }
        return fanin;
    }

    public int getFanout() {
        if (this.fanout == -1) {
            CouplingExtras extras = CouplingExtras.getInstance();
            this.setFanout(extras.getValueFanOutMethod(this.qualifiedMethodName));
        }
        return fanout;
    }

    @Override
    public String toString() {
        return "CKMethodResult{" + "cbo=" + cbo + ", rfc=" + rfc + ", wmc=" + wmc + ", methodName='" + methodName + '\'' + '}';
    }

    public Map<String, Integer> getVariablesUsage() {
        if (this.variablesUsage == null) this.variablesUsage = new HashMap<>();
        return variablesUsage;
    }

    public Map<String, Integer> getFieldUsage() {
        if (this.fieldUsage == null) fieldUsage = new HashMap<>();
        return fieldUsage;
    }

    public Set<String> getFieldsAccessed() {
        if (this.fieldUsage == null) fieldUsage = new HashMap<>();
        return fieldUsage.keySet();
    }

    public boolean isConstructor() {
        return isConstructor;
    }

    public boolean getHasJavadoc() {
        return hasJavadoc;
    }

    public Set<String> getMethodInvocations() {
        if (methodInvocations == null) methodInvocations = new HashSet<>();
        return methodInvocations;
    }

    public void setMethodInvocationLocal(Set<String> methodInvocationsLocal) {
        this.methodInvocationsLocal = methodInvocationsLocal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CKMethodResult that = (CKMethodResult) o;
        return startLine == that.startLine && methodName.equals(that.methodName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(methodName, startLine);
    }
}
