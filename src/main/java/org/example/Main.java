package org.example;

import org.example.iec61850logicalNodes.protocol.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        LSVS lsvs = new LSVS();
        MMXU mmxu = new MMXU(lsvs.getOutA(), lsvs.getOutB(), lsvs.getOutC());
        NHMI nhmi = new NHMI();
        PTOC ptoc = new PTOC();
        CSWI cswi = new CSWI();

        ptoc.getStrVal().getSetMag().getF().setValue(680d);
        ptoc.getOpDlTmms().getSetVal().setValue(800);
        ptoc.setA(mmxu.getA());
        cswi.setOpOpn1(ptoc.getOp());

        nhmi.addSignals(new NHMISignal("PhA", lsvs.getOutA().getInstMag().getF()));
        nhmi.addSignals(new NHMISignal("PhB", lsvs.getOutB().getInstMag().getF()));
        nhmi.addSignals(new NHMISignal("PhC", lsvs.getOutC().getInstMag().getF()));
        nhmi.addSignals(new NHMISignal("PhAFourier", mmxu.getA().getPhsA().getCVal().getMag().getF()), new NHMISignal("", ptoc.getStrVal().getSetMag().getF()));
        nhmi.addSignals(new NHMISignal("PhBFourier", mmxu.getA().getPhsB().getCVal().getMag().getF()), new NHMISignal("", ptoc.getStrVal().getSetMag().getF()));
        nhmi.addSignals(new NHMISignal("PhCFourier", mmxu.getA().getPhsB().getCVal().getMag().getF()), new NHMISignal("", ptoc.getStrVal().getSetMag().getF()));
        nhmi.addSignals(new NHMISignal("PTOC A", ptoc.getOp().getPhsA()));
        nhmi.addSignals(new NHMISignal("PTOC B", ptoc.getOp().getPhsB()));
        nhmi.addSignals(new NHMISignal("PTOC C", ptoc.getOp().getPhsC()));
        nhmi.addSignals(new NHMISignal("PTOC gen", ptoc.getOp().getGeneral()));


        while(lsvs.hasNext()) {
            lsvs.process();
            mmxu.process();
            ptoc.process();
            nhmi.process();
        }
//

//        mmxu.process();
//
//
//
//        PTOC ptoc = new PTOC();
//        ptoc.setA(mmxu.getA());
//        ptoc.getStrVal().getSetMag().getF().set(0, 0.5);
//        ptoc.getOpDlTmms().setValue(String.valueOf(0.1));
//        ptoc.process();
//
////        CSWI cswi = new CSWI(ptoc.getOp());
////        cswi.process();
//

}
}
