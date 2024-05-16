create table pessoa (
	idpessoa serial primary key,
	nome varchar(100) not null,
	login varchar(100) not null,
	senha varchar(128) not null
);
insert into pessoa(nome, login, senha) values ('Alexandre Bernardes', 'xexe', '123456');


create table aluno(
	idaluno serial primary key,
	idpessoa int unique, 
	ra bigint not null unique,
	saldoads numeric(4,2),
	situacao varchar(1) not null,
	permitelogin varchar(1) not null,
	constraint fk_aluno_pessoa foreign key (idpessoa) references pessoa
);

create table administrador(
    idadministrador serial primary key,
    idpessoa int unique,
    cpf varchar(14) not null unique,
    situacao varchar(1) not null,
    permitelogin varchar(1) not null,
	constraint fk_administrador_pessoa foreign key (idpessoa) references pessoa
);

insert into administrador (idpessoa, cpf, situacao, permitelogin) values (1, '49407270840','A', 'S');

create table semestre (
	idsemestre serial primary key,
	numsemestre varchar(20),
	situacao varchar(1) not null
);

insert into semestre(numsemestre, situacao) values ('1º Semestre', 'A'),
('2º Semestre', 'A'),
('3º Semestre', 'A'),
('4º Semestre', 'A'),
('5º Semestre', 'A'),
('6º Semestre', 'A');

create table disciplina(
    iddisciplina serial primary key,
    nomedisciplina varchar(100) not null,
    situacao varchar(1) not null,
    idsemestre int not null,
    constraint fk_semestre foreign key (idsemestre) references semestre(idsemestre)
);

insert into disciplina (nomedisciplina,situacao,idsemestre) values ('Estrutura de Dados', 'A', 1);

create table professor(
	idprofessor serial primary key,
	idpessoa int unique,
	rm bigint not null unique,
	emailprofessor varchar(50) not null,
	formacaoprofessor varchar(30) not null,
	situacao varchar(1) not null,
	permitelogin varchar(1) not null,
	iddisciplina int not null,
	constraint fk_professor_pessoa foreign key (idpessoa) references pessoa,
	constraint fk_prof_disciplina foreign key (iddisciplina) references disciplina
);

create table turma(
	idturma serial primary key,
	numturma varchar(50) not null,
	situacao varchar(1) not null,
	iddisciplina int not null,
	constraint fk_disciplina_turma foreign key (iddisciplina) references disciplina(iddisciplina)
);

create table atividade(
	idatividade serial primary key,
	descricao varchar(100) not null,
	situacao varchar(1) not null,
	status varchar(1) not null,
	dataatividade date not null,
	dataprazo date not null,
	documento text not null, 
	iddisciplina int not null,
	idturma int not null,
	pontuacaomax int not null,
	pontuacaofinal int not null,
	constraint fk_atividade foreign key (iddisciplina) references disciplina(iddisciplina),
	constraint fk_turma foreign key (idturma) references turma(idturma)

);


create table atividaderealizada(
    idatividaderealizada serial primary key,
    idatividade int not null,
    confirmacao varchar(1) not null,
    constraint fk_atividade_realizada foreign key (idatividade) references atividade(idatividade)
   
);


CREATE TABLE turmaaluno (
    idturmaaluno SERIAL PRIMARY KEY,
    idturma INT,
    idaluno INT,
    CONSTRAINT fk_turma FOREIGN KEY (idturma) REFERENCES turma (idturma),
    CONSTRAINT fk_aluno FOREIGN KEY (idaluno) REFERENCES aluno (idaluno)
);

create or replace view usuario as 
select p.idpessoa, p.nome, p.login, p.senha, pr.idprofessor as id, 'Professor' as tipo from pessoa p, professor pr
where pr.idpessoa = p.idpessoa and pr.situacao = 'A' and pr.permitelogin = 'S' 
union 
select p.idpessoa, p.nome, p.login, p.senha, a.idadministrador as id, 'Administrador' as tipo from pessoa p, administrador a 
where a.idpessoa = p.idpessoa and a.situacao = 'A' and a.permitelogin = 'S'
union
select p.idpessoa, p.nome, p.login, p.senha, al.idaluno as id, 'Aluno' as tipo from pessoa p, aluno al 
where al.idpessoa = p.idpessoa and al.situacao = 'A' and al.permitelogin = 'S';


  

CREATE OR REPLACE FUNCTION insere_atividade_realizada()
RETURNS TRIGGER AS $$
BEGIN
    -- Verifica se a atividade mudou de status "E" para "N"
    IF NEW.status = 'N' AND OLD.status = 'E' THEN
        -- Remove a atividade da tabela atividadeRealizada
        DELETE FROM atividaderealizada
        WHERE idatividade = NEW.idatividade;
    ELSIF NEW.status = 'E' AND OLD.status = 'N' THEN
        -- Insere os dados da atividade realizada na tabela atividadeRealizada
        INSERT INTO atividaderealizada (idatividade, confirmacao)
        VALUES (NEW.idatividade, 'N'); -- Substitua 'N' pela confirmação desejada e 0 pela pontuação inicial
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Cria a trigger que acionará a função após uma atualização na tabela atividade
CREATE TRIGGER atividade_status_trigger
AFTER UPDATE OF status ON atividade
FOR EACH ROW
EXECUTE FUNCTION insere_atividade_realizada();



