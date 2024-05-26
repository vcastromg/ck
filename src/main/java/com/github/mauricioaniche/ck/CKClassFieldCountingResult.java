package com.github.mauricioaniche.ck;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CKClassFieldCountingResult {
    private int numberOfFields;
    private int numberOfStaticFields;
    private int numberOfPublicFields;
    private int numberOfPrivateFields;
    private int numberOfProtectedFields;
    private int numberOfDefaultFields;
    private int numberOfFinalFields;
    private int numberOfSynchronizedFields;
}
