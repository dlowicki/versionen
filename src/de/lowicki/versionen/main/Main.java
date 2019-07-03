package de.lowicki.versionen.main;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import de.lowicki.versionen.database.Connect;
import de.lowicki.versionen.gui.GUI;
import de.lowicki.versionen.link.Compare;
import de.lowicki.versionen.link.Versionen;
import de.lowicki.versionen.manager.Load;


public class Main {
	
	private Load config;
	private Versionen version;
	private GUI gui;
	public static HashMap<String, String> urls = new HashMap<String, String>();
	public static HashMap<String, String> chipVersionen = new HashMap<String, String>();
	public static HashMap<String, String> acmpVersionen = new HashMap<String, String>();
	public static String connectionURL;
	public static Path path = Paths.get("C:\\ProgramData\\Chip Versionen\\config.ini");
	public static Boolean configStatus;
  
  public static void main(String[] args) {
	  GUI x = new GUI();
	  new Load();
	  new Connect();
	  new Versionen();
	  new Compare(acmpVersionen, chipVersionen);
	  x.initComponents();
	  
  }
  
  public Load getConfig() {
    return config;
  }
  
  public Versionen getVersion() {
	return version;
  }
  
  public GUI getGui() {
	return gui;
  }

}
