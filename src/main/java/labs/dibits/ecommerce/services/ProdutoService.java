package labs.dibits.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import labs.dibits.ecommerce.domain.Categoria;
import labs.dibits.ecommerce.domain.Produto;
import labs.dibits.ecommerce.repositories.CategoriaRepository;
import labs.dibits.ecommerce.repositories.ProdutoRepository;
import labs.dibits.ecommerce.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;

	@Autowired
	private CategoriaRepository catRepo;

	public Produto find(Integer id) {

		Produto obj = repo.findById(id).orElse(null);
		if (obj == null) {

			throw new ObjectNotFoundException("Objeto inexistente! Id: " + id + ", Pacote: " + Produto.class.getName());
		}
		return obj;
	}

	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		
		System.out.println("Método page produtoservice foi chamado."); // Adicione este log
	    PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
	    
	    List<Categoria> categorias = catRepo.findAllById(ids);
	    
	    
	    return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
		
		
	}

}
