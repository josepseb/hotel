package com.alura.hotel.controller;

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

}
