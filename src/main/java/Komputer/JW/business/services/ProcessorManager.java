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
import Komputer.JW.business.Processor;


public class ProcessorManager implements ManagerInterface<Processor> {

	Connection conn;
	private String url = "jdbc:postgresql://localhost:5432/postgres";

	String createTable = "CREATE TABLE Procesor (id_procesora serial primary key, nazwa_firmy varchar(30), ilosc int, ilosc_rdzeni int, cena int)";

	Statement statement;
	PreparedStatement addProcessorStatement;
	PreparedStatement deleteProcessorStatement;
	PreparedStatement getAllProcessorStatement;
	PreparedStatement getProcessorByIdStatement;

	public ProcessorManager() {
		try {
			conn = DriverManager.getConnection(url, "postgres", "kuba22");
			statement = conn.createStatement();

			ResultSet rs = conn.getMetaData().getTables(null, null, null, null);

			boolean tableExists = false;
			while (rs.next()) {
				String result = rs.getString("TABLE_NAME");
				if ("Procesor_pkey".equalsIgnoreCase(result)) {
					tableExists = true;
					break;

				}}

				if (!tableExists) {
					statement.executeUpdate(createTable);

				}

				addProcessorStatement = conn
						.prepareStatement("INSERT INTO Procesor (nazwa_firmy, ilosc, ilosc_rdzeni, cena)"
								+ "VALUES (?, ?, ?, ?)");
				deleteProcessorStatement = conn
						.prepareStatement("DELETE From Procesor WHERE nazwa_firmy=?");
				getAllProcessorStatement = conn
						.prepareStatement("SELECT * FROM Procesor");
				getProcessorByIdStatement = conn
						.prepareStatement("SELECT * FROM Procesor WHERE id_procesora=?");

		

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Processor get(int id_procesora) {
		try {

			getProcessorByIdStatement.setInt(1, id_procesora);
			ResultSet rs = getProcessorByIdStatement.executeQuery();
			while (rs.next()) {
				return new Processor(rs.getString("nazwa_firmy"),
						rs.getInt("ilosc"), rs.getInt("ilosc_rdzeni"), rs.getInt("cena"));

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;

	}

	@Override
	public List<Processor> getAll() {
		List<Processor> result = new ArrayList<Processor>();

		try {
			ResultSet rs = getAllProcessorStatement.executeQuery();
			while (rs.next()) {
				Processor p = new Processor(createTable, 0, 0, 0);
				p.setNazwa_firmy(rs.getString("nazwa_firmy"));
				p.setIlosc(rs.getInt("ilosc"));
				p.setIlosc_rdzeni(rs.getInt("ilosc_rdzeni"));
				p.setCena(rs.getInt("cena"));
				p.setId(rs.getInt("id_procesora"));
				result.add(p);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}

	@Override
	public boolean save(Processor obj) {
		try {
			addProcessorStatement.setString(1, obj.getNazwa_firmy());
			addProcessorStatement.setInt(2, obj.getIlosc());
			addProcessorStatement.setInt(3, obj.getIlosc_rdzeni());
			addProcessorStatement.setInt(4, obj.getCena());
			return addProcessorStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Processor obj) {
		try {
			deleteProcessorStatement.setString(1, obj.getNazwa_firmy());
			deleteProcessorStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

public List<Processor> search(String nazwa_firmy) {
		
		List<Processor> procesor = getAll();
		
		List<Processor> result = new ArrayList<Processor>();
		for(int i=0; i<5; i++)
		{
			if(nazwa_firmy.equals(procesor.get(i).getNazwa_firmy()))
			{
				result.add(procesor.get(i));
			}
			
		}
		
		return result;
		
		}
}