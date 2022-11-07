package com.gvendas.gestaovendas.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "produto")
@Data
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(name = "descricao", length = 100, nullable = false)
	private String descricao;

	@Column(name = "quantidade", nullable = false)
	private Integer quantidade;

	@Column(name = "preco_custo", length = 10, nullable = false)
	private BigDecimal precoCusto;

	@Column(name = "preco_venda", length = 10, nullable = false)
	private BigDecimal precoVenda;

	@Column(name = "observacao", length = 500)
	private String observacao;

	@ManyToOne
	@JoinColumn(name= "codigo_categoria", referencedColumnName = "codigo")
	private Categoria categoria;

}
