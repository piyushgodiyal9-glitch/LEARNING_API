package in.forweb2.learning_api.UserData;

import in.forweb2.learning_api.TaskData.Task;
import in.forweb2.learning_api.TaskData.TaskService;
import in.forweb2.learning_api.UserData.User;
import in.forweb2.learning_api.UserData.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}/tasks")
    public List<Task> getUserTasks(
            @PathVariable Long id) {
        return taskService.getTasksByUserId(id);
    }

}