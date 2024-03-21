package org.example.iec61850datatypes.measurements;

import org.example.iec61850datatypes.Data;
import org.example.iec61850datatypes.common.Attribute;
//Сотояние оборудования
@lombok.Data
public class ING extends Data {
    private Attribute<Integer> setVal = new Attribute<>();
    private Attribute<Integer> minVal = new Attribute<>();
    private Attribute<Integer> maxVal = new Attribute<>();
    private Attribute<Integer> stepSize = new Attribute<>();
}
