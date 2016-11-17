package com.unopar.entrega.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "produtopedido")
public class ProdutoPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idProdutoPedido;
	private Produto produto;
	private Pedido pedido;
	private BigDecimal valorUnitario;
	private BigDecimal quantidade;
	private BigDecimal valorTotal;

	@Id
	@GeneratedValue
	public int getIdProdutoPedido() {
		return idProdutoPedido;
	}

	public void setIdProdutoPedido(int idProdutoPedido) {
		this.idProdutoPedido = idProdutoPedido;
	}

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "idProduto")
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "idPedido")
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}	
	
	@Column(precision = 10, scale = 2, nullable = false)
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	@Column(precision = 10, scale = 2, nullable = false)
	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	@Column(precision = 10, scale = 2, nullable = false)
	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idProdutoPedido;
		result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
		result = prime * result + ((valorTotal == null) ? 0 : valorTotal.hashCode());
		result = prime * result + ((valorUnitario == null) ? 0 : valorUnitario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoPedido other = (ProdutoPedido) obj;
		if (idProdutoPedido != other.idProdutoPedido)
			return false;
		if (pedido == null) {
			if (other.pedido != null)
				return false;
		} else if (!pedido.equals(other.pedido))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		if (valorTotal == null) {
			if (other.valorTotal != null)
				return false;
		} else if (!valorTotal.equals(other.valorTotal))
			return false;
		if (valorUnitario == null) {
			if (other.valorUnitario != null)
				return false;
		} else if (!valorUnitario.equals(other.valorUnitario))
			return false;
		return true;
	}	

}
