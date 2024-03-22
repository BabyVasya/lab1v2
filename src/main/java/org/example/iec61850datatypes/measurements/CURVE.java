package org.example.iec61850datatypes.measurements;

import org.example.iec61850datatypes.Data;
import org.example.iec61850datatypes.common.Attribute;

//Описание каких-то кривых настройки используемых в защите(Может как в ДЗЛ от экра)
@lombok.Data
public class CURVE extends Data {
    private Attribute<Float> setParA = new Attribute<>();
    private Attribute<Float> setParB = new Attribute<>();
    private Attribute<Float> setParC = new Attribute<>();
    private Attribute<Float> setParD = new Attribute<>();
    private Attribute<Float> setParE = new Attribute<>();
    private Attribute<Float> setParF = new Attribute<>();
}
