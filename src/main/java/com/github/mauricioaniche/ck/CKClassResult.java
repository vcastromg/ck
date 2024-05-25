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
    private int dit;
    private int noc = -1;
    private int wmc;
    private int cbo;
    private int cboModified = -1;
    private int fanin = -1;
    private int fanout = -1;
    private int lcom;
    private float lcomNormalized;
    private int rfc;
    private int nosi;
    private int loc;
    private Set<CKMethodResult> methods;
    private Set<CKMethodResult> visibleMethods;
    private Set<String> fieldNames;
    private int returnQty;
    private int loopQty;
    private int comparisonsQty;
    private int tryCatchQty;
    private int parenthesizedExpsQty;
    private int stringLiteralsQty;
    private int numbersQty;
    private int assignmentsQty;
    private int mathOperationsQty;
    private int variablesQty;
    private int maxNestedBlocks;
    private int anonymousClassesQty;
    private int innerClassesQty;
    private int lambdasQty;
    private int uniqueWordsQty;
    private int numberOfMethods;
    private int numberOfStaticMethods;
    private int numberOfPublicMethods;
    private int numberOfPrivateMethods;
    private int numberOfProtectedMethods;
    private int numberOfDefaultMethods;
    private int numberOfAbstractMethods;
    private int numberOfFinalMethods;
    private int numberOfSynchronizedMethods;
    private int numberOfFields;
    private int numberOfStaticFields;
    private int numberOfPublicFields;
    private int numberOfPrivateFields;
    private int numberOfProtectedFields;
    private int numberOfDefaultFields;
    private int numberOfFinalFields;
    private int numberOfSynchronizedFields;
    /**
     * -- GETTER --
     * public/static/private and other org.eclipse.jdt.core.dom.Modifier modifiers
     *
     * @return
     */

    private int modifiers;
    private int numberOfLogStatements;

    private float tightClassCohesion;
    private float looseClassCohesion;

    public CKClassResult(String file, String className, String type, int modifiers) {
        this.file = file;
        this.className = className;
        this.type = type;
        this.methods = new HashSet<>();
        this.visibleMethods = new HashSet<>();
        this.modifiers = modifiers;
    }

    public int getNoc() {
        if (this.noc == -1) {
            NOCExtras extras = NOCExtras.getInstance();
            this.setNoc(extras.getNocValueByName(this.className));
        }

        return this.noc;
    }

    public int getCboModified() {
        if (this.cboModified == -1) {
            CouplingExtras extras = CouplingExtras.getInstance();
            this.setCboModified(extras.getValueCBOClass(this.className));
        }
        return cboModified;
    }

    public int getFanin() {

        if (this.fanin == -1) {
            CouplingExtras extras = CouplingExtras.getInstance();
            this.setFanin(extras.getValueFanInClass(this.className));
        }

        return fanin;
    }

    public int getFanout() {

        if (this.fanout == -1) {
            CouplingExtras extras = CouplingExtras.getInstance();
            this.setFanout(extras.getValueFanOutClass(this.className));
        }

        return fanout;
    }

    @Override
    public String toString() {
        return "CKClassResult [file=" + file + ", className=" + className + "]";
    }

    public void addMethod(CKMethodResult method) {
        this.methods.add(method);
        if (method.getIsVisible()) {
            visibleMethods.add(method);
        }
    }

    public Set<CKMethodResult> getMethods() {
        return Collections.unmodifiableSet(methods);
    }

    public Set<CKMethodResult> getVisibleMethods() {
        return Collections.unmodifiableSet(visibleMethods);
    }

    public Optional<CKMethodResult> getMethod(String methodName) {
        return methods.stream().filter(m -> m.getMethodName().equals(methodName)).findFirst();
    }

    public int getNumberOfVisibleMethods() {
        return visibleMethods.size();
    }

    public void setLogStatementsQty(int numberOfLogStatements) {
        this.numberOfLogStatements = numberOfLogStatements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CKClassResult that = (CKClassResult) o;
        return file.equals(that.file) &&
                className.equals(that.className) &&
                type.equals(that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, className, type);
    }
}
