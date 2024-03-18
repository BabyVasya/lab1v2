package org.example.iec61850datatypes.common;

import org.example.iec61850datatypes.Data;
@lombok.Data
public class DataAttribute<E> extends Data {
    private E value ;
}
