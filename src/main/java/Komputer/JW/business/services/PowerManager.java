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
import Komputer.JW.business.Power;


public class PowerManager implements ManagerInterface<Power> {

	Connection conn;
	private String url = "jdbc:postgresql://localhost:5432/postgres";

	String createTable = "CREATE TABLE Zasilacz (id_zasilacza serial primary key, nazwa_firmy varchar(30), ilosc int, cena int)";

	Statement statement;
	PreparedStatement addZasilaczStatement;
	PreparedStatement deleteZasilaczStatement;
	PreparedStatement getAllZasilaczStatement;
	PreparedStatement getZasilaczByIdStatement;

	public PowerManager() {
		try {
			conn = DriverManager.getConnection(url, "postgres", "kuba22");
			statement = conn.createStatement();

			ResultSet rs = conn.getMetaData().getTables(null, null, null, null);

			boolean tableExists = false;
			while (rs.next()) {
				String result = rs.getString("TABLE_NAME");
				if ("Zasilacz_pkey".equalsIgnoreCase(result)) {
					tableExists = true;
					break;

				}}

				if (!tableExists) {
					statement.executeUpdate(createTable);

				}

				addZasilaczStatement = conn
						.prepareStatement("INSERT INTO Zasilacz (nazwa_firmy, ilosc, cena)"
								+ "VALUES (?, ?, ?)");
				deleteZasilaczStatement = conn
						.prepareStatement("DELETE From Zasilacz WHERE nazwa_firmy=?");
				getAllZasilaczStatement = conn
						.prepareStatement("SELECT * FROM Zasilacz");
				getZasilaczByIdStatement = conn
						.prepareStatement("SELECT * FROM Zasilacz WHERE id_zasilacza=?");

		

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Power get(int id_plyty) {
		try {

			getZasilaczByIdStatement.setInt(1, id_plyty);
			ResultSet rs = getZasilaczByIdStatement.executeQuery();
			while (rs.next()) {
				return new Power(rs.getString("nazwa_firmy"),
						rs.getInt("ilosc"), rs.getInt("cena"));

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;

	}

	@Override
	public List<Power> getAll() {
		List<Power> result = new ArrayList<Power>();

		try {
			ResultSet rs = getAllZasilaczStatement.executeQuery();
			while (rs.next()) {
				Power p = new Power(createTable, 0, 0);
				p.setNazwa_firmy(rs.getString("nazwa_firmy"));
				p.setIlosc(rs.getInt("ilosc"));
				p.setCena(rs.getInt("cena"));
				p.setId(rs.getInt("id_zasilacza"));
				result.add(p);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}

	@Override
	public boolean save(Power obj) {
		try {
			addZasilaczStatement.setString(1, obj.getNazwa_firmy());
			addZasilaczStatement.setInt(2, obj.getIlosc());
			addZasilaczStatement.setInt(3, obj.getCena());
			return addZasilaczStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Power obj) {
		try {
			deleteZasilaczStatement.setString(1, obj.getNazwa_firmy());
			deleteZasilaczStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

public List<Power> search(String nazwa_firmy) {
		
		List<Power> zasilacz = getAll();
		
		List<Power> result = new ArrayList<Power>();
		
		for(int i=0; i<5; i++)
		{
			if(nazwa_firmy.equals(zasilacz.get(i).getNazwa_firmy()))
			{
				result.add(zasilacz.get(i));
			}
			
		}
		
		return result;
		
		}
}