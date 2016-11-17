package com.unopar.entrega.repository;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.unopar.entrega.model.Cliente;
import com.unopar.entrega.model.Pedido;

public class Pedidos implements Serializable {
	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	@Inject
	public Pedidos(EntityManager manager) {
		this.manager = manager;
	}

	public Pedido porId(int id) {
		return manager.find(Pedido.class, id);
	}

	public Pedido guardar(Pedido pedido) {
		return manager.merge(pedido);
	}

	public void adicionar(Pedido pedido) {
		this.manager.persist(pedido);
	}

	public void remover(Pedido pedido) {
		this.manager.remove(pedido);
	}

	public List<Pedido> buscar(String nome) {
		
		TypedQuery<Pedido> query = manager.createQuery("FROM Pedido p "
				+ (nome != null && !nome.isEmpty()
				? "WHERE p.status = '" + nome + "' " 
						: "") 
				+ "ORDER BY data DESC",
				Pedido.class);
		return query.getResultList();
	}
	
	public List<Pedido> consultar(String status, Date dataInicial, Date dataFinal) {
		TypedQuery<Pedido> query = manager.createQuery("FROM Pedido p "
				+ "WHERE DATE(p.data) BETWEEN DATE('" + new SimpleDateFormat("yyyy-MM-dd").format(dataInicial) + "') AND DATE('" + new SimpleDateFormat("yyyy-MM-dd").format(dataFinal) + "') "
				+ (status != null && !status.isEmpty()
				? "AND p.status = '" + status+ "' " 
						: "") 
				+ "ORDER BY data DESC",
				Pedido.class);
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
