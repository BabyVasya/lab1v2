package org.example.iec61850datatypes.measurements;

import org.example.iec61850datatypes.Data;
import org.example.iec61850datatypes.common.DataAttribute;

//Описание каких-то кривых настройки используемых в защите
@lombok.Data
public class CURVE extends Data {
    private DataAttribute<Float> setParA = new DataAttribute<>();
    private DataAttribute<Float> setParB = new DataAttribute<>();
    private DataAttribute<Float> setParC = new DataAttribute<>();
    private DataAttribute<Float> setParD = new DataAttribute<>();
    private DataAttribute<Float> setParE = new DataAttribute<>();
    private DataAttribute<Float> setParF = new DataAttribute<>();
}
