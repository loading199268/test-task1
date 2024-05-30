package com.example.testtask1.controller.task;

import lombok.Getter;

@Getter
public class NewCreateTask {

    private final StatusTask statusTask;

    private final String text;

    private final String phoneNumber;

    private final String userName;


    public NewCreateTask(StatusTask statusTask, String text, String phoneNumber, String userName) {
        this.statusTask = statusTask;
        this.text = text;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        checkCorrectTask();
    }


    public void checkCorrectTask() {
        if (!(StatusTask.DRAFT.equals(statusTask) || StatusTask.SEND.equals(statusTask))) {
            throw new RuntimeException("не верный статус задачи");
        }
        if (null == text || text.isEmpty()) {
            throw new RuntimeException("не коректный текст задачи");
        }
        if (null == phoneNumber || text.isEmpty()) {
            throw new RuntimeException("не коректный текст задачи");
        }
        if (null == userName || text.isEmpty()) {
            throw new RuntimeException("не коректный текст задачи");
        }
    }
}
