package com.unopar.entrega.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.unopar.entrega.model.Unidade;
import com.unopar.entrega.repository.Unidades;
import com.unopar.entrega.util.Transactional;

public class CadastroUnidades implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private Unidades unidades;

	@Transactional
	public void salvar(Unidade unidade) throws NegocioException {
		if (unidade.getSigla() == null || unidade.getSigla().isEmpty()) {
			throw new NegocioException("Informe uma sigla para a unidade.");
		}
		this.unidades.guardar(unidade);
	}

	@Transactional
	public void excluir(Unidade unidade) throws NegocioException {
		System.out.println(this.unidades);
		System.out.println(unidade);
		unidade = this.unidades.porId(unidade.getIdUnidade());
		this.unidades.remover(unidade);
	}
}
