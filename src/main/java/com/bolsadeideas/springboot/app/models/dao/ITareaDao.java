package com.bolsadeideas.springboot.app.models.dao;
import com.bolsadeideas.springboot.app.models.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITareaDao extends JpaRepository<Tarea, Long> {

    @Query(value = "select * from tareas where username_id_usuario = ?1 and estado = 'ABIERTO'", nativeQuery = true)
    List<Tarea> findAllByUsernameOrderByDesc(Long iduser);




}
