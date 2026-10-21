package in.forweb2.learning_api.TaskData;

import in.forweb2.learning_api.UserData.User;
import in.forweb2.learning_api.UserData.UserRepository;
import in.forweb2.learning_api.dto.TaskResponsedto;
import in.forweb2.learning_api.dto.TaskRequestdto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private UserRepository userRepository;
    private final Task_Repository taskRepository;

    public TaskService(Task_Repository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskResponsedto createTask(TaskRequestdto dto) {

        Task task = new Task();

        task.setTitle(dto.getTitle());
        task.setCompleted(dto.isCompleted());

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        task.setUser(user);

        Task savedTask = taskRepository.save(task);

        return new TaskResponsedto(
                savedTask.getId(),
                savedTask.getTitle(),
                savedTask.isCompleted()
        );
    }

    public List<TaskResponsedto> getAllTask() {

        List<Task> tasks = taskRepository.findAll();

        List<TaskResponsedto> responses = new ArrayList<>();

        for (Task task : tasks) {

            TaskResponsedto dto = new TaskResponsedto();

            dto.setId(task.getId());
            dto.setTitle(task.getTitle());
            dto.setCompleted(task.isCompleted());

            responses.add(dto);
        }

        return responses;
    }

    public TaskResponsedto getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new TaskNotFoundException("Task not found"));

        TaskResponsedto response = new TaskResponsedto();

        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setCompleted(task.isCompleted());

        return response;
    }

    public TaskResponsedto updateTask(Long id, TaskRequestdto dto) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new TaskNotFoundException("Task not found"));

        task.setTitle(dto.getTitle());
        task.setCompleted(dto.isCompleted());

        Task updatedTask = taskRepository.save(task);

        TaskResponsedto response = new TaskResponsedto();

        response.setId(updatedTask.getId());
        response.setTitle(updatedTask.getTitle());
        response.setCompleted(updatedTask.isCompleted());

        return response;
    }

    public void deleteTask(Long id) {

        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException("Task not found");
        }

        taskRepository.deleteById(id);
    }

    public List<Task> getTasksByUserId(Long userId) {
        return taskRepository.findByUserId(userId);
    }

}


