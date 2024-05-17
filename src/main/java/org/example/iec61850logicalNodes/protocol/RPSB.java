package org.example.iec61850logicalNodes.protocol;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.iec61850datatypes.measurements.*;
import org.example.iec61850logicalNodes.common.LN;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Data
@Slf4j
public class RPSB extends LN {
    private ACD Str = new ACD();
    private SPS blkZn = new SPS();
    private SEQ seqA = new SEQ();
    private final int bufferSize = 80;
    private ING OpDlTmmsLast = new ING();

    private List<SEQ> seqABuffer = new ArrayList<>();

    private ASG SwgAVal = new ASG();
    private ASG SwgVVal = new ASG();
    private int count;
    private int timer;
    private boolean timerStart;

    public RPSB() {
        SEQ seq = new SEQ();
        seq.getC2().getCVal().getMag().getF().setValue(0.0);
        seqABuffer.add(seq);
    }


    @Override
    public void process() {
        double avar2;
        if (count < 80) avar2 = 0;
        else avar2 = seqA.getC2().getCVal().getMag().getF().getValue() - seqABuffer.get(count-bufferSize).getC2().getCVal().getMag().getF().getValue();
        if (timer ==0) blkZn.getStVal().setValue(Math.abs(avar2 ) < 10);

        if (!timerStart && !blkZn.getStVal().getValue()) {
            timerStart = true;
        }
        if (timerStart &&  OpDlTmmsLast.getSetVal().getValue() > timer) {
            timer+=1;
            blkZn.getStVal().setValue(false);
//            log.info("Timer " + timer + " blck " + blkZn.getStVal().getValue());
        }
        else if(timerStart && OpDlTmmsLast.getSetVal().getValue() == timer) {
            timer = 0;
            timerStart = false;
        }
//        log.info("Разница " + String.valueOf(avar2));
        SEQ clonedSeqA = new SEQ();
        clonedSeqA.getC2().getCVal().getMag().getF().setValue(seqA.getC2().getCVal().getMag().getF().getValue());
        seqABuffer.add(clonedSeqA);
        count = count + 1;
    }

}
