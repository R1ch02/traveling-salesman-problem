package com.application.travelingsalesmanproblem;

import org.springframework.stereotype.Repository;

@Repository
public class TaskRepository {

    Node root;
    static double Eps = Double.MAX_VALUE;


    public TaskRepository(){
       this.root = null;
   }

   public Node addNode(Node root, Iteration iteration){

       if(root == null){
           root = new Node(iteration);
           return root;
       }
       if(root.leftNode == null){
           root.leftNode = addNode(root.leftNode,iteration);

       } else if(root.rightNode == null){
           root.rightNode = addNode(root.rightNode,iteration);
       }

       root.leftNode = addNode(root.leftNode,iteration);
       root.rightNode = addNode(root.rightNode,iteration);

       return root;
   }

    public void printTree(Node root) {
        if (root != null) {
            printTree(root.leftNode);
            System.out.print(root.iteration.E + " ");
            printTree(root.rightNode);
        }
    }

//    public Node checkForTheLeastE(Node root) {
//        if(root != null){
//
//        }
//    }

    
}
