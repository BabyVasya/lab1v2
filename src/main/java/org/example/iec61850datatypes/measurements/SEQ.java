package org.example.iec61850datatypes.measurements;

import lombok.Data;

@Data
public class SEQ {
    private CMV c1 = new CMV();
    private CMV c2 = new CMV();
    private CMV c3 = new CMV();

    public SEQ() {
    }
}
