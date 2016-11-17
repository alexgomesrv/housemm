package com.unopar.entrega.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.unopar.entrega.model.EmpresaEntrega;
import com.unopar.entrega.repository.EmpresaEntregas;
import com.unopar.entrega.util.Transactional;

public class CadastroEmpresaEntregas implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private EmpresaEntregas empresaEntregas;

	@Transactional
	public void salvar(EmpresaEntrega empresaEntrega) throws NegocioException {
		if (empresaEntrega.getNome() == null || empresaEntrega.getNome().isEmpty()) {
			throw new NegocioException("Informe o nome da empresa.");
		}
		if (empresaEntrega.getCnpj() == null || empresaEntrega.getCnpj().length() != 14) {
			throw new NegocioException("Informe um CNPJ válido para a empresa.");
		}
		if (empresaEntrega.getTelefone() == null || (empresaEntrega.getTelefone().length() != 10 && empresaEntrega.getTelefone().length() != 11)) {
			throw new NegocioException("Informe o telefone da empresa.");
		}
		if (empresaEntrega.getEndereco() == null) {
			throw new NegocioException("Informe o endereço da empresa.");
		}
		this.empresaEntregas.guardar(empresaEntrega);
	}

	@Transactional
	public void excluir(EmpresaEntrega empresaEntrega) throws NegocioException {
		empresaEntrega = this.empresaEntregas.porId(empresaEntrega.getIdEmpresaEntrega());
		this.empresaEntregas.remover(empresaEntrega);
	}
	
}
