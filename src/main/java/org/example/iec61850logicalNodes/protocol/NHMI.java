package org.example.iec61850logicalNodes.protocol;

import lombok.Data;
import org.example.iec61850datatypes.common.Timestamp;
import org.example.iec61850datatypes.measurements.ACT;
import org.example.iec61850datatypes.measurements.AnalogueValue;
import org.example.iec61850datatypes.measurements.CMV;
import org.example.iec61850datatypes.measurements.MV;
import org.example.iec61850logicalNodes.common.LN;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class NHMI extends LN {
    private MV instIphA;
    private MV instIphB;
    private MV instIphC;
    private CMV phA;
    private CMV phB;
    private CMV phC;
    private ACT op;


    public NHMI(MV instIphA, MV instIphB, MV instIphC, CMV phA, CMV phB, CMV phC, ACT op) {
        this.instIphA = instIphA;
        this.instIphB = instIphB;
        this.instIphC = instIphC;
        this.phA = phA;
        this.phB = phB;
        this.phC = phC;
        this.op = op;
    }

    @Override
    public void process() {
//        createGraphic(instIphA.getInstMag().getF(), instIphA.getT(), Color.BLACK);
//        createGraphic(instIphB.getInstMag().getF(), instIphB.getT(), Color.BLUE);
//        createGraphic(instIphC.getInstMag().getF(), instIphC.getT(), Color.RED);
//        createGraphic(phA.getCVal().getMag().getF(), phA.getT(), Color.BLACK);
//        createGraphic(phB.getCVal().getMag().getF(), phB.getT(), Color.BLUE);
//        createGraphic(phC.getCVal().getMag().getF(), phC.getT(), Color.RED);
//        createGraphic(boolToDouble(op.getGeneral()), op.getT(), Color.DARK_GRAY);
    }

    //Создание коллекции для потроения графика
//    public XYSeriesCollection addCollection(List<Double> f, Timestamp t) {
//        XYSeries currentSeries = new XYSeries("Ток");
//        double time =0;
//        for (int i =0; i < f.size()-1; i++) {
//            currentSeries.add(time,
//                    f.get(i));
//            time +=Double.parseDouble(t.getValue());
//        }
//        XYSeriesCollection collection = new XYSeriesCollection();
//        collection.addSeries(currentSeries);
//        return collection;
//    }
//
//    public void createGraphic(List<Double> f,  Timestamp t, Color COLOR) {
//
//        JFreeChart chart = ChartFactory.createXYLineChart(
//                "Осциллограмма тока от времени", // Заголовок графика
//                "Время",
//                "Ток",
//                addCollection(f, t),
//                PlotOrientation.VERTICAL,
//                true,
//                true,
//                false
//        );
//        XYPlot plot = chart.getXYPlot();
//        plot.getRenderer().setSeriesPaint(0, COLOR);
//
//        ChartPanel chartPanel = new ChartPanel(chart);
//        chartPanel.setPreferredSize(new Dimension(800, 600));
//        JFrame frame = new JFrame();
//        frame.setContentPane(chartPanel);
//        frame.pack();
//        frame.setVisible(true);
//    }
//    public List<Double> boolToDouble(List<Boolean> opBool) {
//        List<Double> opDouble = new ArrayList<>();
//        for (int i =0; i < opBool.size()-1; i++) {
//            if(!opBool.get(i)) opDouble.add(0.0);
//            if(opBool.get(i)) opDouble.add(1.0);
//        }
//        return opDouble;
//    }
}

