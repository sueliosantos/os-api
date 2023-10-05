package com.sss.domain;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sss.enums.Perfil;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Cliente extends Pessoa{
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * Protege na busca contra a serialização( entrar em loop)
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<OS> list = new ArrayList<>();
	
	
	public List<OS> getList() {
		return list;
	}

	public void setList(List<OS> list) {
		this.list = list;
	}

	public Cliente() {
		super();
		addPerfil(Perfil.CLIENTE);
	}

	public Cliente(Integer id, String nome, @CPF String cpf, String telefone, String email, String senha) {
		super(id, nome, cpf, telefone, email, senha);
		addPerfil(Perfil.CLIENTE);
	}

}
