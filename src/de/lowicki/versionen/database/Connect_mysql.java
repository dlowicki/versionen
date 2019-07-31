package de.lowicki.versionen.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

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
			this.con = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?" + "user="
					+ dbUser + "&" + "password=" + dbPass);
			System.out.println("[Connect MYSQL] Verbindung zur Datenbank erfolgreich aufgebaut!");
		} catch (ClassNotFoundException e) {
			System.out.println("[Connect MYSQL] Treiber nicht gefunden");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("[Connect MYSQL] Verbindung nicht moglich");
			System.out.println("[Connect MYSQL] SQLException: " + e.getMessage());
			System.out.println("[Connect MYSQL] SQLState: " + e.getSQLState());
			System.out.println("[Connect MYSQL] VendorError: " + e.getErrorCode());
		}
	}
	
	public void disconnect() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // Datenbanktreiber für JDBC Schnittstellen laden.
			// Verbindung zur JDBC-Datenbank herstellen.
			if(this.con != null) {
				con.close();
			}
			System.out.println("[Connect MYSQL] Verbindung getrennt");
		} catch (ClassNotFoundException e) {
			System.out.println("[Connect MYSQL] Treiber nicht gefunden");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("[Connect MYSQL] Anfrage nicht möglich!");
			System.out.println("[Connect MYSQL] SQLException: " + e.getMessage());
			System.out.println("[Connect MYSQL] SQLState: " + e.getSQLState());
			System.out.println("[Connect MYSQL] VendorError: " + e.getErrorCode());
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
	
	public HashMap<String, String> getMysqlVersionen() {
		ResultSet rs = query("SELECT name, version FROM programme");
		HashMap<String, String> temp = new HashMap<String, String>();
		try {
			while(rs.next()) {
				temp.put(rs.getString("name"), rs.getString("version"));
			}
			return temp;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public void createTable() {
		this.update("CREATE TABLE IF NOT EXISTS programme(id int NOT NULL AUTO_INCREMENT, name varchar(50) UNIQUE, version varchar(30), link varchar(120), download varchar(250), endung varchar(10), img varchar(255))");
	}
	
	public void registerProgram(String name, String version, String link, String download, String endung, String img) {
		if(getProgramDataByName(name) == false) {
			update("INSERT INTO programme (name, version, link, download, endung, img) VALUES ('" + name + "','" + version +"','" + link + "', '" + download + "','" + endung + "','" + img +"')");
			System.out.println(name + " wurde in die Datenbank eingetragen");
			return;
		}
		System.out.println(name + " existiert bereits in der Datenbank!");
		return;
	}

}
