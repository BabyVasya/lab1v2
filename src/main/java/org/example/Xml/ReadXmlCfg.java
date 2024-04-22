package org.example.Xml;

import lombok.extern.slf4j.Slf4j;
import org.example.NodesFactory;
import org.example.iec61850logicalNodes.protocol.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
@Slf4j
public class ReadXmlCfg<T> {

    public static NodesFactory writeConfig() {
        NodesFactory nodesFactory = new NodesFactory();
        nodesFactory.createNode(NodesFactory.nodeType.LSVS);
        nodesFactory.createNode(NodesFactory.nodeType.MMXU);
        nodesFactory.createNode(NodesFactory.nodeType.PTOC);
        {
            try {
                JAXBContext context =
                        JAXBContext.newInstance(NodesFactory.class, LSVS.class, MMXU.class, PTOC.class, XCBR.class, CSWI.class);
                Marshaller marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                File outFile = new File("src/main/resources/Config.xml");
                marshaller.marshal(nodesFactory, outFile);
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
        }
        return nodesFactory;
    }
    public static NodesFactory readConfig() {

        NodesFactory cfg = null;
        {
            try {
                JAXBContext context =
                        JAXBContext.newInstance(NodesFactory.class, LSVS.class, MMXU.class, PTOC.class, XCBR.class, CSWI.class);
                Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
                cfg = (NodesFactory) jaxbUnmarshaller.unmarshal(new
                        File("src/main/resources/Config.xml"));
                cfg.getMmxu().setPhsAInst(cfg.getLsvs().getOutA());
                cfg.getMmxu().setPhsBInst(cfg.getLsvs().getOutB());
                cfg.getMmxu().setPhsCInst(cfg.getLsvs().getOutC());
                cfg.getPtoc().get(0).setA(cfg.getMmxu().getA());
                cfg.getPtoc().get(1).setA(cfg.getMmxu().getA());
                cfg.getPtoc().get(2).setA(cfg.getMmxu().getA());
                cfg.getCswi().setOpOpn1(cfg.getPtoc().get(0).getOp());
                cfg.getCswi().setOpOpn2(cfg.getPtoc().get(1).getOp());
                cfg.getCswi().setOpOpn3(cfg.getPtoc().get(2).getOp());
                cfg.getXcbr().setPos(cfg.getCswi().getPos());
                cfg.getXcbr().getOpCnt().getStVal().setValue(0);
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
        }
        return cfg;
    }

}
