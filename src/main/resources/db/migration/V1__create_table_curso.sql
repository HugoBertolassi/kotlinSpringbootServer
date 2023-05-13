create table curso(
    id bigint NOT NULL AUTO_INCREMENT,
    nome varchar(50),
    categoria varchar(50),
    primary key(id)
);

insert into curso(id, nome, categoria) values (1, 'kotlin' ,'backend')