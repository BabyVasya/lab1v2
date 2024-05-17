package org.example.iec61850datatypes.measurements;

import org.example.iec61850datatypes.Data;
import org.example.iec61850datatypes.common.Attribute;
import org.example.iec61850datatypes.common.Quality;
import org.example.iec61850datatypes.common.Timestamp;
import org.w3c.dom.Attr;

@lombok.Data
public class SPS extends Data{
    private Attribute<Boolean> stVal = new Attribute<>();
    private Quality q;
    private Timestamp t;


}
