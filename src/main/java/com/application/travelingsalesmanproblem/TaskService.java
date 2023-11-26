package com.application.travelingsalesmanproblem;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {



    private final TaskRepository repository;


    // Возвращает упрощенную матрицу
    public void simplify(Iteration iteration){

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


    }

    public Iteration itr(Iteration iteration){

    double minStr = Double.MAX_VALUE;
    double minCol = Double.MAX_VALUE;
    double maxSum = Double.MIN_VALUE;

    List<Path> pathIterationList = iteration.getPathList();
    List<Path> paths = new ArrayList<>();


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


            paths.add(new Path(minCol+minStr,i,j));


            }
                minStr = Double.MAX_VALUE;
                minCol = Double.MAX_VALUE;
            }


            }



        for(Path path : paths) {
            if (path.getValue() == maxSum){
                break;
            }

            Iteration addIteration = iteration;
            Iteration excludeIteration = iteration;


            path.addOrEx = true;
            addIteration.matrix = addPath(iteration.matrix,path.from,path.to);
            simplify(addIteration);
            pathIterationList.add(path);
            addIteration.setPathList(pathIterationList);
            addToRepository(addIteration);

            pathIterationList.remove(path);

            path.addOrEx = false;
            excludeIteration.matrix = excludePath(iteration.matrix,path.from,path.to);
            simplify(excludeIteration);
            pathIterationList.add(path);
            excludeIteration.setPathList(pathIterationList);
            addToRepository(excludeIteration);


        }

        iteration.setPathList(pathIterationList);

        return iteration;
    }

    public double[][] addPath(double[][] matrix, double i, double j){
        for (int k = 0; k < matrix.length; k++) {
            for (int l = 0; l < matrix.length; l++) {
                if(i == k || j == l) matrix[k][l] = Double.NaN;
            }
        }
    return matrix;
    }

    public double[][] excludePath(double[][] matrix, double i, double j){
    matrix[(int) i][(int) j] = Double.POSITIVE_INFINITY;
    return matrix;
    }

    public void addToRepository(Iteration iteration){
        repository.addNode(repository.root,iteration);
    }

}
