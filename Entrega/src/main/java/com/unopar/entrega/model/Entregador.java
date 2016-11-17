package com.unopar.entrega.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "entregador")
public class Entregador implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idEntregador;
	private EmpresaEntrega empresaEntrega;
	private String nome;
	private String cpf;
	private String rg;
	private String celular;

	@Id
	@GeneratedValue
	public int getIdEntregador() {
		return idEntregador;
	}

	public void setIdEntregador(int idEntregador) {
		this.idEntregador = idEntregador;
	}

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "idEmpresaEntrega")
	public EmpresaEntrega getEmpresaEntrega() {
		return empresaEntrega;
	}

	public void setEmpresaEntrega(EmpresaEntrega empresaEntrega) {
		this.empresaEntrega = empresaEntrega;
	}

	@NotEmpty
	@Column(length = 240, nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotEmpty
	@Column(length = 11, nullable = false)
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@NotEmpty
	@Column(length = 20, nullable = false)
	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	@NotEmpty
	@Column(length = 11, nullable = false)
	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((celular == null) ? 0 : celular.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((empresaEntrega == null) ? 0 : empresaEntrega.hashCode());
		result = prime * result + idEntregador;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((rg == null) ? 0 : rg.hashCode());
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
		Entregador other = (Entregador) obj;
		if (celular == null) {
			if (other.celular != null)
				return false;
		} else if (!celular.equals(other.celular))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (empresaEntrega == null) {
			if (other.empresaEntrega != null)
				return false;
		} else if (!empresaEntrega.equals(other.empresaEntrega))
			return false;
		if (idEntregador != other.idEntregador)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (rg == null) {
			if (other.rg != null)
				return false;
		} else if (!rg.equals(other.rg))
			return false;
		return true;
	}

}
