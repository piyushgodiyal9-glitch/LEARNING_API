package in.forweb2.learning_api;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Task_Repository extends JpaRepository<Task, Long> {
}