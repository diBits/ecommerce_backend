package com.dibits.ecommerce_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dibits.ecommerce_backend.domain.Pedido;
import com.dibits.ecommerce_backend.repositories.PedidoRepository;
import com.dibits.ecommerce_backend.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
public Pedido find(Integer id) {
		
		Pedido obj = repo.findById(id).orElse(null);
		if (obj == null) {
			
			throw new ObjectNotFoundException("Objeto inexistente! Id: "+id+
				", Pacote: "+ Pedido.class.getName());
		}
		return obj;
	}
}
