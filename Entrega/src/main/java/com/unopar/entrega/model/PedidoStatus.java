package com.unopar.entrega.model;

public enum PedidoStatus {
	PENDENTE("Pendente", "Pendente"), TRANSITO("Em trânsito", "Em trânsito"), CANCELADO("Cancelado", "Cancelado"), ENTREGUE("Entregue", "Entregue");

	private String id;
	private String descricao;

	PedidoStatus(String id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public String getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

}
