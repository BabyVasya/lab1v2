package org.example.iec61850logicalNodes.protocol;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.example.iec61850datatypes.measurements.MV;
import org.example.iec61850logicalNodes.common.LN;

import javax.xml.bind.annotation.*;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Data
@Slf4j
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "LSVS")
public class LSVS extends LN {

    private File cfgFile;
    private File datFile;
    @XmlElement
    private String path;
    private List<String> cfgDataFile = new ArrayList<>();
    private List<String> datDataFile = new ArrayList<>();
    private MV outACurrent = new MV();
    private MV outBCurrent = new MV();
    private MV outCCurrent = new MV();
    private MV outAVoltage = new MV();
    private MV outBVoltage = new MV();
    private MV outCVoltage = new MV();
    private List<String> datFileList = new ArrayList<>();
    private List<Double> aCoef = new ArrayList<>();
    private List<Double> bCoef = new ArrayList<>();
    @XmlTransient
    private Iterator<String> iteratorDat;
    private Iterator<String> iteratorOut;

    //Инициализация листа аналоговых сигналов типа MV(Величина, тип величины, время)
    public LSVS() {
        setPath("C:/Users/artem/Desktop/2 сем/AlgRZA/lab1/Опыты/");
        setFilePath(path,"KZ2");
    }

    @Override
    public void process() {
        //Сбор необходимых данных с cfg
        setMvToOut();
    }


    public void setMvToOut(){
        if(this.iteratorDat.hasNext()) {
            String e =iteratorDat.next();
            this.outAVoltage.getInstMag().getF().setValue((Double.valueOf(e.split(",")[2])*aCoef.get(0)+bCoef.get(0))*1000);
            this.outBVoltage.getInstMag().getF().setValue((Double.valueOf(e.split(",")[3])*aCoef.get(1)+bCoef.get(1))*1000);
            this.outCVoltage.getInstMag().getF().setValue((Double.valueOf(e.split(",")[4])*aCoef.get(2)+bCoef.get(2))*1000);
            this.outACurrent.getInstMag().getF().setValue((Double.valueOf(e.split(",")[5])*aCoef.get(3)+bCoef.get(3))*1000);
            this.outBCurrent.getInstMag().getF().setValue((Double.valueOf(e.split(",")[6])*aCoef.get(4)+bCoef.get(4))*1000);
            this.outCCurrent.getInstMag().getF().setValue((Double.valueOf(e.split(",")[7])*aCoef.get(5)+bCoef.get(5))*1000);

            this.outACurrent.getQ().setValue("A");
            this.outBCurrent.getQ().setValue("A");
            this.outCCurrent.getQ().setValue("A");
            this.outAVoltage.getQ().setValue("V");
            this.outBVoltage.getQ().setValue("V");
            this.outCVoltage.getQ().setValue("V");
            this.outACurrent.getT().setValue(250);
            this.outBCurrent.getT().setValue(250);
            this.outCCurrent.getT().setValue(250);
            this.outAVoltage.getT().setValue(250);
            this.outBVoltage.getT().setValue(250);
            this.outCVoltage.getT().setValue(250);

        }
    }
    //метод чтения файлов
    @SneakyThrows
    public void setFilePath(String path, String name) {
        this.cfgFile = new File(path + name + ".cfg");
        this.datFile = new File(path + name + ".dat");

        this.cfgDataFile = FileUtils.readLines(cfgFile, StandardCharsets.UTF_8);
        this.datDataFile = FileUtils.readLines(datFile, StandardCharsets.UTF_8);
        this.iteratorDat = datDataFile.listIterator();
        extractCfgFileData();
    }

    public boolean hasNext() {
        return iteratorDat.hasNext();
    }
    //сбор коэффициентов для рассчета токов из .dat файла
    private void extractCfgFileData() {
        int analogNumber = Integer.parseInt(cfgDataFile.get(1).split(",")[1].replace("A", ""));
        int discreteNumber = Integer.parseInt(cfgDataFile.get(1).split(",")[2].replace("D", ""));

        for (int i = 2; i < 2 + analogNumber; i++) {
            aCoef.add(Double.parseDouble(cfgDataFile.get(i).split(",")[5]));
            bCoef.add(Double.parseDouble(cfgDataFile.get(i).split(",")[6]));
        }
    }
    }
