package com.alura.hotel.dao;

import java.security.DrbgParameters.Reseed;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.alura.hotel.modelo.Reserva;

public class ReservaDao {

	private Connection con;

	public ReservaDao(Connection con) {
		this.con = con;
	}

	public void guardar(Reserva reserva) {
		try {
			PreparedStatement statement;
			statement = con.prepareStatement("INSERT INTO RESERVAS ( "
					+ "fecha_entrada,fecha_salida,valor,forma_de_pago) " + "VALUES (?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			try (statement) {
				statement.setObject(1, reserva.getFechaEntrada());
				statement.setObject(2, reserva.getFechaSalida());
				statement.setString(3, reserva.getValor());
				statement.setString(4, reserva.getFormaPago());

				statement.execute();

				final ResultSet resultSet = statement.getGeneratedKeys();

				try (resultSet) {
					while (resultSet.next()) {
						reserva.setId(resultSet.getInt(1));
						System.out.println(String.format("Fue insertada la reserva: %s", reserva));

					}

				}

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Reserva> mostrar() {

		List<Reserva> reservas = new ArrayList<Reserva>();

		try {

			final PreparedStatement statement = con
					.prepareStatement("SELECT id, fecha_entrada, fecha_salida, valor, forma_de_pago FROM reservas");

			try (statement) {
				statement.execute();
				final ResultSet resultSet = statement.getResultSet();

				try (resultSet) {
					while (resultSet.next()) {
						int id = resultSet.getInt("id");
						LocalDate fechaEntrada = resultSet.getDate("fecha_entrada").toLocalDate().plusDays(0);
						LocalDate fechaSalida = resultSet.getDate("fecha_salida").toLocalDate().plusDays(0);
						String valor = resultSet.getString("valor");
						String formaPago = resultSet.getString("forma_de_pago");

						Reserva reserva = new Reserva(id, fechaEntrada, fechaSalida, valor, formaPago);
						reservas.add(reserva);
					}
				}

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return reservas;
	}
	
	public List<Reserva> buscarId(String id) {


		List<Reserva> reservas = new ArrayList<Reserva>();

		try {

			final PreparedStatement statement = con
					.prepareStatement("SELECT id, fecha_entrada, fecha_salida, valor, forma_de_pago FROM reservas WHERE id = ? ");

			try (statement) {
				statement.setString(1, id);
				statement.execute();
				final ResultSet resultSet = statement.getResultSet();

				try (resultSet) {
					while (resultSet.next()) {
						int id1 = resultSet.getInt("id");
						LocalDate fechaEntrada = resultSet.getDate("fecha_entrada").toLocalDate().plusDays(1);
						LocalDate fechaSalida = resultSet.getDate("fecha_salida").toLocalDate().plusDays(1);
						String valor = resultSet.getString("valor");
						String formaPago = resultSet.getString("forma_de_pago");

						Reserva reserva = new Reserva(id1, fechaEntrada, fechaSalida, valor, formaPago);
						reservas.add(reserva);
					}
				}

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return reservas;
	}

	public void actualizar(Integer id, LocalDate fechaEntrada, LocalDate fechaSalida, String valor, String formaPago) {
		try {
			final PreparedStatement statement = con
					.prepareStatement("UPDATE reservas SET fecha_entrada=?, fecha_salida=?, valor=?, forma_de_pago=? WHERE id = ? ");
			
			try(statement){
				
				statement.setObject(1, java.sql.Date.valueOf(fechaEntrada));
				statement.setObject(2, java.sql.Date.valueOf(fechaSalida));
				statement.setString(3, valor);
				statement.setString(4, formaPago);
				statement.setInt(5, id);
				
				statement.execute();
				System.out.println("Esta en base de datos");
			}
			
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
