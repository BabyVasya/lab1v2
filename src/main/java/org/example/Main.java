package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.Xml.ReadXmlCfg;
import org.example.iec61850logicalNodes.protocol.graphics.NHMI;
import org.example.iec61850logicalNodes.protocol.graphics.NHMISignal;

@Slf4j
public class Main {

    public static void main(String[] args) {
        NodesFactory nodesFactory = ReadXmlCfg.readConfig();


        NHMI nhmiCurrents = new NHMI();
        nhmiCurrents.addSignals(new NHMISignal("Фаза А", nodesFactory.getLsvs().getOutA().getInstMag().getF()));
        nhmiCurrents.addSignals(new NHMISignal("Фаза B", nodesFactory.getLsvs().getOutB().getInstMag().getF()));
        nhmiCurrents.addSignals(new NHMISignal("Фаза C", nodesFactory.getLsvs().getOutC().getInstMag().getF()));
        nhmiCurrents.addSignals(new NHMISignal("Фаза А действ и уставка", nodesFactory.getMmxu().getA().getPhsA().getCVal().getMag().getF()),
                                                                              new NHMISignal("",nodesFactory.getPtoc().get(0).getStrVal().getSetMag().getF()));
        nhmiCurrents.addSignals(new NHMISignal("Фаза B действ и уставка", nodesFactory.getMmxu().getA().getPhsB().getCVal().getMag().getF()),
                                                                              new NHMISignal("",nodesFactory.getPtoc().get(0).getStrVal().getSetMag().getF()));
        nhmiCurrents.addSignals(new NHMISignal("Фаза C действ и уставка", nodesFactory.getMmxu().getA().getPhsC().getCVal().getMag().getF()),
                                                                              new NHMISignal("",nodesFactory.getPtoc().get(0).getStrVal().getSetMag().getF()));
        NHMI nhmiRelay1 = new NHMI();
        NHMI nhmiRelay2 = new NHMI();
        NHMI nhmiRelay3 = new NHMI();
        nhmiRelay1.addSignals(new NHMISignal("Срабатывание фазы А - 1", nodesFactory.getPtoc().get(0).getStr().getPhsA()),
                new NHMISignal("A", nodesFactory.getPtoc().get(0).getOp().getPhsA()));
        nhmiRelay1.addSignals(new NHMISignal("Срабатывание фазы B - 1", nodesFactory.getPtoc().get(0).getStr().getPhsB()),
                new NHMISignal("B", nodesFactory.getPtoc().get(0).getOp().getPhsB()));
        nhmiRelay1.addSignals(new NHMISignal("Срабатывание фазы C - 1", nodesFactory.getPtoc().get(0).getStr().getPhsC()),
                new NHMISignal("C", nodesFactory.getPtoc().get(0).getOp().getPhsC()));
        nhmiRelay1.addSignals(new NHMISignal("Срабатывание защиты - 1", nodesFactory.getPtoc().get(0).getStr().getGeneral()),
                new NHMISignal("G", nodesFactory.getPtoc().get(0).getOp().getGeneral()));
        nhmiRelay2.addSignals(new NHMISignal("Срабатывание фазы А - 2", nodesFactory.getPtoc().get(1).getStr().getPhsA()),
                new NHMISignal("A", nodesFactory.getPtoc().get(1).getOp().getPhsA()));
        nhmiRelay2.addSignals(new NHMISignal("Срабатывание фазы B - 2", nodesFactory.getPtoc().get(1).getStr().getPhsB()),
                new NHMISignal("B", nodesFactory.getPtoc().get(1).getOp().getPhsB()));
        nhmiRelay2.addSignals(new NHMISignal("Срабатывание фазы C - 2", nodesFactory.getPtoc().get(1).getStr().getPhsC()),
                new NHMISignal("C", nodesFactory.getPtoc().get(1).getOp().getPhsC()));
        nhmiRelay2.addSignals(new NHMISignal("Срабатывание защиты - 2", nodesFactory.getPtoc().get(1).getStr().getGeneral()),
                new NHMISignal("G", nodesFactory.getPtoc().get(1).getOp().getGeneral()));
        nhmiRelay3.addSignals(new NHMISignal("Срабатывание фазы А - 3", nodesFactory.getPtoc().get(2).getStr().getPhsA()),
                new NHMISignal("A", nodesFactory.getPtoc().get(2).getOp().getPhsA()));
        nhmiRelay3.addSignals(new NHMISignal("Срабатывание фазы B - 3", nodesFactory.getPtoc().get(2).getStr().getPhsB()),
                new NHMISignal("B", nodesFactory.getPtoc().get(2).getOp().getPhsB()));
        nhmiRelay3.addSignals(new NHMISignal("Срабатывание фазы C - 3", nodesFactory.getPtoc().get(2).getStr().getPhsC()),
                new NHMISignal("C", nodesFactory.getPtoc().get(2).getOp().getPhsC()));
        nhmiRelay3.addSignals(new NHMISignal("Срабатывание защиты - 3", nodesFactory.getPtoc().get(2).getStr().getGeneral()),
                new NHMISignal("G", nodesFactory.getPtoc().get(2).getOp().getGeneral()));

        while (nodesFactory.getLsvs().hasNext()) {
            nodesFactory.getLsvs().process();
            nodesFactory.getMmxu().process();
            nodesFactory.getPtoc().get(0).process();
            nodesFactory.getPtoc().get(1).process();
            nodesFactory.getPtoc().get(2).process();
            nhmiCurrents.process();
            nhmiRelay1.process();
            nhmiRelay2.process();
            nhmiRelay3.process();
            nodesFactory.getCswi().process();
            nodesFactory.getXcbr().process();
        }
    }
}
