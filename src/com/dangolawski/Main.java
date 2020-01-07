package com.dangolawski;

import com.dangolawski.models.Post;
import com.dangolawski.services.ChartService;
import com.dangolawski.services.DataFactory;
import com.dangolawski.services.MatrixOperationsService;
import com.dangolawski.services.OverfittingService;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, IOException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
	    DataFactory dataFactory = new DataFactory();
        MatrixOperationsService matrixOperationsService = new MatrixOperationsService();
        OverfittingService overfittingService = new OverfittingService();

        // get data from csv file
	    ArrayList<Post> posts = dataFactory.readDataAndCreatePlayers();
	    // remove overfitting values from objects
	    overfittingService.refactorData(posts);

        HashMap<String, Float> xtxProduct = matrixOperationsService.calculateXTXcalculation(posts);
//        System.out.println(xtxProduct);
        matrixOperationsService.calculateInvertibleMatrix(xtxProduct);

    }
}
