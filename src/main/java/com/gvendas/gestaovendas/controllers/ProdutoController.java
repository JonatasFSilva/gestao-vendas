package com.gvendas.gestaovendas.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gvendas.gestaovendas.entities.Produto;
import com.gvendas.gestaovendas.services.ProdutoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Produto")
@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;

	@ApiOperation(value = "Lista todos os Produtos")
	@GetMapping
	public List<Produto> findAll() {
		return produtoService.findAll();
	}

	@ApiOperation(value = "Lista o Produto pelo ID")
	@GetMapping("{/id}")
	public ResponseEntity<Optional<Produto>> findById(@PathVariable Long id) {
		Optional<Produto> produto = produtoService.findById(id);
		return produto.isPresent() ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build();
	}

}
