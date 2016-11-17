package com.unopar.entrega.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.unopar.entrega.model.Cidade;


public class Cidades implements Serializable {
	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	@Inject
	public Cidades(EntityManager manager) {
		this.manager = manager;
	}

	public Cidade porId(int id) {
		return manager.find(Cidade.class, id);
	}

	public Cidade guardar(Cidade cidade) {
		return manager.merge(cidade);
	}

	public void adicionar(Cidade cidade) {
		this.manager.persist(cidade);
	}

	public void remover(Cidade cidade) {
		this.manager.remove(cidade);
	}

	public List<Cidade> buscar(String nome) {
		TypedQuery<Cidade> query = manager
				.createQuery("FROM Cidade WHERE nome LIKE '" + nome + "%' ORDER BY nome ASC", Cidade.class);
		return query.getResultList();
	}
}
