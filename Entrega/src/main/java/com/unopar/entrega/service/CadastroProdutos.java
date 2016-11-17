package com.unopar.entrega.service;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.inject.Inject;

import com.unopar.entrega.model.Produto;
import com.unopar.entrega.repository.Produtos;
import com.unopar.entrega.util.Transactional;

public class CadastroProdutos implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private Produtos produtos;

	@Transactional
	public void salvar(Produto produto) throws NegocioException {
		if (produto.getUnidade() == null) {
			throw new NegocioException("Informe a unidade produto.");
		}
		if (produto.getNome() == null || produto.getNome().isEmpty()) {
			throw new NegocioException("Informe o nome produto.");
		}
		if (produto.getDescricao() == null || produto.getDescricao().isEmpty()) {
			throw new NegocioException("Informe a descrição do produto.");
		}
		if (produto.getTamanho() == null || produto.getTamanho().compareTo(BigDecimal.ZERO) <= 0) {
			throw new NegocioException("Informe um tamanho positivo para o produto.");
		}
		if (produto.getValor() == null || produto.getValor().compareTo(BigDecimal.ZERO) <= 0) {
			throw new NegocioException("Informe um valor positivo para o produto.");
		}
		this.produtos.guardar(produto);
	}

	@Transactional
	public void excluir(Produto produto) throws NegocioException {
		produto = this.produtos.porId(produto.getIdProduto());
		this.produtos.remover(produto);
	}

}
