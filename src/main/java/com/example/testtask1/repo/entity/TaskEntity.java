package com.example.testtask1.repo.entity;

import com.example.testtask1.controller.task.NewCreateTask;
import com.example.testtask1.controller.task.StatusTask;
import com.example.testtask1.controller.task.TaskDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks")
public class TaskEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusTask status;

    @Column(name = "text")
    private String text;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "user_name")
    private String userName;

    public TaskDTO toModel() {
        return new TaskDTO(
                getId(),
                getStatus(),
                getText(),
                getPhoneNumber(),
                getUserName());
    }

    public static TaskEntity toEntity(NewCreateTask task) {
       TaskEntity result = new TaskEntity();
       result.setStatus(task.getStatusTask());
       result.setText(task.getText());
       result.setPhoneNumber(task.getPhoneNumber());
       result.setUserName(task.getUserName());
       return result;
    }
}
