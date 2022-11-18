package com.gvendas.gestaovendas.dto.produto;

import java.math.BigDecimal;

import com.gvendas.gestaovendas.dto.categoria.CategoriaResponseDTO;
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
@ApiModel("Produto Retorno DTO")
public class ProdutoResponseDTO {

	@ApiModelProperty(value = "Código")
	private Long codigo;
	@ApiModelProperty(value = "Descrição")
	private String descricao;
	@ApiModelProperty(value = "Quantidade")
	private Integer quantidade;
	@ApiModelProperty(value = "Preço Custo")
	private BigDecimal precoCusto;
	@ApiModelProperty(value = "Preço Venda")
	private BigDecimal precoVenda;
	@ApiModelProperty(value = "Observação")
	private String observacao;
	@ApiModelProperty(value = "Categoria")
	private CategoriaResponseDTO categoriaResponseDTO;

	// CONERSOR DE ENTITY PARA DTO
	public static ProdutoResponseDTO convertProdutoDTO(Produto produto) {

		return new ProdutoResponseDTO(produto.getCodigo(), produto.getDescricao(), produto.getQuantidade(),
				produto.getPrecoCusto(), produto.getPrecoVenda(), produto.getObservacao(),
				CategoriaResponseDTO.convertCategoriaDTO(produto.getCategoria()));

	}

}
