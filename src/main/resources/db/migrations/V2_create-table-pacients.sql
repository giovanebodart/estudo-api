CREATE TABLE pacientes(
    id TEXT NOT NULL UNIQUE,
    nome TEXT NOT NULL,
    sexo TEXT NOT NULL,
    telefone TEXT NOT NULL UNIQUE,
    data_de_nascimento DATE NOT NULL,
    cpf TEXT NOT NULL UNIQUE,
    peso DOUBLE NOT NULL,
    altura DOUBLE NOT NULL,
    diagnostico TEXT NOT NULL,
    tratamento TEXT NOT NULL
);