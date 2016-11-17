package com.unopar.entrega.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.unopar.entrega.model.Unidade;

public class Unidades implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public Unidades(EntityManager manager) {
		this.manager = manager;
	}
	
	public Unidade porId(int id) {
		return manager.find(Unidade.class, id);
	}
	
	public Unidade guardar(Unidade unidade) {
		return manager.merge(unidade);
	}
	
	public void adicionar(Unidade unidade) {
		this.manager.persist(unidade);
	}
	
	public void remover(Unidade unidade) {
		this.manager.remove(unidade);
	}
	
	public List<Unidade> buscar(String descricao) {
		TypedQuery<Unidade> query = manager.createQuery("FROM Unidade WHERE sigla LIKE '" + descricao + "%' ORDER BY sigla ASC", Unidade.class);
		return query.getResultList();
	}
	
}
