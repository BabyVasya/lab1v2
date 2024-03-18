package org.example.iec61850datatypes.measurements;

import org.example.iec61850datatypes.Data;
import org.example.iec61850datatypes.common.Unit;

@lombok.Data
public class CSD extends Data {
    private Unit xUnits;
    private String xD;
    private String xDU;
    private Unit yUnits;
    private String yD;
    private  String yDU;
}
