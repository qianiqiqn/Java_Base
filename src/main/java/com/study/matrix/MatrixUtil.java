package com.study.matrix;

import java.math.BigDecimal;

public class MatrixUtil {

    public static void main(String[] args) {
        double[][] previousMatrix = {{1,1,1},{1,1,1},{1,1,1}};

        double[][] laterMatrix = {{2},{2},{3}};

        double[][] newMatrix =  matrixMultiply(previousMatrix,laterMatrix);

        double[][] newMatrix2 =  matrixMultiply(2.0,laterMatrix);

        double[][] newMatrix3 =  matrixAdd(previousMatrix,laterMatrix);
        System.out.println("11");


    }

    public static  double[][] matrixMultiply(double[][] previousMatrix , double [][] laterMatrix){
        if(previousMatrix[0].length!= laterMatrix.length){
            return null;
        }
        //新的行数
        int lines = previousMatrix.length;
        //列数
        int colums = laterMatrix[0].length;
        //前一个的列数与后一个的行数
        int commonLines = laterMatrix.length;
        double[][] targetMatrix = new double[lines][colums];
        for(int i = 0; i < lines ; i++){
            for(int j = 0; j< colums ;j++){
                BigDecimal sum = BigDecimal.valueOf(0.0);
                for(int k = 0 ;k < commonLines ;k++){
                    sum = sum.add(BigDecimal.valueOf(previousMatrix[i][k]).multiply(BigDecimal.valueOf(laterMatrix[k][j])));
                }
                targetMatrix[i][j] = sum.doubleValue();
            }
        }
        return targetMatrix;
    }


    public static  double[][] matrixMultiply(double number , double [][] matrix){
        if(matrix.length < 1){
            return matrix;
        }
        //行数
        int lines = matrix.length;
        //列数
        int colums = matrix[0].length;
        double[][] targetMatrix = new double[lines][colums];
        for(int i = 0; i < lines ; i++){
            for(int j = 0; j< colums ;j++){
                BigDecimal sum = BigDecimal.valueOf(0.0);
                sum = sum.add(BigDecimal.valueOf(number).multiply(BigDecimal.valueOf(matrix[i][j])));
                targetMatrix[i][j] = sum.doubleValue();
            }
        }
        return targetMatrix;
    }


    public static  double[][] matrixAdd(double[][] previousMatrix , double [][] laterMatrix){
        if(previousMatrix.length != laterMatrix.length || previousMatrix[0].length != laterMatrix[0].length){
            return null;
        }
        //新的行数
        int lines = previousMatrix.length;
        //列数
        int colums = previousMatrix[0].length;
        double[][] targetMatrix = new double[lines][colums];
        for(int i = 0; i < lines ; i++){
            for(int j = 0; j< colums ;j++){
                BigDecimal sum = BigDecimal.valueOf(0.0);
                sum = sum.add(BigDecimal.valueOf(previousMatrix[i][j]));
                sum = sum.add(BigDecimal.valueOf(laterMatrix[i][j]));
                targetMatrix[i][j] = sum.doubleValue();
            }
        }
        return targetMatrix;
    }




}
