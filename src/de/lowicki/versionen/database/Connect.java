package de.lowicki.versionen.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import de.lowicki.versionen.main.Main;

public class Connect {
	
	Connection conn;
	private String[] versionen = {"CCleaner", "7-Zip", "Adobe Acrobat Reader DC", "Adobe Flash Player", "Notepad++", "IrfanView 4.", "PDF24 8", "PDFCreator", "VLC Media Player"};
	private HashMap<String, String> version = new HashMap<String, String>();
	
	public Connect() {
      try {
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
         String connectionURL = Main.connectionURL;
         conn = DriverManager.getConnection(connectionURL);
         if(conn != null){
        	 System.out.println("Connected");
         }
         
         for(int i = 0; i<versionen.length; i++) {
        	 String ver = getData(conn, versionen[i]);
        	 version.put(versionen[i], ver);
         }
         System.out.println("ACMP Versionen" + version);
         Main.acmpVersionen = version;
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
	

    
    
    private String getData(Connection conn, String program) {
    	try {
	        Statement statement = conn.createStatement();
	        String queryString = "SELECT TOP(1) SWName FROM SYS_SW_CLIENTCOMMANDS WHERE SWName LIKE '" + program + "%' ORDER BY SWName DESC";
	        ResultSet rs = statement.executeQuery(queryString);
	        while (rs.next()) {
	           return rs.getString(1);
	        }
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	return "Nothing";
    }

}
