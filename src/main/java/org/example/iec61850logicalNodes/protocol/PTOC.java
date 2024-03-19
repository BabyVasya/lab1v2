package org.example.iec61850logicalNodes.protocol;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.iec61850datatypes.measurements.*;
import org.example.iec61850logicalNodes.common.LN;


@Data
@Slf4j
public class PTOC extends LN {

    private ACT op = new ACT();
    private ACD Str = new ACD();
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
    private double timeCounter = 0;

    @Override
    public void process() {
        op.getPhsA().setValue(A.getPhsA().getCVal().getMag().getF().getValue() > this.StrVal.getSetMag().getF().getValue());
        op.getPhsB().setValue(A.getPhsB().getCVal().getMag().getF().getValue() > this.StrVal.getSetMag().getF().getValue());
        op.getPhsC().setValue(A.getPhsC().getCVal().getMag().getF().getValue() > this.StrVal.getSetMag().getF().getValue());
        op.getGeneral().setValue(op.getPhsA().getValue()||op.getPhsB().getValue()||op.getPhsC().getValue());

        if (op.getGeneral().getValue()) {
            timeCounter += 1;
        }

        Str.getGeneral().setValue(op.getGeneral().getValue());
        Str.getPhsA().setValue(A.getPhsA().getCVal().getMag().getF().getValue() >
                StrVal.getSetMag().getF().getValue());
        Str.getPhsB().setValue(A.getPhsB().getCVal().getMag().getF().getValue() >
                StrVal.getSetMag().getF().getValue());
        Str.getPhsC().setValue(A.getPhsC().getCVal().getMag().getF().getValue() >
                StrVal.getSetMag().getF().getValue());

        // Внесение данных о наличии сиогнала на отключение во всех фазах при превышении выдержки
        op.getGeneral().setValue(timeCounter > OpDlTmms.getSetVal().getValue());
        op.getPhsA().setValue(timeCounter > OpDlTmms.getSetVal().getValue());
        op.getPhsB().setValue(timeCounter > OpDlTmms.getSetVal().getValue());
        op.getPhsC().setValue(timeCounter > OpDlTmms.getSetVal().getValue());

    }


}

