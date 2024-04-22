package org.example.iec61850logicalNodes.protocol;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.iec61850datatypes.measurements.*;
import org.example.iec61850logicalNodes.common.LN;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//Узел пусковых органов тока
@Data
@Slf4j
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "PTOC")
public class PTOC extends LN {

    private ACT Op = new ACT(); //Срабатывание
    private ACD Str = new ACD(); // Срабатываение направленной защиты (dir - directional)
    private INC OpCntRs;  //Сбрасываемый счетчик операций
    private CURVE TmACrv; //Настройки характеристик
    private CSG TmAChr33; // Связано с curve, какие то графики рисует
    private CSD TmASt;
    @XmlElement
    private ASG StrVal = new ASG(); //Уставка по току
    private ASG TmMult; //Множитель отсчета времени
    private ING MinOpTmms; //Минимальное время работы
    private ING MaxOpTmms; //Минимальное время работы
    @XmlElement
    private ING OpDlTmms = new ING(); //Выдержка времени
    private ENG TypRsCrv; //Тип сбрасываемой характеристики
    private ING RsDlTmms; //Сброс выдержки времени
    private ENG DirMod; //Направленный режим
    private WYE A = new WYE();//Входной отфильтрованный сигнал
    private int timer; //Отсчет реле времени
    public PTOC(){
    }

    @Override
    public void process() {
        //Провервка уставки
        Str.getPhsA().setValue(A.getPhsA().getCVal().getMag().getF().getValue() > this.StrVal.getSetMag().getF().getValue());
        Str.getPhsB().setValue(A.getPhsB().getCVal().getMag().getF().getValue() > this.StrVal.getSetMag().getF().getValue());
        Str.getPhsC().setValue(A.getPhsC().getCVal().getMag().getF().getValue() > this.StrVal.getSetMag().getF().getValue());
        Str.getGeneral().setValue(Str.getPhsA().getValue()||Str.getPhsB().getValue()||Str.getPhsC().getValue());

        if (Str.getGeneral().getValue()) timer+=OpDlTmms.getStepSize().getValue();
        else timer =0;

        // Сигнал на отключение если выдержка времени прошла
        Op.getGeneral().setValue(timer > OpDlTmms.getSetVal().getValue());
        Op.getPhsA().setValue(timer > OpDlTmms.getSetVal().getValue());
        Op.getPhsB().setValue(timer > OpDlTmms.getSetVal().getValue());
        Op.getPhsC().setValue(timer > OpDlTmms.getSetVal().getValue());
    }


}

