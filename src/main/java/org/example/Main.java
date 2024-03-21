package org.example;

import org.example.iec61850logicalNodes.protocol.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //Создание экземпляров классов узлов
        LSVS lsvs = new LSVS();
        MMXU mmxu = new MMXU(lsvs.getOutA(), lsvs.getOutB(), lsvs.getOutC());
        NHMI nhmi = new NHMI();
        PTOC ptoc1 = new PTOC(412d, lsvs.getOutA().getT().getValue(), 200, mmxu.getA());
        PTOC ptoc2 = new PTOC(412d, lsvs.getOutA().getT().getValue(),  400, mmxu.getA());
        PTOC ptoc3 = new PTOC(412d, lsvs.getOutA().getT().getValue(),  600, mmxu.getA());
        CSWI cswi = new CSWI();
        XCBR xcbr = new XCBR();

        //Узел ptoc1 инициализация
        cswi.setOpOpn(ptoc1.getOp());
        cswi.setOpOpn(ptoc2.getOp());
        cswi.setOpOpn(ptoc3.getOp());
        xcbr.setPos(cswi.getPos());
        xcbr.getOpCnt().getStVal().setValue(0);





        //Добавление сигналов для построения графиков
        nhmi.addSignals(new NHMISignal("PhA", lsvs.getOutA().getInstMag().getF()));
        nhmi.addSignals(new NHMISignal("PhB", lsvs.getOutB().getInstMag().getF()));
        nhmi.addSignals(new NHMISignal("PhC", lsvs.getOutC().getInstMag().getF()));
        nhmi.addSignals(new NHMISignal("PhAFourier", mmxu.getA().getPhsA().getCVal().getMag().getF()), new NHMISignal("", ptoc1.getStrVal().getSetMag().getF()));
        nhmi.addSignals(new NHMISignal("PhBFourier", mmxu.getA().getPhsB().getCVal().getMag().getF()), new NHMISignal("", ptoc1.getStrVal().getSetMag().getF()));
        nhmi.addSignals(new NHMISignal("PhCFourier", mmxu.getA().getPhsB().getCVal().getMag().getF()), new NHMISignal("", ptoc1.getStrVal().getSetMag().getF()));

        nhmi.addSignals(new NHMISignal("PTOC1 op A", ptoc1.getOp().getPhsA()), new NHMISignal("PTOC1 str A", ptoc1.getStr().getPhsA()));
        nhmi.addSignals(new NHMISignal("PTOC1 op B", ptoc1.getOp().getPhsB()), new NHMISignal("PTOC1 str B", ptoc1.getStr().getPhsB()));
        nhmi.addSignals(new NHMISignal("PTOC1 op C", ptoc1.getOp().getPhsC()), new NHMISignal("PTOC1 str C", ptoc1.getStr().getPhsC()));
        nhmi.addSignals(new NHMISignal("PTOC1 op gen", ptoc1.getOp().getGeneral()), new NHMISignal("PTOC1 str gen", ptoc1.getStr().getGeneral()));

        nhmi.addSignals(new NHMISignal("PTOC2 op A", ptoc2.getOp().getPhsA()), new NHMISignal("PTOC2 str A", ptoc2.getStr().getPhsA()));
        nhmi.addSignals(new NHMISignal("PTOC2 op B", ptoc2.getOp().getPhsB()), new NHMISignal("PTOC2 str B", ptoc2.getStr().getPhsB()));
        nhmi.addSignals(new NHMISignal("PTOC2 op C", ptoc2.getOp().getPhsC()), new NHMISignal("PTOC2 str C", ptoc2.getStr().getPhsC()));
        nhmi.addSignals(new NHMISignal("PTOC2 op gen", ptoc2.getOp().getGeneral()), new NHMISignal("PTOC2 str gen", ptoc2.getStr().getGeneral()));

        nhmi.addSignals(new NHMISignal("PTOC3 op A", ptoc3.getOp().getPhsA()), new NHMISignal("PTOC3 str A", ptoc3.getStr().getPhsA()));
        nhmi.addSignals(new NHMISignal("PTOC3 op B", ptoc3.getOp().getPhsB()), new NHMISignal("PTOC3 str B", ptoc3.getStr().getPhsB()));
        nhmi.addSignals(new NHMISignal("PTOC3 op C", ptoc3.getOp().getPhsC()), new NHMISignal("PTOC3 str C", ptoc3.getStr().getPhsC()));
        nhmi.addSignals(new NHMISignal("PTOC3 op gen", ptoc3.getOp().getGeneral()), new NHMISignal("PTOC3 str gen", ptoc3.getStr().getGeneral()));
        nhmi.addSignals(new NHMISignal("Breaker", cswi.getPos().getStVal()));


        //Обработка сигналов csv через итератор
        while(lsvs.hasNext()) {
            lsvs.process();
            mmxu.process();
            ptoc1.process();
            ptoc2.process();
            ptoc3.process();
            nhmi.process();
            cswi.process();
            xcbr.process();
        }
    }
}
