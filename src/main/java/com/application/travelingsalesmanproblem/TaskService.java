package com.application.travelingsalesmanproblem;

import com.application.travelingsalesmanproblem.model.Path;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class TaskService {


    public static double E = 0;

    public double[][] simplify(double[][] matrix){
        double minStr = Double.MAX_VALUE;
        double minCol = Double.MAX_VALUE;

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

    return matrix;
    }

    public ArrayList<Path> findZeros(double[][] matrix){
        double minStr = Double.MAX_VALUE;
        double minCol = Double.MAX_VALUE;
        ArrayList<Path> paths = new ArrayList<>();


        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
            if(matrix[i][j] == 0){
                for (int k = 0; k < matrix.length; k++) {
                    if(matrix[i][k] < minStr && k != j) minStr = matrix[i][k];
                }

                for (int k = 0; k < matrix.length; k++) {
                    if(matrix[k][j] < minCol && k != i) minCol = matrix[k][j];
                }
                Path path = new Path(minStr+minCol, i, j);
                paths.add(path);

            }
                minStr = Double.MAX_VALUE;
                minCol = Double.MAX_VALUE;
            }
        }
        return paths;

    }

}
