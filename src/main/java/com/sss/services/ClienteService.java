package com.sss.services;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sss.domain.Cliente;
import com.sss.domain.Pessoa;
import com.sss.dto.ClienteDTO;
import com.sss.exceptions.DataValidateExecpiton;
import com.sss.exceptions.ObjectNotFoundException;
import com.sss.repositories.ClienteRepository;
import com.sss.repositories.PessoaRepository;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public Cliente findById(Integer id) {
		java.util.Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Não encontrado! Id: " 
		+ id + " Tipo: " + Cliente.class.getName()));
	}

	public java.util.List<Cliente> getAll() {
		return repository.findAll();
		
	}
	
	public Cliente create(ClienteDTO dto) {
		if (findByCpf(dto)!=null) {
			throw new DataValidateExecpiton("CPF já cadastrado na base de dados!");
		}
		dto.setSenha(encoder.encode(dto.getSenha()));
		Cliente cliente = new Cliente(null, dto.getNome(), dto.getCpf(), dto.getTelefone(), dto.getEmail(), dto.getSenha());
		return repository.save(cliente);
	}
	
	private Pessoa findByCpf(ClienteDTO dto) {
		Pessoa obj = pessoaRepository.findByCpf(dto.getCpf());
		if (obj !=null) {
			return obj; 
		}
		return null;
	}

	public Cliente update(Integer id, @Valid ClienteDTO dto) {
		Cliente oldObj = findById(id);
		if (findByCpf(dto) != null && findByCpf(dto).getId() != id ) {
			throw new DataValidateExecpiton("CPF já cadastrado na base de dados!");
		}
		
		oldObj.setNome(dto.getNome());
		oldObj.setCpf(dto.getCpf());
		oldObj.setTelefone(dto.getTelefone());
		
		return repository.save(oldObj);
	}

	public void delete(Integer id) {
		Cliente oldObj = findById(id);
		if (oldObj.getList().size() >0 ) {
			throw new DataValidateExecpiton("Cliente não pode ser deletado, pois possui ordem de serviço!");

		}
		repository.deleteById(id);
	}
}
