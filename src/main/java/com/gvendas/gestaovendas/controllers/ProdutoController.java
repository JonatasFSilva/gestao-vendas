package com.gvendas.gestaovendas.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.gvendas.gestaovendas.dto.produto.ProdutoRequestDTO;
import com.gvendas.gestaovendas.dto.produto.ProdutoResponseDTO;
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

	@ApiOperation(value = "Lista todos os Produtos por Categoria", nickname = "findAllsProductsForCategory")
	@GetMapping
	public List<ProdutoResponseDTO> findAll(@PathVariable Long codigoCategoria) {
		return produtoService.findAll(codigoCategoria).stream().map(ProdutoResponseDTO::convertProdutoDTO)
				.collect(Collectors.toList());
	}

	@ApiOperation(value = "Lista o Produto por Codigo", nickname = "findByProduct")
	@GetMapping("/{codigo}")
	public ResponseEntity<ProdutoResponseDTO> findById(@PathVariable Long codigoCategoria, @PathVariable Long codigo) {
		Optional<Produto> produto = produtoService.buscarPorCodigo(codigoCategoria, codigo);
		return produto.isPresent() ? ResponseEntity.ok(ProdutoResponseDTO.convertProdutoDTO(produto.get()))
				: ResponseEntity.notFound().build();
	}

	@ApiOperation(value = "Salva um Produto", nickname = "saveProduct")
	@PostMapping
	public ResponseEntity<ProdutoResponseDTO> save(@PathVariable Long codigoCategoria,
			@Valid @RequestBody ProdutoRequestDTO produtoRequestDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoResponseDTO.convertProdutoDTO(
				produtoService.save(codigoCategoria, produtoRequestDTO.convertEntity(codigoCategoria))));
	}

	@ApiOperation(value = "Atualiza um Produto", nickname = "updateProduct")
	@PutMapping("/{codigoProduto}")
	public ResponseEntity<ProdutoResponseDTO> update(@PathVariable Long codigoCategoria,
			@PathVariable Long codigoProduto, @Valid @RequestBody ProdutoRequestDTO produtoRequestDTO) {
		Produto updateproduto = produtoService.update(codigoCategoria, codigoProduto,
				produtoRequestDTO.convertEntity(codigoCategoria, codigoProduto));
		return ResponseEntity.ok(ProdutoResponseDTO.convertProdutoDTO(updateproduto));
	}

	@ApiOperation(value = "Deleta um Produto", nickname = "deleteProduct")
	@DeleteMapping("/{codigoProduto}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long codigoCategoria, @PathVariable Long codigoProduto) {
		produtoService.delete(codigoCategoria, codigoProduto);
	}

}
