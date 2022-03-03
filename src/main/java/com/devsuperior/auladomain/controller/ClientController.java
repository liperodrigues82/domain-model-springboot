package com.devsuperior.auladomain.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.auladomain.entities.Client;
import com.devsuperior.auladomain.services.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	private ClientService service;
	
	@PostMapping
	public ResponseEntity<Client> insert(@RequestBody Client obj) {
		service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Client> findById(@PathVariable Long id) {
		Client obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<Client>> findAll() {
		List<Client> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delele(id);
		return ResponseEntity.noContent().build();
	}
}
