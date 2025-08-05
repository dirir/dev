package net.codejava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.codejava.entity.ProductType;
import net.codejava.repository.ProductTypeRepository;

@Service
@Transactional
public class ProductTypeService {
	
	@Autowired
	private ProductTypeRepository repo;
	
	public List<ProductType> listAll() {
		return repo.findAll();
	}
	
	public void save(ProductType productType) {
		repo.save(productType);
	}
	
	public ProductType get(long id) {
		return repo.findById(id).get();
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}
}
