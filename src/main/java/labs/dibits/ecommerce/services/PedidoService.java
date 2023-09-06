package labs.dibits.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import labs.dibits.ecommerce.domain.Pedido;
import labs.dibits.ecommerce.repositories.PedidoRepository;
import labs.dibits.ecommerce.services.exceptions.ObjectNotFoundException;

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
