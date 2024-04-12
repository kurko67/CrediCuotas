package com.bolsadeideas.springboot.app.models.service;

import com.bolsadeideas.springboot.app.models.entity.Observacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IObservacionService {


    public List<Observacion> findByIdCliente(Long id_cliente);

    public void save(Observacion observacion);

    public void delete(Long id);


}
