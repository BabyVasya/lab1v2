package org.example.iec61850datatypes.measurements;

import lombok.Getter;
import org.example.iec61850datatypes.Data;
import org.example.iec61850datatypes.common.DataAttribute;
import org.example.iec61850datatypes.common.Quality;
import org.example.iec61850datatypes.common.Timestamp;


@lombok.Data
public class ACT extends Data {
    private DataAttribute<Boolean> general = new DataAttribute<>();
    private DataAttribute<Boolean>  phsA = new DataAttribute<>();
    private DataAttribute<Boolean>  phsB = new DataAttribute<>();
    private DataAttribute<Boolean>  phsC = new DataAttribute<>();
    private DataAttribute<Boolean>  neut = new DataAttribute<>();
//    private Quality q = new Quality();
//    private Timestamp t = new Timestamp();
//    private Timestamp operTmPhsA = new Timestamp();
//    private Timestamp operTmPhsB = new Timestamp();
//    private Timestamp operTmPhsC = new Timestamp();
}
