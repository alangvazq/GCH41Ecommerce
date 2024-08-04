package com.generation.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.Ecommerce.model.OrdenTieneProductos;
import com.generation.Ecommerce.model.OrdenTieneProductosKey;

@Repository
public interface OrdenTieneProductosRepository extends JpaRepository<OrdenTieneProductos, OrdenTieneProductosKey> {

}
