package org.example.iec61850logicalNodes.protocol;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.iec61850datatypes.measurements.ACD;
import org.example.iec61850datatypes.measurements.HWYE;
import org.example.iec61850logicalNodes.common.LN;
@Data
@Slf4j
public class PHAR extends LN {
    private HWYE HA = new HWYE();
    private ACD Str = new ACD();

    @Override
    public void process() {
         double phsAHar1 = HA.getPhsAH().get(0).getCVal().getMag().getF().getValue();
         double phsBHar1 = HA.getPhsBH().get(0).getCVal().getMag().getF().getValue();
         double phsCHar1 = HA.getPhsCH().get(0).getCVal().getMag().getF().getValue();

         double phsAHar5 = HA.getPhsAH().get(4).getCVal().getMag().getF().getValue();
         double phsBHar5 = HA.getPhsBH().get(4).getCVal().getMag().getF().getValue();
         double phsCHar5 = HA.getPhsCH().get(4).getCVal().getMag().getF().getValue();

         Str.getPhsA().setValue((phsAHar5 / phsAHar1) > 0.2);
         Str.getPhsB().setValue((phsBHar5 / phsBHar1) > 0.2);
         Str.getPhsC().setValue((phsCHar5 / phsCHar1) > 0.2);
         Str.getGeneral().setValue(Str.getPhsA().getValue() || Str.getPhsB().getValue() || Str.getPhsC().getValue());
         log.info("Блокировка по 5 гармонике " + String.valueOf(Str.getGeneral().getValue()) + " отношение " + phsAHar5 / phsAHar1);
    }
}
