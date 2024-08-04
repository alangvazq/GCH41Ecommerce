package com.generation.Ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.Ecommerce.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
	Optional<Producto> findByNombre(String nombre);
}
