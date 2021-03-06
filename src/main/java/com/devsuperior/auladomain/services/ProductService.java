package com.devsuperior.auladomain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.devsuperior.auladomain.entities.Product;
import com.devsuperior.auladomain.repositories.ProductRepository;
import com.devsuperior.auladomain.services.exceptions.DatabaseException;
import com.devsuperior.auladomain.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	public Product insert(Product obj) {
		return repository.save(obj);
	}

	public Product findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public List<Product> findAll() {
		return repository.findAll();
	}

	public Product update(Long id, Product obj) {
		Product entity = repository.getById(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	public void updateData(Product entity, Product obj) {
		entity.setName(obj.getName());
		entity.setPrice(obj.getPrice());
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
