package com.gvendas.gestaovendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gvendas.gestaovendas.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
