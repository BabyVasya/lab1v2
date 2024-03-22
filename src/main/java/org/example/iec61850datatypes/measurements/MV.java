package org.example.iec61850datatypes.measurements;


import org.example.iec61850datatypes.Data;
import org.example.iec61850datatypes.common.Quality;
import org.example.iec61850datatypes.common.Timestamp;

//Входной сигнал
@lombok.Data
public class MV extends Data {
    private AnalogueValue instMag = new AnalogueValue();
    private Quality q = new Quality();
    private Timestamp t = new Timestamp();
}
