package org.example.iec61850datatypes.measurements;

import lombok.Data;
import org.example.iec61850datatypes.common.Attribute;
// Срабатывание направленной защиты
@Data
public class ACD extends ACT{

    private Attribute<Direction> dirGeneral = new Attribute<>();


    private Attribute<Direction> dirPhsA = new Attribute<>();


    private Attribute<Direction> dirPhsB = new Attribute<>();


    private Attribute<Direction> dirPhsC = new Attribute<>();

    public enum Direction {
        FORWARD,
        BACKWARD,
        BOTH
    }
}
