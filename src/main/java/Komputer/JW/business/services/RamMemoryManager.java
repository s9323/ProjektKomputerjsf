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
import Komputer.JW.business.RamMemory;


public class RamMemoryManager implements ManagerInterface<RamMemory> {

	Connection conn;
	private String url = "jdbc:postgresql://localhost:5432/postgres";

	String createTable = "CREATE TABLE Pamiec_ram (id_pamieci serial primary key, nazwa_firmy varchar(30), ilosc int, pojemnosc int, cena int)";

	Statement statement;
	PreparedStatement addRamStatement;
	PreparedStatement deleteRamStatement;
	PreparedStatement getAllRamStatement;
	PreparedStatement getRamByIdStatement;

	public RamMemoryManager() {
		try {
			conn = DriverManager.getConnection(url, "postgres", "kuba22");
			statement = conn.createStatement();

			ResultSet rs = conn.getMetaData().getTables(null, null, null, null);

			boolean tableExists = false;
			while (rs.next()) {
				String result = rs.getString("TABLE_NAME");
				if ("Pamiec_ram_pkey".equalsIgnoreCase(result)) {
					tableExists = true;
					break;

				}}

				if (!tableExists) {
					statement.executeUpdate(createTable);

				}

				addRamStatement = conn
						.prepareStatement("INSERT INTO Pamiec_ram (nazwa_firmy, ilosc, pojemnosc, cena)"
								+ "VALUES (?, ?, ?, ?)");
				deleteRamStatement = conn
						.prepareStatement("DELETE From Pamiec_ram WHERE nazwa_firmy=?");
				getAllRamStatement = conn
						.prepareStatement("SELECT * FROM Pamiec_ram");
				getRamByIdStatement = conn
						.prepareStatement("SELECT * FROM Pamiec_ram WHERE id_pamieci=?");

		

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public RamMemory get(int id_karty) {
		try {

			getRamByIdStatement.setInt(1, id_karty);
			ResultSet rs = getRamByIdStatement.executeQuery();
			while (rs.next()) {
				return new RamMemory(rs.getString("nazwa_firmy"), 
						rs.getInt("ilosc"), rs.getInt("pojemnosc"), rs.getInt("cena"));

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;

	}

	@Override
	public List<RamMemory> getAll() {
		List<RamMemory> result = new ArrayList<RamMemory>();

		try {
			ResultSet rs = getAllRamStatement.executeQuery();
			while (rs.next()) {
				RamMemory p = new RamMemory(createTable, 0, 0, 0);
				p.setNazwa_firmy(rs.getString("nazwa_firmy"));
				p.setIlosc(rs.getInt("ilosc"));
				p.setPojemnosc(rs.getInt("pojemnosc"));
				p.setCena(rs.getInt("cena"));
				p.setId(rs.getInt("id_pamieci"));
				result.add(p);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}

	@Override
	public boolean save(RamMemory obj) {
		try {
			addRamStatement.setString(1, obj.getNazwa_firmy());
			addRamStatement.setInt(2, obj.getIlosc());
			addRamStatement.setInt(3, obj.getPojemnosc());
			addRamStatement.setInt(4, obj.getCena());
			return addRamStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(RamMemory obj) {
		try {
			deleteRamStatement.setString(1, obj.getNazwa_karty());
			deleteRamStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

public List<RamMemory> search(String nazwa_firmy) {
		
		List<RamMemory> pamiec = getAll();
		
		List<RamMemory> result = new ArrayList<RamMemory>();
		for(int i=0; i<5; i++)
		{
			if(nazwa_firmy.equals(pamiec.get(i).getNazwa_firmy()))
			{
				result.add(pamiec.get(i));
			}
			
		}
		
		return result;
		
		}
}