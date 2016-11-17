package com.unopar.entrega.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idCliente;
	private Endereco endereco;
	private String nome;
	private String telefone;
	private Date dataNascimento;
	private String enderecoPontoReferencia;
	private String enderecoNumero;

	@Id
	@GeneratedValue
	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "idEndereco")
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Column(length = 240, nullable = true)
	public String getEnderecoPontoReferencia() {
		return enderecoPontoReferencia;
	}

	public void setEnderecoPontoReferencia(String enderecoPontoReferencia) {
		this.enderecoPontoReferencia = enderecoPontoReferencia;
	}

	@Column(length = 10, nullable = true)
	public String getEnderecoNumero() {
		return enderecoNumero;
	}

	public void setEnderecoNumero(String enderecoNumero) {
		this.enderecoNumero = enderecoNumero;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((enderecoNumero == null) ? 0 : enderecoNumero.hashCode());
		result = prime * result + ((enderecoPontoReferencia == null) ? 0 : enderecoPontoReferencia.hashCode());
		result = prime * result + idCliente;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
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
		Cliente other = (Cliente) obj;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (enderecoNumero == null) {
			if (other.enderecoNumero != null)
				return false;
		} else if (!enderecoNumero.equals(other.enderecoNumero))
			return false;
		if (enderecoPontoReferencia == null) {
			if (other.enderecoPontoReferencia != null)
				return false;
		} else if (!enderecoPontoReferencia.equals(other.enderecoPontoReferencia))
			return false;
		if (idCliente != other.idCliente)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}

}
