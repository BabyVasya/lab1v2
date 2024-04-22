package org.example.iec61850datatypes.measurements;

import org.example.iec61850datatypes.Data;
import org.example.iec61850datatypes.common.Attribute;

import javax.xml.bind.annotation.*;

@lombok.Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "AnalogueValue")
public class AnalogueValue extends Data {
    @XmlElement(name  = "Attribute")
    private Attribute<Double> f = new Attribute<>();

    public AnalogueValue() {
    }
}
