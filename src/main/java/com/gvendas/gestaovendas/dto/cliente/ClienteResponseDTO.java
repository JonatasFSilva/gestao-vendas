package com.gvendas.gestaovendas.dto.cliente;

import com.gvendas.gestaovendas.entities.Cliente;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@ApiModel("Cliente Retorno DTO")
public class ClienteResponseDTO {

	@ApiModelProperty(value = "Código")
	private Long codigo;

	@ApiModelProperty(value = "Nome")
	private String nome;

	@ApiModelProperty(value = "Telefone")
	private String telefone;

	@ApiModelProperty(value = "Ativo")
	private Boolean ativo;

	@ApiModelProperty(value = "Endereço")
	private EnderecoResponseDTO enderecoResponseDTO;

	public static ClienteResponseDTO convertClienteDTO(Cliente cliente) {

		EnderecoResponseDTO endereco = new EnderecoResponseDTO(cliente.getEndereco().getLogradouro(),
				cliente.getEndereco().getNumero(), cliente.getEndereco().getComplemento(),
				cliente.getEndereco().getBairro(), cliente.getEndereco().getCep(), cliente.getEndereco().getCidade(),
				cliente.getEndereco().getEstado());

		return new ClienteResponseDTO(cliente.getCodigo(), cliente.getNome(), cliente.getTelefone(), cliente.getAtivo(),
				endereco);
	}

}
