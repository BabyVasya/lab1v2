package org.example.iec61850datatypes.common;

import org.example.iec61850datatypes.Data;
import org.example.iec61850datatypes.measurements.AnalogueValue;

@lombok.Data
public class Vector extends Data {
    private AnalogueValue mag = new AnalogueValue();
    private AnalogueValue ang = new AnalogueValue();

    private AnalogueValue re = new AnalogueValue();
    private AnalogueValue im = new AnalogueValue();

}
