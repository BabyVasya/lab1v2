package org.example;

import lombok.Data;
import org.example.iec61850logicalNodes.common.LN;
import org.example.iec61850logicalNodes.protocol.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Nodes")
public class NodesFactory {
    private LSVS lsvs;
    private MMXU mmxu;
    private List<PTOC> ptoc = new ArrayList<>();
    private XCBR xcbr;
    private CSWI cswi;
    public void createNode(nodeType nodeType) {
        switch(nodeType) {
            case LSVS:
                this.lsvs = new LSVS();
                break;
            case MMXU:
                this.mmxu = new MMXU();
                break;
            case PTOC:
                this.ptoc.add(new PTOC());
                break;
            case CSWI:
                this.cswi = new CSWI();
                break;
            case XCBR:
                this.xcbr = new XCBR();
                break;
        }
    }
    public enum nodeType {
        LSVS,
        MMXU,
        PTOC,
        CSWI,
        XCBR
    }
}
