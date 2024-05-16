package org.example.iec61850logicalNodes.protocol;

import lombok.Data;

import lombok.extern.slf4j.Slf4j;
import org.example.iec61850datatypes.measurements.*;
import org.example.iec61850logicalNodes.common.LN;

//Узел пусковых органов тока
@Data
@Slf4j
public class PTOC extends LN {

    private ACT Op = new ACT(); //Срабатывание
    private ACT pusk= new ACT(); //Пуск
    private ACD Str = new ACD(); // Срабатываение направленной защиты (dir - directional)
    private INC OpCntRs = new INC();  //Сбрасываемый счетчик операций
    private CURVE TmACrv; //Настройки характеристик
    private CSG TmAChr33; // Связано с curve, какие то графики рисует
    private CSD TmASt;
    private ASG StrVal = new ASG(); //Уставка по току
    private ASG TmMult; //Множитель отсчета времени
    private ING MinOpTmms; //Минимальное время работы
    private ING MaxOpTmms; //Минимальное время работы
    private ING OpDlTmms = new ING(); //Выдержка времени
    private ENG TypRsCrv; //Тип сбрасываемой характеристики
    private ING RsDlTmms; //Сброс выдержки времени
    private ENG DirMod = new ENG(); //Направленный режим
    private WYE A;//Входной отфильтрованный сигнал
    private SEQ curA;//Последовательности тока
    private int timerDir; //Отсчет реле времени для напр
    private int timerUnDir; //Отсчет реле времени для ненапр
    private int boostTimer; //Время действия ускорения
    private boolean boost; // ускорение введено\отсутствует
    public PTOC(SEQ a){
        this.curA = a;
    }

    @Override
    public void process() {
        //Провервка уставки
        pusk.getGeneral().setValue(curA.getC3().getCVal().getMag().getF().getValue() > StrVal.getSetMag().getF().getValue());

        if(DirMod.getSetVal().getValue() == ACD.Direction.FORWARD
        && curA.getC3().getCVal().getMag().getF().getValue() > StrVal.getSetMag().getF().getValue()) timerDir += 1;

        else if(DirMod.getSetVal().getValue() == null
        && curA.getC3().getCVal().getMag().getF().getValue() > StrVal.getSetMag().getF().getValue()) timerUnDir+=1;

        else {
            timerUnDir = 0;
            timerDir = 0;
        }
        // Сигнал на отключение если выдержка времени прошла для ненаправленных ступеней
        Op.getGeneral().setValue(timerUnDir > OpDlTmms.getSetVal().getValue());
        // Сигнал на отключение если выдержка времени прошла для направленных ступеней
        Str.getGeneral().setValue(timerDir > OpDlTmms.getSetVal().getValue());


        if(Op.getGeneral().getValue() || Str.getGeneral().getValue() ) boost = true;
        if(boost) {
            boostTimer += 1;
            if(DirMod.getSetVal().getValue() == ACD.Direction.FORWARD
                    && curA.getC3().getCVal().getMag().getF().getValue() > StrVal.getSetMag().getF().getValue()) Op.getGeneral().setValue(boost);

            else if(DirMod.getSetVal().getValue() == null
                    && curA.getC3().getCVal().getMag().getF().getValue() > StrVal.getSetMag().getF().getValue()) Str.getGeneral().setValue(boost);
        }
        if(boostTimer >= getOpCntRs().getTOpOk().getValue()) {
            boostTimer = 0;
            boost = false;
            Op.getGeneral().setValue(timerUnDir > OpDlTmms.getSetVal().getValue());
            Str.getGeneral().setValue(timerDir > OpDlTmms.getSetVal().getValue());
        }

    }


}

