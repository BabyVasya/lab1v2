package org.example.iec61850datatypes;

import java.util.ArrayList;
import java.util.List;

@lombok.Data
public class Data {
    private String name;
    private String reference;
    private Data parent;
    private final List<Data> children = new ArrayList<>();
}
