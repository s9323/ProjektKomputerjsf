package Komputer.JW.business.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import Komputer.JW.business.Disk;
import Komputer.JW.business.Drivers;


public class DriversManager implements ManagerInterface<Drivers> {

	Connection conn;
	private String url = "jdbc:postgresql://localhost:5432/postgres";

	String createTable = "CREATE TABLE Stacja_Dyskow (id_stacji serial primary key, nazwa_firmy varchar(30), ilosc int, cena int)";

	Statement statement;
	PreparedStatement addStacjaDyskStatement;
	PreparedStatement deleteStacjaDyskStatement;
	PreparedStatement getAllStacjaDyskStatement;
	PreparedStatement getStacjaDyskByIdStatement;

	public DriversManager() {
		try {
			conn = DriverManager.getConnection(url, "postgres", "kuba22");
			statement = conn.createStatement();

			ResultSet rs = conn.getMetaData().getTables(null, null, null, null);

			boolean tableExists = false;
			while (rs.next()) {
				String result = rs.getString("TABLE_NAME");
				if ("Stacja_Dyskow_pkey".equalsIgnoreCase(result)) {
					tableExists = true;
					break;

				}}

				if (!tableExists) {
					statement.executeUpdate(createTable);

				}

				addStacjaDyskStatement = conn
						.prepareStatement("INSERT INTO Stacja_Dyskow (nazwa_firmy, ilosc, cena)"
								+ "VALUES (?, ?, ?)");
				deleteStacjaDyskStatement = conn
						.prepareStatement("DELETE From Stacja_Dyskow WHERE nazwa_firmy=?");
				getAllStacjaDyskStatement = conn
						.prepareStatement("SELECT * FROM Stacja_Dyskow");
				getStacjaDyskByIdStatement = conn
						.prepareStatement("SELECT * FROM Stacja_Dyskow WHERE id_stacji=?");

		

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Drivers get(int id_stacji) {
		try {

			getStacjaDyskByIdStatement.setInt(1, id_stacji);
			ResultSet rs = getStacjaDyskByIdStatement.executeQuery();
			while (rs.next()) {
				return new Drivers(rs.getString("nazwa_firmy"),
						rs.getInt("ilosc"), rs.getInt("cena"));

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;

	}

	@Override
	public List<Drivers> getAll() {
		List<Drivers> result = new ArrayList<Drivers>();

		try {
			ResultSet rs = getAllStacjaDyskStatement.executeQuery();
			while (rs.next()) {
				Drivers p = new Drivers(createTable, 0, 0);
				p.setNazwa_firmy(rs.getString("nazwa_firmy"));
				p.setIlosc(rs.getInt("ilosc"));
				p.setCena(rs.getInt("cena"));
				p.setId(rs.getInt("id_stacji"));
				result.add(p);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}

	@Override
	public boolean save(Drivers obj) {
		try {
			addStacjaDyskStatement.setString(1, obj.getNazwa_firmy());
			addStacjaDyskStatement.setInt(2, obj.getIlosc());
			addStacjaDyskStatement.setInt(3, obj.getCena());
			return addStacjaDyskStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Drivers obj) {
		try {
			deleteStacjaDyskStatement.setString(1, obj.getNazwa_firmy());
			deleteStacjaDyskStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public List<Drivers> search(String nazwa_firmy) {
		
		List<Drivers> stacje = getAll();
		
		List<Drivers> result = new ArrayList<Drivers>();
		for(int i=0; i<5; i++)
		{
			if(nazwa_firmy.equals(stacje.get(i).getNazwa_firmy()))
			{
				result.add(stacje.get(i));
			}
			
		}
		
		return result;
		
		}
}