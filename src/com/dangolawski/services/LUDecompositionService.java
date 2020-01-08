package com.dangolawski.services;

import com.dangolawski.models.MatrixItem;

import java.util.HashMap;

import static java.util.stream.IntStream.range;

public class LUDecompositionService {

    public static HashMap<String, MatrixItem[][]> decomposite(MatrixItem[][] A) {
        HashMap<String, MatrixItem[][]> matrixContainer = new HashMap<>();
        int n = A.length;
        MatrixItem[][] L = initializeMatrix(1, A);
        MatrixItem[][] U = initializeMatrix(0, A);

        for (int j = 0; j < n; j++) {
            L[j][j].setValue(1);
            for (int i = 0; i < j + 1; i++) {
                double s1 = 0;
                for (int k = 0; k < i; k++)
                    s1 += U[k][j].getValue() * L[i][k].getValue();
                U[i][j].setValue(A[i][j].getValue() - s1);
            }
            for (int i = j; i < n; i++) {
                double s2 = 0;
                for (int k = 0; k < j; k++)
                    s2 += U[k][j].getValue() * L[i][k].getValue();
                L[i][j].setValue((A[i][j].getValue() - s2) / U[j][j].getValue());
            }
        }
        matrixContainer.put("L", L);
        matrixContainer.put("U", U);
        return matrixContainer;
    }

//    public void getLU_Matrix(double[][] a) {
//        int n = a.length;
//        double[][] L = new double[n][n];
//        double[][] U = new double[n][n];
//        double[][] P = pivotize(a);
////        double[][] A2 = matrixMul(P, a);
//
//        for (int j = 0; j < n; j++) {
//            L[j][j] = 1;
//            for (int i = 0; i < j + 1; i++) {
//                double s1 = 0;
//                for (int k = 0; k < i; k++)
//                    s1 += U[k][j] * L[i][k];
//                U[i][j] = A2[i][j] - s1;
//            }
//            for (int i = j; i < n; i++) {
//                double s2 = 0;
//                for (int k = 0; k < j; k++)
//                    s2 += U[k][j] * L[i][k];
//                L[i][j] = (A2[i][j] - s2) / U[j][j];
//            }
//        }
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(L[i][j] + "\t");
//            }
//            System.out.println();
//        }
//        System.out.println();
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(U[i][j] + "\t");
//            }
//            System.out.println();
//        }
//    }

//    static double[][] matrixMul(double[][] A, double[][] B) {
//        double[][] result = new double[A.length][B[0].length];
//        double[] aux = new double[B.length];
//
//        for (int j = 0; j < B[0].length; j++) {
//
//            for (int k = 0; k < B.length; k++)
//                aux[k] = B[k][j];
//
//            for (int i = 0; i < A.length; i++)
//                result[i][j] = dotProduct(A[i], aux);
//        }
//        return result;
//    }

//    static double dotProduct(double[] a, double[] b) {
//        return range(0, a.length).mapToDouble(i -> a[i] * b[i]).sum();
//    }

//    static double[][] pivotize(double[][] m) {
//        int n = m.length;
//        double[][] id = range(0, n).mapToObj(j -> range(0, n)
//                .mapToDouble(i -> i == j ? 1 : 0).toArray())
//                .toArray(double[][]::new);
//
//        for (int i = 0; i < n; i++) {
//            double maxm = m[i][i];
//            int row = i;
//            for (int j = i; j < n; j++)
//                if (m[j][i] > maxm) {
//                    maxm = m[j][i];
//                    row = j;
//                }
//
//            if (i != row) {
//                double[] tmp = id[i];
//                id[i] = id[row];
//                id[row] = tmp;
//            }
//        }
//        return id;
//    }

    private static MatrixItem[][] pivotize(MatrixItem[][] m) {
        int n = m.length;
        MatrixItem[][] id = range(0, n)
                .mapToObj(j -> range(0, n)
                        .mapToObj(i -> new MatrixItem(m[i][j].getKey1(), m[i][j].getKey2(), i == j ? 1 : 0))
                        .toArray()).toArray(MatrixItem[][]::new);
        for (int i = 0; i < n; i++) {
            double maxm = m[i][i].getValue();
            int row = i;
            for (int j = i; j < n; j++)
                if (m[j][i].getValue() > maxm) {
                    maxm = m[j][i].getValue();
                    row = j;
                }

            if (i != row) {
                MatrixItem[] tmp = id[i];
                id[i] = id[row];
                id[row] = tmp;
            }
        }
        return id;
    }

    private static MatrixItem[][] initializeMatrix(int diagonalItem, MatrixItem[][] matrix) {
        MatrixItem[][] newMatrix = new MatrixItem[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                newMatrix[i][j] = new MatrixItem(
                        matrix[i][j].getKey1(),
                        matrix[i][j].getKey2(),
                        i == j ? diagonalItem : 0
                );
            }
        }
        return newMatrix;
    }
}
