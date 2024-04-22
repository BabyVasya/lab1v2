package org.example.iec61850logicalNodes.protocol;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.iec61850datatypes.measurements.*;
import org.example.iec61850logicalNodes.common.LN;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@Slf4j
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CSWI")
public class CSWI extends LN {
    private SPS LocKey = new SPS(); //Локальный или удаленный ключ
    private SPS Loc = new SPS(); // Поведение местного управления
    private ACT OpOpn1 = new ACT(); //Отключение выключателя
    private ACT OpOpn2 = new ACT(); //Отключение выключателя
    private ACT OpOpn3 = new ACT(); //Отключение выключателя
    private SPS SetOpn = new SPS(); //Выбор "Отключить выключаель"
    private ACT OpCls = new ACT(); //Включить выключатель
    private SPS SetCls = new SPS(); //Выбор "Включить выключаель"
    private INC OpCrtRs = new INC(); //Сбрасываемый счетчик операций
    // Поля хранящие выходные значения сигнала на отключения фаз выключателя
    private SPC LocSta = new SPC();
    private DPC Pos = new DPC(); //Отправка УВ
    private DPC PosA = new DPC(); //Отправка УВ L1
    private DPC PosB = new DPC(); //Отправка УВ L2
    private DPC PosC = new DPC(); //Отправка УВ L3

    @Override
    public void process() {
        //Проверка наличия сигнала на отключение выключателя от каждой из защит
        if (OpOpn1.getGeneral().getValue() || OpOpn2.getGeneral().getValue() || OpOpn3.getGeneral().getValue() ){
            Pos.getStVal().setValue(DPC.Position.OFF);
            PosA.getStVal().setValue(DPC.Position.OFF);
            PosB.getStVal().setValue(DPC.Position.OFF);
            PosC.getStVal().setValue(DPC.Position.OFF);
        } else{
            Pos.getStVal().setValue(DPC.Position.ON);
            PosA.getStVal().setValue(DPC.Position.ON);
            PosB.getStVal().setValue(DPC.Position.ON);
            PosC.getStVal().setValue(DPC.Position.ON);
        }
        log.info("Сигнал на отключение  " + Pos.getStVal());
    }

}
