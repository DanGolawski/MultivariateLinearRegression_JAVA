package com.dangolawski.services;

import com.dangolawski.models.Post;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class MatrixOperationsService {

    public HashMap<String, Float> calculateXTXcalculation(ArrayList<Post> posts) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        HashMap<String, Float> calculations = new HashMap<>();
        for (String column1 : Globals.allowedColumns) {
            for (String column2 : Globals.allowedColumns) {
                float sumOfSquares = 0;
                for (Post post : posts) sumOfSquares += (int) post.getClass().getMethod("get"+column1).invoke(post)
                            * (int) post.getClass().getMethod("get"+column2).invoke(post);
                calculations.put(column1+"/"+column2, sumOfSquares);
            }
        }
        return calculations;
    }

    public void calculateInvertibleMatrix(HashMap<String, Float> values) {

    }

    private void getL_Matrix(HashMap<String, Integer> matrix) {
        ArrayList<ArrayList<Integer>> identityMatrix = getIndentityMatrix((int) Math.sqrt(matrix.size()));
    }

    private ArrayList<ArrayList<Integer>> getIndentityMatrix(int size) {
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                row.add(j == i ? 1 : 0);
            }
        }
        return matrix;
    }
}
