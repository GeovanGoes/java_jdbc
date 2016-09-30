package br.com.penselink.ensino.java.modelo.entidades;

import java.util.Date;

/**
 * Entidade que representa uma pessoa
 * @author geovan.goes
 * */
public class Pessoa 
{

	/**
	 * Chave primaria
	 * */
	private Long id;
	
	/**
	 * Nome da pessoa
	 * */
	private String nome;
	
	/**
	 * Email da pessoa
	 * */
	private String email;
	
	
	/**
	 * Telefone da pessoa
	 * */
	private String telefone;
	
	/**
	 * Aniversário da pessoa
	 * */
	private Date aniversario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getAniversario() {
		return aniversario;
	}

	public void setAniversario(Date aniversario) {
		this.aniversario = aniversario;
	}
}
