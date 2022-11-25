package com.gvendas.gestaovendas.dto.cliente;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@ApiModel("Endereço Requisição DTO")
public class EnderecoRequestDTO {

	@ApiModelProperty(value = "Logradouro")
	@NotBlank(message = "Logradouro")
	@Length(min = 3, max = 30, message = "Logradouro")
	private String logradouro;

	@ApiModelProperty(value = "Número")
	@NotNull(message = "Numero")
	private Integer numero;

	@ApiModelProperty(value = "Complemento")
	@Length(max = 30, message = "Complemento")
	private String complemento;

	@ApiModelProperty(value = "Bairro")
	@NotBlank(message = "Bairro")
	@Length(min = 3, max = 30, message = "Bairro")
	private String bairro;

	@ApiModelProperty(value = "Cep")
	@NotBlank(message = "Cep")
	@Pattern(regexp = "[\\d]{5}-[\\d]{3}", message = "Cep")
	private String cep;

	@ApiModelProperty(value = "Cidade")
	@NotBlank(message = "Cidade")
	@Length(min = 3, max = 30, message = "Cidade")
	private String cidade;

	@ApiModelProperty(value = "Estado")
	@NotBlank(message = "Estado")
	@Length(min = 3, max = 30, message = "Estado")
	private String estado;

}
