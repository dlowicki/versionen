package de.lowicki.versionen.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.lowicki.versionen.main.Main;

public class Connect_mysql {

	private Connection con = null;
	private String dbHost = Main.mysql.get(0); // Hostname
	private String dbPort = Main.mysql.get(1); // Port -- Standard: 3306
	private String dbName = Main.mysql.get(2); // Datenbankname
	private String dbUser = Main.mysql.get(3); // Datenbankuser
	private String dbPass = Main.mysql.get(4); // Datenbankpasswort

	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // Datenbanktreiber für JDBC Schnittstellen laden.
			// Verbindung zur JDBC-Datenbank herstellen.
			con = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?" + "user="
					+ dbUser + "&" + "password=" + dbPass);
			System.out.println("Verbindung erfolgreich");
		} catch (ClassNotFoundException e) {
			System.out.println("Treiber nicht gefunden");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Verbindung nicht moglich");
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
	}

	private Connection getInstance() {
		if (con == null) {
			System.out.println("Verbindung wurde unterbrochen!");
		}
		return con;
	}

	public void update(String qry) {
		try {
			Statement st = (Statement) getInstance().createStatement();
			st.executeUpdate(qry);
			st.close();
		} catch (Exception localException) {
		}
	}

	public ResultSet query(String qry) {
		ResultSet rs = null;
		try {
			Statement st = (Statement) getInstance().createStatement();
			rs = st.executeQuery(qry);
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return rs;
	}

	public boolean getProgramDataByName(String name) {
		ResultSet rs = query("SELECT * FROM programme WHERE name = '" + name + "' LIMIT 1");
		try {
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void registerProgram(String name, String email, String pw) {
		if(getProgramDataByName(name) == false) {
			update("INSERT INTO programme (name, link, img) VALUES ('" + name + "','" + email + "', '" + pw + "')");
			System.out.println(name + " wurde in die Datenbank eingetragen");
			return;
		}
		System.out.println(name + " existiert bereits in der Datenbank!");
		return;
	}

}
