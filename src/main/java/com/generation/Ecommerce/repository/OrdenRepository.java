package com.generation.Ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.Ecommerce.model.Orden;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {
	Optional<Orden> findByIdOrden(Long idOrden);
}
