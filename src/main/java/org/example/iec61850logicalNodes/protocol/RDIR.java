package org.example.iec61850logicalNodes.protocol;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.iec61850datatypes.common.Attribute;
import org.example.iec61850datatypes.measurements.ACD;
import org.example.iec61850datatypes.measurements.ASG;
import org.example.iec61850datatypes.measurements.ENG;
import org.example.iec61850datatypes.measurements.SEQ;
import org.example.iec61850logicalNodes.common.LN;
@Data
@Slf4j
public class RDIR extends LN {
    public ACD dir = new ACD();
    private ASG chrAng = new ASG();
    private ASG minFwdAng = new ASG();
    private ASG minRvAng = new ASG();
    private ASG maxFwdAng = new ASG();
    private ASG maxRvAng = new ASG();
    private ASG blkValA = new ASG();
    private ASG blkValV = new ASG();
    private ENG polQty = new ENG();
    private ASG minPPV = new ASG();
    private SEQ seqA;
    private SEQ seqV;
    public Attribute<Double> power = new Attribute<>();

    public RDIR(SEQ seqA, SEQ seqV) {
        this.seqA = seqA;
        this.seqV = seqV;
    }

    @Override
    public void process() {
        //Аалог формулы P=IU*cos(Fi(u)-Fi(i))
        power.setValue(seqA.getC3().getCVal().getMag().getF().getValue() *
                seqV.getC3().getCVal().getMag().getF().getValue() *
                Math.cos(seqV.getC3().getCVal().getAng().getF().getValue()  -
                        seqA.getC3().getCVal().getAng().getF().getValue()));
        //Если получили мощность нулевой последовательности больше нуля, то направление FORWARD, то есть в прямом напр
        if (power.getValue() > 0) dir.getDirGeneral().setValue(ACD.Direction.FORWARD);
        else  dir.getDirGeneral().setValue(ACD.Direction.BOTH);
//        log.info(String.valueOf(dir.getDirGeneral().getValue()));
    }

}
