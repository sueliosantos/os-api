package com.sss.enums;

public enum Status {
	ABERTO(0, "ABERTO"),
	ANDAMENTO(1, "ANDAMENTO"),
	ENCERRADO(2, "ENCERRADO");
	
	
	private Integer cod;
	private String descricao;
	private Status(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public Integer getCod() {
		return cod;
	}
	public String getDescricao() {
		return descricao;
	}
	
	
	public static Status toEnum(Integer cod) {
		if (cod==null) {
			return null;
		}
		
		for(Status p : Status.values()) {
			if(cod.equals(p.getCod())) {
				return p;
			}
		}
		throw new IllegalArgumentException("Status inválido" + cod);
		
	}
}
