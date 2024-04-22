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
    private MV[] buffer;
    public int  Ii;
    public double Ix;
    public double Iy;
    public double freq;
    public double T;

    public FourierFilter() {
    }

    public FourierFilter(int bufferSize){
        this.bufferSize = bufferSize;
        this.buffer = new MV[bufferSize];
        this.Ii = 0;
        this.Ix = 0;
        this.Iy = 0;
        this.freq = 50;
        this.T = (0.02 / bufferSize);

        for (int i = 0; i < bufferSize; i++) {
            MV Iphs = new MV();
            Iphs.getInstMag().getF().setValue(0.0);
            buffer[i] = Iphs;
        }
    }

    @Override
    public void process(MV input, CMV output) {

        Ix = Ix + (input.getInstMag().getF().getValue() - buffer[Ii].getInstMag().getF().getValue()) * Math.sin(2 * Math.PI * freq * Ii * T) * 2 / bufferSize;

        Iy = Iy + (input.getInstMag().getF().getValue() - buffer[Ii].getInstMag().getF().getValue()) * Math.cos(2 * Math.PI * freq * Ii * T) * 2 / bufferSize;

        output.getCVal().getMag().getF().setValue(Math.sqrt(Math.pow(Ix, 2) + Math.pow(Iy, 2))/Math.sqrt(2));
        output.getCVal().getAng().getF().setValue(Math.atan(Iy / Ix) * 180 / Math.PI);
        output.getT().setValue(input.getT().getValue());

        buffer[Ii].getInstMag().getF().setValue(input.getInstMag().getF().getValue());
        Ii = Ii + 1;
        if (Ii >= bufferSize) Ii =0;

//        log.info(String.valueOf(output));
    }
}
