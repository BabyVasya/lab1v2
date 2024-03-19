package org.example.iec61850datatypes.measurements;

import org.example.iec61850datatypes.Data;
import org.example.iec61850datatypes.common.DataAttribute;
import org.example.iec61850datatypes.common.Originator;
import org.example.iec61850datatypes.common.Quality;
import org.example.iec61850datatypes.common.Timestamp;

@lombok.Data
public class DPC extends Data {
    private DataAttribute<Position> stVal = new DataAttribute<>();
    {
        stVal.setName("stVal");
        stVal.setParent(this);
        this.getChildren().add(stVal);
    }

    private DataAttribute<Position> ctlVal = new DataAttribute<>();

    private  DataAttribute<Integer> ctlNum = new DataAttribute<>();

    private Originator origin = new Originator();

    private Quality q;

    private Timestamp t;
    public enum Position {
        INTERMEDIATESTATE,
        OFF,
        ON
    }


}
