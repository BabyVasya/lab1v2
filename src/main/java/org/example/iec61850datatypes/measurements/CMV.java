package org.example.iec61850datatypes.measurements;

import org.example.iec61850datatypes.Data;
import org.example.iec61850datatypes.common.Quality;
import org.example.iec61850datatypes.common.Timestamp;
import org.example.iec61850datatypes.common.Vector;
@lombok.Data
public class CMV extends Data {
    private Vector cVal = new Vector();
    private Quality q = new Quality();
    private Timestamp t = new Timestamp();

}
