package org.example;

import javassist.expr.Instanceof;
import lombok.extern.slf4j.Slf4j;
import org.example.iec61850datatypes.common.Attribute;
import org.example.iec61850logicalNodes.protocol.*;
import org.example.iec61850logicalNodes.protocol.graphics.NHMI;
import org.example.iec61850logicalNodes.protocol.graphics.NHMIP;
import org.example.iec61850logicalNodes.protocol.graphics.NHMIPoint;
import org.example.iec61850logicalNodes.protocol.graphics.NHMISignal;

import java.util.ArrayList;
import java.util.List;

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
        MSQI msqi = new MSQI(mmxu.getA(), mmxu.getPhV());
        RDIR rdir = new RDIR();
        rdir.setZ(mmxu.getZ());
        rdir.getChrAng().getSetMag().getF().setValue(80d);




        PDIS pdisUnDir1 = new PDIS();
        pdisUnDir1.getOpDlTmms().getSetVal().setValue(100d);
        pdisUnDir1.setZ(mmxu.getZ());
        pdisUnDir1.getPhStr().getSetMag().getF().setValue(85d);


        PDIS pdisUnDir2 = new PDIS();
        pdisUnDir2.getOpDlTmms().getSetVal().setValue(500d);
        pdisUnDir2.setZ(mmxu.getZ());
        pdisUnDir2.getPhStr().getSetMag().getF().setValue(130d);



        PDIS pdisUnDir3 = new PDIS();
        pdisUnDir3.getOpDlTmms().getSetVal().setValue(1000d);
        pdisUnDir3.setZ(mmxu.getZ());
        pdisUnDir3.getPhStr().getSetMag().getF().setValue(200d);



        PDIS pdisDir1 = new PDIS();
        pdisDir1.getOpDlTmms().getSetVal().setValue(100d);
        pdisDir1.setZ(mmxu.getZ());
        pdisDir1.getPhStr().getSetMag().getF().setValue(79d);


        PDIS pdisDir2 = new PDIS();
        pdisDir2.getOpDlTmms().getSetVal().setValue(500d);
        pdisDir2.setZ(mmxu.getZ());
        pdisDir2.getPhStr().getSetMag().getF().setValue(109d);



        PDIS pdisDir3 = new PDIS();
        pdisDir3.getOpDlTmms().getSetVal().setValue(1000d);
        pdisDir3.setZ(mmxu.getZ());
        pdisDir3.getPhStr().getSetMag().getF().setValue(200d);

        RPSB rpsb = new RPSB();
        rpsb.setSeqA(msqi.getSeqA());
        rpsb.setOpDlTmmsLast(pdisDir3.getOpDlTmms());




        CSWI cswi = new CSWI(
                pdisDir1.getOp(),
                pdisDir2.getOp(),
                pdisDir3.getOp(),
                pdisUnDir1.getOp(),
                pdisUnDir2.getOp(),
                pdisUnDir3.getOp()
        );

        XCBR xcbr = new XCBR();
        xcbr.setPos(cswi.getPos());



        NHMI nhmiValues = new NHMI();
        NHMI nhmiFourierValues = new NHMI();
        NHMI nhmiFourierValuesLine = new NHMI();
        NHMI nhmiPdis = new NHMI();
        NHMI nhmiSeq = new NHMI();
        NHMI nhmiZ = new NHMI();
        NHMI nhmiDistance = new NHMI();
        NHMI nhmiPdisDir = new NHMI();
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
        nhmiDistance.addSignals(new NHMISignal("Расстояние между точкой и радиусом окружности 1 ступень AB",
               pdisDir1.getPointAndRadius().getPhsA().getCVal().getMag().getF() ));
        nhmiDistance.addSignals(new NHMISignal("Расстояние между точкой и радиусом окружности 1 ступень  BC",
                pdisDir1.getPointAndRadius().getPhsB().getCVal().getMag().getF() ));
        nhmiDistance.addSignals(new NHMISignal("Расстояние между точкой и радиусом окружности 1 ступень AC",
                pdisDir1.getPointAndRadius().getPhsC().getCVal().getMag().getF() ));

        nhmiZ.addSignals(new NHMISignal("Л Акт Сопр AB", mmxu.getZ().getPhsA().getCVal().getRe().getF() ));
        nhmiZ.addSignals(new NHMISignal("Л Акт Сопр BC", mmxu.getZ().getPhsA().getCVal().getRe().getF() ));
        nhmiZ.addSignals(new NHMISignal("Л Акт Сопр AC", mmxu.getZ().getPhsA().getCVal().getRe().getF() ));
        nhmiZ.addSignals(new NHMISignal("Л Реакт Сопр AB", mmxu.getZ().getPhsA().getCVal().getIm().getF() ));
        nhmiZ.addSignals(new NHMISignal("Л Реакт Сопр BC", mmxu.getZ().getPhsB().getCVal().getIm().getF() ));
        nhmiZ.addSignals(new NHMISignal("Л Реакт Сопр AC", mmxu.getZ().getPhsC().getCVal().getIm().getF() ));

        nhmiPdis.addSignals(new NHMISignal("Блокировка ", rpsb.getBlkZn().getStVal()));
        nhmiPdisDir.addSignals(new NHMISignal("Напр 1", pdisDir1.getOp().getGeneral() ), new NHMISignal(" Сиг 1", pdisDir1.getStr().getGeneral()));
        nhmiPdisDir.addSignals(new NHMISignal("Напр 2", pdisDir2.getOp().getGeneral() ), new NHMISignal(" Сиг 2", pdisDir2.getStr().getGeneral()));
        nhmiPdisDir.addSignals(new NHMISignal("Напр 3", pdisDir3.getOp().getGeneral() ), new NHMISignal(" Сиг 3", pdisDir3.getStr().getGeneral()));
        nhmiPdis.addSignals(new NHMISignal("Ненапр 1", pdisUnDir1.getOp().getGeneral() ), new NHMISignal(" Сиг 1", pdisUnDir1.getStr().getGeneral()));
        nhmiPdis.addSignals(new NHMISignal("Ненапр 2", pdisUnDir2.getOp().getGeneral() ), new NHMISignal(" Сиг 2", pdisUnDir2.getStr().getGeneral()));
        nhmiPdis.addSignals(new NHMISignal("Ненапр 3", pdisUnDir3.getOp().getGeneral() ), new NHMISignal(" Сиг 3", pdisUnDir3.getStr().getGeneral()));

        nhmiSeq.addSignals(new NHMISignal("ОП Напряжения", msqi.getSeqV().getC2().getCVal().getMag().getF()));
        nhmiSeq.addSignals(new NHMISignal("ОП Тока", msqi.getSeqA().getC2().getCVal().getMag().getF()));





        NHMIP nhmip = new NHMIP();
        nhmip.drawCharacteristic("Характеристика срабатывания 1",
                makeCharacteristic(0,0,pdisDir1.getPhStr().getSetMag().getF().getValue()));
        nhmip.drawCharacteristic("",
                makeCharDirection(pdisDir1.getPhStr().getSetMag().getF().getValue(), (rdir.getChrAng().getSetMag().getF().getValue() - 100) * Math.PI/180));
        nhmip.drawCharacteristic("Характеристика срабатывания 2",
                makeCharacteristic(0,0,pdisDir2.getPhStr().getSetMag().getF().getValue()));
        nhmip.drawCharacteristic("",
                makeCharDirection(pdisDir2.getPhStr().getSetMag().getF().getValue(), (rdir.getChrAng().getSetMag().getF().getValue() - 100) * Math.PI/180));
        nhmip.drawCharacteristic("Характеристика срабатывания 3",
                makeCharacteristic(0,0,pdisDir3.getPhStr().getSetMag().getF().getValue()));
        nhmip.drawCharacteristic("",
                makeCharDirection(pdisDir3.getPhStr().getSetMag().getF().getValue(), (rdir.getChrAng().getSetMag().getF().getValue() - 100) * Math.PI/180));

        mmxu.getZ().getPhsA().getCVal().getRe().getF().setValue(0d);
        mmxu.getZ().getPhsA().getCVal().getIm().getF().setValue(0d);
        mmxu.getZ().getPhsB().getCVal().getRe().getF().setValue(0d);
        mmxu.getZ().getPhsB().getCVal().getIm().getF().setValue(0d);
        mmxu.getZ().getPhsC().getCVal().getRe().getF().setValue(0d);
        mmxu.getZ().getPhsC().getCVal().getIm().getF().setValue(0d);
        nhmip.addSignals(new NHMISignal("ZAB", mmxu.getZ().getPhsA().getCVal().getRe().getF(), mmxu.getZ().getPhsA().getCVal().getIm().getF() ));
        nhmip.addSignals(new NHMISignal("ZBC", mmxu.getZ().getPhsB().getCVal().getRe().getF(), mmxu.getZ().getPhsB().getCVal().getIm().getF() ));
        nhmip.addSignals(new NHMISignal("ZAC", mmxu.getZ().getPhsC().getCVal().getRe().getF(), mmxu.getZ().getPhsC().getCVal().getIm().getF() ));




        while (lsvs.getIteratorDat().hasNext()) {
            lsvs.process();
            mmxu.process();
            msqi.process();
            rpsb.process();
            rdir.process();

            pdisDir1.getDirMod().getSetVal().setValue(rdir.getDir().getDirGeneral().getValue());
            pdisDir1.getBlk().getGeneral().setValue(rpsb.getBlkZn().getStVal().getValue());
            pdisDir1.process();

            pdisDir2.getDirMod().getSetVal().setValue(rdir.getDir().getDirGeneral().getValue());
            pdisDir2.getBlk().getGeneral().setValue(rpsb.getBlkZn().getStVal().getValue());
            pdisDir2.process();

            pdisDir3.getDirMod().getSetVal().setValue(rdir.getDir().getDirGeneral().getValue());
            pdisDir3.getBlk().getGeneral().setValue(rpsb.getBlkZn().getStVal().getValue());
            pdisDir3.process();


            pdisUnDir1.getBlk().getGeneral().setValue(rpsb.getBlkZn().getStVal().getValue());
            pdisUnDir1.process();


            pdisUnDir2.getBlk().getGeneral().setValue(rpsb.getBlkZn().getStVal().getValue());
            pdisUnDir2.process();


            pdisUnDir3.getBlk().getGeneral().setValue(rpsb.getBlkZn().getStVal().getValue());
            pdisUnDir3.process();







            cswi.process();
            xcbr.getPos().getStVal().setValue(cswi.getPos().getStVal().getValue());
//            xcbr.process();

            nhmiValues.process();
            nhmiDistance.process();
            nhmiPdis.process();
            nhmiPdisDir.process();
            nhmiSeq.process();
            nhmiFourierValues.process();
            nhmiFourierValuesLine.process();
            nhmip.process();
            nhmiZ.process();



        }
    }
    private static List<NHMIPoint<Double, Double>> makeCharacteristic(double x0, double y0, double r){
        List<NHMIPoint<Double, Double>> pointsList = new ArrayList<>();
        for(double x= -2*r; x<= 2*r; x += 0.1) {
            double y = Math.sqrt(Math.pow(r, 2) - Math.pow((x-x0), 2)) + y0;
            pointsList.add(new NHMIPoint<>(x, y));
            pointsList.add(new NHMIPoint<>(x, -y));
        }
        return pointsList;
    }
    private static List<NHMIPoint<Double, Double>> makeCharDirection(double r, double fi){
        List<NHMIPoint<Double, Double>> pointsList = new ArrayList<>();
        for(double x = -r; x<= r; x += 0.1) {
            double y = x * Math.tan(fi);
            pointsList.add(new NHMIPoint<>(x, y));
        }
        return pointsList;
    }
}
