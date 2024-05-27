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
    public PTOC(WYE A){
        this.A = A;
    }

    @Override
    public void process() {
        Op.getGeneral().setValue(A.getPhsA().getCVal().getMag().getF().getValue() > StrVal.getSetMag().getF().getValue() ||
                A.getPhsB().getCVal().getMag().getF().getValue() > StrVal.getSetMag().getF().getValue() ||
                A.getPhsC().getCVal().getMag().getF().getValue() > StrVal.getSetMag().getF().getValue() );
    }


}

