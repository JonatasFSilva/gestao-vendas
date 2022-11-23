package com.gvendas.gestaovendas.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Setter
@Getter
@EqualsAndHashCode
public class Endereco {

	@Column(name = "logradouro")
	private String logradouro;

	@Column(name = "numero")
	private Integer numero;

	@Column(name = "complemento")
	private String complemento;

	@Column(name = "bairro")
	private String bairro;

	@Column(name = "cep")
	private String cep;

	@Column(name = "cidade")
	private String cidade;

	@Column(name = "estado")
	private String estado;

}
