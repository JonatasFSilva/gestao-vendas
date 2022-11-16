package com.gvendas.gestaovendas.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gvendas.gestaovendas.entities.Produto;
import com.gvendas.gestaovendas.services.ProdutoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Produto")
@RestController
@RequestMapping("/categoria/{codigoCategoria}/produto")
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;

	@ApiOperation(value = "Lista todos os Produtos por Categoria", nickname = "findAll")
	@GetMapping
	public List<Produto> findAll(@PathVariable Long codigoCategoria) {
		return produtoService.findAll(codigoCategoria);
	}

	@ApiOperation(value = "Lista o Produto por Codigo", nickname = "findById")
	@GetMapping("/{codigo}")
	public ResponseEntity<Optional<Produto>> findById(@PathVariable Long codigoCategoria, @PathVariable Long codigo) {
		Optional<Produto> produto = produtoService.buscarPorCodigo(codigoCategoria, codigo);
		return produto.isPresent() ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build();
	}

	@ApiOperation(value = "Salva um Produto", nickname = "saveProduct")
	@PostMapping
	public ResponseEntity<Produto> save(@Valid @RequestBody Produto produto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.save(produto));
	}

}