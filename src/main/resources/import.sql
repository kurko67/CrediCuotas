/* tabla usuario */

insert into usuario (password, username) VALUE ("$2a$10$561esWd5wPWJ.8ocbqMJnuZ4RmVPUTdPPpJMt4b5qnZ6wD9rM/Wn2","lucas_gambetta");
insert into usuario (password, username) VALUE ("$2a$10$561esWd5wPWJ.8ocbqMJnuZ4RmVPUTdPPpJMt4b5qnZ6wD9rM/Wn2","cristian_vicino");
insert into usuario (password, username) VALUE ("$2a$10$561esWd5wPWJ.8ocbqMJnuZ4RmVPUTdPPpJMt4b5qnZ6wD9rM/Wn2","maximiliano_alaniz");
insert into usuario (password, username) VALUE ("$2a$10$561esWd5wPWJ.8ocbqMJnuZ4RmVPUTdPPpJMt4b5qnZ6wD9rM/Wn2","yemina_moretti");

/*  tabla rol */

insert into rol (nombre, id_usuario) VALUES ("ROLE_ADMIN", 1)
insert into rol (nombre, id_usuario) VALUES ("ROLE_ADMIN", 2)
insert into rol (nombre, id_usuario) VALUES ("ROLE_ADMIN", 3)
insert into rol (nombre, id_usuario) VALUES ("ROLE_USER", 3)
insert into rol (nombre, id_usuario) VALUES ("ROLE_USER", 4)

/* tabla clientes */

insert into clientes (apellido, banco_cobro, celular, cuil, dependencia, domicilio, email, localidad, nombre, otro_telefono, provincia, situacion_laboral) values ("ALANIZ", "NACION", "2613438409", "20-35552198-3", "", "", "maxi.alaniz@gmail.com", "", "MAXIMILIANO", "", "MENDOZA", "EMPLEADO PUBLICO");

