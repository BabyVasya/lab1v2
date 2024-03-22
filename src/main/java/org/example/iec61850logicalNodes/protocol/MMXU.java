package org.example.iec61850logicalNodes.protocol;

import lombok.Data;
import org.example.filter.Filter;
import org.example.filter.FourierFilter;
import org.example.iec61850datatypes.measurements.MV;
import org.example.iec61850datatypes.measurements.WYE;
import org.example.iec61850logicalNodes.common.LN;
@Data
public class MMXU extends LN {
    //Отфильтрованные фазные токи, то есть выход узла
    private WYE A = new WYE();
    //Мгновенные значения в фазах, то есть вход узла
    public MV  phsAInst;
    public MV  phsBInst;
    public MV  phsCInst;

    public MMXU(MV  phsAInst, MV  phsBInst, MV  phsCInst) {
        this.phsAInst = phsAInst;
        this.phsBInst = phsBInst;
        this.phsCInst = phsCInst;
    }

    //Фильтры для передачи в процесс
    private Filter phsAFiltration = new FourierFilter(20);
    private Filter phsBFiltration = new FourierFilter(20);
    private Filter phsCFiltration = new FourierFilter(20);

    @Override
    public void process() {
        phsAFiltration.process(phsAInst, A.getPhsA());
        phsBFiltration.process(phsBInst, A.getPhsB());
        phsCFiltration.process(phsCInst, A.getPhsC());
    }
}
