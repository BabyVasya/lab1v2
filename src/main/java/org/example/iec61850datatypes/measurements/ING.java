package org.example.iec61850datatypes.measurements;

import org.example.iec61850datatypes.Data;
import org.example.iec61850datatypes.common.Attribute;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//Сотояние оборудования
@lombok.Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ING")
public class ING extends Data {
    @XmlElement
    private Attribute<Double> setVal = new Attribute<>();
    private Attribute<Integer> minVal = new Attribute<>();
    private Attribute<Integer> maxVal = new Attribute<>();
    private Attribute<Double> stepSize = new Attribute<>();
}
