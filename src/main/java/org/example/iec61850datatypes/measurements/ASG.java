package org.example.iec61850datatypes.measurements;

import org.example.iec61850datatypes.Data;
import org.example.iec61850datatypes.common.Unit;

@lombok.Data
public class ASG extends Data {
    private AnalogueValue setMag = new AnalogueValue();
    private Unit units;
    private AnalogueValue minVal;
    private AnalogueValue maxVal;
    private AnalogueValue stepSize;
}
