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
    private String path;
    private int connectionNumber;
    private List<String> cfgDataFile = new ArrayList<>();
    private List<String> datDataFile = new ArrayList<>();
    private MV outACurrent = new MV();
    private MV outBCurrent = new MV();
    private MV outCCurrent = new MV();
    private List<String> datFileList = new ArrayList<>();
    private List<Double> aCoef = new ArrayList<>();
    private List<Double> bCoef = new ArrayList<>();
    private Iterator<String> iteratorDat;
    private Iterator<String> iteratorOut;
    private int discFreq;

    //Инициализация листа аналоговых сигналов типа MV(Величина, тип величины, время)
    public LSVS(int connectionNumber) {
        if(connectionNumber == 1) this.connectionNumber = 2;
        if(connectionNumber == 2) this.connectionNumber = 5;
        if(connectionNumber == 3) this.connectionNumber = 8;
        if(connectionNumber == 4) this.connectionNumber = 11;
        if(connectionNumber == 5) this.connectionNumber = 14;
        setPath("C:/Users/artem/Desktop/2 сем/AlgRZA/lab1/DPB/5 sections/");
//        setPath("C:/Users/artem/Desktop/2 сем/AlgRZA/lab1/Опыты/");
        setFilePath(path,"Vkl");
    }

    @Override
    public void process() {
        //Сбор необходимых данных с cfg
        setMvToOut();
    }


    public void setMvToOut(){
        if(this.iteratorDat.hasNext()) {
            String e =iteratorDat.next();
            this.outACurrent.getInstMag().getF().setValue((Double.valueOf(e.split(",")[this.connectionNumber])*aCoef.get(this.connectionNumber-2)+bCoef.get(this.connectionNumber-2))*1000);
            this.outBCurrent.getInstMag().getF().setValue((Double.valueOf(e.split(",")[this.connectionNumber+1])*aCoef.get(this.connectionNumber-1)+bCoef.get(this.connectionNumber-1))*1000);
            this.outCCurrent.getInstMag().getF().setValue((Double.valueOf(e.split(",")[this.connectionNumber+2])*aCoef.get(this.connectionNumber-0)+bCoef.get(this.connectionNumber-0))*1000);
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

    //сбор коэффициентов для рассчета токов из .dat файла
    private void extractCfgFileData() {
        int analogNumber = Integer.parseInt(cfgDataFile.get(1).split(",")[1].replace("A", ""));
        int discreteNumber = Integer.parseInt(cfgDataFile.get(1).split(",")[2].replace("D", ""));
        this.discFreq = Integer.parseInt(cfgDataFile.get(20).split(",")[1]);
//        this.discFreq = Integer.parseInt(cfgDataFile.get(10).split(",")[1]);
        for (int i = 2; i < 2 + analogNumber; i++) {
            aCoef.add(Double.parseDouble(cfgDataFile.get(i).split(",")[5]));
            bCoef.add(Double.parseDouble(cfgDataFile.get(i).split(",")[6]));
        }
    }
    }
