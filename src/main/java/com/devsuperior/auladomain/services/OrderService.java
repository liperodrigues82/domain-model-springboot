package com.devsuperior.auladomain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.devsuperior.auladomain.entities.Order;
import com.devsuperior.auladomain.repositories.OrderRepository;
import com.devsuperior.auladomain.services.exceptions.DatabaseException;
import com.devsuperior.auladomain.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	public Order insert(Order obj) {
		return repository.save(obj);
	}

	public Order findById(Long id) {
		Optional<Order> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
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
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
}
