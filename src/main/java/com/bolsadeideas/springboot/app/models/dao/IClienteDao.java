package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long>{



     @Query("select u from Cliente u where u.vendedor = ?1 and u.estado <> 'RECHAZADO' and u.estado <> 'LIQUIDADO' order by u.idCliente desc")
     Page<Cliente> findAllByVendedor(String vendedor, Pageable pageable);

     @Query("select u from Cliente u where u.vendedor = ?1 order by u.idCliente desc")
     Page<Cliente> findAllByVendedorTodos(String vendedor, Pageable pageable);

     /* Buscar todos los clientes */
     @Query("select u from Cliente u order by u.idCliente desc")
     Page<Cliente> findAllByIdOrdeByDesc(Pageable pageable);

     /* select - mis solicitudes  */
     @Query(value = "select * from clientes where cuil LIKE %?1% and vendedor = ?2 ", nativeQuery = true)
     Page<Cliente> findAllByVendedorAndCuit(String cuil, String vendedor, Pageable pageable);


     @Query(value = "select * from clientes where cuil LIKE %?1% order by id_cliente desc", nativeQuery = true)
     Page<Cliente> ControlfindAllByCuil(String cuil, Pageable pageable);

     /* select por estado - mis solicitudes */
     @Query(value = "select * from clientes where estado = ?1 and vendedor = ?2 order by id_cliente desc", nativeQuery = true)
     Page<Cliente> findAllByVendedorAndEstado(String estado, String vendedor, Pageable pageable);

     /* select por estado - control */
     @Query(value = "select * from clientes where estado = ?1 order by id_cliente desc ", nativeQuery = true)
     Page<Cliente> findAllByEstado(String estado, Pageable pageable);

     @Query("select u from Cliente u where u.vendedor = null order by u.idCliente desc")
     Page<Cliente> findAllByVendedorIsnull(Pageable pageable);

     @Query(value = "select * from clientes where cuil LIKE %?1% and vendedor = null ", nativeQuery = true)
     Page<Cliente> findAllByCuitAndVendedorIsNull(String cuil, Pageable pageable);

     /* Estadisticas */


     // CANTIDAD CLIENTES


     @Query(value = "SELECT count(id_cliente) FROM clientes where create_at > ?1", nativeQuery = true)
     Long countClientes(LocalDate fecha_inicio);

     //TOTAL RESP DE DISPONIBLE
     @Query(value = "SELECT count(id_cliente) FROM clientes where estado = 'RESP. DE DISPONIBLE' and create_at > ?1", nativeQuery = true)
     Long SumAprobado(LocalDate fecha_inicio);

     // ES EN PROCESO
     @Query(value = "SELECT count(id_cliente)  FROM clientes where estado = 'ENVIADO' and create_at > ?1", nativeQuery = true)
     Long SolicitudesEnProceso(LocalDate fecha_inicio);

     //OBSERVADOS
     @Query(value = "SELECT count(id_cliente)  FROM clientes where estado = 'OBSERVADO' and create_at > ?1", nativeQuery = true)
     Long SolicitudesObservadas(LocalDate fecha_inicio);

     //RECHAZADOS
     @Query(value = "SELECT count(id_cliente)  FROM clientes where estado = 'RECHAZADO' and create_at > ?1", nativeQuery = true)
     Long SolicitudesRechazadas(LocalDate fecha_inicio);

     //TOTAL LIQUIDADAS
     @Query(value = "SELECT sum(monto_solicitado)  FROM clientes where estado = 'LIQUIDADO' and closed_at > ?1", nativeQuery = true)
     Long SolicitudesLiquidadas(LocalDate fecha_inicio);

     @Query(value = "SELECT count(id_cliente)  FROM clientes where estado = 'LIQUIDADO' and closed_at > ?1", nativeQuery = true)
     Long CantidadLiquidadas(LocalDate fecha_inicio);


     /* reporte de excel por fechas */
     List<Cliente> findBycreateAtBetween(Date fechaInicio, Date fechaFin);



     /* chart estadisticas */

     @Query(value = "SELECT vendedor, count(id_cliente) FROM clientes where estado = 'LIQUIDADO' GROUP BY vendedor", nativeQuery = true)
     List<Object[]> obtenerVentasPorMes();

     @Query(value = "SELECT financiador, count(id_cliente) FROM clientes where estado = 'LIQUIDADO' GROUP BY financiador", nativeQuery = true)
     List<Object[]> obtenerFinanciadorLiquidacion();

     @Query(value = "SELECT provincia, count(id_cliente) as cantidad FROM clientes GROUP BY provincia order by cantidad desc", nativeQuery = true)
     List<Object[]> obtenerTopProvincias();

     @Query(value = "SELECT situacion_laboral, count(id_cliente) as cantidad FROM clientes GROUP BY situacion_laboral order by cantidad desc", nativeQuery = true)
     List<Object[]> obtenerTopSituacionLaboral();

     @Query(value = "SELECT dependencia, count(id_cliente) as cantidad FROM clientes GROUP BY dependencia order by cantidad desc", nativeQuery = true)
     List<Object[]> obtenerTopDependencia();

     @Query(value = "SELECT canal, count(id_cliente) as cantidad FROM clientes GROUP BY canal order by cantidad desc", nativeQuery = true)
     List<Object[]> obtenerTopCanal();


}
