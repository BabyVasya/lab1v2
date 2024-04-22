package org.example.iec61850datatypes.measurements;

import org.example.iec61850datatypes.Data;
import org.example.iec61850datatypes.common.Quality;
import org.example.iec61850datatypes.common.Timestamp;

@lombok.Data
public class SPS extends Data{
    private boolean stVal;
    private Quality q;
    private Timestamp t;


}
