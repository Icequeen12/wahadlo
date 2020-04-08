package edu.pam;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.List;

public class Richard {

    private double f;
    private double A;
    private double c;
    private double omega0;
    private double theta0;
    private double tstart;
    private double tend;
    private double h;

    List<Double> thetaWyniki = new ArrayList<>();
    List<Double> omegaWyniki = new ArrayList<>();
    List<Double> tWyniki = new ArrayList<>();

    public Richard(double tstart, double tend, double theta0, double omega0, double h, double c, double A, double f) {

        this.tstart = tstart;
        this.tend = tend;
        this.theta0 = theta0;
        this.omega0 = omega0;
        this.h = h;
        this.c = c;
        this.A = A;
        this.f = f;
    }

    public void policz() {

        double t = tstart;
        double theta = theta0;
        double omega = omega0;
        double tOut = t;
        double thetaOut = theta0;
        double omegaOut = omega0;
        double n = Math.floor((tend - tstart) / h);
        thetaWyniki.add(theta0);
        omegaWyniki.add(omega0);
        tWyniki.add(tstart);
        for (int i = 1; i < n; i++) {
            double tMid = t + h / 2;
            double thetaMid = theta + h * omega / 2;
            double omegaMid = omega + (h / 2) * (-
                    (Math.sin(thetaWyniki.get(i - 1))) - (c * omega) + A * Math.sin(f * t));
            theta = theta + h * omegaMid;
            omega = omega + (-Math.sin(thetaMid) - (c * omegaMid) + A *
                    Math.sin(f * tMid)) * h;
            t = t + h;
            thetaWyniki.add(theta);
            omegaWyniki.add(omega);
            tWyniki.add(t);
        }
    }

    public XYChart.Series getOmega() {
        XYChart.Series series1 = new XYChart.Series();
        for (int i = 0; i < tWyniki.size(); i++) {
            series1.getData().add(new XYChart.Data(tWyniki.get(i),
                    omegaWyniki.get(i)));
        }
        return series1;
    }

    public XYChart.Series getTheta() {
        XYChart.Series series2 = new XYChart.Series();
        for (int i = 0; i < tWyniki.size(); i++) {
            series2.getData().add(new XYChart.Data(tWyniki.get(i),
                    thetaWyniki.get(i)));
        }
        return series2;
    }
}