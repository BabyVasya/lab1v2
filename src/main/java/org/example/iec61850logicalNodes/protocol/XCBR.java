package org.example.iec61850logicalNodes.protocol;

import org.example.iec61850datatypes.measurements.DPC;
import org.example.iec61850datatypes.measurements.ING;
import org.example.iec61850logicalNodes.common.LN;

public class XCBR extends LN {
    //Switch position
    private DPC pos = new DPC();
    private ING CBTmms = new ING();
    @Override
    public void process() {

    }
}
