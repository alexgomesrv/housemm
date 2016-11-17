package com.unopar.entrega.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.unopar.entrega.model.Empresa;
import com.unopar.entrega.repository.Empresas;
import com.unopar.entrega.util.Transactional;

public class CadastroEmpresas implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private Empresas empresas;

	@Transactional
	public void salvar(Empresa empresa) throws NegocioException {
		if (empresa.getRazaoSocial() == null || empresa.getRazaoSocial().isEmpty()) {
			throw new NegocioException("Informe a razão social da empresa.");
		}
		if (empresa.getNomeFantasia() == null || empresa.getNomeFantasia().isEmpty()) {
			throw new NegocioException("Informe o nome fantasia empresa.");
		}
		if (empresa.getCnpj() == null || empresa.getCnpj().length() != 14) {
			throw new NegocioException("Informe um CNPJ válido para a empresa.");
		}
		if (empresa.getTaxaEntrega() == null) {
			throw new NegocioException("Informe a taxa de entrega da empresa.");
		}
		if (empresa.getTelefone() == null
				|| (empresa.getTelefone().length() != 10 && empresa.getTelefone().length() != 11)) {
			throw new NegocioException("Informe um telefone válido para a empresa.");
		}
		this.empresas.guardar(empresa);
	}

	@Transactional
	public void excluir(Empresa empresa) throws NegocioException {
		empresa = this.empresas.porId(empresa.getIdEmpresa());
		this.empresas.remover(empresa);
	}
}
