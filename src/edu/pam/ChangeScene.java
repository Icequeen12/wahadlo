package edu.pam;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;


public class ChangeScene extends Application {

    LineChart<Number, Number> figure;
    Scene sceneMain;
    Button btnParam;
    Button btnPlot;
    Button btnAnimation;

    TextField txtC;
    TextField txtA;
    TextField txtF;

    TextField txtTstart;
    TextField txtOmega;
    TextField txtTheta;
    TextField txtH;
    TextField txtTend;


    PendulumParam param;

    @Override
    public void start(Stage primaryStage) throws Exception {

        param = new PendulumParam();

        VBox layoutMain = new VBox(20, displayParam(), displayButtons(primaryStage));
        sceneMain = new Scene(layoutMain, 600, 600);
        primaryStage.setScene(sceneMain);
        primaryStage.show();
        primaryStage.setTitle("Change scene");
    }

    public static void main(String[] args) {
        launch(args);
    }

    private VBox displayParam() {
        Label labelC = new Label("c: ");
        txtC = new TextField();
        txtC.setEditable(false);
        txtC.setPrefWidth(75);

        Label labelF = new Label("f: ");
        txtF = new TextField();
        txtF.setEditable(false);
        txtF.setPrefWidth(75);

        Label labelA = new Label("a: ");
        txtA = new TextField();
        txtA.setEditable(false);
        txtA.setPrefWidth(75);

        Label labelH = new Label("h: ");
        txtH = new TextField();
        txtH.setEditable(false);
        txtH.setPrefWidth(75);

        Label labelTstart = new Label("T start: ");
        txtTstart = new TextField();
        txtTstart.setEditable(false);
        txtTstart.setPrefWidth(75);

        Label labelTend = new Label("T end : ");
        txtTend = new TextField();
        txtTend.setEditable(false);
        txtTend.setPrefWidth(75);

        Label labelOmega = new Label("Omega: ");
        txtOmega = new TextField();
        txtOmega.setEditable(false);
        txtOmega.setPrefWidth(75);

        Label labelTheta = new Label("Theta: ");
        txtTheta = new TextField();
        txtTheta.setEditable(false);
        txtTheta.setPrefWidth(75);

        updateParam();


        HBox rzadJeden = new HBox(10, labelOmega, txtOmega, labelTheta, txtTheta, labelTstart, txtTstart, labelTend, txtTend);
        rzadJeden.setAlignment(Pos.CENTER);
        HBox rzadDwa = new HBox(10, labelC, txtC, labelF, txtF, labelA, txtA, labelH, txtH);
        rzadDwa.setAlignment(Pos.CENTER);

        VBox out = new VBox(20, rzadJeden, rzadDwa);
        out.setAlignment(Pos.CENTER);

        return out;
    }

    private HBox displayButtons(Stage stage) {
        btnParam = new Button("Set Param");
        btnParam.setPrefWidth(150);
        btnParam.setOnAction(e -> {
            param.display();
            updateParam();
        });

        btnPlot = new Button("Plot");
        btnPlot.setPrefWidth(150);
        btnPlot.setOnAction(e -> {
            plot(stage);
        });

        btnAnimation = new Button("Animation");
        btnAnimation.setPrefWidth(150);
        btnAnimation.setOnAction(e -> {
            animacja(stage);
        });

        HBox hBox = new HBox(40, btnParam, btnPlot, btnAnimation);
        hBox.setAlignment(Pos.CENTER);

        return hBox;
    }

    private void updateParam() {
        txtC.setText(String.format("%.2f", param.getC()));
        txtF.setText(String.format("%.2f", param.getF()));
        txtA.setText(String.format("%.2f", param.getA()));
        txtH.setText(String.format("%.2f", param.getH()));
        txtOmega.setText(String.format("%.2f", param.getOmega()));
        txtTheta.setText(String.format("%.2f", param.getTheta()));
        txtTstart.setText(String.format("%.2f", param.getTstart()));
        txtTend.setText(String.format("%.2f", param.getTend()));
    }

    private void plot(Stage stage) {

        NumberAxis x = new NumberAxis();
        x.setLabel("Czas");
        NumberAxis y = new NumberAxis();
        y.setLabel("Wartość");
        figure = new LineChart<Number, Number>(x, y);

        Richard ex = new Richard(param.getTstart(), param.getTend(), Math.toRadians(param.getTheta()), param.getOmega(), param.getH(), param.getC(), param.getA(), param.getF());

        ex.policz();

        figure.setCreateSymbols(false);
        figure.getData().clear();
        XYChart.Series data = ex.getOmega();
        data.setName("Omega");
        XYChart.Series data1 = ex.getTheta();
        data1.setName("Theta");
        figure.setLegendVisible(true);
        figure.getData().addAll(data, data1);


        VBox vBox = new VBox(20, displayParam(), displayButtons(stage));

        vBox.getChildren().add(figure);
        Scene scene = new Scene(vBox, 600, 600);
        stage.setScene(scene);
        stage.show();
    }

    private void animacja(Stage stage) {

        double xc = 275;
        double yc = 100;

        Circle circle = new Circle();
        circle.setRadius(25.0);
        circle.setFill(new Color(0.8, 0.2, 0.3, 1));
        circle.setStrokeWidth(10);

        Path path = new Path();
        Richard ex = new Richard(param.getTstart(), param.getTend(), Math.toRadians(param.getTheta()), param.getOmega(), param.getH(), param.getC(), param.getA(), param.getF());
        ex.policz();
        path.getElements().add(new MoveTo((xc + 100 * Math.sin(ex.thetaWyniki.get(0))), (yc + 100 * Math.cos(ex.thetaWyniki.get(0)))));

        for (int i = 0; i < ex.tWyniki.size(); i++) {
            path.getElements().add(new LineTo((xc + 100 * Math.sin(ex.thetaWyniki.get(i))), (yc + 100 * Math.cos(ex.thetaWyniki.get(i)))));
        }

        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(9000));
        pathTransition.setNode(circle);
        pathTransition.setPath(path);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(1);
        pathTransition.setAutoReverse(false);
        pathTransition.play();

        VBox vBox = new VBox(20, displayParam(), displayButtons(stage));
        vBox.getChildren().add(circle);

        Scene scene = new Scene(vBox, 600, 600);

        stage.setScene(scene);
        stage.show();

    }
}
