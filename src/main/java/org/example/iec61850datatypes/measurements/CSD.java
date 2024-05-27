package org.example.iec61850datatypes.measurements;

import org.example.iec61850datatypes.Data;
import org.example.iec61850datatypes.common.Attribute;
import org.example.iec61850datatypes.common.Unit;

import java.util.ArrayList;
import java.util.List;

@lombok.Data
public class CSD extends Data {
    private Unit xUnits;
    private String xD;
    private String xDU;
    private Unit yUnits;
    private String yD;
    private  String yDU;
    private int numPts;
    private List<Attribute<Double>> crvPts = new ArrayList<>();

}
