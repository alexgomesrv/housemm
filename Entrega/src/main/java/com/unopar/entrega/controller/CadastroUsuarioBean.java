package com.unopar.entrega.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.unopar.entrega.model.Empresa;
import com.unopar.entrega.model.Usuario;
import com.unopar.entrega.repository.Empresas;
import com.unopar.entrega.repository.Usuarios;
import com.unopar.entrega.service.CadastroUsuarios;
import com.unopar.entrega.service.NegocioException;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroUsuarios cadastro;

	@Inject
	private Usuarios usuariosRepository;

	private Usuario usuarioSelecionado;
	private Usuario usuario = new Usuario();

	private List<Usuario> usuarios;
	
	@Inject
	private Empresas empresas;
	private List<Empresa> listaEmpresas;

	public void consultar() {
		this.usuarios = usuariosRepository.buscar("");
	}

	public void prepararCadastro() {
		this.listaEmpresas = empresas.buscar("");
		if (this.usuario == null) {
			this.usuario = new Usuario();
		}
		this.consultar();
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.cadastro.salvar(this.usuario);
			this.usuario = new Usuario();
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
			this.cadastro.excluir(this.usuarioSelecionado);
			this.consultar();
			context.addMessage(null, new FacesMessage("Usuario excluída com sucesso!"));
		} catch (com.unopar.entrega.service.NegocioException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}
	
	public List<Empresa> getListaEmpresas() {
		return this.listaEmpresas;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}
}
