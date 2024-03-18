package org.example.iec61850datatypes.measurements;

import org.example.iec61850datatypes.Data;
import org.example.iec61850datatypes.common.DataAttribute;

@lombok.Data
public class AnalogueValue extends Data {
    private DataAttribute<Double> f = new DataAttribute<>();
}
