ALTER TABLE usuario ADD COLUMN password text;

UPDATE usuario SET password ='$2a$12$CjB6LJLDbIguDGQalU8xfuqZEjClYYvAXQ7RdYVmyj.89g/nDvetO' WHERE id =1 ;