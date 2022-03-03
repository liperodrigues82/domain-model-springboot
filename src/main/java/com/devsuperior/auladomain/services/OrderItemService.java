package com.devsuperior.auladomain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.auladomain.entities.OrderItem;
import com.devsuperior.auladomain.repositories.OrderItemRepository;

@Service
public class OrderItemService {

	@Autowired
	private OrderItemRepository repository;
	
	public OrderItem insert(OrderItem obj) {
		return repository.save(obj);
	}
	
	public OrderItem findById(Long id) {
		Optional<OrderItem> obj = repository.findById(id);
		return obj.get();
	}
	
	public List<OrderItem> findAll() {
		return repository.findAll();
	}
	
	public OrderItem update(Long id, OrderItem obj) {
		OrderItem entity = repository.getById(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	public void updateData(OrderItem entity, OrderItem obj) {
		entity.setProduct(obj.getProduct());
		entity.setQuantity(obj.getQuantity());
	}
	
	public void delele(Long id) {
		repository.deleteById(id);
	}
}
