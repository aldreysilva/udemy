package br.com.aas.projetochamado.models.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.com.aas.projetochamado.models.entities.Produto;

public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Integer>{
	
	
	public Iterable<Produto> findByNomeContainingIgnoreCase(String parteNome);
	
	@Query("SELECT p FROM Produto p WHERE p.nome LIKE %:nome%")
	public Iterable<Produto> searchByNomeLike(@Param("nome") String nome);

}
