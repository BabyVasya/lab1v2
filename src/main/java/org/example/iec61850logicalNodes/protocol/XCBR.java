package org.example.iec61850logicalNodes.protocol;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.iec61850datatypes.measurements.*;
import org.example.iec61850logicalNodes.common.LN;
@Data
@Slf4j
public class XCBR extends LN {
    private DPL EEName;
    private ENS EEHealth;
    private SPS LocKey;
    private SPS Loc;
    private INS OpCnt = new INS();
    private ENS CBOpCap;
    private ENS POWCap;
    private INS MaxOpCap;
    private SPS Dsc;
    private BCR SumSwARs;
    private SPC LocSta;
    private DPC Pos = new DPC();
    private SPC BlkOpn = new SPC();
    private SPC BlkCls = new SPC();
    private SPC ChatMotEna;
    private ING CBTmms = new ING();
    @Override
    public void process() {
        if (Pos.getStVal().getValue().equals(DPC.Position.OFF)){
            Pos.getStVal().setValue(DPC.Position.OFF);
            OpCnt.getStVal().setValue(OpCnt.getStVal().getValue() +1);
        }
//        else if(Pos.getStVal().getValue().equals(DPC.Position.OFF)) {
//            Pos.getStVal().setValue(DPC.Position.ON);
//            OpCnt.getStVal().setValue(OpCnt.getStVal().getValue() +1);
//        }
        log.info("Положение выключателя " + Pos.getStVal());
    }
}
