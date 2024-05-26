package com.github.mauricioaniche.ck;

import com.github.mauricioaniche.ck.result.CKClassResult;
import com.github.mauricioaniche.ck.result.CKMethodResult;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class ResultWriter {

    private static final String[] CLASS_HEADER = {
            "file",
            "class",
            "type",

            /* OO Metrics */
            "cbo",
            "cboModified",
            "fanin",
            "fanout",
            "wmc",
            "dit",
            "noc",
            "rfc",
            "lcom",
            "lcom*",
            "tcc",
            "lcc",

            /* Method Counting */
            "totalMethodsQty",
            "staticMethodsQty",
            "publicMethodsQty",
            "privateMethodsQty",
            "protectedMethodsQty",
            "defaultMethodsQty",
            "visibleMethodsQty",
            "abstractMethodsQty",
            "finalMethodsQty",
            "synchronizedMethodsQty",

            /* Field Counting */
            "totalFieldsQty",
            "staticFieldsQty",
            "publicFieldsQty",
            "privateFieldsQty",
            "protectedFieldsQty",
            "defaultFieldsQty",
            "finalFieldsQty",
            "synchronizedFieldsQty",

            /* Others */
            "nosi",
            "loc",
            "returnQty",
            "loopQty",
            "comparisonsQty",
            "tryCatchQty",
            "parenthesizedExpsQty",
            "stringLiteralsQty",
            "numbersQty",
            "assignmentsQty",
            "mathOperationsQty",
            "variablesQty",
            "maxNestedBlocksQty",
            "anonymousClassesQty",
            "innerClassesQty",
            "lambdasQty",
            "uniqueWordsQty",
            "modifiers",
            "logStatementsQty"};
    private static final String[] METHOD_HEADER = { 
            "file", 
            "class", 
            "method", 
            "constructor", 
            "line", 
            "cbo", 
            "cboModified",
            "fanin",
            "fanout",
            "wmc", 
            "rfc", 
            "loc",
            "returnsQty", 
            "variablesQty", 
            "parametersQty", 
            "methodsInvokedQty", 
            "methodsInvokedLocalQty", 
            "methodsInvokedIndirectLocalQty", 
            "loopQty", 
            "comparisonsQty", 
            "tryCatchQty",
            "parenthesizedExpsQty", 
            "stringLiteralsQty", 
            "numbersQty", 
            "assignmentsQty", 
            "mathOperationsQty",
            "maxNestedBlocksQty", 
            "anonymousClassesQty", 
            "innerClassesQty", 
            "lambdasQty", 
            "uniqueWordsQty", 
            "modifiers", 
            "logStatementsQty", 
            "hasJavaDoc" };
                          
    private static final String[] VAR_FIELD_HEADER = { "file", "class", "method", "variable", "usage" };
    private final boolean variablesAndFields;

    private CSVPrinter classPrinter;
    private CSVPrinter methodPrinter;
    private CSVPrinter variablePrinter;
    private CSVPrinter fieldPrinter;

    /**
     * Initialise a new ResultWriter that writes to the specified files. Begins by
     * writing CSV headers to each file.
     * 
     * @param classFile    Output file for class metrics
     * @param methodFile   Output file for method metrics
     * @param variableFile Output file for variable metrics
     * @param fieldFile    Output file for field metrics
     * @throws IOException If headers cannot be written
     */
    public ResultWriter(String classFile, String methodFile, String variableFile, String fieldFile, boolean variablesAndFields) throws IOException {
        FileWriter classOut = new FileWriter(classFile);
        this.classPrinter = new CSVPrinter(classOut, CSVFormat.DEFAULT.withHeader(CLASS_HEADER));
        FileWriter methodOut = new FileWriter(methodFile);
        this.methodPrinter = new CSVPrinter(methodOut, CSVFormat.DEFAULT.withHeader(METHOD_HEADER));

        this.variablesAndFields = variablesAndFields;
        if(variablesAndFields) {
            FileWriter variableOut = new FileWriter(variableFile);
            this.variablePrinter = new CSVPrinter(variableOut, CSVFormat.DEFAULT.withHeader(VAR_FIELD_HEADER));
            FileWriter fieldOut = new FileWriter(fieldFile);
            this.fieldPrinter = new CSVPrinter(fieldOut, CSVFormat.DEFAULT.withHeader(VAR_FIELD_HEADER));
        }
    }

    /**
     * Print results for a single class and its methods and fields to the
     * appropriate CSVPrinters.
     * 
     * @param result The CKClassResult
     * @throws IOException If output files cannot be written to
     */
    public void printResult(CKClassResult result) throws IOException {

        this.classPrinter.printRecord(
                result.getFile(),
                result.getClassName(),
                result.getType(),

                /* OO Metrics */
                result.ooResult.getCbo(),
                result.ooResult.getCboModified(),
                result.ooResult.getFanin(),
                result.ooResult.getFanout(),
                result.ooResult.getWmc(),
                result.ooResult.getDit(),
                result.ooResult.getNoc(),
                result.ooResult.getRfc(),
                result.ooResult.getLcom(),
                result.ooResult.getLcomNormalized(),
                result.ooResult.getTightClassCohesion(),
                result.ooResult.getLooseClassCohesion(),

                /* Method Counting */
                result.methodCountingResult.getNumberOfMethods(),
                result.methodCountingResult.getNumberOfStaticMethods(),
                result.methodCountingResult.getNumberOfPublicMethods(),
                result.methodCountingResult.getNumberOfPrivateMethods(),
                result.methodCountingResult.getNumberOfProtectedMethods(),
                result.methodCountingResult.getNumberOfDefaultMethods(),
                result.methodCountingResult.getVisibleMethods().size(),
                result.methodCountingResult.getNumberOfAbstractMethods(),
                result.methodCountingResult.getNumberOfFinalMethods(),
                result.methodCountingResult.getNumberOfSynchronizedMethods(),

                /* Field Counting */
                result.fieldCountingResult.getNumberOfFields(),
                result.fieldCountingResult.getNumberOfStaticFields(),
                result.fieldCountingResult.getNumberOfPublicFields(),
                result.fieldCountingResult.getNumberOfPrivateFields(),
                result.fieldCountingResult.getNumberOfProtectedFields(),
                result.fieldCountingResult.getNumberOfDefaultFields(),
                result.fieldCountingResult.getNumberOfFinalFields(),
                result.fieldCountingResult.getNumberOfSynchronizedFields(),

                /* Others */
                result.othersResult.getNosi(),
                result.othersResult.getLoc(),
                result.othersResult.getReturnQty(),
                result.othersResult.getLoopQty(),
                result.othersResult.getComparisonsQty(),
                result.othersResult.getTryCatchQty(),
                result.othersResult.getParenthesizedExpsQty(),
                result.othersResult.getStringLiteralsQty(),
                result.othersResult.getNumbersQty(),
                result.othersResult.getAssignmentsQty(),
                result.othersResult.getMathOperationsQty(),
                result.othersResult.getVariablesQty(),
                result.othersResult.getMaxNestedBlocks(),
                result.othersResult.getAnonymousClassesQty(),
                result.othersResult.getInnerClassesQty(),
                result.othersResult.getLambdasQty(),
                result.othersResult.getUniqueWordsQty(),
                result.othersResult.getModifiers(),
                result.othersResult.getNumberOfLogStatements());

        for (CKMethodResult method : result.getMethods()) {
            this.methodPrinter.printRecord(
                    result.getFile(), 
                    result.getClassName(), 
                    method.getMethodName(),
                    method.isConstructor(),
                    method.getStartLine(), 
                    method.getCbo(), 
                    method.getCboModified(), 
                    method.getFanin(), 
                    method.getFanout(), 
                    method.getWmc(), 
                    method.getRfc(), 
                    method.getLoc(),
                    method.getReturnQty(), 
                    method.getVariablesQty(), 
                    method.getParametersQty(),
                    method.getMethodInvocations().size(), 
                    method.getMethodInvocationsLocal().size(), 
                    method.getMethodInvocationsIndirectLocal().size(),
                    method.getLoopQty(), 
                    method.getComparisonsQty(), 
                    method.getTryCatchQty(),
                    method.getParenthesizedExpsQty(), 
                    method.getStringLiteralsQty(), 
                    method.getNumbersQty(),
                    method.getAssignmentsQty(), 
                    method.getMathOperationsQty(), 
                    method.getMaxNestedBlocks(),
                    method.getAnonymousClassesQty(), 
                    method.getInnerClassesQty(), 
                    method.getLambdasQty(),
                    method.getUniqueWordsQty(), 
                    method.getModifiers(), 
                    method.getLogStatementsQty(), 
                    method.getHasJavadoc());

            if(variablesAndFields) {
                for (Map.Entry<String, Integer> entry : method.getVariablesUsage().entrySet()) {
                    this.variablePrinter.printRecord(result.getFile(), result.getClassName(), method.getMethodName(),
                            entry.getKey(), entry.getValue());
                }

                for (Map.Entry<String, Integer> entry : method.getFieldUsage().entrySet()) {
                    this.fieldPrinter.printRecord(result.getFile(), result.getClassName(), method.getMethodName(),
                            entry.getKey(), entry.getValue());
                }
            }
        }
    }

    /**
     * Flush and close resources that were opened to write results. This method
     * should be called after all CKClassResults have been calculated and printed.
     * 
     * @throws IOException If the resources cannot be closed
     */
    public void flushAndClose() throws IOException {
        this.classPrinter.flush();
        this.classPrinter.close();
        this.methodPrinter.flush();
        this.methodPrinter.close();
        if(variablesAndFields) {
            this.variablePrinter.flush();
            this.variablePrinter.close();
            this.fieldPrinter.flush();
            this.fieldPrinter.close();
        }
    }
}
