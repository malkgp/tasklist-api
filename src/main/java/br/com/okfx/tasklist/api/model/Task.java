package br.com.okfx.tasklist.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Where(clause = "ativo=true")
@Entity
@Table(name = "task")
@EqualsAndHashCode(of = {"codigo"})
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotNull
    @Size(min = 5, max = 50)
    private String titulo;

    private String descricao;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private LocalDateTime criadoEm;

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private LocalDateTime modificadoEm;

    @JsonInclude(Include.NON_NULL)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private LocalDateTime removidoEm;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.EM_ANDAMENTO;

    @NotNull
    private boolean ativo = true;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public LocalDateTime getModificadoEm() {
        return modificadoEm;
    }

    public void setModificadoEm(LocalDateTime modificadoEm) {
        this.modificadoEm = modificadoEm;
    }

    public LocalDateTime getRemovidoEm() {
        return removidoEm;
    }

    public void setRemovidoEm(LocalDateTime removidoEm) {
        this.removidoEm = removidoEm;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @PrePersist
    private void prePersist() {
        this.criadoEm = LocalDateTime.now();
        this.modificadoEm = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        this.modificadoEm = LocalDateTime.now();
    }

}
