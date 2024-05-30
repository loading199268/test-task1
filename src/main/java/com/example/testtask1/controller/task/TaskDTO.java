package com.example.testtask1.controller.task;

import com.example.testtask1.controller.task.StatusTask;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskDTO {

    private Long id;
    private StatusTask status;
    private String text;
    private String phoneNumber;
    private String userName;
}
