package com.unopar.entrega.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.unopar.entrega.model.Endereco;


public class Enderecos implements Serializable {
	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	@Inject
	public Enderecos(EntityManager manager) {
		this.manager = manager;
	}

	public Endereco porId(int id) {
		return manager.find(Endereco.class, id);
	}

	public Endereco guardar(Endereco endereco) {
		return manager.merge(endereco);
	}

	public void adicionar(Endereco endereco) {
		this.manager.persist(endereco);
	}

	public void remover(Endereco endereco) {
		this.manager.remove(endereco);
	}

	public List<Endereco> buscar(String logradouro) {
		TypedQuery<Endereco> query = manager.createQuery("FROM Endereco WHERE logradouro LIKE '" + logradouro + "%' ORDER BY CONCAT(tipoLogradouro, logradouro) ASC",
				Endereco.class);
		return query.getResultList();
	}
}
