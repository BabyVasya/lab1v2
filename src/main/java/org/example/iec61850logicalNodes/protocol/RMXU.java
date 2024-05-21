package org.example.iec61850logicalNodes.protocol;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.iec61850datatypes.measurements.SAV;
import org.example.iec61850datatypes.measurements.WYE;
import org.example.iec61850logicalNodes.common.LN;

@Data
@Slf4j
public class RMXU extends LN {
    private WYE ALoc1 = new WYE();
    private WYE ALoc2 = new WYE();
    private WYE dif = new WYE();
    private SAV AmpLocPhsA = new SAV();
    private SAV AmpLocPhsB = new SAV();
    private SAV AmpLocPhsC = new SAV();
    private SAV AmpLocRes = new SAV();

    @Override
    public void process() {
        double sumRe = ALoc1.getPhsA().getCVal().getRe().getF().getValue() + ALoc2.getPhsA().getCVal().getRe().getF().getValue();
        double sumIm = ALoc1.getPhsA().getCVal().getIm().getF().getValue() + ALoc2.getPhsA().getCVal().getIm().getF().getValue();
        dif.getPhsA().getCVal().getMag().getF().setValue(Math.sqrt(Math.pow(sumRe, 2) + Math.pow(sumIm, 2)));
        log.info(String.valueOf(dif.getPhsA().getCVal().getMag().getF().getValue()));
    }
}
