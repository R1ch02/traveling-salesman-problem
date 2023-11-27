package com.application.travelingsalesmanproblem;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    TaskRepository taskRepository = new TaskRepository();
    TaskService taskService = new TaskService(taskRepository);




    private final double[][] startMatrix = {
            {Double.POSITIVE_INFINITY,19,25,11},
            {37,Double.POSITIVE_INFINITY,26,58},
            {10,50,Double.POSITIVE_INFINITY,39},
            {38,39,24,Double.POSITIVE_INFINITY}

    };

    private final double[][] expectedMatrix = {
            {Double.POSITIVE_INFINITY,0,14,0},
            {11,Double.POSITIVE_INFINITY,0,32},
            {0,32,Double.POSITIVE_INFINITY,29},
            {14,7,0,Double.POSITIVE_INFINITY}

    };
    private final double[][] expectedAddMatrix = {
            {Double.NaN,0,Double.POSITIVE_INFINITY,0},
            {Double.NaN,Double.POSITIVE_INFINITY,0,32},
            {Double.NaN,Double.NaN,Double.NaN,Double.NaN},
            {Double.NaN,7,0,Double.POSITIVE_INFINITY}
    };

    private final double[][] expectedExcludeMatrix = {
            {Double.POSITIVE_INFINITY,0,14,0},
            {11,Double.POSITIVE_INFINITY,0,32},
            {Double.POSITIVE_INFINITY,32,Double.POSITIVE_INFINITY,29},
            {14,7,0,Double.POSITIVE_INFINITY}
    };


    @Test
    void simplify() {

        List<Path> paths = new ArrayList<>();
        paths.add(new Path(40,3,1));
        Iteration iteration = new Iteration(expectedAddMatrix,79,paths);
        taskService.simplify(iteration);

        assertArrayEquals(expectedAddMatrix, iteration.matrix);
       assertEquals(79,iteration.E);


    }

    @Test
    void findTheBestWay() {

        List<Path> paths = new ArrayList<>();
        Iteration iteration = new Iteration(expectedMatrix,79,paths);
        Path expectedPath = new Path(40,3,1);
        assertEquals(expectedPath,taskService.findTheBestWay(iteration));
    }




    @Test
    void addPath() {

        List<Path> paths = new ArrayList<>();
        Path path = new Path(40,3,1);
        List<Path> expectedPaths = new ArrayList<>();
        expectedPaths.add(path);


        Iteration iteration = new Iteration(expectedMatrix,79,paths);
        taskRepository.root = taskRepository.addNode(taskRepository.root,iteration);

        Iteration expectedAddIteration = new Iteration(expectedAddMatrix,79,expectedPaths);
        expectedAddIteration = taskService.simplify(expectedAddIteration);




        taskRepository.addNode(taskRepository.root,iteration);
        assertEquals(iteration,taskRepository.root.iteration);

        taskService.addPath(taskRepository.root,path);
        assertEquals(expectedAddIteration,taskRepository.root.leftNode.iteration);


    }

    @Test
    void excludePath() {
        List<Path> paths = new ArrayList<>();
        Path path = new Path(40,3,1);
        List<Path> expectedPaths = new ArrayList<>();
        //expectedPaths.add(path);

        Iteration iteration = new Iteration(expectedMatrix,79,paths);
        taskRepository.root = taskRepository.addNode(taskRepository.root,iteration);

        Iteration expectedExcludeIteration = new Iteration(expectedExcludeMatrix,119,expectedPaths);


        taskRepository.addNode(taskRepository.root,iteration);
        taskRepository.addNode(taskRepository.root,iteration);

        assertEquals(iteration,taskRepository.root.iteration);

        taskService.excludePath(taskRepository.root,path);
        taskService.simplify(taskRepository.root.rightNode.iteration);
        assertEquals(expectedExcludeIteration,taskRepository.root.rightNode.iteration);
    }


    @Test
    void start(){

        taskService.start(startMatrix);
        assertEquals(1,taskRepository.root.node_id);

    }



}