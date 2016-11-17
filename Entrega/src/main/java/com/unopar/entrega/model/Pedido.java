package com.unopar.entrega.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idPedido;
	private Entregador entregador;
	private Cliente cliente;
	private Date data;
	private String status;
	private BigDecimal quantidade;
	private BigDecimal valorTotalProdutos;
	private BigDecimal valorTaxaEntrega;
	private BigDecimal valorTotalPedido;
	private BigDecimal valorPago;
	private BigDecimal valorTroco;

	@Id
	@GeneratedValue
	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "idEntregador")
	public Entregador getEntregador() {
		return entregador;
	}

	public void setEntregador(Entregador entregador) {
		this.entregador = entregador;
	}

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "idCliente")
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "data", nullable = false)
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@NotNull
	@Column(nullable = false)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(precision = 10, scale = 2, nullable = false)
	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	@Column(precision = 10, scale = 2, nullable = false)
	public BigDecimal getValorTotalProdutos() {
		return valorTotalProdutos;
	}

	public void setValorTotalProdutos(BigDecimal valorTotalProdutos) {
		this.valorTotalProdutos = valorTotalProdutos;
	}

	@Column(precision = 10, scale = 2, nullable = false)
	public BigDecimal getValorTaxaEntrega() {
		return valorTaxaEntrega;
	}

	public void setValorTaxaEntrega(BigDecimal valorTaxaEntrega) {
		this.valorTaxaEntrega = valorTaxaEntrega;
	}

	@Column(precision = 10, scale = 2, nullable = false)
	public BigDecimal getValorTotalPedido() {
		return valorTotalPedido;
	}

	public void setValorTotalPedido(BigDecimal valorTotalPedido) {
		this.valorTotalPedido = valorTotalPedido;
	}

	@Column(precision = 10, scale = 2, nullable = true)
	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	@Column(precision = 10, scale = 2, nullable = true)
	public BigDecimal getValorTroco() {
		return valorTroco;
	}

	public void setValorTroco(BigDecimal valorTroco) {
		this.valorTroco = valorTroco;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((entregador == null) ? 0 : entregador.hashCode());
		result = prime * result + idPedido;
		result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((valorPago == null) ? 0 : valorPago.hashCode());
		result = prime * result + ((valorTaxaEntrega == null) ? 0 : valorTaxaEntrega.hashCode());
		result = prime * result + ((valorTotalPedido == null) ? 0 : valorTotalPedido.hashCode());
		result = prime * result + ((valorTotalProdutos == null) ? 0 : valorTotalProdutos.hashCode());
		result = prime * result + ((valorTroco == null) ? 0 : valorTroco.hashCode());
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
		Pedido other = (Pedido) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (entregador == null) {
			if (other.entregador != null)
				return false;
		} else if (!entregador.equals(other.entregador))
			return false;
		if (idPedido != other.idPedido)
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		if (status != other.status)
			return false;
		if (valorPago == null) {
			if (other.valorPago != null)
				return false;
		} else if (!valorPago.equals(other.valorPago))
			return false;
		if (valorTaxaEntrega == null) {
			if (other.valorTaxaEntrega != null)
				return false;
		} else if (!valorTaxaEntrega.equals(other.valorTaxaEntrega))
			return false;
		if (valorTotalPedido == null) {
			if (other.valorTotalPedido != null)
				return false;
		} else if (!valorTotalPedido.equals(other.valorTotalPedido))
			return false;
		if (valorTotalProdutos == null) {
			if (other.valorTotalProdutos != null)
				return false;
		} else if (!valorTotalProdutos.equals(other.valorTotalProdutos))
			return false;
		if (valorTroco == null) {
			if (other.valorTroco != null)
				return false;
		} else if (!valorTroco.equals(other.valorTroco))
			return false;
		return true;
	}

}
