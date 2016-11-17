package com.unopar.entrega.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.unopar.entrega.model.Produto;
import com.unopar.entrega.model.Unidade;
import com.unopar.entrega.repository.Produtos;
import com.unopar.entrega.repository.Unidades;
import com.unopar.entrega.service.CadastroProdutos;
import com.unopar.entrega.service.NegocioException;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroProdutos cadastro;

	@Inject
	private Produtos produtosRepository;

	private Produto produtoSelecionado;
	private Produto produto = new Produto();

	private List<Produto> produtos;
	
	@Inject
	private Unidades unidades;
	private List<Unidade> listaUnidades;

	public void consultar() {
		this.produtos = produtosRepository.buscar("");
	}

	public void prepararCadastro() {
		this.listaUnidades = unidades.buscar("");
		if (this.produto == null) {
			this.produto = new Produto();
		}
		this.consultar();
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.cadastro.salvar(this.produto);
			this.produto = new Produto();
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
			this.cadastro.excluir(this.produtoSelecionado);
			this.consultar();
			context.addMessage(null, new FacesMessage("Produto excluída com sucesso!"));
		} catch (com.unopar.entrega.service.NegocioException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public List<Unidade> getListaUnidades() {
		return this.listaUnidades;
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}
}
