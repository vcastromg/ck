package com.github.mauricioaniche.ck.result;

import com.github.mauricioaniche.ck.metric.CouplingExtras;
import com.github.mauricioaniche.ck.metric.NOCExtras;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CKClassOOResult {
    private String className;
    private int cbo;
    private int cboModified = -1;
    private int fanin = -1;
    private int fanout = -1;
    private int noc = -1;
    private int wmc;
    private int lcom;
    private int dit;
    private float lcomNormalized;
    private int rfc;
    private float tightClassCohesion;
    private float looseClassCohesion;

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
}
