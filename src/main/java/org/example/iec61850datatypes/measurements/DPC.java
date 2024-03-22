package org.example.iec61850datatypes.measurements;

import org.example.iec61850datatypes.Data;
import org.example.iec61850datatypes.common.Attribute;
import org.example.iec61850datatypes.common.Originator;
import org.example.iec61850datatypes.common.Quality;
import org.example.iec61850datatypes.common.Timestamp;

@lombok.Data
public class DPC extends Data {
    private Attribute<Position> stVal = new Attribute<>();

    private Attribute<Position> ctlVal = new Attribute<>();

    private Attribute<Integer> ctlNum = new Attribute<>();

    private Originator origin = new Originator();

    private Quality q;

    private Timestamp t;
    public enum Position {
        INTERMEDIATESTATE,
        OFF,
        ON
    }


}
