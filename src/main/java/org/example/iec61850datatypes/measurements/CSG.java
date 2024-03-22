package org.example.iec61850datatypes.measurements;

import org.example.iec61850datatypes.Data;
import org.example.iec61850datatypes.common.Attribute;


//Форма кривой
@lombok.Data
public class CSG extends Data {
    private Attribute<Float> pointZ = new Attribute<>();
    private Attribute<Integer> numPts = new Attribute<>();
}
