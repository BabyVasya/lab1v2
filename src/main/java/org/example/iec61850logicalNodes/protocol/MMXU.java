package org.example.iec61850logicalNodes.protocol;

import lombok.Data;
import org.example.filter.FourierFilter;
import org.example.iec61850datatypes.measurements.MV;
import org.example.iec61850datatypes.measurements.WYE;
import org.example.iec61850logicalNodes.common.LN;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "MMXU")
public class MMXU extends LN {
    //Отфильтрованные фазные токи, то есть выход узла
    private WYE A = new WYE();
    //Мгновенные значения в фазах, то есть вход узла
    public MV  phsAInst;
    public MV  phsBInst;
    public MV  phsCInst;

    public MMXU() {

    }
    public MMXU(MV  phsAInst, MV  phsBInst, MV  phsCInst) {
        this.phsAInst = phsAInst;
        this.phsBInst = phsBInst;
        this.phsCInst = phsCInst;
    }


    //Фильтры для передачи в процесс
    private FourierFilter phsAFiltration = new FourierFilter(20);
    private FourierFilter phsBFiltration = new FourierFilter(20);
    private FourierFilter phsCFiltration = new FourierFilter(20);

    @Override
    public void process() {
        phsAFiltration.process(phsAInst, A.getPhsA());
        phsBFiltration.process(phsBInst, A.getPhsB());
        phsCFiltration.process(phsCInst, A.getPhsC());
    }
}
