package com.generation.Ecommerce.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.Ecommerce.model.Orden;
import com.generation.Ecommerce.repository.OrdenRepository;

@Service
public class OrdenService {
	public final OrdenRepository ordenRepository;

	@Autowired
	public OrdenService(OrdenRepository ordenRepository) {
		this.ordenRepository = ordenRepository;
	}// constructor

	public List<Orden> getAllOrdenes() {
		return ordenRepository.findAll();
	}// getAllOrdenes

	public Orden getOrden(Long id) {
		return ordenRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("La orden con el id [" + id + "] no existe"));
	}// getOrden

	public Orden deleteOrden(Long id) {
		Orden tmpOrden = null;
		if (ordenRepository.existsById(id)) {
			tmpOrden = ordenRepository.findById(id).get();
			ordenRepository.deleteById(id);
		}
		return tmpOrden;
	}// deleteOrden

	public Orden addOrden(Orden orden) {
		Optional<Orden> tmpOrden = ordenRepository.findByIdOrden(orden.getIdOrden());
		if (tmpOrden.isEmpty()) {
			return ordenRepository.save(orden);
		} else {
			System.out.println("La orden con el id [" + orden.getIdOrden() + "] ya existe");
			return null;
		}
	}// addOrden

	public Orden updateOrden(Long id, Date date, Double price, Integer idUser) {
		Orden tmpOrden = null;
		if (ordenRepository.existsById(id)) {
			Orden orden = ordenRepository.findById(id).get();
			if (date != null) {
				orden.setFecha(date);
			}
			if (price != null) {
				orden.setTotal(price);
			}
			if (idUser != null) {
				orden.setUsuarios_id(idUser);
			}
			ordenRepository.save(orden);
			tmpOrden = orden;
		} // if
		return tmpOrden;
	}// UpdateUorden
}
