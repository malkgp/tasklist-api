package br.com.okfx.tasklist.api.resource;

import br.com.okfx.tasklist.api.event.RecursoCriadoEvent;
import br.com.okfx.tasklist.api.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.okfx.tasklist.api.repository.ITaskRepository;
import br.com.okfx.tasklist.api.service.TaskService;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/tasks")
public class TaskResource {

    @Autowired
    private ITaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @GetMapping
    public List<Task> listar() {
        return taskRepository.findAll();
    }

    @PostMapping
    // @PreAuthorize("hasAuthority('ROLE_CADASTRAR_TASK') and #oauth2.hasScope('write')")
    public ResponseEntity<Task> criar(@Valid @RequestBody Task task, HttpServletResponse response) {
        Task taskSalva = taskRepository.save(task);

        eventPublisher.publishEvent(
                new RecursoCriadoEvent(this, response, taskSalva.getCodigo()));

        return ResponseEntity.status(HttpStatus.CREATED).body(taskSalva);
    }
    
    @PutMapping("/{codigo}")
    // @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_TASK') and #oauth2.hasScope('write')")
    public ResponseEntity<Task> atualizar(@PathVariable Long codigo, @Valid @RequestBody Task novaTask) {
        Task taskAtualizada = taskService.atualizar(codigo, novaTask);
        return ResponseEntity.ok(taskAtualizada);
    }

    @DeleteMapping("/{codigo}")
    // @PreAuthorize("hasAuthority('ROLE_REMOVER_TASK') and #oauth2.hasScope('write')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigo) {
        taskService.remover(codigo);
    }

    @PatchMapping("/{codigo}/concluido")
    // @PreAuthorize("hasAuthority('ROLE_CONCLUIR_TASK') and #oauth2.hasScope('write')")
    public ResponseEntity<Task> concluir(@PathVariable Long codigo) {
        Task taskConcluida = taskService.concluir(codigo);
        return ResponseEntity.ok(taskConcluida);
    }

}
