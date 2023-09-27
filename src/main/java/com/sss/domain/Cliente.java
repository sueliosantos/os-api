package com.sss.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Cliente extends Pessoa{
	
	@OneToMany(mappedBy = "cliente")
	private List<OS> list = new ArrayList<>();
	
	public Cliente() {
		super();
	}

	public Cliente(Integer id, String nome, String cpf, String telefone) {
		super(id, nome, cpf, telefone);
	}

	public List<OS> getList() {
		return list;
	}

	public void setList(List<OS> list) {
		this.list = list;
	}

}
