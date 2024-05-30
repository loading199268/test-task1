package com.example.testtask1.service;

import com.example.testtask1.controller.task.StatusFilter;
import com.example.testtask1.controller.task.NewCreateTask;
import com.example.testtask1.controller.task.StatusTask;
import com.example.testtask1.controller.task.TaskDTO;
import com.example.testtask1.controller.user.Role;
import com.example.testtask1.repo.TaskRepo;
import com.example.testtask1.repo.entity.TaskEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static com.example.testtask1.repo.entity.TaskEntity.toEntity;
import static com.example.testtask1.service.AuthenticationService.getUserName;
import static com.example.testtask1.service.AuthenticationService.hasRole;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepo taskRepository;

    public TaskDTO createTask(NewCreateTask payload) {
        return taskRepository
                .save(toEntity(payload))
                .toModel();
    }

    public Page<TaskDTO> viewAllTask(Pageable pageRequest, StatusFilter filter) {
        Page<TaskEntity> entities = new PageImpl<>(new ArrayList<>());
        if (hasRole(Role.USER)) {
            entities = taskRepository.findAll(pageRequest,
                    filter,
                    TaskRepo.withUserName(getUserName()));
        } else if (hasRole(Role.OPERATOR)) {
            entities = taskRepository.findAll(pageRequest,
                    filter,
                    (r, q, b) -> b.equal(r.get("status").as(String.class), StatusTask.SEND));
        } else if (hasRole(Role.ADMINISTRATOR_ROLE)) {
            entities = taskRepository.findAll(pageRequest,
                    filter,
                    (r, q, b) -> b.notEqual(r.get("status").as(String.class), StatusTask.DRAFT));
        }
        return entities.map(TaskEntity::toModel);
    }

    public TaskDTO sendTask(Long id) {
        TaskEntity task = taskRepository
                .findByIdAndStatus(id, StatusTask.DRAFT)
                .orElseThrow(NoSuchElementException::new);

        task.setStatus(StatusTask.SEND);
        return taskRepository.save(task).toModel();
    }

    public TaskDTO viewTask(Long id) {
        return taskRepository
                .findById(id)
                .orElseThrow(NoSuchElementException::new).toModel();
    }

    public TaskDTO acceptTask(Long id) {
        TaskEntity task = taskRepository.findById(id).orElseThrow(NoSuchElementException::new);
        task.setStatus(StatusTask.ACCEPTED);
        return taskRepository.save(task).toModel();
    }

    public TaskDTO rejectTask(Long id) {
        TaskEntity task = taskRepository.findById(id).orElseThrow(NoSuchElementException::new);
        task.setStatus(StatusTask.REJECTED);
        return taskRepository.save(task).toModel();
    }
}