package com.gvendas.gestaovendas.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.gvendas.gestaovendas.entities.Categoria;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel("Categoria Requisição DTO")
public class CetegoriaRequestDTO {

	@ApiModelProperty(value = "Nome")
	@NotBlank(message = "Nome")
	@Length(min = 3, max = 50, message = "Nome")
	private String nome;

	public Categoria convertEntity() {
		return new Categoria(nome);
	}

	public Categoria convertEntity(Long codigo) {
		return new Categoria(codigo, nome);
	}

}
