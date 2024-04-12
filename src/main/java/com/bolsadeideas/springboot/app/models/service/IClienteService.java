package com.bolsadeideas.springboot.app.models.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.app.models.entity.Cliente;

public interface IClienteService {
	
	public List<Cliente> findAll();

	public List<Cliente> findBycreateAtBetween(Date fechaInicio, Date fechaFin);

	/* estadisticas usuario liquidacion */
	public List<Object[]> obtenerVentasPorMes();

	/* estadisticas financiador liquidacion */
	public List<Object[]> obtenerFinanciadorLiquidacion();

	/* estadisticas top Provincias */
	public List<Object[]> obtenerTopProvincias();

	/* estadisticas top SitLaboral */
	public List<Object[]> obtenerTopSituacionLaboral();

	/* estadisticas top Dependencia */
	public List<Object[]> obtenerTopDependencia();

	/* estadisticas obtenerTopCanal */
	public List<Object[]> obtenerTopCanal();
	
	public Page<Cliente> findAll(Pageable pageable);

	public Page<Cliente> findAllByVendedorIsnull(Pageable pageable);

	public Page<Cliente> findAllByVendedor(String vendedor, Pageable pageable);

	public Page<Cliente> findAllByVendedorTodos(String vendedor, Pageable pageable);

	public Page<Cliente> findAllByIdOrdeByDesc(Pageable pageable);

	public Page<Cliente> findAllByVendedorAndCuit(String cuil, String vendedor, Pageable pageable);

	public Page<Cliente> ControlfindAllByCuil(String cuil, Pageable pageable);

	public Page<Cliente> findAllByVendedorAndEstado(String estado, String vendedor, Pageable pageable);

	public Page<Cliente> findAllByEstado(String estado, Pageable pageable);

	public Page<Cliente> findAllByCuitAndVendedorIsNull(String cuil, Pageable pageable);

	public void save(Cliente cliente);
	
	public Cliente findOne(Long id);
	
	public void delete(Long id);

	public Long countClientes(LocalDate fecha_inicio);

	public Long SumAprobado(LocalDate fecha_inicio);

	public Long SolicitudesEnProceso(LocalDate fecha_inicio);

	public Long SolicitudesRechazadas(LocalDate fecha_inicio);

	public Long SolicitudesLiquidadas(LocalDate fecha_inicio);

	public Long CantidadLiquidadas(LocalDate fecha_inicio);








	
}
