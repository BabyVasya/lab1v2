package org.example.iec61850logicalNodes.common;

import lombok.Data;

@Data
public abstract class LN {
    public abstract void process();
}
