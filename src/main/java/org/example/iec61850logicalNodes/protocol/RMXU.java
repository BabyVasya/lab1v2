package org.example.iec61850logicalNodes.protocol;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.iec61850datatypes.measurements.CMV;
import org.example.iec61850datatypes.measurements.SAV;
import org.example.iec61850datatypes.measurements.WYE;
import org.example.iec61850logicalNodes.common.LN;

@Data
@Slf4j
public class RMXU extends LN {
    public WYE ALoc1 = new WYE();
    public WYE ALoc2 = new WYE();
    public WYE ALoc3 = new WYE();
    public WYE ALoc4 = new WYE();
    public WYE ALoc5 = new WYE();
    private SAV AmpLocPhsA = new SAV();
    private SAV AmpLocPhsB = new SAV();
    private SAV AmpLocPhsC = new SAV();
    private SAV AmpLocRes = new SAV();

    private WYE dif = new WYE();
    private WYE rst = new WYE();

    @Override
    public void process() {
        rst.getPhsA().getCVal().getMag().getF().setValue(
                rstCurrentCalc(ALoc1.getPhsA(),ALoc2.getPhsA(),ALoc3.getPhsA(),ALoc4.getPhsA(),ALoc5.getPhsA() )
        );
        rst.getPhsB().getCVal().getMag().getF().setValue(
                rstCurrentCalc(ALoc1.getPhsB(),ALoc2.getPhsB(),ALoc3.getPhsB(),ALoc4.getPhsB(),ALoc5.getPhsB() )
        );
        rst.getPhsC().getCVal().getMag().getF().setValue(
                rstCurrentCalc(ALoc1.getPhsC(),ALoc2.getPhsC(),ALoc3.getPhsC(),ALoc4.getPhsC(),ALoc5.getPhsC() )
        );
        dif.getPhsA().getCVal().getMag().getF().setValue(
                difCurrentCalc(ALoc1.getPhsA(),ALoc2.getPhsA(),ALoc3.getPhsA(),ALoc4.getPhsA(),ALoc5.getPhsA() )
        );
        dif.getPhsB().getCVal().getMag().getF().setValue(
                difCurrentCalc(ALoc1.getPhsB(),ALoc2.getPhsB(),ALoc3.getPhsB(),ALoc4.getPhsB(),ALoc5.getPhsB() )
        );
        dif.getPhsC().getCVal().getMag().getF().setValue(
                difCurrentCalc(ALoc1.getPhsC(),ALoc2.getPhsC(),ALoc3.getPhsC(),ALoc4.getPhsC(),ALoc5.getPhsC() )
        );
//        log.info("Торм " + String.valueOf(rst.getPhsA().getCVal().getMag().getF().getValue()));
//        log.info("Диф " + String.valueOf(dif.getPhsA().getCVal().getMag().getF().getValue()));
    }

    private double rstCurrentCalc(CMV ALoc1 , CMV ALoc2 , CMV ALoc3 , CMV ALoc4 , CMV ALoc5) {
        double abs1 = Math.sqrt(Math.pow(ALoc1.getCVal().getRe().getF().getValue(), 2) + Math.pow(ALoc1.getCVal().getIm().getF().getValue(), 2));
        double abs2 = Math.sqrt(Math.pow(ALoc2.getCVal().getRe().getF().getValue(), 2) + Math.pow(ALoc2.getCVal().getIm().getF().getValue(), 2));
        double abs3 = Math.sqrt(Math.pow(ALoc3.getCVal().getRe().getF().getValue(), 2) + Math.pow(ALoc3.getCVal().getIm().getF().getValue(), 2));
        double abs4 = Math.sqrt(Math.pow(ALoc4.getCVal().getRe().getF().getValue(), 2) + Math.pow(ALoc4.getCVal().getIm().getF().getValue(), 2));
        double abs5 = Math.sqrt(Math.pow(ALoc5.getCVal().getRe().getF().getValue(), 2) + Math.pow(ALoc5.getCVal().getIm().getF().getValue(), 2));

        return  (abs1+ abs2 + abs3 + abs4 + abs5)/2;
    }

    private double difCurrentCalc(CMV ALoc1 , CMV ALoc2 , CMV ALoc3 , CMV ALoc4 , CMV ALoc5) {
        double ReSum = ALoc1.getCVal().getMag().getF().getValue() +
                ALoc2.getCVal().getMag().getF().getValue() +
                ALoc3.getCVal().getMag().getF().getValue() +
                ALoc4.getCVal().getMag().getF().getValue() +
                ALoc5.getCVal().getMag().getF().getValue();

//        double ReSum = ALoc1.getCVal().getRe().getF().getValue() +
//                ALoc2.getCVal().getRe().getF().getValue() +
//                ALoc3.getCVal().getRe().getF().getValue() +
//                ALoc4.getCVal().getRe().getF().getValue() +
//                ALoc5.getCVal().getRe().getF().getValue();
//
//        double ImSum = ALoc1.getCVal().getIm().getF().getValue() +
//                ALoc2.getCVal().getIm().getF().getValue() +
//                ALoc3.getCVal().getIm().getF().getValue() +
//                ALoc4.getCVal().getIm().getF().getValue() +
//                ALoc5.getCVal().getIm().getF().getValue();
//        return Math.sqrt(Math.pow(ReSum, 2) + Math.pow(ImSum, 2));
        return ReSum;
    }
}
