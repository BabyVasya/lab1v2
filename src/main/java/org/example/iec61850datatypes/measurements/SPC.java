package org.example.iec61850datatypes.measurements;

import org.example.iec61850datatypes.Data;
import org.example.iec61850datatypes.common.Attribute;
import org.example.iec61850datatypes.common.Originator;
import org.example.iec61850datatypes.common.Quality;
import org.example.iec61850datatypes.common.Timestamp;
@lombok.Data
public class SPC extends Data {
    private Attribute<Originator> origin = new Attribute<>();
    private Attribute<Integer> clNum8 = new Attribute<>();
    private Attribute<Boolean> stVal = new Attribute<>();
    private Attribute<Quality> q = new Attribute<>();
    private Attribute<Timestamp> t = new Attribute<>();
    private Attribute<Boolean> stSeld = new Attribute<>();
    private Attribute<Boolean> opRcvd = new Attribute<>();
    private Attribute<Boolean> opOk = new Attribute<>();
    private Attribute<Timestamp> tOpOk = new Attribute<>();
}
