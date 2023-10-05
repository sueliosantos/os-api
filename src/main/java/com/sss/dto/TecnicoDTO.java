package com.sss.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.validator.constraints.br.CPF;

import com.sss.domain.Tecnico;
import com.sss.enums.Perfil;

import jakarta.validation.constraints.NotEmpty;

public class TecnicoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	
	@NotEmpty(message = "O campo NOME é obrigatório")
	private String nome;
	@CPF
	@NotEmpty(message = "O campo CPF é obrigatório")
	private String cpf;
	private String telefone;
	private String email;
	private String senha;
	protected Set<Integer> perfis = new HashSet<>();
	
	public TecnicoDTO() {
		super();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


	public TecnicoDTO(Tecnico obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.telefone = obj.getTelefone();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
		this.perfis = obj.getPerfis().stream().map(x -> x.getCod()).collect(Collectors.toSet());
	}


	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}


	public void addPerfil(Perfil perfi) {
		this.perfis.add(perfi.getCod());
	}
	
	

}
