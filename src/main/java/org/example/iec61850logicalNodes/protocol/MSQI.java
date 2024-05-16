package org.example.iec61850logicalNodes.protocol;

import lombok.Data;
import org.example.iec61850datatypes.measurements.MV;
import org.example.iec61850datatypes.measurements.SEQ;
import org.example.iec61850datatypes.measurements.WYE;
import org.example.iec61850logicalNodes.common.LN;
@Data
public class MSQI extends LN {
    private SEQ seqA = new SEQ();
    private SEQ seqV= new SEQ();
    private SEQ DQ0seq = new SEQ();
    private WYE ImbA = new WYE();
    private MV ImbNgA = new MV();
    private WYE ImbV = new WYE();
    private MV ImbZroA = new MV();
    private MV ImbZroV = new MV();
    private MV MaxImbA = new MV();
    private MV MaxImbPPV = new MV();
    private MV MaxImbV = new MV();
    private WYE A;
    private WYE V;

    public MSQI(WYE a, WYE phV) {
        this.A = a;
        this.V = phV;
    }

    @Override
    public void process() {
        calculateSeq(this.A, this.seqA);
        calculateSeq(this.V, this.seqV);
    }
    private void calculateSeq(WYE value, SEQ components){
        //Fre=F*cos(fi)
        double phsARe = value.getPhsA().getCVal().getMag().getF().getValue() * Math.cos(value.getPhsA().getCVal().getAng().getF().getValue());
        double phsAIm = value.getPhsA().getCVal().getMag().getF().getValue() * Math.sin(value.getPhsA().getCVal().getAng().getF().getValue());
        //A1 = 1/3*(a+120b+240c)
        //A2 = 1/3*(a+240b+120c)
        //A0 = 1/3*(a+b+c)
        double c1Re = (phsARe +
                value.getPhsB().getCVal().getMag().getF().getValue() * Math.cos(
                        value.getPhsB().getCVal().getAng().getF().getValue() + 120 * Math.PI/180) +
                value.getPhsC().getCVal().getMag().getF().getValue() * Math.cos(
                        value.getPhsC().getCVal().getAng().getF().getValue() + 240 * Math.PI/180))/3;
        double c1Im = (phsAIm +
                value.getPhsB().getCVal().getMag().getF().getValue() * Math.sin(
                        value.getPhsB().getCVal().getAng().getF().getValue() + 120 * Math.PI/180) +
                value.getPhsC().getCVal().getMag().getF().getValue() * Math.sin(
                        value.getPhsC().getCVal().getAng().getF().getValue() + 240 * Math.PI/180))/3;
        double c2Re = (phsARe +
                value.getPhsB().getCVal().getMag().getF().getValue() * Math.cos(
                        value.getPhsB().getCVal().getAng().getF().getValue() + 240 * Math.PI/180) +
                value.getPhsC().getCVal().getMag().getF().getValue() * Math.cos(
                        value.getPhsC().getCVal().getAng().getF().getValue() + 120 * Math.PI/180))/3;
        double c2Im = (phsAIm +
                value.getPhsB().getCVal().getMag().getF().getValue() * Math.sin(
                        value.getPhsB().getCVal().getAng().getF().getValue() + 240 * Math.PI/180) +
                value.getPhsC().getCVal().getMag().getF().getValue() * Math.sin(
                        value.getPhsC().getCVal().getAng().getF().getValue() + 120 * Math.PI/180))/3;
        double c3Re = (phsARe +
                value.getPhsB().getCVal().getMag().getF().getValue() * Math.cos(
                        value.getPhsB().getCVal().getAng().getF().getValue()) +
                value.getPhsC().getCVal().getMag().getF().getValue() * Math.cos(
                        value.getPhsC().getCVal().getAng().getF().getValue()))/3;
        double c3Im = (phsAIm +
                value.getPhsB().getCVal().getMag().getF().getValue() * Math.sin(
                        value.getPhsB().getCVal().getAng().getF().getValue()) +
                value.getPhsC().getCVal().getMag().getF().getValue() * Math.sin(
                        value.getPhsC().getCVal().getAng().getF().getValue()))/3;

        components.getC1().getCVal().getAng().getF().setValue(Math.atan(c1Im/c1Re));
        components.getC1().getCVal().getMag().getF().setValue(Math.sqrt(Math.pow(c1Re, 2) + Math.pow(c1Im, 2)));
        components.getC2().getCVal().getAng().getF().setValue(Math.atan(c2Im/c2Re));
        components.getC2().getCVal().getMag().getF().setValue(Math.sqrt(Math.pow(c2Re, 2) + Math.pow(c2Im, 2)));
        components.getC3().getCVal().getAng().getF().setValue(Math.atan(c3Im/c3Re));
        components.getC3().getCVal().getMag().getF().setValue(Math.sqrt(Math.pow(c3Re, 2) + Math.pow(c3Im, 2)));
    }

}
