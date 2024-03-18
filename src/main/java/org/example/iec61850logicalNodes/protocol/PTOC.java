package org.example.iec61850logicalNodes.protocol;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.iec61850datatypes.measurements.*;
import org.example.iec61850logicalNodes.common.LN;


@Data
@Slf4j
public class PTOC extends LN {

    private ACT op = new ACT();
    private INC OpCntRs;
    private CURVE TmACrv;
    private CSG TmAChr33;
    private CSD TmASt;
    private ASG StrVal = new ASG();
    private ASG TmMult;
    private ING MinOpTmms;
    private ING MaxOpTmms;
    private ING OpDlTmms = new ING();
    private ENG TypRsCrv;
    private ING RsDlTmms;
    private ENG DirMod;
    private WYE A;

    @Override
    public void process() {
//            op.getGeneral().add(
//                    A.getPhsA().getCVal().getMag().getF().g > this.StrVal.getSetMag().getF() ||
//                    A.getPhsB().getCVal().getMag().getF() > this.StrVal.getSetMag().getF() ||
//                    A.getPhsC().getCVal().getMag().getF() > this.StrVal.getSetMag().getF()
//            );
//            log.info("Срабатывание защиты " + op);
    }
}
