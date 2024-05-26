package com.github.mauricioaniche.ck;

import java.util.*;

import com.github.mauricioaniche.ck.metric.CouplingExtras;
import com.github.mauricioaniche.ck.metric.NOCExtras;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CKClassResult {
    private String file;
    private String className;
    private String type;
    private Set<CKMethodResult> methods;
    private Set<String> fieldNames;

    public CKClassOOResult ooResult = new CKClassOOResult();
    public CKClassMethodCountingResult methodCountingResult = new CKClassMethodCountingResult();
    public CKClassFieldCountingResult fieldCountingResult = new CKClassFieldCountingResult();
    public CKClassOthersResult othersResult = new CKClassOthersResult();

    public CKClassResult(String file, String className, String type, int modifiers) {
        this.file = file;
        this.className = className;
        this.type = type;
        this.methods = new HashSet<>();
        this.methodCountingResult.visibleMethods = new HashSet<>();
        this.othersResult.setModifiers(modifiers);
    }

    @Override
    public String toString() {
        return "CKClassResult [file=" + file + ", className=" + className + "]";
    }

    public void addMethod(CKMethodResult method) {
        this.methods.add(method);
        if (method.getIsVisible()) {
            methodCountingResult.visibleMethods.add(method);
        }
    }

    public Set<CKMethodResult> getMethods() {
        return Collections.unmodifiableSet(methods);
    }

    public Set<CKMethodResult> getVisibleMethods() {
        return Collections.unmodifiableSet(methodCountingResult.visibleMethods);
    }

    public Optional<CKMethodResult> getMethod(String methodName) {
        return methods.stream().filter(m -> m.getMethodName().equals(methodName)).findFirst();
    }

    public int getNumberOfVisibleMethods() {
        return methodCountingResult.visibleMethods.size();
    }

    public void setLogStatementsQty(int numberOfLogStatements) {
        this.othersResult.setNumberOfLogStatements(numberOfLogStatements);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CKClassResult that = (CKClassResult) o;
        return file.equals(that.file) && className.equals(that.className) && type.equals(that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, className, type);
    }
}
