package com.application.travelingsalesmanproblem;

import com.application.travelingsalesmanproblem.model.Path;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    private final TaskService service = new TaskService();


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
            {Double.NaN,Double.NaN,Double.NaN,Double.NaN},
            {Double.NaN,Double.POSITIVE_INFINITY,26,58},
            {Double.NaN,50,Double.POSITIVE_INFINITY,39},
            {Double.NaN,39,24,Double.POSITIVE_INFINITY}
    };

    private final double[][] expectedExcludeMatrix = {
            {Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,25,11},
            {37,Double.POSITIVE_INFINITY,26,58},
            {10,50,Double.POSITIVE_INFINITY,39},
            {38,39,24,Double.POSITIVE_INFINITY}
    };




//    @Test
//    void simplify() {
//        double Eps = 79;
//        double[][] changedMatrix =service.simplify(startMatrix);
//        assertArrayEquals(expectedMatrix,changedMatrix);
//
//    }

//    @Test
//    void findZeros() {
//        ArrayList<Path> expectedPaths = new ArrayList<>();
//        Path path1 = new Path(7,0,1);
//        Path path2 = new Path(29,0,3);
//        Path path3 = new Path(11,1,2);
//        Path path4 = new Path(40,2,0);
//        Path path5 = new Path(7,3,2);
//
//        expectedPaths.add(path1);
//        expectedPaths.add(path2);
//        expectedPaths.add(path3);
//        expectedPaths.add(path4);
//        expectedPaths.add(path5);
//
//        ArrayList<Path> testPaths = service.findZeros(expectedMatrix);
//
//        assertEquals(expectedPaths,testPaths);
//        assertEquals(expectedPaths.size(),testPaths.size());
//        assertTrue(expectedPaths.containsAll(testPaths));
//        assertTrue(testPaths.containsAll(expectedPaths));
//
//    }

    @Test
    void addPath() {
        assertArrayEquals(expectedAddMatrix,service.addPath(startMatrix,0,0));
    }

    @Test
    void excludePath() {
        assertArrayEquals(expectedExcludeMatrix,service.excludePath(startMatrix,0,1));

    }
}