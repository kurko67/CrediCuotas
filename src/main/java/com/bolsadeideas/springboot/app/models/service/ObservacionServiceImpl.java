package com.bolsadeideas.springboot.app.models.service;

import com.bolsadeideas.springboot.app.models.dao.IObservacionDao;
import com.bolsadeideas.springboot.app.models.entity.Observacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ObservacionServiceImpl implements IObservacionService {


    @Autowired
    private IObservacionDao observacionDao;

    @Override
    @Transactional(readOnly = true)
    public List<Observacion> findByIdCliente(Long id_cliente) {
        return observacionDao.findByIdCliente(id_cliente);
    }

    @Override
    @Transactional
    public void save(Observacion observacion) {
        observacionDao.save(observacion);
    }

    @Override
    public void delete(Long id) {
        observacionDao.deleteById(id);
    }
}
