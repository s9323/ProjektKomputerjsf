package Komputer.JW.business.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Komputer.JW.business.GraphicsCard;
import Komputer.JW.business.MotherBoard;


public class MotherBoardManager implements ManagerInterface<MotherBoard> {

	Connection conn;
	private String url = "jdbc:postgresql://localhost:5432/postgres";

	String createTable = "CREATE TABLE Plyta_glowna (id_plyty serial primary key, nazwa_firmy varchar(30), ilosc int, cena int)";

	Statement statement;
	PreparedStatement addPlytaStatement;
	PreparedStatement deletePlytaStatement;
	PreparedStatement getAllPlytaStatement;
	PreparedStatement getPlytaByIdStatement;

	public MotherBoardManager() {
		try {
			conn = DriverManager.getConnection(url, "postgres", "kuba22");
			statement = conn.createStatement();

			ResultSet rs = conn.getMetaData().getTables(null, null, null, null);

			boolean tableExists = false;
			while (rs.next()) {
				String result = rs.getString("TABLE_NAME");
				if ("Plyta_glowna_pkey".equalsIgnoreCase(result)) {
					tableExists = true;
					break;

				}}

				if (!tableExists) {
					statement.executeUpdate(createTable);

				}

				addPlytaStatement = conn
						.prepareStatement("INSERT INTO Plyta_glowna (nazwa_firmy, ilosc, cena)"
								+ "VALUES (?, ?, ?)");
				deletePlytaStatement = conn
						.prepareStatement("DELETE From Plyta_glowna WHERE nazwa_firmy=?");
				getAllPlytaStatement = conn
						.prepareStatement("SELECT * FROM Plyta_glowna");
				getPlytaByIdStatement = conn
						.prepareStatement("SELECT * FROM Plyta_glowna WHERE id_plyty=?");

		

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public MotherBoard get(int id_plyty) {
		try {

			getPlytaByIdStatement.setInt(1, id_plyty);
			ResultSet rs = getPlytaByIdStatement.executeQuery();
			while (rs.next()) {
				return new MotherBoard(rs.getString("nazwa_firmy"),
						rs.getInt("ilosc"), rs.getInt("cena"));

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;

	}

	@Override
	public List<MotherBoard> getAll() {
		List<MotherBoard> result = new ArrayList<MotherBoard>();

		try {
			ResultSet rs = getAllPlytaStatement.executeQuery();
			while (rs.next()) {
				MotherBoard p = new MotherBoard(createTable, 0, 0);
				p.setNazwa_firmy(rs.getString("nazwa_firmy"));
				p.setIlosc(rs.getInt("ilosc"));
				p.setCena(rs.getInt("cena"));
				p.setId(rs.getInt("id_plyty"));
				result.add(p);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}

	@Override
	public boolean save(MotherBoard obj) {
		try {
			addPlytaStatement.setString(1, obj.getNazwa_firmy());
			addPlytaStatement.setInt(2, obj.getIlosc());
			addPlytaStatement.setInt(3, obj.getCena());
			return addPlytaStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(MotherBoard obj) {
		try {
			deletePlytaStatement.setString(1, obj.getNazwa_firmy());
			deletePlytaStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

public List<MotherBoard> search(String nazwa_firmy) {
		
		List<MotherBoard> plyta = getAll();
		
		List<MotherBoard> result = new ArrayList<MotherBoard>();
		for(int i=0; i<5; i++)
		{
			if(nazwa_firmy.equals(plyta.get(i).getNazwa_firmy()))
			{
				result.add(plyta.get(i));
			}
			
		}
		
		return result;
		
		}
}