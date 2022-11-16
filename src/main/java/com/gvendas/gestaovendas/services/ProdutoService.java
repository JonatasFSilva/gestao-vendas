package com.gvendas.gestaovendas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gvendas.gestaovendas.entities.Produto;
import com.gvendas.gestaovendas.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;

	public List<Produto> findAll(Long codigotCategoria) {
		return produtoRepository.findByCategoriaCodigo(codigotCategoria);
	}

	public Optional<Produto> buscarPorCodigo(Long codigoCategoria, Long codigo) {
		return produtoRepository.buscarPorCodigo(codigoCategoria, codigo);
	}
	
	public Produto save(Produto produto) {
		return produtoRepository.save(produto);
	}

}
