package com.unopar.entrega.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.unopar.entrega.model.ProdutoPedido;

public class ProdutoPedidos implements Serializable {
	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	@Inject
	public ProdutoPedidos(EntityManager manager) {
		this.manager = manager;
	}

	public ProdutoPedido porId(int id) {
		return manager.find(ProdutoPedido.class, id);
	}

	public ProdutoPedido guardar(ProdutoPedido produtoPedido) {
		return manager.merge(produtoPedido);
	}

	public void adicionar(ProdutoPedido produtoPedido) {
		this.manager.persist(produtoPedido);
	}

	public void remover(ProdutoPedido produtoPedido) {
		this.manager.remove(produtoPedido);
	}

	public List<ProdutoPedido> buscar(String nome) {
		TypedQuery<ProdutoPedido> query = manager.createQuery("FROM ProdutoPedido ORDER BY idProdutoPedido ASC",
				ProdutoPedido.class);
		return query.getResultList();
	}
	
	public List<ProdutoPedido> buscarPorPedido(int idPedido) {
		TypedQuery<ProdutoPedido> query = manager.createQuery("SELECT pp FROM ProdutoPedido pp JOIN pp.pedido AS p WHERE p = '" + idPedido + "' ORDER BY idProdutoPedido ASC",
				ProdutoPedido.class);
		return query.getResultList();
	}
}
