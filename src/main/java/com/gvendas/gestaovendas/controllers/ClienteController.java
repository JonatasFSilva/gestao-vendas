package com.gvendas.gestaovendas.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gvendas.gestaovendas.dto.cliente.ClienteResponseDTO;
import com.gvendas.gestaovendas.entities.Cliente;
import com.gvendas.gestaovendas.services.ClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Cliente")
@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService service;

	@ApiOperation(value = "Lista todos os Clientes", nickname = "findAllsClient")
	@GetMapping
	public List<ClienteResponseDTO> findAll() {
		return service.findAll().stream().map(client -> ClienteResponseDTO.convertClienteDTO(client))
				.collect(Collectors.toList());
	}

	@ApiOperation(value = "Lista uma Categoria pelo ID", nickname = "findById")
	@GetMapping("/{id}")
	public ResponseEntity<ClienteResponseDTO> findById(@PathVariable Long id) {
		Optional<Cliente> cliente = service.findById(id);
		// return SE A categoria ESTA PRESENTE NO RETORNO
		// ... ENTAO RETORNA OK (200)
		// SENAO RETORNA NOTFOUND (404)
		return cliente.isPresent() ? ResponseEntity.ok(ClienteResponseDTO.convertClienteDTO(cliente.get()))
				: ResponseEntity.notFound().build();
	}
}
