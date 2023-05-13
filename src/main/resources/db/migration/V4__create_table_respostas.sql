create table resposta(
    id bigint NOT NULL AUTO_INCREMENT,
    mensagem varchar(300) NOT NULL,
    data_criacao datetime NOT NULL,
    status varchar(20) NOT NULL,
    topico_id bigint NOT NULL,
    autor_id bigint NOT NULL,
    solucao int,
    primary key(id),
    foreign key(topico_id) references topico(id),
    foreign key(autor_id) references usuario(id)
);

