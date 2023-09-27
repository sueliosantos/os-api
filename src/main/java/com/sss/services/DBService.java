package com.sss.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.sss.domain.Cliente;
import com.sss.domain.OS;
import com.sss.domain.Tecnico;
import com.sss.enums.Prioridade;
import com.sss.enums.Status;
import com.sss.repositories.ClienteRepository;
import com.sss.repositories.OSRepository;
import com.sss.repositories.TecnicoRepository;

@Service
public class DBService {
	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;
		
	@Autowired
	private OSRepository osRepository;
	
	public void instanciaDB() {
		Tecnico t1 = new Tecnico(null, "Suelio", "034.055.944-64", "8499671-4505");
		Cliente c1 = new Cliente(null, "Claudiana", "711.261.060-54", "8499671-4505");
		OS os1 = new OS(null, Prioridade.ALTA, "Teste", Status.ANDAMENTO, t1, c1);
		
		
		t1.getList().add(os1);
		c1.getList().add(os1);
		
		tecnicoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));
	}

}
