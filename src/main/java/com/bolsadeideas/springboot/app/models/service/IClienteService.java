package com.bolsadeideas.springboot.app.models.service;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.app.models.entity.Cliente;

public interface IClienteService {
	
	public List<Cliente> findAll();
	
	public Page<Cliente> findAll(Pageable pageable);

	public Page<Cliente> findAllByVendedorIsnull(Pageable pageable);

	public Page<Cliente> findAllByVendedor(String vendedor, Pageable pageable);

	public Page<Cliente> findAllByVendedorAndCuit(String cuil, String vendedor, Pageable pageable);

	public Page<Cliente> findAllByCuitAndVendedorIsNull(String cuil, Pageable pageable);


	public void save(Cliente cliente);
	
	public Cliente findOne(Long id);
	
	public void delete(Long id);




	
}
