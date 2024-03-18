package org.example.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.iec61850datatypes.measurements.CMV;
import org.example.iec61850datatypes.measurements.MV;

@Data
@Slf4j
public class Fourier extends Filter{
    private final int bufferSize = 20;
    private double[] bufferRe = new double[bufferSize];
    private double[] bufferIm = new double[bufferSize];
    private double Ire;
    private double Iim;
    private double sumRe;
    private double sumIm;
    private int bCount;



    @Override
    public void process(MV inputValue, CMV  outputValue) {
//        for (int i =0; i < inputValue.getInstMag().getF().size()-1 ; i++) {
//            Ire = inputValue.getInstMag().getF().get(i) * Math.sin(2 * Math.PI * 50 * i * 0.02/bufferSize);
//            Iim = inputValue.getInstMag().getF().get(i) * Math.cos(2 * Math.PI * 50 * i * 0.02/bufferSize);
//
//            sumRe += Ire - bufferRe[bCount];
//            bufferRe[bCount] = Ire;
//            sumIm += Iim - bufferIm[bCount];
//            bufferIm[bCount] = Iim;
//
//            bCount++;
//            if (bCount >= bufferSize) bCount=0;
//
//            outputValue.getCVal().getMag().getF().set(i, Math.sqrt(Math.pow(sumRe * 0.1, 2) + Math.pow(sumIm * 0.1, 2))/Math.sqrt(2));
//        }
//        outputValue.getQ().setValue(inputValue.getQ().getValue());
//        outputValue.getT().setValue(inputValue.getT().getValue());
//        log.info(String.valueOf(outputValue));
    }
}
