package com.unopar.entrega.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.unopar.entrega.model.Produto;
import com.unopar.entrega.model.ProdutoPedido;

@Named
@ViewScoped
public class CadastroProdutoPedidoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Produto produto;
	private BigDecimal valorUnitario;
	private BigDecimal quantidade;
	private BigDecimal valorTotal;
    private ProdutoPedido produtoPedido;
 
    
    private static final ArrayList<ProdutoPedido> listaProdutos= new ArrayList<ProdutoPedido>();
 
    public ArrayList<ProdutoPedido> getListaProdutos() {
        return this.listaProdutos;
    }
 
    public String addAction() {
        ProdutoPedido pp = new ProdutoPedido();
        pp.setProduto(this.produto);
        pp.setValorUnitario(this.valorUnitario);
        pp.setQuantidade(this.quantidade);
        pp.setValorTotal(this.valorTotal);
        listaProdutos.add(pp);
 
        produto = null;
        valorUnitario = BigDecimal.ZERO;
        quantidade = BigDecimal.ZERO;
        valorTotal = BigDecimal.ZERO;
        return null;
    }
    
    public void onEdit(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Item Edited", ((ProdutoPedido) event.getObject()).getProduto().getNome());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
       
    public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Item Cancelled");   
        FacesContext.getCurrentInstance().addMessage(null, msg); 
        listaProdutos.remove((ProdutoPedido) event.getObject());
    }

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public ProdutoPedido getProdutoPedido() {
		return produtoPedido;
	}

	public void setProdutoPedido(ProdutoPedido produtoPedido) {
		this.produtoPedido = produtoPedido;
	}  
    
    
}
