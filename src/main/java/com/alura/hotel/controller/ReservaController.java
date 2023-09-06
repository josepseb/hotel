package com.alura.hotel.controller;

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
	
	public List<Reserva> mostrar(){
		return this.reservaDao.mostrar();
	}

}
