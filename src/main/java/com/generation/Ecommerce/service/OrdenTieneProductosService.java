package com.generation.Ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.Ecommerce.model.OrdenTieneProductos;
import com.generation.Ecommerce.model.OrdenTieneProductosKey;
import com.generation.Ecommerce.repository.OrdenTieneProductosRepository;

@Service
public class OrdenTieneProductosService {
	@Autowired
	private OrdenTieneProductosRepository ordenTieneProductosRepository;

	public List<OrdenTieneProductos> findAll() {
		return ordenTieneProductosRepository.findAll();
	}

	public Optional<OrdenTieneProductos> findById(OrdenTieneProductosKey id) {
		return ordenTieneProductosRepository.findById(id);
	}

	public OrdenTieneProductos deleteById(OrdenTieneProductosKey id) {
		Optional<OrdenTieneProductos> optionalOrdenTieneProductos = ordenTieneProductosRepository.findById(id);
		if (optionalOrdenTieneProductos.isPresent()) {
			OrdenTieneProductos ordenTieneProductos = optionalOrdenTieneProductos.get();
			ordenTieneProductosRepository.deleteById(id);
			return ordenTieneProductos;
		}
		return null;
	}

	public OrdenTieneProductos save(OrdenTieneProductos ordenTieneProductos) {
		return ordenTieneProductosRepository.save(ordenTieneProductos);
	}

	public OrdenTieneProductos updateCantidad(OrdenTieneProductosKey id, int cantidad) {
		Optional<OrdenTieneProductos> optionalOrdenTieneProductos = ordenTieneProductosRepository.findById(id);
		if (optionalOrdenTieneProductos.isPresent()) {
			OrdenTieneProductos ordenTieneProductos = optionalOrdenTieneProductos.get();
			ordenTieneProductos.setCantidad(cantidad);
			return ordenTieneProductosRepository.save(ordenTieneProductos);
		}
		return null;
	}
}
