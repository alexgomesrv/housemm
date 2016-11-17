package com.unopar.entrega.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.unopar.entrega.model.EmpresaEntrega;

public class EmpresaEntregas implements Serializable {
	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	@Inject
	public EmpresaEntregas(EntityManager manager) {
		this.manager = manager;
	}

	public EmpresaEntrega porId(int id) {
		return manager.find(EmpresaEntrega.class, id);
	}

	public EmpresaEntrega guardar(EmpresaEntrega empresaEntrega) {
		return manager.merge(empresaEntrega);
	}

	public void adicionar(EmpresaEntrega empresaEntrega) {
		this.manager.persist(empresaEntrega);
	}

	public void remover(EmpresaEntrega empresaEntrega) {
		this.manager.remove(empresaEntrega);
	}

	public List<EmpresaEntrega> buscar(String nome) {
		TypedQuery<EmpresaEntrega> query = manager
				.createQuery("FROM EmpresaEntrega WHERE nome LIKE '" + nome + "%' ORDER BY nome ASC", EmpresaEntrega.class);
		return query.getResultList();
	}
}
