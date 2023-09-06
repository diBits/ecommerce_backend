package labs.dibits.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import labs.dibits.ecommerce.domain.Cliente;
import labs.dibits.ecommerce.repositories.ClienteRepository;
import labs.dibits.ecommerce.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		
		Cliente obj = repo.findById(id).orElse(null);
		if (obj == null) {
			
			throw new ObjectNotFoundException("Objeto inexistente! Id: "+id+
				", Pacote: "+ Cliente.class.getName());
		}
		return obj;
	}
	
}
