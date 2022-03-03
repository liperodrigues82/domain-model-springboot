package com.devsuperior.auladomain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.auladomain.entities.Client;
import com.devsuperior.auladomain.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	public Client insert(Client obj) {
		return repository.save(obj);
	}
	
	public Client findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		return obj.get();
	}
	
	public List<Client> findAll() {
		return repository.findAll();
	}
	
	public Client update(Long id, Client obj) {
		Client entity = repository.getById(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	public void updateData(Client entity, Client obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
	}
	
	public void delele(Long id) {
		repository.deleteById(id);
	}
}
