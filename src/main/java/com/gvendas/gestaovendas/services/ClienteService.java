package com.gvendas.gestaovendas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gvendas.gestaovendas.entities.Cliente;
import com.gvendas.gestaovendas.exceptions.RuleBusinessException;
import com.gvendas.gestaovendas.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Optional<Cliente> findById(Long codigo) {
		return clienteRepository.findById(codigo);
	}

	public Cliente save(Cliente cliente) {
		validarClienteDuplicade(cliente);
		return clienteRepository.save(cliente);
	}

	public Cliente update(Long codigo, Cliente cliente) {
		Cliente clienteUpdate = validarClienteExite(codigo);
		validarClienteDuplicade(cliente);
		BeanUtils.copyProperties(cliente, clienteUpdate, "codigo");
		return clienteRepository.save(clienteUpdate);

	}

	private Cliente validarClienteExite(Long codigo) {
		Optional<Cliente> cliente = findById(codigo);

		if (cliente.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		return cliente.get();
	}

	private void validarClienteDuplicade(Cliente cliente) {
		Cliente clientePorNome = clienteRepository.findByNome(cliente.getNome());

		if (clientePorNome != null && clientePorNome.getCodigo() != cliente.getCodigo()) {
			throw new RuleBusinessException(
					String.format("O cliente %s ja esta cadastrado", cliente.getNome().toUpperCase()));
		}

	}

}
