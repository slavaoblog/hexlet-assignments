package exercise.controller;

import java.util.List;

import exercise.dto.TaskCreateDTO;
import exercise.dto.TaskDTO;
import exercise.dto.TaskUpdateDTO;
import exercise.mapper.TaskMapper;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.TaskRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    // BEGIN
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskMapper taskMapper;

    @GetMapping
    public List<TaskDTO> index() {
        var tasks = taskRepository.findAll();

        var tasksDTO = tasks.stream()
                .map(task -> taskMapper.map(task))
                .toList();

        return tasksDTO;
    }

    @GetMapping("/{id}")
    public TaskDTO show(@PathVariable long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        var taskDTO = taskMapper.map(task);
        return taskDTO;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO create(@RequestBody TaskCreateDTO taskCreateDTO) {
        var task = taskMapper.map(taskCreateDTO);
        var user = userRepository.findById(taskCreateDTO.getAssigneeId())
                        .orElseThrow(() -> new ResourceNotFoundException("not found"));
        task.setAssignee(user);
        taskRepository.save(task);
        return taskMapper.map(task);
    }

    @PutMapping("/{id}")
    public TaskDTO update(@PathVariable long id, @RequestBody TaskUpdateDTO taskUpdateDTO) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        var user = userRepository.findById(taskUpdateDTO.getAssigneeId())
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        taskMapper.update(taskUpdateDTO, task);
        task.setAssignee(user);
        taskRepository.save(task);
        return taskMapper.map(task);
    }
    // END
}
