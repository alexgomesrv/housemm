package com.unopar.entrega.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.unopar.entrega.model.Cliente;
import com.unopar.entrega.model.Endereco;
import com.unopar.entrega.repository.Clientes;
import com.unopar.entrega.repository.Enderecos;
import com.unopar.entrega.service.CadastroClientes;
import com.unopar.entrega.service.NegocioException;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroClientes cadastro;

	@Inject
	private Clientes clientesRepository;

	private Cliente clienteSelecionado;
	private Cliente cliente = new Cliente();

	private List<Cliente> clientes;

	@Inject
	private Enderecos enderecos;
	private List<Endereco> listaEnderecos;

	public void consultar() {
		this.clientes = clientesRepository.buscar("");
	}

	public void prepararCadastro() {
		this.listaEnderecos = enderecos.buscar("");
		if (this.cliente == null) {
			this.cliente = new Cliente();
		}
		this.consultar();
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.cadastro.salvar(this.cliente);
			this.cliente = new Cliente();
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
			this.cadastro.excluir(this.clienteSelecionado);
			this.consultar();
			context.addMessage(null, new FacesMessage("Cliente excluído com sucesso!"));
		} catch (com.unopar.entrega.service.NegocioException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public List<Endereco> getListaEnderecos() {
		return this.listaEnderecos;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}
}
