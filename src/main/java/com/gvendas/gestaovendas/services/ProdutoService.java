package com.gvendas.gestaovendas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

	public Produto save(Long codigoCategoria, Produto produto) {
		validarCategoruaDoProdutoExiste(codigoCategoria);
		validarProdutoDuplicado(produto);
		return produtoRepository.save(produto);
	}

	public Produto update(Long codigoCategoria, Long codigoProduto, Produto produto) {
		Produto produtoAtualizar = validarProdutoExiste(codigoProduto, codigoCategoria);
		validarCategoruaDoProdutoExiste(codigoCategoria);
		validarProdutoDuplicado(produto);
		BeanUtils.copyProperties(produto, produtoAtualizar, "codigo");

		return produtoRepository.save(produtoAtualizar);
	}

	public void delete(Long codigoCategoria, Long codigoProduto) {
		Produto produto = validarProdutoExiste(codigoProduto, codigoCategoria);
		produtoRepository.delete(produto);
	}

	private Produto validarProdutoExiste(Long codigoProduto, Long codigoCategoria) {
		Optional<Produto> produto = buscarPorCodigo(codigoProduto, codigoCategoria);

		if (produto.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		return produto.get();
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
		Optional<Produto> produtoPorDescricao = produtoRepository
				.findByCategoriaCodigoAndDescricao(produto.getCategoria().getCodigo(), produto.getDescricao());

		if (produtoPorDescricao.isPresent() && produtoPorDescricao.get().getCodigo() != produto.getCodigo()) {
			throw new RuleBusinessException(String.format("A produto %s jã esta cadastrado", produto.getDescricao()));

		}
	}

}
