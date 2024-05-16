package org.example.iec61850datatypes.measurements;

import org.example.iec61850datatypes.Data;
import org.example.iec61850datatypes.common.Originator;
import org.example.iec61850datatypes.common.Quality;
import org.example.iec61850datatypes.common.Timestamp;

//Контроллируемый целочисленный статус (возможно, имеется ввиду сколько раз производились операции в классе узла)
@lombok.Data
public class INC extends Data {
    private Originator origin;
    private int cltNum;
    private int stVal;
    private Quality q;
    private Timestamp t;
    private boolean stSeld;
    private boolean opRcvd;
    private boolean opOk;
    private Timestamp tOpOk = new Timestamp();
}
