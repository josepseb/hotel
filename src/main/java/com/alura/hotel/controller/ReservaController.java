package com.alura.hotel.controller;

import java.time.LocalDate;
import java.util.List;

import com.alura.hotel.dao.ReservaDao;
import com.alura.hotel.dao.UsuarioDao;
import com.alura.hotel.factory.ConnectionFactory;
import com.alura.hotel.modelo.Reserva;

public class ReservaController {

	private ReservaDao reservaDao;

	public ReservaController() {

		var factory = new ConnectionFactory();
		this.reservaDao = new ReservaDao(factory.recuperaConexion());

	}

	public void guardar(Reserva reserva) {
		this.reservaDao.guardar(reserva);
	}

	public List<Reserva> mostrar() {
		return this.reservaDao.mostrar();
	}

	public List<Reserva> buscar(String id) {
		return this.reservaDao.buscarId(id);
	}

	public void actualizarReserva(Integer id, LocalDate fechaEntrada, LocalDate fechaSalida, String valor,
			String formaPago) {
		this.reservaDao.actualizar(id, fechaEntrada, fechaSalida, valor, formaPago);
	}
}
