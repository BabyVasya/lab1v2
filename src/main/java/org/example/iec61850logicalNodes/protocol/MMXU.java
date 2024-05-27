package org.example.iec61850logicalNodes.protocol;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.filter.FourierFilter;
import org.example.iec61850datatypes.measurements.DEL;
import org.example.iec61850datatypes.measurements.MV;
import org.example.iec61850datatypes.measurements.WYE;
import org.example.iec61850logicalNodes.common.LN;

@Data
@Slf4j
public class MMXU extends LN {
    //Отфильтрованные фазные токи, то есть выход узла
    private WYE A = new WYE();
    private  int discFreq;

    //Мгновенные значения в фазах, то есть вход узла
    private MV  phsAInstC;
    private MV  phsBInstC;
    private MV  phsCInstC;
    private FourierFilter phsAFiltrationC;
    private FourierFilter phsBFiltrationC;
    private FourierFilter phsCFiltrationC;

    public MMXU(MV  phsAInstC, MV  phsBInstC, MV  phsCInstC, int discFreq) {
        this.phsAInstC = phsAInstC;
        this.phsBInstC = phsBInstC;
        this.phsCInstC = phsCInstC;
        this.discFreq = discFreq;
        this.phsAFiltrationC = new FourierFilter(this.discFreq, 1);
        this.phsBFiltrationC = new FourierFilter(this.discFreq, 1);
        this.phsCFiltrationC = new FourierFilter(this.discFreq, 1);
    }


    //Фильтры для передачи в процесс



    @Override
    public void process() {
        phsAFiltrationC.process(phsAInstC, A.getPhsA(), 50);
        phsBFiltrationC.process(phsBInstC, A.getPhsB(), 50);
        phsCFiltrationC.process(phsCInstC, A.getPhsC(), 50);

        A.getPhsA().getCVal().getRe().getF().setValue(A.getPhsA().getCVal().getMag().getF().getValue()*Math.cos(A.getPhsA().getCVal().getAng().getF().getValue()));
        A.getPhsA().getCVal().getIm().getF().setValue(A.getPhsA().getCVal().getMag().getF().getValue()*Math.sin(A.getPhsA().getCVal().getAng().getF().getValue()));

        A.getPhsB().getCVal().getRe().getF().setValue(A.getPhsB().getCVal().getMag().getF().getValue()*Math.cos(A.getPhsB().getCVal().getAng().getF().getValue()));
        A.getPhsB().getCVal().getIm().getF().setValue(A.getPhsB().getCVal().getMag().getF().getValue()*Math.sin(A.getPhsB().getCVal().getAng().getF().getValue()));

        A.getPhsC().getCVal().getRe().getF().setValue(A.getPhsC().getCVal().getMag().getF().getValue()*Math.cos(A.getPhsC().getCVal().getAng().getF().getValue()));
        A.getPhsC().getCVal().getIm().getF().setValue(A.getPhsC().getCVal().getMag().getF().getValue()*Math.sin(A.getPhsC().getCVal().getAng().getF().getValue()));
    }






}
