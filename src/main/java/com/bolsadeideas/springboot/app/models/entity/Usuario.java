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
    // le indicamos cual es la llave foránea cuando generamos la relacion en el Schema
    @JoinColumn(name="id_usuario")

    //Lista de roles que acepta el usuario
    private List<Rol> roles;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    private static final long serialVersionUID = 1L;

}
