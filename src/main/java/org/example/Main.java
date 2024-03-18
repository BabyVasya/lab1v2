package org.example;

import org.example.iec61850logicalNodes.protocol.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        LSVS lsvs = new LSVS();
        lsvs.process();
//
//        MMXU mmxu = new MMXU(lsvs.getOutA(), lsvs.getOutB(), lsvs.getOutC());
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
//        NHMI nhmi = new NHMI(lsvs.getOutA(), lsvs.getOutB(), lsvs.getOutC(),
//                             mmxu.getA().getPhsA(), mmxu.getA().getPhsB(), mmxu.getA().getPhsC(),
//                             ptoc.getOp());
//        nhmi.process();
}
}
