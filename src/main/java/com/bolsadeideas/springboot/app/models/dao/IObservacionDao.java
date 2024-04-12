package com.bolsadeideas.springboot.app.models.dao;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.entity.Observacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IObservacionDao extends JpaRepository<Observacion, Long> {

    @Query(value = "select * from observacion where id_cliente = ?1", nativeQuery = true)
    List<Observacion> findByIdCliente(Long id_cliente);

}
