package com.sss.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sss.domain.Cliente;
import com.sss.domain.OS;
import com.sss.dto.ClienteDTO;
import com.sss.dto.OSDTO;
import com.sss.services.ClienteService;
import com.sss.services.OSService;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/os")
public class OSController {
	@Autowired
	private OSService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<OSDTO> findById(@PathVariable Integer id){
		OSDTO dto = new OSDTO(service.findById(id));
		
		return ResponseEntity.ok().body(dto);	
	}
	
	@GetMapping
	public ResponseEntity<List<OSDTO>> findAll(){
		List<OS> list = service.getAll();
		List<OSDTO> listDTO = new ArrayList<>();
		
		for (OS obj : list) {
			listDTO.add(new OSDTO(obj));
		}
		
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<List<OSDTO>> create(@Valid @RequestBody OSDTO obj  ){
		obj = new OSDTO(service.create(obj));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping
	public ResponseEntity<@Valid OSDTO> update(@Valid @RequestBody OSDTO obj){
		obj = new OSDTO(service.update(obj));
		return ResponseEntity.ok().body(obj);
	}

}
