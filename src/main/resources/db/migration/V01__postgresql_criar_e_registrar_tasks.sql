CREATE TABLE task
(
  codigo           SERIAL                        NOT NULL,
  titulo           CHARACTER VARYING(50)         NOT NULL,
  descricao        CHARACTER VARYING(255),
  criado_em        TIMESTAMP WITHOUT TIME ZONE   NOT NULL,
  modificado_em    TIMESTAMP WITHOUT TIME ZONE   NOT NULL,
  removido_em      TIMESTAMP WITHOUT TIME ZONE,
  status           CHARACTER VARYING(20)         NOT NULL,
  ativo            BOOLEAN                       NOT NULL,

  CONSTRAINT pk_task PRIMARY KEY (codigo)
);

INSERT INTO task (titulo, descricao, criado_em, modificado_em, status, ativo) values ('Entrar para a SUPERO', 'Concluir e enviar o desafio', NOW(), NOW(), 'EM_ANDAMENTO', TRUE);
