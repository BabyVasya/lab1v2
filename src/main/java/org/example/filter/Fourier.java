package org.example.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.iec61850datatypes.common.DataAttribute;
import org.example.iec61850datatypes.measurements.CMV;
import org.example.iec61850datatypes.measurements.ING;
import org.example.iec61850datatypes.measurements.MV;

@Data
@Slf4j
public class Fourier extends Filter{
    private ING bSize = new ING();
    private final MV[] buffer;
    public DataAttribute<Integer> bCount = new DataAttribute<>();
    public DataAttribute<Double> rVal = new DataAttribute<>();
    public DataAttribute<Double> imVal = new DataAttribute<>();
    public DataAttribute<Double> freq = new DataAttribute<>();
    public DataAttribute<Double> dT = new DataAttribute<>();

    public Fourier(int bufferSize){
        bSize.getSetVal().setValue(bufferSize);
        bCount.setValue(0);
        rVal.setValue(0D);
        imVal.setValue(0D);
        freq.setValue(50D);
        dT.setValue(0.02 / bSize.getSetVal().getValue());
        buffer = new MV[bSize.getSetVal().getValue()];

        for (int i = 0; i < bSize.getSetVal().getValue(); i++) {
            MV tempVal = new MV();
            tempVal.getInstMag().getF().setValue(0D);
            buffer[i] = tempVal;
        }
    }

    @Override
    public void process(MV measuredValue, CMV result) {

        rVal.setValue(rVal.getValue()
                + (measuredValue.getInstMag().getF().getValue()
                - buffer[bCount.getValue()].getInstMag().getF().getValue())
                * Math.sin(2 * Math.PI * freq.getValue() * bCount.getValue() * dT.getValue())
                * 2 / bSize.getSetVal().getValue()
        );
        imVal.setValue(imVal.getValue()
                + (measuredValue.getInstMag().getF().getValue()
                - buffer[bCount.getValue()].getInstMag().getF().getValue())
                * Math.cos(2 * Math.PI * freq.getValue() * bCount.getValue() * dT.getValue())
                * 2 / bSize.getSetVal().getValue()
        );

        result.getCVal().getMag().getF().setValue(
                0.7071068 * Math.sqrt(Math.pow(rVal.getValue(), 2) + Math.pow(imVal.getValue(), 2))
        );

        result.getCVal().getAng().getF().setValue(
                Math.atan(imVal.getValue() / rVal.getValue()) * 180 / Math.PI
        );

        buffer[bCount.getValue()].getInstMag().getF().setValue(measuredValue.getInstMag().getF().getValue());
        bCount.setValue(bCount.getValue() + 1);
        if (bCount.getValue() >= bSize.getSetVal().getValue()){
            bCount.setValue(0);
        }
        log.info(String.valueOf(result));
    }
}
