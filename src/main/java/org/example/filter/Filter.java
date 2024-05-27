package org.example.filter;

import org.example.iec61850datatypes.measurements.CMV;
import org.example.iec61850datatypes.measurements.MV;

import javax.xml.bind.annotation.XmlTransient;
import java.util.List;
@XmlTransient
public abstract class Filter {
    public abstract void process(MV inputValue, CMV  outputValue, double frequency);
}
