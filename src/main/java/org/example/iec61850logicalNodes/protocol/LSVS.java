package org.example.iec61850logicalNodes.protocol;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.iec61850datatypes.measurements.MV;
import org.example.iec61850logicalNodes.common.LN;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


@Data
@Slf4j
public class LSVS extends LN {

    private File cfgFile;
    private File csvFile;
    private String path;
    private String filename;
    private MV outA;
    private MV outB;
    private MV outC;
    private List<String> csvFileList = new ArrayList<String>();
    private List<Double> aCoef = new ArrayList<>();
    private List<Double> bCoef = new ArrayList<>();
    private int analogNumber;
    private int discreteNumber;

    //Инициализация листа аналоговых сигналов типа MV(Величина, тип величины, время)
    public LSVS() {
        setPath("C:/Users/artem/Desktop/com/");
        setFilename("PhABC20");
        readFile();
        removeTrashInfo();
        this.outA = new MV();
        this.outB = new MV();
        this.outC = new MV();
    }

    @Override
    public void process() {
        //Сбор необходимых данных с cfg
        setMvToOut(this.outA);
        setMvToOut(this.outB);
        setMvToOut(this.outC);
    }


    @SneakyThrows
    public void setFilename(String filename) {
        //Чтение comtrade файлов
        this.csvFile = new File(path + filename + ".csv");
        //Excexption на неправильный путь
        if (!csvFile.exists()) throw new Exception("Путь указан неверно");
    }

    @SneakyThrows
    public void readFile() {
        //Запись всех строк из файлов в листы
        this.csvFileList = Files.readAllLines(csvFile.toPath());
    }

    public void setMvToOut(MV out){
        int phIndex=0;
        if (out == this.outA) phIndex = 1;
        if (out == this.outB) phIndex = 2;
        if (out == this.outC) phIndex = 3;
        out.getT().setValue(Double.parseDouble(this.csvFileList.get(1).split(",")[0]));
        out.getQ().setValue("KA");
        for (int i =0; i < this.csvFileList.size()-1; i++) {
            out.getInstMag().getF().setValue(Double.valueOf(this.csvFileList.get(i).split(",")[phIndex]));
        }
        log.info(String.valueOf(out));

    }
    public void removeTrashInfo(){
        this.csvFileList.remove(0);
    }
}
