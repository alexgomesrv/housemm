package com.unopar.entrega.controller;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.unopar.entrega.model.Usuario;
import com.unopar.entrega.repository.Usuarios;

@Named
@RequestScoped
public class LoginBean {

	@Inject
	private UsuarioLogado usuarioLogado;
	@Inject
	private Usuarios usuariosRepository;
	private String cpf;
	private String senha;

	public String login() {
		FacesContext context = FacesContext.getCurrentInstance();
		List<Usuario> list = usuariosRepository.buscar("");
		for (Usuario u : list) {
			if (u.getCpf().equals(this.cpf) && u.getSenha().equals(this.senha)) {
				this.usuarioLogado.setNome(u.getNome());
				this.usuarioLogado.setDataLogin(new Date());
				return "/CadastroPedidos?faces-redirect=true";
			}
		}
		FacesMessage mensagem = new FacesMessage("Usuário/Senha inválidos.");
		mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
		context.addMessage(null, mensagem);
		return null;
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/Login?faces-redirect=true";
	}

	public UsuarioLogado getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(UsuarioLogado usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
