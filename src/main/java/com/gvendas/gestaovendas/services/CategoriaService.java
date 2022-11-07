package com.gvendas.gestaovendas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gvendas.gestaovendas.entities.Categoria;
import com.gvendas.gestaovendas.repositories.CategoriaResository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaResository repository;;

	public List<Categoria> findAll() {
		return repository.findAll();
	}

	public Optional<Categoria> findById(Long id) {
		return repository.findById(id);
	}

	public Categoria save(Categoria categoria) {
		return repository.save(categoria);
	}

	public Categoria update(Long id, Categoria categoria) {
		Categoria categoriaSalvar = validarCategoriaExiste(id);
		BeanUtils.copyProperties(categoria, categoriaSalvar, "codigo");
		
		return repository.save(categoriaSalvar);
	}

	private Categoria validarCategoriaExiste(Long id) {
		Optional<Categoria> categoria = findById(id);

		if (categoria.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		return categoria.get();
	}

}
