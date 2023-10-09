package com.sss.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sss.enums.Perfil;



@Entity
public class Tecnico extends Pessoa {
	private static final long serialVersionUID = 1L;
	
	/*
	 * Protege contra serialização(Para não entrar em loop nas chamadas)
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "tecnico")
	private List<OS> list = new ArrayList<>();
	
	public List<OS> getList() {
		return list;
	}

	public void setList(List<OS> list) {
		this.list = list;
	}

	public Tecnico() {
		super();
		addPerfil(Perfil.TECNICO);
	}

	public Tecnico(Integer id, String nome, @CPF String cpf, String telefone, String email, String senha) {
		super(id, nome, cpf, telefone, email, senha);
		addPerfil(Perfil.TECNICO);
	}

}
