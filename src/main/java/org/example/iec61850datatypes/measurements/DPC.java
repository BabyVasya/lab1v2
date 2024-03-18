package org.example.iec61850datatypes.measurements;

import org.example.iec61850datatypes.Data;
import org.example.iec61850datatypes.common.Originator;
import org.example.iec61850datatypes.common.Quality;
import org.example.iec61850datatypes.common.Timestamp;

import java.util.ArrayList;
import java.util.List;

@lombok.Data
public class DPC extends Data {
    private Originator origin = new Originator();
    private int cltNum;
    public enum stVal {
        INTERMEDIATESTATE,
        OFF,
        ON,
        BADSTATE
    }
    private Quality q = new Quality();
    private List<Timestamp> t = new ArrayList<>();
    private boolean stSeld;
    private boolean opRcvd;
    private List<Boolean> opOk = new ArrayList<>();
    private List<Timestamp> tOpOk = new ArrayList<>();

}
