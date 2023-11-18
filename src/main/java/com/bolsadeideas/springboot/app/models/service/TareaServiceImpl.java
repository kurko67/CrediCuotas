package com.bolsadeideas.springboot.app.models.service;

import com.bolsadeideas.springboot.app.models.dao.ITareaDao;
import com.bolsadeideas.springboot.app.models.entity.Tarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TareaServiceImpl implements ITareaService{

    @Autowired
    ITareaDao iTareaDao;


    @Override
    @Transactional(readOnly = true)
    public List<Tarea> findAll() {
        return iTareaDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Tarea> findAllByUsernameOrderByDesc(Long iduser, Pageable pageable ) {
        return iTareaDao.findAllByUsernameOrderByDesc(iduser, pageable );
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Tarea> findAllByUsernameEstadoCerrado(Long iduser, Pageable pageable) {
        return iTareaDao.findAllByUsernameEstadoCerrado(iduser, pageable);
    }

    @Override
    public Page<Tarea> findAll(Pageable pageable) {
        return null;
    }

    @Override
    @Transactional
    public void save(Tarea tarea) {
        iTareaDao.save(tarea);
    }

    @Override
    public Tarea findOne(Long id) {
        return iTareaDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        iTareaDao.deleteById(id);
    }

    @Override
    public void DeleTareaByUser(Long id) {
        iTareaDao.DeleTareaByUser(id);
    }


}
