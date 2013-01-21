package Komputer.JW.business.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;




public class DbAdapter implements ClientInterface<Client> {

	Connection conn;
	private String url = "jdbc:postgresql://localhost:5432/postgres";

	String createTable = "CREATE TABLE Klient (id serial primary key, name varchar(30), surname varchar(30), adress varchar(30))";

	Statement statement;
	PreparedStatement addKlientStatement;
	PreparedStatement deleteKlientStatement;
	PreparedStatement getAllKlientStatement;
	PreparedStatement getKlientByIdStatement;

	public DbAdapter() {
		try {
			conn = DriverManager.getConnection(url, "postgres", "kuba22");
			statement = conn.createStatement();

			ResultSet rs = conn.getMetaData().getTables(null, null, null, null);

			boolean tableExists = false;
			while (rs.next()) {
				String result = rs.getString("TABLE_NAME");
				if ("Klient_pkey".equalsIgnoreCase(result)) {
					tableExists = true;
					break;

				}}

				if (!tableExists) {
					statement.executeUpdate(createTable);

				}

				addKlientStatement = conn
						.prepareStatement("INSERT INTO Klient (name, surname, adress)"
								+ "VALUES (?, ?, ?)");
				deleteKlientStatement = conn
						.prepareStatement("DELETE From Klient WHERE name=?");
				getAllKlientStatement = conn
						.prepareStatement("SELECT * FROM Klient");
				getKlientByIdStatement = conn
						.prepareStatement("SELECT * FROM Klient WHERE id=?");

		

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Client get(int id) {
		try {

			getKlientByIdStatement.setInt(1, id);
			ResultSet rs = getKlientByIdStatement.executeQuery();
			while (rs.next()) {
				return new Client(rs.getString("name"),
						rs.getString("surname"), rs.getString("adress"));

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;

	}

	@Override
	public List<Client> getAll() {
		List<Client> result = new ArrayList<Client>();

		try {
			ResultSet rs = getAllKlientStatement.executeQuery();
			while (rs.next()) {
				Client p = new Client(createTable, createTable, createTable);
				p.setName(rs.getString("name"));
				p.setSurname(rs.getString("surname"));
				p.setAdress(rs.getString("adress"));
				p.setId(rs.getInt("id"));
				result.add(p);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}

	@Override
	public boolean save(Client obj) {
		try {
			addKlientStatement.setString(1, obj.getName());
			addKlientStatement.setString(2, obj.getSurname());
			addKlientStatement.setString(3, obj.getAdress());
			return addKlientStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Client obj) {
		try {
			deleteKlientStatement.setString(1, obj.getName());
			deleteKlientStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

}
