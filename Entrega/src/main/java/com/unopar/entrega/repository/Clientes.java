package com.unopar.entrega.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.unopar.entrega.model.Cliente;

public class Clientes implements Serializable {
	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	@Inject
	public Clientes(EntityManager manager) {
		this.manager = manager;
	}

	public Cliente porId(int id) {
		return manager.find(Cliente.class, id);
	}

	public Cliente guardar(Cliente cliente) {
		return manager.merge(cliente);
	}

	public void adicionar(Cliente cliente) {
		this.manager.persist(cliente);
	}

	public void remover(Cliente cliente) {
		this.manager.remove(cliente);
	}

	public List<Cliente> buscar(String nome) {
		TypedQuery<Cliente> query = manager
				.createQuery("FROM Cliente WHERE nome LIKE '" + nome + "%' ORDER BY nome ASC", Cliente.class);
		return query.getResultList();
	}

	public Cliente buscarPorTelefone(String telefone) {
		TypedQuery<Cliente> query = manager
				.createQuery("FROM Cliente WHERE telefone LIKE '" + telefone + "' ORDER BY nome ASC", Cliente.class);
		List<Cliente> list = query.getResultList();
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
}
