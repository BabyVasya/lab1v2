package org.example.iec61850logicalNodes.protocol;

import lombok.Data;
import org.example.iec61850datatypes.measurements.ACT;
import org.example.iec61850datatypes.measurements.DPC;
import org.example.iec61850datatypes.measurements.INC;
import org.example.iec61850datatypes.measurements.SPS;
import org.example.iec61850logicalNodes.common.LN;
@Data
public class CSWI extends LN {
    private SPS LocKey = new SPS();
    private SPS Loc = new SPS();
    //Сигналы на отключение от защит
    public ACT OpOpn1 = new ACT();
    public ACT OpOpn2 = new ACT();
    public SPS SetOpn = new SPS();
    public ACT OpCls = new ACT();
    public SPS SetCls = new SPS();
    private INC OpCrtRs = new INC();
    // Поля хранящие выходные значения сигнала на отключения фаз выключателя
    public DPC Pos = new DPC();
    public DPC PosA = new DPC();
    public DPC PosB = new DPC();
    public DPC PosC = new DPC();



    @Override
    public void process() {
        //Проверка наличия сигнала на отключение выключателя от каждой из защит
        if (OpOpn1.getGeneral().getValue() || OpOpn2.getGeneral().getValue()){
            Pos.getStVal().setValue(DPC.Position.OFF);
            PosA.getStVal().setValue(DPC.Position.OFF);
            PosB.getStVal().setValue(DPC.Position.OFF);
            PosC.getStVal().setValue(DPC.Position.OFF);
        } else{
            Pos.getStVal().setValue(DPC.Position.ON);
            PosA.getStVal().setValue(DPC.Position.ON);
            PosB.getStVal().setValue(DPC.Position.ON);
            PosC.getStVal().setValue(DPC.Position.ON);
        };
    }

}
