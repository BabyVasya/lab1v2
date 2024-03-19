package org.example.iec61850datatypes.measurements;

import org.example.iec61850datatypes.Data;
import org.example.iec61850datatypes.common.DataAttribute;

@lombok.Data
public class ING extends Data {
    private DataAttribute<Integer> setVal = new DataAttribute<>();
}
