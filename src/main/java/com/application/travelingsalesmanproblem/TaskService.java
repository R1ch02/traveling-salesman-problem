package com.application.travelingsalesmanproblem;

import com.application.travelingsalesmanproblem.model.Iteration;
import com.application.travelingsalesmanproblem.model.Path;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class TaskService {



    public Iteration simplify(Iteration iteration){
        double minStr = Double.MAX_VALUE;
        double minCol = Double.MAX_VALUE;
        double[][] matrix = iteration.getMatrix();
        double E = iteration.getE();

        for (int i = 0; i < matrix.length; i++)  {

            for (int j = 0; j < matrix.length; j++) {
                if (minStr > matrix[i][j]) minStr = matrix[i][j];
            }
            E+=minStr;
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j]-=minStr;
            }

            minStr = Integer.MAX_VALUE;

        }


        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix.length; j++) {
                if(minCol > matrix[j][i]) minCol = matrix[j][i];
            }
            E+=minCol;

            for (int j = 0; j < matrix.length; j++) {
                matrix[j][i]-=minCol;
            }
        minCol = Integer.MAX_VALUE;
        }
        iteration.setE(E);
        iteration.setMatrix(matrix);
        return iteration;
    }

//    public Iteration findZeros(double[][] matrix){
//        double minStr = Double.MAX_VALUE;
//        double minCol = Double.MAX_VALUE;
//        ArrayList<Path> paths = new ArrayList<>();
//
//
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix.length; j++) {
//            if(matrix[i][j] == 0){
//                for (int k = 0; k < matrix.length; k++) {
//                    if(matrix[i][k] < minStr && k != j) minStr = matrix[i][k];
//                }
//
//                for (int k = 0; k < matrix.length; k++) {
//                    if(matrix[k][j] < minCol && k != i) minCol = matrix[k][j];
//                }
//                Path path = new Path(minStr+minCol, i, j);
//                paths.add(path);
//
//            }
//                minStr = Double.MAX_VALUE;
//                minCol = Double.MAX_VALUE;
//            }
//        }
//        return ;
//
//    }

    public Iteration itr(Iteration iteration){

    double minStr = Double.MAX_VALUE;
    double minCol = Double.MAX_VALUE;
    double maxSum = Double.MIN_VALUE;
    double E = iteration.getE();
    ArrayList<Path> paths = new ArrayList<>();

    double[][] matrix = iteration.getMatrix();


    for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
            if(matrix[i][j] == 0){
                for (int k = 0; k < matrix.length; k++) {
                    if(matrix[i][k] < minStr && k != j) minStr = matrix[i][k];
                }

                for (int k = 0; k < matrix.length; k++) {
                    if(matrix[k][j] < minCol && k != i) minCol = matrix[k][j];
                }

                if(maxSum < minStr + minCol) maxSum = minStr + minCol;




            }
                minStr = Double.MAX_VALUE;
                minCol = Double.MAX_VALUE;
            }

        }



        return iteration;
    }

    public double[][] addPath(double[][] matrix, int i, int j){
        for (int k = 0; k < matrix.length; k++) {
            for (int l = 0; l < matrix.length; l++) {
                if(i == k || j == l) matrix[k][l] = Double.NaN;
            }
        }
    return matrix;
    }

    public double[][] excludePath(double[][] matrix, int i, int j){
    matrix[i][j] = Double.POSITIVE_INFINITY;
    return matrix;
    }

}
