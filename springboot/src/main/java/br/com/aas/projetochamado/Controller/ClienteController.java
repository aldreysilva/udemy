package br.com.aas.projetochamado.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aas.projetochamado.models.entities.Cliente;

@RestController
public class ClienteController {

	@GetMapping(path = "/clientes/qualquer")
	public Cliente obterCliente() {
		return new Cliente(28, "Pedro", "049.330.159-37");
	}
}
