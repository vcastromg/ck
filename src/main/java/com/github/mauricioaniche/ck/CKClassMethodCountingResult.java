package com.github.mauricioaniche.ck;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CKClassMethodCountingResult {
    private int numberOfMethods;
    private int numberOfStaticMethods;
    private int numberOfPublicMethods;
    private int numberOfPrivateMethods;
    private int numberOfProtectedMethods;
    private int numberOfDefaultMethods;
    private int numberOfAbstractMethods;
    private int numberOfFinalMethods;
    private int numberOfSynchronizedMethods;
    public Set<CKMethodResult> visibleMethods;
}
