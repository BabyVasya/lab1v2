package org.example.filter;

import org.example.iec61850datatypes.measurements.CMV;
import org.example.iec61850datatypes.measurements.MV;

import java.util.List;

public abstract class Filter {
    public abstract void process(MV inputValue, CMV  outputValue);
}
