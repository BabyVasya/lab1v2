package org.example.iec61850datatypes.measurements;

import lombok.Data;

@Data
public class DEL {
    private CMV phsAB = new CMV();
    private CMV phsBC = new CMV();
    private CMV phsAC = new CMV();
}
