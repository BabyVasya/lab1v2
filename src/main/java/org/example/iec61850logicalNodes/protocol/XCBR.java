package org.example.iec61850logicalNodes.protocol;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.iec61850datatypes.measurements.*;
import org.example.iec61850logicalNodes.common.LN;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@Slf4j
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CSWI")
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
    private int c;
    @Override
    public void process() {
        if (c == 0) {
            OpCnt.getStVal().setValue(0);
            c++;
        }
        if (Pos.getStVal().getValue().equals(DPC.Position.OFF)) {
            OpCnt.getStVal().setValue(OpCnt.getStVal().getValue() +1);
        }
//        log.info("Положение выключателя " + Pos.getStVal());
    }
}
