package com.unopar.entrega.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.unopar.entrega.model.EmpresaEntrega;
import com.unopar.entrega.model.Entregador;
import com.unopar.entrega.repository.EmpresaEntregas;
import com.unopar.entrega.repository.Entregadores;
import com.unopar.entrega.service.CadastroEntregadores;
import com.unopar.entrega.service.NegocioException;

@Named
@ViewScoped
public class CadastroEntregadorBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroEntregadores cadastro;

	@Inject
	private Entregadores entregadoresRepository;

	private Entregador entregadorSelecionado;
	private Entregador entregador = new Entregador();

	private List<Entregador> entregadores;
	
	@Inject
	private EmpresaEntregas empresasEntrega;
	private List<EmpresaEntrega> listaEmpresasEntrega;

	public void consultar() {
		this.entregadores = entregadoresRepository.buscar("");
	}

	public void prepararCadastro() {
		this.listaEmpresasEntrega = empresasEntrega.buscar("");
		if (this.entregador == null) {
			this.entregador = new Entregador();
		}
		this.consultar();
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.cadastro.salvar(this.entregador);
			this.entregador = new Entregador();
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
			this.cadastro.excluir(this.entregadorSelecionado);
			this.consultar();
			context.addMessage(null, new FacesMessage("Entregador excluída com sucesso!"));
		} catch (com.unopar.entrega.service.NegocioException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}
	
	public List<EmpresaEntrega> getListaEmpresasEntrega() {
		return this.listaEmpresasEntrega;
	}

	public List<Entregador> getEntregadores() {
		return entregadores;
	}

	public Entregador getEntregador() {
		return entregador;
	}

	public void setEntregador(Entregador entregador) {
		this.entregador = entregador;
	}

	public Entregador getEntregadorSelecionado() {
		return entregadorSelecionado;
	}

	public void setEntregadorSelecionado(Entregador entregadorSelecionado) {
		this.entregadorSelecionado = entregadorSelecionado;
	}
}
