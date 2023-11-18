package com.bolsadeideas.springboot.app.models.service;

import com.bolsadeideas.springboot.app.models.entity.Rol;
import com.bolsadeideas.springboot.app.models.entity.Usuario;

public interface IRolService {

    public void save(Usuario usuario);

    public Usuario FindByUser (Usuario usuario);

    public Rol FindByName(String rol);


}
