package com.bolsadeideas.springboot.app.models.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Forma de generacion de la llave primaria ya que especificamos en la bd q AI
    private Long idUsuario;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    //Esto es para hacer la relacion UNO a MUCHOS es la Schema de tabla Test UN usuario puede tener MUCHOS roles
    @OneToMany
    @JoinColumn(name="id_usuario") // le indicamos cual es la llave foránea cuando generamos la relacion en el Schema

    //Lista de roles que acepta el usuario
    private List<Rol> roles;



}
