package org.example.iec61850datatypes.measurements;

import org.example.iec61850datatypes.Data;
import org.example.iec61850datatypes.common.Unit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@lombok.Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ASG")
public class ASG extends Data {
    @XmlElement
    private AnalogueValue setMag = new AnalogueValue();
    private Unit units;
    private AnalogueValue minVal;
    private AnalogueValue maxVal;
    private AnalogueValue stepSize;


}
