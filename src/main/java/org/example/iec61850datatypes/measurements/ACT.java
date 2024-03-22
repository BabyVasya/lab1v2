package org.example.iec61850datatypes.measurements;

import org.example.iec61850datatypes.Data;
import org.example.iec61850datatypes.common.Attribute;
import org.example.iec61850datatypes.common.Quality;
import org.example.iec61850datatypes.common.Timestamp;

//Информация о срабатывании защиты

@lombok.Data
public class ACT extends Data {
    private Attribute<Boolean> general = new Attribute<>();
    private Attribute<Boolean> phsA = new Attribute<>();
    private Attribute<Boolean> phsB = new Attribute<>();
    private Attribute<Boolean> phsC = new Attribute<>();
    private Attribute<Boolean> neut = new Attribute<>();
    private Quality q = new Quality();
    private Timestamp t = new Timestamp();
    private Timestamp operTmPhsA = new Timestamp(); //Время работы для фазы. Используется для точечного переключения.
    private Timestamp operTmPhsB = new Timestamp();
    private Timestamp operTmPhsC = new Timestamp();
}
