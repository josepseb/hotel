package com.alura.hotel.dao;

import java.security.DrbgParameters.Reseed;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

}
