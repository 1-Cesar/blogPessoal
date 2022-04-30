package org.cesar.blogPessoal.controller;

import java.util.List;

import org.cesar.blogPessoal.model.Postagem;
import org.cesar.blogPessoal.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @date 29/04/2022
 * @author cesar
 * 
 */
//informa ao spring que essa classe trata-se de um controlador
@RestController
@RequestMapping("/postagem")
@CrossOrigin("*")
public class PostagemController {

	//como trata-se de uma interface, não conseguimos instancia-la. Sendo assim, deixamos a responsabilidade para o Spring
	//injeção de dependência
	@Autowired
	private PostagemRepository repository;
	
	//ResponseEntity -> representar toda a resposta HTTP. Posso controlar qualquer coisa que aconteça: código de status, cabeçalho, corpo.
	@GetMapping
	public ResponseEntity<List<Postagem>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> GetById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	//passagem através do corpo da requisição
	//end-point para inserção
	@PostMapping
	public ResponseEntity<Postagem> post(@RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}
	
	//end-point de atualização. Informe o id e a caracteristica que deseja alterar
	@PutMapping
	public ResponseEntity<Postagem> put(@RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
	}
	
	//delete através do id
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
}
