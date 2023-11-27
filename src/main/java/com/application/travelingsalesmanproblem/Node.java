package com.application.travelingsalesmanproblem;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class Node {


     static double id;
     double node_id;
     Iteration iteration;
     Node leftNode;
     Node rightNode;

    public Node(Iteration itr) {
        this.iteration = itr;
        this.leftNode = null;
        this.rightNode = null;
        id++;
        this.node_id = id;
    }
}
