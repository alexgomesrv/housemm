package com.unopar.entrega.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.unopar.entrega.model.Entregador;
import com.unopar.entrega.repository.Entregadores;
import com.unopar.entrega.util.Transactional;

public class CadastroEntregadores implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private Entregadores entregadores;

	@Transactional
	public void salvar(Entregador entregador) throws NegocioException {
		if (entregador.getEmpresaEntrega() == null) {
			throw new NegocioException("Informe a empresa do entregador.");
		}
		if (entregador.getNome() == null || entregador.getNome().isEmpty()) {
			throw new NegocioException("Informe o nome do entregador.");
		}
		if (entregador.getCpf() == null || entregador.getCpf().length() != 11) {
			throw new NegocioException("Informe um CPF válido para o entregador.");
		}
		if (entregador.getRg() == null || entregador.getRg().isEmpty()) {
			throw new NegocioException("Informe o RG do entregador.");
		}
		if (entregador.getCelular() == null || entregador.getCelular().length() != 11) {
			throw new NegocioException("Informe um telefone válido para o entregador.");
		}
		this.entregadores.guardar(entregador);
	}

	@Transactional
	public void excluir(Entregador entregador) throws NegocioException {
		entregador = this.entregadores.porId(entregador.getIdEntregador());
		this.entregadores.remover(entregador);
	}
}
