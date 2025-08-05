package net.codejava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.codejava.entity.ProductType;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long>{

}
