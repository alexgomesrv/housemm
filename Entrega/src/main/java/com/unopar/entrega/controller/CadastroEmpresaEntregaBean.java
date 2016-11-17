package com.unopar.entrega.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.unopar.entrega.model.EmpresaEntrega;
import com.unopar.entrega.model.Endereco;
import com.unopar.entrega.repository.EmpresaEntregas;
import com.unopar.entrega.repository.Enderecos;
import com.unopar.entrega.service.CadastroEmpresaEntregas;
import com.unopar.entrega.service.NegocioException;

@Named
@ViewScoped
public class CadastroEmpresaEntregaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroEmpresaEntregas cadastro;

	@Inject
	private EmpresaEntregas empresaEntregasRepository;

	private EmpresaEntrega empresaEntregaSelecionada;
	private EmpresaEntrega empresaEntrega = new EmpresaEntrega();

	private List<EmpresaEntrega> empresaEntregas;

	@Inject
	private Enderecos enderecos;
	private List<Endereco> listaEnderecos;

	public void consultar() {
		this.empresaEntregas = empresaEntregasRepository.buscar("");
	}

	public void prepararCadastro() {
		this.listaEnderecos = enderecos.buscar("");
		if (this.empresaEntrega == null) {
			this.empresaEntrega = new EmpresaEntrega();
		}
		this.consultar();
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.cadastro.salvar(this.empresaEntrega);
			this.empresaEntrega = new EmpresaEntrega();
			this.consultar();
		} catch (NegocioException e) {
			FacesMessage message = new FacesMessage(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, message);
		}
	}

	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.cadastro.excluir(this.empresaEntregaSelecionada);
			this.consultar();
			context.addMessage(null, new FacesMessage("Empresa de Entrega excluída com sucesso!"));
		} catch (com.unopar.entrega.service.NegocioException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public List<Endereco> getListaEnderecos() {
		return this.listaEnderecos;
	}

	public List<EmpresaEntrega> getEmpresaEntregas() {
		return empresaEntregas;
	}

	public EmpresaEntrega getEmpresaEntrega() {
		return empresaEntrega;
	}

	public void setEmpresaEntrega(EmpresaEntrega empresaEntrega) {
		this.empresaEntrega = empresaEntrega;
	}

	public EmpresaEntrega getEmpresaEntregaSelecionada() {
		return empresaEntregaSelecionada;
	}

	public void setEmpresaEntregaSelecionada(EmpresaEntrega empresaEntregaSelecionada) {
		this.empresaEntregaSelecionada = empresaEntregaSelecionada;
	}
}
