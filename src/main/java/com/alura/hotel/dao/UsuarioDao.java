package com.alura.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDao {

	private Connection con;

	public UsuarioDao(Connection con) {
		this.con = con;
	}

	public boolean validarUsuario(String nombre, String clave) {

		try {
			final PreparedStatement statement = con
					.prepareStatement("SELECT * FROM usuarios WHERE nombre = ? AND clave = ? ");

			try (statement) {
				statement.setString(1, nombre);
				statement.setString(2, clave);
				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				try (resultSet) {
					return resultSet.next();
				}

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
