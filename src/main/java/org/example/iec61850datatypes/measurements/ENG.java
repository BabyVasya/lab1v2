package org.example.iec61850datatypes.measurements;

import org.example.iec61850datatypes.Data;
import org.example.iec61850datatypes.common.Attribute;
@lombok.Data
//Настройка статуса
public class ENG extends Data {
    private Attribute<ACD.Direction> setVal = new Attribute<>();
}
