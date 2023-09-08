package labs.dibits.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import labs.dibits.ecommerce.domain.Categoria;
import labs.dibits.ecommerce.dto.CategoriaDTO;
import labs.dibits.ecommerce.repositories.CategoriaRepository;
import labs.dibits.ecommerce.services.exceptions.DataIntegrityException;
import labs.dibits.ecommerce.services.exceptions.ObjectNotFoundException;

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

	public Categoria insert(Categoria obj) {
		// TODO Auto-generated method stub
		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		// TODO Auto-generated method stub
		find(obj.getId());
		return repo.save(obj);
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não e possível excluir uma categoria com produtos");
		}
		
	}
	public List<Categoria> findAll() {
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
	    PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	/** Metodo auxiliar que instancia uma Categoria a partir de um DTO**/
	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}
	
}
