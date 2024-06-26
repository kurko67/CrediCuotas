package com.bolsadeideas.springboot.app.models.dao;
import com.bolsadeideas.springboot.app.models.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ITareaDao extends JpaRepository<Tarea, Long> {

    @Query(value = "select * from tareas where username_id_usuario = ?1 and estado = 'ABIERTO' order by id_tarea desc", nativeQuery = true)
    Page<Tarea> findAllByUsernameOrderByDesc(Long iduser, Pageable pageable);

    @Query(value = "select * from tareas where username_id_usuario = ?1 and estado = 'CERRADO' order by id_tarea desc", nativeQuery = true)
    Page<Tarea> findAllByUsernameEstadoCerrado(Long iduser, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "delete from tareas where username_id_usuario = :valor1 ", nativeQuery = true)
    void DeleTareaByUser(@Param("valor1") Long id);

}
