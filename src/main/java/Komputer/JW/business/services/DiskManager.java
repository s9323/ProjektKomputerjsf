package Komputer.JW.business.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import Komputer.JW.business.Disk;

@ApplicationScoped
public class DiskManager implements ManagerInterface<Disk> {

	Connection conn;
	private String url = "jdbc:postgresql://localhost:5432/postgres";

	String createTable = "CREATE TABLE Dysk (id_dysku serial primary key, nazwa_firmy varchar(30), ilosc int, pojemnosc int, cena int)";

	Statement statement;
	PreparedStatement addDyskStatement;
	PreparedStatement deleteDyskStatement;
	PreparedStatement getAllDyskStatement;
	PreparedStatement getDyskByIdStatement;
	PreparedStatement getDyskBynameStatement;

	public DiskManager() {
		try {
			conn = DriverManager.getConnection(url, "postgres", "kuba22");
			statement = conn.createStatement();

			ResultSet rs = conn.getMetaData().getTables(null, null, null, null);

			boolean tableExists = false;
			while (rs.next()) {
				String result = rs.getString("TABLE_NAME");
				if ("Dysk_pkey".equalsIgnoreCase(result)) {
					tableExists = true;
					break;

				}
			}

			if (!tableExists) {
				statement.executeUpdate(createTable);

			}

			addDyskStatement = conn
					.prepareStatement("INSERT INTO Dysk (nazwa_firmy, ilosc, pojemnosc, cena)"
							+ "VALUES (?, ?, ?, ?)");
			deleteDyskStatement = conn
					.prepareStatement("DELETE From Dysk WHERE nazwa_firmy=?");
			getAllDyskStatement = conn.prepareStatement("SELECT * FROM Dysk");
			getDyskByIdStatement = conn
					.prepareStatement("SELECT * FROM Dysk WHERE id_dysku=?");
			getDyskBynameStatement = conn
					.prepareStatement("SELECT * FROM Dysk WHERE nazwa_firmy=?");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Disk get(int id_dysku) {
		try {

			getDyskByIdStatement.setInt(1, id_dysku);
			ResultSet rs = getDyskByIdStatement.executeQuery();
			while (rs.next()) {
				return new Disk(rs.getString("nazwa_firmy"),
						rs.getInt("ilosc"), rs.getInt("pojemnosc"),
						rs.getInt("cena"));

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;

	}

	@Override
	public List<Disk> getAll() {
		List<Disk> result = new ArrayList<Disk>();
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, "postgres", "kuba22");
			getAllDyskStatement = conn.prepareStatement("SELECT * FROM Dysk");
			ResultSet rs = getAllDyskStatement.executeQuery();
			while (rs.next()) {
				Disk p = new Disk(createTable, 0, 0, 0);
				p.setNazwa_firmy(rs.getString("nazwa_firmy"));
				p.setIlosc(rs.getInt("ilosc"));
				p.setPojemnosc(rs.getInt("pojemnosc"));
				p.setCena(rs.getInt("cena"));
				p.setId(rs.getInt("id_dysku"));
				result.add(p);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean save(Disk obj) {
		try {
			addDyskStatement.setString(1, obj.getNazwa_firmy());
			addDyskStatement.setInt(2, obj.getIlosc());
			addDyskStatement.setInt(3, obj.getPojemnosc());
			addDyskStatement.setInt(4, obj.getCena());
			return addDyskStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Disk obj) {
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, "postgres", "kuba22");
			deleteDyskStatement = conn.prepareStatement("DELETE From Dysk WHERE nazwa_firmy=?");
			deleteDyskStatement.setString(1, obj.getNazwa_firmy());
			deleteDyskStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public List<Disk> search(String nazwa_firmy) {

		List<Disk> dyski = getAll();

		List<Disk> result = new ArrayList<Disk>();
		for (int i = 0; i < 5; i++) {
			if (nazwa_firmy.equals(dyski.get(i).getNazwa_firmy())) {
				result.add(dyski.get(i));
			}

		}

		return result;

	}

}