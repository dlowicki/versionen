package de.lowicki.versionen.main;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import de.lowicki.versionen.database.Connect_acmp;
import de.lowicki.versionen.database.Connect_mysql;
import de.lowicki.versionen.download.ChipForwardUrl;
import de.lowicki.versionen.gui.StartGUI;
import de.lowicki.versionen.link.Compare;
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

	public static String plattform;

	public static Path pathConfig = Paths.get("C:\\ProgramData\\Chip Versionen\\config.ini");
	public static Path pathDownloads = Paths.get("C:\\ProgramData\\Chip Versionen\\downloads.ini");
	public static Path pathDir = Paths.get(System.getProperty("user.home") + "\\Desktop\\downloads\\");

	public static Boolean configStatus;
	public static Boolean configReady = false;
	public static Boolean downloadStop = false;

	public static void main(String[] args) {
		StartGUI x = new StartGUI();
		x.StartGUI();
		new Load("nothing");
		new Connect_acmp();
		/*
		 * new Load(); loadConfig();
		 * 
		 * new Versionen(); // Aktualisiert die Daten von Chip.de und speichert die
		 * Daten x.add("Config wurde geladen\r\n"); 
		 * new Compare(acmpVersionen, chipVersionen); // Vergleicht die Daten von ACMP und Chip.de
		 * x.add("Versionen wurden abgeglichen\r\n"); new ChipForwardUrl();
		 * 
		 * if(Main.getState() == State.AUSWAHL)
		 * x.add("Download Seiten von Chip geladen\r\n");
		 */
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

	public static void loadConfig() {
		Connect_mysql mysql = new Connect_mysql();
		mysql.connect();
		mysql.createTable();
		mysql.registerProgram("CCleaner", "CCleaner 5.60", "https://www.chip.de/downloads/CCleaner_16317939.html",
				"https://www.chip.de/downloads/CCleaner_16317939.html", ".exe", "not found");
		mysql.registerProgram("Notepad++", "Notepad++ 7.7.1", "https://www.chip.de/downloads/Notepad_12996935.html",
				"https://www.chip.de/downloads/7-Zip-64-Bit_38851222.html", ".zip", "not found");
		// mysql.registerProgram("PDFCreator", "3.3.0",
		// "https://www.chip.de/downloads/PDFCreator_13009777.html", "not found");
		mysql.registerProgram("PDF24", "PDF24 8.9.1", "https://www.chip.de/downloads/PDF24-Creator_43805654.html",
				"https://www.chip.de/downloads/PDF24-Creator_43805654.html", ".exe", "not found");
		mysql.registerProgram("VLC Media Player", "VLC Media Player 3.0.7.1",
				"https://www.chip.de/downloads/VLC-player-64-Bit_53513913.html",
				"https://www.chip.de/downloads/VLC-player-64-Bit_53513913.html", ".exe", "not found");
		mysql.registerProgram("7-Zip", "7-Zip 19.00", "https://www.chip.de/downloads/7-Zip-64-Bit_38851222.html",
				"https://www.chip.de/downloads/7-Zip-64-Bit_38851222.html", ".msi", "not found");
		mysql.registerProgram("IrfanView", "IrfanView 4.53",
				"https://www.chip.de/downloads/IrfanView-64-Bit_81722226.html",
				"https://get.load-balance.net/?ZhokgJcAEdgl3GR1oTHQlf31p", ".exe", "not found");
		mysql.registerProgram("Adobe Acrobat Reader DC", "Adobe Acrobat Reader DC 2019.012.20034",
				"https://www.chip.de/downloads/Adobe-Acrobat-Reader-DC_12998358.html",
				"ftp://ftp.adobe.com/pub/adobe/reader/win/AcrobatDC/1901220034/AcroRdrDC1901220034_de_DE.exe", ".exe",
				"not found");
		mysql.registerProgram("Adobe Flash Player", "Adobe Flash Player 32.0.0.223",
				"https://www.chip.de/downloads/Adobe-Flash-Player_13003561.html",
				"https://fpdownload.macromedia.com/get/flashplayer/distyfp/current/win/install_flash_player_32_active_x.msi",
				".msi", "not found");
		mysql.disconnect();
	}

}
