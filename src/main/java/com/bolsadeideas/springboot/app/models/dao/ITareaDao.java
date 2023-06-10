package com.bolsadeideas.springboot.app.models.dao;
import com.bolsadeideas.springboot.app.models.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITareaDao extends JpaRepository<Tarea, Long> {



}
