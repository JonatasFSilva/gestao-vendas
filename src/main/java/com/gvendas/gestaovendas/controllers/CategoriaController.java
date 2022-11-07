package com.gvendas.gestaovendas.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gvendas.gestaovendas.entities.Categoria;
import com.gvendas.gestaovendas.services.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService service;

	@GetMapping
	public List<Categoria> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Categoria>> findById(@PathVariable Long id) {
		Optional<Categoria> categoria = service.findById(id);
		// return SE A categoria ESTA PRESENTE NO RETORNO
		// ... ENTAO RETORNA OK (200)
		// SENAO RETORNA NOTFOUND (404)
		return categoria.isPresent() ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Categoria> save(@RequestBody Categoria categoria) throws URISyntaxException {
		Categoria newCategoria = service.save(categoria);
		return ResponseEntity.created(new URI("/categoria/" + newCategoria.getCodigo())).body(newCategoria);
	}

}
