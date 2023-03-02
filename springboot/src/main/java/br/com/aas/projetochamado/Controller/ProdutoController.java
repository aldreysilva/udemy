package br.com.aas.projetochamado.Controller;


import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.aas.projetochamado.models.entities.Produto;
import br.com.aas.projetochamado.models.repositories.ProdutoRepository;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	public @ResponseBody Produto salvarProduto(@Valid Produto produto) {
		produtoRepository.save(produto);
		return produto;
	}

	// retorna todos os produtos
	@GetMapping
	public Iterable<Produto> obterProdutos() {
		return produtoRepository.findAll();
	}

	@GetMapping(path = "/{id}")
	public Optional<Produto> obterProdutoPorId(@PathVariable int id) {
		return produtoRepository.findById(id);
	}
	// alterar objeto inteiro -> comentado pois esta junto com o metodo
	// salvarProduto
	// @PutMapping
	// public Produto alterarProduto(@Valid Produto produto) {
	// produtoRepository.save(produto);
	// return produto;
	// }
	
	@GetMapping(path="/nome/{parteNome}")
	public Iterable<Produto> obterProdutosPorContainNome(@PathVariable String parteNome){
		return produtoRepository.findByNomeContainingIgnoreCase(parteNome);
	}

	@GetMapping(path="/nome/{nome}")
	public Iterable<Produto> obterProdutosPorNome(@PathVariable String nome){
		return produtoRepository.searchByNomeLike(nome);
	}
	@DeleteMapping(path = "/{id}")
	public void excluirProduto(@PathVariable int id) {
		produtoRepository.deleteById(id);
	}
	@GetMapping(path ="/pagina/{numeroPagina}")
	public Iterable<Produto> obterProdutoPorPagina(@PathVariable int numeroPagina, @PathVariable int qtdPagina){
		if(qtdPagina >=5 ) {
			qtdPagina = 5;
		}
		
		Pageable page = PageRequest.of(numeroPagina, qtdPagina);
		return produtoRepository.findAll(page);
	}
	
	
	

}
