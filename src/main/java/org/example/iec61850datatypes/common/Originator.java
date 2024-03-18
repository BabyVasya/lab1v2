package org.example.iec61850datatypes.common;

import org.example.iec61850datatypes.Data;
@lombok.Data
public class Originator extends Data {
    //Категория отправителя должна указывать категорию отправителя
    //Возможно имеется ввиду то, откуда пришло управление
    private enum orCat {
        NOTSUPPORTED,
        BAYCONTROL,
        STATIONCONTROL,
        REMOTECONTROL,
        AUTOMATICBAY,
        AUTOMATICSTATION,
        AUTOMATICREMOTE,
        MAINTENANCE,
        PROCESS

    }
    //Что-то вроде адреса (идентификатора) отправителя, null - значит индентификатор неизвестен
    private String orldent;

}
