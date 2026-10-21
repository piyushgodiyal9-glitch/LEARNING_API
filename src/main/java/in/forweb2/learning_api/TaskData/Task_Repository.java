package in.forweb2.learning_api.TaskData;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Task_Repository extends JpaRepository<Task, Long> {

    List<Task> findByUserId(Long userId);

}