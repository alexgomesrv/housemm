package com.unopar.entrega.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.unopar.entrega.model.Usuario;
import com.unopar.entrega.repository.Usuarios;
import com.unopar.entrega.util.Transactional;

public class CadastroUsuarios implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private Usuarios usuarios;

	@Transactional
	public void salvar(Usuario usuario) throws NegocioException {
		if (usuario.getEmpresa() == null) {
			throw new NegocioException("Informe a empresa do usuário.");
		}
		if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
			throw new NegocioException("Informe o nome do usuário.");
		}
		if (usuario.getTelefone() == null
				|| (usuario.getTelefone().length() != 10 && usuario.getTelefone().length() != 11)) {
			throw new NegocioException("Informe um telefone válido para o usuário.");
		}
		if (usuario.getCpf() == null || usuario.getCpf().length() != 11) {
			throw new NegocioException("Informe um CPF válido para o usuário.");
		}
		if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
			throw new NegocioException("Informe a senha do usuário.");
		}
		this.usuarios.guardar(usuario);
	}

	@Transactional
	public void excluir(Usuario usuario) throws NegocioException {
		usuario = this.usuarios.porId(usuario.getIdUsuario());
		this.usuarios.remover(usuario);
	}

}
