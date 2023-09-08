package com.alura.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.alura.hotel.modelo.Huespedes;
import com.alura.hotel.modelo.Reserva;

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

	public List<Huespedes> mostrar() {

		List<Huespedes> huespedes = new ArrayList<Huespedes>();

		try {

			final PreparedStatement statement = con
					.prepareStatement("SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, "
							+ "telefono, id_reserva FROM huespedes");

			try (statement) {
				statement.execute();
				final ResultSet resultSet = statement.getResultSet();

				try (resultSet) {
					while (resultSet.next()) {
						int id = resultSet.getInt("id");
						String nombre = resultSet.getString("nombre");
						String apellido = resultSet.getString("apellido");
						LocalDate fechaNacimiento = resultSet.getDate("fecha_nacimiento").toLocalDate().plusDays(0);
						String nacionalidad = resultSet.getString("nacionalidad");
						String telefono = resultSet.getString("telefono");
						int idReserva = resultSet.getInt("id_reserva");
						
						Huespedes huesped = new Huespedes(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva);
						huespedes.add(huesped);
					}
				}

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return huespedes;
	}

	public List<Huespedes> buscarId(String id) {

		List<Huespedes> huespedes = new ArrayList<Huespedes>();

		try {

			final PreparedStatement statement = con
					.prepareStatement("SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, "
							+ "telefono, id_reserva FROM huespedes WHERE id = ? ");

			try (statement) {
				
				statement.setString(1, id);
				statement.execute();
				
				final ResultSet resultSet = statement.getResultSet();

				try (resultSet) {
					while (resultSet.next()) {
						int id1 = resultSet.getInt("id");
						String nombre = resultSet.getString("nombre");
						String apellido = resultSet.getString("apellido");
						LocalDate fechaNacimiento = resultSet.getDate("fecha_nacimiento").toLocalDate().plusDays(0);
						String nacionalidad = resultSet.getString("nacionalidad");
						String telefono = resultSet.getString("telefono");
						int idReserva = resultSet.getInt("id_reserva");
						
						Huespedes huesped = new Huespedes(id1, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva);
						huespedes.add(huesped);
					}
				}

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return huespedes;
	}

}
