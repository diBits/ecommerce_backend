package com.dibits.ecommerce_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dibits.ecommerce_backend.domain.Cliente;
import com.dibits.ecommerce_backend.repositories.ClienteRepository;
import com.dibits.ecommerce_backend.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente find(Integer id) {

		Cliente obj = repo.findById(id).orElse(null);
		if (obj == null) {

			throw new ObjectNotFoundException("Objeto inexistente! Id: " + id + ", Pacote: " + Cliente.class.getName());
		}
		return obj;
	}
}
