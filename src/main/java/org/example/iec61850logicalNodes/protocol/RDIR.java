package org.example.iec61850logicalNodes.protocol;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.iec61850datatypes.common.Attribute;
import org.example.iec61850datatypes.measurements.*;
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
    private WYE Z;
    public Attribute<Double> power = new Attribute<>();

    public RDIR() {

    }

    @Override
    public void process() {
        if (checkOrientation(Z.getPhsA()) && checkOrientation(Z.getPhsB()) && checkOrientation(Z.getPhsC())){
            dir.getDirGeneral().setValue(ACD.Direction.FORWARD);
        } else {
            dir.getDirGeneral().setValue(ACD.Direction.BACKWARD);
        }

//        log.info(String.valueOf(dir.getDirGeneral().getValue()) );

    }

    private boolean checkOrientation(CMV phase){
        return phase.getCVal().getIm().getF().getValue() >=
                (phase.getCVal().getRe().getF().getValue() *
                        Math.tan((chrAng.getSetMag().getF().getValue() + 100) * Math.PI / 180)) &&
                phase.getCVal().getIm().getF().getValue() >=
                        (phase.getCVal().getRe().getF().getValue() *
                                Math.tan((chrAng.getSetMag().getF().getValue() - 100) * Math.PI / 180));
    }

}
