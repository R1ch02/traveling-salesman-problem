package com.application.travelingsalesmanproblem;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {


    private final TaskRepository repository;


    // Возвращает упрощенную матрицу
    public Iteration simplify(Iteration iteration) {

        double minCol = Double.MAX_VALUE;
        double[][] matrix = iteration.getMatrix();
        double E = iteration.getE();
        boolean flag = false;

        for (int i = 0; i < matrix.length; i++) {

            double minInRow = Double.POSITIVE_INFINITY;

            for (int j = 0; j < matrix.length; j++) {
                if (!Double.isNaN(matrix[i][j]) && matrix[i][j] != Double.POSITIVE_INFINITY) {
                    minInRow = Math.min(minInRow, matrix[i][j]);
                    flag = true;
                }
            }

            for (int j = 0; j < matrix.length; j++) {
                if (!Double.isNaN(matrix[i][j]) && matrix[i][j] != Double.POSITIVE_INFINITY) {
                    matrix[i][j] -= minInRow;

                }
            }

            if(flag) E += minInRow;
            flag = false;

        }


        for (int j = 0; j < matrix.length; j++) {

            double minInCol = Double.POSITIVE_INFINITY;

            for (int i = 0; i < matrix.length; i++) {
                if (!Double.isNaN(matrix[i][j]) && matrix[i][j] != Double.POSITIVE_INFINITY) {
                    minInCol = Math.min(minInCol, matrix[i][j]);
                    flag = true;
                }
            }

            for (int i = 0; i < matrix.length; i++) {
                if (!Double.isNaN(matrix[i][j]) && matrix[i][j] != Double.POSITIVE_INFINITY) {
                    matrix[i][j] -= minInCol;
                }
            }

            if(flag) E += minInCol;
        }
        iteration.setE(E);
        iteration.setMatrix(matrix);

        return iteration;


    }

    // Находим лучший путь (3,1) для первой итерации примера
    public Path findTheBestWay(Iteration iteration) {

        double[][] matrix = iteration.getMatrix();          // изначальная матрица

        List<Path> paths = new ArrayList<>();           // список путей, из которых нужно выбрать

        double minStr = Double.MAX_VALUE;
        double minCol = Double.MAX_VALUE;
        double maxSum = Double.MIN_VALUE;


        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == 0) {
                    for (int k = 0; k < matrix.length; k++) {
                        if (matrix[i][k] < minStr && k != j) minStr = matrix[i][k];
                    }

                    for (int k = 0; k < matrix.length; k++) {
                        if (matrix[k][j] < minCol && k != i) minCol = matrix[k][j];
                    }

                    if (maxSum < minStr + minCol) maxSum = minStr + minCol;


                    paths.add(new Path(minCol + minStr, i + 1, j + 1));


                }
                minStr = Double.MAX_VALUE;
                minCol = Double.MAX_VALUE;
            }

        }

        for (Path path : paths) {
            if (path.getValue() == maxSum) {
                return path;
            }
        }

        return null;
    }

    public void addPath(Node node, Path path) {

        Iteration iteration = node.getIteration();
        double[][] matrix = iteration.getMatrix();
        double i = path.getFrom()-1;
        double j = path.getTo()-1;



        for (int k = 0; k < matrix.length; k++) {
            for (int l = 0; l < matrix.length; l++) {
                if (i == k || j == l) matrix[k][l] = Double.NaN;
                if (i == l && j == k) matrix[k][l] = Double.POSITIVE_INFINITY;
            }
        }

        iteration.setMatrix(matrix);
        iteration.getPathList().add(path);

        node.leftNode.setIteration(iteration);
    }

    public void excludePath(Node node, Path path){

        double i = path.getFrom()-1;
        double j = path.getTo()-1;
        Iteration iteration = node.getIteration();
        double[][] matrix = iteration.getMatrix();

        matrix[(int) i][(int) j] = Double.POSITIVE_INFINITY;
        iteration.setMatrix(matrix);

        node.rightNode.setIteration(iteration);

    }

    public void start(double[][] matrix){
        double E = 0;
        List<Path> pathList = new ArrayList<>();
        Iteration iteration = new Iteration(matrix,E,pathList);
        simplify(iteration);
        addToRepository(iteration);

    }




    public void addToRepository(Iteration iteration) {
        repository.addNode(repository.root, iteration);
    }

}
