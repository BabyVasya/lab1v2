package org.example.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.iec61850datatypes.measurements.CMV;
import org.example.iec61850datatypes.measurements.MV;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Data
@Slf4j
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "FourierFilter")
public class FourierFilter extends Filter{
    private int bufferSize;
    private double[] buffer = new double[9999];
    private final double[] bufferX = new double[9999];
    private final double[] bufferY = new double[9999];
    public int  i;
    public double x;
    public double y;
    public double frequency;
    public double T;
    private double k;


    public FourierFilter(int bufferSize){
        this.bufferSize = bufferSize;
        this.k  =(double) 2/this.bufferSize;
        this.frequency  = 50;
    }


    public void process(MV input, CMV output) {
        double value = input.getInstMag().getF().getValue();

        x += k * value * Math.sin((2 * Math.PI * frequency) * ((1/frequency)/bufferSize) * i) - bufferX[i];
        y += k * value * Math.cos((2 * Math.PI * frequency) * ((1/frequency)/bufferSize) * i) - bufferY[i];

        bufferX[i] = (k * value * Math.sin((2 * Math.PI * frequency) * ((1/frequency)/bufferSize) * i));
        bufferY[i] = (k * value * Math.cos((2 * Math.PI * frequency) * ((1/frequency)/bufferSize) * i));

       log.info(String.valueOf(bufferSize));
       output.getCVal().getMag().getF().setValue(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2))/Math.sqrt(2));
       output.getCVal().getAng().getF().setValue(Math.atan(y / x));
       output.getT().setValue(input.getT().getValue());

        i = i + 1;
        if (i >= this.bufferSize ) i =0;
    }
}
