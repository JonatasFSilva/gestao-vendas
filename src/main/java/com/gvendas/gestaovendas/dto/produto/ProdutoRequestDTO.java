package com.gvendas.gestaovendas.dto.produto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.gvendas.gestaovendas.entities.Categoria;
import com.gvendas.gestaovendas.entities.Produto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Produto Requisição DTO")
public class ProdutoRequestDTO {

	@ApiModelProperty(value = "Descrição")
	@NotBlank(message = "Descrição")
	@Length(min = 3, max = 100, message = "Descrição")
	private String descricao;

	@ApiModelProperty(value = "Quantidade")
	@NotNull(message = "Quantidade")
	private Integer quantidade;

	@ApiModelProperty(value = "Preço Custo")
	@NotNull(message = "Preço Custo")
	private BigDecimal precoCusto;

	@ApiModelProperty(value = "Preço Venda")
	@NotNull(message = "Preço Venda")
	private BigDecimal precoVenda;

	@ApiModelProperty(value = "Observação")
	@Length(max = 500, message = "Observação")
	private String observacao;

	public Produto convertEntity(Long codigoCategoria) {
		return new Produto(descricao, quantidade, precoCusto, precoVenda, observacao, new Categoria(codigoCategoria));
	}

	public Produto convertEntity(Long codigoCategoria, Long codigoProduto) {
		return new Produto(codigoProduto, descricao, quantidade, precoCusto, precoVenda, observacao,
				new Categoria(codigoCategoria));
	}

}
