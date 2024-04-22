package org.example.Xml.Adapaters;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class AttributeDoubleAdapter<T> extends XmlAdapter<String, T> {
    @Override
    public T unmarshal(String value) {

        if (value == null || value.isEmpty()) {
            return null;
        }
        return (T) Double.valueOf(value);
    }

    @Override
    public String marshal(T value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }
}
