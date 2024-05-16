package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.iec61850logicalNodes.protocol.*;
import org.example.iec61850logicalNodes.protocol.graphics.NHMI;
import org.example.iec61850logicalNodes.protocol.graphics.NHMISignal;

@Slf4j
public class Main {

    public static void main(String[] args) {
        LSVS lsvs = new LSVS();
        MMXU mmxu = new MMXU(lsvs.getOutACurrent(),
                lsvs.getOutBCurrent(),
                lsvs.getOutCCurrent(),
                lsvs.getOutAVoltage(),
                lsvs.getOutBVoltage(),
                lsvs.getOutCVoltage());
        PDIS pdis = new PDIS();
        pdis.setZ(mmxu.getZ());
        pdis.getPhStr().getSetMag().getF().setValue(200.2414d);
        pdis.getOpDlTmms().getSetVal().setValue(10d);







        NHMI nhmiValues = new NHMI();
        NHMI nhmiFourierValues = new NHMI();
        NHMI nhmiFourierValuesLine = new NHMI();
        NHMI nhmiPdis = new NHMI();
        nhmiValues.addSignals(new NHMISignal("Ток Фаза А", lsvs.getOutACurrent().getInstMag().getF()));
        nhmiValues.addSignals(new NHMISignal("Ток Фаза B", lsvs.getOutBCurrent().getInstMag().getF()));
        nhmiValues.addSignals(new NHMISignal("Ток Фаза C", lsvs.getOutCCurrent().getInstMag().getF()));
        nhmiValues.addSignals(new NHMISignal("Напр Фаза А", lsvs.getOutAVoltage().getInstMag().getF()));
        nhmiValues.addSignals(new NHMISignal("Напр Фаза B", lsvs.getOutBVoltage().getInstMag().getF()));
        nhmiValues.addSignals(new NHMISignal("Напр Фаза C", lsvs.getOutCVoltage().getInstMag().getF()));

        nhmiFourierValues.addSignals(new NHMISignal("Ф Ток Фаза А", mmxu.getA().getPhsA().getCVal().getMag().getF()));
        nhmiFourierValues.addSignals(new NHMISignal("Ф Ток Фаза B", mmxu.getA().getPhsB().getCVal().getMag().getF()));
        nhmiFourierValues.addSignals(new NHMISignal("Ф Ток Фаза C", mmxu.getA().getPhsC().getCVal().getMag().getF()));
        nhmiFourierValues.addSignals(new NHMISignal("Ф Напряжение Фаза А", mmxu.getPhV().getPhsA().getCVal().getMag().getF()));
        nhmiFourierValues.addSignals(new NHMISignal("Ф Напряжение Фаза B", mmxu.getPhV().getPhsB().getCVal().getMag().getF()));
        nhmiFourierValues.addSignals(new NHMISignal("Ф Напряжение Фаза C", mmxu.getPhV().getPhsC().getCVal().getMag().getF()));

        nhmiFourierValuesLine.addSignals(new NHMISignal("Л Напряжение AB", mmxu.getPPV().getPhsAB().getCVal().getMag().getF() ));
        nhmiFourierValuesLine.addSignals(new NHMISignal("Л Напряжение BC", mmxu.getPPV().getPhsBC().getCVal().getMag().getF() ));
        nhmiFourierValuesLine.addSignals(new NHMISignal("Л Напряжение AC", mmxu.getPPV().getPhsAC().getCVal().getMag().getF() ));

        nhmiFourierValuesLine.addSignals(new NHMISignal("Л Ток AB", mmxu.getPPA().getPhsAB().getCVal().getMag().getF() ));
        nhmiFourierValuesLine.addSignals(new NHMISignal("Л Ток BC", mmxu.getPPA().getPhsBC().getCVal().getMag().getF() ));
        nhmiFourierValuesLine.addSignals(new NHMISignal("Л Ток AC", mmxu.getPPA().getPhsAC().getCVal().getMag().getF() ));

        nhmiFourierValuesLine.addSignals(new NHMISignal("Л Сопр AB", mmxu.getZ().getPhsA().getCVal().getMag().getF() ));
        nhmiFourierValuesLine.addSignals(new NHMISignal("Л Сопр BC", mmxu.getZ().getPhsB().getCVal().getMag().getF() ));
        nhmiFourierValuesLine.addSignals(new NHMISignal("Л Сопр AC", mmxu.getZ().getPhsC().getCVal().getMag().getF() ));

        nhmiPdis.addSignals(new NHMISignal("Реле сопротивления", pdis.getOp().getGeneral() ));
//
//        nhmiFourierValues.addSignals(new NHMISignal("Напр Фаза А действ и уставка", mmxuVoltage.getA().getPhsA().getCVal().getMag().getF()));
//        nhmiFourierValues.addSignals(new NHMISignal("Напр Фаза B действ и уставка", mmxuVoltage.getA().getPhsB().getCVal().getMag().getF()));
//        nhmiFourierValues.addSignals(new NHMISignal("Напр Фаза C действ и уставка", mmxuVoltage.getA().getPhsC().getCVal().getMag().getF()));
//
//        nhmiSeqValues.addSignals(new NHMISignal("ПП тока", msqi.getSeqA().getC1().getCVal().getMag().getF()));
//        nhmiSeqValues.addSignals(new NHMISignal("ОП тока", msqi.getSeqA().getC2().getCVal().getMag().getF()));
//        nhmiSeqValues.addSignals(new NHMISignal("НП тока", msqi.getSeqA().getC3().getCVal().getMag().getF()),
//                new NHMISignal("Уставка", ptocDir1.getStrVal().getSetMag().getF()));
//        nhmiSeqValues.addSignals(new NHMISignal("ПП Напряжения", msqi.getSeqV().getC1().getCVal().getMag().getF()));
//        nhmiSeqValues.addSignals(new NHMISignal("ОП Напряжения", msqi.getSeqV().getC2().getCVal().getMag().getF()));
//        nhmiSeqValues.addSignals(new NHMISignal("НП Напряжения", msqi.getSeqV().getC3().getCVal().getMag().getF()));

//        NHMI nhmiSrab = new NHMI();
//        nhmiSrab.addSignals(new NHMISignal("Срабатывание 1 напр ступ", ptocDir1.getPusk().getGeneral()),
//             new NHMISignal("Пуск 1 напр ступ", ptocDir1.getStr().getGeneral()));
//        nhmiSrab.addSignals(new NHMISignal("Срабатывание 2 напр ступ", ptocDir2.getPusk().getGeneral()),
//                new NHMISignal("Пуск 2 напр ступ", ptocDir2.getStr().getGeneral()));
//        nhmiSrab.addSignals(new NHMISignal("Срабатывание 1 ненапр ступ", ptocUnDir1.getPusk().getGeneral()),
//                new NHMISignal("Пуск 1 ненапр ступ", ptocUnDir1.getOp().getGeneral()));
//        nhmiSrab.addSignals(new NHMISignal("Срабатывание 2 ненапр ступ", ptocUnDir2.getPusk().getGeneral()),
//                new NHMISignal("Пуск 2 ненапр ступ", ptocUnDir2.getOp().getGeneral()));
//        nhmiSrab.addSignals(new NHMISignal("Срабатывание 3 ненапр ступ", ptocUnDir3.getPusk().getGeneral()),
//                new NHMISignal("Пуск 3 ненапр ступ", ptocUnDir3.getOp().getGeneral()));

//        nhmiSrab.addSignals(new NHMISignal("Сигнал в АУВ от 1 напр ступ", cswi.getOpOpnDir1().getGeneral()));



        while (lsvs.getIteratorDat().hasNext()) {
            lsvs.process();
            mmxu.process();
            pdis.process();

//            rdir.process();


//            cswi.process();
//            xcbr.getPos().getStVal().setValue(cswi.getPos().getStVal().getValue());
//            xcbr.process();

            nhmiValues.process();
            nhmiFourierValues.process();
            nhmiFourierValuesLine.process();
            nhmiPdis.process();
//            nhmiSeqValues.process();
//            nhmiSrab.process();

        }
    }
}
