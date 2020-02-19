CREATE TABLE usuario
(
  codigo SERIAL                 NOT NULL,
  nome   CHARACTER VARYING(50)  NOT NULL,
  email  CHARACTER VARYING(50)  NOT NULL,
  senha  CHARACTER VARYING(150) NOT NULL,

  CONSTRAINT pk_usuario PRIMARY KEY (codigo)
);

CREATE TABLE permissao
(
  codigo    SERIAL                NOT NULL,
  descricao CHARACTER VARYING(50) NOT NULL,

  CONSTRAINT pk_permissao PRIMARY KEY (codigo)
);

CREATE TABLE usuario_permissao
(
  codigo_usuario   INTEGER NOT NULL,
  codigo_permissao INTEGER NOT NULL
);
ALTER TABLE usuario_permissao ADD PRIMARY KEY (codigo_usuario, codigo_permissao);
ALTER TABLE usuario_permissao ADD FOREIGN KEY (codigo_usuario) REFERENCES usuario(codigo);
ALTER TABLE usuario_permissao ADD FOREIGN KEY (codigo_permissao) REFERENCES permissao(codigo);

-- INSERTS
INSERT INTO usuario (codigo, nome, email, senha) VALUES (1, 'Admin', 'admin@tasklist.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.');

INSERT INTO permissao (codigo, descricao) VALUES (1, 'ROLE_CADASTRAR_TASK');
INSERT INTO permissao (codigo, descricao) VALUES (2, 'ROLE_ATUALIZAR_TASK');
INSERT INTO permissao (codigo, descricao) VALUES (3, 'ROLE_REMOVER_TASK');
INSERT INTO permissao (codigo, descricao) VALUES (4, 'ROLE_CONCLUIR_TASK');

-- admin
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 1);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 2);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 3);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 4);
