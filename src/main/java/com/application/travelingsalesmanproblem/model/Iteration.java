package com.application.travelingsalesmanproblem.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;

@Data
public class Iteration {

    private double[][] matrix;
    private double E;
    private ArrayList<double[]> pathList;
}
