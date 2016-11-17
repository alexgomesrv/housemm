package com.unopar.entrega.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.unopar.entrega.model.Entregador;

public class Entregadores implements Serializable {
	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	@Inject
	public Entregadores(EntityManager manager) {
		this.manager = manager;
	}

	public Entregador porId(int id) {
		return manager.find(Entregador.class, id);
	}

	public Entregador guardar(Entregador entregador) {
		return manager.merge(entregador);
	}

	public void adicionar(Entregador entregador) {
		this.manager.persist(entregador);
	}

	public void remover(Entregador entregador) {
		this.manager.remove(entregador);
	}

	public List<Entregador> buscar(String nome) {
		TypedQuery<Entregador> query = manager
				.createQuery("FROM Entregador WHERE nome LIKE '" + nome + "%' ORDER BY nome ASC", Entregador.class);
		return query.getResultList();
	}
}
