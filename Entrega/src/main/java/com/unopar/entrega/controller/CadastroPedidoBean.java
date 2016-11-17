package com.unopar.entrega.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.unopar.entrega.model.Cliente;
import com.unopar.entrega.model.Endereco;
import com.unopar.entrega.model.Entregador;
import com.unopar.entrega.model.Pedido;
import com.unopar.entrega.model.PedidoStatus;
import com.unopar.entrega.model.Produto;
import com.unopar.entrega.model.ProdutoPedido;
import com.unopar.entrega.repository.Clientes;
import com.unopar.entrega.repository.Empresas;
import com.unopar.entrega.repository.Enderecos;
import com.unopar.entrega.repository.Entregadores;
import com.unopar.entrega.repository.Pedidos;
import com.unopar.entrega.repository.ProdutoPedidos;
import com.unopar.entrega.repository.Produtos;
import com.unopar.entrega.service.CadastroClientes;
import com.unopar.entrega.service.CadastroPedidos;
import com.unopar.entrega.service.NegocioException;

@Named
@ViewScoped
public class CadastroPedidoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroPedidos cadastro;
	@Inject
	private Pedidos pedidosRepository;
	private Pedido pedidoSelecionada;
	private ProdutoPedido ppSelecionado;
	private Pedido pedido = new Pedido();
	private List<ProdutoPedido> listProdutoPedido;
	private List<Pedido> pedidos;
	private BigDecimal taxaEntrega;

	@Inject
	private CadastroClientes cadastroCliente;
	@Inject
	private Cliente cliente = new Cliente();

	@Inject
	private Enderecos enderecos;
	private List<Endereco> listaEnderecos;

	@Inject
	private Produtos produtos;
	private List<Produto> listaProdutos;
	
	@Inject
	private Entregadores entregadores;
	private List<Entregador> listaEntregadores;
	
	@Inject
	private Empresas empresas;
	
	@Inject
	private ProdutoPedidos produtoPedidoRepository;
	@Inject
	private Clientes clientesRepository;
	
	private Produto produto;
	private BigDecimal quantidade;
	private BigDecimal valorUnitario;
	private BigDecimal valorTotal;
	private BigDecimal totalProdutos;
	private BigDecimal totalPedido;
	private ProdutoPedido produtoPedido;
	
	private String statusFiltro;
	
	public String adicionar() {
		if (listProdutoPedido == null) {
			listProdutoPedido = new ArrayList<ProdutoPedido>();
		}
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

	public void consultar() {
		this.pedidos = pedidosRepository.buscar(getStatusFiltro());
	}

	public void prepararCadastro() {
		this.statusFiltro = "";
		this.listaEnderecos = enderecos.buscar("");
		this.listaProdutos = produtos.buscar("");
		this.listaEntregadores = entregadores.buscar("");
		this.taxaEntrega = empresas.buscar("").get(0).getTaxaEntrega();		
		ItemBean.setTaxaEntrega(taxaEntrega);
		if (this.cliente == null) {
			this.cliente = new Cliente();
		}

		if (this.pedido == null) {
			this.pedido = new Pedido();
			this.pedido.setStatus(PedidoStatus.PENDENTE.getId());
			this.listProdutoPedido = new ArrayList<ProdutoPedido>();
			this.cliente = new Cliente();
		} else {
			this.listProdutoPedido = produtoPedidoRepository.buscarPorPedido(pedido.getIdPedido());
			this.cliente = clientesRepository.porId(this.pedido.getCliente().getIdCliente());
		}
		this.consultar();
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {

			this.pedido.setCliente(this.cadastroCliente.salvar(this.cliente));
			this.pedido.setData(new Date());
			this.pedido.setValorTaxaEntrega(empresas.buscar("").get(0).getTaxaEntrega());

			this.cadastro.salvar(this.pedido, this.listProdutoPedido);
			this.pedido = new Pedido();
			this.cliente = new Cliente();
			listProdutoPedido.clear();
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
			this.cadastro.excluir(this.pedidoSelecionada);
			this.consultar();
			context.addMessage(null, new FacesMessage("Pedido excluído com sucesso!"));
		} catch (com.unopar.entrega.service.NegocioException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}
	
	public void excluirProduto() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.listProdutoPedido.remove(ppSelecionado);
			this.cadastro.excluirProduto(this.ppSelecionado);		
		} catch (com.unopar.entrega.service.NegocioException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public void buscarParceiroPorTelefone(AjaxBehaviorEvent event) {
		String telefone = this.cliente.getTelefone();
		Cliente cl = pedidosRepository.buscarPorTelefone(this.cliente.getTelefone());
		if (cl != null) {
			this.cliente = cl;
		} else {
			this.cliente = new Cliente();
			this.cliente.setTelefone(telefone);
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

	public ProdutoPedido getProdutoPedido() {
		return produtoPedido;
	}

	public void setProdutoPedido(ProdutoPedido produtoPedido) {
		this.produtoPedido = produtoPedido;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Pedido getPedidoSelecionada() {
		return pedidoSelecionada;
	}

	public void setPedidoSelecionada(Pedido pedidoSelecionada) {
		this.pedidoSelecionada = pedidoSelecionada;
	}

	public List<ProdutoPedido> getListProdutoPedido() {
		return listProdutoPedido;
	}

	public void setListProdutoPedido(List<ProdutoPedido> listProdutoPedido) {
		this.listProdutoPedido = listProdutoPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Enderecos getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Enderecos enderecos) {
		this.enderecos = enderecos;
	}

	public List<Endereco> getListaEnderecos() {
		return listaEnderecos;
	}

	public void setListaEnderecos(List<Endereco> listaEnderecos) {
		this.listaEnderecos = listaEnderecos;
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public Empresas getEmpresas() {
		return empresas;
	}

	public void setEmpresas(Empresas empresas) {
		this.empresas = empresas;
	}

	public BigDecimal getTaxaEntrega() {
		return taxaEntrega;
	}

	public void setTaxaEntrega(BigDecimal taxaEntrega) {
		this.taxaEntrega = taxaEntrega;
	}

	public void setTotalProdutos(BigDecimal totalProdutos) {
		this.totalProdutos = totalProdutos;
	}

	public void setTotalPedido(BigDecimal totalPedido) {
		this.totalPedido = totalPedido;
	}

	public List<Entregador> getListaEntregadores() {
		return listaEntregadores;
	}

	public void setListaEntregadores(List<Entregador> listaEntregadores) {
		this.listaEntregadores = listaEntregadores;
	}

	public ProdutoPedido getPpSelecionado() {
		return ppSelecionado;
	}

	public void setPpSelecionado(ProdutoPedido ppSelecionado) {
		this.ppSelecionado = ppSelecionado;
	}

	public String getStatusFiltro() {
		return statusFiltro;
	}

	public void setStatusFiltro(String statusFiltro) {
		this.statusFiltro = statusFiltro;
	}

}
