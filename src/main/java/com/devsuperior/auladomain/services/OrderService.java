package com.devsuperior.auladomain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.auladomain.entities.Order;
import com.devsuperior.auladomain.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	public Order insert(Order obj) {
		return repository.save(obj);
	}
	
	public Order findById(Long id) {
		Optional<Order> obj = repository.findById(id);
		return obj.get();
	}
	
	public List<Order> findAll() {
		return repository.findAll();
	}
	
	public Order update(Long id, Order obj) {
		Order entity = repository.getById(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	public void updateData(Order entity, Order obj) {
		entity.setMoment(obj.getMoment());
		entity.setStatus(obj.getStatus());
	}
	
	public void delele(Long id) {
		repository.deleteById(id);
	}
}
