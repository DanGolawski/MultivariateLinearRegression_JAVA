package com.dangolawski.services;

import com.dangolawski.models.Post;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;


public class ChartService extends Application {

    final String column = "Tag";

    @Override public void start(Stage stage) throws IllegalAccessException, InvocationTargetException, IOException, InstantiationException, NoSuchMethodException, ClassNotFoundException {

        DataFactory dataFactory = new DataFactory();
        ArrayList<Post> posts = dataFactory.readDataAndCreatePlayers();

        stage.setTitle("Line Chart Sample");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        //creating the chart
        final LineChart<Number,Number> lineChart =
                new LineChart<>(xAxis, yAxis);

        lineChart.setTitle("Stock Monitoring, 2010");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("My portfolio");
        //populating the series with data
        int counter = 1;
        for (Post post : posts) {
            series.getData().add(new XYChart.Data(counter++, post.getReputation()));
        }

        ScrollPane root = new ScrollPane(lineChart);
        root.setMinSize(7000,900);
        lineChart.setMinSize(root.getMinWidth(),root.getMinHeight()-20);
        Scene scene  = new Scene(root,800,600);
        lineChart.getData().add(series);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}