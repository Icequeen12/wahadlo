package edu.pam;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PendulumParam {

    private double c;
    private double a;
    private double f;

    private double tstart;
    private double omega;
    private double theta;
    private double h;
    private double tend;

    public PendulumParam() {
        c = 0.5;
        f = 0.66;
        a = 0;
        h = 0.1;
        omega = 0;
        theta = 5;
        tstart = 0.0;
        tend = 100.0;
    }


    public PendulumParam(double c, double a, double f, double tstart, double tend, double h, double omega, double theta) {
        this.c = c;
        this.a = a;
        this.f = f;
        this.h = h;
        this.omega = omega;
        this.theta = theta;
        this.tstart = tstart;
        this.tend = tend;
    }

    public double getTstart() {
        return tstart;
    }

    public void setTstart(double tstart) {
        this.tstart = tstart;
    }

    public double getOmega() {
        return omega;
    }

    public void setOmega(double omega) {
        this.omega = omega;
    }

    public double getTheta() {
        return theta;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public double getTend() {
        return tend;
    }

    public void setTend(double tend) {
        this.tend = tend;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getF() {
        return f;
    }

    public void setF(double f) {
        this.f = f;
    }

    public void display() {
        Stage window = new Stage();
        window.setTitle("Parametres");
        window.initModality(Modality.APPLICATION_MODAL);


        Label lblC = new Label("c: ");
        Label lblF = new Label("f: ");
        Label lblA = new Label("a: ");
        Label lblH = new Label("h : ");
        Label lblOmega = new Label("omega: ");
        Label lblTheta = new Label("theta: ");
        Label lblTstart = new Label(" T start : ");
        Label lblTend = new Label("T end : ");


        Slider sliderC = new Slider(0, 1, c);
        sliderC.setShowTickLabels(true);
        sliderC.setShowTickMarks(true);
        sliderC.setMajorTickUnit(0.1);
        sliderC.setMinorTickCount(3);

        Slider sliderF = new Slider(0, 1, f);
        sliderF.setShowTickLabels(true);
        sliderF.setShowTickMarks(true);
        sliderF.setMajorTickUnit(0.1);
        sliderF.setMinorTickCount(3);

        Slider sliderA = new Slider(0, 2, a);
        sliderA.setShowTickLabels(true);
        sliderA.setShowTickMarks(true);
        sliderA.setMajorTickUnit(0.1);
        sliderA.setMinorTickCount(3);

        Slider sliderH = new Slider(0, 1, h);
        sliderH.setShowTickLabels(true);
        sliderH.setShowTickMarks(true);
        sliderH.setMajorTickUnit(0.1);
        sliderH.setMinorTickCount(3);

        Slider sliderOmega = new Slider(0, 1, omega);
        sliderOmega.setShowTickLabels(true);
        sliderOmega.setShowTickMarks(true);
        sliderOmega.setMajorTickUnit(0.1);
        sliderOmega.setMinorTickCount(3);

        Slider sliderTheta = new Slider(0, 1, theta);
        sliderTheta.setShowTickLabels(true);
        sliderTheta.setShowTickMarks(true);
        sliderTheta.setMajorTickUnit(0.1);
        sliderTheta.setMinorTickCount(3);

        Slider sliderTend = new Slider(0, 100, tend);
        sliderTend.setShowTickLabels(true);
        sliderTend.setShowTickMarks(true);
        sliderTend.setMajorTickUnit(10);
        sliderTend.setMinorTickCount(3);

        Slider sliderTstart = new Slider(0, 100, tstart);
        sliderTstart.setShowTickLabels(true);
        sliderTstart.setShowTickMarks(true);
        sliderTstart.setMajorTickUnit(10);
        sliderTstart.setMinorTickCount(3);


        TextField txtC = new TextField(Double.toString(c));
        TextField txtF = new TextField(Double.toString(f));
        TextField txtA = new TextField(Double.toString(a));
        TextField txtH = new TextField(Double.toString(h));
        TextField txtOmega = new TextField(Double.toString(omega));
        TextField txtTheta = new TextField(Double.toString(theta));
        TextField txtTstart = new TextField(Double.toString(tstart));
        TextField txtTend = new TextField(Double.toString(tend));


        sliderC.valueProperty().addListener((observable, oldValue, newValue) -> txtC.setText(Double.toString(newValue.doubleValue())));
        sliderA.valueProperty().addListener((observable, oldValue, newValue) -> txtA.setText(Double.toString(newValue.doubleValue())));
        sliderF.valueProperty().addListener((observable, oldValue, newValue) -> txtF.setText(Double.toString(newValue.doubleValue())));
        sliderH.valueProperty().addListener((observable, oldValue, newValue) -> txtH.setText(Double.toString(newValue.doubleValue())));
        sliderOmega.valueProperty().addListener((observable, oldValue, newValue) -> txtOmega.setText(Double.toString(newValue.doubleValue())));
        sliderTheta.valueProperty().addListener((observable, oldValue, newValue) -> txtTheta.setText(Double.toString(newValue.doubleValue())));
        sliderTend.valueProperty().addListener((observable, oldValue, newValue) -> txtTend.setText(Double.toString(newValue.doubleValue())));
        sliderTstart.valueProperty().addListener((observable, oldValue, newValue) -> txtTstart.setText(Double.toString(newValue.doubleValue())));

        Button btnSet = new Button("Set");
        btnSet.setPrefWidth(100);
        btnSet.setOnAction(event -> {

            c = Double.parseDouble(txtC.getText());
            f = Double.parseDouble(txtF.getText());
            a = Double.parseDouble(txtA.getText());
            h = Double.parseDouble(txtH.getText());
            tstart = Double.parseDouble(txtTstart.getText());
            tend = Double.parseDouble(txtTend.getText());
            omega = Double.parseDouble(txtOmega.getText());
            theta = Double.parseDouble(txtTheta.getText());

            window.close();

        });

        HBox hBoxC = new HBox(10, lblC, sliderC, txtC);
        hBoxC.setAlignment(Pos.CENTER);

        HBox hBoxF = new HBox(10, lblF, sliderF, txtF);
        hBoxF.setAlignment(Pos.CENTER);

        HBox hBoxA = new HBox(10, lblA, sliderA, txtA);
        hBoxA.setAlignment(Pos.CENTER);

        HBox hBoxH = new HBox(10, lblH, sliderH, txtH);
        hBoxH.setAlignment(Pos.CENTER);

        HBox hBoxTend = new HBox(10, lblTend, sliderTend, txtTend);
        hBoxTend.setAlignment(Pos.CENTER);

        HBox hBoxTstart = new HBox(10, lblTstart, sliderTstart, txtTstart);
        hBoxTstart.setAlignment(Pos.CENTER);

        HBox hBoxOmega = new HBox(10, lblOmega, sliderOmega, txtOmega);
        hBoxOmega.setAlignment(Pos.CENTER);

        HBox hBoxTheta = new HBox(10, lblTheta, sliderTheta, txtTheta);
        hBoxTheta.setAlignment(Pos.CENTER);

        VBox suwaki = new VBox(30, hBoxA, hBoxC, hBoxF, hBoxH, hBoxOmega, hBoxTheta, hBoxTstart, hBoxTend);
        suwaki.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(20, suwaki, btnSet);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox, 450, 700);
        window.setScene(scene);

        window.showAndWait();
    }
}