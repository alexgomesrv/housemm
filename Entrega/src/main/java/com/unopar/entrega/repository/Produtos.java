package com.unopar.entrega.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.unopar.entrega.model.Produto;

public class Produtos implements Serializable {
	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	@Inject
	public Produtos(EntityManager manager) {
		this.manager = manager;
	}

	public Produto porId(int id) {
		return manager.find(Produto.class, id);
	}

	public Produto guardar(Produto produto) {
		return manager.merge(produto);
	}

	public void adicionar(Produto produto) {
		this.manager.persist(produto);
	}

	public void remover(Produto produto) {
		this.manager.remove(produto);
	}

	public List<Produto> buscar(String nome) {
		TypedQuery<Produto> query = manager.createQuery("FROM Produto WHERE nome LIKE '" + nome + "%' ORDER BY nome ASC",
				Produto.class);
		return query.getResultList();
	}
}
