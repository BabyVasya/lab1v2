package org.example.iec61850logicalNodes.protocol;

import lombok.Data;
import org.example.filter.Filter;
import org.example.filter.Fourier;
import org.example.iec61850datatypes.measurements.MV;
import org.example.iec61850datatypes.measurements.WYE;
import org.example.iec61850logicalNodes.common.LN;
@Data
public class MMXU extends LN {
    private WYE A;
    private MV MinAPhs;
    private MV MaxAPhs;

    //мгновенные значения в фазах
    public MV phsAInst;
    public MV  phsBInst;
    public MV  phsCInst;

    public MMXU(MV  phsAInst, MV  phsBInst, MV  phsCInst) {
        this.phsAInst = phsAInst;
        this.phsBInst = phsBInst;
        this.phsCInst = phsCInst;
//        A = new WYE(phsAInst.getInstMag().getF().size());

    }

    //Создание экземпляров класса фильтра, для перобразования сигнала
    private final Filter phsACurrent = new Fourier();
    private final Filter phsBCurrent = new Fourier();
    private final Filter phsCCurrent = new Fourier();
    @Override
    public void process() {
        phsACurrent.process(phsAInst, A.getPhsA());
        phsBCurrent.process(phsBInst, A.getPhsB());
        phsCCurrent.process(phsCInst, A.getPhsC());
    }
}
