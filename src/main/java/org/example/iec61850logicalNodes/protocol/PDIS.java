package org.example.iec61850logicalNodes.protocol;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.iec61850datatypes.measurements.*;
import org.example.iec61850logicalNodes.common.LN;
@Data
@Slf4j
public class PDIS extends LN {
    public final ACD Str = new ACD();
    public final ACT Op = new ACT();

    private INC OpCntRs = new INC();
    private ASG PoRch = new ASG();
    public ASG PhStr = new ASG();
    private ASG GndStr = new ASG();
    public ENG DirMod = new ENG();
    private ASG PctRch = new ASG();
    private ASG Ofs = new ASG();
    private ASG PctOfs = new ASG();
    private ASG RisLod = new ASG();
    private ASG AngLod = new ASG();
    private SPG TmDlMod = new SPG();
    private ING OpDlTmms = new ING();
    private SPG PhDlMod = new SPG();
    private ING PhDlTmms = new ING();
    private SPG GndDlMod = new SPG();
    private ING GndDlTmms = new ING();
    private ASG X1 = new ASG();
    private ASG LinAng = new ASG();
    private ASG RisGndRch = new ASG();
    private ASG RisPhRch = new ASG();
    private ASG K0Fact = new ASG();
    private ASG K0FactAng = new ASG();
    private ING RsDlTmms = new ING();

    private WYE Z = new WYE();
    private ACT blk = new ACT();
    private double timeCounter = 0; // таймер для обеспечения выдержки
    private WYE dif = new WYE();


    @Override
    public void process() {
//        log.info(String.valueOf(Z.getPhsA().getCVal().getMag().getF().getValue()) + " уставка " + getPhStr().getSetMag().getF().getValue());
       Op.getGeneral().setValue(Z.getPhsA().getCVal().getMag().getF().getValue() < getPhStr().getSetMag().getF().getValue());
    }


}
