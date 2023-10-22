package com.sss.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sss.domain.Cliente;
import com.sss.domain.OS;
import com.sss.domain.Tecnico;
import com.sss.enums.Perfil;
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
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	
	public void instanciaDB() {
		// id, String nome, @CPF String cpf, String telefone, String email, String senha
		Tecnico t1 = new Tecnico(null, "Suelio", "034.055.944-64", "8499671-4505", "sueliosantos@gmail.com", encoder.encode("123456"));
		t1.addPerfil(Perfil.ADMIN);
		Cliente c1 = new Cliente(null, "Claudiana", "711.261.060-54", "8499671-4505", "cliente@gmail.com", encoder.encode("123456"));
		Cliente c2 = new Cliente(null, "Giovanna", "337.761.040-58", "8499671-4505", "gi@gmail.com", encoder.encode("123"));
		OS os1 = new OS(null, Prioridade.ALTA, "Teste", Status.ANDAMENTO, t1, c1);
		
		
		t1.getList().add(os1);
		c1.getList().add(os1);
		
		tecnicoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.saveAll(Arrays.asList(c1, c2));
		osRepository.saveAll(Arrays.asList(os1));
	}

}
