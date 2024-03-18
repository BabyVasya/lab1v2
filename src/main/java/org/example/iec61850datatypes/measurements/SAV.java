package org.example.iec61850datatypes.measurements;

import lombok.Data;
import org.example.iec61850datatypes.common.Quality;
import org.example.iec61850datatypes.common.Timestamp;
@Data
public class SAV {
    private AnalogueValue instMag;
    private Quality q;
    private Timestamp t;
}
