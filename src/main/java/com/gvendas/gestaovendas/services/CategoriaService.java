package com.gvendas.gestaovendas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

}
