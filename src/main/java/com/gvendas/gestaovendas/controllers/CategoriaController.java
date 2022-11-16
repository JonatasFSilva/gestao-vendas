package com.gvendas.gestaovendas.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gvendas.gestaovendas.entities.Categoria;
import com.gvendas.gestaovendas.services.CategoriaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Categoria")
@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService service;

	@ApiOperation(value = "Lista todas as Categorias", nickname = "findAll")
	@GetMapping
	public List<Categoria> findAll() {
		return service.findAll();
	}

	@ApiOperation(value = "Lista uma Categoria pelo ID", nickname = "findById")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Categoria>> findById(@PathVariable Long id) {
		Optional<Categoria> categoria = service.findById(id);
		// return SE A categoria ESTA PRESENTE NO RETORNO
		// ... ENTAO RETORNA OK (200)
		// SENAO RETORNA NOTFOUND (404)
		return categoria.isPresent() ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
	}

	@ApiOperation(value = "Salva uma nova Categoria", nickname = "save category")
	@PostMapping
	public ResponseEntity<Categoria> save(@Valid @RequestBody Categoria categoria) throws URISyntaxException {
		Categoria newCategoria = service.save(categoria);
		return ResponseEntity.created(new URI("/categoria/" + newCategoria.getCodigo())).body(newCategoria);
	}

	@ApiOperation(value = "Atualiza uma Categoria pelo ID", nickname = "updateById")
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> update(@PathVariable Long id, @Valid @RequestBody Categoria categoria) {
		return ResponseEntity.ok(service.update(id, categoria));
	}

	@ApiOperation(value = "Deleta uma Categoria", nickname = "delete")
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

}
