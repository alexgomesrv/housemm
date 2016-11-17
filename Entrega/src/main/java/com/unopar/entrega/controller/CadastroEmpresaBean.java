package com.unopar.entrega.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.unopar.entrega.model.Empresa;
import com.unopar.entrega.model.Endereco;
import com.unopar.entrega.repository.Empresas;
import com.unopar.entrega.repository.Enderecos;
import com.unopar.entrega.service.CadastroEmpresas;
import com.unopar.entrega.service.NegocioException;

@Named
@ViewScoped
public class CadastroEmpresaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroEmpresas cadastro;

	@Inject
	private Empresas empresasRepository;

	private Empresa empresaSelecionada;
	private Empresa empresa = new Empresa();

	private List<Empresa> empresas;

	public void consultar() {
		this.empresas = empresasRepository.buscar("");
	}

	public void prepararCadastro() {
		if (this.empresa == null) {
			this.empresa = new Empresa();
		}
		this.consultar();
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.cadastro.salvar(this.empresa);
			this.empresa = new Empresa();
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
			this.cadastro.excluir(this.empresaSelecionada);
			this.consultar();
			context.addMessage(null, new FacesMessage("Empresa excluída com sucesso!"));
		} catch (com.unopar.entrega.service.NegocioException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Empresa getEmpresaSelecionada() {
		return empresaSelecionada;
	}

	public void setEmpresaSelecionada(Empresa empresaSelecionada) {
		this.empresaSelecionada = empresaSelecionada;
	}
}
