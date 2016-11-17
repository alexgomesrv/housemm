package com.unopar.entrega.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.unopar.entrega.model.Cliente;
import com.unopar.entrega.repository.Clientes;
import com.unopar.entrega.util.Transactional;

public class CadastroClientes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private Clientes clientes;

	@Transactional
	public Cliente salvar(Cliente cliente) throws NegocioException {
		if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
			throw new NegocioException("Informe o nome do cliente.");
		}
		if (cliente.getTelefone() == null || cliente.getTelefone().isEmpty()) {
			throw new NegocioException("Informe o telefone do cliente.");
		}
		if (cliente.getEndereco() == null) {
			throw new NegocioException("Informe o endereço do cliente.");
		}
		return this.clientes.guardar(cliente);
	}

	@Transactional
	public void excluir(Cliente cliente) throws NegocioException {
		cliente = this.clientes.porId(cliente.getIdCliente());
		this.clientes.remover(cliente);
	}
}
