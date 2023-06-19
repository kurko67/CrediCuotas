package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsadeideas.springboot.app.models.entity.Cliente;

import java.util.List;

public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long>{

     @Query("select u from Cliente u where u.vendedor = ?1 order by u.idCliente desc")
     List<Cliente> findAllByVendedor(String vendedor);

     @Query("select u from Cliente u where u.vendedor = null order by u.idCliente desc")
     Page<Cliente> findAllByVendedorIsnull(Pageable pageable);

}
