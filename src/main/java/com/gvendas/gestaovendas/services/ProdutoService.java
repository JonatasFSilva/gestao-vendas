package com.gvendas.gestaovendas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gvendas.gestaovendas.entities.Produto;
import com.gvendas.gestaovendas.exceptions.RuleBusinessException;
import com.gvendas.gestaovendas.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaService categoriaService;

	public List<Produto> findAll(Long codigotCategoria) {
		return produtoRepository.findByCategoriaCodigo(codigotCategoria);
	}

	public Optional<Produto> buscarPorCodigo(Long codigoCategoria, Long codigo) {
		return produtoRepository.buscarPorCodigo(codigoCategoria, codigo);
	}

	public Produto save(Produto produto) {
		validarCategoruaDoProdutoExiste(produto.getCategoria().getCodigo());
		validarProdutoDuplicado(produto);
		return produtoRepository.save(produto);
	}

	private void validarCategoruaDoProdutoExiste(Long codigoCategoria) {
		if (codigoCategoria == null) {
			throw new RuleBusinessException("A categoria não pode ser nula.");
		}

		if (categoriaService.findById(codigoCategoria).isEmpty()) {
			throw new RuleBusinessException(
					String.format("A categoria de codigo %s não existe no cadastro", codigoCategoria));
		}
	}

	private void validarProdutoDuplicado(Produto produto) {
		if (produtoRepository
				.findByCategoriaCodigoAndDescricao(produto.getCategoria().getCodigo(), produto.getDescricao())
				.isPresent()) {
			throw new RuleBusinessException(String.format("A produto %s jã esta cadastrado", produto.getDescricao()));

		}
	}

}
