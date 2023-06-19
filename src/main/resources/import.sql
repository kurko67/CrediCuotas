/* tabla usuario

insert into usuario (password, username) VALUE ("$2a$10$561esWd5wPWJ.8ocbqMJnuZ4RmVPUTdPPpJMt4b5qnZ6wD9rM/Wn2","lucas_gambetta");
insert into usuario (password, username) VALUE ("$2a$10$561esWd5wPWJ.8ocbqMJnuZ4RmVPUTdPPpJMt4b5qnZ6wD9rM/Wn2","cristian_vicino");
insert into usuario (password, username) VALUE ("$2a$10$561esWd5wPWJ.8ocbqMJnuZ4RmVPUTdPPpJMt4b5qnZ6wD9rM/Wn2","maximiliano_alaniz");
insert into usuario (password, username) VALUE ("$2a$10$561esWd5wPWJ.8ocbqMJnuZ4RmVPUTdPPpJMt4b5qnZ6wD9rM/Wn2","yemina_moretti");



insert into rol (nombre, id_usuario) VALUES ("ROLE_ADMIN", 1);
insert into rol (nombre, id_usuario) VALUES ("ROLE_ADMIN", 2);
insert into rol (nombre, id_usuario) VALUES ("ROLE_ADMIN", 3);
insert into rol (nombre, id_usuario) VALUES ("ROLE_USER", 4);

/* tabla clientes */

insert into clientes (nombre, apellido, celular, email, cuil, provincia, situacion_laboral, banco_cobro, create_at, vendedor) values ("Maximiliano", "Alaniz", "2613438409", "maxi.alaniz@gmail.com","20355521983", "MENDOZA", "Empleado Publico", "Nacion","2023-06-17 18:09:27.118000","lucas_gambetta");

/* tabla tareas */

insert into tareas (comentario, created_at, estado, fecha_tarea, update_at, cliente_id_cliente, username_id_usuario) values ("Llamarlo al 261343809 14 hs","2023-06-17 18:09:54.848000", "ABIERTO", "2023-06-29 00:00:00.000000", "2023-06-17 18:09:54.847000", 1, 1 );