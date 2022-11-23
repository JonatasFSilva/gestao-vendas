package com.gvendas.gestaovendas.dto.cliente;

import com.gvendas.gestaovendas.entities.Cliente;
import com.gvendas.gestaovendas.entities.Endereco;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@ApiModel("Cliente Requisição DTO")
public class ClienteRequestDTO {

	@ApiModelProperty(value = "Nome")
	private String nome;

	@ApiModelProperty(value = "Telefone")
	private String telefone;

	@ApiModelProperty(value = "Ativo")
	private Boolean ativo;

	@ApiModelProperty(value = "Endereço")
	private EnderecoRequestDTO endereco;

	public Cliente convertEntity() {
		
		Endereco enderecoSave = new Endereco(endereco.getLogradouro(), endereco.getNumero(), endereco.getComplemento(),
				endereco.getBairro(), endereco.getCep(), endereco.getCidade(), endereco.getEstado());
		
		return new Cliente(nome, telefone, ativo, enderecoSave);
	}

}
