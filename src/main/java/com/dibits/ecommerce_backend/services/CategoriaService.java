package com.dibits.ecommerce_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dibits.ecommerce_backend.domain.Categoria;
import com.dibits.ecommerce_backend.repositories.CategoriaRepository;
import com.dibits.ecommerce_backend.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
public Categoria find(Integer id) {
		
		Categoria obj = repo.findById(id).orElse(null);
		if (obj == null) {
			
			throw new ObjectNotFoundException("Objeto inexistente! Id: "+id+
				", Pacote: "+ Categoria.class.getName());
		}
		return obj;
	}
}
