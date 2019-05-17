package de.lowicki.versionen.main;

import java.util.ArrayList;
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
	public static HashMap<String, String> versionen = new HashMap<String, String>();
	public static ArrayList<String> acmpVersionen = new ArrayList<String>();
	public static String connectionURL;
  
  public static void main(String[] args) {
	  new Load();
	  new Connect();
	  new Versionen();
	  new Compare(acmpVersionen, versionen);
	  
	  //new GUI();
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
