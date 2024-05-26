package com.github.mauricioaniche.ck.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CKClassOthersResult {
    private int nosi;
    private int loc;
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
    private int modifiers;
    private int numberOfLogStatements;
}
