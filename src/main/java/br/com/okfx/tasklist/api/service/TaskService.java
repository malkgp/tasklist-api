package br.com.okfx.tasklist.api.service;

import br.com.okfx.tasklist.api.model.Task;
import br.com.okfx.tasklist.api.model.TaskStatus;
import br.com.okfx.tasklist.api.repository.ITaskRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private ITaskRepository taskRepository;

    public Task atualizar(Long codigo, Task novaTask) {
        Task taskSalva = buscarPeloCodigo(codigo);
        BeanUtils.copyProperties(novaTask, taskSalva,
                "codigo",
                "criadoEm",
                "modificadoEm",
                "removidoEm"
        );
        return taskRepository.save(taskSalva);
    }

    public Task concluir(Long codigo) {
        Task taskSalva = buscarPeloCodigo(codigo);
        taskSalva.setStatus(TaskStatus.CONCLUIDO);
        return taskRepository.save(taskSalva);
    }

    public Task remover(Long codigo) {
        Task taskSalva = buscarPeloCodigo(codigo);

        taskSalva.setRemovidoEm(LocalDateTime.now());
        taskSalva.setAtivo(false);

        return taskRepository.save(taskSalva);
    }

    public Task buscarPeloCodigo(Long codigo) {
        Task taskSalva = taskRepository.findOne(codigo);
        return Optional.ofNullable(taskSalva).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }
}
