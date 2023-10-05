package com.sss.enums;



public enum Perfil {
	ADMIN(0,"ROLE_ADMIN"),
	CLIENTE(1,"ROLE_CLIENTE"),
	TECNICO(2,"ROLE_TECNICO");
	
	
	private Integer cod;
	private String descricao;
	private Perfil(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public Integer getCod() {
		return cod;
	}
	public String getDescricao() {
		return descricao;
	}
	
	
	public static Perfil toEnum(Integer cod) {
		if (cod==null) {
			return null;
		}
		
		for(Perfil p : Perfil.values()) {
			if(cod.equals(p.getCod())) {
				return p;
			}
		}
		throw new IllegalArgumentException("Perfil inválido" + cod);
		
	}
	
}
