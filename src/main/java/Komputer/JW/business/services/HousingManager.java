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
import Komputer.JW.business.Housing;




public class HousingManager implements ManagerInterface<Housing> {

	Connection conn;
	private String url = "jdbc:postgresql://localhost:5432/postgres";

	String createTable = "CREATE TABLE Obudowa (id_obudowy serial primary key, nazwa_firmy varchar(30), ilosc int, cena int)";

	Statement statement;
	PreparedStatement addObudowaStatement;
	PreparedStatement deleteObudowaStatement;
	PreparedStatement getAllObudowaStatement;
	PreparedStatement getObudowaByIdStatement;

	public HousingManager() {
		try {
			conn = DriverManager.getConnection(url, "postgres", "kuba22");
			statement = conn.createStatement();

			ResultSet rs = conn.getMetaData().getTables(null, null, null, null);

			boolean tableExists = false;
			while (rs.next()) {
				String result = rs.getString("TABLE_NAME");
				if ("Obudowa_pkey".equalsIgnoreCase(result)) {
					tableExists = true;
					break;

				}}

				if (!tableExists) {
					statement.executeUpdate(createTable);

				}

				addObudowaStatement = conn
						.prepareStatement("INSERT INTO Obudowa (nazwa_firmy, ilosc, cena)"
								+ "VALUES (?, ?, ?)");
				deleteObudowaStatement = conn
						.prepareStatement("DELETE From Obudowa WHERE nazwa_firmy=?");
				getAllObudowaStatement = conn
						.prepareStatement("SELECT * FROM Obudowa");
				getObudowaByIdStatement = conn
						.prepareStatement("SELECT * FROM Obudowa WHERE id_obudowy=?");

		

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Housing get(int id_stacji) {
		try {

			getObudowaByIdStatement.setInt(1, id_stacji);
			ResultSet rs = getObudowaByIdStatement.executeQuery();
			while (rs.next()) {
				return new Housing(rs.getString("nazwa_firmy"),
						rs.getInt("ilosc"), rs.getInt("cena"));

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;

	}

	@Override
	public List<Housing> getAll() {
		List<Housing> result = new ArrayList<Housing>();

		try {
			ResultSet rs = getAllObudowaStatement.executeQuery();
			while (rs.next()) {
				Housing p = new Housing(createTable, 0, 0);
				p.setNazwa_firmy(rs.getString("nazwa_firmy"));
				p.setIlosc(rs.getInt("ilosc"));
				p.setCena(rs.getInt("cena"));
				p.setId(rs.getInt("id_obudowy"));
				result.add(p);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}

	@Override
	public boolean save(Housing obj) {
		try {
			addObudowaStatement.setString(1, obj.getNazwa_firmy());
			addObudowaStatement.setInt(2, obj.getIlosc());
			addObudowaStatement.setInt(3, obj.getCena());
			return addObudowaStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Housing obj) {
		try {
			deleteObudowaStatement.setString(1, obj.getNazwa_firmy());
			deleteObudowaStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

public List<Housing> search(String nazwa_firmy) {
		
		List<Housing> obudowa = getAll();
		
		List<Housing> result = new ArrayList<Housing>();
		for(int i=0; i<5; i++)
		{
			if(nazwa_firmy.equals(obudowa.get(i).getNazwa_firmy()))
			{
				result.add(obudowa.get(i));
			}
			
		}
		
		return result;
		
		}
}
