package com.unopar.entrega.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.unopar.entrega.model.Cidade;
import com.unopar.entrega.model.UF;
import com.unopar.entrega.repository.Cidades;
import com.unopar.entrega.service.CadastroCidades;
import com.unopar.entrega.service.NegocioException;

@Named
@ViewScoped
public class CadastroCidadeBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroCidades cadastro;

	@Inject
	private Cidades cidadesRepository;

	private Cidade cidadeSelecionada;
	private Cidade cidade = new Cidade();

	private List<Cidade> cidades;

	public void consultar() {
		this.cidades = cidadesRepository.buscar("");
	}

	public void prepararCadastro() {
		if (this.cidade == null) {
			this.cidade = new Cidade();
		}
		this.consultar();
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.cadastro.salvar(this.cidade);
			this.cidade = new Cidade();
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
			this.cadastro.excluir(this.cidadeSelecionada);
			this.consultar();
			context.addMessage(null, new FacesMessage("Cidade excluída com sucesso!"));
		} catch (com.unopar.entrega.service.NegocioException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public UF[] getListaUF() {
		return UF.values();
	}
	
	public List<Cidade> getCidades() {
		return cidades;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Cidade getCidadeSelecionada() {
		return cidadeSelecionada;
	}

	public void setCidadeSelecionada(Cidade cidadeSelecionada) {
		this.cidadeSelecionada = cidadeSelecionada;
	}
}
