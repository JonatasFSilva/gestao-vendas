package com.gvendas.gestaovendas.dto.categoria;

import com.gvendas.gestaovendas.entities.Categoria;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@ApiModel("Categoria Retorno DTO")
public class CategoriaResponseDTO {

	@ApiModelProperty(value = "Código")
	private Long codigo;

	@ApiModelProperty(value = "Nome")
	private String nome;

	// CONERSOR DE ENTITY PARA DTO
	public static CategoriaResponseDTO convertCategoriaDTO(Categoria categoria) {
		return new CategoriaResponseDTO(categoria.getCodigo(), categoria.getNome());
	}

}
