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
import Komputer.JW.business.GraphicsCard;


public class GraphicsCardManager implements ManagerInterface<GraphicsCard> {

	Connection conn;
	private String url = "jdbc:postgresql://localhost:5432/postgres";

	String createTable = "CREATE TABLE Karta_graficzna (id_karty serial primary key, nazwa_karty varchar(30), nazwa_firmy varchar(30), ilosc int, pojemnosc int, cena int)";

	Statement statement;
	PreparedStatement addKartaStatement;
	PreparedStatement deleteKartaStatement;
	PreparedStatement getAllKartaStatement;
	PreparedStatement getKartaByIdStatement;

	public GraphicsCardManager() {
		try {
			conn = DriverManager.getConnection(url, "postgres", "kuba22");
			statement = conn.createStatement();

			ResultSet rs = conn.getMetaData().getTables(null, null, null, null);

			boolean tableExists = false;
			while (rs.next()) {
				String result = rs.getString("TABLE_NAME");
				if ("Karta_graficzna_pkey".equalsIgnoreCase(result)) {
					tableExists = true;
					break;

				}}

				if (!tableExists) {
					statement.executeUpdate(createTable);

				}

				addKartaStatement = conn
						.prepareStatement("INSERT INTO Karta_graficzna (nazwa_karty, nazwa_firmy, ilosc, pojemnosc, cena)"
								+ "VALUES (?, ?, ?, ?, ?)");
				deleteKartaStatement = conn
						.prepareStatement("DELETE From Karta_graficzna WHERE nazwa_karty=?");
				getAllKartaStatement = conn
						.prepareStatement("SELECT * FROM Karta_graficzna");
				getKartaByIdStatement = conn
						.prepareStatement("SELECT * FROM Karta_graficzna WHERE id_karty=?");

		

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public GraphicsCard get(int id_karty) {
		try {

			getKartaByIdStatement.setInt(1, id_karty);
			ResultSet rs = getKartaByIdStatement.executeQuery();
			while (rs.next()) {
				return new GraphicsCard(rs.getString("nazwa_karty"),
						rs.getString("nazwa_firmy"), rs.getInt("ilosc"), rs.getInt("pojemnosc"), rs.getInt("cena"));

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;

	}

	@Override
	public List<GraphicsCard> getAll() {
		List<GraphicsCard> result = new ArrayList<GraphicsCard>();

		try {
			ResultSet rs = getAllKartaStatement.executeQuery();
			while (rs.next()) {
				GraphicsCard p = new GraphicsCard(createTable, createTable, 0, 0, 0);
				p.setNazwa_karty(rs.getString("nazwa_karty"));
				p.setNazwa_firmy(rs.getString("nazwa_firmy"));
				p.setIlosc(rs.getInt("ilosc"));
				p.setPojemnosc(rs.getInt("pojemnosc"));
				p.setCena(rs.getInt("cena"));
				p.setId(rs.getInt("id_karty"));
				result.add(p);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}

	@Override
	public boolean save(GraphicsCard obj) {
		try {
			addKartaStatement.setString(1, obj.getNazwa_karty());
			addKartaStatement.setString(2, obj.getNazwa_firmy());
			addKartaStatement.setInt(3, obj.getIlosc());
			addKartaStatement.setInt(4, obj.getPojemnosc());
			addKartaStatement.setInt(5, obj.getCena());
			return addKartaStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(GraphicsCard obj) {
		try {
			deleteKartaStatement.setString(1, obj.getNazwa_karty());
			deleteKartaStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public List<GraphicsCard> search(String nazwa_firmy) {
		
		List<GraphicsCard> karta = getAll();
		
		List<GraphicsCard> result = new ArrayList<GraphicsCard>();
		for(int i=0; i<5; i++)
		{
			if(nazwa_firmy.equals(karta.get(i).getNazwa_firmy()))
			{
				result.add(karta.get(i));
			}
			
		}
		
		return result;
		
		}	
}