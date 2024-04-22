package org.example.iec61850datatypes.measurements;

import org.example.iec61850datatypes.Data;

//Отфльтрованные значения в векторном виде
@lombok.Data
public class WYE extends Data {
    private CMV phsA = new CMV();
    private CMV phsB = new CMV();
    private CMV phsC = new CMV();
    private CMV neut = new CMV();
}
