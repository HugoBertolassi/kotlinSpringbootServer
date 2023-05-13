create table usuario(
    id bigint NOT NULL AUTO_INCREMENT,
    nome varchar(50),
    email varchar(100),
    primary key(id)
);

insert into usuario(id, nome, email) values (1, 'joao' ,'joao@email.com')