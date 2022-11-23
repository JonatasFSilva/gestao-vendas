package com.gvendas.gestaovendas.dto.cliente;

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
	private String logradouro;

	@ApiModelProperty(value = "Número")
	private Integer numero;

	@ApiModelProperty(value = "Complemento")
	private String complemento;

	@ApiModelProperty(value = "Bairro")
	private String bairro;

	@ApiModelProperty(value = "Cep")
	private String cep;

	@ApiModelProperty(value = "Cidade")
	private String cidade;

	@ApiModelProperty(value = "Estado")
	private String estado;
	

}
