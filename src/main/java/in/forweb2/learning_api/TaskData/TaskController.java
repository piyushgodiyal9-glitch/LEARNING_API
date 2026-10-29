package in.forweb2.learning_api.TaskData;

import in.forweb2.learning_api.dto.TaskRequestdto;
import in.forweb2.learning_api.dto.TaskResponsedto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/tasks")
    public TaskResponsedto createTask(@Valid @RequestBody TaskRequestdto dto) {
        return taskService.createTask(dto);
    }

    @GetMapping("/tasks")
    public List<TaskResponsedto> getAllTasks() {
        return taskService.getAllTask();
    }

    @GetMapping("/tasks/page")
    public Page<Task> PageSort(

            @RequestParam int page,

            @RequestParam int size,

            @RequestParam String sortBy,

            @RequestParam String direction){

        return taskService.PageSort(
                page,
                size,
                sortBy,
                direction
        );
    }

    @GetMapping("/tasks/{id}")
    public TaskResponsedto getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("/tasks/{id}")
    public TaskResponsedto updateTask(@PathVariable Long id, @Valid @RequestBody TaskRequestdto dto) {
        return taskService.updateTask(id, dto);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}




