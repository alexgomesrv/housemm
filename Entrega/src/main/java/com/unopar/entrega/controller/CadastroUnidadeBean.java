package com.unopar.entrega.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.JOptionPane;

import com.unopar.entrega.model.Unidade;
import com.unopar.entrega.repository.Unidades;
import com.unopar.entrega.service.CadastroUnidades;
import com.unopar.entrega.service.NegocioException;

@Named
@ViewScoped
public class CadastroUnidadeBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroUnidades cadastro;

	@Inject
	private Unidades unidadesRepository;

	private Unidade unidadeSelecionada;
	private Unidade unidade = new Unidade();

	private List<Unidade> unidades;

	public void consultar() {
		this.unidades = unidadesRepository.buscar("");
	}

	public void prepararCadastro() {
		if (this.unidade == null) {
			this.unidade = new Unidade();
		}
		this.consultar();
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.cadastro.salvar(this.unidade);
			this.unidade = new Unidade();
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
			this.cadastro.excluir(this.unidadeSelecionada);
			this.consultar();
			context.addMessage(null, new FacesMessage("Unidade excluída com sucesso!"));
		} catch (com.unopar.entrega.service.NegocioException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public List<Unidade> getUnidades() {
		return unidades;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Unidade getUnidadeSelecionada() {
		return unidadeSelecionada;
	}

	public void setUnidadeSelecionada(Unidade unidadeSelecionada) {
		this.unidadeSelecionada = unidadeSelecionada;
	}

}
