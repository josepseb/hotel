package com.alura.hotel.controller;

import java.time.LocalDate;
import java.util.List;

import com.alura.hotel.dao.HuespedesDao;
import com.alura.hotel.factory.ConnectionFactory;
import com.alura.hotel.modelo.Huespedes;

public class HuespedesController {

	private HuespedesDao huespedesDao;

	public HuespedesController() {
		var factory = new ConnectionFactory();
		this.huespedesDao = new HuespedesDao(factory.recuperaConexion());
	}

	public void guardar(Huespedes huespedes) {
		this.huespedesDao.guardar(huespedes);
	}
	
	public List<Huespedes> mostrarHuespedes() {
		return this.huespedesDao.mostrar();
	}
	
	public List<Huespedes> buscarHuesped(String id) {
		return this.huespedesDao.buscarId(id);
	}
	
	public void actualizar(Integer id, String nombre, String apellido, LocalDate fechaNacimiento,
			String nacionalidad, String telefono, Integer idReserva) {
		
		this.huespedesDao.actualizarHuesped(
				id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva);		
		
	}
	
	public void eliminar(Integer id) {
		this.huespedesDao.eliminar(id);
	}

}
