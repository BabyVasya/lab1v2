package org.example.iec61850datatypes.common;

import org.example.iec61850datatypes.Data;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@lombok.Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Attribute")
public class Attribute<T> extends Data {
    private T value;

}
