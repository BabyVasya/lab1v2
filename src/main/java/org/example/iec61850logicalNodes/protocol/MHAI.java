package org.example.iec61850logicalNodes.protocol;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.filter.FourierFilter;
import org.example.iec61850datatypes.measurements.HWYE;
import org.example.iec61850datatypes.measurements.MV;
import org.example.iec61850logicalNodes.common.LN;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
public class MHAI extends LN {
    private MV  phsAInstC;
    private MV  phsBInstC;
    private MV  phsCInstC;
    private  int discFreq;

    private HWYE HA = new HWYE();

    private List<FourierFilter> phsACurF = new ArrayList<>();
    private List<FourierFilter> phsBCurF = new ArrayList<>();
    private List<FourierFilter> phsCCurF = new ArrayList<>();

    public MHAI(MV  phsAInstC, MV  phsBInstC, MV  phsCInstC, int discFreq) {
        this.phsAInstC = phsAInstC;
        this.phsBInstC = phsBInstC;
        this.phsCInstC = phsCInstC;
        this.discFreq = discFreq;
        setFilters();
    }

    @Override
    public void process() {
        for (int harm = 1; harm <= HA.getNumHar(); harm++){
            phsACurF.get(harm - 1).process(phsAInstC, HA.getPhsAH().get(harm - 1), 50);
            phsBCurF.get(harm - 1).process(phsBInstC, HA.getPhsBH().get(harm - 1), 50);
            phsCCurF.get(harm - 1).process(phsCInstC, HA.getPhsCH().get(harm - 1), 50);
        }
    }

    private void setFilters() {

        for (int harm = 1; harm <= HA.getNumHar(); harm++) {
            phsACurF.add(new FourierFilter(this.discFreq, harm));
            phsBCurF.add(new FourierFilter(this.discFreq, harm));
            phsCCurF.add(new FourierFilter(this.discFreq, harm));
        }
    }
}
