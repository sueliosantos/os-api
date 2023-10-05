package com.sss.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sss.domain.Pessoa;
import com.sss.domain.Tecnico;
import com.sss.dto.TecnicoDTO;
import com.sss.exceptions.DataValidateExecpiton;
import com.sss.exceptions.ObjectNotFoundException;
import com.sss.repositories.PessoaRepository;
import com.sss.repositories.TecnicoRepository;

import jakarta.validation.Valid;

@Service
public class TecnicoService {
	@Autowired
	private TecnicoRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Não encontrado! Id: " 
		+ id + " Tipo: " + Tecnico.class.getName()));
	}

	public java.util.List<Tecnico> getAll() {
		return repository.findAll();
		
	}
	
	public Tecnico create(TecnicoDTO dto) {
		if (findByCpf(dto)!=null) {
			throw new DataValidateExecpiton("CPF já cadastrado na base de dados!");
		}
		Tecnico tecnico = new Tecnico(null, dto.getNome(), dto.getCpf(), dto.getTelefone(), dto.getEmail(), dto.getSenha());
		return repository.save(tecnico);
	}
	
	private Pessoa findByCpf(TecnicoDTO dto) {
		Pessoa obj = pessoaRepository.findByCpf(dto.getCpf());
		if (obj !=null) {
			return obj; 
		}
		return null;
	}

	public Tecnico update(Integer id, @Valid TecnicoDTO dto) {
		Tecnico oldObj = findById(id);
		if (findByCpf(dto) != null && findByCpf(dto).getId() != id ) {
			throw new DataValidateExecpiton("CPF já cadastrado na base de dados!");
		}
		
		oldObj.setNome(dto.getNome());
		oldObj.setCpf(dto.getCpf());
		oldObj.setTelefone(dto.getTelefone());
		
		return repository.save(oldObj);
	}

	public void delete(Integer id) {
		Tecnico oldObj = findById(id);
		if (oldObj.getList().size() >0 ) {
			throw new DataValidateExecpiton("Técnico não pode ser deletado, pois possui ordem de serviço!");

		}
		
		repository.deleteById(id);
		
	}
}
