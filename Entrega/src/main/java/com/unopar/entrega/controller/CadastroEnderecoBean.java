package com.unopar.entrega.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.unopar.entrega.model.Cidade;
import com.unopar.entrega.model.Endereco;
import com.unopar.entrega.model.TipoLogradouro;
import com.unopar.entrega.repository.Cidades;
import com.unopar.entrega.repository.Enderecos;
import com.unopar.entrega.service.CadastroEnderecos;
import com.unopar.entrega.service.NegocioException;

@Named
@ViewScoped
public class CadastroEnderecoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroEnderecos cadastro;

	@Inject
	private Enderecos enderecosRepository;
	
	@Inject
	private Cidades cidades;

	private Endereco enderecoSelecionado;
	private Endereco endereco = new Endereco();

	private List<Cidade> listaCidades;
	private List<Endereco> enderecos;

	public void consultar() {
		this.enderecos = enderecosRepository.buscar("");
	}

	public void prepararCadastro() {
		this.listaCidades = this.cidades.buscar("");
		if (this.endereco == null) {
			this.endereco = new Endereco();
		}
		this.consultar();
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.cadastro.salvar(this.endereco);
			this.endereco = new Endereco();
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
			this.cadastro.excluir(this.enderecoSelecionado);
			this.consultar();
			context.addMessage(null, new FacesMessage("Endereço excluído com sucesso!"));
		} catch (com.unopar.entrega.service.NegocioException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}
	
	public TipoLogradouro[] getListaTipoLogradouro() {
		return TipoLogradouro.values();
	}

	public List<Cidade> getListaCidades() {
		return this.listaCidades;
	}
	
	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Endereco getEnderecoSelecionado() {
		return enderecoSelecionado;
	}

	public void setEnderecoSelecionado(Endereco enderecoSelecionado) {
		this.enderecoSelecionado = enderecoSelecionado;
	}
}
