package com.gvendas.gestaovendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gvendas.gestaovendas.entities.Categoria;

public interface CategoriaResository extends JpaRepository<Categoria, Long> {

	Categoria findByNome(String nome);

}
