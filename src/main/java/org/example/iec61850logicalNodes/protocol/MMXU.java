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
    private WYE Z = new WYE();
    private WYE  PhV = new WYE();
    private DEL PPV = new DEL();
    private DEL PPA = new DEL();


    //Мгновенные значения в фазах, то есть вход узла
    private MV  phsAInstV;
    private MV  phsBInstV;
    private MV  phsCInstV;
    private MV  phsAInstC;
    private MV  phsBInstC;
    private MV  phsCInstC;

    public MMXU(MV  phsAInstC, MV  phsBInstC, MV  phsCInstC, MV  phsAInstV, MV  phsBInstV, MV  phsCInstV) {
        this.phsAInstV = phsAInstV;
        this.phsBInstV = phsBInstV;
        this.phsCInstV = phsCInstV;
        this.phsAInstC = phsAInstC;
        this.phsBInstC = phsBInstC;
        this.phsCInstC = phsCInstC;
    }


    //Фильтры для передачи в процесс
    private FourierFilter phsAFiltrationC = new FourierFilter(80);
    private FourierFilter phsBFiltrationC = new FourierFilter(80);
    private FourierFilter phsCFiltrationC = new FourierFilter(80);
    private FourierFilter phsAFiltrationV = new FourierFilter(80);
    private FourierFilter phsBFiltrationV = new FourierFilter(80);
    private FourierFilter phsCFiltrationV = new FourierFilter(80);

    @Override
    public void process() {
        phsAFiltrationC.process(phsAInstC, A.getPhsA());
        phsBFiltrationC.process(phsBInstC, A.getPhsB());
        phsCFiltrationC.process(phsCInstC, A.getPhsC());

        phsAFiltrationV.process(phsAInstV, PhV.getPhsA());
        phsBFiltrationV.process(phsBInstV, PhV.getPhsB());
        phsCFiltrationV.process(phsCInstV, PhV.getPhsC());
        convertToPhToPhOrto();
        convertToPhToPhPolar();

        Z.getPhsA().getCVal().getMag().getF().setValue(
             PPV.getPhsAB().getCVal().getMag().getF().getValue()/PPA.getPhsAB().getCVal().getMag().getF().getValue()
        );

        Z.getPhsB().getCVal().getMag().getF().setValue(
                PPV.getPhsBC().getCVal().getMag().getF().getValue()/PPA.getPhsBC().getCVal().getMag().getF().getValue()
        );

        Z.getPhsC().getCVal().getMag().getF().setValue(
                PPV.getPhsAC().getCVal().getMag().getF().getValue()/PPA.getPhsAC().getCVal().getMag().getF().getValue()
        );
    }

    private void convertToPhToPhOrto() {

        PPV.getPhsAB().getCVal().getRe().getF().setValue(
                PhV.getPhsA().getCVal().getMag().getF().getValue()*Math.cos(PhV.getPhsA().getCVal().getAng().getF().getValue())
        - PhV.getPhsB().getCVal().getMag().getF().getValue()*Math.cos(PhV.getPhsB().getCVal().getAng().getF().getValue()));

        PPV.getPhsAB().getCVal().getIm().getF().setValue(
                PhV.getPhsA().getCVal().getMag().getF().getValue()*Math.sin(PhV.getPhsA().getCVal().getAng().getF().getValue())
                        - PhV.getPhsB().getCVal().getMag().getF().getValue()*Math.sin(PhV.getPhsB().getCVal().getAng().getF().getValue()));

        PPV.getPhsBC().getCVal().getRe().getF().setValue(
                PhV.getPhsB().getCVal().getMag().getF().getValue()*Math.cos(PhV.getPhsB().getCVal().getAng().getF().getValue())
                        - PhV.getPhsC().getCVal().getMag().getF().getValue()*Math.cos(PhV.getPhsC().getCVal().getAng().getF().getValue()));

        PPV.getPhsBC().getCVal().getIm().getF().setValue(
                PhV.getPhsB().getCVal().getMag().getF().getValue()*Math.sin(PhV.getPhsB().getCVal().getAng().getF().getValue())
                        - PhV.getPhsC().getCVal().getMag().getF().getValue()*Math.sin(PhV.getPhsC().getCVal().getAng().getF().getValue()));

        PPV.getPhsAC().getCVal().getRe().getF().setValue(
                PhV.getPhsA().getCVal().getMag().getF().getValue()*Math.cos(PhV.getPhsA().getCVal().getAng().getF().getValue())
                        - PhV.getPhsC().getCVal().getMag().getF().getValue()*Math.cos(PhV.getPhsC().getCVal().getAng().getF().getValue()));

        PPV.getPhsAC().getCVal().getIm().getF().setValue(
                PhV.getPhsA().getCVal().getMag().getF().getValue()*Math.sin(PhV.getPhsA().getCVal().getAng().getF().getValue())
                        - PhV.getPhsC().getCVal().getMag().getF().getValue()*Math.sin(PhV.getPhsC().getCVal().getAng().getF().getValue()));



        PPA.getPhsAB().getCVal().getRe().getF().setValue(
                A.getPhsA().getCVal().getMag().getF().getValue()*Math.cos(A.getPhsA().getCVal().getAng().getF().getValue())
                        - A.getPhsB().getCVal().getMag().getF().getValue()*Math.cos(A.getPhsB().getCVal().getAng().getF().getValue()));

        PPA.getPhsAB().getCVal().getIm().getF().setValue(
                A.getPhsA().getCVal().getMag().getF().getValue()*Math.sin(A.getPhsA().getCVal().getAng().getF().getValue())
                        - A.getPhsB().getCVal().getMag().getF().getValue()*Math.sin(A.getPhsB().getCVal().getAng().getF().getValue()));

        PPA.getPhsBC().getCVal().getRe().getF().setValue(
                A.getPhsB().getCVal().getMag().getF().getValue()*Math.cos(A.getPhsB().getCVal().getAng().getF().getValue())
                        - A.getPhsC().getCVal().getMag().getF().getValue()*Math.cos(A.getPhsC().getCVal().getAng().getF().getValue()));

        PPA.getPhsBC().getCVal().getIm().getF().setValue(
                A.getPhsB().getCVal().getMag().getF().getValue()*Math.sin(A.getPhsB().getCVal().getAng().getF().getValue())
                        - A.getPhsC().getCVal().getMag().getF().getValue()*Math.sin(A.getPhsC().getCVal().getAng().getF().getValue()));

        PPA.getPhsAC().getCVal().getRe().getF().setValue(
                A.getPhsA().getCVal().getMag().getF().getValue()*Math.cos(A.getPhsA().getCVal().getAng().getF().getValue())
                        - A.getPhsC().getCVal().getMag().getF().getValue()*Math.cos(A.getPhsC().getCVal().getAng().getF().getValue()));

        PPA.getPhsAC().getCVal().getIm().getF().setValue(
                A.getPhsA().getCVal().getMag().getF().getValue()*Math.sin(A.getPhsA().getCVal().getAng().getF().getValue())
                        - A.getPhsC().getCVal().getMag().getF().getValue()*Math.sin(A.getPhsC().getCVal().getAng().getF().getValue()));

    }

    private void convertToPhToPhPolar() {
        PPV.getPhsAB().getCVal().getMag().getF().setValue(
                Math.sqrt(Math.pow(PPV.getPhsAB().getCVal().getRe().getF().getValue(), 2) +
                        Math.pow(PPV.getPhsAB().getCVal().getIm().getF().getValue(), 2))
        );
        PPV.getPhsAB().getCVal().getAng().getF().setValue(
                Math.atan(PPV.getPhsAB().getCVal().getIm().getF().getValue()/
                        PPV.getPhsAB().getCVal().getRe().getF().getValue())
        );

        PPV.getPhsBC().getCVal().getMag().getF().setValue(
                Math.sqrt(Math.pow(PPV.getPhsBC().getCVal().getRe().getF().getValue(), 2) +
                        Math.pow(PPV.getPhsBC().getCVal().getIm().getF().getValue(), 2))
        );
        PPV.getPhsBC().getCVal().getAng().getF().setValue(
                Math.atan(PPV.getPhsBC().getCVal().getIm().getF().getValue()/
                        PPV.getPhsBC().getCVal().getRe().getF().getValue())
        );

        PPV.getPhsAC().getCVal().getMag().getF().setValue(
                Math.sqrt(Math.pow(PPV.getPhsAC().getCVal().getRe().getF().getValue(), 2) +
                        Math.pow(PPV.getPhsAC().getCVal().getIm().getF().getValue(), 2))
        );
        PPV.getPhsAC().getCVal().getAng().getF().setValue(
                Math.atan(PPV.getPhsAC().getCVal().getIm().getF().getValue()/
                        PPV.getPhsAC().getCVal().getRe().getF().getValue())
        );


        PPA.getPhsAB().getCVal().getMag().getF().setValue(
                Math.sqrt(Math.pow(PPA.getPhsAB().getCVal().getRe().getF().getValue(), 2) +
                        Math.pow(PPA.getPhsAB().getCVal().getIm().getF().getValue(), 2))
        );
        PPA.getPhsAB().getCVal().getAng().getF().setValue(
                Math.atan(PPA.getPhsAB().getCVal().getIm().getF().getValue()/
                        PPA.getPhsAB().getCVal().getRe().getF().getValue())
        );

        PPA.getPhsBC().getCVal().getMag().getF().setValue(
                Math.sqrt(Math.pow(PPA.getPhsBC().getCVal().getRe().getF().getValue(), 2) +
                        Math.pow(PPA.getPhsBC().getCVal().getIm().getF().getValue(), 2))
        );
        PPA.getPhsBC().getCVal().getAng().getF().setValue(
                Math.atan(PPA.getPhsBC().getCVal().getIm().getF().getValue()/
                        PPA.getPhsBC().getCVal().getRe().getF().getValue())
        );

        PPA.getPhsAC().getCVal().getMag().getF().setValue(
                Math.sqrt(Math.pow(PPA.getPhsAC().getCVal().getRe().getF().getValue(), 2) +
                        Math.pow(PPA.getPhsAC().getCVal().getIm().getF().getValue(), 2))
        );
        PPA.getPhsAC().getCVal().getAng().getF().setValue(
                Math.atan(PPA.getPhsAC().getCVal().getIm().getF().getValue()/
                        PPA.getPhsAC().getCVal().getRe().getF().getValue())
        );
    }

}
