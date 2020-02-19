package br.com.okfx.tasklist.api.repository;

import br.com.okfx.tasklist.api.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaskRepository extends JpaRepository<Task, Long> {

}
