package com.unopar.entrega.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.unopar.entrega.model.Pedido;
import com.unopar.entrega.model.ProdutoPedido;
import com.unopar.entrega.repository.Pedidos;
import com.unopar.entrega.repository.ProdutoPedidos;
import com.unopar.entrega.util.Transactional;

public class CadastroPedidos implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private Pedidos pedidos;

	@Inject
	private ProdutoPedidos produtoPedidos;

	@Transactional
	public void salvar(Pedido pedido, List<ProdutoPedido> produtos) throws NegocioException {
		if (pedido.getCliente() == null) {
			throw new NegocioException("Informe o cliente do pedido.");
		}
		if (pedido.getStatus() == null) {
			throw new NegocioException("Informe o status do pedido.");
		}
		if (produtos == null || produtos.isEmpty()) {
			// throw new NegocioException("Informe os produtos do pedido.");
		}
		if (pedido.getData() == null) {
			pedido.setData(new Date());
		}
		if (pedido.getValorTaxaEntrega() == null) {
			pedido.setValorTaxaEntrega(BigDecimal.ZERO);
		}
		if (pedido.getValorPago() == null) {
			pedido.setValorPago(BigDecimal.ZERO);
		}
		if (pedido.getValorTroco() == null) {
			pedido.setValorTroco(BigDecimal.ZERO);
		}

		BigDecimal somaQuantidade = BigDecimal.ZERO;
		BigDecimal somaProdutos = BigDecimal.ZERO;

		if (produtos != null && !produtos.isEmpty()) {
			for (ProdutoPedido pp : produtos) {
				somaQuantidade = somaQuantidade.add(pp.getQuantidade());
				somaProdutos = somaProdutos.add(pp.getValorTotal());
			}
		}

		pedido.setQuantidade(somaQuantidade);
		pedido.setValorTotalProdutos(somaProdutos);
		pedido.setValorTotalPedido(pedido.getValorTotalProdutos().add(pedido.getValorTaxaEntrega()));
		if (pedido.getValorPago().compareTo(BigDecimal.ZERO) > 0) {
			pedido.setValorTroco(pedido.getValorPago().subtract(pedido.getValorTotalPedido()));
		}

		Pedido pedidoSalvo = this.pedidos.guardar(pedido);

		if (produtos != null && !produtos.isEmpty()) {
			for (ProdutoPedido pp : produtos) {
				pp.setPedido(pedidoSalvo);
				this.produtoPedidos.guardar(pp);
			}
		}

	}

	@Transactional
	public void excluir(Pedido pedido) throws NegocioException {
		pedido = this.pedidos.porId(pedido.getIdPedido());
		this.pedidos.remover(pedido);
	}
	
	@Transactional
	public void excluirProduto(ProdutoPedido pp) throws NegocioException {
		pp = this.produtoPedidos.porId(pp.getIdProdutoPedido());
		this.produtoPedidos.remover(pp);
	}
}
