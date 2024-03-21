package org.example.iec61850datatypes.measurements;

import org.example.iec61850datatypes.Data;
import org.example.iec61850datatypes.common.Attribute;
@lombok.Data
public class INS extends Data {
    private Attribute<Integer> stVal = new Attribute<>();
}
