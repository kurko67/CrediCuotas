package com.bolsadeideas.springboot.app.models.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.bolsadeideas.springboot.app.models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.models.dao.IClienteDao;
import com.bolsadeideas.springboot.app.models.entity.Cliente;

import javax.transaction.TransactionScoped;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findBycreateAtBetween(Date fechaInicio, Date fechaFin) {
		return (List<Cliente>) clienteDao.findBycreateAtBetween(fechaInicio, fechaFin);
	}

	/* estadisticas usuario liquidacion */
	@Override
	@Transactional(readOnly = true)
	public List<Object[]> obtenerVentasPorMes() {
		return (List<Object[]>) clienteDao.obtenerVentasPorMes();
	}

	/* estadisticas financiador liquidacion */
	@Override
	@Transactional(readOnly = true)
	public List<Object[]> obtenerFinanciadorLiquidacion() {
		return (List<Object[]>) clienteDao.obtenerFinanciadorLiquidacion();
	}

	/* estadisticas top provincias */
	@Override
	@Transactional(readOnly = true)
	public List<Object[]> obtenerTopProvincias() {
		return (List<Object[]>) clienteDao.obtenerTopProvincias();
	}

	/* estadisticas top obtenerTopSituacionLaboral */
	@Override
	@Transactional(readOnly = true)
	public List<Object[]> obtenerTopSituacionLaboral() {
		return (List<Object[]>) clienteDao.obtenerTopSituacionLaboral();
	}

	/* estadisticas top obtenerTopDependencia */
	@Override
	@Transactional(readOnly = true)
	public List<Object[]> obtenerTopDependencia() {
		return (List<Object[]>) clienteDao.obtenerTopDependencia();
	}

	/* estadisticas obtenerTopCanal */
	@Override
	@Transactional(readOnly = true)
	public List<Object[]> obtenerTopCanal() {
		return (List<Object[]>) clienteDao.obtenerTopCanal();
	}



	@Override
	@Transactional
	public void save(Cliente cliente) {
		clienteDao.save(cliente);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findOne(Long id) {
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Long countClientes(LocalDate fecha_inicio) {
		return clienteDao.countClientes(fecha_inicio);
	}


	@Override
	@Transactional(readOnly = true)
	public Long SumAprobado(LocalDate fecha_inicio) {
		return clienteDao.SumAprobado(fecha_inicio);
	}

	@Override
	@Transactional(readOnly = true)
	public Long SolicitudesEnProceso(LocalDate fecha_inicio) {
		return clienteDao.SolicitudesEnProceso(fecha_inicio);
	}

	@Override
	@Transactional(readOnly = true)
	public Long SolicitudesRechazadas(LocalDate fecha_inicio) {
		return clienteDao.SolicitudesRechazadas(fecha_inicio);
	}

	@Override
	@Transactional(readOnly = true)
	public Long SolicitudesLiquidadas(LocalDate fecha_inicio) {
		return clienteDao.SolicitudesLiquidadas(fecha_inicio);
	}

	@Override
	@Transactional(readOnly = true)
	public Long CantidadLiquidadas(LocalDate fecha_inicio) {
		return clienteDao.CantidadLiquidadas(fecha_inicio);
	}



	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAllByVendedorIsnull(Pageable pageable) {
		return clienteDao.findAllByVendedorIsnull(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAllByVendedor(String vendedor, Pageable pageable) {
		return clienteDao.findAllByVendedor(vendedor, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAllByVendedorTodos(String vendedor, Pageable pageable) {
		return clienteDao.findAllByVendedorTodos(vendedor, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAllByIdOrdeByDesc(Pageable pageable) {
		return clienteDao.findAllByIdOrdeByDesc(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAllByVendedorAndCuit(String cuil, String vendedor, Pageable pageable) {
		return clienteDao.findAllByVendedorAndCuit(cuil, vendedor, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> ControlfindAllByCuil(String cuil, Pageable pageable) {
		return clienteDao.ControlfindAllByCuil(cuil, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAllByVendedorAndEstado(String estado, String vendedor, Pageable pageable) {
		return clienteDao.findAllByVendedorAndEstado(estado, vendedor, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAllByEstado(String estado, Pageable pageable) {
		return clienteDao.findAllByEstado(estado, pageable);
	}



	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAllByCuitAndVendedorIsNull(String cuil, Pageable pageable) {
		return clienteDao.findAllByCuitAndVendedorIsNull(cuil, pageable);
	}



}
