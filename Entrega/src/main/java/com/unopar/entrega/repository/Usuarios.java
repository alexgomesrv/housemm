package com.unopar.entrega.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.unopar.entrega.model.Usuario;

public class Usuarios implements Serializable {
	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	@Inject
	public Usuarios(EntityManager manager) {
		this.manager = manager;
	}

	public Usuario porId(int id) {
		return manager.find(Usuario.class, id);
	}

	public Usuario guardar(Usuario usuario) {
		return manager.merge(usuario);
	}

	public void adicionar(Usuario usuario) {
		this.manager.persist(usuario);
	}

	public void remover(Usuario usuario) {
		this.manager.remove(usuario);
	}

	public List<Usuario> buscar(String nome) {
		TypedQuery<Usuario> query = manager
				.createQuery("FROM Usuario WHERE nome LIKE '" + nome + "%' ORDER BY nome ASC", Usuario.class);
		return query.getResultList();
	}
}
