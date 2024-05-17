package org.example.iec61850logicalNodes.protocol;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.iec61850datatypes.measurements.*;
import org.example.iec61850logicalNodes.common.LN;
@Data
@Slf4j
public class PDIS extends LN {
    //Пуск защиты (превышение уставки)
    public final ACD Str = new ACD();
    //Подача УВ
    public final ACT Op = new ACT();

    private INC OpCntRs = new INC();
    private ASG PoRch = new ASG();
    private ASG PhStr = new ASG();
    private ASG GndStr = new ASG();
    private ENG DirMod = new ENG();
    private ASG PctRch = new ASG();
    private ASG Ofs = new ASG();
    private ASG PctOfs = new ASG();
    private ASG RisLod = new ASG();
    private ASG AngLod = new ASG();
    private SPG TmDlMod = new SPG();
    private ING OpDlTmms = new ING();

    private WYE Z = new WYE();
    private ACT blk = new ACT();
    private double timerDir;
    private double timer;
    private WYE pointAndRadius = new WYE();
    private ACT rpsbBlock = new ACT();

    public PDIS() {
        Op.getGeneral().setValue(false);
        blk.getGeneral().setValue(true);
    }



    @Override
    public void process() {
        pointAndRadius.getPhsA().getCVal().getMag().getF().setValue(
                Z.getPhsA().getCVal().getMag().getF().getValue() - PhStr.getSetMag().getF().getValue()
        );
        pointAndRadius.getPhsB().getCVal().getMag().getF().setValue(
                Z.getPhsB().getCVal().getMag().getF().getValue() - PhStr.getSetMag().getF().getValue()
        );
        pointAndRadius.getPhsC().getCVal().getMag().getF().setValue(
                Z.getPhsC().getCVal().getMag().getF().getValue() - PhStr.getSetMag().getF().getValue()
        );

        //Проверка уставки
        Str.getGeneral().setValue((
                Z.getPhsA().getCVal().getMag().getF().getValue() <
                        PhStr.getSetMag().getF().getValue() ||
                        Z.getPhsB().getCVal().getMag().getF().getValue() <
                                PhStr.getSetMag().getF().getValue() ||
                        Z.getPhsC().getCVal().getMag().getF().getValue() <
                                PhStr.getSetMag().getF().getValue()));

        //Направленная защита или нет
        if(DirMod.getSetVal().getValue() == ACD.Direction.FORWARD && Str.getGeneral().getValue()) timer += 1;
        else if(DirMod.getSetVal().getValue() == null&& Str.getGeneral().getValue())timer += 1;
        else  timer= 0;

        //Проверка блокировки
        if(!blk.getGeneral().getValue() ) Op.getGeneral().setValue(timer > OpDlTmms.getSetVal().getValue());

        //Строгий вовзрат вне завивисимости от блокировки
        if(!Str.getGeneral().getValue()) Op.getGeneral().setValue(false);







    }


}
