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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "produto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(name = "descricao", length = 100, nullable = false)
	@NotBlank(message = "Descrição")
	@Length(min = 3, max = 100, message = "Descrição")
	private String descricao;

	@Column(name = "quantidade", nullable = false)
	@NotNull(message = "Quantidade")
	private Integer quantidade;

	@Column(name = "preco_custo", length = 10, nullable = false)
	@NotNull(message = "Preço Custo")
	private BigDecimal precoCusto;

	@Column(name = "preco_venda", length = 10, nullable = false)
	@NotNull(message = "Preço Venda")
	private BigDecimal precoVenda;

	@Length(max = 500, message = "Observação")
	@Column(name = "observacao", length = 500)
	private String observacao;

	@NotNull(message = "Codigo Categoria")
	@ManyToOne
	@JoinColumn(name = "codigo_categoria", referencedColumnName = "codigo")
	private Categoria categoria;

}
