package com.unopar.entrega.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

import org.primefaces.event.RowEditEvent;

import com.unopar.entrega.model.Empresa;
import com.unopar.entrega.model.Produto;
import com.unopar.entrega.model.ProdutoPedido;
import com.unopar.entrega.repository.Empresas;

@ManagedBean(name = "itemBean")
@SessionScoped
public class ItemBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Produto produto;
	private BigDecimal quantidade;
	private BigDecimal valorUnitario;
	private BigDecimal valorTotal;
	private BigDecimal totalProdutos;
	private BigDecimal totalPedido;
	private static BigDecimal taxaEntrega;
	private ProdutoPedido produtoPedido;
	public static final ArrayList<ProdutoPedido> listProdutoPedido = new ArrayList<>();

	public String adicionar() {
		ProdutoPedido pp = new ProdutoPedido();
		pp.setProduto(this.produto);
		pp.setQuantidade(this.quantidade);
		pp.setValorUnitario(this.valorUnitario);
		pp.setValorTotal(this.valorTotal);

		listProdutoPedido.add(pp);
		this.produto = null;
		this.quantidade = BigDecimal.ZERO;
		this.valorUnitario = BigDecimal.ZERO;
		this.valorTotal = BigDecimal.ZERO;
		return null;
	}

	public void editar(RowEditEvent evt) {
		atualizarValorTotal();
	}

	public void cancelar(RowEditEvent evt) {
		listProdutoPedido.remove((ProdutoPedido) evt.getObject());
	}

	public void excluir() {
		if (this.produtoPedido != null && listProdutoPedido.contains(this.produtoPedido)) {
			listProdutoPedido.remove(this.produtoPedido);
		}
	}

	public void atualizarValorUnitario() {
		if (this.produto != null) {
			this.valorUnitario = this.produto.getValor();
		} else {
			this.valorUnitario = BigDecimal.ZERO;
		}
		if (this.quantidade == null || this.quantidade.compareTo(BigDecimal.ZERO) == 0) {
			this.quantidade = BigDecimal.ONE;
		}
	}

	public void atualizarValorTotal() {
		this.valorTotal = (this.valorUnitario != null ? this.valorUnitario : BigDecimal.ZERO)
				.multiply(this.quantidade != null ? this.quantidade : BigDecimal.ZERO);
	}

	public BigDecimal getTotalPedido() {
		return getTotalProdutos().add(taxaEntrega != null ? taxaEntrega : BigDecimal.ZERO);
	}

	public BigDecimal getTotalProdutos() {
		this.totalProdutos = BigDecimal.ZERO;
		if (listProdutoPedido != null && !listProdutoPedido.isEmpty()) {
			for (ProdutoPedido pp : listProdutoPedido) {
				this.totalProdutos = this.totalProdutos.add(pp.getValorTotal());
			}
		}
		return this.totalProdutos;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public ArrayList<ProdutoPedido> getListProdutoPedido() {
		return listProdutoPedido;
	}

	public ProdutoPedido getProdutoPedido() {
		return produtoPedido;
	}

	public void setProdutoPedido(ProdutoPedido produtoPedido) {
		this.produtoPedido = produtoPedido;
	}

	public static BigDecimal getTaxaEntrega() {
		return taxaEntrega;
	}

	public static void setTaxaEntrega(BigDecimal taxa) {
		taxaEntrega = taxa;
	}

}
