package org.example.iec61850datatypes.common;

import org.example.iec61850datatypes.Data;

public class Unit extends Data {
    //Е.И. СИ
    private enum SIUnit {
        dimensionless,
        meter,
        kilogram,
        second,
        ampere,
        Kelvin,
        mole,
        candela,
        volt,
        hertz

    }
    //Множители системы СИ
    private enum multiplier {
        Kilo,
        Mega,
        Milli

    }
}
