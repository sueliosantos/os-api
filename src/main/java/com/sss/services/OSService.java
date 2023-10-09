package com.sss.services;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sss.domain.Cliente;
import com.sss.domain.OS;
import com.sss.domain.Tecnico;
import com.sss.dto.OSDTO;
import com.sss.exceptions.ObjectNotFoundException;
import com.sss.repositories.OSRepository;

@Service
public class OSService {
	@Autowired
	private OSRepository repository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;
	
	public OS findById(Integer id) {
		java.util.Optional<OS> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Não encontrado! Id: " 
		+ id + " Tipo: " + OS.class.getName()));
	}
	
	public List<OS> getAll() {
		return repository.findAll();
		
	}

	public OS create(@Valid OSDTO obj) {
		return fromDTO(obj);

	}
	
	public OS update(@Valid OSDTO obj) {
		findById(obj.getId());
		
		return fromDTO(obj);
	}
	
	private OS fromDTO(OSDTO obj) {
		OS newOs = new OS();
		newOs.setId(obj.getId());
		newOs.setObservacao(obj.getObservacao());
		newOs.setPrioridade(obj.getPrioridade());
		newOs.setStatus(obj.getStatus());
		Tecnico tec = tecnicoService.findById(obj.getTecnico());
		Cliente cli = clienteService.findById(obj.getCliente());
		newOs.setTecnico(tec);
		newOs.setCliente(cli);
		
		if (newOs.getStatus().getCod().equals(2)) {
			newOs.setDataFechamento(LocalDateTime.now());
		}
		
		return repository.save(newOs);
		
		
	}

}
