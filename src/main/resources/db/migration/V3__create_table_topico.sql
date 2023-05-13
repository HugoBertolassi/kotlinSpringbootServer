create table topico(
    id bigint NOT NULL AUTO_INCREMENT,
    titulo varchar(50) NOT NULL,
    mensagem varchar(300) NOT NULL,
    data_criacao datetime NOT NULL,
    data_ultima_alteracao datetime,
    status varchar(20) NOT NULL,
    curso_id bigint NOT NULL,
    autor_id bigint NOT NULL,
    primary key(id),
    foreign key(curso_id) references curso(id),
    foreign key(autor_id) references usuario(id)
);