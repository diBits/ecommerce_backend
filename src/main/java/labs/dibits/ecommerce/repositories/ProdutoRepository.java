package labs.dibits.ecommerce.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import labs.dibits.ecommerce.domain.Categoria;
import labs.dibits.ecommerce.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE CONCAT('%', :nome, '%') AND cat IN :categorias\r\n"
			+ "")
			Page<Produto> findDistinctByNomeContainingAndCategoriasIn(
			@Param("nome") String nome,
			@Param("categorias") List<Categoria> categorias,
			Pageable pageRequest);
	
	//findDistinctByNomeContainingAndCategoriasIn (colocando nome do metodo conforme descrito, faz com que o Spring execute a query descrita)
	
	

}
