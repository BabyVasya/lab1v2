package org.example.iec61850logicalNodes.protocol;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.iec61850datatypes.common.Attribute;
import org.example.iec61850datatypes.measurements.*;
import org.example.iec61850logicalNodes.common.LN;

import java.awt.*;

@Data
@Slf4j
public class PDIF extends LN {
    public ACD Str = new ACD();
    public ACT Op = new ACT();
    public CSD TmASt = new CSD();
    private WYE DifAClc = new WYE();
    private WYE RstA = new WYE();
    private INC OpCntRs = new INC();
    private ASG LinCapac = new ASG();
    private ASG LoSet = new ASG();
    private ASG HiSet = new ASG();
    private ING MinOpTmms = new ING();
    public ING MaxOpTmms = new ING();
    public ING OpDlTmms = new ING();
    private ENG RstMod = new ENG();
    private ING RsDlTmms = new ING();
    private CURVE TmACrv = new CURVE();
    private CSG TmChr33 = new CSG();

    public WYE ALoc = new WYE();
    public WYE Res = new WYE();
    public WYE Dif = new WYE();
    public ACT Blc = new ACT();
    private double timeCounter = 0;
    private double CntA = 0;
    private double CntB = 0;
    private double CntC = 0;
    public PDIF() {
        this.Str.getGeneral().setValue(false);
        this.Str.getPhsA().setValue(false);
        this.Str.getPhsB().setValue(false);
        this.Str.getPhsC().setValue(false);
        this.Op.getGeneral().setValue(false);
        this.Op.getPhsA().setValue(false);
        this.Op.getPhsB().setValue(false);
        this.Op.getPhsC().setValue(false);
    }

    @Override
    public void process() {
        if(checkCurvePart(RstA.getPhsA()) || checkCurvePart(RstA.getPhsB()) || checkCurvePart(RstA.getPhsC())) {
            Op.getGeneral().setValue(
               checkOpCondition(DifAClc.getPhsA(), RstA.getPhsA()) ||
                       checkOpCondition(DifAClc.getPhsB(), RstA.getPhsB()) ||
                       checkOpCondition(DifAClc.getPhsC(), RstA.getPhsC())
            );
        } else {
            Op.getGeneral().setValue(
                    DifAClc.getPhsA().getCVal().getMag().getF().getValue() > TmACrv.getSetParB().getValue() ||
                    DifAClc.getPhsB().getCVal().getMag().getF().getValue() > TmACrv.getSetParB().getValue() ||
                    DifAClc.getPhsC().getCVal().getMag().getF().getValue() > TmACrv.getSetParB().getValue()
            );
        }


    }

    private boolean checkCurvePart(CMV phase) {
        return phase.getCVal().getMag().getF().getValue() > TmACrv.getSetParA().getValue();
    }

    private boolean checkOpCondition(CMV difPh, CMV rstPh) {
        double dif = difPh.getCVal().getMag().getF().getValue();
        double rst = rstPh.getCVal().getMag().getF().getValue();
        double kt = TmACrv.getSetParC().getValue();
        double rst0 = TmACrv.getSetParA().getValue();
        double dif0 = TmACrv.getSetParB().getValue();
        double diff = kt*(rst - rst0)+dif0;
//        log.info("Срабатывание защиты ф А " + Op.getGeneral().getValue() + "; Дифф ток " + DifAClc.getPhsA().getCVal().getMag().getF().getValue() + "; Торм ток " + RstA.getPhsA().getCVal().getMag().getF().getValue() + " dif " + dif + " diff" + diff);
        return dif >= diff;
    }
}
