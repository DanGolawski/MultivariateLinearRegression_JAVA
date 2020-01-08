package com.dangolawski.services;

import com.dangolawski.models.MatrixItem;
import com.dangolawski.models.Post;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import static java.util.stream.IntStream.range;


public class MatrixOperationsService {

    public MatrixItem[][] calculateXTXcalculation(ArrayList<Post> posts) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int size = Globals.allowedColumns.size();
        MatrixItem[][] calculations = new MatrixItem[size][size];
        int rowNum = 0;
        int columnNum = 0;
        for (String column1 : Globals.allowedColumns) {
            for (String column2 : Globals.allowedColumns) {
                float sumOfSquares = 0;
                for (Post post : posts) sumOfSquares += (int) post.getClass().getMethod("get"+column1).invoke(post)
                            * (int) post.getClass().getMethod("get"+column2).invoke(post);
                calculations[rowNum][columnNum++%size] = new MatrixItem(column1, column2, sumOfSquares);
            }
            rowNum += 1;
        }
        return calculations;
    }



    public void calculateInvertibleMatrix(MatrixItem[][] matrix) {
        HashMap<String, MatrixItem[][]> matrices = LUDecompositionService.decomposite(matrix);
        MatrixItem[][] invertibleLmatrix = getInvertibleTriangularMatrix(matrices.get("L"));

    }

    private MatrixItem[][] getInvertibleTriangularMatrix(MatrixItem[][] matrix) {
        double[][] identituMatrix = getIdentityMatrix(matrix.length);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {

            }
        }
    }

//    public void calculateInvertibleMatrix_TEST() {
//        MatrixItem[][] matrix = new MatrixItem[3][3];
//        matrix[0][0] = new MatrixItem("a", "b", 3);
//        matrix[0][1] = new MatrixItem("a", "b", 4);
//        matrix[0][2] = new MatrixItem("a", "b", 4);
//        matrix[1][0] = new MatrixItem("a", "b", 1);
//        matrix[1][1] = new MatrixItem("a", "b", 5);
//        matrix[1][2] = new MatrixItem("a", "b", 2);
//        matrix[2][0] = new MatrixItem("a", "b", 2);
//        matrix[2][1] = new MatrixItem("a", "b", 3);
//        matrix[2][2] = new MatrixItem("a", "b", 5);
//        displayMatrix(matrix);
//        HashMap<String, MatrixItem[][]> matrices = LUDecompositionService.decomposite(matrix);
//        displayMatrix(matrices.get("L"));
//        displayMatrix(matrices.get("U"));
//        displayMatrix(matrixMultiplication(matrices.get("L"), matrices.get("U")));
//    }

    private MatrixItem[][] matrixMultiplication(MatrixItem[][] matrixL, MatrixItem[][] matrixU) {
        MatrixItem[][] newMatrix = new MatrixItem[matrixL.length][matrixL.length];
        for (int a = 0; a < matrixL.length; a++) {
            for (int b = 0; b < matrixL.length; b++) {
                double sum = 0;
                for (int c = 0; c < matrixL.length; c++) {
                    sum += matrixL[a][c].getValue() * matrixU[c][b].getValue();
                }
                newMatrix[a][b] = new MatrixItem("x", "x", sum);

            }
        }
        return newMatrix;
    }


    private void displayMatrix(MatrixItem[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j].getValue() + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }


    private double[][] getIdentityMatrix(int size) {
        return range(0, size).mapToObj(j -> range(0, size)
                .mapToDouble(i -> i == j ? 1 : 0).toArray())
                .toArray(double[][]::new);
    }

//    private ArrayList<ArrayList<Integer>> getIndentityMatrix(int size) {
//        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
//        for (int i = 0; i < size; i++) {
//            ArrayList<Integer> row = new ArrayList<>();
//            for (int j = 0; j < size; j++) {
//                row.add(j == i ? 1 : 0);
//            }
//        }
//        return matrix;
//    }




}
