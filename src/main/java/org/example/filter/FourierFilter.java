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
public class FourierFilter {
    private int bufferSize;
    private double[] buffer = new double[9999];
    private final double[] bufferX = new double[9999];
    private final double[] bufferY = new double[9999];
    public int  i;
    public double x;
    public double y;
    public double T;
    private double k;
    private double discFreq;
    private double prevValue;
    private int harmNum;


    public FourierFilter(int discFreq, int harmNum){
        this.discFreq = discFreq;
        this.bufferSize = (int) (this.discFreq/40);
        this.k  =(double) 2/this.bufferSize;
        this.harmNum = harmNum;

    }


    public void process(MV input, CMV output , double frequency) {

       if (frequency == 0 || Double.isNaN(frequency)) frequency = 50*harmNum;
       else frequency = frequency*harmNum;
       double bufferSizeDouble = this.discFreq / (frequency/harmNum);
        this.bufferSize = (int) ( this.discFreq/(frequency/harmNum));

       double fractionalPart = bufferSizeDouble - bufferSize ;
       double value = input.getInstMag().getF().getValue() + (input.getInstMag().getF().getValue() - this.prevValue)*fractionalPart;
       this.k  =(double) 2/bufferSizeDouble;


       x += k * value * Math.sin((2 * Math.PI * frequency) * ((1/(frequency/harmNum))/bufferSize) * (i+fractionalPart)) - bufferX[i];
       y += k * value * Math.cos((2 * Math.PI * frequency) * ((1/(frequency/harmNum))/bufferSize) * (i+fractionalPart)) - bufferY[i];

       bufferX[i] = (k * value * Math.sin((2 * Math.PI * frequency) * ((1/(frequency/harmNum))/bufferSize) * (i+fractionalPart)));
       bufferY[i] = (k * value * Math.cos((2 * Math.PI * frequency) * ((1/(frequency/harmNum))/bufferSize) * (i+fractionalPart)));
       output.getCVal().getMag().getF().setValue(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2))/Math.sqrt(2));
       output.getCVal().getAng().getF().setValue(Math.atan2(y , x));
       output.getT().setValue(input.getT().getValue());
       output.getCVal().getRe().getF().setValue(x);
       output.getCVal().getIm().getF().setValue(y);


//        log.info("Размер буфера " + this.bufferSize + " x " + x + " y " + y + " bad " + (1/(frequency/harmNum)) + " k " + k);
       i = i + 1;
       if (i >= this.bufferSize ) i =0;
       this.prevValue = value;
       i = i + 1;
       if (i >= this.bufferSize ) i =0;
       this.prevValue = value;


    }
}
