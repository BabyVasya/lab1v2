package org.example.iec61850logicalNodes.common;

import lombok.Data;

@Data
public abstract class LN {
    private String pref;
    private String clazz;
    private int inst;

    public abstract void process();
}
