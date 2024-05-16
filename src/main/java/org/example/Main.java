package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.iec61850logicalNodes.protocol.*;
import org.example.iec61850logicalNodes.protocol.graphics.NHMI;
import org.example.iec61850logicalNodes.protocol.graphics.NHMISignal;

@Slf4j
public class Main {

    public static void main(String[] args) {
        LSVS lsvs = new LSVS();
        MMXU mmxuCurrent = new MMXU(lsvs.getOutACurrent(), lsvs.getOutBCurrent(), lsvs.getOutCCurrent());
        MMXU mmxuVoltage = new MMXU(lsvs.getOutAVoltage(), lsvs.getOutBVoltage(), lsvs.getOutCVoltage());
        MSQI msqi = new MSQI(mmxuCurrent.getA(), mmxuVoltage.getA());
        RDIR rdir = new RDIR(msqi.getSeqA(), msqi.getSeqV());

        PTOC ptocDir1 = new PTOC(msqi.getSeqA());
        ptocDir1.getStrVal().getSetMag().getF().setValue(650d);
        ptocDir1.getOpDlTmms().getSetVal().setValue(500d);
        ptocDir1.getOpDlTmms().getStepSize().setValue((double) lsvs.getOutACurrent().getT().getValue());
        ptocDir1.getOpCntRs().getTOpOk().setValue(100);

        PTOC ptocDir2 = new PTOC(msqi.getSeqA());
        ptocDir2.getStrVal().getSetMag().getF().setValue(650d);
        ptocDir2.getOpDlTmms().getSetVal().setValue(700d);
        ptocDir2.getOpDlTmms().getStepSize().setValue((double) lsvs.getOutACurrent().getT().getValue());
        ptocDir2.getOpCntRs().getTOpOk().setValue(100);

        PTOC ptocUnDir1 = new PTOC(msqi.getSeqA());
        ptocUnDir1.getStrVal().getSetMag().getF().setValue(650d);
        ptocUnDir1.getOpDlTmms().getSetVal().setValue(900d);
        ptocUnDir1.getOpDlTmms().getStepSize().setValue((double) lsvs.getOutACurrent().getT().getValue());
        ptocUnDir1.getOpCntRs().getTOpOk().setValue(100);

        PTOC ptocUnDir2 = new PTOC(msqi.getSeqA());
        ptocUnDir2.getStrVal().getSetMag().getF().setValue(650d);
        ptocUnDir2.getOpDlTmms().getSetVal().setValue(1100d);
        ptocUnDir2.getOpDlTmms().getStepSize().setValue((double) lsvs.getOutACurrent().getT().getValue());
        ptocUnDir2.getOpCntRs().getTOpOk().setValue(100);

        PTOC ptocUnDir3 = new PTOC(msqi.getSeqA());
        ptocUnDir3.getStrVal().getSetMag().getF().setValue(650d);
        ptocUnDir3.getOpDlTmms().getSetVal().setValue(1300d);
        ptocUnDir3.getOpDlTmms().getStepSize().setValue((double) lsvs.getOutACurrent().getT().getValue());
        ptocUnDir3.getOpCntRs().getTOpOk().setValue(100);

        CSWI cswi = new CSWI(ptocDir1.getStr(),
                ptocDir2.getStr(),
                ptocUnDir1.getOp(),
                ptocUnDir2.getOp(),
                ptocUnDir3.getOp());
        XCBR xcbr = new XCBR();
        xcbr.getOpCnt().getStVal().setValue(0);



        NHMI nhmiValues = new NHMI();
        NHMI nhmiFourierValues = new NHMI();
        NHMI nhmiSeqValues = new NHMI();
        nhmiValues.addSignals(new NHMISignal("Ток Фаза А", lsvs.getOutACurrent().getInstMag().getF()));
        nhmiValues.addSignals(new NHMISignal("Ток Фаза B", lsvs.getOutBCurrent().getInstMag().getF()));
        nhmiValues.addSignals(new NHMISignal("Ток Фаза C", lsvs.getOutCCurrent().getInstMag().getF()));
        nhmiValues.addSignals(new NHMISignal("Напр Фаза А", lsvs.getOutAVoltage().getInstMag().getF()));
        nhmiValues.addSignals(new NHMISignal("Напр Фаза B", lsvs.getOutBVoltage().getInstMag().getF()));
        nhmiValues.addSignals(new NHMISignal("Напр Фаза C", lsvs.getOutCVoltage().getInstMag().getF()));

        nhmiFourierValues.addSignals(new NHMISignal("Ток Фаза А действ и уставка", mmxuCurrent.getA().getPhsA().getCVal().getMag().getF())
               ,new NHMISignal("Фаза тока А", mmxuCurrent.getA().getPhsA().getCVal().getAng().getF()));
        nhmiFourierValues.addSignals(new NHMISignal("Ток Фаза B действ и уставка", mmxuCurrent.getA().getPhsB().getCVal().getMag().getF()),
                new NHMISignal("Фаза тока B", mmxuCurrent.getA().getPhsB().getCVal().getAng().getF()));
        nhmiFourierValues.addSignals(new NHMISignal("Ток Фаза C действ и уставка", mmxuCurrent.getA().getPhsC().getCVal().getMag().getF()),
                new NHMISignal("Фаза тока C", mmxuCurrent.getA().getPhsC().getCVal().getAng().getF()));

        nhmiFourierValues.addSignals(new NHMISignal("Напр Фаза А действ и уставка", mmxuVoltage.getA().getPhsA().getCVal().getMag().getF()));
        nhmiFourierValues.addSignals(new NHMISignal("Напр Фаза B действ и уставка", mmxuVoltage.getA().getPhsB().getCVal().getMag().getF()));
        nhmiFourierValues.addSignals(new NHMISignal("Напр Фаза C действ и уставка", mmxuVoltage.getA().getPhsC().getCVal().getMag().getF()));

        nhmiSeqValues.addSignals(new NHMISignal("ПП тока", msqi.getSeqA().getC1().getCVal().getMag().getF()));
        nhmiSeqValues.addSignals(new NHMISignal("ОП тока", msqi.getSeqA().getC2().getCVal().getMag().getF()));
        nhmiSeqValues.addSignals(new NHMISignal("НП тока", msqi.getSeqA().getC3().getCVal().getMag().getF()),
                new NHMISignal("Уставка", ptocDir1.getStrVal().getSetMag().getF()));
        nhmiSeqValues.addSignals(new NHMISignal("ПП Напряжения", msqi.getSeqV().getC1().getCVal().getMag().getF()));
        nhmiSeqValues.addSignals(new NHMISignal("ОП Напряжения", msqi.getSeqV().getC2().getCVal().getMag().getF()));
        nhmiSeqValues.addSignals(new NHMISignal("НП Напряжения", msqi.getSeqV().getC3().getCVal().getMag().getF()));

        NHMI nhmiSrab = new NHMI();
        nhmiSrab.addSignals(new NHMISignal("Срабатывание 1 напр ступ", ptocDir1.getPusk().getGeneral()),
             new NHMISignal("Пуск 1 напр ступ", ptocDir1.getStr().getGeneral()));
        nhmiSrab.addSignals(new NHMISignal("Срабатывание 2 напр ступ", ptocDir2.getPusk().getGeneral()),
                new NHMISignal("Пуск 2 напр ступ", ptocDir2.getStr().getGeneral()));
        nhmiSrab.addSignals(new NHMISignal("Срабатывание 1 ненапр ступ", ptocUnDir1.getPusk().getGeneral()),
                new NHMISignal("Пуск 1 ненапр ступ", ptocUnDir1.getOp().getGeneral()));
        nhmiSrab.addSignals(new NHMISignal("Срабатывание 2 ненапр ступ", ptocUnDir2.getPusk().getGeneral()),
                new NHMISignal("Пуск 2 ненапр ступ", ptocUnDir2.getOp().getGeneral()));
        nhmiSrab.addSignals(new NHMISignal("Срабатывание 3 ненапр ступ", ptocUnDir3.getPusk().getGeneral()),
                new NHMISignal("Пуск 3 ненапр ступ", ptocUnDir3.getOp().getGeneral()));

        nhmiSrab.addSignals(new NHMISignal("Сигнал в АУВ от 1 напр ступ", cswi.getOpOpnDir1().getGeneral()));
        nhmiSrab.addSignals(new NHMISignal("Сигнал в АУВ от 2 напр ступ", cswi.getOpOpnDir2().getGeneral()));
        nhmiSrab.addSignals(new NHMISignal("Сигнал в АУВ от 1 ненапр ступ", cswi.getOpOpnUnDir1().getGeneral()));
        nhmiSrab.addSignals(new NHMISignal("Сигнал в АУВ от 2 ненапр ступ", cswi.getOpOpnUnDir2().getGeneral()));
        nhmiSrab.addSignals(new NHMISignal("Сигнал в АУВ от 3 ненапр ступ", cswi.getOpOpnUnDir3().getGeneral()));


        while (lsvs.getIteratorDat().hasNext()) {
            lsvs.process();
            mmxuCurrent.process();
            mmxuVoltage.process();
            msqi.process();
            rdir.process();
            ptocDir1.process();
            ptocDir1.getDirMod().getSetVal().setValue(rdir.getDir().getDirGeneral().getValue());
            ptocDir2.process();
            ptocDir2.getDirMod().getSetVal().setValue(rdir.getDir().getDirGeneral().getValue());
            ptocUnDir1.process();
            ptocUnDir2.process();
            ptocUnDir3.process();
            cswi.process();
            xcbr.getPos().getStVal().setValue(cswi.getPos().getStVal().getValue());
            xcbr.process();

            nhmiValues.process();
            nhmiFourierValues.process();
            nhmiSeqValues.process();
            nhmiSrab.process();

        }
    }
}
