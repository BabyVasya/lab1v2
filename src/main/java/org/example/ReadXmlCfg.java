package org.example;

import org.example.iec61850logicalNodes.protocol.LSVS;
import org.example.iec61850logicalNodes.protocol.MMXU;
import org.example.iec61850logicalNodes.protocol.PTOC;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class ReadXmlCfg<T> {
    private LSVS lsvs;
    private MMXU mmxu;
    private PTOC ptoc;

    public PTOC readConfigPTOC() {
        PTOC cfg = null;
        {
            try {
                JAXBContext context =
                        JAXBContext.newInstance(PTOC.class);
                Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
                cfg = (PTOC) jaxbUnmarshaller.unmarshal(new
                        File("src/main/resources/PTOCcfg.xml"));

                String setMagValue = String.valueOf(cfg.getStrVal().getSetMag().getF().getValue());
                double setMagDouble = Double.parseDouble(setMagValue);
                cfg.getStrVal().getSetMag().getF().setValue(setMagDouble);
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
        }
        return cfg;
    }

    public LSVS readConfigLSVS() {
        LSVS cfg = null;
        {
            try {
                JAXBContext context =
                        JAXBContext.newInstance(LSVS.class);
                Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
                cfg = (LSVS) jaxbUnmarshaller.unmarshal(new
                        File("src/main/resources/LSVScfg.xml"));

            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
        }
        return cfg;
    }
}
