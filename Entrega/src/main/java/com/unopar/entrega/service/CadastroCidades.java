package com.unopar.entrega.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.unopar.entrega.model.Cidade;
import com.unopar.entrega.repository.Cidades;
import com.unopar.entrega.util.Transactional;

public class CadastroCidades implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private Cidades cidades;

	@Transactional
	public void salvar(Cidade cidade) throws NegocioException {
		if (cidade.getNome() == null || cidade.getNome().isEmpty()) {
			throw new NegocioException("Informe o nome da cidade.");
		}
		if (cidade.getUf() == null) {
			throw new NegocioException("Informe a UF da cidade.");
		}
		this.cidades.guardar(cidade);
	}

	@Transactional
	public void excluir(Cidade cidade) throws NegocioException {
		cidade = this.cidades.porId(cidade.getIdCidade());
		this.cidades.remover(cidade);
	}

}
