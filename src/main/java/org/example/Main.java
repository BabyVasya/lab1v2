package org.example;


import lombok.extern.slf4j.Slf4j;
import org.example.iec61850logicalNodes.protocol.*;
import org.example.iec61850logicalNodes.protocol.graphics.NHMI;
import org.example.iec61850logicalNodes.protocol.graphics.NHMISignal;



@Slf4j
public class Main {

    public static void main(String[] args) {
        LSVS lsvs1 = new LSVS(1);
        LSVS lsvs2 = new LSVS(2);
        LSVS lsvs3 = new LSVS(3);
        LSVS lsvs4 = new LSVS(4);
        LSVS lsvs5 = new LSVS(5);
        int discFreq = lsvs1.getDiscFreq();
        MMXU mmxu1 = new MMXU(
                lsvs1.getOutACurrent(),
                lsvs1.getOutBCurrent(),
                lsvs1.getOutCCurrent(),
                discFreq
        );
        MMXU mmxu2 = new MMXU(
                lsvs2.getOutACurrent(),
                lsvs2.getOutBCurrent(),
                lsvs2.getOutCCurrent(),
                discFreq
        );
        MMXU mmxu3 = new MMXU(
                lsvs3.getOutACurrent(),
                lsvs3.getOutBCurrent(),
                lsvs3.getOutCCurrent(),
                discFreq
        );
        MMXU mmxu4 = new MMXU(
                lsvs4.getOutACurrent(),
                lsvs4.getOutBCurrent(),
                lsvs4.getOutCCurrent(),
                discFreq
        );
        MMXU mmxu5 = new MMXU(
                lsvs5.getOutACurrent(),
                lsvs5.getOutBCurrent(),
                lsvs5.getOutCCurrent(),
                discFreq
        );
        RMXU rmxu = new RMXU();
        rmxu.setALoc1(mmxu1.getA());
        rmxu.setALoc2(mmxu2.getA());
        rmxu.setALoc3(mmxu3.getA());
        rmxu.setALoc4(mmxu4.getA());
        rmxu.setALoc5(mmxu5.getA());


        MHAI mhai1 = new MHAI(
                lsvs1.getOutACurrent(),
                lsvs1.getOutBCurrent(),
                lsvs1.getOutCCurrent(),
                discFreq
        );
        MHAI mhai2 = new MHAI(
                lsvs2.getOutACurrent(),
                lsvs2.getOutBCurrent(),
                lsvs2.getOutCCurrent(),
                discFreq
        );
        MHAI mhai3 = new MHAI(
                lsvs3.getOutACurrent(),
                lsvs3.getOutBCurrent(),
                lsvs3.getOutCCurrent(),
                discFreq
        );
        MHAI mhai4 = new MHAI(
                lsvs4.getOutACurrent(),
                lsvs4.getOutBCurrent(),
                lsvs4.getOutCCurrent(),
                discFreq
        );
        MHAI mhai5 = new MHAI(
                lsvs5.getOutACurrent(),
                lsvs5.getOutBCurrent(),
                lsvs5.getOutCCurrent(),
                discFreq
        );



        PHAR phar1 = new PHAR();
        PHAR phar2 = new PHAR();
        PHAR phar3 = new PHAR();
        PHAR phar4 = new PHAR();
        PHAR phar5 = new PHAR();
        phar1.setHA(mhai1.getHA());
        phar2.setHA(mhai2.getHA());
        phar3.setHA(mhai3.getHA());
        phar4.setHA(mhai4.getHA());
        phar5.setHA(mhai5.getHA());

        PDIF pdif = new PDIF();
        pdif.getTmACrv().getSetParA().setValue(2800f);
        pdif.getTmACrv().getSetParB().setValue(3000f);
        pdif.getTmACrv().getSetParC().setValue(0.3f);
        pdif.setDifAClc(rmxu.getDif());
        pdif.setRstA(rmxu.getRst());
        pdif.setBlc5Harm1(phar1.getStr());
        pdif.setBlc5Harm2(phar2.getStr());
        pdif.setBlc5Harm3(phar3.getStr());
        pdif.setBlc5Harm4(phar4.getStr());
        pdif.setBlc5Harm5(phar5.getStr());

        PTOC ptoc = new PTOC(rmxu.getDif());
        ptoc.getStrVal().getSetMag().getF().setValue(12000d);

        CSWI cswi1 = new CSWI(ptoc.getOp(), pdif.getOp());
        CSWI cswi2 = new CSWI(ptoc.getOp(), pdif.getOp());
        CSWI cswi3 = new CSWI(ptoc.getOp(), pdif.getOp());
        CSWI cswi4 = new CSWI(ptoc.getOp(), pdif.getOp());
        CSWI cswi5 = new CSWI(ptoc.getOp(), pdif.getOp());

        XCBR xcbr1 = new XCBR(cswi1.getPos());
        XCBR xcbr2 = new XCBR(cswi2.getPos());
        XCBR xcbr3 = new XCBR(cswi3.getPos());
        XCBR xcbr4 = new XCBR(cswi4.getPos());
        XCBR xcbr5 = new XCBR(cswi5.getPos());









        NHMI nhmiValues13 = new NHMI();
        NHMI nhmiValues45 = new NHMI();
        NHMI nhmiFourierValues13 = new NHMI();
        NHMI nhmiFourierValues45 = new NHMI();
        NHMI nhmipidf = new NHMI();
        NHMI harmonics1 = new NHMI();
        nhmiValues13.addSignals(new NHMISignal("Ток Фаза А 1", lsvs1.getOutACurrent().getInstMag().getF()));
        nhmiValues13.addSignals(new NHMISignal("Ток Фаза B 1", lsvs1.getOutBCurrent().getInstMag().getF()));
        nhmiValues13.addSignals(new NHMISignal("Ток Фаза C 1", lsvs1.getOutCCurrent().getInstMag().getF()));
        nhmiValues13.addSignals(new NHMISignal("Ток Фаза А 2", lsvs2.getOutACurrent().getInstMag().getF()));
        nhmiValues13.addSignals(new NHMISignal("Ток Фаза B 2", lsvs2.getOutBCurrent().getInstMag().getF()));
        nhmiValues13.addSignals(new NHMISignal("Ток Фаза C 2", lsvs2.getOutCCurrent().getInstMag().getF()));
        nhmiValues13.addSignals(new NHMISignal("Ток Фаза А 3", lsvs3.getOutACurrent().getInstMag().getF()));
        nhmiValues13.addSignals(new NHMISignal("Ток Фаза B 3", lsvs3.getOutBCurrent().getInstMag().getF()));
        nhmiValues13.addSignals(new NHMISignal("Ток Фаза C 3", lsvs3.getOutCCurrent().getInstMag().getF()));
        nhmiValues45.addSignals(new NHMISignal("Ток Фаза А 4", lsvs4.getOutACurrent().getInstMag().getF()));
        nhmiValues45.addSignals(new NHMISignal("Ток Фаза B 4", lsvs4.getOutBCurrent().getInstMag().getF()));
        nhmiValues45.addSignals(new NHMISignal("Ток Фаза C 4", lsvs4.getOutCCurrent().getInstMag().getF()));
        nhmiValues45.addSignals(new NHMISignal("Ток Фаза А 5", lsvs5.getOutACurrent().getInstMag().getF()));
        nhmiValues45.addSignals(new NHMISignal("Ток Фаза B 5", lsvs5.getOutBCurrent().getInstMag().getF()));
        nhmiValues45.addSignals(new NHMISignal("Ток Фаза C 5", lsvs5.getOutCCurrent().getInstMag().getF()));



        nhmiFourierValues13.addSignals(new NHMISignal("Ф Ток Фаза А 1", mmxu1.getA().getPhsA().getCVal().getMag().getF()));
        nhmiFourierValues13.addSignals(new NHMISignal("Ф Ток Фаза B 1", mmxu1.getA().getPhsB().getCVal().getMag().getF()));
        nhmiFourierValues13.addSignals(new NHMISignal("Ф Ток Фаза C 1", mmxu1.getA().getPhsC().getCVal().getMag().getF()));
        nhmiFourierValues13.addSignals(new NHMISignal("Ф Ток Фаза А 2", mmxu2.getA().getPhsA().getCVal().getMag().getF()));
        nhmiFourierValues13.addSignals(new NHMISignal("Ф Ток Фаза B 2", mmxu2.getA().getPhsB().getCVal().getMag().getF()));
        nhmiFourierValues13.addSignals(new NHMISignal("Ф Ток Фаза C 2", mmxu2.getA().getPhsC().getCVal().getMag().getF()));
        nhmiFourierValues13.addSignals(new NHMISignal("Ф Ток Фаза А 3", mmxu3.getA().getPhsA().getCVal().getMag().getF()));
        nhmiFourierValues13.addSignals(new NHMISignal("Ф Ток Фаза B 3", mmxu3.getA().getPhsB().getCVal().getMag().getF()));
        nhmiFourierValues13.addSignals(new NHMISignal("Ф Ток Фаза C 3", mmxu3.getA().getPhsC().getCVal().getMag().getF()));
        nhmiFourierValues45.addSignals(new NHMISignal("Ф Ток Фаза А 4", mmxu4.getA().getPhsA().getCVal().getMag().getF()));
        nhmiFourierValues45.addSignals(new NHMISignal("Ф Ток Фаза B 4", mmxu4.getA().getPhsB().getCVal().getMag().getF()));
        nhmiFourierValues45.addSignals(new NHMISignal("Ф Ток Фаза C 4", mmxu4.getA().getPhsC().getCVal().getMag().getF()));
        nhmiFourierValues45.addSignals(new NHMISignal("Ф Ток Фаза А 5", mmxu5.getA().getPhsA().getCVal().getMag().getF()));
        nhmiFourierValues45.addSignals(new NHMISignal("Ф Ток Фаза B 5", mmxu5.getA().getPhsB().getCVal().getMag().getF()));
        nhmiFourierValues45.addSignals(new NHMISignal("Ф Ток Фаза C 5", mmxu5.getA().getPhsC().getCVal().getMag().getF()));
        nhmiFourierValues13.addSignals(new NHMISignal("Диф ток", rmxu.getDif().getPhsA().getCVal().getMag().getF()));
        nhmiFourierValues13.addSignals(new NHMISignal("Торм ток", rmxu.getRst().getPhsA().getCVal().getMag().getF()));
        harmonics1.addSignals(new NHMISignal("5 гармоника первого присоединения фаза А", mhai1.getHA().getPhsAH().get(4).getCVal().getMag().getF()));
        nhmipidf.addSignals(new NHMISignal("Срабатывание защиты с торм ", pdif.getOp().getGeneral()));
        nhmipidf.addSignals(new NHMISignal("Срабатывание дифф отсечки", ptoc.getOp().getGeneral()));
        harmonics1.addSignals(new NHMISignal("Блокировка по 5 гармонике " , phar1.getStr().getGeneral()));


        while (lsvs1.getIteratorDat().hasNext()) {
            lsvs1.process();
            lsvs2.process();
            lsvs3.process();
            lsvs4.process();
            lsvs5.process();
            mmxu1.process();
            mmxu2.process();
            mmxu3.process();
            mmxu4.process();
            mmxu5.process();
            mhai1.process();
            mhai2.process();
            mhai3.process();
            mhai4.process();
            mhai5.process();
            phar1.process();
            phar2.process();
            phar3.process();
            phar4.process();
            phar5.process();
            rmxu.process();
            pdif.process();
            ptoc.process();
            cswi1.process();
            cswi2.process();
            cswi3.process();
            cswi4.process();
            cswi5.process();
            xcbr1.process();
            xcbr2.process();
            xcbr3.process();
            xcbr4.process();
            xcbr5.process();

            nhmiValues13.process();
            nhmiValues45.process();
            nhmiFourierValues13.process();
            nhmiFourierValues45.process();
            harmonics1.process();
            nhmipidf.process();
        }
    }

}
