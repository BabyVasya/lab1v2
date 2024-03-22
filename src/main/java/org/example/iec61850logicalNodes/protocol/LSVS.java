package org.example.iec61850logicalNodes.protocol;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.iec61850datatypes.measurements.MV;
import org.example.iec61850logicalNodes.common.LN;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Data
@Slf4j
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "LSVS")
public class LSVS extends LN {

    private File csvFile;
    private String path;
    private String filename;
    private MV outA;
    private MV outB;
    private MV outC;
    private List<String> csvFileList = new ArrayList<String>();
    private List<Double> aCoef = new ArrayList<>();
    private List<Double> bCoef = new ArrayList<>();
    private Iterator<String> iterator;

    //Инициализация листа аналоговых сигналов типа MV(Величина, тип величины, время)
    public LSVS() {
        setPath("C:/Users/artem/Desktop/com/");
        setFilename("PhC20");
        readFile();
        removeTrashInfo();
        this.iterator = this.csvFileList.iterator();
        this.outA = new MV();
        this.outB = new MV();
        this.outC = new MV();
        this.outA.getQ().setValue("A");
        this.outA.getT().setValue((int) (Double.parseDouble(this.csvFileList.get(1).split(",")[0])*1000));
        this.outB.getQ().setValue("A");
        this.outB.getT().setValue((int) (Double.parseDouble(this.csvFileList.get(1).split(",")[0])*1000));
        this.outC.getQ().setValue("A");
        this.outC.getT().setValue((int) (Double.parseDouble(this.csvFileList.get(1).split(",")[0])*1000));
    }
    public LSVS(){

    }

    @Override
    public void process() {
        //Сбор необходимых данных с cfg
        setMvToOut();
    }


    @SneakyThrows
    public void setFilename(String filename) {
        //Чтение csv файла
        this.csvFile = new File(path + filename + ".csv");
        //Excexption на неправильный путь
        if (!csvFile.exists()) throw new Exception("Путь указан неверно");
    }

    @SneakyThrows
    public void readFile() {
        //Запись всех строк из файлов в листы
        this.csvFileList = Files.readAllLines(csvFile.toPath());
    }

    public void setMvToOut(){
        if(this.iterator.hasNext()) {
            String e =iterator.next();
            this.outA.getInstMag().getF().setValue(Double.valueOf(e.split(",")[1])*1000);
            this.outB.getInstMag().getF().setValue(Double.valueOf(e.split(",")[2])*1000);
            this.outC.getInstMag().getF().setValue(Double.valueOf(e.split(",")[3])*1000);
//            log.info(String.valueOf(outC));
        }
    }
    public void removeTrashInfo(){
        this.csvFileList.remove(0);
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }
    }
