package com.unopar.entrega.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.unopar.entrega.model.Endereco;
import com.unopar.entrega.repository.Enderecos;
import com.unopar.entrega.util.Transactional;

public class CadastroEnderecos implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private Enderecos enderecos;

	@Transactional
	public void salvar(Endereco endereco) throws NegocioException {
		if (endereco.getCidade() == null) {
			throw new NegocioException("Informe a cidade do endereço.");
		}
		if (endereco.getTipoLogradouro() == null) {
			throw new NegocioException("Informe o tipo de logradouro do endereço.");
		}
		if (endereco.getLogradouro() == null || endereco.getLogradouro().isEmpty()) {
			throw new NegocioException("Informe o logradouro do endereço.");
		}
		if (endereco.getBairro() == null || endereco.getBairro().isEmpty()) {
			throw new NegocioException("Informe o bairro do endereço.");
		}
		this.enderecos.guardar(endereco);
	}

	@Transactional
	public void excluir(Endereco endereco) throws NegocioException {
		endereco = this.enderecos.porId(endereco.getIdEndereco());
		this.enderecos.remover(endereco);
	}

}
