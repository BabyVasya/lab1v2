package org.example.iec61850datatypes.measurements;

import org.example.iec61850datatypes.Data;
import org.example.iec61850datatypes.common.Attribute;

import javax.xml.bind.annotation.*;

@lombok.Data
public class AnalogueValue extends Data {

    private Attribute<Double> f = new Attribute<>();

}
