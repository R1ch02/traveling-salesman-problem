package com.application.travelingsalesmanproblem;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Iteration {

     double[][] matrix;
     double E;
     List<Path> pathList;

    public Iteration(double[][] matrix, double e, List<Path> pathList) {
        this.matrix = matrix;
        E = e;
        this.pathList = pathList;
    }
}
