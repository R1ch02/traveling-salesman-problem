package com.application.travelingsalesmanproblem;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TaskController {

    private TaskService taskService;


}
