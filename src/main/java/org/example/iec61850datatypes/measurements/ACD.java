package org.example.iec61850datatypes.measurements;

import lombok.Data;
import org.example.iec61850datatypes.common.DataAttribute;

@Data
public class ACD extends ACT{

    private DataAttribute<Direction> dirGeneral = new DataAttribute<>();
    {
        dirGeneral.setName("dirGeneral");
        dirGeneral.setParent(this);
        this.getChildren().add(dirGeneral);
    }


    private DataAttribute<Direction> dirPhsA = new DataAttribute<>();


    private DataAttribute<Direction> dirPhsB = new DataAttribute<>();


    private DataAttribute<Direction> dirPhsC = new DataAttribute<>();

    public enum Direction {
        FORWARD,
        BACKWARD,
        BOTH
    }
}
