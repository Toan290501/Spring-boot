package org.example.spring_boot.repositories;

import org.example.spring_boot.models.Products;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Products, Integer> {
    List<Products> findByName(String name);
}
