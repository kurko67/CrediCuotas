package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long>{

     @Query("select u from Cliente u where u.vendedor = ?1 order by u.idCliente desc")
     Page<Cliente> findAllByVendedor(String vendedor, Pageable pageable);

     @Query(value = "select * from clientes where cuil LIKE %?1% and vendedor = ?2 ", nativeQuery = true)
     Page<Cliente> findAllByVendedorAndCuit(String cuil, String vendedor, Pageable pageable);

     @Query("select u from Cliente u where u.vendedor = null order by u.idCliente desc")
     Page<Cliente> findAllByVendedorIsnull(Pageable pageable);

     @Query(value = "select * from clientes where cuil LIKE %?1% and vendedor = null ", nativeQuery = true)
     Page<Cliente> findAllByCuitAndVendedorIsNull(String cuil, Pageable pageable);

}
