package org.example;


import lombok.extern.slf4j.Slf4j;
import org.example.iec61850logicalNodes.protocol.*;
import org.example.iec61850logicalNodes.protocol.graphics.NHMI;
import org.example.iec61850logicalNodes.protocol.graphics.NHMISignal;



@Slf4j
public class Main {

    public static void main(String[] args) {
        LSVS lsvs1 = new LSVS(1);
//        LSVS lsvs2 = new LSVS(2);
        int discFreq = lsvs1.getDiscFreq();
        MMXU mmxu1 = new MMXU(
                lsvs1.getOutACurrent(),
                lsvs1.getOutBCurrent(),
                lsvs1.getOutCCurrent(),
                discFreq
        );
//        MMXU mmxu2 = new MMXU(
//                lsvs2.getOutACurrent(),
//                lsvs2.getOutBCurrent(),
//                lsvs2.getOutCCurrent(),
//                discFreq
//        );
//        RMXU rmxu = new RMXU();
//        rmxu.setALoc1(mmxu1.getA());
//        rmxu.setALoc2(mmxu2.getA());



        NHMI nhmiValues = new NHMI();
        NHMI nhmiFourierValues = new NHMI();
        nhmiValues.addSignals(new NHMISignal("Ток Фаза А 1", lsvs1.getOutACurrent().getInstMag().getF()));
        nhmiValues.addSignals(new NHMISignal("Ток Фаза B 1", lsvs1.getOutBCurrent().getInstMag().getF()));
        nhmiValues.addSignals(new NHMISignal("Ток Фаза C 1", lsvs1.getOutCCurrent().getInstMag().getF()));
//        nhmiValues.addSignals(new NHMISignal("Ток Фаза А 2", lsvs2.getOutACurrent().getInstMag().getF()));
//        nhmiValues.addSignals(new NHMISignal("Ток Фаза B 2", lsvs2.getOutBCurrent().getInstMag().getF()));
//        nhmiValues.addSignals(new NHMISignal("Ток Фаза C 2", lsvs2.getOutCCurrent().getInstMag().getF()));

        nhmiFourierValues.addSignals(new NHMISignal("Ф Ток Фаза А 1", mmxu1.getA().getPhsA().getCVal().getMag().getF()));
        nhmiFourierValues.addSignals(new NHMISignal("Ф Ток Фаза B 1", mmxu1.getA().getPhsB().getCVal().getMag().getF()));
        nhmiFourierValues.addSignals(new NHMISignal("Ф Ток Фаза C 1", mmxu1.getA().getPhsC().getCVal().getMag().getF()));
//        nhmiFourierValues.addSignals(new NHMISignal("Ф Ток Фаза А 2", mmxu2.getA().getPhsA().getCVal().getMag().getF()));
//        nhmiFourierValues.addSignals(new NHMISignal("Ф Ток Фаза B 2", mmxu2.getA().getPhsB().getCVal().getMag().getF()));
//        nhmiFourierValues.addSignals(new NHMISignal("Ф Ток Фаза C 2", mmxu2.getA().getPhsC().getCVal().getMag().getF()));
//        nhmiFourierValues.addSignals(new NHMISignal("Диф ток", rmxu.getDif().getPhsA().getCVal().getMag().getF()));


        while (lsvs1.getIteratorDat().hasNext()) {
            lsvs1.process();
//            lsvs2.process();
            mmxu1.process();
//            mmxu2.process();
//            rmxu.process();
            nhmiValues.process();
            nhmiFourierValues.process();
        }
    }
//    private static List<NHMIPoint<Double, Double>> makeCharacteristic(double x0, double y0, double r){
//        List<NHMIPoint<Double, Double>> pointsList = new ArrayList<>();
//        for(double x= -2*r; x<= 2*r; x += 0.1) {
//            double y = Math.sqrt(Math.pow(r, 2) - Math.pow((x-x0), 2)) + y0;
//            pointsList.add(new NHMIPoint<>(x, y));
//            pointsList.add(new NHMIPoint<>(x, -y));
//        }
//        return pointsList;
//    }
//    private static List<NHMIPoint<Double, Double>> makeCharDirection(double r, double fi){
//        List<NHMIPoint<Double, Double>> pointsList = new ArrayList<>();
//        for(double x = -r; x<= r; x += 0.1) {
//            double y = x * Math.tan(fi);
//            pointsList.add(new NHMIPoint<>(x, y));
//        }
//        return pointsList;
//    }
}
