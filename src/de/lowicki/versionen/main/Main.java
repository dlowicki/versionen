package de.lowicki.versionen.main;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import de.lowicki.versionen.database.Connect_mysql;
import de.lowicki.versionen.download.ChipForwardUrl;
import de.lowicki.versionen.gui.StartGUI;
import de.lowicki.versionen.link.Versionen;
import de.lowicki.versionen.manager.Load;


public class Main {
	
	
	public static State state;
	public static HashMap<String, String> urls = new HashMap<String, String>();
	public static HashMap<String, String> downloads = new HashMap<String, String>();
	public static HashMap<String, String> aktualisieren = new HashMap<String, String>();
	public static HashMap<String, String> chipVersionen = new HashMap<String, String>();
	public static HashMap<String, String> acmpVersionen = new HashMap<String, String>();
	public static ArrayList<String> mysql = new ArrayList<String>();
	public static String connectionURL;
	
	public static Path pathConfig = Paths.get("C:\\ProgramData\\Chip Versionen\\config.ini");
	public static Path pathDownloads = Paths.get("C:\\ProgramData\\Chip Versionen\\downloads.ini");
	public static Path pathDir = Paths.get(System.getProperty("user.home") + "\\Desktop\\downloads\\");
	
	public static Boolean configStatus;
	public static Boolean configReady = false;
	public static Boolean downloadStop = false;
  
  public static void main(String[] args) {
	  StartGUI x = new StartGUI();
	  x.StartGUI();
	  
	  new Load();
	  //new Connect_acmp();
	  new Versionen(); // Aktualisiert die Daten von Chip.de und speichert die Daten
	  //new Compare(acmpVersionen, chipVersionen); // Vergleicht die Daten von ACMP und Chip.de
	  x.add("Config wurde geladen\r\n");
	  new ChipForwardUrl();
	  
	  if(Main.getState() == State.AUSWAHL)
		  x.add("Download Seiten von Chip geladen");
	  
  }
  
  public static String getDate() {
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); 
	    LocalDateTime myDateObj = LocalDateTime.now(); 
	    String formattedDate = myDateObj.format(myFormatObj); 
		return formattedDate;
  }
  
  public static State getState() {
	return state;
  }
  
  public static void setState(State state) {
	Main.state = state;
}
    
  private void loadConfig() {
	  Connect_mysql mysql = new Connect_mysql();
	  mysql.connect();
	  mysql.registerProgram("CCleaner", "https://www.chip.de/downloads/CCleaner_16317939.html", "not found");
	  mysql.registerProgram("Notepad++", "https://www.chip.de/downloads/Notepad_12996935.html", "not found");
	  mysql.registerProgram("PDFCreator", "https://www.chip.de/downloads/PDFCreator_13009777.html", "not found");
	  mysql.registerProgram("PDF24", "https://www.chip.de/downloads/PDF24-Creator_43805654.html", "not found");
	  mysql.registerProgram("VLC Media Player", "https://www.chip.de/downloads/VLC-player-64-Bit_53513913.html", "not found");
	  mysql.registerProgram("7-Zip", "https://www.chip.de/downloads/7-Zip-64-Bit_38851222.html", "not found");
	  mysql.registerProgram("IrfanView", "https://www.chip.de/downloads/IrfanView-64-Bit_81722226.html", "not found");
	  mysql.registerProgram("Adobe Acrobat Reader DC", "https://www.chip.de/downloads/Adobe-Acrobat-Reader-DC_12998358.html", "not found");
	  mysql.registerProgram("Adobe Flash Player", "https://www.chip.de/downloads/Adobe-Flash-Player_13003561.html", "not found");
  }

}
