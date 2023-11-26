package com.application.travelingsalesmanproblem;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/solution")
    public void solution(@RequestBody double[][] matrix){
        Iteration iteration = new Iteration(matrix,0,new ArrayList<>());
        taskService.simplify(iteration);

    }


}
