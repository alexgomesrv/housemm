package com.unopar.entrega.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.unopar.entrega.model.Empresa;

public class Empresas implements Serializable {
	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	@Inject
	public Empresas(EntityManager manager) {
		this.manager = manager;
	}

	public Empresa porId(int id) {
		return manager.find(Empresa.class, id);
	}

	public Empresa guardar(Empresa empresa) {
		return manager.merge(empresa);
	}

	public void adicionar(Empresa empresa) {
		this.manager.persist(empresa);
	}

	public void remover(Empresa empresa) {
		this.manager.remove(empresa);
	}

	public List<Empresa> buscar(String razaoSocial) {
		TypedQuery<Empresa> query = manager.createQuery(
				"FROM Empresa WHERE razaoSocial LIKE '" + razaoSocial + "%' ORDER BY razaoSocial ASC", Empresa.class);
		return query.getResultList();
	}
}
