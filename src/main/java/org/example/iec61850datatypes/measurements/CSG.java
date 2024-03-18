package org.example.iec61850datatypes.measurements;

import org.example.iec61850datatypes.Data;
import org.example.iec61850datatypes.common.DataAttribute;


//Форма кривой
@lombok.Data
public class CSG extends Data {
    private DataAttribute<Float> pointZ = new DataAttribute<>();
    private  DataAttribute<Integer> numPts = new DataAttribute<>();
}
