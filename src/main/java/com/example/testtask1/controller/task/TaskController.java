package com.example.testtask1.controller.task;

import com.example.testtask1.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/task")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/create")
    public TaskDTO createTask(@RequestBody NewCreateTask task) {
        return taskService.createTask(task);
    }

    @GetMapping("{id}")
    public TaskDTO viewTask(@PathVariable Long id) {
        return taskService.viewTask(id);
    }

    @GetMapping("/tasks")
    public Page<TaskDTO> viewAllTask(
            @PageableDefault (size = 5, page = 0, sort = "CreateData") Pageable pageable,
            StatusFilter statusFilter) {
        return taskService.viewAllTask(pageable, statusFilter);
    }

    @PostMapping("{id}/send")
    public TaskDTO sendTask(@RequestBody Long id) {
        return taskService.sendTask(id);
    }
    @PostMapping("{id}/accept")
    public TaskDTO acceptTask(@PathVariable Long id) {
        return taskService.acceptTask(id);
    }
    @PostMapping("{id}/reject")
    public TaskDTO rejectTask(@PathVariable Long id) {
        return taskService.rejectTask(id);
    }
}
