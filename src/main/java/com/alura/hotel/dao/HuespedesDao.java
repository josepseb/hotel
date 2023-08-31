package com.alura.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.alura.hotel.modelo.Huespedes;

public class HuespedesDao {
	private Connection con;

	public HuespedesDao(Connection con) {
		this.con = con;
	}

	public void guardar(Huespedes huespedes) {

		try {
			final PreparedStatement statement = con.prepareStatement(

					"INSERT INTO huespedes (nombre, apellido, "
							+ "fecha_nacimiento, nacionalidad, telefono, id_reserva) VALUES (?,?,?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS

			);

			try (statement) {
				statement.setString(1, huespedes.getNombre());
				statement.setString(2, huespedes.getApellido());
				statement.setObject(3, huespedes.getFechaNacimiento());
				statement.setString(4, huespedes.getNacionalidad());
				statement.setString(5, huespedes.getTelefono());
				statement.setInt(6, huespedes.getIdReserva());

				statement.execute();

				final ResultSet resultSet = statement.getGeneratedKeys();

				try (resultSet) {
					while (resultSet.next()) {
						huespedes.setId(resultSet.getInt(1));
						System.out.println(String.format("Se registró el huesped: %s", huespedes));

					}
				}

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
