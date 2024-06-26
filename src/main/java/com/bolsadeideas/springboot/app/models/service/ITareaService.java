package com.bolsadeideas.springboot.app.models.service;

import com.bolsadeideas.springboot.app.models.entity.Cliente;

import com.bolsadeideas.springboot.app.models.entity.Tarea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITareaService {

    public List<Tarea> findAll();

    public Page<Tarea> findAllByUsernameOrderByDesc(Long iduser, Pageable pageable);

    public Page<Tarea> findAllByUsernameEstadoCerrado(Long iduser, Pageable pageable);

    public Page<Tarea> findAll(Pageable pageable);

    public void save(Tarea tarea);

    public Tarea findOne(Long id);

    public void delete(Long id);

    public void DeleTareaByUser(Long id);




}
