package com.alura.hotel.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.alura.hotel.dao.UsuarioDao;
import com.alura.hotel.factory.ConnectionFactory;
import com.alura.hotel.views.Login;
import com.alura.hotel.views.MenuUsuario;

public class UsuarioController implements ActionListener {

	private UsuarioDao usuarioDao;
	private Login vista;

	public UsuarioController(Login vista) {
		var factory = new ConnectionFactory();
		this.usuarioDao = new UsuarioDao(factory.recuperaConexion());
		this.vista = vista;
	}

	public boolean validarUsuario(String nombre, String clave) {
		return this.usuarioDao.validarUsuario(nombre, clave);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String nombre = vista.getNombre();
		String clave = vista.getClave();

		if (this.usuarioDao.validarUsuario(nombre, clave)) {
			MenuUsuario menuUsuario = new MenuUsuario();
			menuUsuario.setVisible(true);
			vista.dispose();
		} else {
			JOptionPane.showMessageDialog(vista, "Usuario o Clave no validos");
		}

	}

}
