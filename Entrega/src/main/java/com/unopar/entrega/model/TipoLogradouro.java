package com.unopar.entrega.model;

public enum TipoLogradouro {
	AVENIDA("Avenida"), RUA("Rua");
	private String tipo;

	TipoLogradouro(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
}
